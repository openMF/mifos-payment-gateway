package org.mifos.mifospaymentbridge.controller;


import org.mifos.mifospaymentbridge.Util.StatusCategory;
import org.mifos.mifospaymentbridge.Util.TransactionStatus;
import org.mifos.mifospaymentbridge.integration.OutboundMessageReceiver;
import org.mifos.mifospaymentbridge.model.OutboundRequest;
import org.mifos.mifospaymentbridge.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OutboundController {

    @Autowired
    private OutboundMessageReceiver receiver;

    private Status outboundReceptionStatus;

    @RequestMapping(value = "/outbound/requests", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Status> acceptOutboundRequest(@RequestBody OutboundRequest outboundRequest, @RequestParam(value="tenant") String tenant){
        System.out.println(outboundRequest.toString());
        receiver.receiveRequest(outboundRequest);

        if(outboundRequest != null){
            outboundReceptionStatus = new Status();
            outboundReceptionStatus.setCode(String.valueOf(TransactionStatus.REQUEST_RECEPTION_SUCCESS_CODE));
            outboundReceptionStatus.setDescription(TransactionStatus.REQUEST_RECEPTION_SUCCESS);
            outboundReceptionStatus.setStatusCategory(StatusCategory.GATEWAY_CATEGORY);
        }

        return new ResponseEntity<>(outboundReceptionStatus, HttpStatus.OK);
    }

}
