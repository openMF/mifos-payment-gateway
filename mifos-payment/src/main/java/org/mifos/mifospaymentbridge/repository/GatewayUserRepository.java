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

import java.util.List;

public interface GatewayUserRepository extends CrudRepository<GatewayUser, Long>{
    GatewayUser findOne(Long id);

    List<GatewayUser> findAll();

    GatewayUser save(GatewayUser user);

    List<GatewayUser> save(List<GatewayUser> users);

    boolean exists(Long id);

    void deleteById(Long id);

    List<GatewayUser> findByUsernameIgnoreCase(String userName);
}