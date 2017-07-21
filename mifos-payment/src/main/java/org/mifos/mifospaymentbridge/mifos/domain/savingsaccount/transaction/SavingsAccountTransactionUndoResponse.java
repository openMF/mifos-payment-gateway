package org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SavingsAccountTransactionUndoResponse {
    @JsonProperty("officeId")
    private Long officeId;

    @JsonProperty("clientId")
    private Long clientId;

    @JsonProperty("savingsId")
    private Long savingsId;

    @JsonProperty("resourceId")
    private Long resourceId;

}
