package org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "code",
        "value"
})
@Data
public class InterestCompoundingPeriodType {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("code")
    public String code;
    @JsonProperty("value")
    public String value;

}
