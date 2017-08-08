package org.mifos.mifospaymentbridge.integration;

import org.mifos.mifospaymentbridge.Util.Constants;
import org.mifos.mifospaymentbridge.Util.StatusCategory;
import org.mifos.mifospaymentbridge.Util.TransactionStatus;
import org.mifos.mifospaymentbridge.integration.ProviderApiService.PaymentService;
import org.mifos.mifospaymentbridge.model.*;
import org.mifos.mifospaymentbridge.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;


public class OutboundMessageReceiver {

    private OutboundRequest outboundRequest;

    private OutboundTransactionLog transactionLog;

    private Status outboundTransactionStatus;

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

        //send the request to a particular mmp
        if(outboundRequest.getPaymentMethod().equalsIgnoreCase("mobile money")){
            if(mmp != null) {
                String paymentApiUrl = null;

                //Choose payment channel
                switch (mmp.getName()) {
                    case "Beyonic":
                        paymentApiUrl = Constants.BEYONIC_PAYMENT_URL;
                        break;
                    default:
                        break;
                }

                outboundTransactionStatus = paymentService.sendPayment(paymentApiUrl, outboundRequest);

            }else{
                //Save transaction with failed status
                outboundTransactionStatus.setCode(String.valueOf(TransactionStatus.MMP_LOOKUP_FAILED));
                outboundTransactionStatus.setDescription(TransactionStatus.MMP_LOOKUP_FAILED);
                outboundTransactionStatus.setStatusCategory(StatusCategory.GATEWAY_CATEGORY);
            }
        }else if(outboundRequest.getPaymentMethod().equalsIgnoreCase("bit coin")){

        }

        //Save the transaction status
        statusService.save(outboundTransactionStatus);


        //Log the transaction to DB
        transactionLog = new OutboundTransactionLog();
        transactionLog.setOutboundRequestId(outboundRequest.getId());
        transactionLog.setRequestIpAddress(outboundRequest.getRequestIpAddress());
        transactionLog.setTransactType(outboundRequest.getTransactType());
        transactionLog.setTransactionStatusId(outboundRequest.getOutboundStatusId());
        transactionLog.setTransactionDtm(outboundRequest.getRequestedDtm());
        outboundTransactionLogService.save(transactionLog);

    }


}
