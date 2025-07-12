package com.ui.tests;

import static com.constants.Size.*;
import com.ui.pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductCheckoutTest extends  TestBase{

    private static final String SEARCH_TERM = "Printed Summer Dress";
    private static SearchResultPage searchResultPage;

    @BeforeMethod(description = "User logs into the application and searches for a product")
    public void setUp(){
        searchResultPage = homePage.goToLoginPage().doLoginWith("rotoge2964@binafex.com","Password").
       searchForAProduct(SEARCH_TERM);
    }

    @Test(description = "Verify if the logged in user is able to buy a dress", groups = {"e2e","smoke","sanity"})
    public void checkoutTest(){
        String result = searchResultPage.clickOnTheProductAtIndex(0).changeSize(M).addProductToCart()
                .proceedToCheckout().goToConfirmAddressPage().goToShipmentPage()
                .goToPaymentsPage().makePaymentByWire();
        Assert.assertTrue(result.contains("complete"));
    }

}
