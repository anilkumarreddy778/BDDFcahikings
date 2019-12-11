@Login_feature
Feature: Chaikings Beta login

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