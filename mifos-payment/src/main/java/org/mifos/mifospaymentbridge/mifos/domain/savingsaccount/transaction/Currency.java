package org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;

    @JsonProperty("decimalPlaces")
    private Long decimalPlaces;

    @JsonProperty("inMultiplesOf")
    private Long inMultiplesOf;

    @JsonProperty("displaySymbol")
    private String displaySymbol;

    @JsonProperty("nameCode")
    private String nameCode;

    @JsonProperty("displayLabel")
    private String displayLabel;
}
