package org.mifos.mifospaymentbridge.PaymentProviders.Beyonic;


import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Header;

public interface PaymentInterface {

    @FormUrlEncoded
    @POST("payments")
    Call<PaymentResponse> createNewPayment(@Part("phonenumber") String phoneNumber,
                                         @Part("currency") String currency,
                                         @Part("amount") Double amount,
                                         @Part("description") String description,
                                         @Part("callback_url") String callbackUrl,
                                         @Part("payment_type") String paymentType,
                                           @Header("Authorization") String authorization);
}
