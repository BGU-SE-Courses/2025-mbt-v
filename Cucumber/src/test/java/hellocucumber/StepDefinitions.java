package hellocucumber;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.nio.file.Paths;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StepDefinitions {
    private final String webDriver = "webdriver.chrome.driver";
    private final String path = Paths.get(System.getProperty("user.dir"))
                                   .getParent()
                                   .resolve("Selenium")
                                   .resolve("chromedriver")
                                   .toString();
    private ChromeDriver driver;
    private WebDriverWait wait;

    // first scenario: Student fills out a question with multiple choices and chooses two answers
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




    // second scenario: Teacher edits a question to be single choice
    @Given("a logged-in teacher is on the Moodle home page")
    public void teacherOnHomePage() {
        setUp();
    }

    @When("the teacher logs in with username {string} and password {string}")
    public void teacherLogsIn(String username, String password) {
        gotoLoginPage();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div[1]/input[1]"))).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("loginbtn"))).click();
    }

    @And("the teacher navigates to the course page")
    public void teacherNavigatesToCoursePage() {
        // Wait for the "My first course" link and click it
        WebElement courseLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'My first course')]")));
        
        // Scroll to the element to make sure it's visible
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", courseLink);
        
        // Click the course link
        courseLink.click();
    }

    @And("the teacher creates a quiz")
    public void teacherCreatesAQuiz() {
        // 1️⃣ Click on the "Edit mode" label to enable editing
        WebElement editModeLabel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(),'Edit mode')]")));
        
        // Click the label to enable edit mode
        editModeLabel.click();
        
        // Wait until edit mode is activated (Verify the checkbox gets checked)
        WebElement editModeCheckbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='setmode']")));
        wait.until(ExpectedConditions.attributeContains(editModeCheckbox, "checked", "true"));
    
        // 2️⃣ Click "Add an activity or resource"
        WebElement addActivityButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Add an activity or resource')]")));
        addActivityButton.click();
    
        // 3️⃣ Click on the "Quiz" text to select the activity
        WebElement quizText = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='optionname clamp-2' and contains(text(),'Quiz')]")));
        quizText.click();
    
        // 4️⃣ Enter quiz name
        WebElement quizNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_name")));
        quizNameField.sendKeys("Automated Quiz");
    
        // 5️⃣ Click "Save and display"
        WebElement saveAndDisplayButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("id_submitbutton")));
        saveAndDisplayButton.click();
    }
    

    @And("the teacher creates a multiple-choice question")
    public void teacherCreatesMultipleChoiceQuestion() {
        // 1️⃣ Click on the "Question bank" tab
        WebElement questionBankTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Question bank')]")));
        questionBankTab.click();
    
        // 2️⃣ Click "Create a new question"
        WebElement createNewQuestionButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Create a new question')]")));
        createNewQuestionButton.click();
    
        // 3️⃣ Select "Multiple Choice" from the left sidebar
        WebElement multipleChoiceOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='typename' and contains(text(),'Multiple choice')]")));
        multipleChoiceOption.click();
    
        // 4️⃣ Click "Add" button
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit' and @name='submitbutton' and @value='Add']")));
        addButton.click();
    
        // 5️⃣ Enter a question name
        WebElement questionNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_name")));
        questionNameField.sendKeys("Sample Multiple-Choice Question");
    
        // 6️⃣ Switch to Question Text iframe and enter text
        driver.switchTo().frame("id_questiontext_ifr");
        WebElement questionTextArea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tinymce")));
        questionTextArea.sendKeys("What is the capital of France?");
        driver.switchTo().defaultContent(); // Exit iframe
    
        // 7️⃣ Select "Multiple answers allowed"
        WebElement answerTypeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("id_single")));
        answerTypeDropdown.click();
        WebElement multipleAnswersOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='id_single']/option[@value='0']")));
        multipleAnswersOption.click();
    
        // 8️⃣ Switch to Answer 1 iframe and enter text
        driver.switchTo().frame("id_answer_0_ifr");
        WebElement answer1TextArea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tinymce")));
        answer1TextArea.sendKeys("Paris");
        driver.switchTo().defaultContent(); // Exit iframe
    
        // 9️⃣ Switch to Answer 2 iframe and enter text
        driver.switchTo().frame("id_answer_1_ifr");
        WebElement answer2TextArea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tinymce")));
        answer2TextArea.sendKeys("London");
        driver.switchTo().defaultContent(); // Exit iframe
    
        // 🔟 Set Answer 1 grade to 50%
        WebElement gradeDropdown1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("id_fraction_0")));
        gradeDropdown1.click();
        WebElement gradeOption1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='id_fraction_0']/option[@value='0.5']")));
        gradeOption1.click();
    
        // 1️⃣1️⃣ Set Answer 2 grade to 50%
        WebElement gradeDropdown2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("id_fraction_1")));
        gradeDropdown2.click();
        WebElement gradeOption2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='id_fraction_1']/option[@value='0.5']")));
        gradeOption2.click();
    
        // 1️⃣2️⃣ Click "Save changes" button
        WebElement saveChangesButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("id_submitbutton")));
        saveChangesButton.click();
    }
    

    @And("the teacher adds question to quiz")
    public void teacherAddsQuestionToQuiz() {
        // 1️⃣ Click on the "Quiz" tab (same type as "Question Bank" tab)
        WebElement quizTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Quiz')]")));
        quizTab.click();

        // 2️⃣ Click "Add Question" button
        WebElement addQuestionLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'mod/quiz/edit.php')]")));
        addQuestionLink.click();

        // 3️⃣ Click "Add" dropdown menu
        WebElement addDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='add-menu']")));
        addDropdown.click();

        // 4️⃣ Click "From question bank"
        WebElement fromQuestionBank = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='menu-action-text' and contains(text(),'from question bank')]")));
        fromQuestionBank.click();

        // 5️⃣ Select the checkbox for the question
        WebElement questionCheckbox = null;

        for (int i = 1; i <= 10; i++) {
            try {
                questionCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='checkq" + i + "' and @type='checkbox']")));
                questionCheckbox.click();
                System.out.println("Selected question: q" + i);
                break; // Exit loop after finding and clicking the first available question
            } catch (Exception e) {
                System.out.println("Question q" + i + " not found, trying next...");
            }
        }

        // 6️⃣ Click "Add selected questions to the quiz"
        WebElement addSelectedQuestionsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit' and @name='add' and @value='Add selected questions to the quiz']")));
        addSelectedQuestionsButton.click();

        // 7️⃣ Click the "Save" button
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit' and @name='savechanges' and @value='Save']")));
        saveButton.click();
    }

    
    @And("the teacher edits the question to 'One answer only'")
    public void teacherEditsQuestionToOneAnswerOnly() {
        // 1️⃣ Click on the question name "Sample Multiple-Choice Question"
        WebElement questionName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='questionname' and contains(text(),'Sample Multiple-Choice Question')]")));
        questionName.click();
    
        // 2️⃣ Change "Multiple answers allowed" to "One answer only"
        WebElement answerTypeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("id_single")));
        answerTypeDropdown.click();
        WebElement oneAnswerOnlyOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='id_single']/option[@value='1']")));
        oneAnswerOnlyOption.click();
    
        // 3️⃣ Ensure "Shuffle Answers" is unticked (unchecked)
        WebElement shuffleAnswersCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.id("id_shuffleanswers")));
        if (shuffleAnswersCheckbox.isSelected()) { // If checkbox is checked, click to uncheck
            shuffleAnswersCheckbox.click();
        }
    
        // 4️⃣ Set "Paris" grade to 100%
        WebElement gradeDropdown1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("id_fraction_0")));
        gradeDropdown1.click();
        WebElement grade100Option1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='id_fraction_0']/option[@value='1.0']")));
        grade100Option1.click();
    
        // 5️⃣ Set "London" grade to 0%
        WebElement gradeDropdown2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("id_fraction_1")));
        gradeDropdown2.click();
        WebElement grade0Option2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='id_fraction_1']/option[@value='0.0']")));
        grade0Option2.click();
    
        // 6️⃣ Click "Save changes" button
        WebElement saveChangesButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("id_submitbutton")));
        saveChangesButton.click();
    }
    

    @Then("the question should only allow one answer selection")
    public void questionShouldOnlyAllowOneAnswerSelection() {
        // 1️⃣ Click on the "Quiz" tab
        WebElement quizTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Quiz')]")));
        quizTab.click();

        // 2️⃣ Click "Preview quiz" button
        WebElement previewQuizButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Preview quiz')]")));
        previewQuizButton.click();

        // 3️⃣ Select the "Paris" radio button (verifying that only one option can be selected)
        WebElement parisOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='radio' and contains(@name,'answer') and @value='0']")));
        parisOption.click();

        // 4️⃣ Click "Finish attempt ..."
        WebElement finishAttemptButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit' and @name='next' and @value='Finish attempt ...']")));
        finishAttemptButton.click();

        // 5️⃣ Click "Submit all and finish" (first instance)
        WebElement submitAllButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Submit all and finish')]")));
        submitAllButton1.click();

        // 6️⃣ Click "Submit all and finish" (confirmation)
        WebElement submitAllButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-action='save' and contains(text(),'Submit all and finish')]")));
        submitAllButton2.click();

        // 7️⃣ Assert that the grade is 100%
        WebElement gradeCell = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(@class,'cell')]//b[contains(text(),'100')]")));
        assertTrue(gradeCell.isDisplayed(), "Grade is not 100%, test failed.");
    }


    private void setUp() {
        System.out.println(path);
        System.setProperty(webDriver, path);
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://sandbox.moodledemo.net/");
    }

    private void gotoLoginPage() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'login')]"))).click();
    }
}
