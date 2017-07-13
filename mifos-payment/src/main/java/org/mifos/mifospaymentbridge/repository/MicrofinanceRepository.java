/**
 * File: MicrofinanceRepository.java
 * =========================================
 * This interface will create a jpa repository that will
 * help us crud our MFI database table.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.repository;

import org.mifos.mifospaymentbridge.model.InboundRequest;
import org.springframework.data.repository.CrudRepository;
import org.mifos.mifospaymentbridge.model.Microfinance;
import java.util.List;

public interface MicrofinanceRepository extends CrudRepository<Microfinance, Long>{
    Microfinance findOne(Long id);

    List<Microfinance> findAll();

    Microfinance save(Microfinance user);

    List<Microfinance> save(List<Microfinance> users);

    boolean exists(Long id);

    void deleteById(Long id);

    List<Microfinance> findByNameIgnoreCase(String mfiName);
}