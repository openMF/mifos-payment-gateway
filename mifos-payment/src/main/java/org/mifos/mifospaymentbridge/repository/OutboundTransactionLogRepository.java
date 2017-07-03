/**
 * File: OutboundTransactionLogRepository.java
 * =========================================
 * This interface will create a jpa repository that will
 * help us crud our OutboundTransactionLog database table.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.repository;

import org.springframework.data.repository.CrudRepository;
import org.mifos.mifospaymentbridge.model.OutboundTransactionLog;

public interface OutboundTransactionLogRepository extends CrudRepository<OutboundTransactionLog, Long>{

}