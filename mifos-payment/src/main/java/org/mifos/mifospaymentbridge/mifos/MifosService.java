/**
 * MifosService.java
 * =================================================
 * This class is a service to carry out savings and loan
 * operations for a particular client on mifos. This code was
 * adapted from @Antony Omeri's implementation
 * @author Vladimir Fomene
 */

package org.mifos.mifospaymentbridge.mifos;

import okhttp3.ResponseBody;
import org.mifos.mifospaymentbridge.Util.ApiClient;
import org.mifos.mifospaymentbridge.mifos.api.ClientInterface;
import org.mifos.mifospaymentbridge.mifos.api.LoanInterface;
import org.mifos.mifospaymentbridge.mifos.api.SavingsAccountInterface;
import org.mifos.mifospaymentbridge.mifos.domain.client.Client;
import org.mifos.mifospaymentbridge.mifos.domain.loan.Loan;
import org.mifos.mifospaymentbridge.mifos.domain.loan.LoanAccountSearchResult;
import org.mifos.mifospaymentbridge.mifos.domain.loan.disbursement.LoanDisbursementRequest;
import org.mifos.mifospaymentbridge.mifos.domain.loan.disbursement.LoanDisbursementResponse;
import org.mifos.mifospaymentbridge.mifos.domain.loan.repayment.LoanRepaymentRequest;
import org.mifos.mifospaymentbridge.mifos.domain.loan.repayment.LoanRepaymentResponse;
import org.mifos.mifospaymentbridge.mifos.domain.loan.undodisbursal.UndoLoanDisbursementRequest;
import org.mifos.mifospaymentbridge.mifos.domain.loan.undodisbursal.UndoLoanDisbursementResponse;
import org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit.*;
import org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.transaction.SavingsAccountTransaction;
import org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.transaction.SavingsAccountTransactionUndoResponse;
import org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.withdrawal.SavingsAccountWithdrawRequest;
import org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.withdrawal.SavingsAccountWithdrawResponse;
import org.mifos.mifospaymentbridge.model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

import java.io.IOException;
import java.util.List;

@Service
public class MifosService {
    private ClientInterface clientInterface = null;
    private SavingsAccountInterface savingsAccountInterface = null;
    private LoanInterface loanInterface = null;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Create a client to communicate with Beyonic's api.
     * @param mifosProperties configuration prop for fineract backend
     */
    @Autowired
    public MifosService(MifosProperties mifosProperties) {
        String mifosApiUrl = mifosProperties.getBaseUrl() + mifosProperties.getApiVersion();
        logger.info("Creating mifos service with baseUrl: {}", mifosApiUrl);
        clientInterface = ApiClient.getClient(mifosApiUrl, mifosProperties.getUsername(), mifosProperties.getPassword()).create(ClientInterface.class);
        loanInterface = ApiClient.getClient(mifosApiUrl, mifosProperties.getUsername(), mifosProperties.getPassword()).create(LoanInterface.class);
        savingsAccountInterface = ApiClient.getClient(mifosApiUrl, mifosProperties.getUsername(), mifosProperties.getPassword()).create(SavingsAccountInterface.class);
    }


    /**
     * This method searches a client in fineract using their phone number.
     * @param tenantIdentifier tenant identifier
     * @param isPretty determine whether result is pretty or not
     * @param phoneNumber client phone number
     * @return
     * @throws IOException
     */
    public Client getClientByPhoneNumber(String tenantIdentifier, boolean isPretty, String phoneNumber) throws IOException{
        Client client = null;
        Call<Client> call = clientInterface.getClientByPhoneNumber(tenantIdentifier, isPretty, phoneNumber);
        Response<Client> clientResponse = call.execute();
        boolean isSuccessful = clientResponse.isSuccessful();
        int code = clientResponse.code();
        if (isSuccessful) {
           client = clientResponse.body();
           if (client != null) {
               logger.info("- getClientByPhoneNumber({},{},{}) :Response [isSuccessful: {}, code: {}, client: {}]", tenantIdentifier, isPretty, phoneNumber, isSuccessful, code, client);
           }
        } else {
           ResponseBody errorResponse = clientResponse.errorBody();
           if (errorResponse != null) {
               logger.info("- getClientByPhoneNumber({},{},{}) :Response [isSuccessful: {}, code: {}, error: {}]", tenantIdentifier, isPretty, phoneNumber, isSuccessful, code, errorResponse.string());
           }
        }
        return client;
    }

