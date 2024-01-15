/*Copyright (c) 2022-2023 wavemaker.com All Rights Reserved.This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.wavemaker.servicetracker.supportdb.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.util.Objects;

import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class GetRatingsAggregationResponse implements Serializable {


    @ColumnAlias("rating")
    private String rating;

    @ColumnAlias("count")
    private Long _count;

    @ColumnAlias("color_hex_code")
    private String colorHexCode;

    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Long get_count() {
        return this._count;
    }

    public void set_count(Long _count) {
        this._count = _count;
    }

    public String getColorHexCode() {
        return this.colorHexCode;
    }

    public void setColorHexCode(String colorHexCode) {
        this.colorHexCode = colorHexCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetRatingsAggregationResponse)) return false;
        final GetRatingsAggregationResponse getRatingsAggregationResponse = (GetRatingsAggregationResponse) o;
        return Objects.equals(getRating(), getRatingsAggregationResponse.getRating()) &&
                Objects.equals(get_count(), getRatingsAggregationResponse.get_count()) &&
                Objects.equals(getColorHexCode(), getRatingsAggregationResponse.getColorHexCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRating(),
                get_count(),
                getColorHexCode());
    }
}
