package org.as.devtechsolution.service;

/*
 * Copyright (c) 2021. @Aditya Srivastva
 * All Rights Reserved
 */

import org.as.devtechsolution.dto.CovidApiData;
import org.as.devtechsolution.dto.StateData;
import org.as.devtechsolution.dto.SummaryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class Covid19DataProvider {

    static final String URL = "https://api.rootnet.in/covid19-in/stats/latest";

    @Autowired
    RestTemplate restTemplate;

    public StateData getStateData(String state) {

        CovidApiData covidApiData = restTemplate.getForObject(URL, CovidApiData.class);

        /*if(!covidApiData.isSuccess()){
            throw new RuntimeException("Issue in fetching data");
        }*/
        return Arrays.stream(covidApiData.getData().getRegional())
                .filter(e -> e.getLoc().equalsIgnoreCase(state))
                .findAny()
                .orElse(new StateData());

    }

    public SummaryData getSummaryData() {
        CovidApiData covidApiData = restTemplate.getForObject(URL, CovidApiData.class);

        SummaryData summaryData = covidApiData.getData().getSummary();

        summaryData.setUpdateTime(covidApiData.getLastRefreshed());

        return summaryData;
    }
}