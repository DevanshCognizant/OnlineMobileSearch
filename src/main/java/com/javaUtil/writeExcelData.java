//This file is created to store the values in Excel sheet
package com.javaUtil;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class writeExcelData {
	
	static XSSFWorkbook wbook;
	static XSSFSheet sheet;
	static XSSFRow row1;
	static XSSFRow row2;
	static FileOutputStream file;
	public static void writeExcel() throws Exception {
		file = new FileOutputStream(System.getProperty("user.dir")+"/src/test/resources/newexcel.xlsx");
		wbook = new XSSFWorkbook();
		sheet = wbook.createSheet("Sheet001");
		row1 = sheet.createRow(0);
		row2 = sheet.createRow(1);
	}
	
	public static void Close() throws Exception {
		wbook.write(file); // This function is used to save excel sheet after writing all the values
		wbook.close();
		file.close();
		System.out.println("Data written in excel file");// 
	}
	
	public static void createRow1() {
		//Heading Row is created 
		row1.createCell(0).setCellValue("No.of options");
		sheet.autoSizeColumn(0);
		row1.createCell(1).setCellValue("No.of pages");
		sheet.autoSizeColumn(1);
		row1.createCell(2).setCellValue("No.of items");
		sheet.autoSizeColumn(2);
		row1.createCell(3).setCellValue("Newest Arrivals Button Clicked");
		sheet.autoSizeColumn(3);
		
	}
	public static void showOptions(int size) {
		//This function is used to store number of options in the cell
		 String num_opt = Integer.toString(size);
		 row2.createCell(0).setCellValue(num_opt);
	}
	public static void showPages(String num_page) {
		// This function is used to store number of 
		 String pages = num_page;	
		 row2.createCell(1).setCellValue(pages);
	}
	public static void showItems(String line) {
		//This function is used to store number of items
		 String items = line;
		 row2.createCell(2).setCellValue(items);
	}
	
	public static void Check(String Check) {
		// This function is used to store true or false in the sheet
		String check = Check;
		 if(check.equals("true")) {		
			 XSSFCellStyle style=wbook.createCellStyle(); 
			 style.setFillBackgroundColor(IndexedColors.BRIGHT_GREEN.getIndex()); // To color the cell green
			 style.setFillPattern(FillPatternType.FINE_DOTS);
	         XSSFCell cell=row2.createCell(3); 
	            cell.setCellValue(check); 
	            cell.setCellStyle(style); 
	            System.out.println("Button clicked successfully");
		 }
		 else if(check.equals("false")) {
			 XSSFCellStyle style=wbook.createCellStyle(); 
			 style.setFillBackgroundColor(IndexedColors.RED.getIndex()); // To color the cell red
			 style.setFillPattern(FillPatternType.FINE_DOTS);
	         XSSFCell cell=row2.createCell(3); 
	            cell.setCellValue(check); 
	            cell.setCellStyle(style);
	            System.out.println("Button not clicked successfully");
		 }
		
	}
	
}
