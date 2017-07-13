package org.mifos.mifospaymentbridge.PaymentProviders.Beyonic;

import org.mifos.mifospaymentbridge.services.ConfigurationService;

public class BeyonicProperties {
    //Create service to interact with our config database
    private ConfigurationService providerConfig = new ConfigurationService();

    //Set Beyonic endpoint and api token from database configuration table values
    private final String END_POINT = providerConfig.findOne(1L).getConfigValue();
    private final String API_TOKEN = providerConfig.findOne(2L).getConfigValue();

    /**
     * return beyonic api endpoint
     * @return END_POINT
     */
    public String getEND_POINT() {
        return END_POINT;
    }

    /**
     * return beyonic api token
     * @return API_TOKEN
     */
    public String getAPI_TOKEN() {
        return API_TOKEN;
    }
}
