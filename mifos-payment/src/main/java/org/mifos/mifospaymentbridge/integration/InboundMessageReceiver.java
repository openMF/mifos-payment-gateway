package org.mifos.mifospaymentbridge.integration;

import org.mifos.mifospaymentbridge.Util.StatusCategory;
import org.mifos.mifospaymentbridge.Util.TransactionStatus;
import org.mifos.mifospaymentbridge.integration.ProviderApiService.PaymentService;
import org.mifos.mifospaymentbridge.mifos.MifosService;
import org.mifos.mifospaymentbridge.mifos.domain.loan.Loan;
import org.mifos.mifospaymentbridge.mifos.domain.loan.LoanAccountSearchResult;
import org.mifos.mifospaymentbridge.mifos.domain.loan.repayment.LoanRepaymentRequest;
import org.mifos.mifospaymentbridge.mifos.domain.loan.repayment.LoanRepaymentResponse;
import org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit.*;
import org.mifos.mifospaymentbridge.model.InboundCallbackLog;
import org.mifos.mifospaymentbridge.model.InboundRequest;
import org.mifos.mifospaymentbridge.model.MobileMoneyProvider;
import org.mifos.mifospaymentbridge.model.Status;
import org.mifos.mifospaymentbridge.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Component
public class InboundMessageReceiver {

    private Loan loanAccount;

    private SavingsAccount savingsAccount;

    private String callbackUrl;

    private RecurringDepositAccount depositAccount;

    private InboundCallbackLog callbackLog;

    private LoanRepaymentResponse repaymentResponse;

    private Status callbackStatus = new Status();

    private Status inboundStatus = new Status();

    private LoanRepaymentRequest repaymentRequest;

    private AccountDepositResponse depositResponse;

    private InboundRequest inboundRequest = new InboundRequest();


    @Autowired
    private InboundRequestService inboundRequestService;

    @Autowired
    private InboundCallbackLogService inboundCallbackLogService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private MifosService mifosService;

    @Autowired
    private PaymentService paymentService;



    @JmsListener(destination = "inboundAcceptor", containerFactory = "inboundFactory")
    public void receiveRequest(InboundRequest msg){
        //System.out.println(msg.toString());
        handleTransaction(msg);
    }

    public void handleTransaction(InboundRequest request){

        //Query that particular request
        //inboundRequest = inboundRequestService.findInboundRequestByFineractAccNo(request.getFineractAccNo());

        //Create inbound request object
        buildRequest(request);

        //handle request per payment method
        if(inboundRequest.getPaymentMethod().equalsIgnoreCase("mobile money")){

            //send request to fineract
            if (inboundRequest.getTransactType() == InboundRequest.TransactionType.LOAN_REPAYMENT) {
                initiateLoanRepayment(inboundRequest);
                System.out.println("Loan Repayment");
            }


            if (inboundRequest.getTransactType() == InboundRequest.TransactionType.VOLUNTARY_SAVINGS) {
                initiateVoluntarySaving(inboundRequest);
                System.out.println("Voluntary Savings");
            }

            if(inboundRequest.getTransactType() == InboundRequest.TransactionType.RECURRING_DEPOSIT){
                initiateReccuringDeposit(inboundRequest);
                System.out.println("Fixed Deposit");
            }

            //System.out.println(inboundRequest.getMmpId());
            callbackUrl = configurationService.findConfigurationByConfigNameAndReferenceId("callback_url",
                    inboundRequest.getMmpId()).getConfigValue();



            //String callback = paymentService.sendInboundTransactionStatus(callbackUrl, inboundStatus);
            //System.out.println(callbackStatus.toString());
            //callbackStatus = paymentService.sendInboundTransactionStatus(callbackUrl, callbackStatus);




        }else if(inboundRequest.getPaymentMethod().equalsIgnoreCase("bit coin")){

        }
        
    }

    private SavingsAccount getSavingsAccountByAccountNo(List<SavingsAccount> savingsAccountList, String accountNo){
        SavingsAccount account = null;
        for(SavingsAccount savingsAccount: savingsAccountList){
            if(savingsAccount.getAccountNo().equals(accountNo)){
                account = savingsAccount;
                break;
            }
        }
        return account;
    }

    private void createAndSaveInboudCallbackLog(Status callbackStatus){

        //Create inbound callback log
        callbackLog = new InboundCallbackLog();
        callbackLog.setInboundRequestId(inboundRequest.getId());
        callbackLog.setCallbackUrl(callbackUrl);
        callbackLog.setCallbackStatusId(callbackStatus.getId());
        callbackLog.setCallbackMessage(callbackStatus.getDescription());
        callbackLog.setCallbackDtm(Timestamp.valueOf(LocalDateTime.now()));

        //Log the request to the database
        inboundCallbackLogService.save(callbackLog);

    }

