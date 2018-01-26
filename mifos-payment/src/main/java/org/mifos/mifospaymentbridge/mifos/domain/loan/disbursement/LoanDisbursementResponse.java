package org.mifos.mifospaymentbridge.mifos.domain.loan.disbursement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanDisbursementResponse {

     private Long officeId;

     private Long clientId;

     private Long loanId;

     private Long resourceId;

     private Changes changes;

     private String locale;

     private String dateFormat;

     private String actualDisbursementDate;

     private Double transactionAmount;

}
