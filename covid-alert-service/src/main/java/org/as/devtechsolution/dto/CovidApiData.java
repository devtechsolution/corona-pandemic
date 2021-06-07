package org.as.devtechsolution.dto;

/*
 * Copyright (c) 2021. @Aditya Srivastva
 * All Rights Reserved
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CovidApiData {

//    private boolean success;

    private CountryData data;

    private ZonedDateTime lastRefreshed;

    private ZonedDateTime lastOriginUpdate;

//    public boolean isSuccess() {
//        return success;
//    }
//
//    public void setSuccess(boolean success) {
//        this.success = success;
//    }


}