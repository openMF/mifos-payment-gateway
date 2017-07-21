package org.mifos.mifospaymentbridge.mifos.domain.loan.disbursement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanDisbursementRequest {
    private String dateFormat;

    private String locale;

    private Double transactionAmount;

    private Double fixedEmiAmount;

    private String actualDisbursementDate;

    private String paymentTypeId;

    private String note;

    private String accountNumber;

    private String checkNumber;

    private String routingCode;

    private String receiptNumber;

    private String bankNumber;
}
