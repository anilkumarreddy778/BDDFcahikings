@Order_place
Feature: Test Oreder placeing from POS Screen

@test5
Scenario: login_as_counter_and_place_an_order

Given User should be on login page
Then Enter the counter username and password
Then Click on login Button 
Then POS screen should Display
Then counter user should able to navigate all screens
And add some items to cart 
And check in cart added items should display
Then place order as cash order
And then go to billing screen
Then place order should display
And clcik on bill and take screenshot
Then logout from POS Screen


@test6
Scenario: database_testing

Given database username and password