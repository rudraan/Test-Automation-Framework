package com.ui.pages;

import com.util.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class ShippmentPage extends BrowserUtility {

    private static final By PROCEED_TO_CHECKOUT_BUTTON_LOCATOR = By.name("processCarrier");
    private static final By ACCEPT_TERMS_CHECKBOX_LOCATOR = By.id("uniform-cgv");

    public ShippmentPage(WebDriver driver) {
        super(driver);
    }

    public PaymentsPage goToPaymentsPage(){
        clickOnCheckBox(ACCEPT_TERMS_CHECKBOX_LOCATOR);
        clickOn(PROCEED_TO_CHECKOUT_BUTTON_LOCATOR);
        return new PaymentsPage(getDriver());
    }

}
