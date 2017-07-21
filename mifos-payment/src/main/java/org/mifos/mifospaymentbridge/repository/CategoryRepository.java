/**
 * File: CategoryRepository.java
 * =========================================
 * This interface will create a jpa repository that will
 * help us crud our category database table.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.repository;


import org.springframework.data.repository.CrudRepository;
import org.mifos.mifospaymentbridge.model.Category;

import java.util.List;


public interface CategoryRepository extends CrudRepository<Category, Integer> {


    List<Category> save(List<Category> categories);


    List<Category> findByCategoryNameIgnoreCase(String categoryName);
}
