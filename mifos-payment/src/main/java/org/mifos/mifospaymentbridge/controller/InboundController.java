package org.mifos.mifospaymentbridge.controller;

import org.mifos.mifospaymentbridge.MifosPaymentApplication;
import org.mifos.mifospaymentbridge.Util.StatusCategory;
import org.mifos.mifospaymentbridge.Util.TransactionStatus;
import org.mifos.mifospaymentbridge.integration.OutboundMessageReceiver;
import org.mifos.mifospaymentbridge.mifos.MifosService;
import org.mifos.mifospaymentbridge.mifos.domain.loan.Loan;
import org.mifos.mifospaymentbridge.mifos.domain.loan.LoanAccountRequest;
import org.mifos.mifospaymentbridge.mifos.domain.loan.LoanAccountSearchResult;
import org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit.AccountDepositRequest;
import org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit.RecurringDepositAccount;
import org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit.SavingsAccount;
import org.mifos.mifospaymentbridge.model.InboundRequest;
import org.mifos.mifospaymentbridge.model.Microfinance;
import org.mifos.mifospaymentbridge.model.OutboundRequest;
import org.mifos.mifospaymentbridge.model.Status;
import org.mifos.mifospaymentbridge.services.InboundRequestService;
import org.mifos.mifospaymentbridge.services.MicrofinanceService;
import org.mifos.mifospaymentbridge.services.OutboundRequestService;
import org.mifos.mifospaymentbridge.services.StatusService;
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
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.web.bind.annotation.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.jms.*;
import java.io.IOException;

@RestController
@EnableJms
public class InboundController {

    @Autowired
    private MifosService mifosService;

    @Autowired
    private ConfigurableApplicationContext context;

    @Autowired
    private InboundRequestService inboundRequestService;

    @Autowired
    private OutboundRequestService outboundRequestService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private MicrofinanceService mfiService;

    private Microfinance mfi;

    @Autowired
    private OutboundMessageReceiver outboundMessageReceiver;


    /**
     * Web service to get a recurring deposit account
     * @param depositAcc
     * @return
     */
    @RequestMapping(value = "/recurringDepositAccounts", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RecurringDepositAccount> getRecurringDepositAccount(@RequestBody AccountDepositRequest depositAcc, @RequestParam(value="tenant") String tenant){
        RecurringDepositAccount depositAccount = null;

        try {
            depositAccount = mifosService.getRecurringDepositAccount(depositAcc.getAccountNumber(), true, tenant);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(depositAccount, HttpStatus.OK);
    }


    /**
     * Web service to get voluntary savings account from fineract
     */
    @RequestMapping(value = "/voluntarySavingAccounts", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SavingsAccount> getVoluntarySavingsAccount(@RequestBody AccountDepositRequest depositRequest){

        SavingsAccount savingsAccount = null;


        try {
            savingsAccount = mifosService.getSavingsAccount(depositRequest.getAccountNumber(), true, "default" );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(savingsAccount, HttpStatus.OK);
    }


    /**
     * Web service to get loan account from fineract.
     * @param loanAccRequest
     * @return
     */
    @RequestMapping(value = "/loans", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Loan> getLoanAccount(@RequestBody LoanAccountRequest loanAccRequest, @RequestParam(value="tenant") String tenant){

        Loan loanAccount = null;

        try {
            loanAccount = mifosService.getLoanAccount(loanAccRequest.getLoanAccountNo(), true, tenant);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(loanAccount, HttpStatus.OK);
    }

    @RequestMapping(value = "/outbound/payments/status", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> receiveOutboundStatus(@RequestBody Status outboundTransactionStatus,
                                                        @RequestParam(value="requestID") Long requestID){
        Status reverseStatus = null;
        OutboundRequest request = outboundRequestService.findOne(requestID);
        Status transactionStatus = statusService.findOne(request.getOutboundStatusId());

        transactionStatus.setCode(outboundTransactionStatus.getCode());
        transactionStatus.setDescription(outboundTransactionStatus.getDescription());
        transactionStatus.setStatusCategory(outboundTransactionStatus.getStatusCategory());

        //Save status
        statusService.save(transactionStatus);

        if(transactionStatus.getCode().equalsIgnoreCase(String.valueOf(TransactionStatus.MMP_TRANSACTION_SUCCESS_CODE))){
            //Set reverse status of outboundRequest to NO Action
            reverseStatus.setCode(String.valueOf(TransactionStatus.NO_ACTION_CODE));
            reverseStatus.setDescription(TransactionStatus.NO_ACTION);
            reverseStatus.setStatusCategory(StatusCategory.GATEWAY_CATEGORY);
        }else{
            outboundMessageReceiver.attemptReverseDisbursal();
        }

        request.setReverseStatusId(reverseStatus.getId());
        outboundRequestService.save(request);

        return new ResponseEntity<>("Status Received", HttpStatus.OK);


    }

    /**
     * Web service for receiving inbound request and
     * queueing it and sending it to the inbound message
     * receiver with jms.
     * @param inboundRequest
     * @return
     */
    @RequestMapping(value = "/inbound/requests", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Status> acceptInboundRequest(@RequestBody InboundRequest inboundRequest, @RequestParam(value="tenant") String tenant){
        Status receptionStatus = null;
        context = MifosPaymentApplication.context;

        System.out.println(inboundRequest.toString());

        if(tenant != null){
            mfi = mfiService.findMicrofinanceByName(tenant);
            System.out.println(mfi.toString());
            inboundRequest.setMfiId(mfi.getId());
        }

        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        jmsTemplate.convertAndSend("inboundAcceptor", inboundRequest);

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

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
