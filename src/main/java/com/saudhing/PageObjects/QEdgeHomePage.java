package com.saudhing.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.saudhing.ExtentListeners.ExtentTestManager;
import com.saudhing.utilities.DriverManager;
import com.saudhing.utilities.PropertyManager;

public class QEdgeHomePage extends LoadableComponent<QEdgeHomePage> {
	
	// *********Initialize Variables*********
	private WebDriver driver;
	private BasePage basePage;

	// *********Global Variables*********
	String testURL = PropertyManager.getInstance().getTestsiteurl();
	
	// *********Locators*********
	@FindBy(xpath="//*[text()='QEdge Final Assignment']")
	public WebElement txt_QEdgeFinalAssignment;
	
	@FindBy(className="add-student-button")
	public WebElement btn_AddEmployee;
	
	@FindBy(xpath="//tbody//tr")
	public List<WebElement> rows_Employees;

	// *********Constructor*********
	public QEdgeHomePage(WebDriver driver) {
		
		this.driver = DriverManager.getDriver();
		basePage = new BasePage();
		PageFactory.initElements(driver, this);

	}

	@Override
	protected void load() {
		this.driver.get(testURL);
	}

	@Override
	protected void isLoaded() throws Error {
		//Assert.assertTrue(driver.getCurrentUrl().contains("web-employee-tracker"), "QEdge HomePage is not loaded !!");
		Assert.assertTrue(driver.getCurrentUrl().contains("EmployeeTrackerApp"), "QEdge HomePage is not loaded !!");
	}
	
	public void navigateToAddEmployee() {
		
		basePage.clickElement(btn_AddEmployee, "Add Employee button");
		
	}
	
	private int getEmployeesCount() {
		
		int rows = 0;
		try {
			rows = rows_Employees.size()-1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	private int getEmployeeRow(String firstName, String lastName, String email) {
		
		try {
			
			int rows = getEmployeesCount();
			
			//iterate through rows
			for(int iRow=1; iRow<=rows; iRow++) {
				String tbl_firstName = driver.findElement(By.xpath("//tbody//tr["+(iRow+1)+"]/td[1]")).getText();
				String tbl_lastName = driver.findElement(By.xpath("//tbody//tr["+(iRow+1)+"]/td[2]")).getText();
				String tbl_email = driver.findElement(By.xpath("//tbody//tr["+(iRow+1)+"]/td[3]")).getText();
				
				if(firstName.equals(tbl_firstName) && lastName.equals(tbl_lastName) && email.equals(tbl_email)) {
					ExtentTestManager.logInfo("Employee with first name: "+firstName+" last name: "+lastName+" email: "+email+
							" found at row = "+iRow);
					return iRow;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;		
	}
	
	public void validateEmployeeAdd(String firstName, String lastName, String email) {
		
		try {
			
			//get count of employees
			int count_employees = getEmployeesCount();
			
			//get row of employee
			int row_employee = getEmployeeRow(firstName, lastName, email);
			
			if(row_employee <= count_employees) {
				ExtentTestManager.logPass("Employee with firstName: "+firstName+" last name: "+lastName+" email: "+email+
						" added successfully !!!");
			} else {
				ExtentTestManager.logPass("Employee with firstName: "+firstName+" last name: "+lastName+" email: "+email+
						" not added successfully !!!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void validateEmployeeUpdate(String firstName, String lastName, String email, String append) {
		
		try {
			
			//get count of employees
			int count_employees = getEmployeesCount();
			
			//get row of employee
			int row_employee = getEmployeeRow(firstName+append, lastName+append, email);
			
			if(row_employee <= count_employees) {
				ExtentTestManager.logPass("Employee with firstName: "+firstName+" last name: "+lastName+" email: "+email+
						" updated successfully !!!");
			} else {
				ExtentTestManager.logPass("Employee with firstName: "+firstName+" last name: "+lastName+" email: "+email+
						" not updated successfully !!!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteEmployee(String firstName, String lastName, String email, String... append) {
		
		int row_employee = 0;
		
		try {
			
			//get row of employee
			if(append.length>0) {
				row_employee = getEmployeeRow(firstName+append[0], lastName+append[0], email);
			} else {
				row_employee = getEmployeeRow(firstName, lastName, email);
			}
			
			//delete employee
			By by_lnkDelete = By.xpath("//tbody//tr["+(row_employee+1)+"]/td[4]/a[contains(text(), 'Delete')]");
			basePage.clickElementsUsingByLocator(by_lnkDelete, "Delete link");
			driver.switchTo().alert().accept();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void validateEmployeeDelete(String firstName, String lastName, String email) {
		
		try {
			Thread.sleep(2000);
			
			//get row of employee
			int row_employee = getEmployeeRow(firstName, lastName, email);
			
			if(row_employee == 0) {
				ExtentTestManager.logPass("Employee with firstName: "+firstName+" last name: "+lastName+" email: "+email+
						" deleted successfully !!!");
			} else {
				ExtentTestManager.logFail("Employee with firstName: "+firstName+" last name: "+lastName+" email: "+email+
						" not deleted successfully !!!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickUpdate(String firstname, String lastname, String email) {
		
		try {
			
			//get row of employee
			int row_employee = getEmployeeRow(firstname, lastname, email);
			
			//delete employee
			By by_lnkUpdate = By.xpath("//tbody//tr["+(row_employee+1)+"]/td[4]/a[contains(text(), 'Update')]");
			basePage.clickElementsUsingByLocator(by_lnkUpdate, "Update link");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
