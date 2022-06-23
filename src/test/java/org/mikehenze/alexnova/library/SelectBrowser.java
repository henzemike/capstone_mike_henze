package org.mikehenze.alexnova.library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SelectBrowser {

    static WebDriver driver;

    public static WebDriver StartBrowser(String browserName) {
        // ---If the browser is Firefox----
        if (browserName.equalsIgnoreCase("Firefox")) {
            // Set the path for geckodriver.exe
            System.setProperty("webdriver.firefox.marionette","/Users/MikeHenze/Documents/ActivateIT/Capestone/Henze_Mike_Capstone_AlexNova/src/test/resources/geckodriver");
            driver = new FirefoxDriver();
        }
        //---- If the browser is Chrome--
        else if (browserName.equalsIgnoreCase("Chrome")) {
            // Set the path for chromedriver.exe
            System.setProperty("webdriver.chrome.driver", "/Users/MikeHenze/Documents/ActivateIT/Capestone/Henze_Mike_Capstone_AlexNova/src/test/resources/chromedriver");
            driver = new ChromeDriver();
        }
        // ----If the browser is  EdgeIE----
        else if (browserName.equalsIgnoreCase("EdgeExplore")) {
            // Set the path for IEdriver
            System.setProperty("webdriver.edge.driver", "/Users/MikeHenze/Documents/ActivateIT/Capestone/Henze_Mike_Capstone_AlexNova/src/test/resources/msedgedriver");
            // Instantiate a EdgeDriverclass.
            EdgeOptions options = new EdgeOptions();
            driver = new EdgeDriver(options);
        }
        driver.manage().window().maximize();
        return driver;
    }

}
