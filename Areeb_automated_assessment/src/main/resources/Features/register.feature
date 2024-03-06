Feature: User Registration and Profile Management on UltimateQA Website

  Scenario: Register, Upload Profile Picture, Change Password, and Verify Login with New Password
    Given I am on the UltimateQA website registration page
    When I fill in the registration form with valid information
    And I click the Sign Up button
    And I should be redirected to the profile page
    When I upload a profile picture
    And I should be redirected to the profile page
    When I change the password to a new one
    And I log out
    And I attempt to log in with the old password
    Then I should not be logged in
    And I should see an error message indicating invalid credentials
    When I attempt to log in with the new password
    Then I should be successfully logged in