    /**
     * This methods uses a client phone number to get information about a client. It is
     * asynchronous.
     * @param tenantIdentifier
     * @param isPretty
     * @param phoneNumber
     * @param callback
     */
    public void getClientByPhoneNumberAsync(String tenantIdentifier, boolean isPretty, String phoneNumber, Callback<Client> callback){
        Call<Client> call = clientInterface.getClientByPhoneNumber(tenantIdentifier, isPretty, phoneNumber);
        call.enqueue(callback);
    }



    /**
     * Get client from the fineract backend using a clientID.
     * This method is asynch, so it takes it a callback that is called once the
     * request returns.
     * @param clientId
     * @param tenantIdentifier
     * @param isPretty
     * @param callback
     * @throws IOException
     */
    public void getClientByIdAsync(Long clientId, String tenantIdentifier, boolean isPretty, Callback<Client> callback) throws IOException {
        Call<Client> call = clientInterface.getClientById(clientId, tenantIdentifier, isPretty);
        call.enqueue(callback);
    }


    /**
     * This asynchronous method makes a call to fineract to deposit money into
     * a client's saving account.
     * @param accountsId saving account no
     * @param depositRequest request body of the account deposit request.
     * @param isPretty
     * @param tenantIdentifier
     * @param callback
     * @throws IOException
     */
    public void depositToSavingsAccountAsync(Integer accountsId, AccountDepositRequest depositRequest,
                                                          boolean isPretty, String tenantIdentifier,
                                                          Callback<AccountDepositResponse> callback) throws IOException {
        Call<AccountDepositResponse> call = savingsAccountInterface.deposit(accountsId, depositRequest, isPretty, tenantIdentifier);
        call.enqueue(callback);
    }

    /**
     * This asynchronous method helps you to make deposit to a recurring deposit account.
     * @param accountsId
     * @param depositRequest
     * @param isPretty
     * @param tenantIdentifier
     * @param callback
     * @throws IOException
     */
    public void recurringSavingAsync(Integer accountsId, AccountDepositRequest depositRequest,
                                                  boolean isPretty, String tenantIdentifier, Callback<AccountDepositResponse> callback) throws IOException {
        Call<AccountDepositResponse> call = savingsAccountInterface.recurringSaving(accountsId, depositRequest, isPretty, tenantIdentifier);
        call.enqueue(callback);
    }


    /**
     * This method calls fineract to withdraw from a particular savings account.
     * @param accountsId
     * @param withdrawRequest
     * @param isPretty
     * @param tenantIdentifier
     * @return
     * @throws IOException
     */
    public SavingsAccountWithdrawResponse withdrawFromSavingsAccount(Long accountsId, SavingsAccountWithdrawRequest withdrawRequest, boolean isPretty, String tenantIdentifier) throws IOException {
        SavingsAccountWithdrawResponse withdrawResponse = null;
        Call<SavingsAccountWithdrawResponse> call = savingsAccountInterface.withdraw(accountsId, withdrawRequest, isPretty, tenantIdentifier);
        Response<SavingsAccountWithdrawResponse> response = call.execute();
        boolean isSuccessful = response.isSuccessful();
        int code = response.code();
        if (isSuccessful) {
            withdrawResponse = response.body();
            if (withdrawResponse != null) {
                logger.info("- withdrawFromSavingsAccount({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, withdrawResponse: {}]", accountsId, withdrawRequest, isPretty, tenantIdentifier, isSuccessful, code, withdrawResponse);

            } else {
                ResponseBody errorResponse = response.errorBody();
                if (errorResponse != null) {
                    logger.info("- withdrawFromSavingsAccount({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, error: {}]", accountsId, withdrawRequest, isPretty, tenantIdentifier, isSuccessful, code, errorResponse.string());
                }
            }
        } else {
            ResponseBody errorResponse = response.errorBody();
            if (errorResponse != null) {

            }
        }
        return withdrawResponse;
    }

    /**
     * This asynchronous method withdraws fund from a particular savings account.
     * @param accountsId
     * @param withdrawRequest
     * @param isPretty
     * @param tenantIdentifier
     * @param callback
     * @throws IOException
     */
    public void withdrawFromSavingsAccountAsync(Long accountsId,
                                                                     SavingsAccountWithdrawRequest withdrawRequest,
                                                                     boolean isPretty, String tenantIdentifier,
                                                                     Callback<SavingsAccountWithdrawResponse> callback) throws IOException {
        Call<SavingsAccountWithdrawResponse> call = savingsAccountInterface.withdraw(accountsId, withdrawRequest, isPretty, tenantIdentifier);
        call.enqueue(callback);
    }

