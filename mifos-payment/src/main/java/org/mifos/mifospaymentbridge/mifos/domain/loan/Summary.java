package org.mifos.mifospaymentbridge.mifos.domain.loan;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "currency",
        "principalDisbursed",
        "principalPaid",
        "principalWrittenOff",
        "principalOutstanding",
        "principalOverdue",
        "interestCharged",
        "interestPaid",
        "interestWaived",
        "interestWrittenOff",
        "interestOutstanding",
        "interestOverdue",
        "feeChargesCharged",
        "feeChargesDueAtDisbursementCharged",
        "feeChargesPaid",
        "feeChargesWaived",
        "feeChargesWrittenOff",
        "feeChargesOutstanding",
        "feeChargesOverdue",
        "penaltyChargesCharged",
        "penaltyChargesPaid",
        "penaltyChargesWaived",
        "penaltyChargesWrittenOff",
        "penaltyChargesOutstanding",
        "penaltyChargesOverdue",
        "totalExpectedRepayment",
        "totalRepayment",
        "totalExpectedCostOfLoan",
        "totalCostOfLoan",
        "totalWaived",
        "totalWrittenOff",
        "totalOutstanding",
        "totalOverdue"
})
public class Summary {

    @JsonProperty("currency")
    public Currency_ currency;
    @JsonProperty("principalDisbursed")
    public Double principalDisbursed;
    @JsonProperty("principalPaid")
    public Double principalPaid;
    @JsonProperty("principalWrittenOff")
    public Double principalWrittenOff;
    @JsonProperty("principalOutstanding")
    public Double principalOutstanding;
    @JsonProperty("principalOverdue")
    public Integer principalOverdue;
    @JsonProperty("interestCharged")
    public Double interestCharged;
    @JsonProperty("interestPaid")
    public Double interestPaid;
    @JsonProperty("interestWaived")
    public Double interestWaived;
    @JsonProperty("interestWrittenOff")
    public Double interestWrittenOff;
    @JsonProperty("interestOutstanding")
    public Double interestOutstanding;
    @JsonProperty("interestOverdue")
    public Integer interestOverdue;
    @JsonProperty("feeChargesCharged")
    public Double feeChargesCharged;
    @JsonProperty("feeChargesDueAtDisbursementCharged")
    public Double feeChargesDueAtDisbursementCharged;
    @JsonProperty("feeChargesPaid")
    public Double feeChargesPaid;
    @JsonProperty("feeChargesWaived")
    public Double feeChargesWaived;
    @JsonProperty("feeChargesWrittenOff")
    public Double feeChargesWrittenOff;
    @JsonProperty("feeChargesOutstanding")
    public Double feeChargesOutstanding;
    @JsonProperty("feeChargesOverdue")
    public Integer feeChargesOverdue;
    @JsonProperty("penaltyChargesCharged")
    public Double penaltyChargesCharged;
    @JsonProperty("penaltyChargesPaid")
    public Double penaltyChargesPaid;
    @JsonProperty("penaltyChargesWaived")
    public Double penaltyChargesWaived;
    @JsonProperty("penaltyChargesWrittenOff")
    public Double penaltyChargesWrittenOff;
    @JsonProperty("penaltyChargesOutstanding")
    public Double penaltyChargesOutstanding;
    @JsonProperty("penaltyChargesOverdue")
    public Integer penaltyChargesOverdue;
    @JsonProperty("totalExpectedRepayment")
    public Double totalExpectedRepayment;
    @JsonProperty("totalRepayment")
    public Double totalRepayment;
    @JsonProperty("totalExpectedCostOfLoan")
    public Double totalExpectedCostOfLoan;
    @JsonProperty("totalCostOfLoan")
    public Double totalCostOfLoan;
    @JsonProperty("totalWaived")
    public Double totalWaived;
    @JsonProperty("totalWrittenOff")
    public Double totalWrittenOff;
    @JsonProperty("totalOutstanding")
    public Double totalOutstanding;
    @JsonProperty("totalOverdue")
    public Integer totalOverdue;

}
