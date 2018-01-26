/**
 * File: OutboundTransactionLogRepository.java
 * =========================================
 * This interface will create a jpa repository that will
 * help us crud our OutboundTransactionLog database table.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.repository;

import org.mifos.mifospaymentbridge.Util.TransactionType;
import org.springframework.data.repository.CrudRepository;
import org.mifos.mifospaymentbridge.model.OutboundTransactionLog;
import java.util.List;

public interface OutboundTransactionLogRepository extends CrudRepository<OutboundTransactionLog, Long>{

    List<OutboundTransactionLog> save(List<OutboundTransactionLog> logs);

    List<OutboundTransactionLog> findByTransactType(TransactionType transactionType);

    OutboundTransactionLog findByOutboundRequestId(Long id);

}