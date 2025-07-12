package com.ui.pages;

import com.util.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class PaymentsPage extends BrowserUtility {

    private static final By PAYMENT_BY_WIRE_BUTTON_LOCATOR = By.xpath("//a[@title='Pay by bank wire']");
    private static final By CONFIRM_ORDER_BUTTON_LOCATOR = By.xpath("//button[contains(@class,'button-medium')]");
    private static final By ALERT_SUCCESS_MESSAGE_LOCATOR = By.xpath("//p[contains(@class,'alert-success')]");

    public PaymentsPage(WebDriver driver) {
        super(driver);
    }

    public String makePaymentByWire(){
        clickOn(PAYMENT_BY_WIRE_BUTTON_LOCATOR);
        clickOn(CONFIRM_ORDER_BUTTON_LOCATOR);
        return getVisibleText(ALERT_SUCCESS_MESSAGE_LOCATOR);

    }

}
