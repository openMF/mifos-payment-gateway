package org.mifos.mifospaymentbridge.integration;

import org.mifos.mifospaymentbridge.Util.StatusCategory;
import org.mifos.mifospaymentbridge.Util.TransactionStatus;
import org.mifos.mifospaymentbridge.integration.ProviderApiService.PaymentService;
import org.mifos.mifospaymentbridge.model.*;
import org.mifos.mifospaymentbridge.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;


public class OutboundTransactionHandler {

    private OutboundRequest outboundRequest;

    private OutboundTransactionLog transactionLog;

    private Status outboundTransactionStatus;

    private Configuration configuration;

    @Autowired
    private OutboundTransactionLogService outboundTransactionLogService;

    @Autowired
    private OutboundRequestService outboundRequestService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private MobileMoneyProviderService mmpService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ConfigurationService configurationService;

    public void receiveRequest(Message<OutboundRequest> msg){
        handleTransaction(msg);
    }

    public void handleTransaction(Message<OutboundRequest> request){
        outboundRequest = request.getPayload();

        //Save request in database
        outboundRequest = outboundRequestService.save(outboundRequest);

        //mmp lookup
        MobileMoneyProvider mmp = mmpService.findOne(outboundRequest.getMmpId());


        if(mmp != null){
            //send the request to a particular mmp
            if(outboundRequest.getPaymentMethod().equalsIgnoreCase("mobile money")){
                String paymentApiUrl = configurationService.findByReferenceIdAndRefType(mmp.getId(), "mmp").getConfigValue();
                outboundTransactionStatus = paymentService.sendPayment(paymentApiUrl, outboundRequest);
            }else if(outboundRequest.getPaymentMethod().equalsIgnoreCase("bit coin")){

            }

        }else{
            //Save transaction with failed status
            outboundTransactionStatus.setCode(String.valueOf(TransactionStatus.MMP_LOOKUP_FAILED));
            outboundTransactionStatus.setDescription("mmp lookup failed: mmp is not in the database");
            outboundTransactionStatus.setStatusCategory(StatusCategory.GATEWAY_CATEGORY);
        }

        //Log the transaction to DB
        transactionLog = new OutboundTransactionLog();
        transactionLog.setOutboundRequestId(outboundRequest.getId());
        transactionLog.setRequestIpAddress(outboundRequest.getRequestIpAddress());
        transactionLog.setTransactType(outboundRequest.getTransactType());
        transactionLog.setTransactionStatusId(outboundRequest.getOutboundStatusId());
        transactionLog.setTransactionDtm(outboundRequest.getRequestedDtm());

    }


}
