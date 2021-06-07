package org.as.devtechsolution.controller;

/*
 * Copyright (c) 2021. @Aditya Srivastva
 * All Rights Reserved
 */

import org.as.devtechsolution.dto.AlertStatus;
import org.as.devtechsolution.dto.SummaryData;
import org.as.devtechsolution.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/india/")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @GetMapping("/{state}")
    public AlertStatus getAlertAboutState(@PathVariable String state){

        return alertService.getAlertAboutState(state);
    }

    @GetMapping("/summary")
    public SummaryData getOverAllSummary(){

        return alertService.getOverAllSummary();
    }
}