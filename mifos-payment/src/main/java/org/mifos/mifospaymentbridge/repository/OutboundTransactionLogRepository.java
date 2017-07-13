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
import java.util.List;

public interface OutboundTransactionLogRepository extends CrudRepository<OutboundTransactionLog, Long>{
    OutboundTransactionLog findOne(Long id);

    List<OutboundTransactionLog> findAll();

    OutboundTransactionLog save(OutboundTransactionLog log);

    List<OutboundTransactionLog> save(List<OutboundTransactionLog> logs);

    boolean exists(Long id);

    void deleteById(Long id);

    List<OutboundTransactionLog> findByTransactType(OutboundTransactionLog.TransactionType transactionType);
}