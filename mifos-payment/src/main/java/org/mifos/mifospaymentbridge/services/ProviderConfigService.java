/**
 * File: ProviderConfigService.java
 * =========================================
 *  This class helps us to manipulate our ProviderConfig models
 * and persist changes to the database.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.services;


import org.mifos.mifospaymentbridge.model.ProviderConfig;
import org.mifos.mifospaymentbridge.repository.ProviderConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProviderConfigService {

    @Autowired
    private ProviderConfigRepository providerConfigRepository;

    public ProviderConfigService(){

    }

    public ProviderConfig findOne(Long id){
        return providerConfigRepository.findOne(id);
    }

    public List<ProviderConfig> findAll(){
        return providerConfigRepository.findAll();
    }

    public ProviderConfig save(ProviderConfig config){
        return providerConfigRepository.save(config);
    }

    public List<ProviderConfig> save(List<ProviderConfig> configs){
        return providerConfigRepository.save(configs);
    }

    public boolean exists(Long id){
        return providerConfigRepository.exists(id);
    }

    public void deleteById(Long id){
        providerConfigRepository.deleteById(id);
    }

    public List<ProviderConfig> findByConfigNameIgnoreCase(String configName){
        return providerConfigRepository.findByConfigNameIgnoreCase(configName);
    }

}
