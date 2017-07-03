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

public interface InboundRequestRepository extends CrudRepository<InboundRequest, Long>{

}