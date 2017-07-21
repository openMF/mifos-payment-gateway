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
import org.mifos.mifospaymentbridge.mifos.domain.loan.disbursement.LoanDisbursementRequest;
import org.mifos.mifospaymentbridge.mifos.domain.loan.disbursement.LoanDisbursementResponse;
import org.mifos.mifospaymentbridge.mifos.domain.loan.repayment.LoanRepaymentRequest;
import org.mifos.mifospaymentbridge.mifos.domain.loan.repayment.LoanRepaymentResponse;
import org.mifos.mifospaymentbridge.mifos.domain.loan.undodisbursal.UndoLoanDisbursementRequest;
import org.mifos.mifospaymentbridge.mifos.domain.loan.undodisbursal.UndoLoanDisbursementResponse;
import org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit.SavingsAccountDepositRequest;
import org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit.SavingsAccountDepositResponse;
import org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.transaction.SavingsAccountTransaction;
import org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.transaction.SavingsAccountTransactionUndoResponse;
import org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.withdrawal.SavingsAccountWithdrawRequest;
import org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.withdrawal.SavingsAccountWithdrawResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import java.io.IOException;

@Service
public class MifosService {
    private ClientInterface clientInterface = null;
    private SavingsAccountInterface savingsAccountInterface = null;
    private LoanInterface loanInterface = null;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public MifosService(MifosProperties mifosProperties) {
        String mifosApiUrl = mifosProperties.getBaseUrl() + mifosProperties.getApiVersion();
        logger.info("Creating mifos service with baseUrl: {}", mifosApiUrl);
        clientInterface = ApiClient.getClient(mifosApiUrl, mifosProperties.getUsername(), mifosProperties.getPassword()).create(ClientInterface.class);
    }



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


    public Client getClientByID(Long clientId, String tenantIdentifier, boolean isPretty) throws IOException {
        Client client = null;
        Call<Client> call = clientInterface.getClientById(clientId, tenantIdentifier, isPretty);
        Response<Client> clientResponse = call.execute();
        boolean isSuccessful = clientResponse.isSuccessful();
        int code = clientResponse.code();
        if (isSuccessful) {
            client = clientResponse.body();
            if (client != null) {
                logger.info("- getClientByID({},{},{}) :Response [isSuccessful: {}, code: {}, client: {}]", clientId, tenantIdentifier, isPretty, isSuccessful, code, client);
            }
        } else {
            ResponseBody errorResponse = clientResponse.errorBody();
            if (errorResponse != null) {
                logger.info("- getClientByID({},{},{}) :Response [isSuccessful: {}, code: {}, error: {}]", clientId, tenantIdentifier, isPretty, isSuccessful, code, errorResponse.string());
            }
        }
        return client;
    }


    public Client getClientByID(Long clientId, String tenantIdentifier, boolean isPretty, String fields) throws IOException {
        Client client = null;
        Call<Client> call = clientInterface.getClientById(clientId, tenantIdentifier, false, fields);
        Response<Client> response = call.execute();
        boolean isSuccessful = response.isSuccessful();
        int code = response.code();
        if (isSuccessful) {
            client = response.body();
            if (client != null) {
                logger.info("- getClientByID({},{},{},{}) :Response [isSuccessful: {}, code: {}, client: {}]", clientId, tenantIdentifier, isPretty, fields, isSuccessful, code, client);
            }
        } else {
            ResponseBody errorResponse = response.errorBody();
            if (errorResponse != null) {
                logger.info("- getClientByID({},{},{}, {}) :Response [isSuccessful: {}, code: {}, error: {}]", clientId, tenantIdentifier, isPretty, fields, isSuccessful, code, errorResponse.string());
            }
        }
        return client;
    }


    public SavingsAccountDepositResponse depositToSavingsAccount(Long accountsId, SavingsAccountDepositRequest depositRequest, boolean isPretty, String tenantIdentifier) throws IOException {
        SavingsAccountDepositResponse depositResponse = null;
        Call<SavingsAccountDepositResponse> call = savingsAccountInterface.deposit(accountsId, depositRequest, isPretty, tenantIdentifier);
        Response<SavingsAccountDepositResponse> response = call.execute();
        boolean isSuccessful = response.isSuccessful();
        int code = response.code();
        if (isSuccessful) {
            depositResponse = response.body();
            if (depositResponse != null) {
                logger.info("- depositToSavingsAccount({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, depositResponse: {}]", accountsId, depositRequest, isPretty, tenantIdentifier, isSuccessful, code, depositResponse);
            } else {
                ResponseBody errorResponse = response.errorBody();
                if (errorResponse != null) {
                    logger.info("- depositToSavingsAccount({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, error: {}]", accountsId, depositRequest, isPretty, tenantIdentifier, isSuccessful, code, errorResponse.string());
                }
            }
        } else {
            ResponseBody errorResponse = response.errorBody();
            if (errorResponse != null) {
                logger.info("- depositToSavingsAccount({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, error: {}]", accountsId, depositRequest, isPretty, tenantIdentifier, isSuccessful, code, errorResponse.string());
            }
        }
        return depositResponse;
    }


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


    public LoanRepaymentResponse repay(Long loanId,
                                                LoanRepaymentRequest loanRepaymentRequest,
                                                boolean isPretty,
                                                String tenantIdentifier) throws IOException{
        LoanRepaymentResponse repaymentResponse = null;
        Call<LoanRepaymentResponse> call = loanInterface.repay(loanId, loanRepaymentRequest, isPretty, tenantIdentifier);
        Response<LoanRepaymentResponse> response = call.execute();

        boolean isSuccessful = response.isSuccessful();
        int code = response.code();
        if (isSuccessful) {
            repaymentResponse = response.body();
            if (repaymentResponse != null) {
                logger.info("- repay({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, accountTransaction: {}]", loanId, loanRepaymentRequest, isPretty, tenantIdentifier, isSuccessful, code, repaymentResponse);
            } else {
                ResponseBody errorResponse = response.errorBody();
                if (errorResponse != null) {
                    logger.info("- repay({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, error: {}]", loanId, loanRepaymentRequest, isPretty, tenantIdentifier, isSuccessful, code, errorResponse.string());
                }
            }
        }else {
            ResponseBody errorResponse = response.errorBody();
            if (errorResponse != null) {
                logger.info("- repay({}, {}, {}, {}) :Response [isSuccessful: {}, code: {}, error: {}]", loanId, loanRepaymentRequest, isPretty, tenantIdentifier, isSuccessful, code, errorResponse.string());
            }
        }

        return repaymentResponse;
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
}
