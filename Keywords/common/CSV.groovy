package common

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

import java.io.File;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.xpath.axes.UnionPathIterator.iterOwner
import org.junit.After

import java.lang.String

import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFCellStyle
import org.apache.poi.hssf.usermodel.HSSFPalette
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFFont
import org.apache.poi.xssf.usermodel.XSSFHyperlink
import java.awt.Color
import com.kms.katalon.core.configuration.RunConfiguration
import java.text.NumberFormat

import org.apache.poi.ss.usermodel.DataFormatter

import com.kms.katalon.core.testdata.reader.ExcelFactory as ExcelFactory
import org.apache.poi.ss.usermodel.Cell as Cell
import org.apache.poi.ss.usermodel.Row as Row


public class CSVReader {
	XSSFWorkbook wb;
	DataFormatter formatter;
	FormulaEvaluator evaluator;
	File src;
	CSVReader csvReader;
	String CsvFilePath = "";

	def TestDataReader (String filePath){
		try {
			if (filePath.endsWith(".csv")) {
				CsvFilePath = filePath;
				csvReader = new CSVReader(filePath);
			} else {
				src = new File(filePath);
				FileInputStream fis = new FileInputStream(src);
				wb = new XSSFWorkbook(fis);
				fis.close();
				formatter = new DataFormatter();
				evaluator = wb.getCreationHelper().createFormulaEvaluator();
				//System.out.println("evaluating file formulas...");
				//XSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
			}
		} catch (IOException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
		}
	}

	def String getCellText(XSSFCell c) {
		if (c == null || c.getCellType() == CellType.BLANK)
			return ""; //return an empty string in case of null value
		else {
			return formatter.formatCellValue(c,evaluator);
		}
	}

