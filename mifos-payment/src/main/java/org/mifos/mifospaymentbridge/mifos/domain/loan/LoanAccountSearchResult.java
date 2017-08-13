package org.mifos.mifospaymentbridge.mifos.domain.loan;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "totalFilteredRecords",
        "pageItems"
})
@Data
public class LoanAccountSearchResult {

    @JsonProperty("totalFilteredRecords")
    public Integer totalFilteredRecords;
    @JsonProperty("pageItems")
    public List<Loan> pageItems = null;
}

