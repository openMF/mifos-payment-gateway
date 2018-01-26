package org.mifos.mifospaymentbridge.mifos.domain.loan.disbursement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Status {

    private Long id;

    private String code;

    private String value;

    private Boolean pendingApproval;

    private Boolean waitingForDisbursal;

    private Boolean active;

    private Boolean closedObligationsMet;

    private Boolean closedWrittenOff;

    private Boolean closedRescheduled;

    private Boolean closed;

    private Boolean overpaid;
}
