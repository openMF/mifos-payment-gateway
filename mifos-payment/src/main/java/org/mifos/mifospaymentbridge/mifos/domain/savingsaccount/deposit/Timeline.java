package org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit;

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
        "submittedByLastname"
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

}