package com.saudhing.cucumber.qedge.steps;

import com.aventstack.extentreports.Status;
import com.saudhing.ExtentListeners.ExtentManager;
import com.saudhing.ExtentListeners.ExtentTestManager;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends BaseSteps {
	
	protected Scenario scenario;
	static String scenarioName;
	static int i = 0;
	
	@Before
	public void before(Scenario scenario) {

		this.scenario = scenario;
		scenarioName = scenario.getName();
		ExtentTestManager.startTest("Scenario Name : " + scenario.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Scenario Started : " + scenario.getName());
		setUpFramework();
	}
	
	@After
	public void after(Scenario scenario) {

		if (scenario.isFailed()) {
			ExtentTestManager.logFail("Scenarion Failed");
			ExtentTestManager.addScreenShotsOnFailure();
		} else {
			ExtentTestManager.logPass("Scenario Passed");
		}

		ExtentManager.getReporter().flush();

		quit();

	}
	
}
