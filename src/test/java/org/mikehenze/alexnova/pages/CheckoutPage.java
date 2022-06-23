package org.mikehenze.alexnova.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {

    WebDriver driver;

    //gift card input field
    By giftCard = By.id("checkout_reduction_code");

    // apply gift card button
    By applyDiscount = By.name("checkout[submit]");

    //    subtotal of product
    By subtotal = By.xpath("/html/body/div[1]/div/aside/div[2]/div[1]/div/div[3]/table/tbody/tr[1]/td/span");

    //total of product with discount
    By total = By.xpath("/html/body/div[1]/div/aside/div[2]/div[1]/div/div[3]/table/tfoot/tr/td/span[2]");

    //shipping address field
    By addressField = By.id("checkout_shipping_address_address1");

    //shipping city field
    By cityField = By.id("checkout_shipping_address_city");

    //shipping zip field
    By zipField = By.id("checkout_shipping_address_zip");

    //continue to shipping button and payment
    By continueCheckoutButton = By.id("continue_button");

    //path to shop pay text
    By shopPay = By.xpath("/html/body/div/div/div/main/div[1]/div/form/div[1]/div[2]/div[2]/fieldset/div[4]/div[2]/label/span");

    //path to payPal text
    By payPal = By.xpath("/html/body/div/div/div/main/div[1]/div/form/div[1]/div[2]/div[2]/fieldset/div[6]/div[2]/label/span");

    //iframe for card number input field
    By cardNumberFrame = By.xpath("*//iframe[contains(@id, 'card-fields-number')]");

    //number field on card
    By numberOnCard = By.id("number");

    //iframe for card name input field
    By cardNameFrame = By.xpath("*//iframe[contains(@id, 'card-fields-name')]");

    //name field on card
    By nameOnCard = By.id("name");

    //iframe for card expiration date field
    By cardExpFrame = By.xpath("*//iframe[contains(@id, 'card-fields-expiry')]");

    //expiration date for card
    By expOnCard = By.id("expiry");

    //iframe for card security code input field
    By cardSecurityCodeFrame = By.xpath("*//iframe[contains(@id, 'card-fields-verification_value')]");

    //security code on card
    By securityCodeOnCard = By.id("verification_value");

    //path for credit card field error message
    By paymentErrorMessage = By.xpath("/html/body/div/div/div/main/div[1]/div/form/div[1]/div[2]/div[2]/div/p");

    //pay now button text value
    By payNowText = By.xpath("//*[@id='continue_button']/span");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }


    //method to enter gift card code
    public void enterGiftCard(String discount) {
        driver.findElement(giftCard).sendKeys(discount);
    }

    //method to click discount button
    public void applyDiscountCode() {
        driver.findElement(applyDiscount).click();
    }

    //method to get value of total of product
    public String getTotal() {
        WebElement totalValue = driver.findElement(total);
        String productTotalValue = totalValue.getAttribute("innerHTML").trim();
        return productTotalValue;
    }

    //method to get subtotal value after discount code
    public String getSubTotal() {
        WebElement subtotalValue = driver.findElement(subtotal);
        String productSubtotalValue = subtotalValue.getAttribute("innerHTML").trim();
        return productSubtotalValue;
    }

    //method to enter address in field
    public void enterAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    //method to enter city in field
    public void enterCity(String city) {
        driver.findElement(cityField).sendKeys(city);
    }

    //method to enter zip in field
    public void enterZip(String zip) {
        driver.findElement(zipField).sendKeys(zip);
    }

    //method to click checkout button
    public void clickCheckoutButton() {
        driver.findElement(continueCheckoutButton).click();
    }

    //method to get text value of shop pay
    public String getShopPayText() {
        WebElement shopPayPath = driver.findElement(shopPay);
        String shopPayText = shopPayPath.getAttribute("innerHTML").trim();
        return shopPayText;
    }

    //method to get text value of paypal
    public String getPayPalText() {
        WebElement payPalPath = driver.findElement(payPal);
        String payPalText = payPalPath.getAttribute("innerHTML").trim();
        return payPalText;
    }

    //method to get text value of payment error message
    public String getPaymentErrorMessage() {
        WebElement errorMessage = driver.findElement(paymentErrorMessage);
        String paymentError = errorMessage.getAttribute("innerHTML");
        return paymentError;
    }


    //method to enter number on card
    public void enterNumberOnCard(String number){
        driver.switchTo().frame(driver.findElement(cardNumberFrame));
        driver.findElement(numberOnCard).sendKeys(number);
        driver.switchTo().parentFrame();
    }

    //method to enter name on card
    public void enterNameOnCard(String name) {
        driver.switchTo().frame(driver.findElement(cardNameFrame));
        driver.findElement(nameOnCard).sendKeys(name);
        driver.switchTo().parentFrame();
    }

    //method to enter expiration on card
    public void enterExpOnCard(String exp) {
        driver.switchTo().frame(driver.findElement(cardExpFrame));
        driver.findElement(expOnCard).sendKeys(exp);
        driver.switchTo().parentFrame();
    }

    //method to enter security code on card
    public void enterSecurityCodeOnCard(String code) {
        driver.switchTo().frame(driver.findElement(cardSecurityCodeFrame));
        driver.findElement(securityCodeOnCard).sendKeys(code);
        driver.switchTo().parentFrame();
    }

    //method to get text value from pay now button
    public String getPayNowText() {
        WebElement payNowPath = driver.findElement(payNowText);
        String payNowTextValue = payNowPath.getAttribute("innerHTML");
        return payNowTextValue;
    }


}
