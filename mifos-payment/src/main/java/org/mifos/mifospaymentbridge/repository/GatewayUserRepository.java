/**
 * File: GatewayUserRepository.java
 * =========================================
 * This interface will create a jpa repository that will
 * help us crud our GatewayUser database table.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.repository;

import org.springframework.data.repository.CrudRepository;
import org.mifos.mifospaymentbridge.model.GatewayUser;

public interface GatewayUserRepository extends CrudRepository<GatewayUser, Long>{

}