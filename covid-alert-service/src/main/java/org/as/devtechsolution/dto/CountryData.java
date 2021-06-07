package org.as.devtechsolution.dto;

/*
 * Copyright (c) 2021. @Aditya Srivastva
 * All Rights Reserved
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryData {

    private SummaryData summary;

    private StateData[] regional;
}