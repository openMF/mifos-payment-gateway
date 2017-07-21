/**
 * OutboundRequestService.java
 * ========================================
 * This class with help us interact with the outboundRequest
 * entity via the outboundrequest repository
 * @author vladimirfomene.
 */

package org.mifos.mifospaymentbridge.services;


import org.mifos.mifospaymentbridge.domain.OutboundRequest;
import org.mifos.mifospaymentbridge.repository.OutboundRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class OutboundRequestService {

    @Autowired
    private OutboundRequestRepository outboundRequestRepository;

    public OutboundRequestService(){

    }

    public OutboundRequest findOne(Long id){
        return outboundRequestRepository.findOne(id);
    }

    public List<OutboundRequest> findAll(){
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

    public void deleteById(Long id){
        outboundRequestRepository.deleteById(id);
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
}
