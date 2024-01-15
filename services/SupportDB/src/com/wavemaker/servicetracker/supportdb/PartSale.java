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
 * PartSale generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`part_sale`")
public class PartSale implements Serializable {

    private Integer id;
    private Integer repairActivityId;
    private Integer partId;
    private Integer paymentTypeId;
    private Boolean buyBack;
    private Integer discount;
    private Integer price;
    private Integer quantity;
    private Part part;
    private PaymentType paymentType;
    private RepairActivity repairActivity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`repair_activity_id`", nullable = true, scale = 0, precision = 10)
    public Integer getRepairActivityId() {
        return this.repairActivityId;
    }

    public void setRepairActivityId(Integer repairActivityId) {
        this.repairActivityId = repairActivityId;
    }

    @Column(name = "`part_id`", nullable = true, scale = 0, precision = 10)
    public Integer getPartId() {
        return this.partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    @Column(name = "`payment_type_id`", nullable = true, scale = 0, precision = 10)
    public Integer getPaymentTypeId() {
        return this.paymentTypeId;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    @Column(name = "`buy_back`", nullable = true)
    public Boolean getBuyBack() {
        return this.buyBack;
    }

    public void setBuyBack(Boolean buyBack) {
        this.buyBack = buyBack;
    }

    @Column(name = "`discount`", nullable = true, scale = 0, precision = 10)
    public Integer getDiscount() {
        return this.discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Column(name = "`price`", nullable = true, scale = 0, precision = 10)
    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Column(name = "`quantity`", nullable = true, scale = 0, precision = 10)
    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`part_id`", referencedColumnName = "`id`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_part_sale_TO_part_parJjGam`"))
    @Fetch(FetchMode.JOIN)
    public Part getPart() {
        return this.part;
    }

    public void setPart(Part part) {
        if(part != null) {
            this.partId = part.getId();
        }

        this.part = part;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`payment_type_id`", referencedColumnName = "`id`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_part_sale_TO_payment_BbmL3`"))
    @Fetch(FetchMode.JOIN)
    public PaymentType getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        if(paymentType != null) {
            this.paymentTypeId = paymentType.getId();
        }

        this.paymentType = paymentType;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`repair_activity_id`", referencedColumnName = "`id`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_part_sale_TO_repair_ajROtT`"))
    @Fetch(FetchMode.JOIN)
    public RepairActivity getRepairActivity() {
        return this.repairActivity;
    }

    public void setRepairActivity(RepairActivity repairActivity) {
        if(repairActivity != null) {
            this.repairActivityId = repairActivity.getId();
        }

        this.repairActivity = repairActivity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PartSale)) return false;
        final PartSale partSale = (PartSale) o;
        return Objects.equals(getId(), partSale.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
