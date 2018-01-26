package org.mifos.mifospaymentbridge.mifos.domain.loan.undodisbursal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UndoLoanDisbursementResponse {
    private Long officeId;

    private Long clientId;

    private Long loanId;

    private Long resourceId;

    private Changes changes;

    private String actualDisbursementDate;
}
