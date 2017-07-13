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
import java.util.List;

public class ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationRepository;

    public ConfigurationService(){

    }

    public Configuration findOne(Long id){
        return configurationRepository.findOne(id);
    }

    public List<Configuration> findAll(){
        return configurationRepository.findAll();
    }

    public Configuration save(Configuration config){
        return configurationRepository.save(config);
    }

    public List<Configuration> save(List<Configuration> configs){
        return configurationRepository.save(configs);
    }

    public boolean exists(Long id){
        return configurationRepository.exists(id);
    }

    public void deleteById(Long id){
        configurationRepository.deleteById(id);
    }

    public List<Configuration> findByConfigNameIgnoreCase(String configName){
        return configurationRepository.findByConfigNameIgnoreCase(configName);
    }
}
