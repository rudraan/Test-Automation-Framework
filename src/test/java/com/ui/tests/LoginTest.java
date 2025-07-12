package com.ui.tests;

import static com.constants.Browser.*;
import com.ui.pages.HomePage;
import static org.testng.Assert.*;

import com.ui.pojo.User;
import com.util.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({com.ui.listeners.TestListener.class})
public class LoginTest extends TestBase {

    Logger logger = LoggerUtility.getLogger(this.getClass());

    @Test(description = "Verifies if the valid user is able to login into the application", groups = {"e2e","sanity"},
    dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider",
            retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
    public void loginTest(User user){
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),"Anindita Rudra");
    }

    @Test(description = "Verifies if the valid user is able to login into the application", groups = {"e2e","sanity"},
            dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "loginTestCSVDataProvider",
            retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
    public void loginCSVTest(User user){
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),"Anindita Rudra");
    }

    @Test(description = "Verifies if the valid user is able to login into the application", groups = {"e2e","sanity"},
            dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "loginTestExcelDataProvider",
    retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
    public void loginExcelTest(User user){
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),"Anindita Rudra");
    }

}

