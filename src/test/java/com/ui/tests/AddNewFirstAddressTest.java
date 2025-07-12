package com.ui.tests;

import com.ui.pages.AddressPage;
import com.ui.pages.MyAccountPage;
import com.ui.pojo.AddressPOJO;
import com.util.FakeAddressUtility;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewFirstAddressTest extends TestBase{

    private MyAccountPage myAccountPage;
    private AddressPOJO address;

    @BeforeMethod(description = "Valid First Time user logs into the application")
    public void setUp(){
        myAccountPage = homePage.goToLoginPage().doLoginWith("rotoge2964@binafex.com","Password");
        address = FakeAddressUtility.getFakeAddress();
    }

    @Test(description = "Verifying new address is added")
    public void addNewAddress(){
        String newAddress = myAccountPage.goToAddAddressPage().saveAddress(address);
        Assert.assertEquals(newAddress, address.getAddressAlias().toUpperCase());

    }
}
