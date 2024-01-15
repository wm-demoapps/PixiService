/*Copyright (c) 2022-2023 wavemaker.com All Rights Reserved.This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.wavemaker.servicetracker.supportdb.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.wavemaker.commons.InvalidInputException;
import com.wavemaker.commons.MessageResource;
import com.wavemaker.runtime.commons.file.model.Downloadable;
import com.wavemaker.runtime.data.annotations.EntityService;
import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;

import com.wavemaker.servicetracker.supportdb.CustomerSatisfaction;


/**
 * ServiceImpl object for domain model class CustomerSatisfaction.
 *
 * @see CustomerSatisfaction
 */
@Service("SupportDB.CustomerSatisfactionService")
@Validated
@EntityService(entityClass = CustomerSatisfaction.class, serviceId = "SupportDB")
public class CustomerSatisfactionServiceImpl implements CustomerSatisfactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerSatisfactionServiceImpl.class);


    @Autowired
    @Qualifier("SupportDB.CustomerSatisfactionDao")
    private WMGenericDao<CustomerSatisfaction, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<CustomerSatisfaction, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "SupportDBTransactionManager")
    @Override
    public CustomerSatisfaction create(CustomerSatisfaction customerSatisfaction) {
        LOGGER.debug("Creating a new CustomerSatisfaction with information: {}", customerSatisfaction);

        CustomerSatisfaction customerSatisfactionCreated = this.wmGenericDao.create(customerSatisfaction);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(customerSatisfactionCreated);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public CustomerSatisfaction getById(Integer customersatisfactionId) {
        LOGGER.debug("Finding CustomerSatisfaction by id: {}", customersatisfactionId);
        return this.wmGenericDao.findById(customersatisfactionId);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public CustomerSatisfaction findById(Integer customersatisfactionId) {
        LOGGER.debug("Finding CustomerSatisfaction by id: {}", customersatisfactionId);
        try {
            return this.wmGenericDao.findById(customersatisfactionId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No CustomerSatisfaction found with id: {}", customersatisfactionId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public List<CustomerSatisfaction> findByMultipleIds(List<Integer> customersatisfactionIds, boolean orderedReturn) {
        LOGGER.debug("Finding CustomerSatisfactions by ids: {}", customersatisfactionIds);

        return this.wmGenericDao.findByMultipleIds(customersatisfactionIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "SupportDBTransactionManager")
    @Override
    public CustomerSatisfaction update(CustomerSatisfaction customerSatisfaction) {
        LOGGER.debug("Updating CustomerSatisfaction with information: {}", customerSatisfaction);

        this.wmGenericDao.update(customerSatisfaction);
        this.wmGenericDao.refresh(customerSatisfaction);

        return customerSatisfaction;
    }

    @Transactional(value = "SupportDBTransactionManager")
    @Override
    public CustomerSatisfaction partialUpdate(Integer customersatisfactionId, Map<String, Object>customerSatisfactionPatch) {
        LOGGER.debug("Partially Updating the CustomerSatisfaction with id: {}", customersatisfactionId);

        CustomerSatisfaction customerSatisfaction = getById(customersatisfactionId);

        try {
            ObjectReader customerSatisfactionReader = this.objectMapper.reader().forType(CustomerSatisfaction.class).withValueToUpdate(customerSatisfaction);
            customerSatisfaction = customerSatisfactionReader.readValue(this.objectMapper.writeValueAsString(customerSatisfactionPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", customerSatisfactionPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        customerSatisfaction = update(customerSatisfaction);

        return customerSatisfaction;
    }

    @Transactional(value = "SupportDBTransactionManager")
    @Override
    public CustomerSatisfaction delete(Integer customersatisfactionId) {
        LOGGER.debug("Deleting CustomerSatisfaction with id: {}", customersatisfactionId);
        CustomerSatisfaction deleted = this.wmGenericDao.findById(customersatisfactionId);
        if (deleted == null) {
            LOGGER.debug("No CustomerSatisfaction found with id: {}", customersatisfactionId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), CustomerSatisfaction.class.getSimpleName(), customersatisfactionId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "SupportDBTransactionManager")
    @Override
    public void delete(CustomerSatisfaction customerSatisfaction) {
        LOGGER.debug("Deleting CustomerSatisfaction with {}", customerSatisfaction);
        this.wmGenericDao.delete(customerSatisfaction);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public Page<CustomerSatisfaction> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all CustomerSatisfactions");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public Page<CustomerSatisfaction> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all CustomerSatisfactions");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service SupportDB for table CustomerSatisfaction to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service SupportDB for table CustomerSatisfaction to {} format", options.getExportType());
        this.wmGenericDao.export(options, pageable, outputStream);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }



}
