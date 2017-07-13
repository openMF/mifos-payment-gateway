/**
 * File: MobileMoneyProviderMicrofinance.java
 * =========================================
 * This class models the mmp_mfi
 * table in our database.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.model;

import org.joda.time.DateTime;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name="mmp_mfi")
public class MobileMoneyProviderMicrofinance{

    /**
     * table Fields of the InboundRequest table
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="mmp_mfi_id")
    private Long id;

    @Column(name="mfi_phone_number")
    private String mfiPhoneNumber;

    @Column(name="mfi_id")
    private Long mfiId;

    @Column(name="mmp_id")
    private Long mmpId;

    /**
     * Get id of the mmp_mfi instance
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the id of the mmp_mfi instance
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get mfi phone number
     * @return mfiPhoneNumber
     */
    public String getMfiPhoneNumber() {
        return mfiPhoneNumber;
    }

    /**
     * Set mfi phone number
     * @param mfiPhoneNumber
     */
    public void setMfiPhoneNumber(String mfiPhoneNumber) {
        this.mfiPhoneNumber = mfiPhoneNumber;
    }

    /**
     * Get mfi id
     * @return mfiId
     */
    public Long getMfiId() {
        return mfiId;
    }

    /**
     * Set mfi id
     * @param mfiId
     */
    public void setMfiId(Long mfiId) {
        this.mfiId = mfiId;
    }

    /**
     * Get the mmp_id
     * @return mmpId
     */
    public Long getMmpId() {
        return mmpId;
    }

    /**
     * Set the mmp_id
     * @param mmpId
     */
    public void setMmpId(Long mmpId) {
        this.mmpId = mmpId;
    }
}