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

import com.wavemaker.servicetracker.supportdb.EngineerAppointment;
import com.wavemaker.servicetracker.supportdb.Rma;

/**
 * Service object for domain model class {@link EngineerAppointment}.
 */
public interface EngineerAppointmentService {

    /**
     * Creates a new EngineerAppointment. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on EngineerAppointment if any.
     *
     * @param engineerAppointment Details of the EngineerAppointment to be created; value cannot be null.
     * @return The newly created EngineerAppointment.
     */
    EngineerAppointment create(@Valid EngineerAppointment engineerAppointment);


	/**
     * Returns EngineerAppointment by given id if exists.
     *
     * @param engineerappointmentId The id of the EngineerAppointment to get; value cannot be null.
     * @return EngineerAppointment associated with the given engineerappointmentId.
	 * @throws EntityNotFoundException If no EngineerAppointment is found.
     */
    EngineerAppointment getById(Integer engineerappointmentId);

    /**
     * Find and return the EngineerAppointment by given id if exists, returns null otherwise.
     *
     * @param engineerappointmentId The id of the EngineerAppointment to get; value cannot be null.
     * @return EngineerAppointment associated with the given engineerappointmentId.
     */
    EngineerAppointment findById(Integer engineerappointmentId);

	/**
     * Find and return the list of EngineerAppointments by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param engineerappointmentIds The id's of the EngineerAppointment to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return EngineerAppointments associated with the given engineerappointmentIds.
     */
    List<EngineerAppointment> findByMultipleIds(List<Integer> engineerappointmentIds, boolean orderedReturn);


    /**
     * Updates the details of an existing EngineerAppointment. It replaces all fields of the existing EngineerAppointment with the given engineerAppointment.
     *
     * This method overrides the input field values using Server side or database managed properties defined on EngineerAppointment if any.
     *
     * @param engineerAppointment The details of the EngineerAppointment to be updated; value cannot be null.
     * @return The updated EngineerAppointment.
     * @throws EntityNotFoundException if no EngineerAppointment is found with given input.
     */
    EngineerAppointment update(@Valid EngineerAppointment engineerAppointment);


    /**
     * Partially updates the details of an existing EngineerAppointment. It updates only the
     * fields of the existing EngineerAppointment which are passed in the engineerAppointmentPatch.
     *
     * This method overrides the input field values using Server side or database managed properties defined on EngineerAppointment if any.
     *
     * @param engineerappointmentId The id of the EngineerAppointment to be deleted; value cannot be null.
     * @param engineerAppointmentPatch The partial data of EngineerAppointment which is supposed to be updated; value cannot be null.
     * @return The updated EngineerAppointment.
     * @throws EntityNotFoundException if no EngineerAppointment is found with given input.
     */
    EngineerAppointment partialUpdate(Integer engineerappointmentId, Map<String, Object> engineerAppointmentPatch);

    /**
     * Deletes an existing EngineerAppointment with the given id.
     *
     * @param engineerappointmentId The id of the EngineerAppointment to be deleted; value cannot be null.
     * @return The deleted EngineerAppointment.
     * @throws EntityNotFoundException if no EngineerAppointment found with the given id.
     */
    EngineerAppointment delete(Integer engineerappointmentId);

    /**
     * Deletes an existing EngineerAppointment with the given object.
     *
     * @param engineerAppointment The instance of the EngineerAppointment to be deleted; value cannot be null.
     */
    void delete(EngineerAppointment engineerAppointment);

    /**
     * Find all EngineerAppointments matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching EngineerAppointments.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<EngineerAppointment> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all EngineerAppointments matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching EngineerAppointments.
     *
     * @see Pageable
     * @see Page
     */
    Page<EngineerAppointment> findAll(String query, Pageable pageable);

    /**
     * Exports all EngineerAppointments matching the given input query to the given exportType format.
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
     * Exports all EngineerAppointments matching the given input query to the given exportType format.
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
     * Retrieve the count of the EngineerAppointments in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the EngineerAppointment.
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
     * Returns the associated rmas for given EngineerAppointment id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Rma instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Rma> findAssociatedRmas(Integer id, Pageable pageable);

}
