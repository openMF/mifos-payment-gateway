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
    Status findOne(Integer id);

    List<Status> findAll();

    Status save(Status status);

    List<Status> save(List<Status> statuses);

    boolean exists(Integer id);

    void deleteById(Integer id);

    List<Status> findByCodeIgnoreCase(String code);
}