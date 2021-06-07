package org.as.devtechsolution.dto;

/*
 * Copyright (c) 2021. @Aditya Srivastva
 * All Rights Reserved
 */

import lombok.*;

import java.time.ZonedDateTime;
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor

public class SummaryData extends BaseDataClass{

    private int total;
    private int confirmedButLocationUnidentified;

    private ZonedDateTime updateTime;

    @Builder
    public SummaryData (int confirmedCasesIndian, int confirmedCasesForeign, int discharged, int deaths, int total, int confirmedButLocationUnidentified, ZonedDateTime updateTime) {
        super(confirmedCasesIndian, confirmedCasesForeign, discharged, deaths);
        this.total = total;
        this.confirmedButLocationUnidentified = confirmedButLocationUnidentified;
        this.updateTime = updateTime;
    }
}