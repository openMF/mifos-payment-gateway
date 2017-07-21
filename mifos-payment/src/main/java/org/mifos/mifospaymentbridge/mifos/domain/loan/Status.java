package org.mifos.mifospaymentbridge.mifos.domain.loan;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "code",
        "value",
        "pendingApproval",
        "waitingForDisbursal",
        "active",
        "closedObligationsMet",
        "closedWrittenOff",
        "closedRescheduled",
        "closed",
        "overpaid"
})
public class Status {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("code")
    public String code;
    @JsonProperty("value")
    public String value;
    @JsonProperty("pendingApproval")
    public Boolean pendingApproval;
    @JsonProperty("waitingForDisbursal")
    public Boolean waitingForDisbursal;
    @JsonProperty("active")
    public Boolean active;
    @JsonProperty("closedObligationsMet")
    public Boolean closedObligationsMet;
    @JsonProperty("closedWrittenOff")
    public Boolean closedWrittenOff;
    @JsonProperty("closedRescheduled")
    public Boolean closedRescheduled;
    @JsonProperty("closed")
    public Boolean closed;
    @JsonProperty("overpaid")
    public Boolean overpaid;

}