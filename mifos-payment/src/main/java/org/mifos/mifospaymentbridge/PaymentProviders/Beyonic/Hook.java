/**
 * Hook.java
 * ============================================
 * Represents the hook object in Beyonic notification
 * json object.
 */

package org.mifos.mifospaymentbridge.PaymentProviders.Beyonic;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.joda.time.DateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Hook {

    private Long id;

    private DateTime created;

    private DateTime updated;

    private String event;

    private String target;

    private Long user;
}
