package org.mifos.mifospaymentbridge.mifos.domain.loan;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "submittedOnDate",
        "submittedByUsername",
        "submittedByFirstname",
        "submittedByLastname",
        "approvedOnDate",
        "approvedByUsername",
        "approvedByFirstname",
        "approvedByLastname",
        "expectedDisbursementDate",
        "actualDisbursementDate",
        "disbursedByUsername",
        "disbursedByFirstname",
        "disbursedByLastname",
        "closedOnDate",
        "expectedMaturityDate"
})
@Data
public class Timeline {

    @JsonProperty("submittedOnDate")
    public List<Integer> submittedOnDate = null;
    @JsonProperty("submittedByUsername")
    public String submittedByUsername;
    @JsonProperty("submittedByFirstname")
    public String submittedByFirstname;
    @JsonProperty("submittedByLastname")
    public String submittedByLastname;
    @JsonProperty("approvedOnDate")
    public List<Integer> approvedOnDate = null;
    @JsonProperty("approvedByUsername")
    public String approvedByUsername;
    @JsonProperty("approvedByFirstname")
    public String approvedByFirstname;
    @JsonProperty("approvedByLastname")
    public String approvedByLastname;
    @JsonProperty("expectedDisbursementDate")
    public List<Integer> expectedDisbursementDate = null;
    @JsonProperty("actualDisbursementDate")
    public List<Integer> actualDisbursementDate = null;
    @JsonProperty("disbursedByUsername")
    public String disbursedByUsername;
    @JsonProperty("disbursedByFirstname")
    public String disbursedByFirstname;
    @JsonProperty("disbursedByLastname")
    public String disbursedByLastname;
    @JsonProperty("closedOnDate")
    public List<Integer> closedOnDate = null;
    @JsonProperty("expectedMaturityDate")
    public List<Integer> expectedMaturityDate = null;

}