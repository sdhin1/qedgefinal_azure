package com.saudhing.utilities;

public class TestContext {
	
	private PageObjectManager pageObjectManager;
	
	public TestContext() {
		pageObjectManager = new PageObjectManager(DriverManager.getDriver());
	}
	
	public PageObjectManager getPageObjectManager() {
		return pageObjectManager;
	}

}
