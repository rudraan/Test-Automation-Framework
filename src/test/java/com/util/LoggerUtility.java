package com.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtility {

       // private static Logger logger; one memory allocation- so static, not accessible outside class-private

        private LoggerUtility(){ //private constructor cannot be called outside the class, create obj inside this class

        }

        public static Logger getLogger(Class<?> clazz){//to call this method outside class mark it as static
           Logger logger = null; //this will have respective class logs
            if(logger == null){
                logger = LogManager.getLogger(clazz);
            }
            return logger;
        }


}
