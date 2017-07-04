/**
 * File: OutboundTransactionLog.java
 * =========================================
 * This class models the mifos payment gateway outbound_transaction_log
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
@Table(name = "outbound_transaction_log")
public class OutboundTransactionLog{

    //Creation of TransactionType type
    public enum TransactionType{
        DISBURSEMENT, WITHDRAWAL, REVERSAL
    }

    /**
     * table Fields of the outbound_transaction_log_id table
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="outbound_transaction_log_id")
    private Long id;

    @Column(name="outbound_request_id")
    private Long outboundRequestId;

    @Column(name="request_ip_address")
    private String requestIpAddress;

    @Column(name="transaction_type")
    private TransactionType transactType;

    @Column(name="transaction_status_id")
    private Integer transactionStatusId;

    @Column(name="transaction_dtm")
    private DateTime transactionDtm;

    /**
     * Get the id of the log
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the log
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the outbound request id
     * @return outboundRequestId
     */
    public Long getOutboundRequestId() {
        return outboundRequestId;
    }

    /**
     * Set the outbound request id
     * @param outboundRequestId
     */
    public void setOutboundRequestId(Long outboundRequestId) {
        this.outboundRequestId = outboundRequestId;
    }

    /**
     * Get request ip address
     * @return requestIpAddress
     */
    public String getRequestIpAddress() {
        return requestIpAddress;
    }

    /**
     * Set request ip address of log
     * @param requestIpAddress
     */
    public void setRequestIpAddress(String requestIpAddress) {
        this.requestIpAddress = requestIpAddress;
    }

    /**
     * Get the transaction type
     * @return transactType
     */
    public TransactionType getTransactType() {
        return transactType;
    }

    /**
     * Set the transaction type
     * @param transactType
     */
    public void setTransactType(TransactionType transactType) {
        this.transactType = transactType;
    }

    /**
     * Get the transaction status id
     * @return transactionStatusId
     */
    public Integer getTransactionStatusId() {
        return transactionStatusId;
    }

    /**
     * Set the transaction status id
     * @param transactionStatusId
     */
    public void setTransactionStatusId(Integer transactionStatusId) {
        this.transactionStatusId = transactionStatusId;
    }

    /**
     * Get transaction datetime
     * @return transactionDtm
     */
    public DateTime getTransactionDtm() {
        return transactionDtm;
    }

    /**
     * Set transaction datetime
     * @param transactionDtm
     */
    public void setTransactionDtm(DateTime transactionDtm) {
        this.transactionDtm = transactionDtm;
    }
}