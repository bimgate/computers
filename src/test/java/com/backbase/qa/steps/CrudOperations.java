package com.backbase.qa.steps;

import com.backbase.qa.resources.HttpClientGet;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

import static com.backbase.qa.helpers.Element.*;
import static com.backbase.qa.utils.WebDriver.getFirefoxDriver;

/**
 * Created by bratislav.miletic on 09/11/2017.
 */
public class CrudOperations {
    HttpClientGet client = new HttpClientGet();
    int getResponseCode;
    FirefoxDriver driver = getFirefoxDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10);
    int totalSumValueInt;

    @Given("^server API is up and running on \"([^\"]*)\"$")
    public void serverAPIIsUpAndRunningOn(String url) throws Throwable {
        getResponseCode = client.getResponseCode(url);
    }

    @And("^response code is (\\d+)$")
    public void responseCodeIs(int code) throws Throwable {
        Assert.assertEquals(getResponseCode, code, "different response code");
    }

    @When("^user navigate to \"([^\"]*)\"$")
    public void userNavigateTo(String url) throws Throwable {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
    }

    @And("^get data about Total sum of created computers in Heading text$")
    public void getDataAboutTotalSumOfCreatedComputersInHeadingText() throws Throwable {
       String headingText = driver.findElementByXPath(TOTAL_SUM).getText();
       String totalSumValue = headingText.replaceAll("[^0-9]", "");
       totalSumValueInt = Integer.parseInt(totalSumValue);

    }

    @Then("^landing page appears with elements: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void landingPageAppearsWithElements(String titleVal, String subtitleVal, String searchFieldVal, String searchButtonVal, String addButtonVal, String tabColumnIVal, String tabColumnIIVal, String tabColumnIIIVal, String tabColumnIVVal) throws Throwable {
       Assert.assertEquals(driver.getTitle(),titleVal);
       Assert.assertEquals(driver.findElementByXPath(SUBTITLE).getText(),subtitleVal);
       Assert.assertEquals(driver.findElementById(SEARCH_FIELD_ID).getTagName(),searchFieldVal);
       Assert.assertEquals(driver.findElementById(SEARCH_BUTTON_ID).getAttribute("value"),searchButtonVal);
       Assert.assertEquals(driver.findElementById(ADD_BUTTON_ID).getText(),addButtonVal);
       Assert.assertEquals(driver.findElementByXPath(COMPUTER_NAME).getText(),tabColumnIVal);
       Assert.assertEquals(driver.findElementByXPath(INTRODUCED).getText(),tabColumnIIVal);
       Assert.assertEquals(driver.findElementByXPath(DISCONTINUED).getText(),tabColumnIIIVal);
       Assert.assertEquals(driver.findElementByXPath(COMPANY).getText(),tabColumnIVVal);
    }

    @When("^user click on button \"([^\"]*)\"$")
    public void userClickOnButton(String add) throws Throwable {
       driver.findElementByXPath(ADD_NEW_COMPUTER).click();
    }

    @And("^new page appears with elements: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" , \"([^\"]*)\"$")
    public void newPageAppearsWithElements(String headingTextVal, String formFieldIVal, String formFieldIIVal, String formFieldIIIVal, String formFieldIVVal, String buttonIVal, String buttonIIVal) throws Throwable {
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(HEADING_TEXT)));
       Assert.assertEquals(driver.findElementByXPath(HEADING_TEXT).getText(),headingTextVal);
       Assert.assertEquals(driver.findElementById(COMPUTER_NAME_FIELD_ID).getAttribute("name"),formFieldIVal);
       Assert.assertEquals(driver.findElementById(INTRODUCED_FIELD_ID).getAttribute("name"),formFieldIIVal);
       Assert.assertEquals(driver.findElementById(DISCONTINUED_FIELD_ID).getAttribute("name"),formFieldIIIVal);
       Assert.assertEquals(driver.findElementById(COMPANY_FIELD_ID).getAttribute("name"),formFieldIVVal);
       Assert.assertEquals(driver.findElementByXPath(CREATE_COMPUTER_BUTTON).getAttribute("value"),buttonIVal);
       Assert.assertEquals(driver.findElementByXPath(CANCEL_BUTTON).getAttribute("href"),buttonIIVal);
    }

    @And("^populate Computer name with name info \"([^\"]*)\"$")
    public void populateComputerNameWithNameInfo(String nameVal) throws Throwable {
        driver.findElementById(COMPUTER_NAME_FIELD_ID).clear();
        driver.findElementById(COMPUTER_NAME_FIELD_ID).sendKeys(nameVal);
    }

    @And("^populate introduced with date info \"([^\"]*)\"$")
    public void populateIntroducedWithDateInfo(String intVal) throws Throwable {
        driver.findElementById(INTRODUCED_FIELD_ID).clear();
        driver.findElementById(INTRODUCED_FIELD_ID).sendKeys(intVal);
    }

    @And("^populate discontinued date with date info \"([^\"]*)\"$")
    public void populateDiscontinuedDateWithDateInfo(String discVal) throws Throwable {
        driver.findElementById(DISCONTINUED_FIELD_ID).clear();
        driver.findElementById(DISCONTINUED_FIELD_ID).sendKeys(discVal);
    }

    @And("^select company from drop-down list \"([^\"]*)\"$")
    public void selectCompanyFromDropDownList(String compVal) throws Throwable {
        driver.findElementById(COMPANY_FIELD_ID).sendKeys(compVal);
    }

    @And("^Click on button Create this computer$")
    public void clickOnButtonCreateThisComputer() throws Throwable {
        driver.findElementByXPath(CREATE_COMPUTER_BUTTON).click();
    }

    @Then("^landing page appears with message \"([^\"]*)\"$")
    public void landingPageAppearsWithMessage(String allertMessage) throws Throwable {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SUBTITLE)));
        Assert.assertEquals(driver.findElementByXPath(ALLERT_MSG).getText(), allertMessage);
    }

    @And("^Total sum of created computers in Heading text is greater for one$")
    public void totalSumOfCreatedComputersInHeadingTextIsGreaterForOne() throws Throwable {
        String headingText = driver.findElementByXPath(TOTAL_SUM).getText();
        String totalSumValue = headingText.replaceAll("[^0-9]", "");
        int totalSumValueIntNew = Integer.parseInt(totalSumValue);
        Assert.assertEquals(totalSumValueInt+1,totalSumValueIntNew);
    }

    @And("^Click on button Cancel$")
    public void clickOnButtonCancel() throws Throwable {
        driver.findElementByXPath(CANCEL_BUTTON).click();
    }

    @Then("^landing page appears$")
    public void landingPageAppears() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
    }

    @And("^Total sum of created computers in Heading text is unchanged.$")
    public void totalSumOfCreatedComputersInHeadingTextIsUnchanged() throws Throwable {
        String headingText = driver.findElementByXPath(TOTAL_SUM).getText();
        String totalSumValue = headingText.replaceAll("[^0-9]", "");
        int totalSumValueIntNew = Integer.parseInt(totalSumValue);
        Assert.assertEquals(totalSumValueInt,totalSumValueIntNew);
    }

    @Then("^alert appears for required field Computer name$")
    public void alertAppearsForRequiredFieldComputerName() throws Throwable {
        Assert.assertEquals(driver.findElementByXPath(ALLERT_REQ_RED).getCssValue("color"),"rgb(157, 38, 29)");
    }

    @And("^populate \"([^\"]*)\" with date info \"([^\"]*)\"$")
    public void populateWithDateInfo(String dateFirst, String dateSecond) throws Throwable {
        driver.findElementById(INTRODUCED_FIELD_ID).sendKeys(dateFirst);
        driver.findElementById(DISCONTINUED_FIELD_ID).sendKeys(dateSecond);
    }

    @Then("^alert appears for fields \"([^\"]*)\", \"([^\"]*)\"$")
    public void alertAppearsForFields(String dateFirst, String dateSecond) throws Throwable {
       Assert.assertEquals(driver.findElementByXPath(ALLERT_DATE_I_RED).getCssValue("color"), "rgb(157, 38, 29)");
       Assert.assertEquals(driver.findElementByXPath(ALLERT_DATE_II_RED).getCssValue("color"), "rgb(157, 38, 29)");
    }

    @When("^Populate Search field with \"([^\"]*)\"$")
    public void populateSearchFieldWith(String name) throws Throwable {
        driver.findElementById(SEARCH_FIELD_ID).clear();
        driver.findElementById(SEARCH_FIELD_ID).sendKeys(name);
    }

    @And("^Click on button Filter by name$")
    public void clickOnButtonFilterByName() throws Throwable {
        driver.findElementById(SEARCH_BUTTON_ID).click();
    }

    @And("^only computer with requested name is shown \"([^\"]*)\"$")
    public void onlyComputerWithRequestedNameIsShown(String name) throws Throwable {
        Assert.assertEquals(driver.findElementByXPath(COMPUTER_NAME_ONE).getText(),name);
    }

    @When("^click on Computer name \"([^\"]*)\"$")
    public void clickOnComputerName(String arg0) throws Throwable {
        driver.findElementByXPath(COMPUTER_NAME_ONE).click();
    }

    @And("^new page appears with elements: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" , \"([^\"]*)\", \"([^\"]*)\"$")
    public void newPageAppearsWithElements(String headingTextVal, String formFieldIVal, String formFieldIIVal, String formFieldIIIVal, String formFieldIVVal, String buttonIVal, String buttonIIVal, String buttonIIIVal) throws Throwable {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(HEADING_TEXT)));
        Assert.assertEquals(driver.findElementByXPath(HEADING_TEXT).getText(),headingTextVal);
        Assert.assertEquals(driver.findElementById(COMPUTER_NAME_FIELD_ID).getAttribute("name"),formFieldIVal);
        Assert.assertEquals(driver.findElementById(INTRODUCED_FIELD_ID).getAttribute("name"),formFieldIIVal);
        Assert.assertEquals(driver.findElementById(DISCONTINUED_FIELD_ID).getAttribute("name"),formFieldIIIVal);
        Assert.assertEquals(driver.findElementById(COMPANY_FIELD_ID).getAttribute("name"),formFieldIVVal);
        Assert.assertEquals(driver.findElementByXPath(CREATE_COMPUTER_BUTTON).getAttribute("value"),buttonIVal);
        Assert.assertEquals(driver.findElementByXPath(CANCEL_BUTTON).getAttribute("href"),buttonIIVal);
        Assert.assertEquals(driver.findElementByXPath(DELETE_COMP).getAttribute("value"),buttonIIIVal);

    }

    @And("^populate Introduced date with date info \"([^\"]*)\"$")
    public void populateIntroducedDateWithDateInfo(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^Click on button Save this computer$")
    public void clickOnButtonSaveThisComputer() throws Throwable {
       driver.findElementByXPath(SAVE_COMPUTER_BTN).click();
    }

    @And("^new page appears with title \"([^\"]*)\"$")
    public void newPageAppearsWithTitle(String titleEdit) throws Throwable {
       Assert.assertEquals(driver.findElementByXPath(EDIT_COMP).getText(), titleEdit);
    }

    @And("^Click on button Delete this computer$")
    public void clickOnButtonDeleteThisComputer() throws Throwable {
        driver.findElementByXPath(DELETE_COMP).click();
    }

    @And("^Total sum of created computers in Heading text is less for one$")
    public void totalSumOfCreatedComputersInHeadingTextIsLessForOne() throws Throwable {
        String headingText = driver.findElementByXPath(TOTAL_SUM).getText();
        String totalSumValue = headingText.replaceAll("[^0-9]", "");
        int totalSumValueIntNew = Integer.parseInt(totalSumValue);
        Assert.assertEquals(totalSumValueInt-1,totalSumValueIntNew);
    }

    @And("^close browser$")
    public void closeBrowser() throws Throwable {
       driver.close();
    }
}
