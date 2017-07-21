/**
 * File: Microfinance.java
 * =========================================
 * This class models the MFI institution
 * table in our database.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.model;

import org.joda.time.DateTime;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name="mfi")
public class Microfinance{

    /**
     * table Fields of our Microfinance table
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="mfi_id")
    private Long id;

    @Column(name="mfi_name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="last_modified_dtm")
    private DateTime lastModified;

    @Column(name="last_modified_by_id")
    private Long lastModifiedById;

    /**
     * Get the id of a microfinance
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of a microfinance
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the name of a microfinance
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of a microfinance
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the descriptions of the microfinance
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of a microfinance
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the last modified datetime of a microfinance
     * @return lastModified
     */
    public DateTime getLastModified() {
        return lastModified;
    }

    /**
     * Sets the last modified datetime of a microfinance
     * @param lastModified
     */
    public void setLastModified(DateTime lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * Gets the last_modified_by_id of a microfinance
     * @return
     */
    public Long getLastModifiedById() {
        return lastModifiedById;
    }

    /**
     * Sets the last_modified_by_id of a microfinance
     * @param lastModifiedById
     */
    public void setLastModifiedById(Long lastModifiedById) {
        this.lastModifiedById = lastModifiedById;
    }
}