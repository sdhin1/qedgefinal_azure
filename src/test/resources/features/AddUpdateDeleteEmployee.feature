Feature: Add and Delete Employee to QEdge application

  Background: 
    Given user launch browser 'chrome'

  Scenario Outline: Validate adding and deleting an employee is successful
    Given user navigates to Add Employee page
    When user adds employee details "<firstName>", "<lastName>", "<email>"
    Then user validates employee is added successfully "<firstName>", "<lastName>", "<email>"
    When user deletes employee details "<firstName>", "<lastName>", "<email>"
    Then user validates employee is deleted successfully "<firstName>", "<lastName>", "<email>"

    Examples: 
      | firstName | lastName  | email            |
      | Ricky     | Ponting   | ricky@abc.com    |
      | Sachin    | Tendulkar | sachin@gmail.com |
      | Virat     | Kohli     | virat@kohli.com  |

  Scenario Outline: Validate update to an employee is successful
    Given user navigates to Add Employee page
    When user adds employee details "<firstName>", "<lastName>", "<email>"
    Then user validates employee is added successfully "<firstName>", "<lastName>", "<email>"
    When user updates the employee "<firstName>", "<lastName>", "<email>"
    Then user validates the employee details are updated successfully "<firstName>", "<lastName>", "<email>"
    When user deletes updated employee details "<firstName>", "<lastName>", "<email>"
    Then user validates employee is deleted successfully "<firstName>", "<lastName>", "<email>"

    Examples: 
      | firstName | lastName  | email             |
      | Ricky     | Ponting   | ricky@ponting.com |
      | Sachin    | Tendulkar | sachin@gmail.com  |
      | Virat     | Kohli     | virat@kohli.com   |
