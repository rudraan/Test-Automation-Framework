package com.util;

import com.ui.pojo.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReaderUtility {

    public static Iterator<User> readExcelFile(String fileName)  {
        File xlsxFile = new File(System.getProperty("user.dir") + "\\testData\\"+fileName);
        XSSFWorkbook xssfWorkbook = null;
        List<User> userList = null;
        Iterator<Row> rowIterator;
        Cell emailAddressCell;
        Row row;
        Cell passwordCell;
        User user;
        try {
            xssfWorkbook = new XSSFWorkbook(xlsxFile);
            XSSFSheet xssfSheet = xssfWorkbook.getSheet("LoginTestData");
            userList = new ArrayList<User>();
            rowIterator = xssfSheet.iterator();
            rowIterator.next();
            while(rowIterator.hasNext()){
                row = rowIterator.next();
                emailAddressCell = row.getCell(0);
                passwordCell = row.getCell(1);
                user = new User(emailAddressCell.toString(),passwordCell.toString());
                userList.add(user);
                xssfWorkbook.close();
            }

        }
        catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userList.iterator();
    }
}
