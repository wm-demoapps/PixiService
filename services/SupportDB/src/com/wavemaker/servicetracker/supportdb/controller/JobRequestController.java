/*Copyright (c) 2022-2023 wavemaker.com All Rights Reserved.This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.wavemaker.servicetracker.supportdb.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.commons.wrapper.StringWrapper;
import com.wavemaker.runtime.commons.file.manager.ExportedFileManager;
import com.wavemaker.runtime.commons.file.model.Downloadable;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.tools.api.core.annotations.MapTo;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.wavemaker.servicetracker.supportdb.JobRequest;
import com.wavemaker.servicetracker.supportdb.Rma;
import com.wavemaker.servicetracker.supportdb.service.JobRequestService;


/**
 * Controller object for domain model class JobRequest.
 * @see JobRequest
 */
@RestController("SupportDB.JobRequestController")
@Api(value = "JobRequestController", description = "Exposes APIs to work with JobRequest resource.")
@RequestMapping("/SupportDB/JobRequest")
public class JobRequestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobRequestController.class);

    @Autowired
	@Qualifier("SupportDB.JobRequestService")
	private JobRequestService jobRequestService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new JobRequest instance.")
    @PostMapping
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public JobRequest createJobRequest(@RequestBody JobRequest jobRequest) {
		LOGGER.debug("Create JobRequest with information: {}" , jobRequest);

		jobRequest = jobRequestService.create(jobRequest);
		LOGGER.debug("Created JobRequest with information: {}" , jobRequest);

	    return jobRequest;
	}

    @ApiOperation(value = "Returns the JobRequest instance associated with the given id.")
    @GetMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public JobRequest getJobRequest(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting JobRequest with id: {}" , id);

        JobRequest foundJobRequest = jobRequestService.getById(id);
        LOGGER.debug("JobRequest details with id: {}" , foundJobRequest);

        return foundJobRequest;
    }

    @ApiOperation(value = "Updates the JobRequest instance associated with the given id.")
    @PutMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public JobRequest editJobRequest(@PathVariable("id") Integer id, @RequestBody JobRequest jobRequest) {
        LOGGER.debug("Editing JobRequest with id: {}" , jobRequest.getId());

        jobRequest.setId(id);
        jobRequest = jobRequestService.update(jobRequest);
        LOGGER.debug("JobRequest details with id: {}" , jobRequest);

        return jobRequest;
    }
    
    @ApiOperation(value = "Partially updates the JobRequest instance associated with the given id.")
    @PatchMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public JobRequest patchJobRequest(@PathVariable("id") Integer id, @RequestBody @MapTo(JobRequest.class) Map<String, Object> jobRequestPatch) {
        LOGGER.debug("Partially updating JobRequest with id: {}" , id);

        JobRequest jobRequest = jobRequestService.partialUpdate(id, jobRequestPatch);
        LOGGER.debug("JobRequest details after partial update: {}" , jobRequest);

        return jobRequest;
    }

    @ApiOperation(value = "Deletes the JobRequest instance associated with the given id.")
    @DeleteMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteJobRequest(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting JobRequest with id: {}" , id);

        JobRequest deletedJobRequest = jobRequestService.delete(id);

        return deletedJobRequest != null;
    }

    /**
     * @deprecated Use {@link #findJobRequests(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of JobRequest instances matching the search criteria.")
    @PostMapping(value = "/search")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<JobRequest> searchJobRequestsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering JobRequests list by query filter:{}", (Object) queryFilters);
        return jobRequestService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of JobRequest instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @GetMapping
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<JobRequest> findJobRequests(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering JobRequests list by filter:", query);
        return jobRequestService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of JobRequest instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @PostMapping(value="/filter", consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<JobRequest> filterJobRequests(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering JobRequests list by filter", query);
        return jobRequestService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param.")
    @GetMapping(value = "/export/{exportType}", produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportJobRequests(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return jobRequestService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @PostMapping(value = "/export", consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportJobRequestsAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = JobRequest.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> jobRequestService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of JobRequest instances matching the optional query (q) request param.")
	@GetMapping(value = "/count")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countJobRequests( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting JobRequests");
		return jobRequestService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@PostMapping(value = "/aggregations")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getJobRequestAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return jobRequestService.getAggregatedValues(aggregationInfo, pageable);
    }

    @GetMapping(value="/{id:.+}/rmas")
    @ApiOperation(value = "Gets the rmas instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Rma> findAssociatedRmas(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated rmas");
        return jobRequestService.findAssociatedRmas(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service JobRequestService instance
	 */
	protected void setJobRequestService(JobRequestService service) {
		this.jobRequestService = service;
	}

}
