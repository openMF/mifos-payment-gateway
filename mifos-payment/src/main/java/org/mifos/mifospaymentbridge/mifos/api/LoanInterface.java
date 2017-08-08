/**
 * LoanInterface.java
 * ================================
 * This interface is for performing client focus
 * loan operations on mifos.
 * @Vladimirfomene
 */

package org.mifos.mifospaymentbridge.mifos.api;

import org.mifos.mifospaymentbridge.mifos.domain.loan.Loan;
import org.mifos.mifospaymentbridge.mifos.domain.loan.disbursement.LoanDisbursementRequest;
import org.mifos.mifospaymentbridge.mifos.domain.loan.disbursement.LoanDisbursementResponse;
import org.mifos.mifospaymentbridge.mifos.domain.loan.repayment.LoanRepaymentRequest;
import org.mifos.mifospaymentbridge.mifos.domain.loan.repayment.LoanRepaymentResponse;
import org.mifos.mifospaymentbridge.mifos.domain.loan.undodisbursal.UndoLoanDisbursementRequest;
import org.mifos.mifospaymentbridge.mifos.domain.loan.undodisbursal.UndoLoanDisbursementResponse;
import org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.transaction.SavingsAccountTransaction;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface LoanInterface {


    /**
     * API to fetch a loan account using a loan account identifier
     *
     * @param accountNo loan account number
     * @param isPretty Flag whether to format JSON
     * @param tenantIdentifier Mifos Tenant Identifier
     * @return
     */
    @GET("loans")
    Call<Loan> getLoanAccount(@Query("accountNo") String accountNo,
                        @Query("pretty") boolean isPretty,
                        @Query("tenantIdentifier") String tenantIdentifier);


    /**
     * API to disburse a loan to a client from mifos
     *
     * @param loanId loan Identifier
     * @param isPretty Flag whether to format JSON
     * @param tenantIdentifier Mifos Tenant Identifier
     * @return
     */
    @GET("loans/{loanId}?command=disburse")
    Call<LoanDisbursementResponse> disburse(@Path("loanId") Long loanId,
                                            @Body LoanDisbursementRequest loanDisbursementRequest,
                                            @Query("pretty") boolean isPretty,
                                            @Query("tenantIdentifier") String tenantIdentifier);



    /**
     * API to submit a loan repayment to mifos
     *
     * @param loanRepaymentRequest request body
     * @param loanId loan Identifier
     * @param isPretty Flag whether to format JSON
     * @param tenantIdentifier Mifos Tenant Identifier
     * @return
     */
    @GET("loans/{loanId}/transactions?command=repayment")
    Call<LoanRepaymentResponse> repay(@Path("loanId") Long loanId,
                                         @Body LoanRepaymentRequest loanRepaymentRequest,
                                         @Query("pretty") boolean isPretty,
                                         @Query("tenantIdentifier") String tenantIdentifier);


    /**
     * API to undo a loan disbursal to mifos
     *
     * @param undoLoanDisbursementRequest request body
     * @param loanId loan Identifier
     * @param isPretty Flag whether to format JSON
     * @param tenantIdentifier Mifos Tenant Identifier
     * @return
     */
    @GET("loans/{loanId}?command=undoDisbursal")
    Call<UndoLoanDisbursementResponse> undoDisbursal(@Path("loanId") Long loanId,
                                                     @Body UndoLoanDisbursementRequest undoLoanDisbursementRequest,
                                                     @Query("pretty") boolean isPretty,
                                                     @Query("tenantIdentifier") String tenantIdentifier);
}
