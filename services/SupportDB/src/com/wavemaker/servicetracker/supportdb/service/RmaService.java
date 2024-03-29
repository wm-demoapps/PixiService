/*Copyright (c) 2022-2023 wavemaker.com All Rights Reserved.This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.wavemaker.servicetracker.supportdb.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.commons.file.model.Downloadable;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.DataExportOptions;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;

import com.wavemaker.servicetracker.supportdb.CustomerSatisfaction;
import com.wavemaker.servicetracker.supportdb.RepairActivity;
import com.wavemaker.servicetracker.supportdb.Rma;

/**
 * Service object for domain model class {@link Rma}.
 */
public interface RmaService {

    /**
     * Creates a new Rma. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Rma if any.
     *
     * @param rma Details of the Rma to be created; value cannot be null.
     * @return The newly created Rma.
     */
    Rma create(@Valid Rma rma);


	/**
     * Returns Rma by given id if exists.
     *
     * @param rmaId The id of the Rma to get; value cannot be null.
     * @return Rma associated with the given rmaId.
	 * @throws EntityNotFoundException If no Rma is found.
     */
    Rma getById(Integer rmaId);

    /**
     * Find and return the Rma by given id if exists, returns null otherwise.
     *
     * @param rmaId The id of the Rma to get; value cannot be null.
     * @return Rma associated with the given rmaId.
     */
    Rma findById(Integer rmaId);

	/**
     * Find and return the list of Rmas by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param rmaIds The id's of the Rma to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return Rmas associated with the given rmaIds.
     */
    List<Rma> findByMultipleIds(List<Integer> rmaIds, boolean orderedReturn);


    /**
     * Updates the details of an existing Rma. It replaces all fields of the existing Rma with the given rma.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Rma if any.
     *
     * @param rma The details of the Rma to be updated; value cannot be null.
     * @return The updated Rma.
     * @throws EntityNotFoundException if no Rma is found with given input.
     */
    Rma update(@Valid Rma rma);


    /**
     * Partially updates the details of an existing Rma. It updates only the
     * fields of the existing Rma which are passed in the rmaPatch.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Rma if any.
     *
     * @param rmaId The id of the Rma to be deleted; value cannot be null.
     * @param rmaPatch The partial data of Rma which is supposed to be updated; value cannot be null.
     * @return The updated Rma.
     * @throws EntityNotFoundException if no Rma is found with given input.
     */
    Rma partialUpdate(Integer rmaId, Map<String, Object> rmaPatch);

    /**
     * Deletes an existing Rma with the given id.
     *
     * @param rmaId The id of the Rma to be deleted; value cannot be null.
     * @return The deleted Rma.
     * @throws EntityNotFoundException if no Rma found with the given id.
     */
    Rma delete(Integer rmaId);

    /**
     * Deletes an existing Rma with the given object.
     *
     * @param rma The instance of the Rma to be deleted; value cannot be null.
     */
    void delete(Rma rma);

    /**
     * Find all Rmas matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Rmas.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<Rma> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all Rmas matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Rmas.
     *
     * @see Pageable
     * @see Page
     */
    Page<Rma> findAll(String query, Pageable pageable);

    /**
     * Exports all Rmas matching the given input query to the given exportType format.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param exportType The format in which to export the data; value cannot be null.
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return The Downloadable file in given export type.
     *
     * @see Pageable
     * @see ExportType
     * @see Downloadable
     */
    Downloadable export(ExportType exportType, String query, Pageable pageable);

    /**
     * Exports all Rmas matching the given input query to the given exportType format.
     *
     * @param options The export options provided by the user; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @param outputStream The output stream of the file for the exported data to be written to.
     *
     * @see DataExportOptions
     * @see Pageable
     * @see OutputStream
     */
    void export(DataExportOptions options, Pageable pageable, OutputStream outputStream);

    /**
     * Retrieve the count of the Rmas in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the Rma.
     */
    long count(String query);

    /**
     * Retrieve aggregated values with matching aggregation info.
     *
     * @param aggregationInfo info related to aggregations.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return Paginated data with included fields.
     *
     * @see AggregationInfo
     * @see Pageable
     * @see Page
	 */
    Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable);

    /*
     * Returns the associated customerSatisfactions for given Rma id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated CustomerSatisfaction instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<CustomerSatisfaction> findAssociatedCustomerSatisfactions(Integer id, Pageable pageable);

    /*
     * Returns the associated repairActivities for given Rma id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated RepairActivity instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<RepairActivity> findAssociatedRepairActivities(Integer id, Pageable pageable);

}
