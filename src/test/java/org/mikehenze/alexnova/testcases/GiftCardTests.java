package org.mikehenze.alexnova.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.mikehenze.alexnova.library.SelectBrowser;
import org.mikehenze.alexnova.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class GiftCardTests {

    WebDriver driver;

    LandingPage landingPage;

    LoginPage loginPage;

    AccountPage accountPage;

    SearchPage searchPage;

    ProductPage productPage;

    ProductCartPage productCartPage;

    CheckoutPage checkoutPage;

    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        //create the HtmlReporter in that path by the name of  MyOwnReport.html
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/GiftCardReport.html");
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

    @Test(priority = 16)
    public void TC0016_discount_code_test() {
        test = extent.createTest("TC0016_discount_code_test", "Test Pass");
        productPage = new ProductPage(driver);
        productCartPage = new ProductCartPage(driver);
        landingPage = new LandingPage(driver);
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);
        searchPage = new SearchPage(driver);
        checkoutPage = new CheckoutPage(driver);
        landingPage.clickOnAccountButton();
        loginPage.enterEmail("capstone110@gmail.com");
        loginPage.enterPassword("P@ssword");
        loginPage.submitLogInButton();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='keyboard-nav-3']/div[2]/div[1]/p[1]/span[1]")));
        landingPage.searchItemField("baby shoes");
        landingPage.clickItemSearch();
        searchPage.clickProductImage();
        productPage.scrollToCartButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='product_form_6831262531637']/div[4]/input")));
        productPage.selectProductSize();
        productPage.selectProductColor();
        productPage.addToCart();
        productPage.clickOpenCart();
        productCartPage.scrollToCheckoutButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='shopify-section-cart']/section/form/div/div/div[2]/button/span")));
        productCartPage.submitCheckout();
        checkoutPage.enterGiftCard("OHBABY15");
        checkoutPage.applyDiscountCode();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/aside/div[2]/div[1]/div/div[3]/table/tbody/tr[2]/th/span[2]/span")));

        String expectedSubtotal = "$34.95";
        String expectedTotal = "$29.71";
        Assert.assertEquals(expectedSubtotal, checkoutPage.getSubTotal());
        Assert.assertEquals(expectedTotal, checkoutPage.getTotal());
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
