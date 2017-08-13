/**
 * File: StatusService.java
 * =========================================
 *  This class helps us to manipulate our Status models
 * and persist changes to the database.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.services;


import org.mifos.mifospaymentbridge.model.Status;
import org.mifos.mifospaymentbridge.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;


    public Status findOne(Integer id){
        return statusRepository.findOne(id);
    }

    public Iterable<Status> findAll(){
        return statusRepository.findAll();
    }

    public Status save(Status status){
        return statusRepository.save(status);
    }

    public List<Status> save(List<Status> statuses){
        return statusRepository.save(statuses);
    }

    public boolean exists(Integer id){
        return statusRepository.exists(id);
    }

    public void delete(Integer id){
        statusRepository.delete(id);
    }

    public List<Status> findByCodeIgnoreCase(String code){
        return statusRepository.findByCodeIgnoreCase(code);
    }
}
