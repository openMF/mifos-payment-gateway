package org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency_ {

    @JsonProperty("code")
    public String code;
    @JsonProperty("name")
    public String name;
    @JsonProperty("decimalPlaces")
    public Integer decimalPlaces;
    @JsonProperty("inMultiplesOf")
    public Integer inMultiplesOf;
    @JsonProperty("displaySymbol")
    public String displaySymbol;
    @JsonProperty("nameCode")
    public String nameCode;
    @JsonProperty("displayLabel")
    public String displayLabel;

}