package com.chaikings.Runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features= {"D:/anil/Anil QA-TE_A/test_data/eclips/java devlop/chaikings/src/main/java/featuresFiles/loginFeatur.feature"},
		glue= {"com.chaikings.stepdefination"},
		dryRun=false,
		monochrome=true,
		plugin= {"pretty","html:target/cucumber-html-report"})

public class Runner {
	

}