    public SavingsAccountTransaction getSavingsAccountTransaction(Long accountsId, Long transactionId, boolean isPretty, String tenantIdentifier) throws IOException {
        SavingsAccountTransaction accountTransaction = null;
        Call<SavingsAccountTransaction> call = savingsAccountInterface.getTransaction(accountsId, transactionId, isPretty, tenantIdentifier);
        Response<SavingsAccountTransaction> response = call.execute();
        boolean isSuccessful = response.isSuccessful();
        int code = response.code();
        if (isSuccessful) {
            accountTransaction = response.body();
            if (accountTransaction != null) {
                logger.info("- getSavingsAccountTransaction({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, accountTransaction: {}]", accountsId, transactionId, isPretty, tenantIdentifier, isSuccessful, code, accountTransaction);
            } else {
                ResponseBody errorResponse = response.errorBody();
                if (errorResponse != null) {
                    logger.info("- getSavingsAccountTransaction({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, error: {}]", accountsId, transactionId, isPretty, tenantIdentifier, isSuccessful, code, errorResponse.string());
                }
            }
        }else {
            ResponseBody errorResponse = response.errorBody();
            if (errorResponse != null) {
                logger.info("- getSavingsAccountTransaction({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, error: {}]", accountsId, transactionId, isPretty, tenantIdentifier, isSuccessful, code, errorResponse.string());
            }
        }
        return accountTransaction;
    }


    public void getSavingsAccountTransactionAsync(Long accountsId, Long transactionId, boolean isPretty,
                                                                  String tenantIdentifier,
                                             Callback<SavingsAccountTransaction> callback) throws IOException {
        Call<SavingsAccountTransaction> call = savingsAccountInterface.getTransaction(accountsId, transactionId, isPretty, tenantIdentifier);
        call.enqueue(callback);
    }


    public SavingsAccountTransactionUndoResponse undoSavingsAccountTransaction(Long accountsId, Long transactionId, boolean isPretty, String tenantIdentifier) throws IOException {
        SavingsAccountTransactionUndoResponse reversedTransaction = null;
        Call<SavingsAccountTransactionUndoResponse> call = savingsAccountInterface.undoSavingsAccountTransaction(accountsId, transactionId, isPretty, tenantIdentifier);
        Response<SavingsAccountTransactionUndoResponse> response = call.execute();
        boolean isSuccessful = response.isSuccessful();
        int code = response.code();
        if (isSuccessful) {
            reversedTransaction = response.body();
            if (reversedTransaction != null) {
                logger.info("- undoSavingsAccountTransaction({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, accountTransaction: {}]", accountsId, transactionId, isPretty, tenantIdentifier, isSuccessful, code, reversedTransaction);
            } else {
                ResponseBody errorResponse = response.errorBody();
                if (errorResponse != null) {
                    logger.info("- undoSavingsAccountTransaction({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, error: {}]", accountsId, transactionId, isPretty, tenantIdentifier, isSuccessful, code, errorResponse.string());
                }
            }
        }else {
            ResponseBody errorResponse = response.errorBody();
            if (errorResponse != null) {
                logger.info("- undoSavingsAccountTransaction({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, error: {}]", accountsId, transactionId, isPretty, tenantIdentifier, isSuccessful, code, errorResponse.string());
            }
        }
        return reversedTransaction;
    }


    /**
     * This asynchronous method undo any savings account transacton given the
     * following params.
     * @param accountsId
     * @param transactionId
     * @param isPretty
     * @param tenantIdentifier
     * @param callback
     * @throws IOException
     */
    public void undoSavingsAccountTransaction(Long accountsId, Long transactionId,
                                                                               boolean isPretty,
                                                                               String tenantIdentifier,
                                              Callback<SavingsAccountTransactionUndoResponse> callback) throws IOException {
        Call<SavingsAccountTransactionUndoResponse> call = savingsAccountInterface.undoSavingsAccountTransaction(accountsId, transactionId, isPretty, tenantIdentifier);
        call.enqueue(callback);
    }


    public LoanDisbursementResponse disburse(Long loanId,
                                             LoanDisbursementRequest loanDisbursementRequest,
                                             boolean isPretty,
                                             String tenantIdentifier) throws IOException{
        LoanDisbursementResponse disburseResponse = null;
        Call<LoanDisbursementResponse> call = loanInterface.disburse(loanId, loanDisbursementRequest, isPretty, tenantIdentifier);
        Response<LoanDisbursementResponse> response = call.execute();

        boolean isSuccessful = response.isSuccessful();
        int code = response.code();
        if (isSuccessful) {
            disburseResponse = response.body();
            if (disburseResponse != null) {
                logger.info("- disburse({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, accountTransaction: {}]", loanId, loanDisbursementRequest, isPretty, tenantIdentifier, isSuccessful, code, disburseResponse);
            } else {
                ResponseBody errorResponse = response.errorBody();
                if (errorResponse != null) {
                    logger.info("- disburse({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, error: {}]", loanId, loanDisbursementRequest, isPretty, tenantIdentifier, isSuccessful, code, errorResponse.string());
                }
            }
        }else {
            ResponseBody errorResponse = response.errorBody();
            if (errorResponse != null) {
                logger.info("- disburse({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, error: {}]", loanId, loanDisbursementRequest, isPretty, tenantIdentifier, isSuccessful, code, errorResponse.string());
            }
        }

        return disburseResponse;
    }


