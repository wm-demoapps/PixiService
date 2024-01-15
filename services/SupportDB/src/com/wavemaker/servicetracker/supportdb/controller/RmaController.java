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

import com.wavemaker.servicetracker.supportdb.CustomerSatisfaction;
import com.wavemaker.servicetracker.supportdb.RepairActivity;
import com.wavemaker.servicetracker.supportdb.Rma;
import com.wavemaker.servicetracker.supportdb.service.RmaService;


/**
 * Controller object for domain model class Rma.
 * @see Rma
 */
@RestController("SupportDB.RmaController")
@Api(value = "RmaController", description = "Exposes APIs to work with Rma resource.")
@RequestMapping("/SupportDB/Rma")
public class RmaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RmaController.class);

    @Autowired
	@Qualifier("SupportDB.RmaService")
	private RmaService rmaService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new Rma instance.")
    @PostMapping
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Rma createRma(@RequestBody Rma rma) {
		LOGGER.debug("Create Rma with information: {}" , rma);

		rma = rmaService.create(rma);
		LOGGER.debug("Created Rma with information: {}" , rma);

	    return rma;
	}

    @ApiOperation(value = "Returns the Rma instance associated with the given id.")
    @GetMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Rma getRma(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting Rma with id: {}" , id);

        Rma foundRma = rmaService.getById(id);
        LOGGER.debug("Rma details with id: {}" , foundRma);

        return foundRma;
    }

    @ApiOperation(value = "Updates the Rma instance associated with the given id.")
    @PutMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Rma editRma(@PathVariable("id") Integer id, @RequestBody Rma rma) {
        LOGGER.debug("Editing Rma with id: {}" , rma.getId());

        rma.setId(id);
        rma = rmaService.update(rma);
        LOGGER.debug("Rma details with id: {}" , rma);

        return rma;
    }
    
    @ApiOperation(value = "Partially updates the Rma instance associated with the given id.")
    @PatchMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Rma patchRma(@PathVariable("id") Integer id, @RequestBody @MapTo(Rma.class) Map<String, Object> rmaPatch) {
        LOGGER.debug("Partially updating Rma with id: {}" , id);

        Rma rma = rmaService.partialUpdate(id, rmaPatch);
        LOGGER.debug("Rma details after partial update: {}" , rma);

        return rma;
    }

    @ApiOperation(value = "Deletes the Rma instance associated with the given id.")
    @DeleteMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteRma(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting Rma with id: {}" , id);

        Rma deletedRma = rmaService.delete(id);

        return deletedRma != null;
    }

    /**
     * @deprecated Use {@link #findRmas(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Rma instances matching the search criteria.")
    @PostMapping(value = "/search")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Rma> searchRmasByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Rmas list by query filter:{}", (Object) queryFilters);
        return rmaService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Rma instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @GetMapping
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Rma> findRmas(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Rmas list by filter:", query);
        return rmaService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Rma instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @PostMapping(value="/filter", consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Rma> filterRmas(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Rmas list by filter", query);
        return rmaService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param.")
    @GetMapping(value = "/export/{exportType}", produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportRmas(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return rmaService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @PostMapping(value = "/export", consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportRmasAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = Rma.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> rmaService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of Rma instances matching the optional query (q) request param.")
	@GetMapping(value = "/count")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countRmas( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Rmas");
		return rmaService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@PostMapping(value = "/aggregations")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getRmaAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return rmaService.getAggregatedValues(aggregationInfo, pageable);
    }

    @GetMapping(value="/{id:.+}/customerSatisfactions")
    @ApiOperation(value = "Gets the customerSatisfactions instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<CustomerSatisfaction> findAssociatedCustomerSatisfactions(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated customerSatisfactions");
        return rmaService.findAssociatedCustomerSatisfactions(id, pageable);
    }

    @GetMapping(value="/{id:.+}/repairActivities")
    @ApiOperation(value = "Gets the repairActivities instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<RepairActivity> findAssociatedRepairActivities(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated repairActivities");
        return rmaService.findAssociatedRepairActivities(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service RmaService instance
	 */
	protected void setRmaService(RmaService service) {
		this.rmaService = service;
	}

}