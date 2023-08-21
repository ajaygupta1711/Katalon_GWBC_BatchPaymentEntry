package common

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable as GlobalVariable
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

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


public class Excel {

	def static int getColumnNumber(String filePath, String sheetName, String columnName){


		FileInputStream file = new FileInputStream (new File(filePath))
		XSSFWorkbook workbook = new XSSFWorkbook(file)
		XSSFSheet sheet = workbook.getSheet(sheetName)
		int cnt = sheet.getRow(0).getLastCellNum()
		println cnt
		int i = 0;

		'Read data from excel'
		for (i = 0; i < cnt; i ++) {
			String text = sheet.getRow(0).getCell(i).getStringCellValue()
			println text
			if(text == columnName) break
		}
		file.close()

		return i
	}

	def static String ReadFromExcel(String filePath, String sheetName, int iRowNumber, String columnName){

		int columnNumber = getColumnNumber(filePath, sheetName, columnName)
		return ReadFromExcel(filePath, sheetName, iRowNumber, columnNumber)
	}

	def static void WirteToExcel(String filePath, String sheetName, int iRowNumber, String columnName, String text){

		int columnNumber = getColumnNumber(filePath, sheetName, columnName)

		WirteToExcel(filePath, sheetName, iRowNumber, columnNumber, text)
	}

	def static List GetValuesFromExcel(String filePath, String sheetName, String columnName){
		int columnNumber = getColumnNumber(filePath, sheetName, columnName)

		return GetValuesFromExcel(filePath, sheetName, columnNumber)
	}

	def static void WirteToExcel(String filePath, String sheetName, int iRowNumber, int columnNumber, String text){

		FileInputStream file = new FileInputStream (new File(filePath))
		XSSFWorkbook workbook = new XSSFWorkbook(file)
		XSSFSheet sheet = workbook.getSheet(sheetName)

		CellStyle wrapStyle = workbook.createCellStyle();
		wrapStyle.setWrapText(true);

		'Write data to excel'
		if(sheet.getRow(iRowNumber) == null)
			sheet.createRow(iRowNumber)
		Cell cell = sheet.getRow(iRowNumber).createCell(columnNumber)
		cell.setCellStyle(wrapStyle)
		cell.setCellValue(text)

		//sheet.getRow(iRowNumber).createCell(columnNumber).setCellValue(text)
		//sheet.createRow(iRowNumber).createCell(columnNumber).setCellValue(text)

		file.close()

		FileOutputStream outFile =new FileOutputStream(new File(filePath))
		workbook.write(outFile)
		outFile.close()
	}

	def static String ReadFromExcel(String filePath, String sheetName, int iRowNumber, int columnNumber){

		FileInputStream file = new FileInputStream (new File(filePath))
		XSSFWorkbook workbook = new XSSFWorkbook(file)
		XSSFSheet sheet = workbook.getSheet(sheetName)

		'Read data from excel'
		String text = sheet.getRow(iRowNumber).getCell(columnNumber).getStringCellValue()
		println text
		file.close()

		return text
	}

	def static List GetValuesFromExcel(String filePath, String sheetName, int columnNumber){
		List lst = new ArrayList<String>()

		FileInputStream file = new FileInputStream (new File(filePath))
		XSSFWorkbook workbook = new XSSFWorkbook(file)
		XSSFSheet sheet = workbook.getSheet(sheetName)
		int cnt = sheet.getLastRowNum()

		'Read data from excel'
		for(int i=1;i<=cnt;i++){
			if(sheet.getRow(i).getCell(columnNumber) != null){
				String text = sheet.getRow(i).getCell(columnNumber).getStringCellValue()
				println text
				lst.add(text)
			}
		}

		file.close()

		return lst
	}

	def static RemoveValuesFromExcel(String filePath, String sheetName, int beginRow){
		//List lst = new ArrayList<String>()

		FileInputStream file = new FileInputStream (new File(filePath))
		XSSFWorkbook workbook = new XSSFWorkbook(file)
		XSSFSheet sheet = workbook.getSheet(sheetName)
		int cnt = sheet.getLastRowNum()

		'Remove data from excel'
		for(int i=beginRow;i<=cnt;i++){
			Row row=sheet.getRow(i)
			if(sheet.getRow(i)==null){
				continue
			}
			sheet.removeRow(row)
		}

		file.close()

		FileOutputStream outFile =new FileOutputStream(new File(filePath))
		workbook.write(outFile)
		outFile.close()
	}

	def static RemoveValuesFromExcel(String filePath, String sheetName, List RowList){
		//List lst = new ArrayList<String>()

		FileInputStream file = new FileInputStream (new File(filePath))
		XSSFWorkbook workbook = new XSSFWorkbook(file)
		XSSFSheet sheet = workbook.getSheet(sheetName)
		int cnt = sheet.getLastRowNum()
		for(int i=0;i<RowList.size();i++){
			'Remove data from excel'

			Row row=sheet.getRow(Integer.valueOf(RowList.get(i)))
			if(sheet.getRow(Integer.valueOf(RowList.get(i)))==null){
				continue
			}
			sheet.removeRow(row)

		}
		file.close()

		FileOutputStream outFile =new FileOutputStream(new File(filePath))
		workbook.write(outFile)
		outFile.close()
	}