    public void disburseAsync(Long loanId,
                                             LoanDisbursementRequest loanDisbursementRequest,
                                             boolean isPretty,
                                             String tenantIdentifier,
                                                  Callback<LoanDisbursementResponse> callback) throws IOException {
        Call<LoanDisbursementResponse> call = loanInterface.disburse(loanId, loanDisbursementRequest, isPretty, tenantIdentifier);
        call.enqueue(callback);
    }


    public void repayLoanAsync(Long loanId,
                      LoanRepaymentRequest loanRepaymentRequest,
                      boolean isPretty,
                      String tenantIdentifier,
                      Callback<LoanRepaymentResponse> callback) throws IOException{
        Call<LoanRepaymentResponse> call = loanInterface.repay(loanId, loanRepaymentRequest, isPretty, tenantIdentifier);
        call.enqueue(callback);

    }


    public UndoLoanDisbursementResponse undoDisbursal(Long loanId,
                                                      UndoLoanDisbursementRequest undoLoanDisbursementRequest,
                                                      boolean isPretty,
                                                      String tenantIdentifier) throws IOException{
        UndoLoanDisbursementResponse undoDisbursalResponse = null;
        Call<UndoLoanDisbursementResponse> call = loanInterface.undoDisbursal(loanId, undoLoanDisbursementRequest, isPretty, tenantIdentifier);
        Response<UndoLoanDisbursementResponse> response = call.execute();

        boolean isSuccessful = response.isSuccessful();
        int code = response.code();
        if (isSuccessful) {
            undoDisbursalResponse = response.body();
            if (undoDisbursalResponse != null) {
                logger.info("- undoDisbursal({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, accountTransaction: {}]", loanId, undoLoanDisbursementRequest, isPretty, tenantIdentifier, isSuccessful, code, undoDisbursalResponse);
            } else {
                ResponseBody errorResponse = response.errorBody();
                if (errorResponse != null) {
                    logger.info("- undoDisbursal({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, error: {}]", loanId, undoLoanDisbursementRequest, isPretty, tenantIdentifier, isSuccessful, code, errorResponse.string());
                }
            }
        }else {
            ResponseBody errorResponse = response.errorBody();
            if (errorResponse != null) {
                logger.info("- undoDisbursal({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, error: {}]", loanId, undoLoanDisbursementRequest, isPretty, tenantIdentifier, isSuccessful, code, errorResponse.string());
            }
        }

        return undoDisbursalResponse;
    }

    public void undoDisbursalAsync(Long loanId, UndoLoanDisbursementRequest undoLoanDisbursementRequest, boolean
            isPretty, String tenantIdentifier, Callback<UndoLoanDisbursementResponse> callback) throws IOException{
        Call<UndoLoanDisbursementResponse> call = loanInterface.undoDisbursal(loanId, undoLoanDisbursementRequest, true, "default");
        call.enqueue(callback);
    }

    public void getLoanAccountAsync(String loanAccNo, boolean isPretty, String tenantIdentifier, Callback<LoanAccountSearchResult> callback) throws IOException{
        Call<LoanAccountSearchResult> call = loanInterface.getLoanAccount(loanAccNo, isPretty, tenantIdentifier);
        call.enqueue(callback);

    }


    public Loan getLoanAccount(String loanAccNo, boolean isPretty, String tenantIdentifier) throws IOException{
        LoanAccountSearchResult result = null;
        Call<LoanAccountSearchResult> call = loanInterface.getLoanAccount(loanAccNo, isPretty, tenantIdentifier);

        Response<LoanAccountSearchResult> response = call.execute();

        boolean isSuccessful = response.isSuccessful();
        int code = response.code();
        if (isSuccessful) {
            result = response.body();
            if (result != null) {
                logger.info("- getLoanAccount({}, {}, {}) :Response [isSuccessful: {}, code: {}, accountTransaction: {}]", loanAccNo, isPretty, tenantIdentifier, isSuccessful, code, result);
            } else {
                ResponseBody errorResponse = response.errorBody();
                if (errorResponse != null) {
                    logger.info("- getLoanAccount({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, error: {}]", loanAccNo, isPretty, tenantIdentifier, isSuccessful, code, errorResponse.string());
                }
            }
        }else {
            ResponseBody errorResponse = response.errorBody();
            if (errorResponse != null) {
                logger.info("- getLoanAccount({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, error: {}]", loanAccNo, isPretty, tenantIdentifier, isSuccessful, code, errorResponse.string());
            }
        }

        return result.pageItems.get(0);

    }


