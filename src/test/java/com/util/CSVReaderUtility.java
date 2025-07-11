package com.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVReaderUtility {

    public static Iterator<User> readCSVFile(String fileName)  {
        File csvFile = new File(System.getProperty("user.dir") + "//testData//"+fileName);
        FileReader fileReader = null;
        CSVReader csvReader;
        String[] line;
        List<User> userList;
        User userData;
        try {
            fileReader = new FileReader(csvFile);
            csvReader = new CSVReader(fileReader);
            csvReader.readNext();//row1 with heading

            userList = new ArrayList<User>();

            while((line = csvReader.readNext()) != null){
                userData = new User(line[0],line[1]);
                userList.add(userData);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
         catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
        return userList.iterator();
    }
}
