/**
 * File: OutboundRequest.java
 * =========================================
 * This class models the mifos payment gateway outbound-request
 * table in our database. It serves as an object to which data from the
 * fineract api is bind on every outbound transaction
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.mifos.mifospaymentbridge.Util.TransactionType;
import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name="outbound_request")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OutboundRequest{

    /**
     * table Fields of the OutboundRequest table
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="outbound_request_id")
    private Long id;

    @Column(name="transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactType;

    @Column(name="payment_method")
    private String paymentMethod;

    @Column(name="payment_method_type")
    private String paymentMethodType;

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
    private double amount;

    @Column(name="transaction_reason")
    private String transactionReason;


    @Column(name="external_system_id")
    private String externalSystId;

    @Column(name="comments")
    private String comments;

    @Column(name="request_dtm")
    private Timestamp requestedDtm;

    @Column(name="request_ip_address")
    private String requestIpAddress;

    @Column(name="outbound_status_id")
    private Integer outboundStatusId;

    @Column(name="outbound_status_dtm")
    private Timestamp outboundStatusDtm;

    @Column(name="reverse_status_id")
    private Integer reverseStatusId;

    @Column(name="reverse_status_id_dtm")
    private Timestamp reverseStatusIdDtm;


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
    public void setTransactType(TransactionType transactType) {
        this.transactType = transactType;
    }

    /**
     * Gets the payment method of this request
     * @return paymentMethod
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Sets the paymentMethod of the request
     * @param paymentMethod
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Gets the payment method type(wave, beyonic)
     * @return paymentMethodType
     */
    public String getPaymentMethodType() {
        return paymentMethodType;
    }

    /**
     * Set the paymentMethodType for a request
     * @param paymentMethodType
     */
    public void setPaymentMethodType(String paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
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
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the request
     * @param amount
     */
    public void setAmount(double amount) {
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
     * Get the outbound status id of a transaction
     * @return outboundStatusId
     */
    public Integer getOutboundStatusId() {
        return outboundStatusId;
    }

    /**
     * set the outboundStatusId of a transaction
     * @param outboundStatusId
     */
    public void setOutboundStatusId(Integer outboundStatusId) {
        this.outboundStatusId = outboundStatusId;
    }

    /**
     * Get the outboundStatus Date and time
     * @return outboundStatusDtm
     */
    public Timestamp getOutboundStatusDtm() {
        return outboundStatusDtm;
    }

    /**
     * Set the inboundStatus date and time
     * @param outboundStatusDtm
     */
    public void setOutboundStatusDtm(Timestamp outboundStatusDtm) {
        this.outboundStatusDtm = outboundStatusDtm;
    }

    /**
     * Get reverse status id of a request
     * @return reverseStatusId
     */
    public Integer getReverseStatusId() {
        return reverseStatusId;
    }

    /**
     * Set reverse status id of a request
     * @param reverseStatusId
     */
    public void setReverseStatusId(Integer reverseStatusId) {
        this.reverseStatusId = reverseStatusId;
    }

    /**
     * Get reverse status id's date and time
     * @return reverseStatusIdDtm
     */
    public Timestamp getReverseStatusIdDtm() {
        return reverseStatusIdDtm;
    }

    /**
     * Set reverse status id's date and time
     * @param reverseStatusIdDtm
     */
    public void setReverseStatusIdDtm(Timestamp reverseStatusIdDtm) {
        this.reverseStatusIdDtm = reverseStatusIdDtm;
    }

    @Override
    public String toString() {
        return "OutboundRequest{" +
                "id=" + id +
                ", transactType=" + transactType +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentMethodType='" + paymentMethodType + '\'' +
                ", mmpId=" + mmpId +
                ", mfiId=" + mfiId +
                ", sourceRef='" + sourceRef + '\'' +
                ", destinationRef='" + destinationRef + '\'' +
                ", fineractAccNo='" + fineractAccNo + '\'' +
                ", fineractClientId=" + fineractClientId +
                ", amount=" + amount +
                ", transactionReason='" + transactionReason + '\'' +
                ", externalSystId='" + externalSystId + '\'' +
                ", comments='" + comments + '\'' +
                ", requestedDtm='" + requestedDtm + '\'' +
                ", requestIpAddress='" + requestIpAddress + '\'' +
                ", outboundStatusId=" + outboundStatusId +
                ", outboundStatusDtm='" + outboundStatusDtm + '\'' +
                ", reverseStatusId=" + reverseStatusId +
                ", reverseStatusIdDtm='" + reverseStatusIdDtm + '\'' +
                '}';
    }
}