/**
 * File: Configuration.java
 * =========================================
 * This class models the mifos payment Configuration
 * table in our database.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.model;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="configuration")
public class Configuration{

    //creation of reference type
    private enum ReferenceType{
        MMP, MFI
    }


    /**
     * table Fields of the configuration table
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="configuration_id")
    private Long id;

    @Column(name="reference_id")
    private Long referenceId;

    @Column(name="reference_type")
    @Enumerated(EnumType.STRING)
    private ReferenceType refType;

    @Column(name="config_name")
    private String configName;

    @Column(name="config_value")
    private String configValue;

    @Column(name="config_category")
    private Integer configCategory;

    @Column(name="last_modified_dtm")
    private Timestamp lastModifiedDtm;

    @Column(name="last_modified_by_id")
    private Long lastModifiedById;

    /**
     * Gets the id of a configuration
     * @return Id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of a configuration
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the reference id of a configuration
     * @return
     */
    public Long getReferenceId() {
        return referenceId;
    }

    /**
     * Sets the reference id of a configuration
     * @param referenceId
     */
    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    /**
     * Gets the referenceType of the configuration
     * @return refType
     */
    public ReferenceType getRefType() {
        return refType;
    }

    /**
     * Sets the referenceType of a configuration
     * @param refType
     */
    public void setRefType(ReferenceType refType) {
        this.refType = refType;
    }

    /**
     * Gets the configuration name
     * @return configName
     */
    public String getConfigName() {
        return configName;
    }

    /**
     * Sets the config_name field of a configuration
     * @param configName
     */
    public void setConfigName(String configName) {
        this.configName = configName;
    }

    /**
     * Gets the config value of a configuration
     * @return configValue
     */
    public String getConfigValue() {
        return configValue;
    }

    /**
     * Sets the config value of a configuration
     * @param configValue
     */
    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    /**
     * Gets the configuration category
     * @return configCategory
     */
    public Integer getConfigCategory() {
        return configCategory;
    }

    /**
     * Sets the configurations category of a configuration
     * @param configCategory
     */
    public void setConfigCategory(Integer configCategory) {
        this.configCategory = configCategory;
    }

    /**
     * Gets the last Modified date
     * @return lastModifiedDtm
     */
    public Timestamp getLastModifiedDtm() {
        return lastModifiedDtm;
    }

    /**
     * Set the last modified date
     * @param lastModifiedDtm
     */
    public void setLastModifiedDtm(Timestamp lastModifiedDtm) {
        this.lastModifiedDtm = lastModifiedDtm;
    }

    /**
     * Gets the id that last modified the configuration
     * @return lastModifiedById
     */
    public Long getLastModifiedById() {
        return lastModifiedById;
    }

    /**
     * Sets the id that last modified the configuration
     * @param lastModifiedById
     */
    public void setLastModifiedById(Long lastModifiedById) {
        this.lastModifiedById = lastModifiedById;
    }
}