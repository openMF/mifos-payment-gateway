/**
 * File: PaymentService.java
 * ==================================
 * A collection of methods to interact with Beyonic's
 * payment gateway.
 */


package org.mifos.mifospaymentbridge.PaymentProviders.Beyonic;


import retrofit2.Retrofit;
import org.mifos.mifospaymentbridge.PaymentProviders.Beyonic.BeyonicProperties;
import org.mifos.mifospaymentbridge.PaymentProviders.Beyonic.PaymentRequest;


public class PaymentService {

    private PaymentInterface paymentInterface;

    private PaymentInterface createPaymentService(String endPoint){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endPoint)
                .build();

        return retrofit.create(PaymentInterface.class);
    }

    private void sendPayment(PaymentRequest request){
        BeyonicProperties beyonicProp = new BeyonicProperties();
        paymentInterface = createPaymentService(beyonicProp.getEND_POINT());
        paymentInterface.createNewPayment(request.getPhonenumber(), request.getCurrency(),
                request.getAmount(), request.getDescription(),
                request.getCallback_url(), request.getPayment_type(),
                "Token " + beyonicProp.getAPI_TOKEN());

    }
}
