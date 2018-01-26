/**
 * File: MicrofinanceService.java
 * =========================================
 *  This class helps us to manipulate Microfinance models
 * and persist changes to the database.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.services;


import org.mifos.mifospaymentbridge.model.Microfinance;
import org.mifos.mifospaymentbridge.repository.MicrofinanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MicrofinanceService {

    @Autowired
    private MicrofinanceRepository microfinanceRepository;

    public MicrofinanceService(){

    }

    public Microfinance findOne(Long id){
        return microfinanceRepository.findOne(id);
    }

    public Iterable<Microfinance> findAll(){
        return microfinanceRepository.findAll();
    }

    public Microfinance save(Microfinance mfi){
        return microfinanceRepository.save(mfi);
    }

    public boolean exists(Long id){
        return microfinanceRepository.exists(id);
    }

    public void delete(Long id){
        microfinanceRepository.delete(id);
    }
    public List<Microfinance> save(List<Microfinance> users){
        return microfinanceRepository.save(users);
    }

    public Microfinance findMicrofinanceByName(String mfiName){
        return microfinanceRepository.findMicrofinanceByName(mfiName);
    }
}
