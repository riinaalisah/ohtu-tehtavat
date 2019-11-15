package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";


    // -------------- LOGIN STUFF ----------------
    
    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();   
    }

    @When("username {string} and password {string} are given")
    public void usernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @When("nonexistent username {string} and password {string} are given")
    public void nonexistentUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }



    // ---------------------------------------------------------

    // ----------- NEW USER STUFF ------------------------------


    @Given("command new user is selected")
    public void newUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @Given("user with username {string} with password {string} is successfully created")
    public void userWithUsernameAndPasswordIsCreated(String username, String password) {
        newUserIsSelected();
        validUsernameAndPasswordAndPasswordConfirmationAreGiven(username, password);
        logOutAfterCreation();
    }

    @Given("user with username {string} and password {string} is tried to be created")
    public void userWithInvalidUsernameAndPasswordIsTriedToBeCreated(String username, String password) {
        newUserIsSelected();
        registerNewUserWith(username, password, true);
        returnHomeAfterTriedCreation();
    }

    @When("a too short username {string} and valid password {string} and matching password confirmation are given")
    public void tooShortUsernameAndValidPasswordAndConfirmationAreGiven(String username, String password) {
        registerNewUserWith(username, password, true);
    }

    @When("a valid username {string} and too short password {string} are given")
    public void validUsernameAndTooShortPasswordAreGiven(String username, String password) {
        registerNewUserWith(username, password, true);
    }

    @When("a valid username {string} and password {string} and matching password confirmation are given")
    public void validUsernameAndPasswordAndPasswordConfirmationAreGiven(String username, String password) {
        registerNewUserWith(username, password, true);
    }

    @When("a valid username {string} and valid password {string} are given and password confirmation does not match")
    public void validUsernameAndPasswordAreGivenAndConfirmationDoesNotMatch(String username, String password) {
        registerNewUserWith(username, password, false);
    }

    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @Then("user is not created and error {string} is reported")
    public void userIsNotCreatedAndErrorIsReported(String error) {
        pageHasContent(error);
    }

    @Then("a new user is created")
    public void newUserIsCreated() {
        pageHasContent("Welcome to Ohtu Application!");
    }

    // -------------------------------------------------------
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }

    private void registerNewUserWith(String username, String password, boolean correctConfirmation) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        if (correctConfirmation) {
            element.sendKeys(password);
        } else {
            element.sendKeys(password + "not");
        }
        element = driver.findElement(By.name("signup"));
        element.submit();
    }

    private void logOutAfterCreation() {
        assertTrue(driver.getPageSource().contains("Welcome to Ohtu Application!"));
        WebElement element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        element = driver.findElement(By.linkText("logout"));
        element.click();
        assertTrue(driver.getPageSource().contains("Ohtu App"));
    }

    private void returnHomeAfterTriedCreation() {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.linkText("back to home"));
        element.click();
    }
}
