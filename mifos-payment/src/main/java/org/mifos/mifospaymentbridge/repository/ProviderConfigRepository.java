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

public interface ProviderConfigRepository extends CrudRepository<ProviderConfig, Long>{

}