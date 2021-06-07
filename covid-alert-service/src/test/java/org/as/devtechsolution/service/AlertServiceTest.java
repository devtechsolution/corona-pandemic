package org.as.devtechsolution.service;

/*
 * Copyright (c) 2021. @Aditya Srivastva
 * All Rights Reserved
 */

import org.as.devtechsolution.dto.AlertStatus;
import org.as.devtechsolution.dto.StateData;
import org.as.devtechsolution.dto.SummaryData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class AlertServiceTest {

    @InjectMocks
    private  AlertService alertService;

    @Mock
    private  Covid19DataProvider covid19DataProvider;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("When the total number of confirmed case are less than 1000")
    void getAlertAboutStateTestGreen(){

        StateData stateData = new StateData().builder().totalConfirmed(100).build();
        when(covid19DataProvider.getStateData(anyString())).thenReturn(stateData);

        final var status = alertService.getAlertAboutState("Bihar");

        assertEquals("GREEN", status.getAlertLevel());
        assertEquals(Arrays.asList("Everything is Normal !!"), status.getMeasuresToBeTaken());
        assertEquals(stateData, status.getSummaryData());

        verify(covid19DataProvider, times(1)).getStateData("Bihar");


    }

    @Test
    @DisplayName("When the total number of confirmed case are greatet than 1000 and less than 10000")
    void getAlertAboutStateTestOrange(){

        StateData stateData = new StateData().builder().totalConfirmed(1001).build();
        when(covid19DataProvider.getStateData(anyString())).thenReturn(stateData);

        final var status = alertService.getAlertAboutState("Delhi");

        assertEquals("ORANGE", status.getAlertLevel());
        assertEquals(Arrays.asList("Only Essential services are allowed", "List of services that come under essential service"), status.getMeasuresToBeTaken());
        assertEquals(stateData, status.getSummaryData());

        verify(covid19DataProvider, times(1)).getStateData("Delhi");
    }

    @Test
    @DisplayName("Boundry conditions")
    void getAlertAboutStateTestOrange2(){
        StateData stateData = new StateData();
        stateData.setTotalConfirmed(1001);

        when(covid19DataProvider.getStateData(anyString())).thenReturn(stateData);

        AlertStatus status = alertService.getAlertAboutState("uttar pradesh");

        assertEquals("ORANGE", status.getAlertLevel());
        assertEquals(Arrays.asList("Only Essential services are allowed", "List of services that come under essential service"), status.getMeasuresToBeTaken());
        assertEquals(stateData, status.getSummaryData());

        verify(covid19DataProvider).getStateData("uttar pradesh");
    }
    @Test
    @DisplayName("When the total number of confirmed cases are 10005")
    void getAlertAboutStateTestRed(){
        StateData stateData = new StateData();
        stateData.setTotalConfirmed(10005);

        when(covid19DataProvider.getStateData(anyString())).thenReturn(stateData);

        AlertStatus status = alertService.getAlertAboutState("Delhi");

        assertEquals("RED", status.getAlertLevel());
        assertEquals(Arrays.asList("Absolute lock-down", "Only Medical and food services are allowed here"), status.getMeasuresToBeTaken());
        assertEquals(stateData, status.getSummaryData());

        verify(covid19DataProvider).getStateData("Delhi");
    }


    @Test
    @DisplayName("Overall summary test")
    void getOverAllSummaryTest(){
        SummaryData /*summaryData = new SummaryData();
        summaryData.setUpdateTime(ZonedDateTime.now());
        summaryData.setConfirmedButLocationUnidentified(10);
        summaryData.setConfirmedCasesForeign(1);
        summaryData.setConfirmedCasesIndian(1000);
        summaryData.setDischarged(20);
        summaryData.setDeaths(2);
        summaryData.setTotal(1011);*/
        summaryData = new SummaryData().builder()
                .updateTime(ZonedDateTime.now())
                .confirmedButLocationUnidentified(10)
                .confirmedCasesForeign(1)
                .confirmedCasesIndian(1000)
                .discharged(20)
                .deaths(2)
                .total(1011)
                .build();

        when(covid19DataProvider.getSummaryData()).thenReturn(summaryData);

        SummaryData actualSummary = alertService.getOverAllSummary();

        assertEquals(summaryData, actualSummary);

    }


}