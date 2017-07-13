/**
 * File: InboundCallbackLogRepository.java
 * =========================================
 * This interface will create a jpa repository that will
 * help us crud our InboundCallbackLog database table.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.repository;


import org.springframework.data.repository.CrudRepository;
import org.mifos.mifospaymentbridge.model.InboundCallbackLog;
import java.util.List;

public interface InboundCallbackLogRepository extends CrudRepository<InboundCallbackLog, Long>{
    InboundCallbackLog findOne(Long id);

    List<InboundCallbackLog> findAll();

    InboundCallbackLog save(InboundCallbackLog log);

    List<InboundCallbackLog> save(List<InboundCallbackLog> logs);

    boolean exists(Long id);

    void deleteById(Long id);

    List<InboundCallbackLog> findByCallbackUrlIgnoreCase(String url);
}