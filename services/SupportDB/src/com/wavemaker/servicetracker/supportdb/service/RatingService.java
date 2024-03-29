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
import com.wavemaker.servicetracker.supportdb.Rating;

/**
 * Service object for domain model class {@link Rating}.
 */
public interface RatingService {

    /**
     * Creates a new Rating. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Rating if any.
     *
     * @param ratingInstance Details of the Rating to be created; value cannot be null.
     * @return The newly created Rating.
     */
    Rating create(@Valid Rating ratingInstance);


	/**
     * Returns Rating by given id if exists.
     *
     * @param ratingId The id of the Rating to get; value cannot be null.
     * @return Rating associated with the given ratingId.
	 * @throws EntityNotFoundException If no Rating is found.
     */
    Rating getById(Integer ratingId);

    /**
     * Find and return the Rating by given id if exists, returns null otherwise.
     *
     * @param ratingId The id of the Rating to get; value cannot be null.
     * @return Rating associated with the given ratingId.
     */
    Rating findById(Integer ratingId);

	/**
     * Find and return the list of Ratings by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param ratingIds The id's of the Rating to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return Ratings associated with the given ratingIds.
     */
    List<Rating> findByMultipleIds(List<Integer> ratingIds, boolean orderedReturn);


    /**
     * Updates the details of an existing Rating. It replaces all fields of the existing Rating with the given ratingInstance.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Rating if any.
     *
     * @param ratingInstance The details of the Rating to be updated; value cannot be null.
     * @return The updated Rating.
     * @throws EntityNotFoundException if no Rating is found with given input.
     */
    Rating update(@Valid Rating ratingInstance);


    /**
     * Partially updates the details of an existing Rating. It updates only the
     * fields of the existing Rating which are passed in the ratingInstancePatch.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Rating if any.
     *
     * @param ratingId The id of the Rating to be deleted; value cannot be null.
     * @param ratingInstancePatch The partial data of Rating which is supposed to be updated; value cannot be null.
     * @return The updated Rating.
     * @throws EntityNotFoundException if no Rating is found with given input.
     */
    Rating partialUpdate(Integer ratingId, Map<String, Object> ratingInstancePatch);

    /**
     * Deletes an existing Rating with the given id.
     *
     * @param ratingId The id of the Rating to be deleted; value cannot be null.
     * @return The deleted Rating.
     * @throws EntityNotFoundException if no Rating found with the given id.
     */
    Rating delete(Integer ratingId);

    /**
     * Deletes an existing Rating with the given object.
     *
     * @param ratingInstance The instance of the Rating to be deleted; value cannot be null.
     */
    void delete(Rating ratingInstance);

    /**
     * Find all Ratings matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Ratings.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<Rating> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all Ratings matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Ratings.
     *
     * @see Pageable
     * @see Page
     */
    Page<Rating> findAll(String query, Pageable pageable);

    /**
     * Exports all Ratings matching the given input query to the given exportType format.
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
     * Exports all Ratings matching the given input query to the given exportType format.
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
     * Retrieve the count of the Ratings in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the Rating.
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
     * Returns the associated customerSatisfactions for given Rating id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated CustomerSatisfaction instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<CustomerSatisfaction> findAssociatedCustomerSatisfactions(Integer id, Pageable pageable);

}
