/**
 * File: GatewayUser.java
 * =========================================
 * This class models the mifos payment gateway user
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

@Entity
@Table(name = "gateway_users")
public class GatewayUser{

    /**
     * table Fields of our gateway_users table
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="gateway_user_id")
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="mmp_id")
    private Long mmpId;

    @Column(name="mfi_id")
    private Long mfiId;

    @Column(name="mifos_user_id")
    private Long mifosUserId;

    @Column(name="role_id")
    private String roleId;


    /**
     * Gets the id of the gateway user
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets a new id for a gateway user
     * @param id new id of our gateway user
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the username of a gateway user
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets a new username for a gateway user
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of a gateway user
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of a gateway user
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the mmp_id of a gateway user
     * @return mmpId
     */
    public Long getMmpId() {
        return mmpId;
    }

    /**
     * Sets the mmp_id of a gateway user
     * @param mmpId
     */
    public void setMmpId(Long mmpId) {
        this.mmpId = mmpId;
    }

    /**
     * Gets the mfi_id for a gateway user
     * @return mfiId
     */
    public Long getMfiId() {
        return mfiId;
    }

    /**
     * Sets the mfi_id for a gateway user
     * @param mfiId
     */
    public void setMfiId(Long mfiId) {
        this.mfiId = mfiId;
    }

    /**
     * Gets the mifos_user_id of a gateway user
     * @return mifosUserId
     */
    public Long getMifosUserId() {
        return mifosUserId;
    }

    /**
     * Sets the mifos_user_id of a gateway user
     * @param mifosUserId
     */
    public void setMifosUserId(Long mifosUserId) {
        this.mifosUserId = mifosUserId;
    }

    /**
     * Gets role_id of a gateway user
     * @return roleId
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * Sets the Role_id of a gateway user.
     * @param roleId
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}