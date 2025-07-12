package com.ui.tests;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.util.BrowserUtility;
import com.util.LambdaTestUtility;
import com.util.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static com.constants.Browser.CHROME;

public class TestBase {
    protected HomePage homePage;
    private boolean isLambdaTest;

    Logger logger = LoggerUtility.getLogger(this.getClass());

    @Parameters({"browser","isLambdaTest","isHeadless"})
    @BeforeMethod(description = "Load the HomePage of the Website")
    public void setUp(@Optional("chrome") String browser,
                      @Optional("false")boolean isLambdaTest,
                      @Optional("false")boolean isHeadless, ITestResult result){

        this.isLambdaTest = isLambdaTest;
        WebDriver lambdaDriver;
        if(isLambdaTest){
            lambdaDriver = LambdaTestUtility.initializeLambdaTestSession(browser,result.getMethod().getMethodName());
            homePage = new HomePage(lambdaDriver);
        }else{
            logger.info("Load the HomePage of the Website");
            homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless);
        }
    }

    public BrowserUtility getInstance(){//return type can be of parent class
        return homePage;
    }

    @AfterMethod(description =  "Tear down the browser")
    public void tearDown(){
        if(isLambdaTest){
            LambdaTestUtility.quitSession();// quit browser session on lambda test
        }else{
            homePage.quit(); //quit local session
        }
    }


}
