Areeb Automated Task 

Automated test written as BDD which is based on Cucumber and selenium using java working on "https://courses.ultimateqa.com/collections" webiste 
IMPORTANT ::: """ make sure to do the reCaptcha if appeared """ :::

First: Website Scenario Features

	
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


Second: Describtion for each test case 


	1- "I am on the UltimateQA website registration page"
		- websites loads with the home page of Ultimate QA webiste and navigates to sign in then create new account page

	2- "I fill in the registration form with valid information"
		- user upload his data with dynamic "Mail" wich changes every time to make automation script eligable each run time for the scenario and then agrees for the site terms and conditions 

	3- "I click the Sign Up button"
		- user finish his registration step then sign up 

	4- "I should be redirected to the profile page"
		- user navigates to his profile 

	5- "I upload a profile picture"
		- user uploads his profile photo and save changes make sure to pass a valid path to the image filed as it changes from device to another at line 85 "fileInput.sendKeys("Your_path_here");"

	6- "I change the password to a new one"
		- user navigates to change password page then change his current password by entring his old password then his new password and confirming it then commit his change 

	7- "I log out"
		- user logs out 

	8- "I attempt to log in with the old password"
		- user attemps to login using his old password 

	9- "I should not be logged in"
		- user still at same login page as he entered his old password 

	10- "I should see an error message indicating invalid credentials"
		- popup appears which making sure that user entered invalid data and checking message value with error messeage
	
	11- "I attempt to log in with the new password"
		- user now attempts to login in using his new password then logins
	
	12- "I should be successfully logged in"
		- site check homepage url title with title which ensures that user successfully logged in 

	13- "Website closes after the cycle"
