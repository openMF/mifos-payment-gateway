/**
 * SavingsAccountInterface.java
 * ============================================
 * This is an interface to do transaction on a
 * client's mifos saving account. This code was
 * adapted from @Antony Omeri original implementation.
 * @VladimirFomene
 */

package org.mifos.mifospaymentbridge.mifos.api;

import org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit.FixedDepositAccount;
import org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit.SavingsAccountDepositRequest;
import org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit.SavingsAccountDepositResponse;
import org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.transaction.SavingsAccountTransaction;
import org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.transaction.SavingsAccountTransactionUndoResponse;
import org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.withdrawal.SavingsAccountWithdrawRequest;
import org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.withdrawal.SavingsAccountWithdrawResponse;
import retrofit2.Call;
import retrofit2.http.*;


public interface SavingsAccountInterface {

    /**
     * Deposit API for Mifos
     *
     * @param accountsId Account Identifier
     * @param depositRequest SavingsAccountDepositRequest object
     * @param isPretty Flag whether to format JSON
     * @param tenantIdentifier Mifos Tenant Identifier
     * @return
     */
    @POST("savingsaccounts/{accountsId}/transactions?command=deposit")
    Call<SavingsAccountDepositResponse> deposit(@Path("accountsId") String accountsId,
                                                @Body SavingsAccountDepositRequest depositRequest,
                                                @Query("pretty") boolean isPretty,
                                                @Query("tenantIdentifier") String tenantIdentifier);

    /**
     * Withdraw API for Mifos
     *
     * @param accountsId Account Identifier
     * @param withdrawRequest SavingsAccountWithdrawRequest object
     * @param isPretty Flag whether to format JSON
     * @param tenantIdentifier Mifos Tenant Identifier
     * @return
     */
    @POST("savingsaccounts/{accountsId}/transactions?command=withdraw")
    Call<SavingsAccountWithdrawResponse> withdraw(@Path("accountsId") Long accountsId,
                                                  @Body SavingsAccountWithdrawRequest withdrawRequest,
                                                  @Query("pretty") boolean isPretty,
                                                  @Query("tenantIdentifier") String tenantIdentifier);

    /**
     * API to fetch savings account transaction by transaction Identifier
     *
     * @param accountsId Account Identifier
     * @param transactionId transaction Identifier
     * @param isPretty Flag whether to format JSON
     * @param tenantIdentifier Mifos Tenant Identifier
     * @return
     */
    @GET("savingsaccounts/{accountsId}/transactions/transactions/{transactionId}")
    Call<SavingsAccountTransaction> getTransaction(@Path("accountsId") Long accountsId,
                                                   @Path("transactionId") Long transactionId,
                                                   @Query("pretty") boolean isPretty,
                                                   @Query("tenantIdentifier") String tenantIdentifier);

    /**
     * API to fetch savings account transaction by transaction Identifier
     *
     * @param accountsId Account Identifier
     * @param transactionId transaction Identifier
     * @param isPretty Flag whether to format JSON
     * @param tenantIdentifier Mifos Tenant Identifier
     * @return
     */
    @POST("savingsaccounts/{accountsId}/transactions/{transactionId}?command=undo")
    Call<SavingsAccountTransactionUndoResponse> undoSavingsAccountTransaction(@Path("accountsId") Long accountsId,
                                                               @Path("transactionId") Long transactionId,
                                                               @Query("pretty") boolean isPretty,
                                                               @Query("tenantIdentifier") String tenantIdentifier);


    /**
     * API to fetch fixed deposit account by account identifier
     *
     * @param accountId Account Identifier
     * @param isPretty Flag whether to format JSON
     * @param tenantIdentifier Mifos Tenant Identifier
     * @return
     */
    @GET("fixeddepositaccounts/{accountId}")
    Call<FixedDepositAccount> getFixDepositAccount(@Path("accountId") Long accountId,
                                                            @Query("pretty") boolean isPretty,
                                                            @Query("tenantIdentifier") String tenantIdentifier);
}
