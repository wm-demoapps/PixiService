/*Copyright (c) 2022-2023 wavemaker.com All Rights Reserved.This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.wavemaker.servicetracker.supportdb;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.sql.Date;
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
 * ProductSerial generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`product_serial`")
public class ProductSerial implements Serializable {

    private Integer id;
    private String serialNo;
    private Integer productId;
    private Integer customerId;
    private Date warrantyStartDate;
    private Date warrantyEndDate;
    private Customer customer;
    private Product product;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`serial_no`", nullable = true, length = 255)
    public String getSerialNo() {
        return this.serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    @Column(name = "`product_id`", nullable = true, scale = 0, precision = 10)
    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Column(name = "`customer_id`", nullable = true, scale = 0, precision = 10)
    public Integer getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Column(name = "`warranty_start_date`", nullable = true)
    public Date getWarrantyStartDate() {
        return this.warrantyStartDate;
    }

    public void setWarrantyStartDate(Date warrantyStartDate) {
        this.warrantyStartDate = warrantyStartDate;
    }

    @Column(name = "`warranty_end_date`", nullable = true)
    public Date getWarrantyEndDate() {
        return this.warrantyEndDate;
    }

    public void setWarrantyEndDate(Date warrantyEndDate) {
        this.warrantyEndDate = warrantyEndDate;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`customer_id`", referencedColumnName = "`id`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_product_serial_TO_cusm05Ni`"))
    @Fetch(FetchMode.JOIN)
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        if(customer != null) {
            this.customerId = customer.getId();
        }

        this.customer = customer;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`product_id`", referencedColumnName = "`id`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_product_serial_TO_proAH8tm`"))
    @Fetch(FetchMode.JOIN)
    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        if(product != null) {
            this.productId = product.getId();
        }

        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductSerial)) return false;
        final ProductSerial productSerial = (ProductSerial) o;
        return Objects.equals(getId(), productSerial.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}