package org.mikehenze.alexnova.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage {

    WebDriver driver;

    //search text value
    By searchText = By.xpath("//*[@id='keyboard-nav-3']/h1/span[2]");

    //blank search text value
    By blankSearchValue = By.xpath("//*[@id='isp_search_results_container']/li[1]");

    // product image path
    By productImage = By.xpath("//*[@id='isp_search_results_container']/li[1]/div[2]/a");



    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    //method to get access to text value from searched item
    public String itemText() {
        return driver.findElement(searchText).getText();
    }

    //method to get text value from blank search field
    public String blankSearchText() {
        return driver.findElement(blankSearchValue).getText();
    }

    //method to click product image for product page
    public void clickProductImage() {
        driver.findElement(productImage).click();
    }



}
