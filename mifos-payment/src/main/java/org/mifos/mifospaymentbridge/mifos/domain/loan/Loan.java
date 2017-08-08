package org.mifos.mifospaymentbridge.mifos.domain.loan;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "accountNo",
        "status",
        "clientId",
        "clientAccountNo",
        "clientName",
        "clientOfficeId",
        "loanProductId",
        "loanProductName",
        "isLoanProductLinkedToFloatingRate",
        "fundId",
        "fundName",
        "loanType",
        "currency",
        "principal",
        "approvedPrincipal",
        "proposedPrincipal",
        "termFrequency",
        "termPeriodFrequencyType",
        "numberOfRepayments",
        "repaymentEvery",
        "repaymentFrequencyType",
        "interestRatePerPeriod",
        "interestRateFrequencyType",
        "annualInterestRate",
        "isFloatingInterestRate",
        "interestRateDifferential",
        "amortizationType",
        "interestType",
        "interestCalculationPeriodType",
        "allowPartialPeriodInterestCalcualtion",
        "transactionProcessingStrategyId",
        "transactionProcessingStrategyName",
        "syncDisbursementWithMeeting",
        "timeline",
        "summary",
        "feeChargesAtDisbursementCharged",
        "loanProductCounter",
        "multiDisburseLoan",
        "canDefineInstallmentAmount",
        "canDisburse",
        "canUseForTopup",
        "isTopup",
        "closureLoanId",
        "inArrears",
        "isNPA",
        "overdueCharges",
        "daysInMonthType",
        "daysInYearType",
        "isInterestRecalculationEnabled",
        "createStandingInstructionAtDisbursement",
        "paidInAdvance",
        "isVariableInstallmentsAllowed",
        "minimumGap",
        "maximumGap"
})
@Data
public class Loan {

    @JsonProperty("id")
    public Long id;
    @JsonProperty("accountNo")
    public String accountNo;
    @JsonProperty("status")
    public Status status;
    @JsonProperty("clientId")
    public Integer clientId;
    @JsonProperty("clientAccountNo")
    public String clientAccountNo;
    @JsonProperty("clientName")
    public String clientName;
    @JsonProperty("clientOfficeId")
    public Integer clientOfficeId;
    @JsonProperty("loanProductId")
    public Integer loanProductId;
    @JsonProperty("loanProductName")
    public String loanProductName;
    @JsonProperty("isLoanProductLinkedToFloatingRate")
    public Boolean isLoanProductLinkedToFloatingRate;
    @JsonProperty("fundId")
    public Integer fundId;
    @JsonProperty("fundName")
    public String fundName;
    @JsonProperty("loanType")
    public LoanType loanType;
    @JsonProperty("currency")
    public Currency currency;
    @JsonProperty("principal")
    public Double principal;
    @JsonProperty("approvedPrincipal")
    public Double approvedPrincipal;
    @JsonProperty("proposedPrincipal")
    public Double proposedPrincipal;
    @JsonProperty("termFrequency")
    public Integer termFrequency;
    @JsonProperty("termPeriodFrequencyType")
    public TermPeriodFrequencyType termPeriodFrequencyType;
    @JsonProperty("numberOfRepayments")
    public Integer numberOfRepayments;
    @JsonProperty("repaymentEvery")
    public Integer repaymentEvery;
    @JsonProperty("repaymentFrequencyType")
    public RepaymentFrequencyType repaymentFrequencyType;
    @JsonProperty("interestRatePerPeriod")
    public Double interestRatePerPeriod;
    @JsonProperty("interestRateFrequencyType")
    public InterestRateFrequencyType interestRateFrequencyType;
    @JsonProperty("annualInterestRate")
    public Double annualInterestRate;
    @JsonProperty("isFloatingInterestRate")
    public Boolean isFloatingInterestRate;
    @JsonProperty("interestRateDifferential")
    public Double interestRateDifferential;
    @JsonProperty("amortizationType")
    public AmortizationType amortizationType;
    @JsonProperty("interestType")
    public InterestType interestType;
    @JsonProperty("interestCalculationPeriodType")
    public InterestCalculationPeriodType interestCalculationPeriodType;
    @JsonProperty("allowPartialPeriodInterestCalcualtion")
    public Boolean allowPartialPeriodInterestCalcualtion;
    @JsonProperty("transactionProcessingStrategyId")
    public Integer transactionProcessingStrategyId;
    @JsonProperty("transactionProcessingStrategyName")
    public String transactionProcessingStrategyName;
    @JsonProperty("syncDisbursementWithMeeting")
    public Boolean syncDisbursementWithMeeting;
    @JsonProperty("timeline")
    public Timeline timeline;
    @JsonProperty("summary")
    public Summary summary;
    @JsonProperty("feeChargesAtDisbursementCharged")
    public Double feeChargesAtDisbursementCharged;
    @JsonProperty("loanProductCounter")
    public Integer loanProductCounter;
    @JsonProperty("multiDisburseLoan")
    public Boolean multiDisburseLoan;
    @JsonProperty("canDefineInstallmentAmount")
    public Boolean canDefineInstallmentAmount;
    @JsonProperty("canDisburse")
    public Boolean canDisburse;
    @JsonProperty("canUseForTopup")
    public Boolean canUseForTopup;
    @JsonProperty("isTopup")
    public Boolean isTopup;
    @JsonProperty("closureLoanId")
    public Integer closureLoanId;
    @JsonProperty("inArrears")
    public Boolean inArrears;
    @JsonProperty("isNPA")
    public Boolean isNPA;
    @JsonProperty("overdueCharges")
    public List<Object> overdueCharges = null;
    @JsonProperty("daysInMonthType")
    public DaysInMonthType daysInMonthType;
    @JsonProperty("daysInYearType")
    public DaysInYearType daysInYearType;
    @JsonProperty("isInterestRecalculationEnabled")
    public Boolean isInterestRecalculationEnabled;
    @JsonProperty("createStandingInstructionAtDisbursement")
    public Boolean createStandingInstructionAtDisbursement;
    @JsonProperty("paidInAdvance")
    public PaidInAdvance paidInAdvance;
    @JsonProperty("isVariableInstallmentsAllowed")
    public Boolean isVariableInstallmentsAllowed;
    @JsonProperty("minimumGap")
    public Integer minimumGap;
    @JsonProperty("maximumGap")
    public Integer maximumGap;

}