/**
 * File: BatchService.java
 * =========================================
 * This class helps us to manipulate our batch models
 * and persist changes to the database.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.services;

import org.mifos.mifospaymentbridge.model.Category;
import org.mifos.mifospaymentbridge.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryService(){

    }

    public Category findOne(Integer id){
        return categoryRepository.findOne(id);
    }

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> save(List<Category> categories){
        return categoryRepository.save(categories);
    }

    public boolean exists(Integer id){
        return categoryRepository.exists(id);
    }

    public void deleteById(Integer id){
        categoryRepository.deleteById(id);
    }

    public List<Category> findByCategoryNameIgnoreCase(String categoryName){
        return categoryRepository.findByCategoryNameIgnoreCase(categoryName);
    }
}
