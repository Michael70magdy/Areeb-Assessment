package Scenario;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class BDDScenario {
    static WebDriver driver;
    private WebDriverWait wait;

    @Given("I am on the UltimateQA website registration page")
    public void i_am_on_the_ultimate_qa_website_registration_page() {
        driver = new EdgeDriver();
        driver.get("https://courses.ultimateqa.com/collections");
        driver.manage().window().maximize();
        WebElement signin = driver.findElement(By.xpath("/html/body/header/div[2]/div/nav/ul/li/a"));
        signin.click();
        driver.navigate().to("https://courses.ultimateqa.com/users/sign_up");

    }

    public static String Mail = new StringBuilder().append(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8)).append("@gmail.com").toString();

    @When("I fill in the registration form with valid information")
    public void i_fill_in_the_registration_form_with_valid_information() {

        WebElement FirstName = driver.findElement(By.id("user[first_name]"));
        FirstName.sendKeys("userTestFirstName");

        WebElement LastName = driver.findElement(By.id("user[last_name]"));
        LastName.sendKeys("userTestLastName");

        WebElement Email = driver.findElement(By.id("user[email]"));
        Email.sendKeys(Mail);

        WebElement Password = driver.findElement(By.id("user[password]"));
        Password.sendKeys("TestPassword");

        WebElement CheckBox = driver.findElement(By.id("user[terms]"));
        CheckBox.click();


    }

    @When("I click the Sign Up button")
    public void i_click_the_sign_up_button() {
        driver.findElement(By.xpath("/html/body/main/div/div/article/form/div[6]/button")).click();
    }


    @Then("I should be redirected to the profile page")
    public void i_should_be_redirected_to_the_profile_page() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        WebElement profile = driver.findElement(By.xpath("/html/body/header/div[2]/div/nav/ul/li[2]"));
        profile.click();

        WebElement MyAccount = driver.findElement(By.xpath("//*[@id=\"header-dropdown-menu\"]/li[2]"));
        MyAccount.click();
    }

    @When("I upload a profile picture")
    public void i_upload_a_profile_picture() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));

        WebElement uploadButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/main/form/div/div[1]/div/div/label"));
        //uploadButton.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));

        WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
        /* make sure to put a valid image path relative to device reviewing the code */
        fileInput.sendKeys("C:/Users/Maikool/Desktop/Pic.jpeg");


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(999));
    }

    @When("I change the password to a new one")
    public void i_change_the_password_to_a_new_one() {
        driver.findElement(By.xpath("/html/body/div[2]/div/div/nav/ul/li[2]/a")).click();
        WebElement currentPasswordInput = driver.findElement(By.id("user[current_password]"));
        currentPasswordInput.sendKeys("TestPassword");
        WebElement newPasswordInput = driver.findElement(By.id("user[password]"));
        newPasswordInput.sendKeys("NewPassword");
        WebElement confirmPasswordInput = driver.findElement(By.id("user[password_confirmation]"));
        confirmPasswordInput.sendKeys("NewPassword");
        driver.findElement(By.name("commit")).click();
    }

    @When("I log out")
    public void i_log_out() {
        WebElement profile = driver.findElement(By.xpath("/html/body/header/div[2]/div/nav/ul/li[2]/button"));
        profile.click();
        driver.findElement(By.xpath("//*[@id=\"header-dropdown-menu\"]/li[4]")).click();
    }

    @When("I attempt to log in with the old password")
    public void i_attempt_to_log_in_with_the_old_password() {
        driver.get("https://courses.ultimateqa.com/users/sign_in");
        WebElement emailInput = driver.findElement(By.id("user[email]"));
        emailInput.sendKeys(Mail);
        WebElement passwordInput = driver.findElement(By.id("user[password]"));
        passwordInput.sendKeys("TestPassword");
        driver.findElement(By.xpath("/html/body/main/div/div/article/form/div[5]/button")).click();
    }

    @Then("I should not be logged in")
    public void i_should_not_be_logged_in() {

        assertTrue(driver.getCurrentUrl().contains("/sign_in"));
    }

    @Then("I should see an error message indicating invalid credentials")
    public void i_should_see_an_error_message_indicating_invalid_credentials() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"flash\"]/div/p"));
        assertEquals("Invalid email or password.", errorMessage.getText());
    }

    @When("I attempt to log in with the new password")
    public void i_attempt_to_log_in_with_the_new_password() {
        driver.get("https://courses.ultimateqa.com/users/sign_in");
        WebElement emailInput = driver.findElement(By.id("user[email]"));
        emailInput.sendKeys(Mail);
        WebElement passwordInput = driver.findElement(By.id("user[password]"));
        passwordInput.sendKeys("NewPassword");
        driver.findElement(By.xpath("/html/body/main/div/div/article/form/div[5]/button")).click();
    }


    @Then("I should be successfully logged in")
    public void i_should_be_successfully_logged_in() {

        assertEquals("Sign In - Ultimate QA", driver.getTitle());
    }


    @After
    public void tearDown() {
        driver.quit();
    }

}
