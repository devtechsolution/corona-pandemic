package org.as.devtechsolution.dto;

/*
 * Copyright (c) 2021. @Aditya Srivastva
 * All Rights Reserved
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertStatus {

    private String alertLevel;//RED, GREEN, ORNAGE

    private List<String> measuresToBeTaken;

    private StateData summaryData;

}