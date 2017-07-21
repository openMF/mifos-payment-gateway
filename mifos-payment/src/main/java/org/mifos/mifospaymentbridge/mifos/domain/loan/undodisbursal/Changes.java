package org.mifos.mifospaymentbridge.mifos.domain.loan.undodisbursal;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.mifos.mifospaymentbridge.mifos.domain.loan.disbursement.Status;

/**
 * Created by vladimirfomene on 8/2/17.
 */
public class Changes {

    @JsonProperty("accountNumber")
    private String accountNumber;

    @JsonProperty("checkNumber")
    private String checkNumber;

    @JsonProperty("routingCode")
    private String routingCode;

    @JsonProperty("receiptNumber")
    private String receiptNumber;

    @JsonProperty("bankNumber")
    private String bankNumber;

    @JsonProperty("status")
    private Status status;

    @JsonProperty("actualDisbursementDate")
    private String actualDisbursementDate;
}
