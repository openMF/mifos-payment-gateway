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

public interface InboundCallbackLogRepository extends CrudRepository<InboundCallbackLog, Long>{

}