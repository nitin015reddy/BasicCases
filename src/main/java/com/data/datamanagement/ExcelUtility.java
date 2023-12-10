package com.data.datamanagement;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public static String[][] readExcelValue(String sheetname) {
		String[][] data = null;
		try {
			XSSFWorkbook workBook = new XSSFWorkbook("./data/Testdata.xlsx");
			XSSFSheet sheet = workBook.getSheet(sheetname);
			XSSFRow row = sheet.getRow(0);
			int rowCount = sheet.getLastRowNum();
			int colCount = row.getLastCellNum();
			data = new String[rowCount][colCount];
			for (int i = 1; i <= rowCount; i++) {
				for (int j = 0; j < colCount; j++) {
					XSSFCell cell = sheet.getRow(i).getCell(j);
					data[i-1][j] = cell.getStringCellValue();
				}
			}
			workBook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

}
