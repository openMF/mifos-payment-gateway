/**
 * File: InboundRequestService.java
 * =========================================
 *  This class helps us to manipulate our InboundRequest models
 * and persist changes to the database.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.services;


import org.mifos.mifospaymentbridge.model.InboundRequest;
import org.mifos.mifospaymentbridge.repository.InboundRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InboundRequestService {

    @Autowired
    private InboundRequestRepository inboundRequestRepository;

    public InboundRequestService(){

    }

    public InboundRequest findOne(Long id){
        return inboundRequestRepository.findOne(id);
    }

    public Iterable<InboundRequest> findAll(){
        return inboundRequestRepository.findAll();
    }

    public InboundRequest save(InboundRequest request){
        return inboundRequestRepository.save(request);
    }

    public List<InboundRequest> save(List<InboundRequest> requests){
        return inboundRequestRepository.save(requests);
    }

    public boolean exists(Long id){
        return inboundRequestRepository.exists(id);
    }

    public void delete(Long id){
        inboundRequestRepository.delete(id);
    }

    public List<InboundRequest> findBySourceRefIgnoreCase(String ref){
        return inboundRequestRepository.findBySourceRefIgnoreCase(ref);
    }

    public List<InboundRequest> findByDestinationRefIgnoreCase(String ref){
        return inboundRequestRepository.findByDestinationRefIgnoreCase(ref);
    }

    public List<InboundRequest> findByFineractAccNoIgnoreCase(String accNumber){
        return inboundRequestRepository.findByFineractAccNoIgnoreCase(accNumber);
    }
}
