package org.mikehenze.alexnova.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.mikehenze.alexnova.library.SelectBrowser;
import org.mikehenze.alexnova.pages.LandingPage;
import org.mikehenze.alexnova.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class SearchProductTests {

    WebDriver driver;
    LandingPage landingPage;
    SearchPage searchPage;


    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        //create the HtmlReporter in that path by the name of  MyOwnReport.html
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/SearchProductReport.html");
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



    @Test(priority = 8)
    public void TC0008_correct_item_search_results_test() {
        test = extent.createTest("TC0008_correct_item_search_results_test", "Test Pass");
        landingPage = new LandingPage(driver);
        searchPage = new SearchPage(driver);
        landingPage.searchItemField("baby shoes");
        landingPage.clickItemSearch();

        String expected = "baby shoes";
        Assert.assertEquals(expected, searchPage.itemText());
    }

    @Test(priority = 9)
    public void TC0009_blank_search_result_test() {
        test = extent.createTest("TC0009_blank_search_result_test", "Test Pass");
        landingPage = new LandingPage(driver);
        searchPage = new SearchPage(driver);
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        landingPage.searchItemField("");
        landingPage.clickItemSearch();

        String expected = "No results found. Showing top popular products you might want to consider...";
        Assert.assertEquals(expected, searchPage.blankSearchText());
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
