package org.mifos.mifospaymentbridge.controller;

import org.mifos.mifospaymentbridge.Util.StatusCategory;
import org.mifos.mifospaymentbridge.Util.TransactionStatus;
import org.mifos.mifospaymentbridge.mifos.MifosService;
import org.mifos.mifospaymentbridge.mifos.domain.loan.Loan;
import org.mifos.mifospaymentbridge.model.InboundRequest;
import org.mifos.mifospaymentbridge.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.web.bind.annotation.*;
import javax.jms.Message;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;
import java.io.IOException;

@RestController
@EnableJms
public class InboundController {

    @Autowired
    private MifosService mifosService;

    @Autowired
    private ConfigurableApplicationContext context;


    /**
     * Web service to get loan account for fineract.
     * @param loanAccNo
     * @return
     */
    @RequestMapping(value = "/loans", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Loan> getLoanAccount(@RequestParam(value = "loanAccNo", required=true) String loanAccNo){
        Loan loanAccount = null;

        try {
            loanAccount = mifosService.getLoanAccount(loanAccNo, true, "default");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(loanAccount, HttpStatus.OK);
    }

    @RequestMapping(value = "/inbound/requests", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Status> acceptInboundRequest(@RequestBody InboundRequest inboundRequest){
        Status receptionStatus = null;

        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        jmsTemplate.send("inboundAcceptor", new MessageCreator(){
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(inboundRequest);
            }
        });

        if(inboundRequest != null){
            receptionStatus = new Status();
            receptionStatus.setCode(String.valueOf(TransactionStatus.REQUEST_RECEPTION_SUCCESS_CODE));
            receptionStatus.setDescription(TransactionStatus.REQUEST_RECEPTION_SUCCESS);
            receptionStatus.setStatusCategory(StatusCategory.GATEWAY_CATEGORY);
        }



        return new ResponseEntity<>(receptionStatus, HttpStatus.OK);
    }

    @Bean
    public JmsListenerContainerFactory<?> inboundFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);

        return factory;
    }
}
