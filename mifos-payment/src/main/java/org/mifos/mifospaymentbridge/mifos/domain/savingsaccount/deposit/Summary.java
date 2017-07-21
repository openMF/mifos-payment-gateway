package org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "currency",
        "accountBalance"
})
public class Summary {

    @JsonProperty("currency")
    public Currency_ currency;
    @JsonProperty("accountBalance")
    public Integer accountBalance;

}
