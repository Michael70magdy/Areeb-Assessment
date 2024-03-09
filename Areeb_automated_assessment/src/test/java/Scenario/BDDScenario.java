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


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class BDDScenario {
    static WebDriver driver;
    private WebDriverWait wait;

    @Given("I am on the UltimateQA website registration page")
    public void NavigateToUltimateQARegistrationPage() {
        driver = new EdgeDriver();
        driver.get("https://courses.ultimateqa.com/collections");
        driver.manage().window().maximize();
        WebElement signin = driver.findElement(By.xpath("/html/body/header/div[2]/div/nav/ul/li/a"));
        signin.click();
        driver.navigate().to("https://courses.ultimateqa.com/users/sign_up");

    }

    public static String Mail = new StringBuilder().append(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8)).append("@gmail.com").toString();

    @When("I fill in the registration form with valid information")
    public void FillRegistrationFormWithValidInformation() {

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
    public void ClickSignUpButton() {
        driver.findElement(By.xpath("/html/body/main/div/div/article/form/div[6]/button")).click();
    }


    @Then("I should be redirected to the profile page")
    public void RedirectToProfilePage() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        WebElement profile = driver.findElement(By.xpath("/html/body/header/div[2]/div/nav/ul/li[2]"));
        profile.click();

        WebElement MyAccount = driver.findElement(By.xpath("//*[@id=\"header-dropdown-menu\"]/li[2]"));
        MyAccount.click();
    }

    @When("I upload a profile picture")
    public void UploadProfilePicture() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        WebElement uploadButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/main/form/div/div[1]/div/div/label"));
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
        String imageUrl = "https://i0.wp.com/www.nasscal.com/wp-content/uploads/2015/09/cropped-testimage.png?fit=881%2C1200&ssl=1&w=640";
        // Download the image from the URL to a temporary location
        String tempImagePath = downloadImage(imageUrl);
        // Upload the image using the temporary file path
        fileInput.sendKeys(tempImagePath);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9999));
    }
    private static String downloadImage(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            InputStream in = url.openStream();
            String tempDir = System.getProperty("java.io.tmpdir");
            Path tempImagePath = Paths.get(tempDir, "tempImage"+Mail+".jpg");
            Files.copy(in, tempImagePath);
            in.close();
            return tempImagePath.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
    @When("I change the password to a new one")
    public void ChangePasswordToNewOne() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9999));
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
    public void LogOut() {
        WebElement profile = driver.findElement(By.xpath("/html/body/header/div[2]/div/nav/ul/li[2]/button"));
        profile.click();
        driver.findElement(By.xpath("//*[@id=\"header-dropdown-menu\"]/li[4]")).click();
    }

    @When("I attempt to log in with the old password")
    public void AttemptLoginWithOldPassword() {
        driver.get("https://courses.ultimateqa.com/users/sign_in");
        WebElement emailInput = driver.findElement(By.id("user[email]"));
        emailInput.sendKeys(Mail);
        WebElement passwordInput = driver.findElement(By.id("user[password]"));
        passwordInput.sendKeys("TestPassword");
        driver.findElement(By.xpath("/html/body/main/div/div/article/form/div[5]/button")).click();
    }

    @Then("I should not be logged in")
    public void ShouldNotBeLoggedIn() {

        assertTrue(driver.getCurrentUrl().contains("/sign_in"));
    }

    @Then("I should see an error message indicating invalid credentials")
    public void SeeInvalidCredentialsErrorMessage() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"flash\"]/div/p"));
        assertEquals("Invalid email or password.", errorMessage.getText());
    }

    @When("I attempt to log in with the new password")
    public void AttemptLoginWithNewPassword() {
        driver.get("https://courses.ultimateqa.com/users/sign_in");
        WebElement emailInput = driver.findElement(By.id("user[email]"));
        emailInput.sendKeys(Mail);
        WebElement passwordInput = driver.findElement(By.id("user[password]"));
        passwordInput.sendKeys("NewPassword");
        driver.findElement(By.xpath("/html/body/main/div/div/article/form/div[5]/button")).click();
    }


    @Then("I should be successfully logged in")
    public void ShouldBeSuccessfullyLoggedIn() {

        assertEquals("Sign In - Ultimate QA", driver.getTitle());
    }


    @After
    public void tearDown() {
        driver.quit();
    }

}
