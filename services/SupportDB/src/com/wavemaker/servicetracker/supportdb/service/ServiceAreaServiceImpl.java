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
import org.springframework.context.annotation.Lazy;
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

import com.wavemaker.servicetracker.supportdb.Rma;
import com.wavemaker.servicetracker.supportdb.ServiceArea;


/**
 * ServiceImpl object for domain model class ServiceArea.
 *
 * @see ServiceArea
 */
@Service("SupportDB.ServiceAreaService")
@Validated
@EntityService(entityClass = ServiceArea.class, serviceId = "SupportDB")
public class ServiceAreaServiceImpl implements ServiceAreaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAreaServiceImpl.class);

    @Lazy
    @Autowired
    @Qualifier("SupportDB.RmaService")
    private RmaService rmaService;

    @Autowired
    @Qualifier("SupportDB.ServiceAreaDao")
    private WMGenericDao<ServiceArea, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<ServiceArea, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "SupportDBTransactionManager")
    @Override
    public ServiceArea create(ServiceArea serviceArea) {
        LOGGER.debug("Creating a new ServiceArea with information: {}", serviceArea);

        ServiceArea serviceAreaCreated = this.wmGenericDao.create(serviceArea);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(serviceAreaCreated);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public ServiceArea getById(Integer serviceareaId) {
        LOGGER.debug("Finding ServiceArea by id: {}", serviceareaId);
        return this.wmGenericDao.findById(serviceareaId);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public ServiceArea findById(Integer serviceareaId) {
        LOGGER.debug("Finding ServiceArea by id: {}", serviceareaId);
        try {
            return this.wmGenericDao.findById(serviceareaId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No ServiceArea found with id: {}", serviceareaId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public List<ServiceArea> findByMultipleIds(List<Integer> serviceareaIds, boolean orderedReturn) {
        LOGGER.debug("Finding ServiceAreas by ids: {}", serviceareaIds);

        return this.wmGenericDao.findByMultipleIds(serviceareaIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "SupportDBTransactionManager")
    @Override
    public ServiceArea update(ServiceArea serviceArea) {
        LOGGER.debug("Updating ServiceArea with information: {}", serviceArea);

        this.wmGenericDao.update(serviceArea);
        this.wmGenericDao.refresh(serviceArea);

        return serviceArea;
    }

    @Transactional(value = "SupportDBTransactionManager")
    @Override
    public ServiceArea partialUpdate(Integer serviceareaId, Map<String, Object>serviceAreaPatch) {
        LOGGER.debug("Partially Updating the ServiceArea with id: {}", serviceareaId);

        ServiceArea serviceArea = getById(serviceareaId);

        try {
            ObjectReader serviceAreaReader = this.objectMapper.reader().forType(ServiceArea.class).withValueToUpdate(serviceArea);
            serviceArea = serviceAreaReader.readValue(this.objectMapper.writeValueAsString(serviceAreaPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", serviceAreaPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        serviceArea = update(serviceArea);

        return serviceArea;
    }

    @Transactional(value = "SupportDBTransactionManager")
    @Override
    public ServiceArea delete(Integer serviceareaId) {
        LOGGER.debug("Deleting ServiceArea with id: {}", serviceareaId);
        ServiceArea deleted = this.wmGenericDao.findById(serviceareaId);
        if (deleted == null) {
            LOGGER.debug("No ServiceArea found with id: {}", serviceareaId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), ServiceArea.class.getSimpleName(), serviceareaId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "SupportDBTransactionManager")
    @Override
    public void delete(ServiceArea serviceArea) {
        LOGGER.debug("Deleting ServiceArea with {}", serviceArea);
        this.wmGenericDao.delete(serviceArea);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public Page<ServiceArea> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all ServiceAreas");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public Page<ServiceArea> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all ServiceAreas");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service SupportDB for table ServiceArea to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service SupportDB for table ServiceArea to {} format", options.getExportType());
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

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public Page<Rma> findAssociatedRmas(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated rmas");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("serviceArea.id = '" + id + "'");

        return rmaService.findAll(queryBuilder.toString(), pageable);
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
