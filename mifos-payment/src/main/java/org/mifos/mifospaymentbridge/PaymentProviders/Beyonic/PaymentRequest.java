/**
 * File: PaymentRequest.java
 * ==================================
 * This is a representation of the payment request
 * send to the beyonic api
 */

package org.mifos.mifospaymentbridge.PaymentProviders.Beyonic;

import lombok.Data;

@Data
public class PaymentRequest {

    private String phonenumber;

    private String currency;

    private String payment_type;

    private Double amount;

    private String description;

    private String callback_url;
}
