package com.rediff.qa.testData;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class SupplyTestDataFromDataProviderAndExcel {
	
	public static FileInputStream ip;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	
	@DataProvider(name = "RediffDataProviderSupply")
	public static Object[][] dataSupplyFrom2DimensionalObjectArray() {
		
		Object[][] data = {{"seleniumpanda@rediffmail.com", "Selenium@123"},
				           {"Mohamedboudguig@rediffmail.com", "Medbdg0707@"},
				           {"seleniumpanda1@rediffmail.com", "Donkey@123"},
				           {"seleniumpanda2@rediffmail.com", "Selenium@123"}};
		return data;
		}
	
	
	@DataProvider (name = "RediffExcelDataWithDataProvider")
	 public static Object[][] excelSheetDataSuppy() throws Exception {
		Object[][] data = SupplyTestDataFromDataProviderAndExcel.getRediffTestDataFromExcelSheet("Login");
		return data;
	}
	
	
	 public static Object[][] getRediffTestDataFromExcelSheet(String sheetName) throws Exception {
		 //Step 1 - you have to create the object of FileInputStream
		 ip = new FileInputStream(System.getProperty("user.dir") +"\\src\\test\\java\\com\\rediff\\qa\\testData\\RediffTestData.xlsx" );
		 
		 //Step 2 - You have to create the Object of XSSFWorkbook
		 workbook = new XSSFWorkbook(ip);
		 
		 //Step 3 - XSSFSheet
		 sheet = workbook.getSheet(sheetName);
		 
		 //Step 4 - you have to determine number of rows and cols
		    int rows =  sheet.getLastRowNum();
		    int cols = sheet.getRow(0).getLastCellNum();
		    
		 Object[][] data = new Object[rows][cols];
		 
		 for(int i=0 ; i<rows ; i++) {
			 XSSFRow row = sheet.getRow(i+1);
			 
			  for(int j=0 ; j<cols ; j++) {
				  XSSFCell cell = row.getCell(j);
				 CellType cellype = cell.getCellType();
				 
				 switch(cellype) {
				 
				 case STRING:
					 data[i][j] = cell.getStringCellValue();
					 break;
					 
				 case NUMERIC:
					 data[i][j] = Integer.toString((int)cell.getNumericCellValue());
					 break;
					 
				 case BOOLEAN:
					 data[i][j] = cell.getBooleanCellValue();
					 break;
				 }
			  }
		 }
		 
		 return data;
	 }
	
	
	}


