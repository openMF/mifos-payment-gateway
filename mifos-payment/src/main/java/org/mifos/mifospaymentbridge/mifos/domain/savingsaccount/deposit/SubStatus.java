package org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "code",
        "value",
        "none",
        "inactive",
        "dormant",
        "escheat",
        "block",
        "blockCredit",
        "blockDebit"
})
public class SubStatus {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("code")
    public String code;
    @JsonProperty("value")
    public String value;
    @JsonProperty("none")
    public Boolean none;
    @JsonProperty("inactive")
    public Boolean inactive;
    @JsonProperty("dormant")
    public Boolean dormant;
    @JsonProperty("escheat")
    public Boolean escheat;
    @JsonProperty("block")
    public Boolean block;
    @JsonProperty("blockCredit")
    public Boolean blockCredit;
    @JsonProperty("blockDebit")
    public Boolean blockDebit;

}
