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
 * Rma generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`rma`")
public class Rma implements Serializable {

    private Integer id;
    private Integer jobRequestId;
    private Integer serviceAreaId;
    private Integer serviceTypeId;
    private Integer serviceModeId;
    private String fullAddress;
    private String rmaNumber;
    private Boolean isApproved;
    private Integer quotationAmount;
    private Integer paymentTypeId;
    private Integer engineerAppointmentId;
    private Integer totalAmount;
    private Boolean isClosed;
    private String problemDescription;
    private Date createdOn;
    private EngineerAppointment engineerAppointment;
    private JobRequest jobRequest;
    private PaymentType paymentType;
    private ServiceArea serviceArea;
    private ServiceMode serviceMode;
    private ServiceType serviceType;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`job_request_id`", nullable = true, scale = 0, precision = 10)
    public Integer getJobRequestId() {
        return this.jobRequestId;
    }

    public void setJobRequestId(Integer jobRequestId) {
        this.jobRequestId = jobRequestId;
    }

    @Column(name = "`service_area_id`", nullable = true, scale = 0, precision = 10)
    public Integer getServiceAreaId() {
        return this.serviceAreaId;
    }

    public void setServiceAreaId(Integer serviceAreaId) {
        this.serviceAreaId = serviceAreaId;
    }

    @Column(name = "`service_type_id`", nullable = true, scale = 0, precision = 10)
    public Integer getServiceTypeId() {
        return this.serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    @Column(name = "`service_mode_id`", nullable = true, scale = 0, precision = 10)
    public Integer getServiceModeId() {
        return this.serviceModeId;
    }

    public void setServiceModeId(Integer serviceModeId) {
        this.serviceModeId = serviceModeId;
    }

    @Column(name = "`full_address`", nullable = true, length = 255)
    public String getFullAddress() {
        return this.fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    @Column(name = "`rma_number`", nullable = true, length = 255)
    public String getRmaNumber() {
        return this.rmaNumber;
    }

    public void setRmaNumber(String rmaNumber) {
        this.rmaNumber = rmaNumber;
    }

    @Column(name = "`is_approved`", nullable = true)
    public Boolean getIsApproved() {
        return this.isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    @Column(name = "`quotation_amount`", nullable = true, scale = 0, precision = 10)
    public Integer getQuotationAmount() {
        return this.quotationAmount;
    }

    public void setQuotationAmount(Integer quotationAmount) {
        this.quotationAmount = quotationAmount;
    }

    @Column(name = "`payment_type_id`", nullable = true, scale = 0, precision = 10)
    public Integer getPaymentTypeId() {
        return this.paymentTypeId;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    @Column(name = "`engineer_appointment_id`", nullable = true, scale = 0, precision = 10)
    public Integer getEngineerAppointmentId() {
        return this.engineerAppointmentId;
    }

    public void setEngineerAppointmentId(Integer engineerAppointmentId) {
        this.engineerAppointmentId = engineerAppointmentId;
    }

    @Column(name = "`total_amount`", nullable = true, scale = 0, precision = 10)
    public Integer getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Column(name = "`is_closed`", nullable = true)
    public Boolean getIsClosed() {
        return this.isClosed;
    }

    public void setIsClosed(Boolean isClosed) {
        this.isClosed = isClosed;
    }

    @Column(name = "`problem_description`", nullable = true, length = 255)
    public String getProblemDescription() {
        return this.problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    @Column(name = "`created_on`", nullable = true)
    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`engineer_appointment_id`", referencedColumnName = "`id`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_rma_TO_engineer_appoidy8Vk`"))
    @Fetch(FetchMode.JOIN)
    public EngineerAppointment getEngineerAppointment() {
        return this.engineerAppointment;
    }

    public void setEngineerAppointment(EngineerAppointment engineerAppointment) {
        if(engineerAppointment != null) {
            this.engineerAppointmentId = engineerAppointment.getId();
        }

        this.engineerAppointment = engineerAppointment;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`job_request_id`", referencedColumnName = "`id`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_rma_TO_job_request_joi7tNK`"))
    @Fetch(FetchMode.JOIN)
    public JobRequest getJobRequest() {
        return this.jobRequest;
    }

    public void setJobRequest(JobRequest jobRequest) {
        if(jobRequest != null) {
            this.jobRequestId = jobRequest.getId();
        }

        this.jobRequest = jobRequest;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`payment_type_id`", referencedColumnName = "`id`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_rma_TO_payment_type_potArt`"))
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
    @JoinColumn(name = "`service_area_id`", referencedColumnName = "`id`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_rma_TO_service_area_swyW6X`"))
    @Fetch(FetchMode.JOIN)
    public ServiceArea getServiceArea() {
        return this.serviceArea;
    }

    public void setServiceArea(ServiceArea serviceArea) {
        if(serviceArea != null) {
            this.serviceAreaId = serviceArea.getId();
        }

        this.serviceArea = serviceArea;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`service_mode_id`", referencedColumnName = "`id`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_rma_TO_service_mode_sF6ncR`"))
    @Fetch(FetchMode.JOIN)
    public ServiceMode getServiceMode() {
        return this.serviceMode;
    }

    public void setServiceMode(ServiceMode serviceMode) {
        if(serviceMode != null) {
            this.serviceModeId = serviceMode.getId();
        }

        this.serviceMode = serviceMode;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`service_type_id`", referencedColumnName = "`id`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_rma_TO_service_type_s8z1vX`"))
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rma)) return false;
        final Rma rma = (Rma) o;
        return Objects.equals(getId(), rma.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
