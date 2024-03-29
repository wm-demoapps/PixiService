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
 * Engineer generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`engineer`")
public class Engineer implements Serializable {

    private Integer id;
    private String name;
    private String engineerId;
    private Integer serviceTypeId;
    private String mobileNumber;
    private Integer userId;
    private ServiceType serviceType;
    private UserLogin userLogin;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`name`", nullable = true, length = 255)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "`engineer_id`", nullable = true, length = 255)
    public String getEngineerId() {
        return this.engineerId;
    }

    public void setEngineerId(String engineerId) {
        this.engineerId = engineerId;
    }

    @Column(name = "`service_type_id`", nullable = true, scale = 0, precision = 10)
    public Integer getServiceTypeId() {
        return this.serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    @Column(name = "`mobile_number`", nullable = true, length = 255)
    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Column(name = "`user_id`", nullable = true, scale = 0, precision = 10)
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`service_type_id`", referencedColumnName = "`id`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_engineer_TO_service_tS5kS3`"))
    @Fetch(FetchMode.JOIN)
    public ServiceType getServiceType() {
        return this.serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        if(serviceType != null) {
            this.serviceTypeId = serviceType.getId();
        }

        this.serviceType = serviceType;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`user_id`", referencedColumnName = "`id`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_engineer_TO_user_logiDvBrh`"))
    @Fetch(FetchMode.JOIN)
    public UserLogin getUserLogin() {
        return this.userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        if(userLogin != null) {
            this.userId = userLogin.getId();
        }

        this.userLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Engineer)) return false;
        final Engineer engineer = (Engineer) o;
        return Objects.equals(getId(), engineer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
