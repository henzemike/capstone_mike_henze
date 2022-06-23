package org.mikehenze.alexnova.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage {

    WebDriver driver;

    // linkText to get account button
    By accountButton = By.linkText("Account");

    //search input field
    By searchField = By.id("ispbxii_0");

    // submit button for search field
    By clickSearch = By.xpath("//*[@id='shopify-section-header']/section/header/div[2]/div[4]/form/input[2]");

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    //Method to click account button
    public void clickOnAccountButton() {
        driver.findElement(accountButton).click();
    }

    //method for search field
    public void searchItemField(String item) {
        driver.findElement(searchField).sendKeys(item);
    }

    public void clickItemSearch() {
        driver.findElement(clickSearch).click();
    }



}
