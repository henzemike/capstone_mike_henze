package org.mikehenze.alexnova.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductCartPage {

    WebDriver driver;

    //quantity text in cart page
    By productInCartText = By.linkText("Tracy Stripes Mesh First Walker Baby Sandals");

    //remove product from cart
    By removeProduct = By.xpath("//*[@id='shopify-section-cart']/section/form/table/tbody/tr/td[1]/a[2]");

    //product price
    By productPrice = By.xpath("/html/body/div[3]/div/section/form/table/tbody/tr/td[2]/span/span");


    //product total price from quantity of products
    By productTotalPrice = By.xpath("/html/body/div[3]/div/section/form/table/tbody/tr/td[4]/span/span");

    //select product quantity
    By productQuantity = By.name("updates[]");

    //update cart button
    By updateCart = By.name("update");

    //cart value 0
    By cartValue = By.xpath("/html/body/div[2]/section/header/div[1]/div/div[2]/div[2]/a/span[2]");

    //checkout button
    By checkout = By.name("checkout");


    public ProductCartPage(WebDriver driver) {
        this.driver = driver;
    }


    // method to get text value of quantity in cart
    public String getQuantityInCart() {
       return driver.findElement(productInCartText).getText();
    }

    // method to remove product from cart
    public void clickRemoveProduct() {
        driver.findElement(removeProduct).click();
    }

    //method to get product quantity value
    public String productQuantityValue() {
        WebElement quantity = driver.findElement(productQuantity);
        String quantityNumber = quantity.getAttribute("value");
        return quantityNumber;
    }

    //method to get text value of products price
    public String getProductPrice() {
        WebElement price = driver.findElement(productPrice);
        String prodPrice = price.getAttribute("innerHTML");
        return prodPrice;
    }

    //method to get total price of products in cart
    public String getTotalProductPrice() {
        WebElement totalPrice = driver.findElement(productTotalPrice);
        String prodTotalPrice = totalPrice.getAttribute("innerHTML");
        return prodTotalPrice;
    }

    //method to select product quantity
    public void selectProductQuantity(String number) {
        driver.findElement(productQuantity).sendKeys(number);
    }

    //method to click update cart
    public void submitUpdateCart() {
        driver.findElement(updateCart).submit();
    }

    //method to get cart value
    public String getCartValue() {
        WebElement value = driver.findElement(cartValue);
        String cartAmount = value.getAttribute("innerHTML");
        return cartAmount;
    }

    //method to scroll down to make checkout button visible
    public void scrollToCheckoutButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 100)", "");
    }

    //method to click checkout button
    public void submitCheckout() {
        driver.findElement(checkout).click();
    }


}
