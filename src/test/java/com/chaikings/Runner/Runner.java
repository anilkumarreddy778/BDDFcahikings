package com.chaikings.Runner;



import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features= {".\\src\\main\\java\\featuresFiles"},
		glue= {"com.chaikings.stepdefination"},
		dryRun=false,
		monochrome=true,
		tags= {"@test6"},
		plugin= {"pretty","json:target/cucumber-reports/cucumber.json"})

public class Runner {
		
}


	//ctrl + shift + o - to import
	//ctrl + shift + R - for open resource
	

 	//@RunWith(Cucumeber.class) - - @RunWith annotation tells Junit to run the class as Cucumber Test
	//features - - Features keyword provides the location of the feature file.
	//glue - - Glue keyword provides the path of the step Defination class.
	
	//TEST RUNNER
	//In addition to running a cucumber feature file, the test runner class also acts as an 
	//interlink between feature files and step definition classes. 
	//It is in test runner class, that you provide the path for both feature file and step defs class
	//There are multiple types of test runners such as JUnit runner, CLI runner, Android runner etc, that you can use to run Cucumber feature file. In this article, we will be using the JUnit runner
 
	//STEP DEFINATION  (Step defs) 
	//Cucumber step definition class is a normal java class where you can store step definition methods.
	//And a step definition method is a java method that is linked to a step in the scenario in feature file.
	//For each scenario step in your feature file, you should add a step definition method.
	// This way, all your steps in feature files will have a method associated to it.
	//So that, when you run the feature file, Cucumber would execute the step definition methods linked with the steps.

