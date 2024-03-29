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

import com.wavemaker.servicetracker.supportdb.FaultAnalysis;
import com.wavemaker.servicetracker.supportdb.RepairActivity;

/**
 * Service object for domain model class {@link FaultAnalysis}.
 */
public interface FaultAnalysisService {

    /**
     * Creates a new FaultAnalysis. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on FaultAnalysis if any.
     *
     * @param faultAnalysis Details of the FaultAnalysis to be created; value cannot be null.
     * @return The newly created FaultAnalysis.
     */
    FaultAnalysis create(@Valid FaultAnalysis faultAnalysis);


	/**
     * Returns FaultAnalysis by given id if exists.
     *
     * @param faultanalysisId The id of the FaultAnalysis to get; value cannot be null.
     * @return FaultAnalysis associated with the given faultanalysisId.
	 * @throws EntityNotFoundException If no FaultAnalysis is found.
     */
    FaultAnalysis getById(Integer faultanalysisId);

    /**
     * Find and return the FaultAnalysis by given id if exists, returns null otherwise.
     *
     * @param faultanalysisId The id of the FaultAnalysis to get; value cannot be null.
     * @return FaultAnalysis associated with the given faultanalysisId.
     */
    FaultAnalysis findById(Integer faultanalysisId);

	/**
     * Find and return the list of FaultAnalyses by given id's.
     *
     * If orderedReturn true, the return List is ordered and positional relative to the incoming ids.
     *
     * In case of unknown entities:
     *
     * If enabled, A null is inserted into the List at the proper position(s).
     * If disabled, the nulls are not put into the return List.
     *
     * @param faultanalysisIds The id's of the FaultAnalysis to get; value cannot be null.
     * @param orderedReturn Should the return List be ordered and positional in relation to the incoming ids?
     * @return FaultAnalyses associated with the given faultanalysisIds.
     */
    List<FaultAnalysis> findByMultipleIds(List<Integer> faultanalysisIds, boolean orderedReturn);


    /**
     * Updates the details of an existing FaultAnalysis. It replaces all fields of the existing FaultAnalysis with the given faultAnalysis.
     *
     * This method overrides the input field values using Server side or database managed properties defined on FaultAnalysis if any.
     *
     * @param faultAnalysis The details of the FaultAnalysis to be updated; value cannot be null.
     * @return The updated FaultAnalysis.
     * @throws EntityNotFoundException if no FaultAnalysis is found with given input.
     */
    FaultAnalysis update(@Valid FaultAnalysis faultAnalysis);


    /**
     * Partially updates the details of an existing FaultAnalysis. It updates only the
     * fields of the existing FaultAnalysis which are passed in the faultAnalysisPatch.
     *
     * This method overrides the input field values using Server side or database managed properties defined on FaultAnalysis if any.
     *
     * @param faultanalysisId The id of the FaultAnalysis to be deleted; value cannot be null.
     * @param faultAnalysisPatch The partial data of FaultAnalysis which is supposed to be updated; value cannot be null.
     * @return The updated FaultAnalysis.
     * @throws EntityNotFoundException if no FaultAnalysis is found with given input.
     */
    FaultAnalysis partialUpdate(Integer faultanalysisId, Map<String, Object> faultAnalysisPatch);

    /**
     * Deletes an existing FaultAnalysis with the given id.
     *
     * @param faultanalysisId The id of the FaultAnalysis to be deleted; value cannot be null.
     * @return The deleted FaultAnalysis.
     * @throws EntityNotFoundException if no FaultAnalysis found with the given id.
     */
    FaultAnalysis delete(Integer faultanalysisId);

    /**
     * Deletes an existing FaultAnalysis with the given object.
     *
     * @param faultAnalysis The instance of the FaultAnalysis to be deleted; value cannot be null.
     */
    void delete(FaultAnalysis faultAnalysis);

    /**
     * Find all FaultAnalyses matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
     *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
     *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching FaultAnalyses.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
     */
    @Deprecated
    Page<FaultAnalysis> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
     * Find all FaultAnalyses matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching FaultAnalyses.
     *
     * @see Pageable
     * @see Page
     */
    Page<FaultAnalysis> findAll(String query, Pageable pageable);

    /**
     * Exports all FaultAnalyses matching the given input query to the given exportType format.
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
     * Exports all FaultAnalyses matching the given input query to the given exportType format.
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
     * Retrieve the count of the FaultAnalyses in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
     * @return The count of the FaultAnalysis.
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
     * Returns the associated repairActivities for given FaultAnalysis id.
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
