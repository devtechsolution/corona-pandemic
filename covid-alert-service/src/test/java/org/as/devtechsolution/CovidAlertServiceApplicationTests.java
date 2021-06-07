package org.as.devtechsolution;

/*
 * Copyright (c) 2021. @Aditya Srivastva
 * All Rights Reserved
 */



import org.as.devtechsolution.controller.AlertController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@ContextConfiguration(classes = {CovidAlertServiceApplication.class})
class CovidAlertServiceApplicationTests {

	@Test
	void contextLoads() {
	}
}
