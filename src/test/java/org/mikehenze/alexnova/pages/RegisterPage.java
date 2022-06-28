package org.mikehenze.alexnova.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.Random;

public class RegisterPage {

    WebDriver driver;
//    LandingPage loginPage;

    //xpath for Sign Up text value
    By signUpText = By.xpath("//div[@id='keyboard-nav-3']/h1");

    //ID for First Name input field
    By firstNameField = By.id("ispbxii_1");

    //ID for Last Name input field
    By lastNameField = By.id("ispbxii_2");

    //Name for Email input field
    By emailField = By.name("customer[email]");

    //Name for password input field
    By passwordField = By.name("customer[password]");

    //xpath for register button
    By registerButton = By.xpath("//div/input[@Value='Register']");


    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    //method to access element and get text value for Sign up
    public String getSignUpText() {
       return driver.findElement(signUpText).getText();

    }

    //method to enter first name
    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    //method to enter last name
    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    //method to enter email
    public void enterEmail(String emailName, String emailEnding) {
        if (emailName != "" && emailEnding != "") {
            //imported Random
            Random randomGenerator = new Random();
            //generates random number to add after email name for continues testing of emails
            int randomInt = randomGenerator.nextInt(1000);
            driver.findElement(emailField).sendKeys(emailName + randomInt + emailEnding);
        } else {
            driver.findElement(emailField).sendKeys(emailName + emailEnding);
        }

    }

    //method to enter password
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    //method to click register button
    public void submitRegisterButton() {

        driver.findElement(registerButton).submit();
    }


}
