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

import com.wavemaker.servicetracker.supportdb.Engineer;
import com.wavemaker.servicetracker.supportdb.Rma;
import com.wavemaker.servicetracker.supportdb.ServiceType;


/**
 * ServiceImpl object for domain model class ServiceType.
 *
 * @see ServiceType
 */
@Service("SupportDB.ServiceTypeService")
@Validated
@EntityService(entityClass = ServiceType.class, serviceId = "SupportDB")
public class ServiceTypeServiceImpl implements ServiceTypeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceTypeServiceImpl.class);

    @Lazy
    @Autowired
    @Qualifier("SupportDB.RmaService")
    private RmaService rmaService;

    @Lazy
    @Autowired
    @Qualifier("SupportDB.EngineerService")
    private EngineerService engineerService;

    @Autowired
    @Qualifier("SupportDB.ServiceTypeDao")
    private WMGenericDao<ServiceType, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<ServiceType, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "SupportDBTransactionManager")
    @Override
    public ServiceType create(ServiceType serviceType) {
        LOGGER.debug("Creating a new ServiceType with information: {}", serviceType);

        ServiceType serviceTypeCreated = this.wmGenericDao.create(serviceType);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(serviceTypeCreated);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public ServiceType getById(Integer servicetypeId) {
        LOGGER.debug("Finding ServiceType by id: {}", servicetypeId);
        return this.wmGenericDao.findById(servicetypeId);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public ServiceType findById(Integer servicetypeId) {
        LOGGER.debug("Finding ServiceType by id: {}", servicetypeId);
        try {
            return this.wmGenericDao.findById(servicetypeId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No ServiceType found with id: {}", servicetypeId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public List<ServiceType> findByMultipleIds(List<Integer> servicetypeIds, boolean orderedReturn) {
        LOGGER.debug("Finding ServiceTypes by ids: {}", servicetypeIds);

        return this.wmGenericDao.findByMultipleIds(servicetypeIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "SupportDBTransactionManager")
    @Override
    public ServiceType update(ServiceType serviceType) {
        LOGGER.debug("Updating ServiceType with information: {}", serviceType);

        this.wmGenericDao.update(serviceType);
        this.wmGenericDao.refresh(serviceType);

        return serviceType;
    }

    @Transactional(value = "SupportDBTransactionManager")
    @Override
    public ServiceType partialUpdate(Integer servicetypeId, Map<String, Object>serviceTypePatch) {
        LOGGER.debug("Partially Updating the ServiceType with id: {}", servicetypeId);

        ServiceType serviceType = getById(servicetypeId);

        try {
            ObjectReader serviceTypeReader = this.objectMapper.reader().forType(ServiceType.class).withValueToUpdate(serviceType);
            serviceType = serviceTypeReader.readValue(this.objectMapper.writeValueAsString(serviceTypePatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", serviceTypePatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        serviceType = update(serviceType);

        return serviceType;
    }

    @Transactional(value = "SupportDBTransactionManager")
    @Override
    public ServiceType delete(Integer servicetypeId) {
        LOGGER.debug("Deleting ServiceType with id: {}", servicetypeId);
        ServiceType deleted = this.wmGenericDao.findById(servicetypeId);
        if (deleted == null) {
            LOGGER.debug("No ServiceType found with id: {}", servicetypeId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), ServiceType.class.getSimpleName(), servicetypeId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "SupportDBTransactionManager")
    @Override
    public void delete(ServiceType serviceType) {
        LOGGER.debug("Deleting ServiceType with {}", serviceType);
        this.wmGenericDao.delete(serviceType);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public Page<ServiceType> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all ServiceTypes");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public Page<ServiceType> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all ServiceTypes");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service SupportDB for table ServiceType to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service SupportDB for table ServiceType to {} format", options.getExportType());
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
    public Page<Engineer> findAssociatedEngineers(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated engineers");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("serviceType.id = '" + id + "'");

        return engineerService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public Page<Rma> findAssociatedRmas(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated rmas");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("serviceType.id = '" + id + "'");

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

    /**
     * This setter method should only be used by unit tests
     *
     * @param service EngineerService instance
     */
    protected void setEngineerService(EngineerService service) {
        this.engineerService = service;
    }

}
