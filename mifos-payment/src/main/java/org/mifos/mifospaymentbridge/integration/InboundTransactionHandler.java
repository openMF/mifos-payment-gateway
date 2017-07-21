package org.mifos.mifospaymentbridge.integration;

import org.mifos.mifospaymentbridge.model.InboundCallbackLog;
import org.mifos.mifospaymentbridge.model.InboundRequest;
import org.mifos.mifospaymentbridge.model.Status;
import org.mifos.mifospaymentbridge.services.ConfigurationService;
import org.mifos.mifospaymentbridge.services.InboundCallbackLogService;
import org.mifos.mifospaymentbridge.services.InboundRequestService;
import org.mifos.mifospaymentbridge.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by vladimirfomene on 8/4/17.
 */
public class InboundTransactionHandler {

    private InboundRequest inboundRequest;

    private InboundCallbackLog callbackLog;

    private Status inboundStatus;

    @Autowired
    private InboundRequestService inboundRequestService;

    @Autowired
    private InboundCallbackLogService inboundCallbackLogService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private ConfigurationService configurationService;

    public void handleTransaction(){
        
    }
}
