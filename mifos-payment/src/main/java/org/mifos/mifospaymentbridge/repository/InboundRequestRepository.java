/**
 * File: InboundRequestRepository.java
 * =========================================
 * This interface will create a jpa repository that will
 * help us crud our inbound_request database table.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.repository;

import org.springframework.data.repository.CrudRepository;
import org.mifos.mifospaymentbridge.model.InboundRequest;

import java.util.List;

public interface InboundRequestRepository extends CrudRepository<InboundRequest, Long>{
    InboundRequest findOne(Long id);

    List<InboundRequest> findAll();

    InboundRequest save(InboundRequest request);

    List<InboundRequest> save(List<InboundRequest> requests);

    boolean exists(Long id);

    void deleteById(Long id);

    List<InboundRequest> findBySourceRefIgnoreCase(String ref);

    List<InboundRequest> findByDestinationRefIgnoreCase(String ref);

    List<InboundRequest> findByFineractAccNoIgnoreCase(String accNumber);
}