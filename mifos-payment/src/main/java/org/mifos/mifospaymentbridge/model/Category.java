/**
 * File: Category.java
 * =========================================
 * This class models the category
 * table in our database.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.model;


import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Table(name = "category")
public class Category {

    /**
     * table Fields of the category table
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="category_id")
    private Integer id;

    @Column(name="category_name")
    private String categoryName;

    @Column(name="last_modified_dtm")
    private Timestamp lastModifiedDtm;

    @Column(name="last_modified_by_id")
    private Integer lastModifiedById;

    /**
     * Gets the id of a category
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id of a category
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the name of a category
     * @return categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Sets the category name of a category
     * @param categoryName
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Get the last modified date
     * @return lastModifiedDtm
     */
    public Timestamp getLastModifiedDtm() {
        return lastModifiedDtm;
    }

    /**
     * Sets the last modified date
     * @param lastModifiedDtm
     */
    public void setLastModifiedDtm(Timestamp lastModifiedDtm) {
        this.lastModifiedDtm = lastModifiedDtm;
    }

    /**
     * Get the id which last modified the category
     * @return lastModifiedById
     */
    public Integer getLastModifiedById() {
        return lastModifiedById;
    }

    /**
     * Sets the id that last modified the category
     * @param lastModifiedById
     */
    public void setLastModifiedById(Integer lastModifiedById) {
        this.lastModifiedById = lastModifiedById;
    }
}
