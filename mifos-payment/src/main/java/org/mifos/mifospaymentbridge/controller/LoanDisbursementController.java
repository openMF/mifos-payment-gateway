/**
 * LoanDisbursementController.java
 * =======================================
 * @RestController that handles all http requests
 * for loan disbursement via the payment gateway
 * @author vladimir fomene
 */

package org.mifos.mifospaymentbridge.controller;

import org.mifos.mifospaymentbridge.Constant.GatewayConstants;
import org.mifos.mifospaymentbridge.PaymentProviders.Beyonic.PaymentResponse;
import org.mifos.mifospaymentbridge.Util.TransactionType;
import org.mifos.mifospaymentbridge.domain.DisbursementRequest;
import org.mifos.mifospaymentbridge.model.OutboundRequest;
import org.mifos.mifospaymentbridge.model.OutboundTransactionLog;
import org.mifos.mifospaymentbridge.model.Status;
import org.mifos.mifospaymentbridge.services.OutboundRequestService;
import org.mifos.mifospaymentbridge.services.OutboundTransactionLogService;
import org.mifos.mifospaymentbridge.services.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.Response;

import java.sql.Timestamp;

@RestController
public class LoanDisbursementController extends BaseController{

    private OutboundRequest outRequest;

    @Autowired
    private StatusService statusService;

    @Autowired
    private OutboundTransactionLogService logService;

    @Autowired
    private OutboundRequestService outboundRequestService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = LOAN_URL, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentResponse> disburseLoan(
            @RequestParam(value = "command", required=true, defaultValue = "disburse") String disburse,
            @RequestParam(value="mfi_id", required=true) String mfiId,
            @RequestParam(value="mmp_id", required=true) String mmpId,
            @RequestParam(value="payment_method", required=true) String paymentMethod,
            @RequestParam(value="payment_method_type", required=true) String paymentMethodType,
            @RequestParam(value="source_reference", required=true) String srcRef,
            @RequestParam(value="destination_reference", required=true) String destRef,
            @RequestParam(value="external_system_id", required=false, defaultValue = "0") String extSystId,
            @RequestParam(value="comments", required=false) String comments,
            @RequestParam(value="request_ip_address", required=false, defaultValue = "127.0.0.1") String reqIpAddr,
            @PathVariable(value = "loanId") Long id,
            @RequestBody DisbursementRequest request){

        //Persist disbursement request
        outRequest = new OutboundRequest();
        outRequest.setTransactType(TransactionType.DISBURSEMENT);
        outRequest.setMfiId(Long.valueOf(mfiId));
        outRequest.setMmpId(Long.valueOf(mmpId));
        outRequest.setPaymentMethod(paymentMethod);
        outRequest.setPaymentMethodType(paymentMethodType);
        outRequest.setSourceRef(srcRef);
        outRequest.setDestinationRef(destRef);
        outRequest.setFineractAccNo(request.getAccountNumber());
        outRequest.setFineractClientId(null);
        outRequest.setAmount(request.getTransactionAmount());
        outRequest.setTransactionReason(request.getNote());
        outRequest.setExternalSystId(extSystId);
        outRequest.setComments(comments);
        outRequest.setRequestedDtm(new Timestamp(System.currentTimeMillis()));
        outRequest.setOutboundStatusId(null);  //Set these values once you have the value of the status
        outRequest.setOutboundStatusDtm(null);
        outRequest.setReverseStatusId(null);
        outRequest.setReverseStatusIdDtm(null);

        //Create a status for this outbound request
        Status disbursementStatus = new Status();

        //Send transaction to Beyonic
        Response<PaymentResponse> response = makePayment(outRequest, disbursementStatus);

        //Set response status of our api call
        disbursementStatus.setCode(String.valueOf(response.code()));
        disbursementStatus.setDescription(response.message());
        disbursementStatus.setStatusCategory(GatewayConstants.PAYMENT_STATUS);
        disbursementStatus = statusService.save(disbursementStatus);


        //Save the outbound request in the gateway database.
        System.out.println(request.toString());
        outRequest.setOutboundStatusDtm(new Timestamp(System.currentTimeMillis()));
        outRequest.setReverseStatusIdDtm(new Timestamp(System.currentTimeMillis()));
        outRequest.setOutboundStatusId(disbursementStatus.getId());
        outRequest.setReverseStatusId(disbursementStatus.getId());

        //Update the external system id of the request with id of the payment response
        outRequest.setExternalSystId(String.valueOf(response.body().getId()));

        OutboundRequest req = outboundRequestService.save(outRequest);




        //Prepare to log transaction to the gateway database.
        OutboundTransactionLog log = new OutboundTransactionLog();
        log.setOutboundRequestId(req.getId());
        log.setRequestIpAddress(req.getRequestIpAddress());
        log.setTransactType(req.getTransactType());
        log.setTransactionDtm(req.getOutboundStatusDtm());
        log.setTransactionStatusId(disbursementStatus.getId());
        logService.save(log);






        return new ResponseEntity<PaymentResponse>(response.body(), HttpStatus.CREATED);

    }


}