	def void writeToFile() {
		try {
			FileOutputStream fileOut;
			fileOut = new FileOutputStream(src);
			wb.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	def void writeToCell(String text, String sheetName, int rowInd, int colInd) {
		XSSFSheet sheet = wb.getSheet(sheetName);
		XSSFRow row = sheet.getRow(rowInd);
		XSSFCell c = row.getCell(colInd);
		if (c == null) {
			c = row.createCell(colInd);
		}
		c.setCellType(CellType.STRING);
		c.setCellValue(text);
		writeToFile();
	}

	def String getCellData (int sheetNumber, int row, int column) {
		if (CsvFilePath.endsWith(".csv")) {
			return getCellDataCSV(row, column);
		}
		else {
			XSSFSheet sheet1=wb.getSheetAt(sheetNumber);
			String data = getCellText(sheet1.getRow(row).getCell(column));
			return data;
		}
	}
	def String getCellData (String sheetName, int row, int column) {
		if (CsvFilePath.endsWith(".csv")) {
			return getCellDataCSV(row, column);
		}
		else {
			XSSFSheet sheet1=wb.getSheet(sheetName);
			String data = getCellText(sheet1.getRow(row).getCell(column));
			return data;
		}
	}
	def String getCellData (String sheetName, String rowKey, String columnKey) {
		XSSFSheet sheet1=wb.getSheet(sheetName);
		String col0Name = getCellData(sheetName,0,0);
		int row = getRowByKey(sheetName,col0Name,rowKey);
		int column = getColumnByKey(sheetName,columnKey);
		String data = getCellText(sheet1.getRow(row).getCell(column));
		return data;
	}
	def ArrayList<String> getKeys(String sheetName) {
		return parseRow(wb.getSheet(sheetName).getRow(0));
	}
	def XSSFRow getRow(String sheetName,int rowInd) {
		return wb.getSheet(sheetName).getRow(rowInd);
	}

	def int getRowCount(String sheetName)	{
		int row = wb.getSheet(sheetName).getLastRowNum();
		row =row+1;
		return row;
	}
	def int getColumnCount(String sheetName)	{
		if (CsvFilePath.endsWith(".csv")) {
			return getColumnCountCSV();
		}
		else {
			int col = wb.getSheet(sheetName).getRow(0).getLastCellNum();
			return col;
		}
	}
	def int getRowByKey(String sheetName, String keyName, String value) {
		if (CsvFilePath.endsWith(".csv")) {
			return getRowIndexCSV(keyName, value);
		}
		else {
			int col = getColumnByKey(sheetName, keyName);
			ArrayList<String> cols = parseColumn(sheetName, col);
			for(int i = 0; i<cols.size();i++) {
				if(cols.get(i).equals(value)) {
					return i; //key found
				}
			}
			System.out.println("error: value:" + value + " not found in sheet:" + sheetName + " for key:" + keyName);
			return -1; //key not found
		}
	}


	def int getColumnByKey(String sheetName,String keyName) {
		if (CsvFilePath.endsWith(".csv")) {
			return getColIndexCSV(keyName);
		}
		else {
			ArrayList<String> keys = getKeys(sheetName);
			for(int i = 0; i<keys.size();i++) {
				if(keys.get(i).equals(keyName)) {
					return i; //key found
				}
			}
			System.out.println("error: key:" + keyName + " not found in sheet:" + sheetName);
			return -1; //key not found
		}
	}
	def ArrayList<String> parseRow(String sheetName, int rowIndex) {
		ArrayList<String> rowContents = new ArrayList<String>();
		XSSFRow row = getRow(sheetName,rowIndex);
		int columns = getColumnCount(sheetName);
		for(int i = 0; i<columns;i++) {
			rowContents.add(getCellText(row.getCell(i)));
		}
		return rowContents;
	}
	def ArrayList<String> parseRow(XSSFRow row) {
		ArrayList<String> contents = new ArrayList<String>();
		int columns = row.getLastCellNum();
		if (row != null) {
			for(int i = 0; i<columns;i++) {
				contents.add(getCellText(row.getCell(i)));
			}
		}
		return contents;
	}
	def ArrayList<String> parseColumn(String sheetName, int colIndex) {
		ArrayList<String> contents = new ArrayList<String>();
		int rows = getRowCount(sheetName);
		for(int i = 0; i<rows;i++) {
			XSSFRow row = getRow(sheetName,i);
			if (row != null) {
				contents.add(getCellText(row.getCell(colIndex)));
			}
			else {
				contents.add("");// add blank string if whole row is empty
			}
		}
		return contents;
	}

	def ArrayList<ArrayList<String>> searchForRows(String sheetName, String targetText, String targetColumn) {
		ArrayList<ArrayList<String>> contents = new ArrayList<ArrayList<String>>();
		ArrayList<Integer> relevantRows = new ArrayList<Integer>();
		int rowCount = getRowCount(sheetName);
		int tagColumn = getColumnByKey(sheetName,targetColumn);
		ArrayList<String> column = parseColumn(sheetName,tagColumn);
		for(int i = 0;i<rowCount;i++) {
			if(column.get(i).equals(targetText)) {
				relevantRows.add(i);
			}
		}
		for (int j = 0; j<relevantRows.size(); j++) {
			contents.add(parseRow(sheetName,relevantRows.get(j)));
		}
		return contents;
	}
	def ArrayList<ArrayList<String>> searchForRowsByTag(String sheetName, String targetText) {
		return searchForRows(sheetName, targetText, "Tag");
	}


	def String getCellDataCSV(String valueColumnName, String tagColumnName, String tag) {
		return csvReader.getCSVCellDataByTagAndColumnName(valueColumnName, tagColumnName, tag);
	}
	def String getCellDataCSV(int rowIndex, int colIndex) {
		return csvReader.getCSVCellDataByRowAndColumnIndex(rowIndex, colIndex);
	}
	def int getRowIndexCSV(String columnName, String tag) {
		return csvReader.getCSVRowIndex(columnName, tag);
	}
	def int getColIndexCSV(String columnName) {
		return csvReader.getCSVColIndex(columnName);
	}
	def int getColumnCountCSV() {
		return csvReader.getCSVColumnCount();
	}
	def static void WirteToCSV(String filePath, String sheetName, int iRowNumber, String columnName, String text){

		int columnNumber = getColumnNumber(filePath, sheetName, columnName)

		WirteToCSV(filePath, sheetName, iRowNumber, columnNumber, text)
	}
}

