package org.mifos.mifospaymentbridge.Util;

/**
 * Created by vladimirfomene on 8/4/17.
 */
public class TransactionStatus {

    public static final String MMP_TRANSACTION_SUCCESS = "Funds is at destination";

    public static final Integer MMP_TRANSACTION_SUCCESS_CODE = 2200;

    public static final String MMP_TRANSACTION_FAILURE = "Funds did not get to client";

    public static final Integer MMP_TRANSACTION_FAILURE_CODE = 4100;

    public static final String REVERSE_DISBURSEMENT_SUCCESS = "Funds are back in mifos";

    public static final Integer REVERSE_DISBURSEMENT_SUCCESS_CODE = 2400;

    public static final String REVERSE_DISBURSEMENT_FAILURE = "Fund are not back in mifos";

    public static final Integer REVERSE_DISBURSEMENT_FAILURE_CODE = 4400;

    public static final String REVERSE_DISBURSEMENT_NO_ACTION = "Funds already reversed.";

    public static final String NO_ACTION = "MMP transaction was successfull";

    public static final Integer NO_ACTION_CODE = 0;

    public static final String RECURRING_DEPOSIT_SUCCESS = "Recurring deposit was successful.";

    public static final Integer RECURRING_DEPOSIT_SUCCESS_CODE = 2300;

    public static final String RECURRING_DEPOSIT_FAILURE = "Recurring deposit failed";

    public static final Integer RECURRING_DEPOSIT_FAILURE_CODE = 4500;

    public static final String REQUEST_RECEPTION_SUCCESS = "Request was received";

    public static final Integer REQUEST_RECEPTION_SUCCESS_CODE = 2100;

    public static final String MMP_LOOKUP_FAILED = "mmp lookup failed";

    public static final Integer MMP_LOOKUP_FAILED_CODE = 4000;

    public static final String ACCOUNT_LOOKUP_SUCCESS = "Account lookup Success";

    public static final Integer ACCOUNT_LOOKUP_SUCCESS_CODE = 2000;

    public static final String ACCOUNT_LOOKUP_FAILED = "Account lookup failed.";

    public static final Integer ACCOUNT_LOOKUP_FAILED_CODE = 4200;

    public static final String REPAYMENT_SUCCESS = "Loan repayment Successful";

    public static final Integer REPAYMENT_SUCCESS_CODE = 2500;

    public static final String REPAYMENT_FAILURE = "Loan repayment Failed.";

    public static final Integer REPAYMENT_FAILURE_CODE = 4300;

    public static final String VOLUNTARY_DEPOSIT_SUCCESS = "Deposit successful";

    public static final Integer VOLUNTARY_DEPOSIT_SUCCESS_CODE = 2600;

    public static final String VOLUNTARY_DEPOSIT_FAILURE = "Deposit failed.";

    public static final Integer VOLUNTARY_DEPOSIT_FAILURE_CODE = 4600;
}
