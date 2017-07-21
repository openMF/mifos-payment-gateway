package org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "fromDate",
        "accountId",
        "accountNumber",
        "chartSlabs",
        "periodTypes"
})
public class AccountChart {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("fromDate")
    public List<Integer> fromDate = null;
    @JsonProperty("accountId")
    public Integer accountId;
    @JsonProperty("accountNumber")
    public String accountNumber;
    @JsonProperty("chartSlabs")
    public List<ChartSlab> chartSlabs = null;
    @JsonProperty("periodTypes")
    public List<PeriodType_> periodTypes = null;

}
