package org.mifos.mifospaymentbridge.integration.ProviderApiService;

import org.mifos.mifospaymentbridge.mifos.domain.loan.Loan;
import org.mifos.mifospaymentbridge.model.InboundCallbackLog;
import org.mifos.mifospaymentbridge.model.OutboundRequest;
import org.mifos.mifospaymentbridge.model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

/**
 * Created by vladimirfomene on 8/4/17.
 */
@Service
public class PaymentService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Retrofit createPaymentInterface(String mmpApiUrl){
        return new Retrofit.Builder().baseUrl(mmpApiUrl)
                .addConverterFactory(JacksonConverterFactory
                        .create()).build();
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

    public InboundCallbackLog sendLoanAccount(String mmpApiUrl, Loan loanAccount, Long requestId){
        PaymentInterface paymentInterface = createPaymentInterface(mmpApiUrl).create(PaymentInterface.class);
        Call<InboundCallbackLog> call = paymentInterface.sendLoanAccount(loanAccount, loanAccount.getId(), requestId);

        Response<InboundCallbackLog> mmpResponse = null;
        InboundCallbackLog inboundCallback = null;

        try{
            mmpResponse = call.execute();

        }catch(IOException ex){

            logger.error(ex.getMessage(), ex);
        }

        if(mmpResponse.isSuccessful()) inboundCallback = mmpResponse.body();


        return inboundCallback;
    }

    public Status sendInboundTransactionStatus(String mmpApiUrl, Status callbackStatus){
        PaymentInterface paymentInterface = createPaymentInterface(mmpApiUrl).create(PaymentInterface.class);
        //System.out.println(callbackStatus.toString());
        Call<Status> call = paymentInterface.sendInboundTransactionStatus(callbackStatus);

        Response<Status> mmpResponse = null;
        Status inboundStatus = null;

        try{
            mmpResponse = call.execute();

        }catch(IOException ex){

            logger.error(ex.getMessage(), ex);
        }

        if(mmpResponse.isSuccessful()) inboundStatus = mmpResponse.body();

        return inboundStatus;
    }


}
