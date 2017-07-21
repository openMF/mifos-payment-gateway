/**
 * PaymentData.java
 * ================================
 * Representation class of Data type in the
 * notification sent to the beyonic's callback
 * @author vladimir fomene
 */

package org.mifos.mifospaymentbridge.PaymentProviders.Beyonic;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.joda.time.DateTime;
import lombok.Data;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentData {

    private Long id;

    private Long organization;

    private Double amount;

    private String currency;

    private String payment_type;

    private Metadata metadata;

    private String description;

    private String[] phone_nos;

    private BeyonicPaymentState state;

    private String last_error;

    private String rejected_reason;

    private Long rejected_by;

    private DateTime rejected_time;

    private String cancelled_reason;

    private Long cancelled_by;

    private DateTime cancelled_time;

    private DateTime created;

    private Long author;

    private DateTime modified;

    private String updated_by;

    private DateTime start_date;
}
