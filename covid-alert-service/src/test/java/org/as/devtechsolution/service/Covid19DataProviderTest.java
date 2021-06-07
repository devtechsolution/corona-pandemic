
package org.as.devtechsolution.service;
/*
*/
/*
 * Copyright (c) 2021. @Aditya Srivastva
 * All Rights Reserved
 */


import org.as.devtechsolution.dto.CountryData;
import org.as.devtechsolution.dto.CovidApiData;
import org.as.devtechsolution.dto.StateData;
import org.as.devtechsolution.dto.SummaryData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

//@ExtendWith(MockitoExtension.class)
class Covid19DataProviderTest {

    @InjectMocks
    private Covid19DataProvider covid19DataProvider;

    @Mock
    private RestTemplate restTemplate;

    {
        openMocks(this);
    }




    @Test
    @DisplayName("state data provider test")
    void getStateDataTest() {

        when(restTemplate.getForObject(anyString(), any())).thenReturn(getCovidApiData());

        StateData stateData = covid19DataProvider.getStateData("Delhi");

        assertAll(
                () -> assertEquals("Delhi", stateData.getLoc()),
                () -> assertEquals(4, stateData.getDeaths()),
                () -> assertEquals(0, stateData.getConfirmedCasesForeign()),
                () -> assertEquals(1000, stateData.getConfirmedCasesIndian()),
                () -> assertEquals(4, stateData.getDischarged()),
                () -> assertEquals(1000, stateData.getTotalConfirmed())
        );
    }


    @Test
    @DisplayName("state data provider test - no data found")
    void getStateDataTestNoDataFoundForState() {

        when(restTemplate.getForObject(anyString(), any())).thenReturn(getCovidApiData());

        StateData maharashtra = covid19DataProvider.getStateData("Maharashtra");

        assertAll(
                () -> assertEquals(null, maharashtra.getLoc()),
                () -> assertEquals(0, maharashtra.getDeaths()),
                () -> assertEquals(0, maharashtra.getConfirmedCasesForeign()),
                () -> assertEquals(0, maharashtra.getConfirmedCasesIndian()),
                () -> assertEquals(0, maharashtra.getDischarged()),
                () -> assertEquals(0, maharashtra.getTotalConfirmed())
        );
    }

    @Test
    @DisplayName("summary data test")
    void getSummaryDataTest() {
        when(restTemplate.getForObject(anyString(), any())).thenReturn(getCovidApiDataForSummary());

        SummaryData data = covid19DataProvider.getSummaryData();

        assertAll(
                () -> assertEquals(5, data.getConfirmedButLocationUnidentified()),
                () -> assertEquals(100, data.getTotal()),
                () -> assertEquals(2, data.getDeaths()),
                () -> assertEquals(1, data.getDischarged()),
                () -> assertEquals(10, data.getConfirmedCasesForeign()),
                () -> assertEquals(90, data.getConfirmedCasesIndian()),
                () -> assertNotNull(data.getUpdateTime())
        );
    }



    private CovidApiData getCovidApiDataForSummary() {
        SummaryData summaryData = SummaryData.builder()
                .total(100)
                .deaths(2)
                .discharged(1)
                .confirmedCasesIndian(90)
                .confirmedCasesForeign(10)
                .updateTime(ZonedDateTime.now())
                .confirmedButLocationUnidentified(5)
                .build();

        CountryData countryData = CountryData.builder()
                .summary(summaryData)
                .build();

        CovidApiData covidApiData = CovidApiData.builder()
                .data(countryData)
                .lastRefreshed(ZonedDateTime.now()).build();

        return covidApiData;
    }

    private CovidApiData getCovidApiData() {

        StateData sd = StateData.builder()
                .deaths(4)
                .loc("Delhi")
                .discharged(4)
                .confirmedCasesForeign(0)
                .confirmedCasesIndian(1000)
                .totalConfirmed(1000)
                .build();
        CovidApiData covidApiData = CovidApiData.builder()
                .data(
                        CountryData.builder()
                                .regional(new StateData[]{sd})
                                .build()
                ).build();
        return covidApiData;
    }
}


