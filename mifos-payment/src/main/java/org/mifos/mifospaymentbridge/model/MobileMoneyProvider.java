/**
 * File: MobileMoneyProvider.java
 * =========================================
 * This class models the MMP
 * table in our database.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.model;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Table(name = "mmp")
public class MobileMoneyProvider{


    /**
     * table Fields of our gateway_users table
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="mmp_id")
    private Long id;

    @Column(name="provider_name")
    private String name;

    @Column(name="currency_code")
    private String currencyCode;

    @Column(name="last_modified_dtm")
    private Timestamp lastModified;

    @Column(name="last_modified_by_id")
    private Long lastModifiedById;

    /**
     * Gets the id of the mobile money provider
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the mobile money provider
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the mobile money provider
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the mobile money provider
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the currency_code of this provider
     * @return currencyCode
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the currency code of this mobile money provider
     * @param currencyCode
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * Gets the last modified datetime of this mobile money provider
     * @return
     */
    public Timestamp getLastModified() {
        return lastModified;
    }

    /**
     * Sets the last modified datetime of this mobile money provider
     * @param lastModified
     */
    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * Get the last_modified_by_id of this mobile money provider
     * @return
     */
    public Long getLastModifiedById() {
        return lastModifiedById;
    }

    /**
     * Sets the last_modified_by_id of this mobile money provider
     * @param lastModifiedById
     */
    public void setLastModifiedById(Long lastModifiedById) {
        this.lastModifiedById = lastModifiedById;
    }

    @Override
    public String toString() {
        return "MobileMoneyProvider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", lastModified=" + lastModified +
                ", lastModifiedById=" + lastModifiedById +
                '}';
    }
}