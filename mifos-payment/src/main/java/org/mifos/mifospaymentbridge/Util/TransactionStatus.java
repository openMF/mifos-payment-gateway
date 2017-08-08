package org.mifos.mifospaymentbridge.Util;

/**
 * Created by vladimirfomene on 8/4/17.
 */
public class TransactionStatus {
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
}
