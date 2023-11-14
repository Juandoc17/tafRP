Feature: Login Functionalities

  @RegularTest
  Scenario: Successful login with correct credentials
    Given the user has navigated to the login page
    When the user enters username "tomsmith" and password "SuperSecretPassword!"
    And user click on 'login' button
    Then the user should be redirected to the secure page

  @RegularTest
  Scenario: Error message appears when login with incorrect data
    Given the user has navigated to the login page
    When the user enters username "tomsmith" and password "incorrectPassword"
    And user click on 'login' button
    Then the user should see an error message "Your password is invalid!"