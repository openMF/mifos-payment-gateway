package org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Changes {

    @JsonProperty("accountNumber")
    public String accountNumber;

    @JsonProperty("checkNumber")
    public String checkNumber;

    @JsonProperty("routingCode")
    public String routingCode;

    @JsonProperty("receiptNumber")
    public String receiptNumber;

    @JsonProperty("bankNumber")
    public String bankNumber;

}
