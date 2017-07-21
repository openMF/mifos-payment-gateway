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
import java.util.List;

public interface StatusRepository extends CrudRepository<Status, Integer>{

    List<Status> save(List<Status> statuses);

    List<Status> findByCodeIgnoreCase(String code);
}