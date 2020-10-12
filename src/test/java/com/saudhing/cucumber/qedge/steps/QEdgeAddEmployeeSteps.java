package com.saudhing.cucumber.qedge.steps;

import com.saudhing.ExtentListeners.ExtentTestManager;
import com.saudhing.PageObjects.BasePage;
import com.saudhing.PageObjects.QEdgeAddEmployeePage;
import com.saudhing.PageObjects.QEdgeHomePage;
import com.saudhing.utilities.TestContext;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class QEdgeAddEmployeeSteps extends BaseSteps {
	
	TestContext testContext;
	BasePage basePage;
	QEdgeHomePage qedgeHomePage;
	QEdgeAddEmployeePage qedgeAddEmployeePage;
	
	public QEdgeAddEmployeeSteps(TestContext testContext) {
		this.testContext = testContext;
		this.basePage = new BasePage();
		qedgeHomePage = testContext.getPageObjectManager().getQEdgeHomePage();
		qedgeAddEmployeePage = testContext.getPageObjectManager().getQEdgeAddEmployeePage();
	}
	
	@Given("^user navigates to Add Employee page$")
    public void user_navigates_to_add_employee_page() throws Throwable {
		qedgeAddEmployeePage.get();
    }
	
	@When("^user adds employee details \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void user_adds_employee_details(String firstname, String lastname, String email) throws Throwable {
		
		qedgeAddEmployeePage.addEmployee(firstname, lastname, email);	
		
	}
	
	@Then("^user validates employee is added successfully \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void user_validates_employee_is_added_successfully_something_something_something(String firstname, String lastname, String email) throws Throwable {
		
		qedgeHomePage.validateEmployeeAdd(firstname, lastname, email);
	
	}
	
	@When("^user deletes employee details \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void user_deletes_employee_details_something_something_something(String firstname, String lastname, String email) throws Throwable {

		qedgeHomePage.deleteEmployee(firstname, lastname, email);
	
	}
	
	@Then("^user validates employee is deleted successfully \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void user_validates_employee_is_deleted_successfully_something_something_something(String firstname, String lastname, String email) throws Throwable {
    
		qedgeHomePage.validateEmployeeDelete(firstname, lastname, email);
		
	}
	
	@When("^user clicks on the Back to list link$")
    public void user_clicks_on_the_back_to_list_link() throws Throwable {
		
		Thread.sleep(2000);
		this.basePage.clickElement(qedgeAddEmployeePage.lnk_Backtolist, "Back to list link");
		
    }
	
	@Then("^user validates navigation to home page is successful$")
    public void user_navigates_to_home_page() throws Throwable {
		
		if(this.basePage.isElementDisplayed(qedgeHomePage.btn_AddEmployee)) {
			ExtentTestManager.logPass("Back to list link functionality - Navigation to Home page is successful !!!");
		} else {
			ExtentTestManager.logFail("Back to list link functionality - Navigation to Home page is not successful !!!");
		}
    }
	
	@When("^user updates the employee \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void user_updates_the_employee_something_something_something(String firstname, String lastname, String email) throws Throwable {
		
		qedgeHomePage.clickUpdate(firstname, lastname, email);
		qedgeAddEmployeePage.updateEmployee(firstname, lastname, email);
		
	}
	
	@Then("^user validates the employee details are updated successfully \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void user_validates_the_employee_details_are_updated_successfully_something_something_something(String firstname, String lastname, String email) throws Throwable {
    
		qedgeHomePage.validateEmployeeUpdate(firstname, lastname, email, "_new");
		
	}
	
	@When("^user deletes updated employee details \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void user_deletes_updated_employee_details_something_something_something(String firstname, String lastname, String email) throws Throwable {
		
		qedgeHomePage.deleteEmployee(firstname, lastname, email, "_new");
	
	}
	
	@When("^user navigates to update page \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void user_navigates_to_update_page_something_something_something(String firstname, String lastname, String email) throws Throwable {

		qedgeHomePage.clickUpdate(firstname, lastname, email);
		
	}
}
