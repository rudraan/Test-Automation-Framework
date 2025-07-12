package com.ui.tests;

import com.util.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


@Listeners({com.ui.listeners.TestListener.class})

public class InvalidCredLoginTest extends TestBase {

    Logger logger = LoggerUtility.getLogger(this.getClass());
    private static final String INVALID_EMAIL_ADDRESS = "ani123@gmail.com";
    private static final String INVALID_PASSWORD = "password";

    @Test(description = "Verify if error message is shown for the user when they enter invalid credentials",
            groups = {"e2e","sanity","smoke"}
            )
    public void loginTest(){
        assertEquals(homePage.goToLoginPage().doLoginWithInvalidCredentials(INVALID_EMAIL_ADDRESS, INVALID_PASSWORD).getErrorMessage(),"Authentication failed.");
    }
}

