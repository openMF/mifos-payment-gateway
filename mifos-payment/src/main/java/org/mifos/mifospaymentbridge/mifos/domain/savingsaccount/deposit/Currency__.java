package org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "code",
        "name",
        "decimalPlaces",
        "displaySymbol",
        "nameCode",
        "displayLabel"
})
@Data
public class Currency__ {

    @JsonProperty("code")
    public String code;
    @JsonProperty("name")
    public String name;
    @JsonProperty("decimalPlaces")
    public Integer decimalPlaces;
    @JsonProperty("displaySymbol")
    public String displaySymbol;
    @JsonProperty("nameCode")
    public String nameCode;
    @JsonProperty("displayLabel")
    public String displayLabel;

}
