/**
 * File: InboundRequest.java
 * =========================================
 * This class models the inbound request
 * table in our database.
 * @author Vladimir Fomene
 **/


package org.mifos.mifospaymentbridge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Table(name="inbound_request")
public class InboundRequest implements Serializable{

    //Creation of TransactionType type
    public enum TransactionType{
        SAVINGS, LOAN_REPAYMENT
    }

    /**
     * table Fields of the InboundRequest table
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="inbound_request")
    private Long id;

    @Column(name="transaction_type")
    private TransactionType transactType;

    @Column(name="mmp_id")
    private Long mmpId;

    @Column(name="mfi_id")
    private Long mfiId;

    @Column(name="source_reference")
    private String sourceRef;

    @Column(name="destination_reference")
    private String destinationRef;

    @Column(name="fineract_account_number")
    private String fineractAccNo;

    @Column(name="fineract_client_id")
    private Long fineractClientId;

    @Column(name="amount")
    private Double amount;

    @Column(name="transaction_reason")
    private String transactionReason;

    @Column(name="external_system_id")
    private String externalSystId;

    @Column(name="comments")
    private String comments;

    @Column(name="requested_dtm")
    private Timestamp requestedDtm;

    @Column(name="request_id_address")
    private String requestIpAddress;

    @Column(name="inbound_status_id")
    private Integer inboundStatusId;

    @Column(name="inbound_status_dtm")
    private Timestamp inboundStatusDtm;

    @Column(name="payment_method")
    private String paymentMethod;

    @Column(name="payment_method_type")
    private String paymentMethodType;

    /**
     * Gets the id of the request
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the request
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the transaction type of the request
     * @return transactType
     */
    public TransactionType getTransactType() {
        return transactType;
    }

    /**
     * Sets the transaction type of the request.
     * @param transactType
     */
    public void setTransactType(org.mifos.mifospaymentbridge.model.InboundRequest.TransactionType transactType) {
        this.transactType = transactType;
    }

    /**
     * Get the mmp_id of the request
     * @return mmpId
     */
    public Long getMmpId() {
        return mmpId;
    }

    /**
     * set the mmp_id of the request
     * @param mmpId
     */
    public void setMmpId(Long mmpId) {
        this.mmpId = mmpId;
    }

    /**
     * Get the mfi_id of the request
     * @return mfiId
     */
    public Long getMfiId() {
        return mfiId;
    }

    /**
     * Set the mfi_id of a request
     * @param mfiId
     */
    public void setMfiId(Long mfiId) {
        this.mfiId = mfiId;
    }

    /**
     * Get the source reference of this request
     * @return sourceRef
     */
    public String getSourceRef() {
        return sourceRef;
    }

    /**
     * Set the source reference of this request
     * @param sourceRef
     */
    public void setSourceRef(String sourceRef) {
        this.sourceRef = sourceRef;
    }

    /**
     * Get the destination reference of the request
     * @return destinationRef
     */
    public String getDestinationRef() {
        return destinationRef;
    }

    /**
     * Set the destination reference of the request
     * @param destinationRef
     */
    public void setDestinationRef(String destinationRef) {
        this.destinationRef = destinationRef;
    }

    /**
     * Get fineract account number for a request
     * @return fineractAccNo
     */
    public String getFineractAccNo() {
        return fineractAccNo;
    }

    /**
     * Set the fineract account number for a request
     * @param fineractAccNo
     */
    public void setFineractAccNo(String fineractAccNo) {
        this.fineractAccNo = fineractAccNo;
    }

    /**
     * Get a fineract client id for a request
     * @return fineractClientId
     */
    public Long getFineractClientId() {
        return fineractClientId;
    }

    /**
     * Sets the fineract client id of the request
     * @param fineractClientId
     */
    public void setFineractClientId(Long fineractClientId) {
        this.fineractClientId = fineractClientId;
    }

    /**
     * Get the amount of the request
     * @return amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the request
     * @param amount
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Get the reason for the transaction
     * @return transactionReason
     */
    public String getTransactionReason() {
        return transactionReason;
    }

    /**
     * Set the reason for a transaction
     * @param transactionReason
     */
    public void setTransactionReason(String transactionReason) {
        this.transactionReason = transactionReason;
    }

    /**
     * Get the external system id of the request
     * @return externalSystId
     */
    public String getExternalSystId() {
        return externalSystId;
    }

    /**
     * Set the external system id of the request
     * @param externalSystId
     */
    public void setExternalSystId(String externalSystId) {
        this.externalSystId = externalSystId;
    }

    /**
     * Get the comments associated to this request
     * @return comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * Set the comments for this particular request
     * @param comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Get requested datetime
     * @return requestedDtm
     */
    public Timestamp getRequestedDtm() {
        return requestedDtm;
    }

    /**
     * Set requestedDtm for a request
     * @param requestedDtm
     */
    public void setRequestedDtm(Timestamp requestedDtm) {
        this.requestedDtm = requestedDtm;
    }

    /**
     * Gets the ip address from which the request is coming from.
     * @return requestIpAddress
     */
    public String getRequestIpAddress() {
        return requestIpAddress;
    }

    /**
     * Set the request api address
     * @param requestIpAddress
     */
    public void setRequestIpAddress(String requestIpAddress) {
        this.requestIpAddress = requestIpAddress;
    }

    /**
     * Get the inbound status id of a transaction
     * @return inboundStatusId
     */
    public Integer getInboundStatusId() {
        return inboundStatusId;
    }

    /**
     * set the inboundStatusId of a transaction
     * @param inboundStatusId
     */
    public void setInboundStatusId(Integer inboundStatusId) {
        this.inboundStatusId = inboundStatusId;
    }

    /**
     * Get the inboundStatus Date and time
     * @return inboundStatusDtm
     */
    public Timestamp getInboundStatusDtm() {
        return inboundStatusDtm;
    }

    /**
     * Set the inboundStatus date and time
     * @param inboundStatusDtm
     */
    public void setInboundStatusDtm(Timestamp inboundStatusDtm) {
        this.inboundStatusDtm = inboundStatusDtm;
    }

    /**
     * Get the payment method for the request
     * @return
     */
    public String getPaymentMethod(){
        return paymentMethod;
    }

    /**
     * Set the payment method of the request
     * @param paymentMethod
     */
    public void setPaymentMethod(String paymentMethod){
        this.paymentMethod = paymentMethod;
    }

    /**
     * Get the payment method type of a request
     * @return
     */
    public String getPaymentMethodType(){
        return paymentMethodType;
    }

    /**
     * Set the paymentmethod type of the request.
     * @param paymentMethodType
     */
    public void setPaymentMethodType(String paymentMethodType){
        this.paymentMethodType = paymentMethodType;
    }
}