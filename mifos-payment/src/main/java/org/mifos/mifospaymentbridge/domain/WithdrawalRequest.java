package org.mifos.mifospaymentbridge.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by vladimirfomene on 8/1/17.
 */


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawalRequest {
    private String dateFormat;

    private String locale;

    private Double transactionAmount;

    private String transactionDate;

    private String paymentTypeId;

    private String note;

    private String accountNumber;

    private String checkNumber;

    private String routingCode;

    private String receiptNumber;

    private String bankNumber;
}
