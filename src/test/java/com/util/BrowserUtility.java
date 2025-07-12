package com.util;

import com.constants.Browser;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class BrowserUtility { //parent class should be abstract if some class is extending it
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private Logger logger = LoggerUtility.getLogger(this.getClass());
    private WebDriverWait wait;

    public WebDriver getDriver() {
        return driver.get();
    }

    public BrowserUtility(String browserName){
        logger.info("Launching browser for " +browserName);
        if(browserName.equalsIgnoreCase("Chrome")){
            driver.set(new ChromeDriver() );//browser session is created and browser is launched
            wait = new WebDriverWait(driver.get(), Duration.ofSeconds(50L));
        } else if (browserName.equalsIgnoreCase("Edge")) {
            driver.set(new EdgeDriver());
            wait = new WebDriverWait(driver.get(), Duration.ofSeconds(50L));
        }else{
            logger.error("Invalid Browser name....Please select Chrome or Edge");
            System.err.println("Invalid Browser name....Please select Chrome or Edge");
        }

    }

    public BrowserUtility(Browser browserName){
        logger.info("Launching browser for " +browserName);
        if(browserName == Browser.CHROME){
            driver.set(new ChromeDriver()); //browser session is created and browser is launched
            wait = new WebDriverWait(driver.get(), Duration.ofSeconds(50L));
        } else if (browserName == Browser.EDGE) {
            driver.set(new EdgeDriver());
            wait = new WebDriverWait(driver.get(), Duration.ofSeconds(50L));
        }else if (browserName == Browser.FIREFOX) {
            driver.set(new FirefoxDriver());
            wait = new WebDriverWait(driver.get(), Duration.ofSeconds(50L));
        }else{
            System.err.println("Invalid Browser name....Please select Chrome or Edge");
        }

    }

    public BrowserUtility(Browser browserName, boolean isHeadless){
        logger.info("Launching browser for " +browserName);
        if(browserName == Browser.CHROME ){
            if(isHeadless){
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");//headless mode
                options.addArguments("--window-size=1920,1080");
                driver.set(new ChromeDriver(options)); //browser session is created and browser is launched
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(50L));
            }else{
                driver.set(new ChromeDriver());
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(50L));
            }

        } else if (browserName == Browser.EDGE) {
            if(isHeadless){
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless=new");//headless mode
                options.addArguments("disable-gpu");
                driver.set(new EdgeDriver(options)); //browser session is created and browser is launched
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(50L));
            }else{
                driver.set(new EdgeDriver());
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(50L));
            }

        }else if (browserName == Browser.FIREFOX) {
            if(isHeadless){
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless=new");//headless mode
                driver.set(new FirefoxDriver(options)); //browser session is created and browser is launched
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(50L));
            }else{
                driver.set(new FirefoxDriver());
                wait = new WebDriverWait(driver.get(), Duration.ofSeconds(50L));
            }
        }else{
            System.err.println("Invalid Browser name....Please select Chrome or Edge");
        }

    }

    public BrowserUtility(WebDriver driver) {
        super();
        this.driver.set(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(50L));
    }

    public void goToWebsite(String url){
        logger.info("Visiting the website" + url);
        driver.get().get(url);
    }

    public void maximizeWindow(){
        logger.info("Maximizing the browser window");
        driver.get().manage().window().maximize();
    }

    public void clickOn(By locator) {
        logger.info("Finding element with the locator" + locator);
        WebElement element =  wait.until(ExpectedConditions.elementToBeClickable(locator));
        //WebElement element = driver.get().findElement(locator);
        logger.info("Element found and now performing click");
        element.click();
    }

    public void clickOnCheckBox(By locator) {
        logger.info("Finding element with the locator" + locator);
        WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        //WebElement element = driver.get().findElement(locator);
        logger.info("Element found and now performing click");
        element.click();
    }

    public void enterText(By locator, String textToEnter){
        logger.info("Finding element with the locator" + locator);
        WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        //WebElement element = driver.get().findElement(locator);
        logger.info("Element found and now enter text" +textToEnter);
        element.sendKeys(textToEnter);
    }

    public void clearText(By textBoxLocator){
        logger.info("Finding element with the locator" + textBoxLocator);
        WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(textBoxLocator));
        //WebElement element = driver.get().findElement(textBoxLocator);
        logger.info("Element found and now clearing the text box field");
        element.clear();
    }

    public void selectFromDropdown(By dropdownLocator, String optionToSelect){
        logger.info("Finding element with the locator" + dropdownLocator);
        WebElement element = driver.get().findElement(dropdownLocator);
        Select select = new Select(element);
        logger.info("Selecting the option " + optionToSelect);
        select.selectByVisibleText(optionToSelect);
    }

    public void enterSpecialKey(By locator, Keys keyToEnter){
        logger.info("Finding element with the locator" + locator);
        WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        //WebElement element = driver.get().findElement(locator);
        logger.info("Element found and now enter text" +keyToEnter);
        element.sendKeys(keyToEnter);
    }

    public List<String> getAllVisibleText(By locator){
        logger.info("Finding element with the locator" + locator);
        List<WebElement> elementList = driver.get().findElements(locator);
        logger.info("Element found and now printing the list of elements ");
        List<String> visibleTextList = new ArrayList<String>();
        for(WebElement element : elementList){
            visibleTextList.add(getVisibleText(element));
        }
        return visibleTextList;
    }

    public List<WebElement> getAllElements(By locator){
        logger.info("Finding element with the locator" + locator);
        List<WebElement> elementList = driver.get().findElements(locator);
        logger.info("Element found and now printing the list of elements ");
        return elementList;
    }

    public void clickOn(WebElement webElement) {
        logger.info("Element found and now performing click");
        webElement.click();
    }

    public String getVisibleText(WebElement element){
        logger.info("Return the visible text " +element.getText());
        return element.getText();
    }

    public String getVisibleText(By locator){
        logger.info("Finding element with the locator" + locator);
        WebElement element = driver.get().findElement(locator);
        logger.info("Element found and now returning the visible text" + element.getText());
        return element.getText();
    }

    public String takeScreenshot(String name){
        TakesScreenshot screenshot = (TakesScreenshot)driver.get();
        File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
        String timeStamp = format.format(date);
        String path = "./screenshots/" + name + " - " + timeStamp + ".png";
        File screenshotFile = new File(path);
        try {
            FileUtils.copyFile(screenshotData, screenshotFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }

    public void quit(){
        driver.get().quit();
    }
}
