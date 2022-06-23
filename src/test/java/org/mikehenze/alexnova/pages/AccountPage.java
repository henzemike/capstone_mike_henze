package org.mikehenze.alexnova.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {

    WebDriver driver;


    //xpath for account welcome text change to xpath
    By welcomeText = By.xpath("//*[@id='keyboard-nav-3']/h1");

    //users name text
    By usersNameText = By.xpath("//*[@id='keyboard-nav-3']/div[2]/div[1]/p[1]/span[1]");


    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }


    //method to get welcome text from element
    public String getWelcomeText() {
        return driver.findElement(welcomeText).getText();
    }

    // method to get users name text from element
    public String getUsersNameText() {
        return driver.findElement(usersNameText).getText();
    }


}
