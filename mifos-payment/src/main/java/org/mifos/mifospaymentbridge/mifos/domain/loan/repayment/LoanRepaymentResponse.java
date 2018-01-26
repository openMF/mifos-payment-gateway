package org.mifos.mifospaymentbridge.mifos.domain.loan.repayment;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanRepaymentResponse {

    private Long officeId;

    private Long clientId;

    private Long loanId;

    private Long resourceId;

    private Changes changes;
}
