package org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SavingsAccountTransaction {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("transactionType")
    private TransactionType transactionType;

    @JsonProperty("accountId")
    private Long accountId;

    @JsonProperty("accountNo")
    private String accountNo;

    @JsonProperty("date")
    private List<Long> date = new ArrayList<Long>();

    @JsonProperty("currency")
    private Currency currency;

    @JsonProperty("amount")
    private Long amount;

    @JsonProperty("runningBalance")
    private Long runningBalance;

    @JsonProperty("reversed")
    private Boolean reversed;

    @JsonProperty("submittedOnDate")
    private List<Long> submittedOnDate = new ArrayList<Long>();

    @JsonProperty("interestedPostedAsOn")
    private Boolean interestedPostedAsOn;
}
