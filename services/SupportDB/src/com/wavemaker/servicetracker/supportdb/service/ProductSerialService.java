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

import com.wavemaker.servicetracker.supportdb.JobRequest;
import com.wavemaker.servicetracker.supportdb.ProductSerial;

/**
 * Service object for domain model class {@link ProductSerial}.
 */
public interface ProductSerialService {

    /**
     * Creates a new ProductSerial. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on ProductSerial if any.
     *
     * @param productSerial Details of the ProductSerial to be created; value cannot be null.
     * @return The newly created ProductSerial.
     */
    ProductSerial create(@Valid ProductSerial productSerial);


	/**
     * Returns ProductSerial by given id if exists.
     *
     * @param productserialId The id of the ProductSerial to get; value cannot be null.
     * @return ProductSerial associated with the given productserialId.
	 * @throws EntityNotFoundException If no ProductSerial is found.
     */
    ProductSerial getById(Integer productserialId);

    /**
     * Find and return the ProductSerial by given id if exists, returns null otherwise.
     *
     * @param productserialId The id of the ProductSerial to get; value cannot be null.
     * @return ProductSerial associated with the given productserialId.
     */
    ProductSerial findById(Integer productserialId);

	/**
     * Find and return the list of ProductSerials by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param productserialIds The id's of the ProductSerial to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return ProductSerials associated with the given productserialIds.
     */
    List<ProductSerial> findByMultipleIds(List<Integer> productserialIds, boolean orderedReturn);


    /**
     * Updates the details of an existing ProductSerial. It replaces all fields of the existing ProductSerial with the given productSerial.
     *
     * This method overrides the input field values using Server side or database managed properties defined on ProductSerial if any.
     *
     * @param productSerial The details of the ProductSerial to be updated; value cannot be null.
     * @return The updated ProductSerial.
     * @throws EntityNotFoundException if no ProductSerial is found with given input.
     */
    ProductSerial update(@Valid ProductSerial productSerial);


    /**
     * Partially updates the details of an existing ProductSerial. It updates only the
     * fields of the existing ProductSerial which are passed in the productSerialPatch.
     *
     * This method overrides the input field values using Server side or database managed properties defined on ProductSerial if any.
     *
     * @param productserialId The id of the ProductSerial to be deleted; value cannot be null.
     * @param productSerialPatch The partial data of ProductSerial which is supposed to be updated; value cannot be null.
     * @return The updated ProductSerial.
     * @throws EntityNotFoundException if no ProductSerial is found with given input.
     */
    ProductSerial partialUpdate(Integer productserialId, Map<String, Object> productSerialPatch);

    /**
     * Deletes an existing ProductSerial with the given id.
     *
     * @param productserialId The id of the ProductSerial to be deleted; value cannot be null.
     * @return The deleted ProductSerial.
     * @throws EntityNotFoundException if no ProductSerial found with the given id.
     */
    ProductSerial delete(Integer productserialId);

    /**
     * Deletes an existing ProductSerial with the given object.
     *
     * @param productSerial The instance of the ProductSerial to be deleted; value cannot be null.
     */
    void delete(ProductSerial productSerial);

    /**
     * Find all ProductSerials matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching ProductSerials.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<ProductSerial> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all ProductSerials matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching ProductSerials.
     *
     * @see Pageable
     * @see Page
     */
    Page<ProductSerial> findAll(String query, Pageable pageable);

    /**
     * Exports all ProductSerials matching the given input query to the given exportType format.
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
     * Exports all ProductSerials matching the given input query to the given exportType format.
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
     * Retrieve the count of the ProductSerials in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the ProductSerial.
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
     * Returns the associated jobRequests for given ProductSerial id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated JobRequest instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<JobRequest> findAssociatedJobRequests(Integer id, Pageable pageable);

}
