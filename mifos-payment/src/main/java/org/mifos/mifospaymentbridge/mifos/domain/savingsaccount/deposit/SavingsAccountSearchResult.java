package org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit;


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
public class SavingsAccountSearchResult {

    @JsonProperty("totalFilteredRecords")
    public Integer totalFilteredRecords;
    @JsonProperty("pageItems")
    public List<SavingsAccount> pageItems = null;
}
