/**
 * File: HostConfig.java
 * =====================================
 * This class has properties for the host's
 * configuration
 * @author vladimir fomene
 */

package org.mifos.mifospaymentbridge.Util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HostConfig {

    @Value("${hostconfig.host-address}")
    private String hostName ;

    @Value("${hostconfig.protocol}")
    private String protocol ;

    @Value("${server.port}")
    private Integer port ;

    public String getHostName() {
        return this.hostName ;
    }

    public String getProtocol() {
        return this.protocol ;
    }

    public Integer getPort() {
        return this.port ;
    }
}
