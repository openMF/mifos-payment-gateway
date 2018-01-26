package org.mifos.mifospaymentbridge.integration;

import org.mifos.mifospaymentbridge.Util.Constants;
import org.mifos.mifospaymentbridge.Util.StatusCategory;
import org.mifos.mifospaymentbridge.Util.TransactionStatus;
import org.mifos.mifospaymentbridge.integration.ProviderApiService.PaymentService;
import org.mifos.mifospaymentbridge.mifos.MifosService;
import org.mifos.mifospaymentbridge.mifos.domain.loan.Loan;
import org.mifos.mifospaymentbridge.mifos.domain.loan.LoanAccountSearchResult;
import org.mifos.mifospaymentbridge.mifos.domain.loan.undodisbursal.UndoLoanDisbursementRequest;
import org.mifos.mifospaymentbridge.mifos.domain.loan.undodisbursal.UndoLoanDisbursementResponse;
import org.mifos.mifospaymentbridge.model.*;
import org.mifos.mifospaymentbridge.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

@Component
public class OutboundMessageReceiver{

    private OutboundRequest outboundRequest;

    private OutboundTransactionLog transactionLog;

    private Status outboundTransactionStatus;

    private Loan loanAccount;

    private Status reverseStatus = new Status();

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

    @Autowired
    private MifosService mifosService;

    public void receiveRequest(OutboundRequest msg){
        handleTransaction(msg);
    }

    public void handleTransaction(OutboundRequest request){
        outboundRequest = request;


        //send the request to a particular mmp
        if(outboundRequest.getPaymentMethod().equalsIgnoreCase("mobile money")){

            //mmp lookup
            MobileMoneyProvider mmp = mmpService.findOne(outboundRequest.getMmpId());

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
                //Set status of transaction with failed mmp lookup
                outboundTransactionStatus.setCode(String.valueOf(TransactionStatus.MMP_LOOKUP_FAILED_CODE));
                outboundTransactionStatus.setDescription(TransactionStatus.MMP_LOOKUP_FAILED);
                outboundTransactionStatus.setStatusCategory(StatusCategory.GATEWAY_CATEGORY);
            }

            //Save the transaction status and reverse transaction status
            statusService.save(outboundTransactionStatus);

            //Save request in database
            outboundRequest.setOutboundStatusId(outboundTransactionStatus.getId());
            outboundRequest = outboundRequestService.save(outboundRequest);

            //Log the transaction to DB
            logTransaction();
        }else if(outboundRequest.getPaymentMethod().equalsIgnoreCase("bit coin")){

        }



    }


    public void attemptReverseDisbursal(){
        //attempt to reverse transaction
        try {
            mifosService.getLoanAccountAsync(outboundRequest.getFineractAccNo(), true, "default", new Callback<LoanAccountSearchResult>() {
                @Override
                public void onResponse(Call<LoanAccountSearchResult> call, Response<LoanAccountSearchResult> response) {
                    if(response.isSuccessful()){
                        loanAccount = response.body().pageItems.get(0);
                        UndoLoanDisbursementRequest undoDisbursementRequest = new UndoLoanDisbursementRequest();
                        undoDisbursementRequest.setNote(outboundTransactionStatus.getDescription());
                        try {
                            mifosService.undoDisbursalAsync(loanAccount.getId(), undoDisbursementRequest, true, "default", new Callback<UndoLoanDisbursementResponse>() {
                                @Override
                                public void onResponse(Call<UndoLoanDisbursementResponse> call, Response<UndoLoanDisbursementResponse> response) {
                                    if(response.isSuccessful()){
                                        reverseStatus.setCode(String.valueOf(TransactionStatus.REVERSE_DISBURSEMENT_SUCCESS_CODE));
                                        reverseStatus.setDescription(TransactionStatus.REVERSE_DISBURSEMENT_SUCCESS);
                                        reverseStatus.setStatusCategory(StatusCategory.FINERACT_CATEGORY);
                                    }else{
                                        reverseStatus.setCode(String.valueOf(TransactionStatus.REVERSE_DISBURSEMENT_FAILURE_CODE));
                                        reverseStatus.setDescription(TransactionStatus.REVERSE_DISBURSEMENT_FAILURE);
                                        reverseStatus.setStatusCategory(StatusCategory.FINERACT_CATEGORY);
                                    }
                                }

                                @Override
                                public void onFailure(Call<UndoLoanDisbursementResponse> call, Throwable t) {
                                    reverseStatus.setCode(String.valueOf(TransactionStatus.REVERSE_DISBURSEMENT_FAILURE_CODE));
                                    reverseStatus.setDescription(TransactionStatus.REVERSE_DISBURSEMENT_FAILURE);
                                    reverseStatus.setStatusCategory(StatusCategory.FINERACT_CATEGORY);
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        System.out.println(response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<LoanAccountSearchResult> call, Throwable t) {
                    System.out.println(t.getMessage());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void logTransaction(){
        transactionLog = new OutboundTransactionLog();
        transactionLog.setOutboundRequestId(outboundRequest.getId());
        transactionLog.setRequestIpAddress(outboundRequest.getRequestIpAddress());
        transactionLog.setTransactType(outboundRequest.getTransactType());
        transactionLog.setTransactionStatusId(outboundRequest.getOutboundStatusId());
        transactionLog.setTransactionDtm(outboundRequest.getRequestedDtm());
        outboundTransactionLogService.save(transactionLog);
    }

}
