package com.ui.listeners;

import com.constants.Env;
import com.util.JSONUtility;
import com.util.PropertiesUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class MyRetryAnalyzer implements IRetryAnalyzer {

    //using properties file
   // private static final int MAX_NO_OF_ATTEMPTS = Integer.parseInt(PropertiesUtil.readProperty(Env.QA,"MAX_NO_OF_ATTEMPTS"));
    //using json file
    private static final int MAX_NO_OF_ATTEMPTS = JSONUtility.readJSON(Env.QA).getMAX_NO_OF_ATTEMPTS();

    private static int currentAttempt = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {//ITestResult gives information about Test
        if(currentAttempt <= MAX_NO_OF_ATTEMPTS){
           currentAttempt++;
            return true; //retry and tests are skipped
        }
        else {
            return false;//mark as failed
        }
    }
}
