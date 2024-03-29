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

import com.wavemaker.servicetracker.supportdb.Part;
import com.wavemaker.servicetracker.supportdb.PartStockType;


/**
 * ServiceImpl object for domain model class PartStockType.
 *
 * @see PartStockType
 */
@Service("SupportDB.PartStockTypeService")
@Validated
@EntityService(entityClass = PartStockType.class, serviceId = "SupportDB")
public class PartStockTypeServiceImpl implements PartStockTypeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartStockTypeServiceImpl.class);

    @Lazy
    @Autowired
    @Qualifier("SupportDB.PartService")
    private PartService partService;

    @Autowired
    @Qualifier("SupportDB.PartStockTypeDao")
    private WMGenericDao<PartStockType, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<PartStockType, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "SupportDBTransactionManager")
    @Override
    public PartStockType create(PartStockType partStockType) {
        LOGGER.debug("Creating a new PartStockType with information: {}", partStockType);

        PartStockType partStockTypeCreated = this.wmGenericDao.create(partStockType);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(partStockTypeCreated);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public PartStockType getById(Integer partstocktypeId) {
        LOGGER.debug("Finding PartStockType by id: {}", partstocktypeId);
        return this.wmGenericDao.findById(partstocktypeId);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public PartStockType findById(Integer partstocktypeId) {
        LOGGER.debug("Finding PartStockType by id: {}", partstocktypeId);
        try {
            return this.wmGenericDao.findById(partstocktypeId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No PartStockType found with id: {}", partstocktypeId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public List<PartStockType> findByMultipleIds(List<Integer> partstocktypeIds, boolean orderedReturn) {
        LOGGER.debug("Finding PartStockTypes by ids: {}", partstocktypeIds);

        return this.wmGenericDao.findByMultipleIds(partstocktypeIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "SupportDBTransactionManager")
    @Override
    public PartStockType update(PartStockType partStockType) {
        LOGGER.debug("Updating PartStockType with information: {}", partStockType);

        this.wmGenericDao.update(partStockType);
        this.wmGenericDao.refresh(partStockType);

        return partStockType;
    }

    @Transactional(value = "SupportDBTransactionManager")
    @Override
    public PartStockType partialUpdate(Integer partstocktypeId, Map<String, Object>partStockTypePatch) {
        LOGGER.debug("Partially Updating the PartStockType with id: {}", partstocktypeId);

        PartStockType partStockType = getById(partstocktypeId);

        try {
            ObjectReader partStockTypeReader = this.objectMapper.reader().forType(PartStockType.class).withValueToUpdate(partStockType);
            partStockType = partStockTypeReader.readValue(this.objectMapper.writeValueAsString(partStockTypePatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", partStockTypePatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        partStockType = update(partStockType);

        return partStockType;
    }

    @Transactional(value = "SupportDBTransactionManager")
    @Override
    public PartStockType delete(Integer partstocktypeId) {
        LOGGER.debug("Deleting PartStockType with id: {}", partstocktypeId);
        PartStockType deleted = this.wmGenericDao.findById(partstocktypeId);
        if (deleted == null) {
            LOGGER.debug("No PartStockType found with id: {}", partstocktypeId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), PartStockType.class.getSimpleName(), partstocktypeId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "SupportDBTransactionManager")
    @Override
    public void delete(PartStockType partStockType) {
        LOGGER.debug("Deleting PartStockType with {}", partStockType);
        this.wmGenericDao.delete(partStockType);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public Page<PartStockType> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all PartStockTypes");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public Page<PartStockType> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all PartStockTypes");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service SupportDB for table PartStockType to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service SupportDB for table PartStockType to {} format", options.getExportType());
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
    public Page<Part> findAssociatedParts(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated parts");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("partStockType.id = '" + id + "'");

        return partService.findAll(queryBuilder.toString(), pageable);
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service PartService instance
     */
    protected void setPartService(PartService service) {
        this.partService = service;
    }

}
