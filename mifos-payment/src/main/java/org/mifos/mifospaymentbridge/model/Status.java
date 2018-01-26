/**
 * File: Status.java
 * =========================================
 * This class models the status
 * table in our database.
 * @author Vladimir Fomene
 **/

package org.mifos.mifospaymentbridge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name="status")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Status{

    /**
     * table Fields of the status table
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="status_id")
    private Integer id;

    @Column(name="code")
    private String code;

    @Column(name="description")
    private String description;

    @Column(name="status_category")
    private Integer statusCategory;

    /**
     * Gets the status id
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set the status id
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the status_code of a status
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the status_code of a status
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the description of a status
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of a status
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the category of a status
     * @return statusCategory
     */
    public Integer getStatusCategory() {
        return statusCategory;
    }

    /**
     * Sets the category of a status
     * @param statusCategory
     */
    public void setStatusCategory(Integer statusCategory) {
        this.statusCategory = statusCategory;
    }
}