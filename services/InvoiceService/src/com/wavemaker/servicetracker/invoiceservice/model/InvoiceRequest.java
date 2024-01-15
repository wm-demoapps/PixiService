/*Copyright (c) 2022-2023 wavemaker.com All Rights Reserved.This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.wavemaker.servicetracker.invoiceservice.model;

import com.wavemaker.servicetracker.supportdb.PartSale;
import com.wavemaker.servicetracker.supportdb.RepairActivity;
import com.wavemaker.servicetracker.supportdb.Rma;

import java.util.List;

public class InvoiceRequest {
    private Rma rma;
    private RepairActivity repairActivity;
    List<PartSale> partSales;

    public Rma getRma() {
        return rma;
    }

    public void setRma(Rma rma) {
        this.rma = rma;
    }

    public RepairActivity getRepairActivity() {
        return repairActivity;
    }

    public void setRepairActivity(RepairActivity repairActivity) {
        this.repairActivity = repairActivity;
    }

    public List<PartSale> getPartSales() {
        return partSales;
    }

    public void setPartSales(List<PartSale> partSales) {
        this.partSales = partSales;
    }
}
