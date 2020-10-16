Feature: Update Employee to QEdge application

  Background: 
    Given user launch browser 'chrome'

  Scenario: Validate 'Back to list' link functionality
    Given user navigates to Add Employee page
    When user clicks on the Back to list link
    Then user validates navigation to home page is successful

  Scenario Outline: Validate 'Back to list' link functionality on Update page
  	Given user navigates to Add Employee page
  	When user adds employee details "<firstName>", "<lastName>", "<email>"
  	Then user validates employee is added successfully "<firstName>", "<lastName>", "<email>"
   	When user navigates to update page "<firstName>", "<lastName>", "<email>"
    And user clicks on the Back to list link
    Then user validates navigation to home page is successful
    When user deletes employee details "<firstName>", "<lastName>", "<email>"
    Then user validates employee is deleted successfully "<firstName>", "<lastName>", "<email>"
    
    Examples: 
      | firstName | lastName  | email             |
      | Ricky     | Ponting   | ricky@ponting.com |
