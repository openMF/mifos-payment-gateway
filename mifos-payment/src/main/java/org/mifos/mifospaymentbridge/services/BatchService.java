/**
 * File: BatchService.java
 * =========================================
 * This class helps us to manipulate our batch models
 * and persist changes to the database.
 * @author Vladimir Fomene
 **/


package org.mifos.mifospaymentbridge.services;


import org.mifos.mifospaymentbridge.model.Batch;
import org.mifos.mifospaymentbridge.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class BatchService {
    @Autowired
    private BatchRepository batchRepository;

    public BatchService(){

    }

    public Batch findOne(Long id){
        return batchRepository.findOne(id);
    }

    public Iterable<Batch> findAll(){
        return batchRepository.findAll();
    }

    public Batch save(Batch batch){
        return batchRepository.save(batch);
    }

    public List<Batch> save(List<Batch> batches){
        return batchRepository.save(batches);
    }

    public boolean exists(Long id){
        return batchRepository.exists(id);
    }

    public void delete(Long id){
        batchRepository.delete(id);
    }

    public List<Batch> findByTransact_directionIgnoreCase(Batch.TransactionDirection transactionDirection){
        return batchRepository.findByTransactDirection(transactionDirection);
    }
}
