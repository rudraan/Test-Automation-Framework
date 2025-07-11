package com.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterUtility {

    private static ExtentReports extentReports; //we want only single report so have made it static
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //using threadlocal for parallel testing

    public static void setupSparkReporter(String reportName){
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\" +reportName);
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
    }

    public static void createExtentTest(String testName){
        ExtentTest test = extentReports.createTest(testName);
        extentTest.set(test);
    }

    public static ExtentTest getTest(){
        return extentTest.get();
    }

    public static void flushReport(){
         extentReports.flush();//dumping data into html file
    }
}
