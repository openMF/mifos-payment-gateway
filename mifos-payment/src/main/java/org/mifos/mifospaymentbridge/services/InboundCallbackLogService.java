/**
 * File: InboundCallbackLogService.java
 * =========================================
 *  This class helps us to manipulate our InboundCallbackLog models
 * and persist changes to the database.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.services;


import org.mifos.mifospaymentbridge.model.InboundCallbackLog;
import org.mifos.mifospaymentbridge.repository.InboundCallbackLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InboundCallbackLogService {

    @Autowired
    private InboundCallbackLogRepository inboundCallbackLogRepository;

    public InboundCallbackLogService(){

    }

    public InboundCallbackLog findOne(Long id){
        return inboundCallbackLogRepository.findOne(id);
    }

    public Iterable<InboundCallbackLog> findAll(){
        return inboundCallbackLogRepository.findAll();
    }

    public InboundCallbackLog save(InboundCallbackLog log){
        return inboundCallbackLogRepository.save(log);
    }

    public List<InboundCallbackLog> save(List<InboundCallbackLog> logs){
        return inboundCallbackLogRepository.save(logs);
    }

    public boolean exists(Long id){
        return inboundCallbackLogRepository.exists(id);
    }

    public void delete(Long id){
        inboundCallbackLogRepository.delete(id);
    }

    public List<InboundCallbackLog> findByCallbackUrlIgnoreCase(String url){
        return inboundCallbackLogRepository.findByCallbackUrlIgnoreCase(url);
    }
}
