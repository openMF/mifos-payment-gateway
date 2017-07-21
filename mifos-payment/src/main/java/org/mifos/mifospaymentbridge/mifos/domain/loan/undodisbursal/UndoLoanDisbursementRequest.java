package org.mifos.mifospaymentbridge.mifos.domain.loan.undodisbursal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UndoLoanDisbursementRequest {

    @JsonProperty("note")
    private String note;
}
