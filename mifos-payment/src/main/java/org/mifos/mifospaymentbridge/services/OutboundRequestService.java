/**
 * OutboundRequestService.java
 * ========================================
 * This class with help us interact with the outboundRequest
 * entity via the outboundrequest repository
 * @author vladimirfomene.
 */

package org.mifos.mifospaymentbridge.services;


import org.mifos.mifospaymentbridge.model.OutboundRequest;
import org.mifos.mifospaymentbridge.repository.OutboundRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutboundRequestService {

    @Autowired
    private OutboundRequestRepository outboundRequestRepository;

    public OutboundRequestService(){

    }

    public OutboundRequest findOne(Long id){
        return outboundRequestRepository.findOne(id);
    }

    public Iterable<OutboundRequest> findAll(){
        return outboundRequestRepository.findAll();
    }

    public OutboundRequest save(OutboundRequest outboundRequest){
        return outboundRequestRepository.save(outboundRequest);
    }

    public List<OutboundRequest> save(List<OutboundRequest> requests){
        return outboundRequestRepository.save(requests);
    }

    public boolean exists(Long id){
        return outboundRequestRepository.exists(id);
    }

    public void delete(Long id){
        outboundRequestRepository.delete(id);
    }

    public List<OutboundRequest> findBySourceRefIgnoreCase(String ref){
        return outboundRequestRepository.findBySourceRefIgnoreCase(ref);
    }

    public List<OutboundRequest> findByDestinationRefIgnoreCase(String ref){
        return outboundRequestRepository.findByDestinationRefIgnoreCase(ref);
    }

    public List<OutboundRequest> findByFineractAccNoIgnoreCase(String accNumber){
        return outboundRequestRepository.findByFineractAccNoIgnoreCase(accNumber);
    }

    public OutboundRequest findByExternalSystId(Long id){
        return outboundRequestRepository.findByExternalSystId(id);
    }
}
