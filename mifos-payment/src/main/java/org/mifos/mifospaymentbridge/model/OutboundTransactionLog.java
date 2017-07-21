/**
 * File: OutboundTransactionLog.java
 * =========================================
 * This class models the mifos payment gateway outbound_transaction_log
 * table in our database.
 * @author Vladimir Fomene
 **/
package org.mifos.mifospaymentbridge.model;

import org.joda.time.DateTime;
import org.mifos.mifospaymentbridge.Util.TransactionType;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "outbound_transaction_log")
public class OutboundTransactionLog{
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
    @Enumerated(EnumType.STRING)
    private TransactionType transactType;

    @Column(name="transaction_status_id")
    private Integer transactionStatusId;

    @Column(name="transaction_dtm")
    private Timestamp transactionDtm;

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
    public Timestamp getTransactionDtm() {
        return transactionDtm;
    }

    /**
     * Set transaction datetime
     * @param transactionDtm
     */
    public void setTransactionDtm(Timestamp transactionDtm) {
        this.transactionDtm = transactionDtm;
    }
}