package com.saudhing.cucumber.qedge.steps;

import com.saudhing.ExtentListeners.ExtentTestManager;

import cucumber.api.java.en.Given;

public class QEdgeCommonSteps extends BaseSteps {
	
	@Given("^user launch browser '(.*?)'$")
	public void launch_browser(String browser) throws Throwable {		
		openBrowser(browser);
		ExtentTestManager.logInfo("Browser launched : "+ browser);
	}

}
