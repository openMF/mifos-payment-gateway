/**
 * File: ConfigurationRepository.java
 * =========================================
 * This interface will create a jpa repository that will
 * help us crud our Configuration database table.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.repository;

import org.springframework.data.repository.CrudRepository;
import org.mifos.mifospaymentbridge.model.Configuration;
import java.util.List;

public interface ConfigurationRepository extends CrudRepository<Configuration, Long>{
    Configuration findOne(Long id);

    List<Configuration> findAll();

    Configuration save(Configuration config);

    List<Configuration> save(List<Configuration> configs);

    boolean exists(Long id);

    void deleteById(Long id);

    List<Configuration> findByConfigNameIgnoreCase(String configName);

}