/**
 * File: MobileMoneyProviderRepository.java
 * =========================================
 * This interface will create a jpa repository that will
 * help us crud our MMP database table.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.repository;

import org.springframework.data.repository.CrudRepository;
import org.mifos.mifospaymentbridge.model.MobileMoneyProvider;

import java.util.List;

public interface MobileMoneyProviderRepository extends CrudRepository<MobileMoneyProvider, Long>{

    List<MobileMoneyProvider> save(List<MobileMoneyProvider> providers);

    List<MobileMoneyProvider> findByNameIgnoreCase(String name);
}