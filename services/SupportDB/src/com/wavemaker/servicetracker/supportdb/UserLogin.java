/*Copyright (c) 2022-2023 wavemaker.com All Rights Reserved.This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.wavemaker.servicetracker.supportdb;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * UserLogin generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`user_login`")
public class UserLogin implements Serializable {

    private Integer id;
    private String username;
    private String email;
    private String password;
    private Integer roleId;
    private String name;
    private UserRole userRole;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`username`", nullable = true, length = 255)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "`email`", nullable = true, length = 255)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "`password`", nullable = true, length = 255)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "`role_id`", nullable = true, scale = 0, precision = 10)
    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Column(name = "`name`", nullable = true, length = 255)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`role_id`", referencedColumnName = "`id`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_user_login_TO_user_roAH0YE`"))
    @Fetch(FetchMode.JOIN)
    public UserRole getUserRole() {
        return this.userRole;
    }

    public void setUserRole(UserRole userRole) {
        if(userRole != null) {
            this.roleId = userRole.getId();
        }

        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserLogin)) return false;
        final UserLogin userLogin = (UserLogin) o;
        return Objects.equals(getId(), userLogin.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
