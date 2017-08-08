/**
 * File: ConfigurationService.java
 * =========================================
 *  This class helps us to manipulate our configuration models
 * and persist changes to the database.
 * @author Vladimir Fomene
 **/


package org.mifos.mifospaymentbridge.services;


import org.mifos.mifospaymentbridge.model.Configuration;
import org.mifos.mifospaymentbridge.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationRepository;

    public ConfigurationService(){

    }

    public Configuration findOne(Long id){
        return configurationRepository.findOne(id);
    }

    public Iterable<Configuration> findAll(){
        return configurationRepository.findAll();
    }

    public Configuration save(Configuration config){
        return configurationRepository.save(config);
    }

    public Iterable<Configuration> save(List<Configuration> configs){
        return configurationRepository.save(configs);
    }

    public boolean exists(Long id){
        return configurationRepository.exists(id);
    }

    public void deleteById(Long id){
        configurationRepository.delete(id);
    }

    public Configuration findConfigurationByConfigNameAndReferenceId(String confName, Long refId){
        return configurationRepository.findConfigurationByConfigNameAndReferenceId(confName, refId);
    }

    public List<Configuration> findByConfigNameIgnoreCase(String configName){
        return configurationRepository.findByConfigNameIgnoreCase(configName);
    }

    public Configuration findByReferenceIdAndRefType(Long id, String type){
        return configurationRepository.findByReferenceIdAndRefType(id, type);
    }
}
