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

public class CheckoutTests {

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
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/CheckoutProductReport.html");
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

    @Test(priority = 17)
    public void TC0017_verify_payment_options_test() throws InterruptedException, IOException {
        test = extent.createTest("TC0017_verify_payment_options_test", "Test Pass");
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
        Thread.sleep(3000);
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("continue_button")));
        checkoutPage.enterAddress("123 main");
        checkoutPage.enterCity("lone tree");
        checkoutPage.enterZip("80124");
        checkoutPage.clickCheckoutButton();
        Thread.sleep(400);
        checkoutPage.clickCheckoutButton();

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("/Users/MikeHenze/Documents/ActivateIT/Capestone/Henze_Mike_Capstone_AlexNova/test-output/CreditCardOptions.png"));
        String expectedShopPay = "Shop Pay";
        String expectedPayPal = "PayPal";
        Assert.assertEquals(checkoutPage.getShopPayText(), expectedShopPay);
        Assert.assertEquals(checkoutPage.getPayPalText(), expectedPayPal);
    }

    @Test(priority = 18)
    public void TC0018_check_payment_validation_test() throws InterruptedException {
        test = extent.createTest("TC0018_check_payment_validation_test", "Test Pass");
        checkoutPage = new CheckoutPage(driver);
        Thread.sleep(300);
        checkoutPage.enterNameOnCard("john fink");
        checkoutPage.enterExpOnCard("05/23");
        checkoutPage.enterSecurityCodeOnCard("5789");
        checkoutPage.clickCheckoutButton();

        String expected = "Your payment details couldn???t be verified. Check your card details and try again.";
        Assert.assertEquals(checkoutPage.getPaymentErrorMessage(), expected);
    }

    @Test(priority = 19)
    public void TC0019_check_payment_page_has_pay_button_test() {
        test = extent.createTest("TC0019_check_payment_page_has_pay_button_test", "Test Pass");
        checkoutPage = new CheckoutPage(driver);
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='continue_button']")));

        String expected = "Pay now";
        Assert.assertEquals(checkoutPage.getPayNowText(), expected);
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
