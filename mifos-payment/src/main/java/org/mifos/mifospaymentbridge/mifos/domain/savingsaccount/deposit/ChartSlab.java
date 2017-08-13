package org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
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