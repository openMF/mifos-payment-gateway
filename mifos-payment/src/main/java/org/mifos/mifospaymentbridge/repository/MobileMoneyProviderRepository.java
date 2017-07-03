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

public interface MobileMoneyProviderRepository extends CrudRepository<MobileMoneyProvider, Long>{

}