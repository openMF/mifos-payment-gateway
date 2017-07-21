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

    List<Microfinance> save(List<Microfinance> users);

    List<Microfinance> findByNameIgnoreCase(String mfiName);
}