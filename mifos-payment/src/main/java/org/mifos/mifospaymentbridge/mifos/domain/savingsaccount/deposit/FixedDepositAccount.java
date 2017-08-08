package org.mifos.mifospaymentbridge.mifos.domain.savingsaccount.deposit;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "accountNo",
        "externalId",
        "clientId",
        "clientName",
        "savingsProductId",
        "savingsProductName",
        "fieldOfficerId",
        "status",
        "timeline",
        "currency",
        "interestCompoundingPeriodType",
        "interestPostingPeriodType",
        "interestCalculationType",
        "interestCalculationDaysInYearType",
        "interestFreePeriodApplicable",
        "preClosurePenalApplicable",
        "minDepositTerm",
        "maxDepositTerm",
        "minDepositTermType",
        "maxDepositTermType",
        "depositAmount",
        "maturityAmount",
        "maturityDate",
        "depositPeriod",
        "depositPeriodFrequency",
        "summary",
        "accountChart"
})
@Data
public class FixedDepositAccount {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("accountNo")
    public String accountNo;
    @JsonProperty("externalId")
    public String externalId;
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
    @JsonProperty("timeline")
    public Timeline timeline;
    @JsonProperty("currency")
    public Currency currency;
    @JsonProperty("interestCompoundingPeriodType")
    public InterestCompoundingPeriodType interestCompoundingPeriodType;
    @JsonProperty("interestPostingPeriodType")
    public InterestPostingPeriodType interestPostingPeriodType;
    @JsonProperty("interestCalculationType")
    public InterestCalculationType interestCalculationType;
    @JsonProperty("interestCalculationDaysInYearType")
    public InterestCalculationDaysInYearType interestCalculationDaysInYearType;
    @JsonProperty("interestFreePeriodApplicable")
    public Boolean interestFreePeriodApplicable;
    @JsonProperty("preClosurePenalApplicable")
    public Boolean preClosurePenalApplicable;
    @JsonProperty("minDepositTerm")
    public Integer minDepositTerm;
    @JsonProperty("maxDepositTerm")
    public Integer maxDepositTerm;
    @JsonProperty("minDepositTermType")
    public MinDepositTermType minDepositTermType;
    @JsonProperty("maxDepositTermType")
    public MaxDepositTermType maxDepositTermType;
    @JsonProperty("depositAmount")
    public Integer depositAmount;
    @JsonProperty("maturityAmount")
    public Double maturityAmount;
    @JsonProperty("maturityDate")
    public List<Integer> maturityDate = null;
    @JsonProperty("depositPeriod")
    public Integer depositPeriod;
    @JsonProperty("depositPeriodFrequency")
    public DepositPeriodFrequency depositPeriodFrequency;
    @JsonProperty("summary")
    public Summary summary;
    @JsonProperty("accountChart")
    public AccountChart accountChart;

}
