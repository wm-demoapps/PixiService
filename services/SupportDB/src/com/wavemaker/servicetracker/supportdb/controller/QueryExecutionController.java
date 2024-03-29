/*Copyright (c) 2022-2023 wavemaker.com All Rights Reserved.This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.wavemaker.servicetracker.supportdb.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.commons.wrapper.StringWrapper;
import com.wavemaker.runtime.commons.file.manager.ExportedFileManager;
import com.wavemaker.runtime.data.export.ExportOptions;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import com.wavemaker.servicetracker.supportdb.service.SupportDBQueryExecutorService;
import com.wavemaker.servicetracker.supportdb.models.query.*;

@RestController(value = "SupportDB.QueryExecutionController")
@RequestMapping("/SupportDB/queryExecutor")
@Api(value = "QueryExecutionController", description = "controller class for query execution")
public class QueryExecutionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryExecutionController.class);

    @Autowired
    private SupportDBQueryExecutorService queryService;

    @Autowired
	private ExportedFileManager exportedFileManager;

    @GetMapping(value = "/queries/getRatingsAggregation")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "getRatingsAggregation")
    public Page<GetRatingsAggregationResponse> executeGetRatingsAggregation(Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: getRatingsAggregation");
        Page<GetRatingsAggregationResponse> _result = queryService.executeGetRatingsAggregation(pageable);
        LOGGER.debug("got the result for named query: getRatingsAggregation, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file url for query getRatingsAggregation")
    @PostMapping(value = "/queries/getRatingsAggregation/export")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportGetRatingsAggregation(@RequestBody ExportOptions exportOptions, Pageable pageable) {
        LOGGER.debug("Exporting named query: getRatingsAggregation");

        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = "getRatingsAggregation";
        }
        exportedFileName += exportOptions.getExportType().getExtension();

        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName,
                        outputStream -> queryService.exportGetRatingsAggregation( exportOptions, pageable, outputStream));

        return new StringWrapper(exportedUrl);
    }

    @GetMapping(value = "/queries/getTotalJobRequests")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "getTotalJobRequests")
    public Page<GetTotalJobRequestsResponse> executeGetTotalJobRequests(Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: getTotalJobRequests");
        Page<GetTotalJobRequestsResponse> _result = queryService.executeGetTotalJobRequests(pageable);
        LOGGER.debug("got the result for named query: getTotalJobRequests, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file url for query getTotalJobRequests")
    @PostMapping(value = "/queries/getTotalJobRequests/export")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportGetTotalJobRequests(@RequestBody ExportOptions exportOptions, Pageable pageable) {
        LOGGER.debug("Exporting named query: getTotalJobRequests");

        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = "getTotalJobRequests";
        }
        exportedFileName += exportOptions.getExportType().getExtension();

        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName,
                        outputStream -> queryService.exportGetTotalJobRequests( exportOptions, pageable, outputStream));

        return new StringWrapper(exportedUrl);
    }

    @GetMapping(value = "/queries/getTotalOutstandingRMAs")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "getTotalOutstandingRMAs")
    public Page<GetTotalOutstandingRmasResponse> executeGetTotalOutstandingRMAs(Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: getTotalOutstandingRMAs");
        Page<GetTotalOutstandingRmasResponse> _result = queryService.executeGetTotalOutstandingRMAs(pageable);
        LOGGER.debug("got the result for named query: getTotalOutstandingRMAs, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file url for query getTotalOutstandingRMAs")
    @PostMapping(value = "/queries/getTotalOutstandingRMAs/export")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportGetTotalOutstandingRMAs(@RequestBody ExportOptions exportOptions, Pageable pageable) {
        LOGGER.debug("Exporting named query: getTotalOutstandingRMAs");

        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = "getTotalOutstandingRMAs";
        }
        exportedFileName += exportOptions.getExportType().getExtension();

        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName,
                        outputStream -> queryService.exportGetTotalOutstandingRMAs( exportOptions, pageable, outputStream));

        return new StringWrapper(exportedUrl);
    }

    @GetMapping(value = "/queries/getDashboardMetrics")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "getDashboardMetrics")
    public Page<GetDashboardMetricsResponse> executeGetDashboardMetrics(Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: getDashboardMetrics");
        Page<GetDashboardMetricsResponse> _result = queryService.executeGetDashboardMetrics(pageable);
        LOGGER.debug("got the result for named query: getDashboardMetrics, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file url for query getDashboardMetrics")
    @PostMapping(value = "/queries/getDashboardMetrics/export")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportGetDashboardMetrics(@RequestBody ExportOptions exportOptions, Pageable pageable) {
        LOGGER.debug("Exporting named query: getDashboardMetrics");

        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = "getDashboardMetrics";
        }
        exportedFileName += exportOptions.getExportType().getExtension();

        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName,
                        outputStream -> queryService.exportGetDashboardMetrics( exportOptions, pageable, outputStream));

        return new StringWrapper(exportedUrl);
    }

    @GetMapping(value = "/queries/getTotalClosedRMAs")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "getTotalClosedRMAs")
    public Page<GetTotalClosedRmasResponse> executeGetTotalClosedRMAs(Pageable pageable, HttpServletRequest _request) {
        LOGGER.debug("Executing named query: getTotalClosedRMAs");
        Page<GetTotalClosedRmasResponse> _result = queryService.executeGetTotalClosedRMAs(pageable);
        LOGGER.debug("got the result for named query: getTotalClosedRMAs, result:{}", _result);
        return _result;
    }

    @ApiOperation(value = "Returns downloadable file url for query getTotalClosedRMAs")
    @PostMapping(value = "/queries/getTotalClosedRMAs/export")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportGetTotalClosedRMAs(@RequestBody ExportOptions exportOptions, Pageable pageable) {
        LOGGER.debug("Exporting named query: getTotalClosedRMAs");

        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = "getTotalClosedRMAs";
        }
        exportedFileName += exportOptions.getExportType().getExtension();

        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName,
                        outputStream -> queryService.exportGetTotalClosedRMAs( exportOptions, pageable, outputStream));

        return new StringWrapper(exportedUrl);
    }

}
