package com.ui.pages;

import com.constants.Browser;
import static com.constants.Env.*;
import com.util.BrowserUtility;

import com.util.JSONUtility;
import static com.util.PropertiesUtil.*;

import com.util.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class HomePage extends  BrowserUtility{ //mark child class with final keyword
    //will follow page object design pattern
    //create locators with static final as they are constants and class variable
    private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),'Sign in')]");
    Logger logger = LoggerUtility.getLogger(this.getClass());

    public HomePage(Browser browserName, boolean isHeadless) {
        super(browserName, isHeadless);//call parent class constructor from child class constructor
       // goToWebsite(readProperty(QA,"URL")); //for properties file
        goToWebsite(JSONUtility.readJSON(QA).getUrl());// for json file
    }

    public HomePage(WebDriver lambdaDriver) {
        super(lambdaDriver);
    }

    //in page object design pattern void return type cannot be used so return 'LoginPage' here
    public LoginPage goToLoginPage(){ //page functions
        logger.info("Trying to perform click to go to Sign in Page");
        clickOn(SIGN_IN_LINK_LOCATOR);
        LoginPage loginPage = new LoginPage(getDriver());
        return loginPage;
    }

}
