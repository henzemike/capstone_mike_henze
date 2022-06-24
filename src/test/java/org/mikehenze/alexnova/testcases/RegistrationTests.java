package org.mikehenze.alexnova.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.mikehenze.alexnova.library.SelectBrowser;
import org.mikehenze.alexnova.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class RegistrationTests {

    WebDriver driver;
    LandingPage landingPage;
    LoginPage loginPage;

    RegisterPage registerPage;

    AccountPage accountPage;


    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        //create the HtmlReporter in that path by the name of  MyOwnReport.html
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/RegistrationReport.html");
        extent = new ExtentReports();

        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "mike.home-server.local");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Mike Henze");
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Automation Testing Registration Report");
        htmlReporter.config().setReportName("Automation Testing Registration Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
    }

    @BeforeTest
    public void launchBrowser() {
        driver = SelectBrowser.StartBrowser("Chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));
        driver.get("https://www.alexandnova.com/");
    }

    @Test(priority = 1)
    public void TC0001_register_page_test() {
        test = extent.createTest("TC0001_register_page_test", "Test Pass");
        landingPage = new LandingPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        landingPage.clickOnAccountButton();
        loginPage.scrollToRegisterButton();
        loginPage.clickOnRegisterButton();

        String expectedValue = "Sign up";
        Assert.assertEquals(expectedValue, registerPage.getSignUpText());
    }

    @Test(priority = 2)
    public void TC0002_register_user_test() throws InterruptedException {
        test = extent.createTest("TC0002_register_user_test", "Test Pass");
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        accountPage = new AccountPage(driver);
        registerPage.enterFirstName("john");
        registerPage.enterLastName("fink");
        registerPage.enterEmail("mhenze","@gmail.com");
        registerPage.enterPassword("P@ssword");
        registerPage.submitRegisterButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        loginPage.clickAccountButton();

        String expectedValue = "Welcome, john";
        Assert.assertEquals(expectedValue, accountPage.getWelcomeText());
    }

    @Test(priority = 3)
    public void TC0003_email_validation_error_test() {
        test = extent.createTest("TC0003_email_validation_error_test", "Test Pass");
        landingPage = new LandingPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        landingPage.clickOnAccountButton();
        loginPage.scrollToRegisterButton();
        loginPage.clickOnRegisterButton();
        registerPage.enterFirstName("john");
        registerPage.enterLastName("fink");
        registerPage.enterEmail("test", "Atgmail.com ");
        registerPage.enterPassword("P@ssword");
        registerPage.submitRegisterButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

        String expected = "Sorry! Please try that again.";
        Assert.assertEquals(expected, loginPage.getSignUpError());
    }

    @Test(priority = 4)
    public void TC0004_registration_mandatory_fields_test() {
        test = extent.createTest("TC0004_registration_mandatory_fields_test", "Test Pass");
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        loginPage.scrollToRegisterButton();
        loginPage.clickOnRegisterButton();
        registerPage.enterFirstName("john");
        registerPage.enterLastName("fink");
        registerPage.enterEmail("", "");
        registerPage.enterPassword("");
        registerPage.submitRegisterButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

        String expected = "Sorry! Please try that again.";
        Assert.assertEquals(expected, loginPage.getSignUpError());
    }

    @Test(priority = 5)
    public void TC0005_password_requirements_test() throws IOException {
        test = extent.createTest("TC0005_password_requirements_test", "Test Pass");
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        loginPage.scrollToRegisterButton();
        loginPage.clickOnRegisterButton();
        registerPage.enterFirstName("john");
        registerPage.enterLastName("fink");
        registerPage.enterEmail("mhenze", "@gmail.com");
        registerPage.enterPassword("passw");
        registerPage.submitRegisterButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

        String expected = "passw is invalid.";
        if (expected != loginPage.getSignUpError()) {
            File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("/Users/MikeHenze/Documents/ActivateIT/Capestone/Henze_Mike_Capstone_AlexNova/test-output/passwordValidation.png"));
            Assert.assertEquals(expected, loginPage.getSignUpError());
        } else {
            Assert.assertEquals(expected, loginPage.getSignUpError());
        }

    }

    @AfterClass
    public void quitBrowser() {
        driver.quit();
    }


    @AfterSuite
    public void tearDown()
    {
        extent.flush();
    }

}
