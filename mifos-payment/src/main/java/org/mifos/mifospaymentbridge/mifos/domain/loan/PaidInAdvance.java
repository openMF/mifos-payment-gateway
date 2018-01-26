package org.mifos.mifospaymentbridge.mifos.domain.loan;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "paidInAdvance"
})
@Data
public class PaidInAdvance {

    @JsonProperty("paidInAdvance")
    public Integer paidInAdvance;

}