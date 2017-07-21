package org.mifos.mifospaymentbridge.integration.ProviderApiService;

import org.mifos.mifospaymentbridge.model.OutboundRequest;
import org.mifos.mifospaymentbridge.model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

/**
 * Created by vladimirfomene on 8/4/17.
 */
@Service
public class PaymentService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Retrofit createPaymentInterface(String mmpApiUrl){
        return new Retrofit.Builder().baseUrl(mmpApiUrl).build();
    }

    public Status sendPayment(String mmpApiUrl, OutboundRequest request){
        PaymentInterface paymentInterface = createPaymentInterface(mmpApiUrl).create(PaymentInterface.class);
        Call<Status> call = paymentInterface.makePayment(request);

        Response<Status> mmpResponse = null;
        Status mmpStatus = null;

        try{
            mmpResponse = call.execute();

        }catch(IOException ex){

            logger.error(ex.getMessage(), ex);
        }

        if(mmpResponse.isSuccessful()) mmpStatus = mmpResponse.body();


        return mmpStatus;

    }


}
