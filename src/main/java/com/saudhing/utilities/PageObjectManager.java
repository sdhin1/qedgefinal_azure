package com.saudhing.utilities;

import org.openqa.selenium.WebDriver;

import com.saudhing.PageObjects.QEdgeAddEmployeePage;
import com.saudhing.PageObjects.QEdgeHomePage;

public class PageObjectManager {
	
	private WebDriver driver;
	public static QEdgeHomePage qedgeHomePage = null;
	public static QEdgeAddEmployeePage qedgeAddEmployeePage = null;
	
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}
	
	public QEdgeHomePage getQEdgeHomePage() {
		//return (qedgeHomePage == null) ? new QEdgeHomePage(driver) : qedgeHomePage;
		return new QEdgeHomePage(driver);
	}
	
	public QEdgeAddEmployeePage getQEdgeAddEmployeePage() {
		//qedgeHomePage = (qedgeHomePage == null) ? getQEdgeHomePage() : qedgeHomePage;
		//return (qedgeAddEmployeePage == null) ? new QEdgeAddEmployeePage(driver, qedgeHomePage) : qedgeAddEmployeePage;
		qedgeHomePage = getQEdgeHomePage();
		return new QEdgeAddEmployeePage(driver, qedgeHomePage);
	}

}
