package org.mikehenze.alexnova.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.mikehenze.alexnova.library.SelectBrowser;
import org.mikehenze.alexnova.pages.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class ProductCartTests {

    WebDriver driver;

    LandingPage landingPage;

    LoginPage loginPage;

    AccountPage accountPage;

    SearchPage searchPage;

    ProductPage productPage;

    ProductCartPage productCartPage;

    private static ExtentReports extent;
    private static ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        //create the HtmlReporter in that path by the name of  MyOwnReport.html
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/ProductCartReport.html");
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

    @Test(priority = 10)
    public void TC0010_check_product_page_price_test() {
        test = extent.createTest("TC0010_check_product_page_price_test", "Test Pass");
        landingPage = new LandingPage(driver);
        searchPage = new SearchPage(driver);
        productPage = new ProductPage(driver);
        landingPage.searchItemField("baby shoes");
        landingPage.clickItemSearch();
        searchPage.clickProductImage();

        String expected = "$34.95 USD";
        Assert.assertEquals(expected, productPage.checkPrice());
    }

    @Test(priority = 11)
    public void TC0011_add_product_to_cart_test() {
        test = extent.createTest("TC0010_check_product_page_price_test", "Test Pass");
        productPage = new ProductPage(driver);
        productCartPage = new ProductCartPage(driver);
        landingPage = new LandingPage(driver);
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);
        searchPage = new SearchPage(driver);
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

        String expected = "Tracy Stripes Mesh First Walker Baby Sandals";
        Assert.assertEquals(expected, productCartPage.getQuantityInCart());
    }

    @Test(priority = 12)
    public void TC0012_refresh_page_checking_items_still_in_cart_test() {
        test = extent.createTest("TC0012_refresh_page_checking_items_still_in_cart_test", "Test Pass");
        productCartPage = new ProductCartPage(driver);
        driver.navigate().refresh();

        String expected = "Tracy Stripes Mesh First Walker Baby Sandals";
        Assert.assertEquals(expected, productCartPage.getQuantityInCart());
    }

    @Test(priority = 13)
    public void TC0013_check_product_quantity_in_cart_test(){
        test = extent.createTest("TC0013_check_product_quantity_in_cart_test", "Test Pass");
        landingPage = new LandingPage(driver);
        searchPage = new SearchPage(driver);
        productPage = new ProductPage(driver);
        productCartPage = new ProductCartPage(driver);
        productCartPage.clickRemoveProduct();
        landingPage.searchItemField("baby shoes");
        landingPage.clickItemSearch();
        searchPage.clickProductImage();
        productPage.scrollToCartButton();
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='product_form_6831262531637']/div[4]/input")));
        productPage.selectProductSize();
        productPage.selectProductColor();
        WebElement quantity = driver.findElement(By.id("ispbxii_1"));
        quantity.clear();
        productPage.selectProductQuantity("2");
        productPage.addToCart();
        productPage.clickOpenCart();

        String expected = "2";
        Assert.assertEquals(expected, productCartPage.productQuantityValue());
    }

    @Test(priority = 14)
    public void TC0014_product_price_in_cart_quantity_test() throws IOException {
        test = extent.createTest("TC0014_product_price_in_cart_quantity_test", "Test Pass");
        productCartPage = new ProductCartPage(driver);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("/Users/MikeHenze/Documents/ActivateIT/Capestone/Henze_Mike_Capstone_AlexNova/test-output/ProductQuantityPrice.png"));
        String expectedPrice = "$34.95 USD";
        String expectedTotalPrice = "$69.90 USD";
        Assert.assertEquals(expectedPrice, productCartPage.getProductPrice());
        Assert.assertEquals(expectedTotalPrice, productCartPage.getTotalProductPrice());
    }

    @Test(priority = 15)
    public void TC0015_remove_product_from_cart_test() {
        test = extent.createTest("TC0015_remove_product_from_cart_test", "Test Pass");
        productCartPage = new ProductCartPage(driver);
        WebElement quantity = driver.findElement(By.id("updates_40723160268853"));
        quantity.clear();
        productCartPage.selectProductQuantity("0");
        productCartPage.submitUpdateCart();

        String expected = "0";
        Assert.assertEquals(expected, productCartPage.getCartValue());
    }


    @AfterSuite
    public void tearDown()
    {
        extent.flush();
    }

}
