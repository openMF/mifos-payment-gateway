package org.mifos.mifospaymentbridge.integration.ProviderApiService;

import org.mifos.mifospaymentbridge.model.OutboundRequest;
import org.mifos.mifospaymentbridge.model.Status;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by vladimirfomene on 8/4/17.
 */

public interface PaymentInterface {

    @POST("/payments")
    Call<Status> makePayment(@Body OutboundRequest request);
}
