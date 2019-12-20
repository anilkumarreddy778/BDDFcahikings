@Login_feature
Feature: Test User Login Functionality Screen

@test1
Scenario: login_with_correct_credentials

Given User is already on chaiking login page
Then verify the title of login page is chaikings
Given enter username and password
And Clcik on Submit button
Then home pos screen should display


@test2
Scenario: login_with_invalid_credentials

Given User is on chaiking login page
Then Verify the title of login page should chaikings
Given enter invalid username and password
Then verify proper error messge should Display

@test3
Scenario: login_and_place_an_order

Given counter will login to chaikings site
Then user will add some items to cart
Then counter will click on conform order
And order should be confrimed

@test4
Scenario: login_as_manager_and_check_manager_able_to_login_or_not

Given Manager in login page and verfiy login should dispaly
Then enter manger username and password and click on submit button
Then check weather succesfully logined or not
And click on logout button
Then verify login page should display



