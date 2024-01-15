/*Copyright (c) 2022-2023 wavemaker.com All Rights Reserved.This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.wavemaker.servicetracker.supportdb.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wavemaker.runtime.data.dao.query.WMQueryExecutor;
import com.wavemaker.runtime.data.export.ExportOptions;
import com.wavemaker.runtime.data.model.QueryProcedureInput;

import com.wavemaker.servicetracker.supportdb.models.query.*;

@Service
public class SupportDBQueryExecutorServiceImpl implements SupportDBQueryExecutorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SupportDBQueryExecutorServiceImpl.class);

    @Autowired
    @Qualifier("SupportDBWMQueryExecutor")
    private WMQueryExecutor queryExecutor;

    @Transactional(value = "SupportDBTransactionManager", readOnly = true)
    @Override
    public Page<GetRatingsAggregationResponse> executeGetRatingsAggregation(Pageable pageable) {
        Map<String, Object> params = new HashMap<>(0);


        return queryExecutor.executeNamedQuery("getRatingsAggregation", params, GetRatingsAggregationResponse.class, pageable);
    }

    @Transactional(value = "SupportDBTransactionManager", timeout = 300, readOnly = true)
    @Override
    public void exportGetRatingsAggregation(ExportOptions exportOptions, Pageable pageable, OutputStream outputStream) {
        Map<String, Object> params = new HashMap<>(0);


        QueryProcedureInput<GetRatingsAggregationResponse> queryInput = new QueryProcedureInput<>("getRatingsAggregation", params, GetRatingsAggregationResponse.class);

        queryExecutor.exportNamedQueryData(queryInput, exportOptions, pageable, outputStream);
    }

    @Transactional(value = "SupportDBTransactionManager", readOnly = true)
    @Override
    public Page<GetTotalJobRequestsResponse> executeGetTotalJobRequests(Pageable pageable) {
        Map<String, Object> params = new HashMap<>(0);


        return queryExecutor.executeNamedQuery("getTotalJobRequests", params, GetTotalJobRequestsResponse.class, pageable);
    }

    @Transactional(value = "SupportDBTransactionManager", timeout = 300, readOnly = true)
    @Override
    public void exportGetTotalJobRequests(ExportOptions exportOptions, Pageable pageable, OutputStream outputStream) {
        Map<String, Object> params = new HashMap<>(0);


        QueryProcedureInput<GetTotalJobRequestsResponse> queryInput = new QueryProcedureInput<>("getTotalJobRequests", params, GetTotalJobRequestsResponse.class);

        queryExecutor.exportNamedQueryData(queryInput, exportOptions, pageable, outputStream);
    }

    @Transactional(value = "SupportDBTransactionManager", readOnly = true)
    @Override
    public Page<GetTotalOutstandingRmasResponse> executeGetTotalOutstandingRMAs(Pageable pageable) {
        Map<String, Object> params = new HashMap<>(0);


        return queryExecutor.executeNamedQuery("getTotalOutstandingRMAs", params, GetTotalOutstandingRmasResponse.class, pageable);
    }

    @Transactional(value = "SupportDBTransactionManager", timeout = 300, readOnly = true)
    @Override
    public void exportGetTotalOutstandingRMAs(ExportOptions exportOptions, Pageable pageable, OutputStream outputStream) {
        Map<String, Object> params = new HashMap<>(0);


        QueryProcedureInput<GetTotalOutstandingRmasResponse> queryInput = new QueryProcedureInput<>("getTotalOutstandingRMAs", params, GetTotalOutstandingRmasResponse.class);

        queryExecutor.exportNamedQueryData(queryInput, exportOptions, pageable, outputStream);
    }

    @Transactional(value = "SupportDBTransactionManager", readOnly = true)
    @Override
    public Page<GetDashboardMetricsResponse> executeGetDashboardMetrics(Pageable pageable) {
        Map<String, Object> params = new HashMap<>(0);


        return queryExecutor.executeNamedQuery("getDashboardMetrics", params, GetDashboardMetricsResponse.class, pageable);
    }

    @Transactional(value = "SupportDBTransactionManager", timeout = 300, readOnly = true)
    @Override
    public void exportGetDashboardMetrics(ExportOptions exportOptions, Pageable pageable, OutputStream outputStream) {
        Map<String, Object> params = new HashMap<>(0);


        QueryProcedureInput<GetDashboardMetricsResponse> queryInput = new QueryProcedureInput<>("getDashboardMetrics", params, GetDashboardMetricsResponse.class);

        queryExecutor.exportNamedQueryData(queryInput, exportOptions, pageable, outputStream);
    }

    @Transactional(value = "SupportDBTransactionManager", readOnly = true)
    @Override
    public Page<GetTotalClosedRmasResponse> executeGetTotalClosedRMAs(Pageable pageable) {
        Map<String, Object> params = new HashMap<>(0);


        return queryExecutor.executeNamedQuery("getTotalClosedRMAs", params, GetTotalClosedRmasResponse.class, pageable);
    }

    @Transactional(value = "SupportDBTransactionManager", timeout = 300, readOnly = true)
    @Override
    public void exportGetTotalClosedRMAs(ExportOptions exportOptions, Pageable pageable, OutputStream outputStream) {
        Map<String, Object> params = new HashMap<>(0);


        QueryProcedureInput<GetTotalClosedRmasResponse> queryInput = new QueryProcedureInput<>("getTotalClosedRMAs", params, GetTotalClosedRmasResponse.class);

        queryExecutor.exportNamedQueryData(queryInput, exportOptions, pageable, outputStream);
    }

}
