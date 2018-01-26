/**
 * File: MobileMoneyProviderService.java
 * =========================================
 *  This class helps us to manipulate our MobileMoneyProvider models
 * and persist changes to the database.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.services;


import org.mifos.mifospaymentbridge.model.MobileMoneyProvider;
import org.mifos.mifospaymentbridge.repository.MobileMoneyProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MobileMoneyProviderService {

    @Autowired
    private MobileMoneyProviderRepository mobileMoneyProviderRepository;

    public MobileMoneyProviderService(){

    }

    public MobileMoneyProvider findOne(Long id){
        return mobileMoneyProviderRepository.findOne(id);
    }

    public Iterable<MobileMoneyProvider> findAll(){
        return mobileMoneyProviderRepository.findAll();
    }

    public MobileMoneyProvider save(MobileMoneyProvider provider){
        return mobileMoneyProviderRepository.save(provider);
    }

    public List<MobileMoneyProvider> save(List<MobileMoneyProvider> providers){
        return (List<MobileMoneyProvider>) mobileMoneyProviderRepository.save(providers);
    }

    public boolean exists(Long id){
        return mobileMoneyProviderRepository.exists(id);
    }

    public void delete(Long id){
        mobileMoneyProviderRepository.delete(id);
    }

    public List<MobileMoneyProvider> findByNameIgnoreCase(String name){
        return mobileMoneyProviderRepository.findByNameIgnoreCase(name);
    }
}
