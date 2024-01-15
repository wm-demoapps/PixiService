/*Copyright (c) 2022-2023 wavemaker.com All Rights Reserved.This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.wavemaker.servicetracker.supportdb;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * RepairActivity generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`repair_activity`")
public class RepairActivity implements Serializable {

    private Integer id;
    private Integer rmaId;
    private Date date;
    private Time startTime;
    private Time endTime;
    private Integer faultAnalysisId;
    private String faultDescription;
    private String actionTaken;
    private String errorCode;
    private FaultAnalysis faultAnalysis;
    private Rma rma;
    private List<PartSale> partSales;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", nullable = false, scale = 0, precision = 10)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "`rma_id`", nullable = true, scale = 0, precision = 10)
    public Integer getRmaId() {
        return this.rmaId;
    }

    public void setRmaId(Integer rmaId) {
        this.rmaId = rmaId;
    }

    @Column(name = "`date`", nullable = true)
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "`start_time`", nullable = true)
    public Time getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    @Column(name = "`end_time`", nullable = true)
    public Time getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Column(name = "`fault_analysis_id`", nullable = true, scale = 0, precision = 10)
    public Integer getFaultAnalysisId() {
        return this.faultAnalysisId;
    }

    public void setFaultAnalysisId(Integer faultAnalysisId) {
        this.faultAnalysisId = faultAnalysisId;
    }

    @Column(name = "`fault_description`", nullable = true, length = 255)
    public String getFaultDescription() {
        return this.faultDescription;
    }

    public void setFaultDescription(String faultDescription) {
        this.faultDescription = faultDescription;
    }

    @Column(name = "`action_taken`", nullable = true, length = 255)
    public String getActionTaken() {
        return this.actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    @Column(name = "`error_code`", nullable = true, length = 255)
    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`fault_analysis_id`", referencedColumnName = "`id`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_repair_activity_TO_fabjRpS`"))
    @Fetch(FetchMode.JOIN)
    public FaultAnalysis getFaultAnalysis() {
        return this.faultAnalysis;
    }

    public void setFaultAnalysis(FaultAnalysis faultAnalysis) {
        if(faultAnalysis != null) {
            this.faultAnalysisId = faultAnalysis.getId();
        }

        this.faultAnalysis = faultAnalysis;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`rma_id`", referencedColumnName = "`id`", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "`FK_repair_activity_TO_rm4M2HO`"))
    @Fetch(FetchMode.JOIN)
    public Rma getRma() {
        return this.rma;
    }

    public void setRma(Rma rma) {
        if(rma != null) {
            this.rmaId = rma.getId();
        }

        this.rma = rma;
    }
    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "repairActivity")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.REMOVE})
    public List<PartSale> getPartSales() {
        return this.partSales;
    }

    public void setPartSales(List<PartSale> partSales) {
        this.partSales = partSales;
    }

    @PostPersist
    public void onPostPersist() {
        if(partSales != null) {
            partSales.forEach(_partSale -> _partSale.setRepairActivity(this));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RepairActivity)) return false;
        final RepairActivity repairActivity = (RepairActivity) o;
        return Objects.equals(getId(), repairActivity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}