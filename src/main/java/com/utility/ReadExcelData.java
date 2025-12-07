package com.utility;

import java.io.File;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {

	public static void main(String[] args) {
		ReadParticularData();
	}

	static void ReadParticularData() {

		try {
			File file = new File("//Users//fi-triveni//eclipse-workspace//org.apache.maven//DataDriven//DataDriven_IPT.xlsx");

			Workbook book = new XSSFWorkbook(file);
			Sheet sheet = book.getSheet("Sheet1");
			Row row = sheet.getRow(4);
			Cell cell = row.getCell(1);

			// String data = cell.getStringCellValue(); -- will fetch only string. doesn't
			// fetch numeric

			// Data formatter - to convert the cell's data type into string value

			DataFormatter dataformat = new DataFormatter();
			String data = dataformat.formatCellValue(cell);
			System.out.println(data);

			book.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String readCellData(int rowValue, int colValue) {

		String data = null;

		try {
			File file = new File("//Users//fi-triveni//eclipse-workspace//org.apache.maven//DataDriven//DataDriven_IPT.xlsx");

			Workbook book = new XSSFWorkbook(file);
			Sheet sheet = book.getSheet("Sheet1");
			Row row = sheet.getRow(rowValue);
			Cell cell = row.getCell(colValue);

			DataFormatter dataformat = new DataFormatter();
			data = dataformat.formatCellValue(cell);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;

	}
	
	public static void getAllData() {
		
		try {
			File file = new File("//Users//fi-triveni//eclipse-workspace//org.apache.maven//DataDriven//DataDriven_IPT.xlsx");
			
			Workbook book = new XSSFWorkbook(file);
			Sheet sheet = book.getSheet("Sheet1");
			
			int noOfRows = sheet.getLastRowNum();
			System.out.println("No. of rows: " + noOfRows);
			
			short noOfColumns = sheet.getRow(0).getLastCellNum();
			System.out.println("No. of Columns: " + noOfColumns);
			
			Row row = sheet.getRow(0);
			Cell cell = row.getCell(0);
					
			DataFormatter dataFormat = new DataFormatter();
			String data = dataFormat.formatCellValue(cell);
			System.out.println(data);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
