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

import com.wavemaker.servicetracker.supportdb.PartSale;
import com.wavemaker.servicetracker.supportdb.RepairActivity;
import com.wavemaker.servicetracker.supportdb.service.RepairActivityService;


/**
 * Controller object for domain model class RepairActivity.
 * @see RepairActivity
 */
@RestController("SupportDB.RepairActivityController")
@Api(value = "RepairActivityController", description = "Exposes APIs to work with RepairActivity resource.")
@RequestMapping("/SupportDB/RepairActivity")
public class RepairActivityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepairActivityController.class);

    @Autowired
	@Qualifier("SupportDB.RepairActivityService")
	private RepairActivityService repairActivityService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new RepairActivity instance.")
    @PostMapping
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public RepairActivity createRepairActivity(@RequestBody RepairActivity repairActivity) {
		LOGGER.debug("Create RepairActivity with information: {}" , repairActivity);

		repairActivity = repairActivityService.create(repairActivity);
		LOGGER.debug("Created RepairActivity with information: {}" , repairActivity);

	    return repairActivity;
	}

    @ApiOperation(value = "Returns the RepairActivity instance associated with the given id.")
    @GetMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public RepairActivity getRepairActivity(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting RepairActivity with id: {}" , id);

        RepairActivity foundRepairActivity = repairActivityService.getById(id);
        LOGGER.debug("RepairActivity details with id: {}" , foundRepairActivity);

        return foundRepairActivity;
    }

    @ApiOperation(value = "Updates the RepairActivity instance associated with the given id.")
    @PutMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public RepairActivity editRepairActivity(@PathVariable("id") Integer id, @RequestBody RepairActivity repairActivity) {
        LOGGER.debug("Editing RepairActivity with id: {}" , repairActivity.getId());

        repairActivity.setId(id);
        repairActivity = repairActivityService.update(repairActivity);
        LOGGER.debug("RepairActivity details with id: {}" , repairActivity);

        return repairActivity;
    }
    
    @ApiOperation(value = "Partially updates the RepairActivity instance associated with the given id.")
    @PatchMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public RepairActivity patchRepairActivity(@PathVariable("id") Integer id, @RequestBody @MapTo(RepairActivity.class) Map<String, Object> repairActivityPatch) {
        LOGGER.debug("Partially updating RepairActivity with id: {}" , id);

        RepairActivity repairActivity = repairActivityService.partialUpdate(id, repairActivityPatch);
        LOGGER.debug("RepairActivity details after partial update: {}" , repairActivity);

        return repairActivity;
    }

    @ApiOperation(value = "Deletes the RepairActivity instance associated with the given id.")
    @DeleteMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteRepairActivity(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting RepairActivity with id: {}" , id);

        RepairActivity deletedRepairActivity = repairActivityService.delete(id);

        return deletedRepairActivity != null;
    }

    /**
     * @deprecated Use {@link #findRepairActivities(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of RepairActivity instances matching the search criteria.")
    @PostMapping(value = "/search")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<RepairActivity> searchRepairActivitiesByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering RepairActivities list by query filter:{}", (Object) queryFilters);
        return repairActivityService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of RepairActivity instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @GetMapping
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<RepairActivity> findRepairActivities(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering RepairActivities list by filter:", query);
        return repairActivityService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of RepairActivity instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @PostMapping(value="/filter", consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<RepairActivity> filterRepairActivities(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering RepairActivities list by filter", query);
        return repairActivityService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param.")
    @GetMapping(value = "/export/{exportType}", produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportRepairActivities(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return repairActivityService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @PostMapping(value = "/export", consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportRepairActivitiesAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = RepairActivity.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> repairActivityService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of RepairActivity instances matching the optional query (q) request param.")
	@GetMapping(value = "/count")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countRepairActivities( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting RepairActivities");
		return repairActivityService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@PostMapping(value = "/aggregations")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getRepairActivityAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return repairActivityService.getAggregatedValues(aggregationInfo, pageable);
    }

    @GetMapping(value="/{id:.+}/partSales")
    @ApiOperation(value = "Gets the partSales instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<PartSale> findAssociatedPartSales(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated partSales");
        return repairActivityService.findAssociatedPartSales(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service RepairActivityService instance
	 */
	protected void setRepairActivityService(RepairActivityService service) {
		this.repairActivityService = service;
	}

}
