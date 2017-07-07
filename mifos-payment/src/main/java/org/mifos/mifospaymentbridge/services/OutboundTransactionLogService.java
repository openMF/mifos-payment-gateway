/**
 * File: OutboundTransactionLogService.java
 * =========================================
 *  This class helps us to manipulate our OutboundTransactionLog models
 * and persist changes to the database.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.services;


import org.mifos.mifospaymentbridge.model.OutboundTransactionLog;
import org.mifos.mifospaymentbridge.repository.OutboundTransactionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OutboundTransactionLogService {

    @Autowired
    private OutboundTransactionLogRepository outboundTransactionLogRepository;

    public OutboundTransactionLogService(){

    }

    public OutboundTransactionLog findOne(Long id){
        return outboundTransactionLogRepository.findOne(id);
    }

    public List<OutboundTransactionLog> findAll(){
        return outboundTransactionLogRepository.findAll();
    }

    public OutboundTransactionLog save(OutboundTransactionLog log){
        return outboundTransactionLogRepository.save(log);
    }

    public List<OutboundTransactionLog> save(List<OutboundTransactionLog> logs){
        return outboundTransactionLogRepository.save(logs);
    }

    public boolean exists(Long id){
        return outboundTransactionLogRepository.exists(id);
    }

    public void deleteById(Long id){
        outboundTransactionLogRepository.deleteById(id);
    }

    public List<OutboundTransactionLog> findByTransactTypeIgnoreCase(OutboundTransactionLog.TransactionType transactionType){
        return outboundTransactionLogRepository.findByTransactType(transactionType);
    }
}
