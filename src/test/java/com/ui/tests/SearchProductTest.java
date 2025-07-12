package com.ui.tests;

import com.ui.pages.MyAccountPage;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({com.ui.listeners.TestListener.class})
public class SearchProductTest extends TestBase{

    private MyAccountPage myAccountPage;
    private static final String SEARCH_TERM = "Printed Summer Dress";

    @BeforeMethod(description = "Valid user logs into the application")
    public void setUp(){
        myAccountPage = homePage.goToLoginPage().doLoginWith("rotoge2964@binafex.com","Password");
    }

    @Test(description = "Verify if the logged in user is able to search the product and correct products search are displayed",
            groups = {"e2e","smoke","sanity"})
    public void verifyProductSearchTest(){
        boolean actualResult = myAccountPage.searchForAProduct(SEARCH_TERM).isSearchTermPresentInProductList(SEARCH_TERM);
        assertEquals(actualResult,true);
    }

}

