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

import com.wavemaker.servicetracker.supportdb.Customer;
import com.wavemaker.servicetracker.supportdb.ProductSerial;


/**
 * ServiceImpl object for domain model class Customer.
 *
 * @see Customer
 */
@Service("SupportDB.CustomerService")
@Validated
@EntityService(entityClass = Customer.class, serviceId = "SupportDB")
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Lazy
    @Autowired
    @Qualifier("SupportDB.ProductSerialService")
    private ProductSerialService productSerialService;

    @Autowired
    @Qualifier("SupportDB.CustomerDao")
    private WMGenericDao<Customer, Integer> wmGenericDao;

    @Autowired
    @Qualifier("wmAppObjectMapper")
    private ObjectMapper objectMapper;


    public void setWMGenericDao(WMGenericDao<Customer, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "SupportDBTransactionManager")
    @Override
    public Customer create(Customer customer) {
        LOGGER.debug("Creating a new Customer with information: {}", customer);

        Customer customerCreated = this.wmGenericDao.create(customer);
        // reloading object from database to get database defined & server defined values.
        return this.wmGenericDao.refresh(customerCreated);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public Customer getById(Integer customerId) {
        LOGGER.debug("Finding Customer by id: {}", customerId);
        return this.wmGenericDao.findById(customerId);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public Customer findById(Integer customerId) {
        LOGGER.debug("Finding Customer by id: {}", customerId);
        try {
            return this.wmGenericDao.findById(customerId);
        } catch (EntityNotFoundException ex) {
            LOGGER.debug("No Customer found with id: {}", customerId, ex);
            return null;
        }
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public List<Customer> findByMultipleIds(List<Integer> customerIds, boolean orderedReturn) {
        LOGGER.debug("Finding Customers by ids: {}", customerIds);

        return this.wmGenericDao.findByMultipleIds(customerIds, orderedReturn);
    }


    @Transactional(rollbackFor = EntityNotFoundException.class, value = "SupportDBTransactionManager")
    @Override
    public Customer update(Customer customer) {
        LOGGER.debug("Updating Customer with information: {}", customer);

        this.wmGenericDao.update(customer);
        this.wmGenericDao.refresh(customer);

        return customer;
    }

    @Transactional(value = "SupportDBTransactionManager")
    @Override
    public Customer partialUpdate(Integer customerId, Map<String, Object>customerPatch) {
        LOGGER.debug("Partially Updating the Customer with id: {}", customerId);

        Customer customer = getById(customerId);

        try {
            ObjectReader customerReader = this.objectMapper.reader().forType(Customer.class).withValueToUpdate(customer);
            customer = customerReader.readValue(this.objectMapper.writeValueAsString(customerPatch));
        } catch (IOException ex) {
            LOGGER.debug("There was a problem in applying the patch: {}", customerPatch, ex);
            throw new InvalidInputException("Could not apply patch",ex);
        }

        customer = update(customer);

        return customer;
    }

    @Transactional(value = "SupportDBTransactionManager")
    @Override
    public Customer delete(Integer customerId) {
        LOGGER.debug("Deleting Customer with id: {}", customerId);
        Customer deleted = this.wmGenericDao.findById(customerId);
        if (deleted == null) {
            LOGGER.debug("No Customer found with id: {}", customerId);
            throw new EntityNotFoundException(MessageResource.create("com.wavemaker.runtime.entity.not.found"), Customer.class.getSimpleName(), customerId);
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

    @Transactional(value = "SupportDBTransactionManager")
    @Override
    public void delete(Customer customer) {
        LOGGER.debug("Deleting Customer with {}", customer);
        this.wmGenericDao.delete(customer);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public Page<Customer> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Customers");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager")
    @Override
    public Page<Customer> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Customers");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager", timeout = 300)
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service SupportDB for table Customer to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

    @Transactional(readOnly = true, value = "SupportDBTransactionManager", timeout = 300)
    @Override
    public void export(DataExportOptions options, Pageable pageable, OutputStream outputStream) {
        LOGGER.debug("exporting data in the service SupportDB for table Customer to {} format", options.getExportType());
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
    public Page<ProductSerial> findAssociatedProductSerials(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated productSerials");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("customer.id = '" + id + "'");

        return productSerialService.findAll(queryBuilder.toString(), pageable);
    }

    /**
     * This setter method should only be used by unit tests
     *
     * @param service ProductSerialService instance
     */
    protected void setProductSerialService(ProductSerialService service) {
        this.productSerialService = service;
    }

}
