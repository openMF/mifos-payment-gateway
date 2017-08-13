package org.mifos.mifospaymentbridge.mifos.domain.loan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanAccountRequest {

    @JsonProperty("loanAccountNo")
    public String loanAccountNo;
}
