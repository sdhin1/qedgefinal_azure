package com.saudhing.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class QEdgeAddEmployeePage extends LoadableComponent<QEdgeAddEmployeePage> {

	// *********Initialize Variables*********
	WebDriver driver;
	private BasePage basePage;
	LoadableComponent<QEdgeHomePage> parent;

	// *********Locators*********
	//@FindBy(xpath="//body//*[text()='Add Employee']")
	//public WebElement txt_AddEmployee;
	public By txt_AddEmployee = By.xpath("//body//*[text()='Add Employee']");

	@FindBy(name="firstName")
	public WebElement txtBox_FirstName;

	@FindBy(name="lastName")
	public WebElement txtBox_LastName;

	@FindBy(name="email")
	public WebElement txtBox_Email;

	@FindBy(xpath="//input[@type='submit']")
	public WebElement btn_Save;

	@FindBy(xpath="//a[text()='Back to list']")
	public WebElement lnk_Backtolist;

	public QEdgeAddEmployeePage(WebDriver driver, LoadableComponent<QEdgeHomePage> parent) {
		this.driver = driver;
		basePage = new BasePage();
		this.parent = parent;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {		
		parent.get().navigateToAddEmployee();		
	}

	@Override
	protected void isLoaded() throws Error {
		Assert.assertEquals(basePage.isElementPresent(txt_AddEmployee), true, "Add Employee Page is not loaded!");	
	}

	public void addEmployee(String firstName, String lastName, String email) {

		try {
			Thread.sleep(2000);
			basePage.sendKeysToWebElement(txtBox_FirstName, "First Name text box", firstName);
			basePage.sendKeysToWebElement(txtBox_LastName, "Last Name text box", lastName);
			basePage.sendKeysToWebElement(txtBox_Email, "Email text box", email);
			basePage.clickElement(btn_Save, "Save button");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void updateEmployee(String firstname, String lastname, String email) {

		try {

			basePage.WaitUntilWebElementIsVisible(txtBox_FirstName);
			Thread.sleep(2000);
			basePage.sendKeysToWebElement(txtBox_FirstName, "First Name text box", firstname+"_new");
			basePage.sendKeysToWebElement(txtBox_LastName, "Last Name text box", lastname+"_new");
			basePage.sendKeysToWebElement(txtBox_Email, "Email text box", email);
			basePage.clickElement(btn_Save, "Save button");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	}
