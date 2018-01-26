/**
 * DisbursementRequest.java
 * =================================================
 * This class represent the request body sent to fineract
 * for loan disbursement
 * @author vladimirfomene
 */


package org.mifos.mifospaymentbridge.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DisbursementRequest {
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
