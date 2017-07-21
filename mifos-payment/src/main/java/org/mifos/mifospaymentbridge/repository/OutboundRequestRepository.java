/**
 * File: OutboundRequestRepository.java
 * =========================================
 * This interface will create a jpa repository that will
 * help us crud our status database table.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.repository;

import org.springframework.data.repository.CrudRepository;
import org.mifos.mifospaymentbridge.model.OutboundRequest;

import java.util.List;

public interface OutboundRequestRepository extends CrudRepository<OutboundRequest, Long>{

    List<OutboundRequest> save(List<OutboundRequest> requests);

    List<OutboundRequest> findBySourceRefIgnoreCase(String ref);

    List<OutboundRequest> findByDestinationRefIgnoreCase(String ref);

    List<OutboundRequest> findByFineractAccNoIgnoreCase(String accNumber);

    OutboundRequest findByExternalSystId(Long id);
}