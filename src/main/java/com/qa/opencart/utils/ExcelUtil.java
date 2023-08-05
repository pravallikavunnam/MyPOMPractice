package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	public static final String TEST_DATA_SHEET_PATH=".src/test/resources/testdata/OpenCartTestData.xlsx";
	public static Workbook book;
	public static Sheet sheet;
	
	//static--we can call using classname
	public static Object[][] getTestData(String sheetName) { //we can give any number of sheets from excel workbook which used to read data
		System.out.println("reading the data from sheet:" +sheetName); //we need to return 2D Object Array
		
		//we have to supply data from sheet to dataprovider which will supply to @Test method
		//return type of dataprovider is 2D Object array
		//so excel utility also return 2D Object Array
		//initially it will be null---
		Object data[][]=null;
		try {
			FileInputStream ip=new FileInputStream(TEST_DATA_SHEET_PATH) ; //creating FIS Class Object
			book=WorkbookFactory.create(ip);  //Workbook class in Apache POI API ,in create method passing ip as param
			//create method returns workbook reference
			book.getSheet(sheetName); //get sheet method returns sheet ref
			//now we have reached the sheet and read the data in the form of rows and columns
			//we need to initialize the data Array
			data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			//In order to fill the data we use for loop ,first loop is for row
			for(int i=0;i<sheet.getLastRowNum();i++) {
				
				for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
					data[i][j]=sheet.getRow(i+1).getCell(j).toString();
					
				}
				
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return data;
		
	}
	
	
	
	
}