	//get all match rownumbers without column name
	@Keyword
	def static List getRowNumbers(String filePath, String sheetName, String cellContent){
		List lst = new ArrayList<String>()

		FileInputStream file = new FileInputStream (new File(filePath))
		XSSFWorkbook workbook = new XSSFWorkbook(file)
		XSSFSheet sheet = workbook.getSheet(sheetName)

		//		for (Row row : sheet) {
		//			for (Cell cell : row) {
		//				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
		//					if (cell.getRichStringCellValue().getString().trim().contains(cellContent)) {
		//						lst.add(row.getRowNum())
		//						//println(row.getRowNum())
		//					}
		//				}
		//			}
		//		}

		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			for(Cell cell : row){
				if (cell.getRichStringCellValue().getString().trim().contains(cellContent)) {
					lst.add(row.getRowNum())
				}
			}
		}
		file.close()
		return lst
	}

	//get all match rownumbers with column name
	@Keyword
	def static List getRowNumbers(String filePath, String sheetName, String columnName, String cellContent){
		List lst = new ArrayList<String>()

		FileInputStream file = new FileInputStream (new File(filePath))
		XSSFWorkbook workbook = new XSSFWorkbook(file)
		XSSFSheet sheet = workbook.getSheet(sheetName)

		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			Cell cell = row.getCell(getColumnNumber(filePath, sheetName, columnName));
			if (cell.getRichStringCellValue().getString().trim().contains(cellContent)) {
				lst.add(row.getRowNum())
			}
		}

		file.close()
		return lst
	}

	def static void removeRow(String filePath, String sheetName, int rowIndex) {
		FileInputStream file = new FileInputStream (new File(filePath))
		XSSFWorkbook workbook = new XSSFWorkbook(file)
		XSSFSheet sheet = workbook.getSheet(sheetName)
		int lastRowNum=sheet.getLastRowNum();
		if(rowIndex>=0&&rowIndex<lastRowNum){
			sheet.shiftRows(rowIndex+1,lastRowNum, -1);
		}
		if(rowIndex==lastRowNum){
			Row removingRow=sheet.getRow(rowIndex);
			if(removingRow!=null){
				sheet.removeRow(removingRow);
			}
		}
		file.close()
		FileOutputStream outFile =new FileOutputStream(new File(filePath))
		workbook.write(outFile)
		outFile.close()
	}

	def static void removeRows(String filePath, String sheetName, List rowList) {
		FileInputStream file = new FileInputStream (new File(filePath))
		XSSFWorkbook workbook = new XSSFWorkbook(file)
		XSSFSheet sheet = workbook.getSheet(sheetName)

		int rowIndex
		int deleteCount=0
		for(int i=0;i<rowList.size();i++){
			if(deleteCount>0){
				rowIndex=Integer.valueOf(rowList.get(i))-1
			}
			else{
				rowIndex=Integer.valueOf(rowList.get(i))
			}
			int lastRowNum=sheet.getLastRowNum()
			println('当前表格有： '+lastRowNum.toString())
			if(rowIndex>=0&&rowIndex<lastRowNum){
				sheet.shiftRows(rowIndex+1,lastRowNum, -1);
				deleteCount+=1
			}
			if(rowIndex==lastRowNum){
				Row removingRow=sheet.getRow(rowIndex);
				if(removingRow!=null){
					sheet.removeRow(removingRow);
				}
			}
		}
		file.close()
		FileOutputStream outFile =new FileOutputStream(new File(filePath))
		workbook.write(outFile)
		outFile.close()
	}

	def static void removeEmptyRows(String filePath, String sheetName) {
		FileInputStream file = new FileInputStream (new File(filePath))
		XSSFWorkbook workbook = new XSSFWorkbook(file)
		XSSFSheet sheet = workbook.getSheet(sheetName)
		Boolean isRowEmpty = Boolean.FALSE;
		for(int i = 0; i <= sheet.getLastRowNum(); i++){
			if(sheet.getRow(i)==null){
				isRowEmpty=true;
				sheet.shiftRows(i + 1, sheet.getLastRowNum()+1, -1);
				i--;
				continue;
			}
			for(int j =0; j<sheet.getRow(i).getLastCellNum();j++){
				if(sheet.getRow(i).getCell(j) == null ||
				sheet.getRow(i).getCell(j).toString().trim().equals("")){
					isRowEmpty=true;
				}else {
					isRowEmpty=false;
					break;
				}
			}
			if(isRowEmpty==true){
				sheet.shiftRows(i + 1, sheet.getLastRowNum()+1, -1);
				i--;
			}
		}
		file.close()
		FileOutputStream outFile =new FileOutputStream(new File(filePath))
		workbook.write(outFile)
		outFile.close()
	}



	@Keyword
	def static void WirteToExcelHyperlink(String filePath, String sheetName, String hyperAddress, int iRowNumber, String columnName, String text){

		FileInputStream file = new FileInputStream (new File(filePath))
		XSSFWorkbook workbook = new XSSFWorkbook(file)
		XSSFSheet sheet = workbook.getSheet(sheetName)

		CellStyle style = workbook.createCellStyle()
		//style.setWrapText(true)
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT)
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER)

		style.setBorderBottom(HSSFCellStyle.BORDER_THIN)
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN)
		style.setBorderTop(HSSFCellStyle.BORDER_THIN)
		style.setBorderRight(HSSFCellStyle.BORDER_THIN)

		if(sheet.getRow(iRowNumber) == null) {
			sheet.createRow(iRowNumber)
		}

		int columnNumber = getColumnNumber(filePath, sheetName, columnName)

		Cell cell = sheet.getRow(iRowNumber).createCell(columnNumber)

		CreationHelper createHelper = workbook.getCreationHelper()
		XSSFCellStyle hlinkstyle = workbook.createCellStyle()
		XSSFFont hlinkfont = workbook.createFont()
		hlinkfont.setUnderline(XSSFFont.U_SINGLE)
		hlinkfont.setColor(XSSFFont.COLOR_RED)
		hlinkstyle.setFont(hlinkfont)
		hlinkstyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_TOP)

		XSSFHyperlink  hyperLink = (XSSFHyperlink) createHelper.createHyperlink(Hyperlink.LINK_FILE)
		hyperLink.setAddress(hyperAddress)

		cell.setCellValue(text)
		cell.setHyperlink(hyperLink)
		cell.setCellStyle(hlinkstyle)

		file.close()

		FileOutputStream outFile =new FileOutputStream(new File(filePath))
		workbook.write(outFile)
		outFile.close()
	}


	@Keyword
	def static void WirteToExcelLocationHyperlink(String filePath, String sheetName, String hyperAddress, String hyperAddressLocation, int iRowNumber, String columnName, String text){

		FileInputStream file = new FileInputStream (new File(filePath))
		XSSFWorkbook workbook = new XSSFWorkbook(file)
		XSSFSheet sheet = workbook.getSheet(sheetName)

		CellStyle style = workbook.createCellStyle()
		//style.setWrapText(true)
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT)
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER)

		style.setBorderBottom(HSSFCellStyle.BORDER_THIN)
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN)
		style.setBorderTop(HSSFCellStyle.BORDER_THIN)
		style.setBorderRight(HSSFCellStyle.BORDER_THIN)

		if(sheet.getRow(iRowNumber) == null) {
			sheet.createRow(iRowNumber)
		}

		int columnNumber = getColumnNumber(filePath, sheetName, columnName)

		Cell cell = sheet.getRow(iRowNumber).createCell(columnNumber)

		CreationHelper createHelper = workbook.getCreationHelper()
		XSSFCellStyle hlinkstyle = workbook.createCellStyle()
		XSSFFont hlinkfont = workbook.createFont()
		hlinkfont.setUnderline(XSSFFont.U_SINGLE)
		hlinkfont.setColor(XSSFFont.COLOR_RED)
		hlinkstyle.setFont(hlinkfont)
		hlinkstyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_TOP)

		XSSFHyperlink  hyperLink = (XSSFHyperlink) createHelper.createHyperlink(Hyperlink.LINK_FILE)
		hyperLink.setAddress(hyperAddress)
		hyperLink.setLocation(hyperAddressLocation)

		cell.setCellValue(text)
		cell.setHyperlink(hyperLink)
		cell.setCellStyle(hlinkstyle)

		file.close()

		FileOutputStream outFile =new FileOutputStream(new File(filePath))
		workbook.write(outFile)
		outFile.close()
	}


	@Keyword
	def static RemoveValuesFromExcelCell(String filePath, String sheetName, int iRowNumber, String iColName){

		//List lst = new ArrayList<String>()
		FileInputStream file = new FileInputStream (new File(filePath))
		XSSFWorkbook workbook = new XSSFWorkbook(file)
		XSSFSheet sheet = workbook.getSheet(sheetName)

		Row row = sheet.getRow(iRowNumber)

		int iColNumber = getColumnNumber(filePath, sheetName, iColName)

		Cell cell = row.createCell(iColNumber)

		CreationHelper createHelper = workbook.getCreationHelper()
		XSSFCellStyle hlinkstyle = workbook.createCellStyle()
		hlinkstyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_TOP)

		XSSFHyperlink  hyperLink = (XSSFHyperlink) createHelper.createHyperlink(Hyperlink.LINK_FILE)
		hyperLink.setAddress("")
		cell.setHyperlink(hyperLink)

		file.close()

		FileOutputStream outFile =new FileOutputStream(new File(filePath))
		workbook.write(outFile)
		outFile.close()
	}



	@Keyword
	def public insertFirstColumns(String excelFile, String excelSheet, int insertColNum) {
		int colCount,rowCount
		ArrayList < String > arrayRowCellValue = new ArrayList < String > ()
		try {
			//Define the workbook object which can support 'xlsx' and 'xls'
			Workbook oldWorkbook = null
			FileInputStream file = new FileInputStream(new File(excelFile))
			File fileName = new File(excelFile)
			String fileNameString = fileName.getName()
			if (fileNameString.endsWith("xlsx")) {
				oldWorkbook = new XSSFWorkbook(file)
			} else if (fileNameString.endsWith("xls")) {
				oldWorkbook = new HSSFWorkbook(file)
			} else {
				System.out.println("Please check your excel file format which should be 'Xlsx' or 'Xls' extension name")
			}

			Sheet oldSheet = (Sheet) oldWorkbook.getSheet(excelSheet)

			//Get the oldSheet(The sheet before insert column) total row numbers and column numbers
			rowCount = oldSheet.getLastRowNum()
			colCount = oldSheet.getRow(0).getLastCellNum()

			//Define the data style for the value and cell after insert columns
			CellStyle dataStyle = (CellStyle) oldWorkbook.createCellStyle()
			dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN)
			dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN)
			dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN)
			dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN)

			//Insert X columns and set value & data style
			for (int vRow = 0; vRow <= rowCount; vRow++) {
				for (int vCol = 0; vCol < colCount; vCol++) {
					oldSheet.getRow(vRow).getCell(vCol).setCellType(Cell.CELL_TYPE_STRING)
					String oldCell = oldSheet.getRow(vRow).getCell(vCol).getStringCellValue().trim()
					arrayRowCellValue.add(oldCell)
				}

				Row oldRow = (Row) oldSheet.createRow(vRow)

				for (int vColNew = 0; vColNew < colCount; vColNew++) {
					Cell newCell = (Cell) oldRow.createCell(vColNew + insertColNum)
					oldSheet.autoSizeColumn(vColNew + insertColNum)
					newCell.setCellStyle(dataStyle)
					newCell.setCellValue(arrayRowCellValue.get(vColNew))
				}

				arrayRowCellValue.clear()

			}

			file.close()

			FileOutputStream outFile = new FileOutputStream(new File(excelFile))
			oldWorkbook.write(outFile)
			outFile.close()

		}
		catch(Exception ex) {
			throw new Exception(ex.getMessage())
		}
	}



	//Check the duplicate header in Excel
	@Keyword
	def public duplicateExcelColHeader(String excelFile, String excelSheet) {
		int colCount
		int count = 0
		String duplicateHeaderSum = ''
		ArrayList<String> arrayColHeader = new ArrayList<String>()
		ArrayList<String> arrayDuplicateHeader = new ArrayList<String>()
		//ArrayList<String> arrayDuplicateHeaderSum = new ArrayList<String>()
		ArrayList<Integer> arrayColIndex = new ArrayList<Integer>()
		try {
			//Define the workbook object which can support 'xlsx' and 'xls'
			Workbook workbook = null
			FileInputStream file = new FileInputStream (new File(excelFile))
			File fileName = new File(excelFile)
			String fileNameString = fileName.getName()
			if (fileNameString.endsWith("xlsx")) {
				workbook = new XSSFWorkbook(file)
			} else if (fileNameString.endsWith("xls")) {
				workbook = new HSSFWorkbook(file)
			} else {
				System.out.println("Please check your excel file format which should be 'Xlsx' or 'Xls' extension name")
			}

			Sheet sheet = (Sheet) workbook.getSheet(excelSheet)

			colCount = sheet.getRow(0).getLastCellNum()
			//System.out.println("The colCount value is: "+ colCount)

			// Check if it include duplicated columns
			for (int i=0; i<colCount; i++) {
				arrayColHeader.add(sheet.getRow(0).getCell(i).getStringCellValue())
			}

			for(int j=0;j<arrayColHeader.size();j++) {
				for(int k =j+1;k<arrayColHeader.size();k++) {
					if(arrayColHeader[j]==arrayColHeader[k] && (!arrayDuplicateHeader.contains(sheet.getRow(0).getCell(j+1).getStringCellValue()))) {
						arrayColIndex.add(j+1)
						arrayColIndex.add(k+1)
						for (int m = k+1; m<arrayColHeader.size(); m++){
							if(arrayColHeader[j]==arrayColHeader[m]){
								arrayColIndex.add(m+1)
							}
						}

						String duplicateHeader = "The duplicate column '"+sheet.getRow(0).getCell(j+1).getStringCellValue()+"' displayed at Column Index "+arrayColIndex
						// Sum and Save all the duplicateHeader info
						duplicateHeaderSum += duplicateHeader + "\n"
						arrayDuplicateHeader.add(sheet.getRow(0).getCell(j+1).getStringCellValue())
						arrayColIndex.clear()
						count++;
					}
				}
			}
			return duplicateHeaderSum
		}
		catch (Exception ex) {
			throw new Exception(ex.getMessage())
		}
	}



	@Keyword
	def public compareExcelData(String dataFileName, String sumFileName, String sumSheetName, String excelFile1, String excelSheet1, String excelFile2, String excelSheet2, String dataColor, String headColor) {

		int rowCount1, rowCount2, colCount1, colCount2
		int diffCount = 0, diffCountTol = 0
		String arrayDiffCols = "",arrayDiffCells = "", arrayMissCols = ""
		ArrayList<String> arrayDiffCol = new ArrayList<String>()
		ArrayList<String> arrayDiffCell = new ArrayList<String>()
		// Test Result sub Title define
		String tDiffHeader = "<<The Col Header Compare Info>>"
		String tDiffCell = "<<The Diff Cells Info>>"
		String tMissMatch = "<<The Miss Matched Info>>"
		String tSummary = "<<The Cells Compare Summary>>"
		try {
			// Define the workbook1 and workbook2
			Workbook workbook1 = null
			Workbook workbook2 = null

			// Define the excel file1 which in FileInputStream
			FileInputStream file1 = new FileInputStream (new File(excelFile1))
			File fileName1 = new File(excelFile1)
			String fileNameString1 = fileName1.getName()
			if (fileNameString1.endsWith("xlsx")) {
				workbook1 = new XSSFWorkbook(file1)
			} else if (fileNameString1.endsWith("xls")) {
				workbook1 = new HSSFWorkbook(file1)
			} else {
				System.out.println("Please check your excel file format which should be 'Xlsx' or 'Xls' extension name")
			}
			//XSSFWorkbook workbook1 = new XSSFWorkbook(file1)
			Sheet sheet1 = (Sheet) workbook1.getSheet(excelSheet1)

			// Define the excel file2 which in FileInputStream
			FileInputStream file2 = new FileInputStream (new File(excelFile2))
			File fileName2 = new File(excelFile2)
			String fileNameString2 = fileName2.getName()
			if (fileNameString2.endsWith("xlsx")) {
				workbook2 = new XSSFWorkbook(file2)
			} else if (fileNameString2.endsWith("xls")) {
				workbook2 = new HSSFWorkbook(file2)
			} else {
				System.out.println("Please check your excel file format which should be 'Xlsx' or 'Xls' extension name")
			}
			//XSSFWorkbook workbook2 = new XSSFWorkbook(file2)
			Sheet sheet2 = (Sheet) workbook2.getSheet(excelSheet2)

			// get the total row count for excel1 and excel2
			rowCount1 = sheet1.getLastRowNum()
			rowCount2 = sheet2.getLastRowNum()

			// get the total column count for excel1 and excel2
			colCount1 = sheet1.getRow(0).getLastCellNum()
			colCount2 = sheet2.getRow(0).getLastCellNum()

			// Define excel file1 data style after compare, including the color, border
			CellStyle dataStyle1 = (CellStyle) workbook1.createCellStyle()
			dataStyle1.setFillForegroundColor(IndexedColors.valueOf(dataColor).getIndex())
			dataStyle1.setFillPattern(CellStyle.SOLID_FOREGROUND)
			dataStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN)
			dataStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN)
			dataStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN)
			dataStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN)

			// Define excel file2 data style after compare, including the color, border
			CellStyle dataStyle2 = (CellStyle) workbook2.createCellStyle();
			dataStyle2.setFillForegroundColor(IndexedColors.valueOf(dataColor).getIndex())
			dataStyle2.setFillPattern(CellStyle.SOLID_FOREGROUND)
			dataStyle2.setFillPattern(CellStyle.SOLID_FOREGROUND)
			dataStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN)
			dataStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN)
			dataStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN)
			dataStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN)

			// Define excel file1 header style after compare, including the color, border
			CellStyle headStyle1 = (CellStyle) workbook1.createCellStyle()
			headStyle1.setFillForegroundColor(IndexedColors.valueOf(headColor).getIndex())
			headStyle1.setFillPattern(CellStyle.SOLID_FOREGROUND)

			// Define excel file2 header style after compare, including the color, border
			CellStyle headStyle2 = (CellStyle) workbook2.createCellStyle()
			headStyle2.setFillForegroundColor(IndexedColors.valueOf(headColor).getIndex())
			headStyle2.setFillPattern(CellStyle.SOLID_FOREGROUND)

			// The prepare check if the excel1 and excel2 meet the Pre-condition to compare.
			if (rowCount1 == rowCount2 && colCount1 == colCount2){
				for (int vCol=0; vCol<colCount2; vCol++){
					for (int vRow=1; vRow<=rowCount2; vRow++){

						String cell1 = sheet1.getRow(vRow).getCell(vCol).getStringCellValue()
						String cell2 = sheet2.getRow(vRow).getCell(vCol).getStringCellValue()

						if (cell1 != cell2){
							String diffCell = "The different cell data at Row: "+ vRow +"; Column: '"+ sheet1.getRow(0).getCell(vCol) + "' , which ExcelSheet1 = '"+ sheet1.getRow(vRow).getCell(vCol) +"' but ExcelSheet2 = '"+ sheet2.getRow(vRow).getCell(vCol) +"'"
							arrayDiffCell.add(diffCell)
							/*System.out.println("When Not Equal The vRow number is " + vRow)
							 System.out.println("When Not Equal The vCol number is "+ vCol)*/

							sheet1.getRow(vRow).getCell(vCol).setCellStyle(dataStyle1)
							sheet2.getRow(vRow).getCell(vCol).setCellStyle(dataStyle2)

							++diffCount

						} else{
							System.out.println("Test passed for two sheet")
						}
					}
					diffCountTol += diffCount
					diffCount = 0
				}

				//Get the different column header info
				for (int col1=0; col1<colCount1; col1++){
					String header1 = sheet1.getRow(0).getCell(col1)
					String header2 = sheet2.getRow(0).getCell(col1)

					System.out.println("The header1 and header2 are" + header1 + "&" + header2)

					header1.trim()
					header2.trim()
					if (header1 != header2){
						String diffCol = "The difference column (ColumnIndex= "+ col1+ ") name in ExcelSheet2 is '"+ sheet2.getRow(0).getCell(col1) + "' , but in ExcelSheet1 the column name is '"+ sheet1.getRow(0).getCell(col1) + "'"
						arrayDiffCol.add(diffCol)
					}
				}
				//Sum the difference column header info
				if (arrayDiffCol.size() == 0){
					arrayDiffCols = tDiffHeader + "\n All Column Name are same for ExcelSheet1 and ExcelSheet2 \n"
				} else {
					// To record the difference column header info
					for (int vArray=0; vArray<arrayDiffCol.size(); vArray++){
						arrayDiffCols += arrayDiffCol.get(vArray) + "\n"
					}
					arrayDiffCols = tDiffHeader + "\n" + arrayDiffCols
				}

				//Sum the difference cell info
				if (arrayDiffCell.size() == 0){
					arrayDiffCells = tDiffCell + "\n All the cells value are same for ExcelSheet1 and ExcelSheet2 \n"
					gw.Text.SaveTextToExcel(tSummary + "\n All the cells value are same for ExcelSheet1 and ExcelSheet2 \n", dataFileName, sumSheetName, "Test Result", GlobalVariable.Env_Iteration.toString())
				} else {
					// To record the difference cell info
					for (int vArray=0; vArray<arrayDiffCell.size(); vArray++){
						arrayDiffCells += arrayDiffCell.get(vArray) + "\n"
					}
					arrayDiffCells = tDiffCell + "\n" + arrayDiffCells
				}

			} else{
				//Write down the compare info into sum file
				System.out.println("The two Excel data table has different size scale, please double check the excel data or styled cells amount")
				//gw.Text.SaveTextToExcel(tSummary + "\n The two data table has different size scale, please double check query string", "DB/DB_Excel", "DBCompareSameScaleQueryData", "Test Result", GlobalVariable.Env_Iteration.toString())
				gw.Text.SaveTextToExcel(tSummary + "\n The two Excel data table has different size scale, please double check the excel data or styled cells amount", dataFileName, sumSheetName, "Test Result", GlobalVariable.Env_Iteration.toString())
			}

			//Write down the differ info into sum file
			String diffCounts = tSummary + "\n The difference cells are "+diffCountTol+" , and the compare passed cells are "+(rowCount2*colCount2-diffCountTol) + "\n"
			String diffSum = diffCounts+"\n"+arrayDiffCols+"\n"+arrayDiffCells

			//Get excel name and used to generate HyperLink in sum report.
			File excel1 = new File(excelFile1)
			String hyperlinkAddress1 = excel1.getName()
			File excel2 = new File(excelFile2)
			String hyperlinkAddress2 = excel2.getName()

			if (diffCountTol>0) {
				gw.Text.SaveTextToExcel(diffSum, dataFileName, sumSheetName, "Test Result", GlobalVariable.Env_Iteration.toString())

				WirteToExcelHyperlink (sumFileName,sumSheetName,hyperlinkAddress1,GlobalVariable.Env_Iteration,"Excel1_CompareResult","excel1_difference")
				WirteToExcelHyperlink (sumFileName,sumSheetName,hyperlinkAddress2,GlobalVariable.Env_Iteration,"Excel2_CompareResult","excel2_difference")
			}

			file1.close()
			file2.close()

			//Write excel1 and excel2 for compare result.
			FileOutputStream outFile1 = new FileOutputStream(new File(excelFile1))
			workbook1.write(outFile1)
			outFile1.close()

			FileOutputStream outFile2 = new FileOutputStream(new File(excelFile2))
			workbook2.write(outFile2)
			outFile2.close()

			return 0 //arrayDiffReturn
		}	catch (Exception ex) {
			throw new Exception(ex.getMessage())
		}
	}




	@Keyword
	def ifExistsInExcelData(String dataFileName, String sumFileName, String sumSheetName, String excelFile1, String excelSheet1, String excelFile2, String excelSheet2, String dataColor, String headColor) {
		int rowCount1,colCount1,rowCount2,colCount2
		int existCount = 0
		int vCol1 = 0, vRow1 = 1
		// The arrayExistCells to record all exist cells info
		String arrayExistsCells = ""
		// Test result sub Title define
		String tExistsCell = "<<The Exist Cells Info>>"
		String tSummary = "<<The Exist Cells Summary>>"
		ArrayList<String> arrayExistsCell = new ArrayList<String>()
		try {
			//Define two workbook
			Workbook workbook1 = null
			Workbook workbook2 = null
			FileInputStream file1 = new FileInputStream (new File(excelFile1))

			File fileName1 = new File(excelFile1)
			String fileNameString1 = fileName1.getName()
			if (fileNameString1.endsWith("xlsx")) {
				workbook1 = new XSSFWorkbook(file1)
			} else if (fileNameString1.endsWith("xls")) {
				workbook1 = new HSSFWorkbook(file1)
			} else {
				System.out.println("Please check your excel file format which should be 'Xlsx' or 'Xls' extension name")
			}
			//XSSFWorkbook workbook1 = new XSSFWorkbook(file1)
			Sheet sheet1 = (Sheet) workbook1.getSheet(excelSheet1)

			FileInputStream file2 = new FileInputStream (new File(excelFile2))

			File fileName2 = new File(excelFile2)
			String fileNameString2 = fileName2.getName()
			if (fileNameString2.endsWith("xlsx")) {
				workbook2 = new XSSFWorkbook(file2)
			} else if (fileNameString2.endsWith("xls")) {
				workbook2 = new HSSFWorkbook(file2)
			} else {
				System.out.println("Please check your excel file format which should be 'Xlsx' or 'Xls' extension name")
			}
			//XSSFWorkbook workbook2 = new XSSFWorkbook(file2)
			Sheet sheet2 = (Sheet) workbook2.getSheet(excelSheet2)

			//get the excel row count and column count
			rowCount1 = sheet1.getLastRowNum()
			rowCount2 = sheet2.getLastRowNum()

			colCount1 = sheet1.getRow(0).getLastCellNum()
			colCount2 = sheet2.getRow(0).getLastCellNum()

			//Set the data style for compare result.
			CellStyle dataStyle1 = (CellStyle) workbook1.createCellStyle()
			dataStyle1.setFillForegroundColor(IndexedColors.valueOf(dataColor).getIndex())
			dataStyle1.setFillPattern(CellStyle.SOLID_FOREGROUND)
			dataStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN)
			dataStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN)
			dataStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN)
			dataStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN)

			CellStyle dataStyle2 = (CellStyle) workbook2.createCellStyle()
			dataStyle2.setFillForegroundColor(IndexedColors.valueOf(dataColor).getIndex())
			dataStyle2.setFillPattern(CellStyle.SOLID_FOREGROUND)
			dataStyle2.setFillPattern(CellStyle.SOLID_FOREGROUND)
			dataStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN)
			dataStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN)
			dataStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN)
			dataStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN)

			CellStyle headStyle1 = (CellStyle) workbook1.createCellStyle()
			headStyle1.setFillForegroundColor(IndexedColors.valueOf(headColor).getIndex())
			headStyle1.setFillPattern(CellStyle.SOLID_FOREGROUND)

			CellStyle headStyle2 = (CellStyle) workbook2.createCellStyle()
			headStyle2.setFillForegroundColor(IndexedColors.valueOf(headColor).getIndex())
			headStyle2.setFillPattern(CellStyle.SOLID_FOREGROUND)

			//Pre condition check for the two excel compare.
			while ((vRow1 < rowCount1 + 1) && (vCol1 < colCount1)) {
				//rs1.absolute(vRow1)
				while (vCol1 < colCount1) {
					for (int vRow2 = 1; vRow2 <= rowCount2; vRow2++) {
						//rs2.absolute(vRow2)
						for (int vCol2 = 0; vCol2 < colCount2; vCol2++) {
							String cell1 = sheet1.getRow(vRow1).getCell(vCol1).getStringCellValue()
							String cell2 = sheet2.getRow(vRow2).getCell(vCol2).getStringCellValue()
							String cellHeader1 = sheet1.getRow(0).getCell(vCol1).getStringCellValue()
							String cellHeader2 = sheet2.getRow(0).getCell(vCol2).getStringCellValue()

							if ((cell1.trim() == cell2.trim()) &&  (cellHeader1.trim() == cellHeader2.trim())) {
								arrayExistsCells = "The ExcelSheet1 data at Row " + vRow1 + "; Column: " + cellHeader1 + ": '" + cell1 + "' --> existed in ExcelSheet2 at Row: " + vRow2 + "; Column: " + cellHeader2
								/*System.out.println("The ExcelSheet1 data at Row " + vRow1 + "; Column: " + cellHeader1 + ": '" + cell1 + "' --> existed in ExcelSheet2 at Row: " + vRow2 + "; Column: " + cellHeader2)*/
								sheet1.getRow(vRow1).getCell(vCol1).setCellStyle(dataStyle1)
								sheet2.getRow(vRow2).getCell(vCol2).setCellStyle(dataStyle2)
								arrayExistsCell.add(arrayExistsCells)
								arrayExistsCells = ""
								existCount = ++existCount
							}
							//System.out.println("The Sheet2 cell Row: " + vRow2 + " Col: " + vCol2 + " had been compared!")
						}
					}
					vCol1 = ++vCol1
				}
				vRow1 = ++vRow1
				vCol1 = 0
			}

			//Sum the difference cell info
			if (arrayExistsCell.size() == 0){
				arrayExistsCells = tExistsCell + "\n All the cells value are same for ExcelSheet1 and ExcelSheet2 \n"
			} else {
				// To record the difference cell info
				for (int vArray=0; vArray<arrayExistsCell.size(); vArray++){
					arrayExistsCells += arrayExistsCell.get(vArray) + "\n"
				}
				arrayExistsCells = tExistsCell + "\n" + arrayExistsCells
			}

			// Write the compare sum result
			gw.Text.SaveTextToExcel(arrayExistsCells , dataFileName, sumSheetName, "Test Result", GlobalVariable.Env_Iteration.toString())

			if (existCount == 0) {
				System.err.println("All ExcelSheet1 data matched Header Name can NOT be found out in ExcelSheet2")
				arrayExistsCells = tExistsCell + "\n" + "All ExcelSheet1 data matched Header Name can NOT be found out in ExcelSheet2"
			}


			File excel1 = new File(excelFile1)
			String hyperlinkAddress1 = excel1.getName()

			File excel2 = new File(excelFile2)
			String hyperlinkAddress2 = excel2.getName()

			//Add the Hyper Link into Excel which can open compare detail report
			if (existCount>0) {
				//gw.Text.SaveTextToExcel(diffSum, dataFileName, sumSheetName, "Test Result", GlobalVariable.Env_Iteration.toString())
				WirteToExcelHyperlink (sumFileName,sumSheetName,hyperlinkAddress1,GlobalVariable.Env_Iteration,"Excel1_CompareResult","excel1_difference")
				WirteToExcelHyperlink (sumFileName,sumSheetName,hyperlinkAddress2,GlobalVariable.Env_Iteration,"Excel2_CompareResult","excel2_difference")
			}

			file1.close()
			file2.close()

			//Output the excel info
			FileOutputStream outFile1 = new FileOutputStream(new File(excelFile1))
			workbook1.write(outFile1)
			outFile1.close()

			FileOutputStream outFile2 = new FileOutputStream(new File(excelFile2))
			workbook2.write(outFile2)
			outFile2.close()

		}	catch (Exception ex) {
			throw new Exception(ex.getMessage())
		}

	}



	@Keyword
	def static copyExcelSheetByIndex(String excelFile, int copyIndex, String copySheetName) {
		try {

			//Workbook Define
			Workbook workbook = null
			FileInputStream file = new FileInputStream (new File(excelFile))

			File fileName1 = new File(excelFile)
			String fileNameString1 = fileName1.getName()
			if (fileNameString1.endsWith("xlsx")) {
				workbook = new XSSFWorkbook(file)
			} else if (fileNameString1.endsWith("xls")) {
				workbook = new HSSFWorkbook(file)
			} else {
				System.out.println("Please check your excel file format which should be 'Xlsx' or 'Xls' extension name")
			}

			// Copy the first work sheet and rename the copy
			Sheet sheetCopy = (Sheet) workbook.cloneSheet(copyIndex)
			workbook.setSheetName(1,copySheetName)

			file.close()

			FileOutputStream outFile = new FileOutputStream(new File(excelFile))
			workbook.write(outFile)
			outFile.close()

		}	catch (Exception ex) {
			throw new Exception(ex.getMessage())
		}

	}


	@Keyword
	def static copyExcelSheetByName(String excelFile, String copySourceSheet, String copyTargetSheet) {
		try {

			//Workbook Define
			Workbook workbook = null
			FileInputStream file = new FileInputStream (new File(excelFile))

			File fileName1 = new File(excelFile)
			String fileNameString1 = fileName1.getName()
			if (fileNameString1.endsWith("xlsx")) {
				workbook = new XSSFWorkbook(file)
			} else if (fileNameString1.endsWith("xls")) {
				workbook = new HSSFWorkbook(file)
			} else {
				System.out.println("Please check your excel file format which should be 'Xlsx' or 'Xls' extension name")
			}

			// Copy the first work sheet and rename the copy
			Sheet sheetCopy = (Sheet) workbook.cloneSheet(workbook.getSheetIndex(copySourceSheet))
			workbook.setSheetName(workbook.getSheetIndex(copySourceSheet+" (2)"),copyTargetSheet)

			file.close()

			FileOutputStream outFile = new FileOutputStream(new File(excelFile))
			workbook.write(outFile)
			outFile.close()

		}	catch (Exception ex) {
			throw new Exception(ex.getMessage())
		}

	}

	@Keyword
	def void writeToExcel(String filePath, String sheetName, int rowIndex, int columnIndex, String value){

		FileInputStream file = new FileInputStream(new File(filePath))

		XSSFWorkbook workbook = new XSSFWorkbook(file)

		XSSFSheet sheet = workbook.getSheet(sheetName)

		sheet.getRow(rowIndex).createCell(columnIndex).setCellValue(value)

		file.close()

		FileOutputStream outFile = new FileOutputStream(new File(filePath))

		workbook.write(outFile)

		outFile.close()
	}



}
