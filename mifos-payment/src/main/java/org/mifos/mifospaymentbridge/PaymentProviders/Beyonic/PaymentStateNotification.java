package org.mifos.mifospaymentbridge.PaymentProviders.Beyonic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentStateNotification {

    @JsonProperty("hook")
    private Hook hook;

    @JsonProperty("data")
    private PaymentData data;
}
