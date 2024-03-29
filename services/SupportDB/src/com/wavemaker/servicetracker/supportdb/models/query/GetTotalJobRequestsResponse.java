/*Copyright (c) 2022-2023 wavemaker.com All Rights Reserved.This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.wavemaker.servicetracker.supportdb.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.io.Serializable;
import java.util.Objects;

import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class GetTotalJobRequestsResponse implements Serializable {


    @ColumnAlias("count(id)")
    private Long count_id_;

    public Long getCount_id_() {
        return this.count_id_;
    }

    public void setCount_id_(Long count_id_) {
        this.count_id_ = count_id_;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetTotalJobRequestsResponse)) return false;
        final GetTotalJobRequestsResponse getTotalJobRequestsResponse = (GetTotalJobRequestsResponse) o;
        return Objects.equals(getCount_id_(), getTotalJobRequestsResponse.getCount_id_());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCount_id_());
    }
}
