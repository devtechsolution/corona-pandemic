package org.as.devtechsolution.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/*
 * Copyright (c) 2021. @Aditya Srivastva
 * All Rights Reserved
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor

public class StateData  extends BaseDataClass{
    private String loc;
    private int totalConfirmed;
    @Builder
    public StateData (int confirmedCasesIndian, int confirmedCasesForeign, int discharged, int deaths, String loc, int totalConfirmed) {
        super(confirmedCasesIndian, confirmedCasesForeign, discharged, deaths);
        this.loc = loc;
        this.totalConfirmed = totalConfirmed;
    }
}