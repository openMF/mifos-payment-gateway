/**
 * File: StatusRepository.java
 * =========================================
 * This interface will create a jpa repository that will
 * help us crud our status database table.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.repository;

import org.springframework.data.repository.CrudRepository;
import org.mifos.mifospaymentbridge.model.Status;

public interface StatusRepository extends CrudRepository<Status, Long>{

}