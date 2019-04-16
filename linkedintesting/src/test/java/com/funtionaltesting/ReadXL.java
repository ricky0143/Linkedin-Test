package com.funtionaltesting;


import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sun.media.sound.InvalidFormatException;


public class ReadXL {
	
	public static Object[][] readExcel(String filePath, String sheetName) throws InvalidFormatException, IOException 
	{
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		int column = sheet.getRow(0).getLastCellNum();
		Object[][] data = new Object[rowCount][column];
		for (int i = 1; i <= rowCount; i++) {
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < column; j++) {
				XSSFCell cell = row.getCell(j);
				DataFormatter formatter = new DataFormatter();
				String val = formatter.formatCellValue(cell);
				data[i - 1][j] = val;
			}
		}

		return data;
	}
	
	
}