    private void savingInboundRequest(Status inboundStatus){
        inboundRequest.setInboundStatusId(inboundStatus.getId());
        inboundRequest = inboundRequestService.save(inboundRequest);
    }

    private void buildRequest(InboundRequest request){

        inboundRequest.setId(request.getId());
        inboundRequest.setTransactType(request.getTransactType());
        inboundRequest.setMmpId(request.getMmpId());
        inboundRequest.setMfiId(request.getMfiId());
        inboundRequest.setSourceRef(request.getSourceRef());
        inboundRequest.setDestinationRef(request.getDestinationRef());
        inboundRequest.setFineractAccNo(request.getFineractAccNo());
        inboundRequest.setFineractClientId(request.getFineractClientId());
        inboundRequest.setAmount(request.getAmount());
        inboundRequest.setTransactionReason(request.getTransactionReason());
        inboundRequest.setExternalSystId(request.getExternalSystId());
        inboundRequest.setComments(request.getComments());
        inboundRequest.setRequestedDtm(request.getRequestedDtm());
        inboundRequest.setRequestIpAddress(request.getRequestIpAddress());
        inboundRequest.setInboundStatusId(request.getInboundStatusId());
        inboundRequest.setInboundStatusDtm(request.getInboundStatusDtm());
        inboundRequest.setPaymentMethod(request.getPaymentMethod());
        inboundRequest.setPaymentMethodType(request.getPaymentMethodType());
    }

    private void initiateLoanRepayment(InboundRequest request){
        //build a loan repayment object
        repaymentRequest = new LoanRepaymentRequest();
        repaymentRequest.setAccountNumber(request.getFineractAccNo());
        repaymentRequest.setTransactionAmount(String.valueOf(request.getAmount()));
        repaymentRequest.setDateFormat("dd MMMM yyyy");
        repaymentRequest.setNote(request.getTransactionReason());
        repaymentRequest.setLocale("en");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        repaymentRequest.setTransactionDate(LocalDateTime.now().toLocalDate().format(formatter));

        //Get the loan account and repay it
        getLoanAccountAndRepayIt(request);

    }

    private void initiateVoluntarySaving(InboundRequest inboundRequest){
        AccountDepositRequest depositRequest = new AccountDepositRequest();
        depositRequest.setTransactionAmount(String.valueOf(inboundRequest.getAmount()));
        depositRequest.setAccountNumber(inboundRequest.getFineractAccNo());
        depositRequest.setDateFormat("dd MMMM yyyy");
        depositRequest.setLocale("en");
        depositRequest.setPaymentTypeId("11");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy"); //Locale.getDefault()
        depositRequest.setTransactionDate(LocalDateTime.now().toLocalDate().format(formatter));

        getSavingsAccountAndMakeVoluntarySaving(depositRequest, inboundRequest);
    }

    private void initiateReccuringDeposit(InboundRequest request){

        //Build an account deposit request object
        AccountDepositRequest depositRequest = new AccountDepositRequest();
        depositRequest.setTransactionAmount(String.valueOf(request.getAmount()));
        depositRequest.setAccountNumber(request.getFineractAccNo());
        depositRequest.setDateFormat("dd MMMM yyyy");
        depositRequest.setLocale("en");
        depositRequest.setPaymentTypeId("11");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        depositRequest.setTransactionDate(LocalDateTime.now().toLocalDate().format(formatter));


        //Get the recurring deposit account from fineract and make a deposit
        getRecurringDepositAccountAndMakeDeposit(request, depositRequest);


    }

