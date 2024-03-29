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

import com.wavemaker.servicetracker.supportdb.PartSale;


/**
 * ServiceImpl object for domain model class PartSale.
 *
 * @see PartSale
 */
@Service("SupportDB.PartSaleService")
@Validated
@EntityService(entityClass = PartSale.class, serviceId = "SupportDB")
public class PartSaleServiceImpl implements PartSaleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartSaleServiceImpl.class);


    @Autowired
    @Qualifier("SupportDB.PartSaleDao")
    private WMGenericDao<PartSale, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<PartSale, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "SupportDBTransactionManager")
    @Override
    public PartSale create(PartSale partSale) {
        LOGGER.debug("Creating a new PartSale with information: {}", partSale);

        PartSale partSaleCreated = this.wmGenericDao.create(partSale);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(partSaleCreated);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public PartSale getById(Integer partsaleId) {
        LOGGER.debug("Finding PartSale by id: {}", partsaleId);
        return this.wmGenericDao.findById(partsaleId);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public PartSale findById(Integer partsaleId) {
        LOGGER.debug("Finding PartSale by id: {}", partsaleId);
        try {
            return this.wmGenericDao.findById(partsaleId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No PartSale found with id: {}", partsaleId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public List<PartSale> findByMultipleIds(List<Integer> partsaleIds, boolean orderedReturn) {
        LOGGER.debug("Finding PartSales by ids: {}", partsaleIds);

        return this.wmGenericDao.findByMultipleIds(partsaleIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "SupportDBTransactionManager")
    @Override
    public PartSale update(PartSale partSale) {
        LOGGER.debug("Updating PartSale with information: {}", partSale);

        this.wmGenericDao.update(partSale);
        this.wmGenericDao.refresh(partSale);

        return partSale;
    }

    @Transactional(value = "SupportDBTransactionManager")
    @Override
    public PartSale partialUpdate(Integer partsaleId, Map<String, Object>partSalePatch) {
        LOGGER.debug("Partially Updating the PartSale with id: {}", partsaleId);

        PartSale partSale = getById(partsaleId);

        try {
            ObjectReader partSaleReader = this.objectMapper.reader().forType(PartSale.class).withValueToUpdate(partSale);
            partSale = partSaleReader.readValue(this.objectMapper.writeValueAsString(partSalePatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", partSalePatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        partSale = update(partSale);

        return partSale;
    }

    @Transactional(value = "SupportDBTransactionManager")
    @Override
    public PartSale delete(Integer partsaleId) {
        LOGGER.debug("Deleting PartSale with id: {}", partsaleId);
        PartSale deleted = this.wmGenericDao.findById(partsaleId);
        if (deleted == null) {
            LOGGER.debug("No PartSale found with id: {}", partsaleId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), PartSale.class.getSimpleName(), partsaleId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "SupportDBTransactionManager")
    @Override
    public void delete(PartSale partSale) {
        LOGGER.debug("Deleting PartSale with {}", partSale);
        this.wmGenericDao.delete(partSale);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public Page<PartSale> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all PartSales");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public Page<PartSale> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all PartSales");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service SupportDB for table PartSale to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service SupportDB for table PartSale to {} format", options.getExportType());
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
