/**
 * File: ProviderConfigRepository.java
 * =========================================
 * This interface will create a jpa repository that will
 * help us crud our MMP_config database table.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.repository;


import org.springframework.data.repository.CrudRepository;
import org.mifos.mifospaymentbridge.model.ProviderConfig;
import java.util.List;

public interface ProviderConfigRepository extends CrudRepository<ProviderConfig, Long>{
    ProviderConfig findOne(Long id);

    List<ProviderConfig> findAll();

    ProviderConfig save(ProviderConfig config);

    List<ProviderConfig> save(List<ProviderConfig> configs);

    boolean exists(Long id);

    void deleteById(Long id);

    List<ProviderConfig> findByConfigNameIgnoreCase(String configName);
}