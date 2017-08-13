package org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Status {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("code")
    public String code;
    @JsonProperty("value")
    public String value;
    @JsonProperty("submittedAndPendingApproval")
    public Boolean submittedAndPendingApproval;
    @JsonProperty("approved")
    public Boolean approved;
    @JsonProperty("rejected")
    public Boolean rejected;
    @JsonProperty("withdrawnByApplicant")
    public Boolean withdrawnByApplicant;
    @JsonProperty("active")
    public Boolean active;
    @JsonProperty("closed")
    public Boolean closed;
    @JsonProperty("prematureClosed")
    public Boolean prematureClosed;
    @JsonProperty("transferInProgress")
    public Boolean transferInProgress;
    @JsonProperty("transferOnHold")
    public Boolean transferOnHold;

}
