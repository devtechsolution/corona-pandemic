package org.as.devtechsolution.service;

/*
 * Copyright (c) 2021. @Aditya Srivastva
 * All Rights Reserved
 */

import org.as.devtechsolution.dto.AlertStatus;
import org.as.devtechsolution.dto.StateData;
import org.as.devtechsolution.dto.SummaryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AlertService {

    @Autowired
    private Covid19DataProvider covid19DataProvider;

    public AlertStatus getAlertAboutState(String state) {

        AlertStatus alertStatus = new AlertStatus();

        //business logic to derive the alert goes here
        StateData stateData = covid19DataProvider.getStateData(state);

        alertStatus.setSummaryData(stateData);
        if (stateData.getTotalConfirmed() < 1000) {
            alertStatus.setAlertLevel("GREEN");
            alertStatus.setMeasuresToBeTaken(Arrays.asList("Everything is Normal !!"));
        } else if (stateData.getTotalConfirmed() > 1000 && stateData.getTotalConfirmed() < 10000) {
            alertStatus.setAlertLevel("ORANGE");
            alertStatus.setMeasuresToBeTaken(Arrays.asList("Only Essential services are allowed", "List of services that come under essential service"));
        } else {
            alertStatus.setAlertLevel("RED");
            alertStatus.setMeasuresToBeTaken(Arrays.asList("Absolute lock-down", "Only Medical and food services are allowed here"));
        }

        return alertStatus;
    }

    public SummaryData getOverAllSummary() {

        return covid19DataProvider.getSummaryData();
    }
}