    private void getSavingsAccountAndMakeVoluntarySaving(AccountDepositRequest depositRequest, InboundRequest inboundRequest){
        try {
            mifosService.getSavingsAccountAsync(inboundRequest.getFineractAccNo(), true, "default", new Callback<SavingsAccountSearchResult>() {
                @Override
                public void onResponse(Call<SavingsAccountSearchResult> call, Response<SavingsAccountSearchResult> response) {
                    if(response.isSuccessful()){
                        if(!response.body().pageItems.isEmpty()){
                            savingsAccount = getSavingsAccountByAccountNo(response.body().pageItems, inboundRequest.getFineractAccNo());
                            //System.out.println(savingsAccount.toString());
                        }
                        //System.out.println(loanAccount.toString());

                        if(savingsAccount != null){
                            callFineractForVoluntarySaving(depositRequest, inboundRequest);
                        }

                    }else{
                        System.out.println(response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<SavingsAccountSearchResult> call, Throwable t) {
                    System.out.println(t.getMessage());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getLoanAccountAndRepayIt(InboundRequest request){
        try {
            //System.out.println(mifosService.toString());
            mifosService.getLoanAccountAsync(request.getFineractAccNo(), true, "default", new Callback<LoanAccountSearchResult>() {
                @Override
                public void onResponse(Call<LoanAccountSearchResult> call, Response<LoanAccountSearchResult> response) {
                    if(response.isSuccessful()){
                        if(!response.body().pageItems.isEmpty()) loanAccount = response.body().pageItems.get(0);
                        //System.out.println(loanAccount.toString());

                        if(loanAccount != null){
                            callFineractForRepayment();
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

    private void callFineractForRepayment(){

        try {
            mifosService.repayLoanAsync(loanAccount.getId(), repaymentRequest, true, "default", new Callback<LoanRepaymentResponse>() {
                @Override
                public void onResponse(Call<LoanRepaymentResponse> call, Response<LoanRepaymentResponse> response) {
                    if(response.isSuccessful()){
                        repaymentResponse = response.body();

                        if(repaymentResponse != null){
                            inboundStatus.setCode(String.valueOf(TransactionStatus.REPAYMENT_SUCCESS_CODE));
                            inboundStatus.setDescription(TransactionStatus.REPAYMENT_SUCCESS);
                            inboundStatus.setStatusCategory(StatusCategory.FINERACT_CATEGORY);
                        }

                    }else{
                        System.out.println(response.errorBody());

                        inboundStatus.setCode(String.valueOf(TransactionStatus.REPAYMENT_FAILURE_CODE));
                        inboundStatus.setDescription(TransactionStatus.REPAYMENT_FAILURE);
                        inboundStatus.setStatusCategory(StatusCategory.FINERACT_CATEGORY);
                    }

                    statusService.save(inboundStatus);
                    callbackStatus = paymentService.sendInboundTransactionStatus(callbackUrl, inboundStatus);
                    //Log callback status
                    statusService.save(callbackStatus);

                    createAndSaveInboudCallbackLog(callbackStatus);
                    savingInboundRequest(inboundStatus);

                }

                @Override
                public void onFailure(Call<LoanRepaymentResponse> call, Throwable t) {

                    inboundStatus.setCode(String.valueOf(TransactionStatus.REPAYMENT_FAILURE_CODE));
                    inboundStatus.setDescription(TransactionStatus.REPAYMENT_FAILURE);
                    inboundStatus.setStatusCategory(StatusCategory.FINERACT_CATEGORY);
                    System.out.println(t.getMessage());
                    statusService.save(inboundStatus);
                    callbackStatus = paymentService.sendInboundTransactionStatus(callbackUrl, inboundStatus);
                    //Log callback status
                    statusService.save(callbackStatus);
                    //System.out.println(inboundStatus.toString());

                    createAndSaveInboudCallbackLog(callbackStatus);
                    savingInboundRequest(inboundStatus);
                }




            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void callFineractForVoluntarySaving(AccountDepositRequest depositRequest, InboundRequest request){
        try {
            mifosService.depositToSavingsAccountAsync(savingsAccount.getId(), depositRequest,
                    true, "default", new Callback<AccountDepositResponse>() {
                        @Override
                        public void onResponse(Call<AccountDepositResponse> call, Response<AccountDepositResponse> response) {
                            if(response.isSuccessful()){
                                depositResponse = response.body();
                                if(depositResponse != null){

                                    inboundStatus.setCode(String.valueOf(TransactionStatus.VOLUNTARY_DEPOSIT_SUCCESS_CODE));
                                    inboundStatus.setDescription(TransactionStatus.VOLUNTARY_DEPOSIT_SUCCESS);
                                    inboundStatus.setStatusCategory(StatusCategory.FINERACT_CATEGORY);
                                }
                            }else{
                                System.out.println(response.errorBody().toString());
                                inboundStatus.setCode(String.valueOf(TransactionStatus.VOLUNTARY_DEPOSIT_FAILURE_CODE));
                                inboundStatus.setDescription(TransactionStatus.VOLUNTARY_DEPOSIT_FAILURE);
                                inboundStatus.setStatusCategory(StatusCategory.FINERACT_CATEGORY);
                            }

                            statusService.save(inboundStatus);
                            callbackStatus = paymentService.sendInboundTransactionStatus(callbackUrl, inboundStatus);
                            //Log callback status
                            statusService.save(callbackStatus);
                            //System.out.println(inboundStatus.toString());

                            createAndSaveInboudCallbackLog(callbackStatus);
                            savingInboundRequest(inboundStatus);
                        }

                        @Override
                        public void onFailure(Call<AccountDepositResponse> call, Throwable t) {
                            System.out.println(t.getMessage());
                            inboundStatus.setCode(String.valueOf(TransactionStatus.VOLUNTARY_DEPOSIT_FAILURE_CODE));
                            inboundStatus.setDescription(TransactionStatus.VOLUNTARY_DEPOSIT_FAILURE);
                            inboundStatus.setStatusCategory(StatusCategory.FINERACT_CATEGORY);

                            statusService.save(inboundStatus);
                            callbackStatus = paymentService.sendInboundTransactionStatus(callbackUrl, inboundStatus);
                            //Log callback status
                            statusService.save(callbackStatus);
                            //System.out.println(inboundStatus.toString());

                            createAndSaveInboudCallbackLog(callbackStatus);
                            savingInboundRequest(inboundStatus);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void getRecurringDepositAccountAndMakeDeposit(InboundRequest request, AccountDepositRequest depositRequest){
        try {
            mifosService.getRecurringDepositAccountAsync(request.getFineractAccNo(),
                    true, "default", new Callback<List<RecurringDepositAccount>>() {
                        @Override
                        public void onResponse(Call<List<RecurringDepositAccount>> call, Response<List<RecurringDepositAccount>> response) {
                            if(response.isSuccessful()){
                                depositAccount = getDepositAccountByAccountNo(response.body(), request.getFineractAccNo());
                                System.out.println(depositAccount.toString());
                                if(depositAccount != null){
                                    callFineractForRecurringDeposit(request, depositRequest);
                                }


                            }else{
                                System.out.println(response.errorBody());
                            }

                        }

                        @Override
                        public void onFailure(Call<List<RecurringDepositAccount>> call, Throwable t) {
                            System.out.println(t.getMessage());
                        }
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private RecurringDepositAccount getDepositAccountByAccountNo(List<RecurringDepositAccount> depositAccounts, String accountNo){
        RecurringDepositAccount account = null;
        for(RecurringDepositAccount depositAccount: depositAccounts){
            if(depositAccount.getAccountNo().equals(accountNo)){
                account = depositAccount;
                break;
            }
        }
        return account;
    }

    private void callFineractForRecurringDeposit(InboundRequest request, AccountDepositRequest depositRequest){
        try {
            mifosService.recurringSavingAsync(depositAccount.getId(), depositRequest,
                    true, "default", new Callback<AccountDepositResponse>() {
                        @Override
                        public void onResponse(Call<AccountDepositResponse> call, Response<AccountDepositResponse> response) {
                            if(response.isSuccessful()){
                                depositResponse = response.body();

                                //Set status for the transaction
                                inboundStatus.setCode(String.valueOf(TransactionStatus.RECURRING_DEPOSIT_SUCCESS_CODE));
                                inboundStatus.setDescription(TransactionStatus.RECURRING_DEPOSIT_SUCCESS);
                                inboundStatus.setStatusCategory(StatusCategory.FINERACT_CATEGORY);

                            }else{

                                inboundStatus.setCode(String.valueOf(TransactionStatus.RECURRING_DEPOSIT_FAILURE_CODE));
                                inboundStatus.setDescription(TransactionStatus.RECURRING_DEPOSIT_FAILURE);
                                inboundStatus.setStatusCategory(StatusCategory.FINERACT_CATEGORY);
                                System.out.println(response.errorBody());
                            }

                            statusService.save(inboundStatus);
                            callbackStatus = paymentService.sendInboundTransactionStatus(callbackUrl, inboundStatus);
                            //Log callback status
                            statusService.save(callbackStatus);
                            //System.out.println(inboundStatus.toString());

                            createAndSaveInboudCallbackLog(callbackStatus);
                            savingInboundRequest(inboundStatus);

                        }

                        @Override
                        public void onFailure(Call<AccountDepositResponse> call, Throwable t) {

                            inboundStatus.setCode(String.valueOf(TransactionStatus.RECURRING_DEPOSIT_FAILURE_CODE));
                            inboundStatus.setDescription(TransactionStatus.RECURRING_DEPOSIT_FAILURE);
                            inboundStatus.setStatusCategory(StatusCategory.FINERACT_CATEGORY);
                            System.out.println(t.getMessage());

                            statusService.save(inboundStatus);
                            callbackStatus = paymentService.sendInboundTransactionStatus(callbackUrl, inboundStatus);
                            //Log callback status
                            statusService.save(callbackStatus);
                            //System.out.println(inboundStatus.toString());

                            createAndSaveInboudCallbackLog(callbackStatus);
                            savingInboundRequest(inboundStatus);
                        }
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
