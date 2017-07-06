/**
 * File: OutboundRequestRepository.java
 * =========================================
 * This interface will create a jpa repository that will
 * help us crud our status database table.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.repository;

import org.springframework.data.repository.CrudRepository;
import org.mifos.mifospaymentbridge.domain.OutboundRequest;

import java.util.List;

public interface OutboundRequestRepository extends CrudRepository<OutboundRequest, Long>{
    OutboundRequest findOne(Long id);

    List<OutboundRequest> findAll();

    OutboundRequest save(OutboundRequest request);

    List<OutboundRequest> save(List<OutboundRequest> requests);

    boolean exists(Long id);

    void deleteById(Long id);

    List<OutboundRequest> findBySourceRefIgnoreCase(String ref);

    List<OutboundRequest> findByDestinationRefIgnoreCase(String ref);

    List<OutboundRequest> findByFineractAccNoIgnoreCase(String accNumber);
}