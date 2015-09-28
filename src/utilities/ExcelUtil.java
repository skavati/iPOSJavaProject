/*package utilities;

public class ExcelUtil {

}
*/
package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

//import org.apache.http.util.TextUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
//import org.junit.Test;

public class ExcelUtil {

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

	

//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method

public static void setExcelFile(String Path,String SheetName) throws Exception {

		try {

			// Open the Excel file

		FileInputStream ExcelFile = new FileInputStream(Path);

		// Access the required test data sheet

		ExcelWBook = new XSSFWorkbook(ExcelFile);

		ExcelWSheet = ExcelWBook.getSheet(SheetName);

		} catch (Exception e){

			throw (e);

		}

}

//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

public static String getCellData(int RowNum, int ColNum) throws Exception{

		try{

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			String CellData = Cell.getStringCellValue();

			return CellData;

			}catch (Exception e){

			return"";

			}

}

//This method is to write in the Excel cell, Row num and Col num are the parameters

public static void setCellData(String CellValue,  int RowNum, int ColNum,String Path) throws Exception	{

		try{

			Row  = ExcelWSheet.getRow(RowNum);

		Cell = Row.getCell(ColNum, org.apache.poi.ss.usermodel.Row.RETURN_BLANK_AS_NULL);

		if (Cell == null) {

			Cell = Row.createCell(ColNum);

			Cell.setCellValue(CellValue);

			} else {

				Cell.setCellValue(CellValue);

			}

// Constant variables Test Data path and Test Data file name

				FileOutputStream fileOut = new FileOutputStream(Path);

				ExcelWBook.write(fileOut);

				fileOut.flush();

				fileOut.close();

			}catch(Exception e){

				throw (e);

		}

	}





 @Test
 // To read data from Excel sheets
 
 public static void ReadDataFromExcel (){ 
	 boolean KeyCellfound;   
 try {
	 System.out.println("Reading from Excel STARTS...");
//  FileInputStream file = new FileInputStream(new File("C:\\Work\\IPOSAutoQA\\iPOS 580\\Source Files\\Requisitions.xlsx")); 
	 FileInputStream file = new FileInputStream("C:\\Work\\IPOSAutoQA\\iPOS 580\\Source Files\\Requisitions.xlsx"); 
  XSSFWorkbook workbook = new XSSFWorkbook(file); 
  XSSFSheet sheet = workbook.getSheetAt(1);
  KeyCellfound= false;
for (int i=1; i <= sheet.getLastRowNum(); i++){ 
	    if  (sheet.getRow(i).getCell(0).getStringCellValue().trim().equals( "2.7.2.6.1.1") ) { 
	    	System.out.println("KeyCell found");
	    	 for (int j=1; j <= sheet.getRow(i).getLastCellNum(); j++){
	    		 try {	    			
	    			 // If cell value  is empty then continue next iteration
	    			 sheet.getRow(i).getCell(j).getStringCellValue().equalsIgnoreCase(null);
	    			} catch (NullPointerException npe) {
	    			   continue;
	    			}     	    		 
	    		System.out.println(sheet.getRow(i).getCell(j).getStringCellValue().trim());	    		
	    	 }
	    	 KeyCellfound=true;
	   } 
 if (KeyCellfound){
	 break;
 }
 
 }
  workbook.close();
  file.close(); 
 } catch (FileNotFoundException fnfe) {
  fnfe.printStackTrace();
 } catch (IOException ioe) {
  ioe.printStackTrace();
 }
 }
 }

