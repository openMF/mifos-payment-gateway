package org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionType {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("code")
    private String code;

    @JsonProperty("value")
    private String value;

    @JsonProperty("deposit")
    private Boolean deposit;

    @JsonProperty("dividendPayout")
    private Boolean dividendPayout;

    @JsonProperty("withdrawal")
    private Boolean withdrawal;

    @JsonProperty("interestPosting")
    private Boolean interestPosting;

    @JsonProperty("feeDeduction")
    private Boolean feeDeduction;

    @JsonProperty("initiateTransfer")
    private Boolean initiateTransfer;

    @JsonProperty("approveTransfer")
    private Boolean approveTransfer;

    @JsonProperty("withdrawTransfer")
    private Boolean withdrawTransfer;

    @JsonProperty("rejectTransfer")
    private Boolean rejectTransfer;

    @JsonProperty("overdraftInterest")
    private Boolean overdraftInterest;

    @JsonProperty("writtenoff")
    private Boolean writtenoff;

    @JsonProperty("overdraftFee")
    private Boolean overdraftFee;

    @JsonProperty("withholdTax")
    private Boolean withholdTax;

    @JsonProperty("escheat")
    private Boolean escheat;
}
