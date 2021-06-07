package org.as.devtechsolution.controller;

/*
 * Copyright (c) 2021. @Aditya Srivastva
 * All Rights Reserved
 */

import org.as.devtechsolution.dto.AlertStatus;
import org.as.devtechsolution.dto.SummaryData;
import org.as.devtechsolution.service.AlertService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AlertController.class)
@ContextConfiguration(classes = {AlertController.class})
public class AlertControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AlertService alertService;

    @Test
    void getAlertAboutStateTest() throws Exception {

        AlertStatus alertStatus = AlertStatus.builder().alertLevel("GREEN").build();
        when(alertService.getAlertAboutState(anyString())).thenReturn(alertStatus);

        mockMvc.perform(get("/india/maharashtra"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"alertLevel\":\"GREEN\",\"measuresToBeTaken\":null,\"summaryData\":null}"));
    }

    @Test
    void getSummaryTest() throws Exception {

        SummaryData sd  = new SummaryData();

        when(alertService.getOverAllSummary()).thenReturn(sd);

        mockMvc.perform(get("/india/summary"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"total\":0,\"confirmedCasesIndian\":0,\"confirmedCasesForeign\":0,\"discharged\":0,\"deaths\":0,\"confirmedButLocationUnidentified\":0,\"updateTime\":null}"));
    }

    @Test
    void invalidEndpoint() throws Exception {

        mockMvc.perform(get("/india123"))
                .andExpect(status().isNotFound());
    }




}