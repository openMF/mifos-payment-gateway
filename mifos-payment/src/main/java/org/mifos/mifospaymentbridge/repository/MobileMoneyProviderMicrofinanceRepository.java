/**
 * File: MobileMoneyProviderMicrofinanceRepository.java
 * =========================================
 * This interface will create a jpa repository that will
 * help us crud our MMP_MFI database table.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.repository;

import org.springframework.data.repository.CrudRepository;
import org.mifos.mifospaymentbridge.model.MobileMoneyProviderMicrofinance;
import java.util.List;

public interface MobileMoneyProviderMicrofinanceRepository extends CrudRepository<MobileMoneyProviderMicrofinance, Long>{


    List<MobileMoneyProviderMicrofinance> save(List<MobileMoneyProviderMicrofinance> mmp_mfis);

    List<MobileMoneyProviderMicrofinance> findByMfiPhoneNumberIgnoreCase(String mfiPhoneNumber);
}