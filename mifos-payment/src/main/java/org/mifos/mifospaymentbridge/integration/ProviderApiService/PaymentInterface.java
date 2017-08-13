package org.mifos.mifospaymentbridge.integration.ProviderApiService;

import org.mifos.mifospaymentbridge.mifos.domain.loan.Loan;
import org.mifos.mifospaymentbridge.model.InboundCallbackLog;
import org.mifos.mifospaymentbridge.model.OutboundRequest;
import org.mifos.mifospaymentbridge.model.Status;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by vladimirfomene on 8/4/17.
 */

public interface PaymentInterface {

    @POST("outbound/payments")
    Call<Status> makePayment(@Body OutboundRequest request);

    @POST("inbound/loans/{loanId}")
    Call<InboundCallbackLog> sendLoanAccount(@Body Loan loanAccount,
                                             @Path("loanId") Long loanId,
                                             @Query("inboundRequestId") Long inboundRequestId);

    @POST("inbound/payments/status")
    Call<Status> sendInboundTransactionStatus(@Body Status callbackStatus);


}
