package org.mikehenze.alexnova.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.mikehenze.alexnova.library.SelectBrowser;
import org.mikehenze.alexnova.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class LogInTests {

    WebDriver driver;
    LandingPage landingPage;
    LoginPage loginPage;

    AccountPage accountPage;


    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        //create the HtmlReporter in that path by the name of  MyOwnReport.html
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/LogInReport.html");
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


    @Test(priority = 6)
    public void TC0006_user_login_test() {
        test = extent.createTest("TC0006_user_login_test", "Test Pass");
        landingPage = new LandingPage(driver);
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);
        landingPage.clickOnAccountButton();
        loginPage.enterEmail("capstone110@gmail.com");
        loginPage.enterPassword("P@ssword");
        loginPage.submitLogInButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

        String expectedValue = "john fink";
        Assert.assertEquals(expectedValue, accountPage.getUsersNameText());
    }

    @Test(priority = 7)
    public void TC0007_user_login_invalid_email_test() {
        test = extent.createTest("TC0007_user_login_invalid_email_test", "Test Pass");
        landingPage = new LandingPage(driver);
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        landingPage.clickOnAccountButton();
        loginPage.enterEmail("capestone@gmail.com");
        loginPage.enterPassword("P@ssword");
        loginPage.submitLogInButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

        String expected = "Sorry! Please try that again.";
        Assert.assertEquals(expected, loginPage.getSignUpError());
    }

    @AfterSuite
    public void tearDown()
    {
        extent.flush();
    }


}
