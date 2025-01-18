package hellocucumber;

import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Paths;
import java.time.Duration;

import org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StepDefinitions {
    private final String webDriver = "webdriver.chrome.driver";
    private final String path = Paths.get(System.getProperty("user.dir")).getParent().resolve("Selenium").resolve("chromedriver").toString(); // dynamically gets the parent of the working directory
    private ChromeDriver driver;
    private WebDriverWait wait;
    // in this scenario, the user is already logged in and is in the home page
    @Given("a logged in user is in the home page")
    public void loggedInUserInHomePage() {
        // set up the driver
        setUp();

    }
    // the user logs in with the given username and password
    @When("the user logged in with username {string} and password {string}")
    public void userLoggedIn(String username, String password) {
        // first the user clicks the login button
        gotoLoginPage();    
        // insert the username and password
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='username']"))).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div[1]/input[1]"))).sendKeys(password);
        // click the login button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='loginbtn']"))).click();
    }
    @And("the user navigates to the course page")
    public void userNavigatesToCoursePage() {
        // click the course link
        driver.findElement(By.xpath("/html/body/div[2]/nav/div/div[1]/nav/ul/li[3]/a")).click();
        // go to first course
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/section")));
        WebElement courseSection = wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/section")));
        // Wait for the specific course link to be clickable
        WebElement firstCourseLink = wait.until(ExpectedConditions.elementToBeClickable(
        By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/section/div/aside/section/div/div/div[1]/div[2]/div/div/div[1]/div/div/div/div/div[1]/div/div/a")));
        firstCourseLink.click();


    }
    @And("the user navigates to quiz page")
    public void userNavigatesToQuizPage() {
        // xpath to the quiz link:
        // /html/body/div[2]/div[5]/div/div[3]/div/section/div/div/div/ul/li[6]/div/div[2]/ul/li/div/div[2]/div[2]/div/div/a
        // first wait for the page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[5]/div/div[3]/div/section/div/div/div/ul/li[6]/div/div[2]/ul/li/div/div[2]/div[2]/div/div/a")));
        // click the quiz link
        driver.findElement(By.xpath("/html/body/div[2]/div[5]/div/div[3]/div/section/div/div/div/ul/li[6]/div/div[2]/ul/li/div/div[2]/div[2]/div/div/a")).click();
        // xpath to quiz button:
        // /html/body/div[2]/div[4]/div/div[2]/div/section/div[2]/div[1]/div/div/form/button
        // wait for the quiz button to be clickable, then click it
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[4]/div/div[2]/div/section/div[2]/div[1]/div/div/form/button"))).click();
    }
    @And("the user fills out a question with multiple choices and chooses two answers")
    public void the_user_fills_out_a_question_with_multiple_choices_and_chooses_two_answers() {
        /// xpath of first checkbox: 
        /// /html/body/div[2]/div[5]/div/div[2]/div/section/div[2]/form/div/div[1]/div[2]/div/fieldset/div/div[1]/input[2]
        /// xpath of second checkbox:
        /// /html/body/div[2]/div[5]/div/div[2]/div/section/div[2]/form/div/div[1]/div[2]/div/fieldset/div/div[2]/input[2]
        // wait for the checkboxes to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[5]/div/div[2]/div/section/div[2]/form/div/div[1]/div[2]/div/fieldset/div/div[1]/input[2]")));
        // click the first checkbox
        driver.findElement(By.xpath("/html/body/div[2]/div[5]/div/div[2]/div/section/div[2]/form/div/div[1]/div[2]/div/fieldset/div/div[1]/input[2]")).click();
        // click the second checkbox
        driver.findElement(By.xpath("/html/body/div[2]/div[5]/div/div[2]/div/section/div[2]/form/div/div[1]/div[2]/div/fieldset/div/div[2]/input[2]")).click();


    }

    @Then("the student's answer is recorded")
    public void the_student_s_answer_must_be_recorded() {
        // now we need to check if the answers are recorded()
        // we can do this by checking if the checkbox is selected
        // check if the first checkbox is selected
        assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div[5]/div/div[2]/div/section/div[2]/form/div/div[1]/div[2]/div/fieldset/div/div[1]/input[2]")).isSelected());
        // check if the second checkbox is selected
        assertTrue(driver.findElement(By.xpath("/html/body/div[2]/div[5]/div/div[2]/div/section/div[2]/form/div/div[1]/div[2]/div/fieldset/div/div[2]/input[2]")).isSelected());
    }


    // *** general pupose methods: ***

    private void setUp() {
        System.out.println(path);
        System.setProperty(webDriver, path);
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://sandbox.moodledemo.net/");
    }
    private void gotoLoginPage() {
        driver.findElement(By.xpath("//*[@id='usernavigation']/div/div/span/a")).click();
    }
}
