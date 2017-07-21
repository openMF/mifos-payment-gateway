/**
 * File: GatewayUserService.java
 * =========================================
 *  This class helps us to manipulate our GatewayUser models
 * and persist changes to the database.
 * @author Vladimir Fomene
 **/
package org.mifos.mifospaymentbridge.services;


import org.mifos.mifospaymentbridge.model.GatewayUser;
import org.mifos.mifospaymentbridge.repository.GatewayUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatewayUserService {
    @Autowired
    private GatewayUserRepository gatewayUserRepository;

    public GatewayUserService(){

    }

    public GatewayUser findOne(Long id){
        return gatewayUserRepository.findOne(id);
    }

    public Iterable<GatewayUser> findAll(){
        return gatewayUserRepository.findAll();
    }

    public GatewayUser save(GatewayUser user){
        return gatewayUserRepository.save(user);
    }

    public List<GatewayUser> save(List<GatewayUser> users){
        return gatewayUserRepository.save(users);
    }

    public boolean exists(Long id){
        return gatewayUserRepository.exists(id);
    }

    public void delete(Long id){
        gatewayUserRepository.delete(id);
    }

    public List<GatewayUser> findByUsernameIgnoreCase(String userName){
        return gatewayUserRepository.findByUsernameIgnoreCase(userName);
    }
}
