/**
 * MifosProperties.java
 * ========================================
 * This class holds all configuration for fineract
 * backend api.
 * @author vladimirfomene
 */

package org.mifos.mifospaymentbridge.mifos;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class MifosProperties {

    @Value("${mifos.baseUrl}")
    private String baseUrl;

    @Value("${mifos.apiVersion}")
    private String apiVersion;

    @Value("${mifos.username}")
    private String username;

    @Value("${mifos.password}")
    private String password;
}
