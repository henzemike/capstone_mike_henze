package org.mikehenze.alexnova.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage {

    WebDriver driver;


    //link text to grab register button
    By registerButton = By.xpath("//*[@id='keyboard-nav-3']/div[2]/div[3]/a");

    //link text to get account button
    By accountButton = By.linkText("My account");

    // login / registration error message
    By signUpErrorMessage = By.xpath("//*[@id='customer_login']/p");

    // login email field
    By emailField = By.id("ispbxii_1");

    //login password field
    By passwordField = By.name("customer[password]");

    // login button
    By logInButton = By.xpath("//*[@id='customer_login']/div[3]/input");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //method to scroll down to register button
    public void scrollToRegisterButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement registerElement = driver.findElement(By.xpath("//*[@id='keyboard-nav-3']/div[2]/div[3]/p"));
        js.executeScript("arguments[0].scrollIntoView();", registerElement);
    }

    //method to click on register button for register page
    public void clickOnRegisterButton() {
        driver.findElement(registerButton).click();
    }

    //method to click account button
    public void clickAccountButton() {
        driver.findElement(accountButton).click();
    }

    //method to get text of error message for invalid fields
    public String getSignUpError() {
        return driver.findElement(signUpErrorMessage).getText();
    }

    // method to enter email for login
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    //method to enter password for login
    public void  enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    //method to submit login button
    public void submitLogInButton() {
        driver.findElement(logInButton).submit();
    }


}
