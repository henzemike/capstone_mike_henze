package org.mikehenze.alexnova.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {

    WebDriver driver;

    // path to quick view image
    By quickViewImage = By.xpath("//button[@id='isp_product_quick_view_close_button_quick_view']");

    //path to price on product page
    By productPrice = By.xpath("//*[@id='shopify-section-product']/section/div/div[2]/div/p/span[1]");

    //select product size
    By productSize = By.xpath("//*[@id='bcpo-select-option-0']/div[5]/label");

    //select product color
    By productColor = By.xpath("//*[@id='bcpo-select-option-1']/div[1]/label");

    //select product quantity
    By productQuantity = By.name("quantity");

    // add to cart button
    By cartButton = By.xpath("//*[contains(@id, 'product_form_')]/div[4]/input");

    //open cart button
    By openCart = By.xpath("//*[@id='shopify-section-header']/section/header/div[1]/div/div[2]/div[2]/a");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // method to check price on product page
    public String checkPrice() {
        return driver.findElement(productPrice).getText();
    }

    //method to select product size
    public void selectProductSize() {
        driver.findElement(productSize).click();
    }

    //method to select product color
    public void selectProductColor() {
        driver.findElement(productColor).click();
    }

    //method to select product quantity
    public void selectProductQuantity(String number) {
        driver.findElement(productQuantity).sendKeys(number);
    }

    //method to add product to cart
    public void addToCart() {
        driver.findElement(cartButton).submit();
    }

    //method to open the cart
    public void clickOpenCart() {
        driver.findElement(openCart).click();
    }

    //scroll for cart button to be in view
    public void scrollToCartButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 100)", "");
    }

}
