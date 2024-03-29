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
import com.wavemaker.servicetracker.supportdb.service.CustomerSatisfactionService;


/**
 * Controller object for domain model class CustomerSatisfaction.
 * @see CustomerSatisfaction
 */
@RestController("SupportDB.CustomerSatisfactionController")
@Api(value = "CustomerSatisfactionController", description = "Exposes APIs to work with CustomerSatisfaction resource.")
@RequestMapping("/SupportDB/CustomerSatisfaction")
public class CustomerSatisfactionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerSatisfactionController.class);

    @Autowired
	@Qualifier("SupportDB.CustomerSatisfactionService")
	private CustomerSatisfactionService customerSatisfactionService;

	@Autowired
	private ExportedFileManager exportedFileManager;

	@ApiOperation(value = "Creates a new CustomerSatisfaction instance.")
    @PostMapping
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public CustomerSatisfaction createCustomerSatisfaction(@RequestBody CustomerSatisfaction customerSatisfaction) {
		LOGGER.debug("Create CustomerSatisfaction with information: {}" , customerSatisfaction);

		customerSatisfaction = customerSatisfactionService.create(customerSatisfaction);
		LOGGER.debug("Created CustomerSatisfaction with information: {}" , customerSatisfaction);

	    return customerSatisfaction;
	}

    @ApiOperation(value = "Returns the CustomerSatisfaction instance associated with the given id.")
    @GetMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public CustomerSatisfaction getCustomerSatisfaction(@PathVariable("id") Integer id) {
        LOGGER.debug("Getting CustomerSatisfaction with id: {}" , id);

        CustomerSatisfaction foundCustomerSatisfaction = customerSatisfactionService.getById(id);
        LOGGER.debug("CustomerSatisfaction details with id: {}" , foundCustomerSatisfaction);

        return foundCustomerSatisfaction;
    }

    @ApiOperation(value = "Updates the CustomerSatisfaction instance associated with the given id.")
    @PutMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public CustomerSatisfaction editCustomerSatisfaction(@PathVariable("id") Integer id, @RequestBody CustomerSatisfaction customerSatisfaction) {
        LOGGER.debug("Editing CustomerSatisfaction with id: {}" , customerSatisfaction.getId());

        customerSatisfaction.setId(id);
        customerSatisfaction = customerSatisfactionService.update(customerSatisfaction);
        LOGGER.debug("CustomerSatisfaction details with id: {}" , customerSatisfaction);

        return customerSatisfaction;
    }
    
    @ApiOperation(value = "Partially updates the CustomerSatisfaction instance associated with the given id.")
    @PatchMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public CustomerSatisfaction patchCustomerSatisfaction(@PathVariable("id") Integer id, @RequestBody @MapTo(CustomerSatisfaction.class) Map<String, Object> customerSatisfactionPatch) {
        LOGGER.debug("Partially updating CustomerSatisfaction with id: {}" , id);

        CustomerSatisfaction customerSatisfaction = customerSatisfactionService.partialUpdate(id, customerSatisfactionPatch);
        LOGGER.debug("CustomerSatisfaction details after partial update: {}" , customerSatisfaction);

        return customerSatisfaction;
    }

    @ApiOperation(value = "Deletes the CustomerSatisfaction instance associated with the given id.")
    @DeleteMapping(value = "/{id:.+}")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteCustomerSatisfaction(@PathVariable("id") Integer id) {
        LOGGER.debug("Deleting CustomerSatisfaction with id: {}" , id);

        CustomerSatisfaction deletedCustomerSatisfaction = customerSatisfactionService.delete(id);

        return deletedCustomerSatisfaction != null;
    }

    /**
     * @deprecated Use {@link #findCustomerSatisfactions(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of CustomerSatisfaction instances matching the search criteria.")
    @PostMapping(value = "/search")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<CustomerSatisfaction> searchCustomerSatisfactionsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering CustomerSatisfactions list by query filter:{}", (Object) queryFilters);
        return customerSatisfactionService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of CustomerSatisfaction instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @GetMapping
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<CustomerSatisfaction> findCustomerSatisfactions(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering CustomerSatisfactions list by filter:", query);
        return customerSatisfactionService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of CustomerSatisfaction instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @PostMapping(value="/filter", consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<CustomerSatisfaction> filterCustomerSatisfactions(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering CustomerSatisfactions list by filter", query);
        return customerSatisfactionService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param.")
    @GetMapping(value = "/export/{exportType}", produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportCustomerSatisfactions(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return customerSatisfactionService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns a URL to download a file for the data matching the optional query (q) request param and the required fields provided in the Export Options.") 
    @PostMapping(value = "/export", consumes = "application/json")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public StringWrapper exportCustomerSatisfactionsAndGetURL(@RequestBody DataExportOptions exportOptions, Pageable pageable) {
        String exportedFileName = exportOptions.getFileName();
        if(exportedFileName == null || exportedFileName.isEmpty()) {
            exportedFileName = CustomerSatisfaction.class.getSimpleName();
        }
        exportedFileName += exportOptions.getExportType().getExtension();
        String exportedUrl = exportedFileManager.registerAndGetURL(exportedFileName, outputStream -> customerSatisfactionService.export(exportOptions, pageable, outputStream));
        return new StringWrapper(exportedUrl);
    }

	@ApiOperation(value = "Returns the total count of CustomerSatisfaction instances matching the optional query (q) request param.")
	@GetMapping(value = "/count")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countCustomerSatisfactions( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting CustomerSatisfactions");
		return customerSatisfactionService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@PostMapping(value = "/aggregations")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getCustomerSatisfactionAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return customerSatisfactionService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service CustomerSatisfactionService instance
	 */
	protected void setCustomerSatisfactionService(CustomerSatisfactionService service) {
		this.customerSatisfactionService = service;
	}

}
