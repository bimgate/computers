package com.backbase.qa.utils;

import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by bratislav.miletic on 10/11/2017.
 */
public class WebDriver {
    public static FirefoxDriver getFirefoxDriver(){
        System.setProperty("webdriver.gecko.driver",
                "./src/test/java/com/backbase/qa/resources/drivers/geckodriver");
       FirefoxDriver driver = new FirefoxDriver();
       driver.manage().window().maximize();
        return driver;
    }
}
