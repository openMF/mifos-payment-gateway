package org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "periodType",
        "fromPeriod",
        "toPeriod",
        "annualInterestRate",
        "currency"
})
@Data
public class ChartSlab {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("periodType")
    public PeriodType periodType;
    @JsonProperty("fromPeriod")
    public Integer fromPeriod;
    @JsonProperty("toPeriod")
    public Integer toPeriod;
    @JsonProperty("annualInterestRate")
    public Integer annualInterestRate;
    @JsonProperty("currency")
    public Currency__ currency;

}
