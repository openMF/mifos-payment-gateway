/**
 * File: GatewayUser.java
 * =========================================
 * This class models the MMP_config
 * table in our database.
 * @author Vladimir Fomene
 **/
package org.mifos.mifospaymentbridge.model;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "MMP_Config")
public class ProviderConfig{


    /**
     * table Fields of mmp_config table
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="mmp_config_id")
    private Long id;

    @Column(name="mmp_id")
    private Long providerId;

    @Column(name="config_name")
    private String configName;

    @Column(name="config_value")
    private String configValue;

    /**
     * Gets the id of a mmp_config
     * @return id
     */
    public Long getId() {
        return id;
    }


    /**
     * Sets the id of a mmp_config
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the provider's id
     * @return providerId
     */
    public Long getProviderId() {
        return providerId;
    }

    /**
     * Sets the provider's id
     * @param providerId
     */
    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    /**
     * Gets the configName of the mobile money provider
     * @return configName
     */
    public String getConfigName() {
        return configName;
    }

    /**
     * Sets the configName of the mobile money provider
     * @param configName
     */
    public void setConfigName(String configName) {
        this.configName = configName;
    }

    /**
     * Gets the config value for a mobile money provider
     * @return configValue
     */
    public String getConfigValue() {
        return configValue;
    }

    /**
     * Sets the config value for a mobile money provider
     * @param configValue
     */
    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
}