package JASS.Common;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileReader {
	public static String filepath1 = "/Users/Pallavi/Documents/abc123.xlsx";
	public static String filepath2 = "/users/Pallavi/Documents/Signup.xlsx";
	public static Logger logger = Logger.getLogger(ExcelFileReader.class);

	public static String[][] getInput() throws InvalidFormatException, IOException {

		FileInputStream fin = new FileInputStream(filepath1);
		Workbook wb = WorkbookFactory.create(fin);
		Sheet s2 = wb.getSheet("sheet 1");
		int max = s2.getLastRowNum();
		String[][] userdata = new String[max][2];
		for (int i = 0; i < max; i++) {
			String username = s2.getRow(i).getCell(0).getStringCellValue();
			String password = s2.getRow(i).getCell(1).getStringCellValue();
			userdata[i][0] = username;
			userdata[i][1] = password;
			logger.info("Username: " + username + "Password: " + password);

		}
		wb.close();
		return userdata;
	}

	public static String[][] getData() throws EncryptedDocumentException, InvalidFormatException, IOException {
		FileInputStream fin = new FileInputStream(filepath2);
		Workbook wb = WorkbookFactory.create(fin);
		Sheet s1 = wb.getSheet("sheet 1");
		int rowcount = s1.getLastRowNum();
		int columncount = s1.getRow(0).getLastCellNum();
		String[][] userdata = new String[rowcount][columncount];
		for (int i = 0; i < rowcount; i++) {

			for (int j = 0; j < columncount; j++) {

				String data = s1.getRow(i+1).getCell(j).getStringCellValue();
				userdata[i][j] = data;

			}
		}
		return userdata;

	}
	public static String[][] getKey() throws EncryptedDocumentException, InvalidFormatException, IOException {
		FileInputStream fin = new FileInputStream(filepath2);
		Workbook wb = WorkbookFactory.create(fin);
		Sheet s2 = wb.getSheet("sheet 2");
		int rowcount = s2.getLastRowNum();
		String[][] keys=new String[rowcount][1];
		for (int i = 0; i < rowcount; i++) {
			  String data = s2.getRow(i).getCell(0).getStringCellValue();
			  keys[i][0]=data;
		
		}
		return keys;
	
	}
	
		
	}


