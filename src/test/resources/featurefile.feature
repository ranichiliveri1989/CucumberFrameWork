

Feature: login functionality of saleforce application
Background:

    Given Application is up and running and in loginpage
    @daily 
  Scenario: login with valid credentials
    When user on "LoginPage"
    When user enter a username "login.valid.uname"
    And  user enter a password "login.valid.password"
    And click on login button
    And user on "HomePage"
    Then verify page user name "Rani chiliveri"  

@weekly
Scenario: login with valid user name and empty password field
    When user on "LoginPage"
    When user enter a username "login.valid.uname"
    And  user enter a password "login.invalid.empty.password"
    And click on login button
    Then It should give the error message "Please enter your password."
  
 @monthly
 Scenario: check the remember me box
    When user on "LoginPage"
    When user enter a username "login.valid.uname"
    And  user enter a password "login.valid.password"
    And check the RememberMe checkbox
    And click on login button
    And user on "HomePage"
    And logout the homepage 
    Then I should get username field with my username as "login.valid.uname"
    
    @rani
    Scenario: validate the forgot password link
     When user on "LoginPage"
    When user enter a username "login.valid.uname"
    And  user enter a password "login.invalid.empty.password"
    And click on forgot password link
    And user on "ForgotPasswordpage"
    And user enter a username as in ForgotPasswordpage as "login.valid.uname"
    And click on continue button
    Then I should get the message like "Check Your Email"
   
    Scenario: login with in-valid credentials
    When user on "LoginPage"
    When user enter a username "login.invalid.uname"
    And  user enter a password "login.invalid.password"
    And click on login button
    Then I should get the error message like "Please check your username and password. If you still can't log in, contact your Salesforce administrator."
