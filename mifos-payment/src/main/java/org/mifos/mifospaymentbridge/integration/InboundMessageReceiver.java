package org.mifos.mifospaymentbridge.integration;

import org.joda.time.DateTime;
import org.mifos.mifospaymentbridge.Util.StatusCategory;
import org.mifos.mifospaymentbridge.Util.TransactionStatus;
import org.mifos.mifospaymentbridge.integration.ProviderApiService.PaymentService;
import org.mifos.mifospaymentbridge.mifos.MifosService;
import org.mifos.mifospaymentbridge.mifos.domain.loan.Loan;
import org.mifos.mifospaymentbridge.mifos.domain.loan.repayment.LoanRepaymentRequest;
import org.mifos.mifospaymentbridge.mifos.domain.loan.repayment.LoanRepaymentResponse;
import org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit.SavingsAccountDepositRequest;
import org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit.SavingsAccountDepositResponse;
import org.mifos.mifospaymentbridge.model.InboundCallbackLog;
import org.mifos.mifospaymentbridge.model.InboundRequest;
import org.mifos.mifospaymentbridge.model.MobileMoneyProvider;
import org.mifos.mifospaymentbridge.model.Status;
import org.mifos.mifospaymentbridge.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by vladimirfomene on 8/4/17.
 */
public class InboundMessageReceiver {

    private Loan loanAccount;

    private InboundCallbackLog callbackLog;

    private LoanRepaymentResponse repaymentResponse;


    private Status callbackStatus;

    private LoanRepaymentRequest repaymentRequest;

    private SavingsAccountDepositResponse depositResponse;


    @Autowired
    private InboundRequestService inboundRequestService;

    @Autowired
    private InboundCallbackLogService inboundCallbackLogService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private MobileMoneyProviderService mmpService;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private MifosService mifosService;

    @Autowired
    private PaymentService paymentService;


    public InboundMessageReceiver(){

    }

    @JmsListener(destination = "inboundAcceptor", containerFactory = "inboundFactory")
    public void receiveRequest(InboundRequest msg){
        handleTransaction(msg);
    }

    public void handleTransaction(InboundRequest request){

        //mmp lookup
        MobileMoneyProvider mmp = mmpService.findOne(request.getMmpId());

        //handle request per payment method
        if(request.getPaymentMethod().equalsIgnoreCase("mobile money")){

            //Log the request to the database
            request = inboundRequestService.save(request);

            if(!request.getAmount().equals(null) && !request.getFineractAccNo().equals(null)) {

                //send request to fineract
                if (request.getTransactType() == InboundRequest.TransactionType.LOAN_REPAYMENT) {

                    //build a loan repayment object
                    repaymentRequest = new LoanRepaymentRequest();
                    repaymentRequest.setAccountNumber(request.getFineractAccNo());
                    repaymentRequest.setTransactionAmount(String.valueOf(request.getAmount()));
                    repaymentRequest.setDateFormat("dd MMMM yyyy");
                    repaymentRequest.setNote(request.getTransactionReason());
                    repaymentRequest.setTransactionDate(request.getRequestedDtm().toString());

                    //Get the loan account from fineract
                    try {
                        loanAccount = mifosService.getLoanAccount(request.getFineractAccNo(), true, "default");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //Call mifos for a loan repayment
                    try {
                        repaymentResponse = mifosService.repay(loanAccount.getId(), repaymentRequest, true, "default");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    callbackStatus = new Status();
                    if (repaymentResponse != null) {
                        callbackStatus.setCode(String.valueOf(TransactionStatus.REPAYMENT_SUCCESS_CODE));
                        callbackStatus.setDescription(TransactionStatus.REPAYMENT_SUCCESS);
                        callbackStatus.setStatusCategory(StatusCategory.FINERACT_CATEGORY);
                    } else {
                        callbackStatus.setCode(String.valueOf(TransactionStatus.REPAYMENT_FAILURE_CODE));
                        callbackStatus.setDescription(TransactionStatus.REPAYMENT_FAILURE);
                        callbackStatus.setStatusCategory(StatusCategory.FINERACT_CATEGORY);
                    }

                }


                if (request.getTransactType() == InboundRequest.TransactionType.SAVINGS) {

                    SavingsAccountDepositRequest depositRequest = new SavingsAccountDepositRequest();
                    depositRequest.setTransactionAmount(String.valueOf(request.getAmount()));
                    depositRequest.setAccountNumber(request.getFineractAccNo());
                    depositRequest.setTransactionDate(request.getInboundStatusDtm().toString());

                    try {
                        depositResponse = mifosService.depositToSavingsAccount(request.getFineractAccNo(), depositRequest, true, "default");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (depositResponse != null) {
                        //Send response back to mmp
                        callbackStatus = new Status();
                        callbackStatus.setCode(String.valueOf(TransactionStatus.VOLUNTARY_DEPOSIT_SUCCESS_CODE));
                        callbackStatus.setDescription(TransactionStatus.VOLUNTARY_DEPOSIT_SUCCESS);
                        callbackStatus.setStatusCategory(StatusCategory.FINERACT_CATEGORY);




                    }
                }
                String callbackUrl = configurationService.findConfigurationByConfigNameAndReferenceId("callback_url",
                        request.getMmpId()).getConfigValue();

                //Create callback log and save it.
                callbackLog = new InboundCallbackLog();
                callbackLog.setInboundRequestId(request.getId());
                callbackLog.setCallbackUrl(callbackUrl);
                callbackLog.setCallbackMessage(callbackStatus.getDescription());
                callbackLog.setCallbackDtm(Timestamp.valueOf(LocalDateTime.now()));
                inboundCallbackLogService.save(callbackLog);

                //Log callback status and send it
                statusService.save(callbackStatus);
                callbackStatus = paymentService.sendInboundTransactionStatus(callbackUrl, callbackStatus);
            }

        }else if(request.getPaymentMethod().equalsIgnoreCase("bit coin")){

        }
        
    }
}
