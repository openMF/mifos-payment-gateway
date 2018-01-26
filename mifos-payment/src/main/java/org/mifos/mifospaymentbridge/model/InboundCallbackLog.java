/**
 * File: InboundCallbackLog.java
 * =========================================
 * This class models the mifos payment inbound_callback_log
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
import java.sql.Timestamp;

@Entity
@Table(name="inbound_callback_log")
public class InboundCallbackLog{

    /**
     * table Fields of inbound_callback_table table
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="status_id")
    private Long id;

    @Column(name="inbound_request_id")
    private Long inboundRequestId;

    @Column(name="callback_url")
    private String callbackUrl;

    @Column(name="callback_status_id")
    private Integer callbackStatusId;

    @Column(name="callback_message")
    private String callbackMessage;

    @Column(name="callback_dtm")
    private Timestamp callbackDtm;

    /**
     * Gets the id of the callback log
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of a callback log
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the inbound request id associated with this log
     * @return inboundRequestId
     */
    public Long getInboundRequestId() {
        return inboundRequestId;
    }

    /**
     * Sets the inbound request id associated to this log
     * @param inboundRequestId
     */
    public void setInboundRequestId(Long inboundRequestId) {
        this.inboundRequestId = inboundRequestId;
    }

    /**
     * Gets the callback url associated to this log
     * @return callbackUrl
     */
    public String getCallbackUrl() {
        return callbackUrl;
    }

    /**
     * Sets the callbackUrl associated to this log
     * @param callbackUrl
     */
    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    /**
     * Get the callback status id of the request
     * @return
     */
    public Integer getCallbackStatusId() {
        return callbackStatusId;
    }

    /**
     * Sets the callback status id of the request
     * @param callbackStatusId
     */
    public void setCallbackStatusId(Integer callbackStatusId) {
        this.callbackStatusId = callbackStatusId;
    }

    /**
     * Gets the callback message of the request
     * @return callbackMessage
     */
    public String getCallbackMessage() {
        return callbackMessage;
    }

    /**
     * sets the callback message associated to this log
     * @param callbackMessage
     */
    public void setCallbackMessage(String callbackMessage) {
        this.callbackMessage = callbackMessage;
    }

    /**
     * Gets the callback date of this log
     * @return callbackDtm
     */
    public Timestamp getCallbackDtm() {
        return callbackDtm;
    }

    /**
     * Sets the callback date of this log
     * @param callbackDtm
     */
    public void setCallbackDtm(Timestamp callbackDtm) {
        this.callbackDtm = callbackDtm;
    }
}