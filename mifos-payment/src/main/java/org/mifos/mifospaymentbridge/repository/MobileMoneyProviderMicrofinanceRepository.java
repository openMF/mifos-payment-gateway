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
    MobileMoneyProviderMicrofinance findOne(Long id);

    List<MobileMoneyProviderMicrofinance> findAll();

    MobileMoneyProviderMicrofinance save(MobileMoneyProviderMicrofinance mmp_mfi);

    List<MobileMoneyProviderMicrofinance> save(List<MobileMoneyProviderMicrofinance> mmp_mfis);

    boolean exists(Long id);

    void deleteById(Long id);

    List<MobileMoneyProviderMicrofinance> findByMfiPhoneNumberIgnoreCase(String mfiPhoneNumber);
}