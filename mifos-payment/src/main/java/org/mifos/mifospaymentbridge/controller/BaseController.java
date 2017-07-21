/**
 *  File: BaseController.java
 *  ====================================
 *  This class implements all the general functionalities
 *  needed by all controllers
 *  @author Vladimir Fomene
 */

package org.mifos.mifospaymentbridge.controller;


import org.mifos.mifospaymentbridge.Constant.GatewayConstants;
import org.mifos.mifospaymentbridge.Util.HostConfig;
import org.mifos.mifospaymentbridge.model.MobileMoneyProvider;
import org.mifos.mifospaymentbridge.model.OutboundRequest;
import org.mifos.mifospaymentbridge.model.Status;
import org.mifos.mifospaymentbridge.services.MobileMoneyProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import retrofit2.Response;
import java.io.IOException;

public abstract class BaseController {

    public static final String FINERACT_API_PATH = "/api/v1";

    public static final String LOAN_URL = FINERACT_API_PATH + "/loans/{loanId}";

    public static final String WITHDRAWAL_URL = FINERACT_API_PATH + "/savingsaccounts/{accountsId}/transactions";


}