    public void getRecurringDepositAccountAsync(String depositAccNo, boolean isPretty,
                                                              String tenantIdentifier,
                                                              Callback<List<RecurringDepositAccount>> callback) throws IOException{

        Call<List<RecurringDepositAccount>> call = savingsAccountInterface.getRecurringDepositAccount(depositAccNo, isPretty, tenantIdentifier);
        call.enqueue(callback);
    }

    public void getSavingsAccountAsync(String accountNo,
                                       boolean isPretty,
                                       String tenantIdentifier,
                                       Callback<SavingsAccountSearchResult> callback) throws IOException{
        Call<SavingsAccountSearchResult> call = savingsAccountInterface.getSavingsAccount(accountNo, isPretty, tenantIdentifier);
        call.enqueue(callback);
    }


    public SavingsAccount getSavingsAccount(String accountNo,
                                            boolean isPretty,
                                            String tenantIdentifier) throws IOException{
        SavingsAccount savingsAccount = null;
        Call<SavingsAccountSearchResult> call = savingsAccountInterface.getSavingsAccount(accountNo, isPretty, tenantIdentifier);

        Response<SavingsAccountSearchResult> response = call.execute();

        boolean isSuccessful = response.isSuccessful();
        int code = response.code();
        if (isSuccessful) {
            savingsAccount = getSavingsAccountByAccountNo(response.body().pageItems, accountNo);
            if (savingsAccount != null) {
                logger.info("- getSavingsAccount({}, {}, {}) :Response [isSuccessful: {}, code: {}, accountTransaction: {}]", accountNo, isPretty, tenantIdentifier, isSuccessful, code, savingsAccount);
            } else {
                ResponseBody errorResponse = response.errorBody();
                if (errorResponse != null) {
                    logger.info("- getSavingsAccount({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, error: {}]", accountNo, isPretty, tenantIdentifier, isSuccessful, code, errorResponse.string());
                }
            }
        }else {
            ResponseBody errorResponse = response.errorBody();
            if (errorResponse != null) {
                logger.info("- getSavingsAccount({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, error: {}]", accountNo, isPretty, tenantIdentifier, isSuccessful, code, errorResponse.string());
            }
        }

        return savingsAccount;
    }


    private SavingsAccount getSavingsAccountByAccountNo(List<SavingsAccount> savingsAccounts, String accountNo){
        SavingsAccount account = null;
        for(SavingsAccount savingsAccount: savingsAccounts){
            if(savingsAccount.getAccountNo().equals(accountNo)){
                account = savingsAccount;
                break;
            }
        }
        return account;
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


    public RecurringDepositAccount getRecurringDepositAccount(String depositAccNo, boolean isPretty,
                                                String tenantIdentifier) throws IOException{

        RecurringDepositAccount depositAccount = null;
        Call<List<RecurringDepositAccount>> call = savingsAccountInterface.getRecurringDepositAccount(depositAccNo, isPretty, tenantIdentifier);

        Response<List<RecurringDepositAccount>> response = call.execute();

        boolean isSuccessful = response.isSuccessful();
        int code = response.code();
        if (isSuccessful) {
            depositAccount = getDepositAccountByAccountNo(response.body(), depositAccNo);
            if (depositAccount != null) {
                logger.info("- getRecurringDepositAccount({}, {}, {}) :Response [isSuccessful: {}, code: {}, accountTransaction: {}]", depositAccNo, isPretty, tenantIdentifier, isSuccessful, code, depositAccount);
            } else {
                ResponseBody errorResponse = response.errorBody();
                if (errorResponse != null) {
                    logger.info("- getRecurringDepositAccount({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, error: {}]", depositAccNo, isPretty, tenantIdentifier, isSuccessful, code, errorResponse.string());
                }
            }
        }else {
            ResponseBody errorResponse = response.errorBody();
            if (errorResponse != null) {
                logger.info("- getRecurringDepositAccount({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, error: {}]", depositAccNo, isPretty, tenantIdentifier, isSuccessful, code, errorResponse.string());
            }
        }

        return depositAccount;

    }
}
