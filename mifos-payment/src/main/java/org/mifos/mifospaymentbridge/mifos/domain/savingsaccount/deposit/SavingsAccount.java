package org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "accountNo",
        "depositType",
        "clientId",
        "clientName",
        "savingsProductId",
        "savingsProductName",
        "fieldOfficerId",
        "status",
        "subStatus",
        "timeline",
        "currency",
        "nominalAnnualInterestRate",
        "interestCompoundingPeriodType",
        "interestPostingPeriodType",
        "interestCalculationType",
        "interestCalculationDaysInYearType",
        "minRequiredOpeningBalance",
        "lockinPeriodFrequency",
        "lockinPeriodFrequencyType",
        "withdrawalFeeForTransfers",
        "allowOverdraft",
        "enforceMinRequiredBalance",
        "withHoldTax",
        "lastActiveTransactionDate",
        "isDormancyTrackingActive",
        "summary"
})
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SavingsAccount {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("accountNo")
    public String accountNo;
    @JsonProperty("depositType")
    public DepositType depositType;
    @JsonProperty("clientId")
    public Integer clientId;
    @JsonProperty("clientName")
    public String clientName;
    @JsonProperty("savingsProductId")
    public Integer savingsProductId;
    @JsonProperty("savingsProductName")
    public String savingsProductName;
    @JsonProperty("fieldOfficerId")
    public Integer fieldOfficerId;
    @JsonProperty("status")
    public Status status;
    @JsonProperty("subStatus")
    public SubStatus subStatus;
    @JsonProperty("timeline")
    public Timeline timeline;
    @JsonProperty("currency")
    public Currency currency;
    @JsonProperty("nominalAnnualInterestRate")
    public Double nominalAnnualInterestRate;
    @JsonProperty("interestCompoundingPeriodType")
    public InterestCompoundingPeriodType interestCompoundingPeriodType;
    @JsonProperty("interestPostingPeriodType")
    public InterestPostingPeriodType interestPostingPeriodType;
    @JsonProperty("interestCalculationType")
    public InterestCalculationType interestCalculationType;
    @JsonProperty("interestCalculationDaysInYearType")
    public InterestCalculationDaysInYearType interestCalculationDaysInYearType;
    @JsonProperty("minRequiredOpeningBalance")
    public Double minRequiredOpeningBalance;
    @JsonProperty("lockinPeriodFrequency")
    public Integer lockinPeriodFrequency;
    @JsonProperty("lockinPeriodFrequencyType")
    public LockinPeriodFrequencyType lockinPeriodFrequencyType;
    @JsonProperty("withdrawalFeeForTransfers")
    public Boolean withdrawalFeeForTransfers;
    @JsonProperty("allowOverdraft")
    public Boolean allowOverdraft;
    @JsonProperty("enforceMinRequiredBalance")
    public Boolean enforceMinRequiredBalance;
    @JsonProperty("withHoldTax")
    public Boolean withHoldTax;
    @JsonProperty("lastActiveTransactionDate")
    public List<Integer> lastActiveTransactionDate = null;
    @JsonProperty("isDormancyTrackingActive")
    public Boolean isDormancyTrackingActive;
    @JsonProperty("summary")
    public Summary summary;

}
