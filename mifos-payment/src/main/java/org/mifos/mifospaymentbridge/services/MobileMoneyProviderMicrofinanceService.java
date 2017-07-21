/**
 * File: MobileMoneyProviderMicrofinanceService.java
 * =========================================
 *  This class helps us to manipulate our MobileMoneyProviderMicrofinance models
 * and persist changes to the database.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.services;


import org.mifos.mifospaymentbridge.model.MobileMoneyProviderMicrofinance;
import org.mifos.mifospaymentbridge.repository.MobileMoneyProviderMicrofinanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MobileMoneyProviderMicrofinanceService {

    @Autowired
    private MobileMoneyProviderMicrofinanceRepository mobileMoneyProviderMicrofinanceRepository;

    public MobileMoneyProviderMicrofinanceService(){

    }

    public MobileMoneyProviderMicrofinance findOne(Long id){
        return mobileMoneyProviderMicrofinanceRepository.findOne(id);
    }

    public Iterable<MobileMoneyProviderMicrofinance> findAll(){
        return mobileMoneyProviderMicrofinanceRepository.findAll();
    }

    public MobileMoneyProviderMicrofinance save(MobileMoneyProviderMicrofinance mmp_mfi){
        return mobileMoneyProviderMicrofinanceRepository.save(mmp_mfi);
    }

    public List<MobileMoneyProviderMicrofinance> save(List<MobileMoneyProviderMicrofinance> mmp_mfis){
        return mobileMoneyProviderMicrofinanceRepository.save(mmp_mfis);
    }

    public boolean exists(Long id){
        return mobileMoneyProviderMicrofinanceRepository.exists(id);
    }

    public void delete(Long id){
        mobileMoneyProviderMicrofinanceRepository.delete(id);
    }

    public List<MobileMoneyProviderMicrofinance> findByMfiPhoneNumberIgnoreCase(String mfiPhoneNumber){
        return mobileMoneyProviderMicrofinanceRepository.findByMfiPhoneNumberIgnoreCase(mfiPhoneNumber);
    }
}
