/*package common
 import gw.Text.*
 import java.sql.Connection
 import java.sql.DriverManager
 import java.sql.ResultSet
 import java.sql.ResultSetMetaData
 import java.sql.Statement
 import org.apache.poi.hssf.usermodel.HSSFCell
 import org.apache.poi.hssf.usermodel.HSSFRow
 import org.apache.poi.hssf.usermodel.HSSFSheet
 import org.apache.poi.hssf.usermodel.HSSFWorkbook
 import org.apache.poi.ss.usermodel.Cell
 import org.apache.poi.ss.usermodel.Row
 import org.apache.poi.ss.usermodel.Sheet
 import org.apache.poi.ss.usermodel.Workbook
 import org.apache.poi.xssf.usermodel.XSSFCell
 import org.apache.poi.xssf.usermodel.XSSFCellStyle
 import org.apache.poi.xssf.usermodel.XSSFRow
 import org.apache.poi.xssf.usermodel.XSSFSheet
 import org.apache.poi.xssf.usermodel.XSSFWorkbook
 import com.kms.katalon.core.annotation.Keyword
 import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUIdef
 import internal.GlobalVariable as GlobalVariable
 import com.kms.katalon.core.testdata.*
 //import com.mysql.jdbc.Connection
 import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
 import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
 import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
 import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
 import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
 import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
 import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
 import com.kms.katalon.core.model.FailureHandling as FailureHandling
 import com.kms.katalon.core.testcase.TestCase as TestCase
 import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
 import com.kms.katalon.core.testdata.TestData as TestData
 import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
 import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
 import com.kms.katalon.core.testobject.TestObject as TestObject
 import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
 import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
 import com.kms.katalon.core.logging.KeywordLogger
 import java.lang.String
 //From Excel.groovy
 import java.util.List
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.IOException;
 import java.util.Date;
 import org.apache.poi.hssf.usermodel.HSSFCell
 import org.apache.poi.hssf.usermodel.HSSFCellStyle
 import org.apache.poi.hssf.usermodel.HSSFPalette
 import org.apache.poi.hssf.usermodel.HSSFSheet
 import org.apache.poi.hssf.usermodel.HSSFWorkbook
 import org.apache.poi.ss.usermodel.*;
 import org.apache.poi.xssf.usermodel.XSSFCell;
 import org.apache.poi.xssf.usermodel.XSSFRow;
 import org.apache.poi.xssf.usermodel.XSSFSheet;
 import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 //import org.apache.xpath.axes.UnionPathIterator.iterOwner
 import org.junit.After
 import org.apache.poi.hssf.util.HSSFColor
 //import java.awt.Color
 import org.apache.poi.ss.usermodel.Color
 //import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined
 import com.kms.katalon.core.configuration.RunConfiguration
 public class DataBase {
 private static Connection connection = null;
 static String sumFileNameDat
 static String sumFileSheetName
 static String sumFileSheetCol
 *//**
 * Open and return a connection to database
 * @param dataFile absolute file path
 * @return an instance of java.sql.Connection
 *//*
 //Establishing a connection to the DataBase
 @Keyword
 def connectDB(String url, String dbname, String port, String username, String password){
 //Load driver class for your specific database type
 String conn = "jdbc:sqlserver://" + url + ":" + port + ";" + "databaseName=" + dbname
 if(connection != null && !connection.isClosed()){
 connection.close()
 }
 connection = DriverManager.getConnection(conn, username, password)
 return connection
 }
 *//**
 * execute a SQL query on database
 * @param queryString SQL query string
 * @return a reference to returned data collection, an instance of java.sql.ResultSet
 *//*
 //Executing the constructed Query and Saving results in resultset
 @Keyword
 def executeQuery(String queryString) {
 Statement stm = connection.createStatement()
 ResultSet rs = stm.executeQuery(queryString)
 return rs
 }
 @Keyword
 def getQueryDatabyNum(String queryString, String cellRow, String cellCol) {
 int rowCount,colCount
 try {
 Statement stm = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
 ResultSet rs = stm.executeQuery(queryString)
 // Count Columns
 ResultSetMetaData rsmd = rs.getMetaData()
 colCount = rsmd.getColumnCount()
 // Count Rows
 rs.last()
 rowCount = rs.getRow()
 // Reset row before iterating to get data
 rs.beforeFirst()
 // Convert the String Row, Col number as Int data type, because all the data retrieved from Excel had been set as String
 int vIntRow = Integer.valueOf(cellRow).intValue()
 int vIntCol = Integer.valueOf(cellCol).intValue()
 // Retrieve Cell value
 rs.absolute(vIntRow)
 if (rs.getString(vIntCol) == null){
 //internal.GlobalVariable.gPath = "NULL"
 internal.GlobalVariable.gQueryCellValue = "NULL"
 }else{
 //internal.GlobalVariable.gPath = rs.getString(vIntCol)
 internal.GlobalVariable.gQueryCellValue = rs.getString(vIntCol)
 System.out.println("The gQueryCellValue is "+internal.GlobalVariable.gQueryCellValue)
 System.out.println("The 'getQueryDatabyNum' returned Cell Value had been saved into 'internal.GlobalVariable.gQueryCellValue' variable with value: "+internal.GlobalVariable.gQueryCellValue)
 }
 return rs
 }	catch (Exception ex) {
 System.err.println("The cell Row number must be more than 0 and NOT exceed total ROW count: "+ rowCount + "\n The cell Column number must be more than 0 and NOT exceed total COLUMN count: "+ colCount)
 throw new Exception(ex.getMessage())
 }
 }
 @Keyword
 def getQueryDatabyName(String queryString, String cellRow, String nameCol) {
 int rowCount
 try {
 Statement stm = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
 ResultSet rs = stm.executeQuery(queryString)
 // Count Rows
 rs.last()
 rowCount = rs.getRow()
 // Reset row before iterating to get data
 rs.beforeFirst()
 int vIntRow = Integer.valueOf(cellRow).intValue()
 rs.absolute(vIntRow)
 if (rs.getString(nameCol) == null){
 internal.GlobalVariable.gQueryCellValue = "NULL"
 }else{
 internal.GlobalVariable.gQueryCellValue = rs.getString(nameCol)
 System.out.println("The 'getQueryDatabyName' returned Cell Value had been saved into 'internal.GlobalVariable.gQueryCellValue' variable with value: "+internal.GlobalVariable.gQueryCellValue)
 }
 return rs
 }	catch (Exception ex) {
 System.err.println("The cell Row number must be more than 0 and NOT exceed total ROW count: "+ rowCount)
 throw new Exception(ex.getMessage())
 }
 }
 @Keyword
 def getRowCount(String queryString) {
 int rowCount,colCount
 try {
 Statement stm = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
 ResultSet rs = stm.executeQuery(queryString)
 rs.last()
 rowCount = rs.getRow()
 internal.GlobalVariable.gQueryRowCount = rowCount
 System.out.println("The 'getRowCount' returned query total Row Count had been saved into 'internal.GlobalVariable.gQueryRowCount' variable with value: "+internal.GlobalVariable.gQueryRowCount)
 return rowCount
 }	catch (Exception ex) {
 throw new Exception(ex.getMessage())
 }
 }
 @Keyword
 def getColCount(String queryString) {
 int rowCount,colCount
 try {
 Statement stm = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
 ResultSet rs = stm.executeQuery(queryString)
 // Count Columns
 ResultSetMetaData rsmd = rs.getMetaData()
 colCount = rsmd.getColumnCount()
 internal.GlobalVariable.gQueryColCount = colCount
 return colCount
 } catch(Exception ex) {
 throw new Exception(ex.getMessage())
 }
 }
 @Keyword
 def public compareQueryData(String queryString1, String queryString2, String resultFileName, String resultSheetName, String resultColumnName) {
 internal.GlobalVariable.gQueryString1 = queryString1
 internal.GlobalVariable.gQueryString2 = queryString2
 sumFileNameDat = resultFileName
 sumFileSheetName = resultSheetName
 sumFileSheetCol = resultColumnName
 int rowCount1,colCount1,rowCount2,colCount2
 int diffCount = 0, diffCountTol = 0
 // The arrayDiffCols to record all difference column header info
 // The arrayDiffCells to record all difference cells info
 // The arrayMissCols to record all Miss Matched column info
 String arrayDiffCols = "",arrayDiffCells = "", arrayMissCols = ""
 // Test Result sub Title define
 String tDiffHeader = "<<The Col Header Compare Info>>"
 String tDiffCell = "<<The Diff Cells Info>>"
 String tMissMatch = "<<The Miss Matched Info>>"
 String tSummary = "<<The Cells Compare Summary>>"
 try {
 // Define the ArrayList to store compare info
 ArrayList<String> arrayDiffCol = new ArrayList<String>();
 ArrayList<String> arrayDiffCell = new ArrayList<String>();
 ArrayList<String> arrayMissCol = new ArrayList<String>();
 ArrayList<Integer> arrayDiffReturn = new ArrayList<Integer>();
 // Prepare the Query1
 Statement stm1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
 ResultSet rs1 = stm1.executeQuery(queryString1)
 // Count Columns
 ResultSetMetaData rsmd1 = rs1.getMetaData()
 colCount1 = rsmd1.getColumnCount()
 // Count Rows
 rs1.last()
 rowCount1 = rs1.getRow()
 // Reset row before iterating to get data
 rs1.beforeFirst()
 // Prepare the Query2
 Statement stm2 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
 ResultSet rs2 = stm2.executeQuery(queryString2)
 // Count Columns
 ResultSetMetaData rsmd2 = rs2.getMetaData()
 colCount2 = rsmd2.getColumnCount()
 // Count Rows
 rs2.last()
 rowCount2 = rs2.getRow()
 // Reset row before iterating to get data
 rs2.beforeFirst()
 // To Compare Row Column Count firstly.
 // To Compare with Column and Row
 if (rowCount1 == rowCount2 && colCount1 == colCount2){
 for (int vCol=1; vCol<=colCount2; vCol++){
 for (int vRow=1; vRow<=rowCount2; vRow++){
 rs1.absolute(vRow)
 rs2.absolute(vRow)
 if (rs1.getString(vCol) != rs2.getString(vCol)){
 String diffCell = "The different cell data at Row: "+ vRow +"; Column: '"+ rsmd1.getColumnName(vCol) + "' , which query1 = '"+ rs1.getString(vCol) +"' but query2 = '"+ rs2.getString(vCol) +"'"
 arrayDiffCell.add(diffCell)
 arrayDiffReturn.add(vRow)
 arrayDiffReturn.add(vCol)
 diffCount = ++diffCount
 }
 }
 if (diffCount == rowCount2){
 String missCol = "All the data in column '"+rsmd1.getColumnName(vCol)+"' test failed, please double check if this column data miss matched?"
 arrayMissCol.add(missCol)
 }
 diffCountTol += diffCount
 diffCount = 0
 }
 //Get the different column header
 for (int col1=1; col1<=colCount1; col1++){
 if (rsmd1.getColumnName(col1) != rsmd2.getColumnName(col1)){
 String diffCol = "The difference column (ColumnIndex= "+ col1+ ") name in Query2 is '"+ rsmd2.getColumnName(col1) + "' , but in Query1 the column name is '"+ rsmd1.getColumnName(col1)+"'"
 arrayDiffCol.add(diffCol)
 }
 }
 //Sum the difference column header info
 if (arrayDiffCol.size() == 0){
 arrayDiffCols = tDiffHeader + "\n All Column Name are same for Query1 and Query2 \n"
 } else {
 // To record the difference column header info
 for (int vArray=0; vArray<arrayDiffCol.size(); vArray++){
 arrayDiffCols += arrayDiffCol.get(vArray) + "\n"
 }
 arrayDiffCols = tDiffHeader + "\n" + arrayDiffCols
 }
 //Sum the difference cell info
 if (arrayDiffCell.size() == 0){
 arrayDiffCells = tDiffCell + "\n All the cells value are same for Query1 and Query2 \n"
 } else {
 // To record the difference cell info
 for (int vArray=0; vArray<arrayDiffCell.size(); vArray++){
 arrayDiffCells += arrayDiffCell.get(vArray) + "\n"
 }
 arrayDiffCells = tDiffCell + "\n" + arrayDiffCells
 }
 //Sum the Miss Match info
 if (arrayMissCol.size() == 0){
 arrayMissCols = tMissMatch + "\n There are No Miss Matched columns potentially \n"
 } else {
 // To record the Miss Match info
 for (int vArray=0; vArray<arrayMissCol.size(); vArray++){
 arrayMissCols += arrayMissCol.get(vArray) + "\n"
 }
 arrayMissCols = tMissMatch + "\n" + arrayMissCols
 }
 // Difference Summary test result
 String diffCounts = tSummary + "\n The difference cells are "+diffCountTol+" , and the compare passed cells are "+(rowCount2*colCount2-diffCountTol) + "\n"
 // Full Test Result Output
 String diffSum = diffCounts+"\n"+arrayDiffCols+"\n"+arrayMissCols+"\n"+arrayDiffCells
 // Test Result without the sum of difference cells.
 String diffSum = diffCounts+"\n"+arrayDiffCols+"\n"+arrayMissCols
 KeywordLogger log = new KeywordLogger()
 log.logInfo(diffSum)
 //gw.Text.SaveTextToExcel(diffSum, "DB/DB_Excel", "DBCompareSameScaleQueryData", "Test Result", GlobalVariable.Env_Iteration.toString())
 gw.Text.SaveTextToExcel(diffSum, resultFileName, resultSheetName, resultColumnName, GlobalVariable.Env_Iteration.toString())
 } else{
 System.err.println("The two data table has different size scale, please double check query string")
 //gw.Text.SaveTextToExcel(tSummary + "\n The two data table has different size scale, please double check query string", "DB/DB_Excel", "DBCompareSameScaleQueryData", "Test Result", GlobalVariable.Env_Iteration.toString())
 gw.Text.SaveTextToExcel(tSummary + "\n The two data table has different size scale, please double check query string", resultFileName, resultSheetName, resultColumnName, GlobalVariable.Env_Iteration.toString())
 arrayDiffReturn.clear()
 return arrayDiffReturn
 throw new com.kms.katalon.core.exception.StepErrorException('The two data table has different size scale, please double check query string')
 }
 return arrayDiffReturn
 }	catch (Exception ex) {
 throw new Exception(ex.getMessage())
 }
 }
 @Keyword
 def ifExistInQueryData(String queryString1, String queryString2, String resultFileName, String resultSheetName, String resultColumnName) {
 int rowCount1,colCount1,rowCount2,colCount2
 int existCount = 0
 // The arrayExistCells to record all exist cells info
 String arrayExistCells = ""
 // Test result sub Title define
 String tExistCell = "<<The Exist Cells Info>>"
 String tSummary = "<<The Exist Cells Summary>>"
 try {
 ArrayList<String> arrayExistCell = new ArrayList<String>()
 // Prepare the Query1
 Statement stm1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
 ResultSet rs1 = stm1.executeQuery(queryString1)
 // Count Columns
 ResultSetMetaData rsmd1 = rs1.getMetaData()
 colCount1 = rsmd1.getColumnCount()
 // Count Rows
 rs1.last()
 rowCount1 = rs1.getRow()
 // Reset row before iterating to get data
 rs1.beforeFirst()
 // Prepare the Query2
 Statement stm2 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
 ResultSet rs2 = stm2.executeQuery(queryString2)
 // Count Columns
 ResultSetMetaData rsmd2 = rs2.getMetaData()
 colCount2 = rsmd2.getColumnCount()
 // Count Rows
 rs2.last()
 rowCount2 = rs2.getRow()
 // Reset row before iterating to get data
 rs2.beforeFirst()
 // To check if the Query1 data existed in Query2 result
 rs1.first()
 for (int vRow=1; vRow<=rowCount2; vRow++){
 rs2.absolute(vRow)
 for (int vCol=1; vCol<=colCount2; vCol++){
 if (rs1.getString(1) == rs2.getString(vCol)){
 String existCell = "The Query1 data: '"+rs1.getString(1)+"' existed in Query2 at Row: "+ vRow +"; Column: "+ rsmd2.getColumnName(vCol)
 arrayExistCell.add(existCell)
 existCount = ++existCount
 }
 }
 }
 //Sum the exist cells info
 if (arrayExistCell.size() == 0){
 arrayExistCells = tExistCell + "\n The Query1 cell value NOT exist in Query2 \n"
 } else {
 // To record the exist cell info
 for (int vArray=0; vArray<arrayExistCell.size(); vArray++){
 arrayExistCells += arrayExistCell.get(vArray) + "\n"
 }
 arrayExistCells = tExistCell + "\n" + arrayExistCells
 }
 // Exist Cells Summary Output
 String existCounts = tSummary + "\n The Exist cells are "+existCount+" \n"
 String existSum = existCounts+"\n"+ arrayExistCells
 KeywordLogger log = new KeywordLogger()
 log.logInfo(existSum)
 //gw.Text.SaveTextToExcel(existSum, "DB/DB_Excel_ifExistInQueryData", "ifExistInQueryData", "Test Result", GlobalVariable.Env_Iteration.toString())
 gw.Text.SaveTextToExcel(existSum, resultFileName, resultSheetName, resultColumnName, GlobalVariable.Env_Iteration.toString())
 return 0
 }	catch (Exception ex) {
 throw new Exception(ex.getMessage())
 }
 }
 @Keyword
 def ifExistsInQueryRowGroupData(String queryString1, String queryString2, String resultFileName, String resultSheetName, String resultColumnName) {
 int rowCount1,colCount1,rowCount2,colCount2,tempMapCount
 int existCount = 0, colMapCount = 0, nbrExistCount = 0
 int vRow1=1
 String existsDataDetails = ""
 String existsDataSum = ""
 String tDetail = "<<The 'IF Exists' Detail Info>>"
 String tSummary = "<<The 'IF Exists' Summary Info>>"
 try {
 ArrayList<String> arrayNotExistCol = new ArrayList<String>()
 // Prepare the Query1
 Statement stm1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
 ResultSet rs1 = stm1.executeQuery(queryString1)
 // Count Columns
 ResultSetMetaData rsmd1 = rs1.getMetaData()
 colCount1 = rsmd1.getColumnCount()
 // Count Rows
 rs1.last()
 rowCount1 = rs1.getRow()
 // Reset row before iterating to get data
 rs1.beforeFirst()
 // Prepare the Query2
 Statement stm2 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
 ResultSet rs2 = stm2.executeQuery(queryString2)
 // Count Columns
 ResultSetMetaData rsmd2 = rs2.getMetaData()
 colCount2 = rsmd2.getColumnCount()
 // Count Rows
 rs2.last()
 rowCount2 = rs2.getRow()
 // Reset row before iterating to get data
 rs2.beforeFirst()
 // To check if the Query1 data existed in Query2 result
 rs1.first()
 rs2.first()
 //To check if the Query1 has duplicate column name
 String colHeaderDuplicated1 = duplicateColHeader(queryString1)
 String colHeaderDuplicated2 = duplicateColHeader(queryString2)
 if (!(colHeaderDuplicated1.isEmpty() && colHeaderDuplicated2.isEmpty())){
 if (colHeaderDuplicated1.isEmpty()){
 colHeaderDuplicated1 = "No duplicated column header \n"
 }
 if (colHeaderDuplicated2.isEmpty()){
 colHeaderDuplicated2 = "No duplicated column header \n"
 }
 existsDataSum = tSummary+"\n"+"Stop Compare!!! Please make sure the Query has no duplicated columns inside individually \n"+"The Query1 duplicated column header are: \n"+ colHeaderDuplicated1 + "The Query2 duplicated column header are: \n"+ colHeaderDuplicated2
 System.out.println(existsDataSum)
 //gw.Text.SaveTextToExcel(existsDataSum, "DB/DB_Excel_ifExistsInQueryRowGroupData", "ifExistsInQueryRowGroupData", "Test Result", GlobalVariable.Env_Iteration.toString())
 gw.Text.SaveTextToExcel(existsDataSum, resultFileName, resultSheetName, resultColumnName, GlobalVariable.Env_Iteration.toString())
 return 0
 }
 // To Check if the query1 column name existed in query2
 for (int vCol1=1; vCol1<colCount1+1; vCol1++){
 for (int vCol2=1; vCol2<=colCount2; vCol2++){
 if (rsmd1.getColumnName(vCol1) == rsmd2.getColumnName(vCol2)){
 colMapCount = ++colMapCount
 tempMapCount = colMapCount
 } else {
 arrayNotExistCol.add(rsmd1.getColumnName(vCol1))					
 }
 }
 if (tempMapCount == 0){
 arrayNotExistCol.add(rsmd1.getColumnName(vCol1))
 } else {
 tempMapCount = 0
 }
 }
 System.out.println ("The colMapCount value is "+colMapCount )
 if (colMapCount==0){
 existsDataSum = tSummary+"\n"+"All the Query1 column headers can NOT be found out in Query2 headers, it's not ready to do the 'Row Group' value compare, please double check your query"
 System.err.println(existsDataSum)
 //gw.Text.SaveTextToExcel(existsDataSum, "DB/DB_Excel_ifExistsInQueryRowGroupData", "ifExistsInQueryRowGroupData", "Test Result", GlobalVariable.Env_Iteration.toString())
 gw.Text.SaveTextToExcel(existsDataSum, resultFileName, resultSheetName, resultColumnName, GlobalVariable.Env_Iteration.toString())
 return 0
 } else if (colMapCount>0 && colMapCount<colCount1){
 existsDataSum = tSummary+"\n"+"Query1 column headers partically can be found out in Query2"+"\n"+"The Columns "+arrayNotExistCol+" NOT existed in Query2, it's not ready to do the 'Row Group' value compare, please double check your query"
 System.err.println(existsDataSum)
 //gw.Text.SaveTextToExcel(existsDataSum, "DB/DB_Excel_ifExistsInQueryRowGroupData", "ifExistsInQueryRowGroupData", "Test Result", GlobalVariable.Env_Iteration.toString())
 gw.Text.SaveTextToExcel(existsDataSum, resultFileName, resultSheetName, resultColumnName, GlobalVariable.Env_Iteration.toString())
 return 0
 }
 else {
 while (vRow1 < rowCount1+1){
 rs1.absolute(vRow1)
 for (int vRow2=1; vRow2<=rowCount2; vRow2++){
 rs2.absolute(vRow2)
 for (int vCol2=1; vCol2<=colCount2; vCol2++){
 if (rs1.getString(1) == rs2.getString(vCol2) && rsmd1.getColumnName(1) == rsmd2.getColumnName(vCol2)){
 for (int tempRow2=vRow2; tempRow2<=rowCount2; tempRow2++){
 rs2.absolute(tempRow2)
 for (int vNeighbor=2; vNeighbor<=colCount1; vNeighbor++){
 for (int tempCol2=1; tempCol2<=colCount2; tempCol2++){
 if (rs1.getString(vNeighbor) == rs2.getString(tempCol2) && rsmd1.getColumnName(vNeighbor) == rsmd2.getColumnName(tempCol2)){
 nbrExistCount = ++nbrExistCount
 }
 }
 }
 if (nbrExistCount == (colCount1-1)){
 String existsDataDetail = "The Query1 group data at Row "+vRow1+" existed in Query2 Row "+tempRow2+"\n"
 System.err.println("The Query1 group data at Row "+vRow1+" existed in Query2 Row "+tempRow2)
 existsDataDetails += existsDataDetail
 existCount = ++existCount
 } else if (nbrExistCount > (colCount1-1)) {
 System.out.println("Please check if the query2 have duplicated column label")
 }
 nbrExistCount = 0
 tempRow2 = rowCount2
 vCol2 = colCount2
 }
 }
 }
 }
 vRow1 = ++vRow1
 }
 //existsDataDetails = tDetail + "\n" + existsDataDetails
 //gw.Text.SaveTextToExcel(existsDataDetails, "DB/DB_Excel_ifExistsInQueryRowGroupData", "ifExistsInQueryRowGroupData", "Test Result", GlobalVariable.Env_Iteration.toString())
 }
 //existsDataSum = tSummary + "\n" + "The record of Query1 row group data existed in Query2 are " + existCount + "\n"
 existsDataSum = tSummary + "\n" + "The records of Query1 row group data existed in Query2 are " + existCount + ", and there are " + (rowCount1 - existCount) + " Row data records NOT existed in Query2"  +"\n"
 existsDataDetails = "\n" + tDetail + "\n" + existsDataDetails
 //gw.Text.SaveTextToExcel(existsDataSum + existsDataDetails, "DB/DB_Excel_ifExistsInQueryRowGroupData", "ifExistsInQueryRowGroupData", "Test Result", GlobalVariable.Env_Iteration.toString())
 gw.Text.SaveTextToExcel(existsDataSum + existsDataDetails, resultFileName, resultSheetName, resultColumnName, GlobalVariable.Env_Iteration.toString())
 if (existCount == 0){
 System.err.println("All Query1 row group data can NOT be found out in Query2")
 }
 return 0
 }	catch (Exception ex) {
 throw new Exception(ex.getMessage())
 }
 }
 @Keyword
 def ifExistsInQueryCellData(String queryString1, String queryString2) {
 int rowCount1,
 colCount1,
 rowCount2,
 colCount2
 int existCount = 0
 int vCol1 = 1,
 vRow1 = 1
 try {
 // Prepare the Query1
 Statement stm1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
 ResultSet rs1 = stm1.executeQuery(queryString1)
 // Count Columns
 ResultSetMetaData rsmd1 = rs1.getMetaData()
 colCount1 = rsmd1.getColumnCount()
 // Count Rows
 rs1.last()
 rowCount1 = rs1.getRow()
 // Reset row before iterating to get data
 rs1.beforeFirst()
 // Prepare the Query2
 Statement stm2 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
 ResultSet rs2 = stm2.executeQuery(queryString2)
 // Count Columns
 ResultSetMetaData rsmd2 = rs2.getMetaData()
 colCount2 = rsmd2.getColumnCount()
 // Count Rows
 rs2.last()
 rowCount2 = rs2.getRow()
 // Reset row before iterating to get data
 rs2.beforeFirst()
 // To check if the Query1 data existed in Query2 result
 rs1.first()
 rs2.first()
 while ((vRow1 < rowCount1 + 1) && (vCol1 < colCount1 + 1)) {
 rs1.absolute(vRow1)
 while (vCol1 < colCount1 + 1) {
 for (int vRow2 = 1; vRow2 <= rowCount2; vRow2++) {
 rs2.absolute(vRow2)
 for (int vCol2 = 1; vCol2 <= colCount2; vCol2++) {
 if (rs1.getString(vCol1) == rs2.getString(vCol2)) {
 System.err.println("The Query1 data at Row " + vRow1 + "; Column: " + rsmd1.getColumnName(vCol1) + ": '" + rs1.getString(vCol1) + "' --> existed in Query2 at Row: " + vRow2 + "; Column: " + rsmd2.getColumnName(vCol2))
 existCount = ++existCount
 }
 }
 }
 vCol1 = ++vCol1
 }
 vRow1 = ++vRow1
 vCol1 = 1
 }
 if (existCount == 0) {
 System.err.println("All Query1 data can NOT be found out in Query2")
 }
 return 0
 } catch(Exception ex) {
 throw new Exception(ex.getMessage())
 }
 }
 @Keyword
 def public duplicateColHeader(String queryString) {
 int colCount
 int count = 0
 String duplicateHeaderSum = ''
 ArrayList<String> arrayColHeader = new ArrayList<String>()
 ArrayList<String> arrayDuplicateHeader = new ArrayList<String>()
 ArrayList<String> arrayDuplicateHeaderSum = new ArrayList<String>()
 ArrayList<Integer> arrayColIndex = new ArrayList<Integer>()
 try {
 // Prepare the Query1
 Statement stm = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
 ResultSet rs = stm.executeQuery(queryString)
 // Count Columns
 ResultSetMetaData rsmd = rs.getMetaData()
 colCount = rsmd.getColumnCount()
 // Check if it include duplicated columns
 for (int i=1; i<=colCount; i++) {
 arrayColHeader.add(rsmd.getColumnName(i))
 }
 for(int j=0;j<arrayColHeader.size();j++) {
 for(int k =j+1;k<arrayColHeader.size();k++) {
 if(arrayColHeader[j]==arrayColHeader[k] && (!arrayDuplicateHeader.contains(rsmd.getColumnName(j+1)))) {
 arrayColIndex.add(j+1)
 arrayColIndex.add(k+1)
 for (int m = k+1; m<arrayColHeader.size(); m++){
 if(arrayColHeader[j]==arrayColHeader[m]){
 arrayColIndex.add(m+1)
 }
 }
 String duplicateHeader = "The duplicate column '"+rsmd.getColumnName(j+1)+"' displayed at Column Index "+arrayColIndex
 duplicateHeaderSum += duplicateHeader + "\n"
 arrayDuplicateHeader.add(rsmd.getColumnName(j+1))
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
 def ifNOTExistsInQueryData(String queryString1, String queryString2) {
 int rowCount1,colCount1,rowCount2,colCount2
 int existCount = 0
 int vCol1=1,vRow1 = 1
 try {
 // Prepare the Query1
 Statement stm1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
 ResultSet rs1 = stm1.executeQuery(queryString1)
 // Count Columns
 ResultSetMetaData rsmd1 = rs1.getMetaData()
 colCount1 = rsmd1.getColumnCount()
 // Count Rows
 rs1.last()
 rowCount1 = rs1.getRow()
 // Reset row before iterating to get data
 rs1.beforeFirst()
 // Prepare the Query2
 Statement stm2 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
 ResultSet rs2 = stm2.executeQuery(queryString2)
 // Count Columns
 ResultSetMetaData rsmd2 = rs2.getMetaData()
 colCount2 = rsmd2.getColumnCount()
 // Count Rows
 rs2.last()
 rowCount2 = rs2.getRow()
 // Reset row before iterating to get data
 rs2.beforeFirst()
 // To check if the Query1 data existed in Query2 result
 rs1.first()
 rs2.first()
 while ((vRow1 < rowCount1+1) && (vCol1 < colCount1+1)){
 rs1.absolute(vRow1)
 while (vCol1 < colCount1+1){
 for (int vRow2=1; vRow2<=rowCount2; vRow2++){
 rs2.absolute(vRow2)
 for (int vCol2=1; vCol2<=colCount2; vCol2++){
 if (rs1.getString(vCol1) == rs2.getString(vCol2)){
 existCount = ++existCount
 }
 }
 }
 vCol1 = ++vCol1
 }
 vRow1 = ++vRow1
 vCol1 = 1
 }
 if (existCount == 0){
 System.out.println("All Query1 data are NOT existed in Query2")
 } else {
 System.err.println("Some data in Query1 are existed in Query2, try keywords 'ifExistsInQueryData' for existed ones detail info")
 }
 return 0
 }	catch (Exception ex) {
 throw new Exception(ex.getMessage())
 }
 }
 @Keyword // export whole query to excel
 def exportQueryToExcel(String queryString, String path, String fileName, String extName, String sheetName) {
 int row = 1;
 Workbook wb = null
 try {
 if (extName.equals("xls")) {
 wb = new HSSFWorkbook();
 }
 else if (extName.equals("xlsx")) {
 wb = new XSSFWorkbook();
 }
 else {
 System.err.println("your excel format is not correct, please make sure you are using 'Xlsx' or 'Xls' extension name");
 }
 Statement stm = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
 ResultSet rs = stm.executeQuery(queryString)
 ResultSetMetaData rsmd = rs.getMetaData()
 Sheet querySheet = (Sheet) wb.createSheet(sheetName);
 Row headerRow = (Row) querySheet.createRow(0);
 CellStyle dataStyle = (CellStyle) wb.createCellStyle();
 dataStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
 dataStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
 dataStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
 dataStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
 CellStyle headStyle = (CellStyle) wb.createCellStyle();
 headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
 headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
 headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
 headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
 for (int vColHeader = 1; vColHeader <= getColCount(queryString); vColHeader++) {
 Cell queryCell = (Cell) headerRow.createCell(vColHeader - 1);
 queryCell.setCellValue(rsmd.getColumnName(vColHeader));
 queryCell.setCellStyle(headStyle)
 }
 while (rs.next()) {
 Row dataRow = (Row) querySheet.createRow(row);
 for (int vCol = 1; vCol <= getColCount(queryString); vCol++) {
 Cell queryCell = (Cell) dataRow.createCell(vCol - 1);
 if (rs.getString(vCol) == null) {
 queryCell.setCellValue("NULL")
 queryCell.setCellStyle(dataStyle)
 } else {
 queryCell.setCellValue(rs.getString(vCol));
 queryCell.setCellStyle(dataStyle)
 }
 }
 row += 1;
 }
 for (int queryCol = 0; queryCol <= getColCount(queryString); queryCol++) {
 querySheet.autoSizeColumn(queryCol)
 }
 String outputDirPath = path + fileName + "." + extName
 FileOutputStream fileOut = new FileOutputStream(outputDirPath);
 wb.write(fileOut);
 fileOut.close();
 return wb
 }
 catch(Exception ex) {
 throw new Exception(ex.getMessage())
 }
 }
 @Keyword
 def exportQueryToExcelPlus(String queryString, String path, String fileName, String extName, String sheetName, String headerColor, String dataColor) {
 int row = 1
 Workbook wb = null
 try {
 if (extName.equals("xls")) {
 wb = new HSSFWorkbook();
 }
 else if (extName.equals("xlsx")) {
 wb = new XSSFWorkbook();
 }
 else {
 System.err.println("your excel format is not correct, please make sure you are using 'Xlsx' or 'Xls' extension name");
 }
 Statement stm = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
 ResultSet rs = stm.executeQuery(queryString)
 ResultSetMetaData rsmd = rs.getMetaData()
 // Both Format Together
 Sheet querySheet = (Sheet) wb.createSheet(sheetName);
 Row queryHeaderRow = (Row) querySheet.createRow(0);
 CellStyle dataStyle = (CellStyle) wb.createCellStyle();
 dataStyle.setFillForegroundColor(IndexedColors.valueOf(dataColor).getIndex())
 dataStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
 CellStyle headStyle = (CellStyle) wb.createCellStyle();
 headStyle.setFillForegroundColor(IndexedColors.valueOf(headerColor).getIndex())
 headStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
 for (int vColHeader = 1; vColHeader <= getColCount(queryString); vColHeader++) {
 Cell queryHeadCell = (Cell) queryHeaderRow.createCell(vColHeader - 1);
 queryHeadCell.setCellValue(rsmd.getColumnName(vColHeader));
 queryHeadCell.setCellStyle(headStyle)
 }
 while (rs.next()) {
 Row QueryDataRow = (Row) querySheet.createRow(row);
 for (int vCol = 1; vCol <= getColCount(queryString); vCol++) {
 Cell queryDataCell = (Cell) QueryDataRow.createCell(vCol - 1);
 if (rs.getString(vCol) == null) {
 queryDataCell.setCellValue("NULL")
 queryDataCell.setCellStyle(dataStyle)
 } else {
 queryDataCell.setCellStyle(dataStyle)
 //queryCell.getCellStyle().cloneStyleFrom(common.Excel.setStyle("BLUE","BLACK"));
 queryDataCell.setCellValue(rs.getString(vCol));
 }
 }
 row += 1
 System.err.println("The row number is "+row)
 }
 String outputDirPath = path + fileName + "." + extName
 FileOutputStream fileOut = new FileOutputStream(outputDirPath);
 wb.write(fileOut);
 fileOut.close();
 }
 catch(Exception ex) {
 throw new Exception(ex.getMessage())
 }
 }
 @Keyword   //The Pre-condition to run this Keyword is to run "compareQueryData" keyword firstly within the same Test Case
 def compareQueryDataColorful(String queryString, String sumFileName, String detailFileName, String detailFileExtName, String detailFileSheetName, String headerColor, String dataColor) {
 int row = 1
 Workbook wb = null
 ArrayList<Integer> arrayDiffColorCellCol = new ArrayList<Integer>()
 try {
 if (detailFileExtName.equals("xls")) {
 wb = new HSSFWorkbook();
 }
 else if (detailFileExtName.equals("xlsx")) {
 wb = new XSSFWorkbook();
 }
 else {
 System.err.println("your excel format is not correct, please make sure you are using 'Xlsx' or 'Xls' extension name")
 }
 Statement stm = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
 ResultSet rs = stm.executeQuery(queryString)
 ResultSetMetaData rsmd = rs.getMetaData()
 Sheet querySheet = (Sheet) wb.createSheet(detailFileSheetName);
 Row queryHeaderRow = (Row) querySheet.createRow(0);
 Row queryDataRow
 Cell queryDataCell
 CellStyle dataStyle = (CellStyle) wb.createCellStyle();
 dataStyle.setFillForegroundColor(IndexedColors.valueOf(dataColor).getIndex())
 dataStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
 CellStyle headStyle = (CellStyle) wb.createCellStyle();
 headStyle.setFillForegroundColor(IndexedColors.valueOf(headerColor).getIndex())
 headStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
 for (int vColHeader = 1; vColHeader <= getColCount(queryString); vColHeader++) {
 Cell queryHeadCell = (Cell) queryHeaderRow.createCell(vColHeader - 1);
 queryHeadCell.setCellValue(rsmd.getColumnName(vColHeader));
 queryHeadCell.setCellStyle(headStyle)
 }
 ArrayList<Integer> arrayDiffColorCell = compareQueryData(internal.GlobalVariable.gQueryString1, internal.GlobalVariable.gQueryString2, sumFileNameDat, sumFileSheetName, sumFileSheetCol)
 while (rs.next()) {
 //queryDataRow = (Row) querySheet.createRow(row)
 for (int vColorIndex = 0; vColorIndex < arrayDiffColorCell.size(); vColorIndex +=2) {
 if ((arrayDiffColorCell.get(vColorIndex)) == row) {
 arrayDiffColorCellCol.add(arrayDiffColorCell.get(vColorIndex+1))
 }
 }
 queryDataRow = (Row) querySheet.createRow(row)
 for (int vCol = 1; vCol <= getColCount(queryString); vCol++) {
 queryDataCell = (Cell) queryDataRow.createCell(vCol - 1)
 if (arrayDiffColorCellCol.size() == 0) {
 if (rs.getString(vCol) == null) {
 queryDataCell.setCellValue("NULL")
 } else {
 queryDataCell.setCellValue(rs.getString(vCol));
 }
 } else {
 for (int tempColorColIndex = 0; tempColorColIndex < arrayDiffColorCellCol.size(); tempColorColIndex++) {
 if (vCol == (arrayDiffColorCellCol.get(tempColorColIndex))) {
 queryDataCell.setCellStyle(dataStyle)
 if (rs.getString(vCol) == null) {
 queryDataCell.setCellValue("NULL")
 } else {
 queryDataCell.setCellValue(rs.getString(vCol));
 }
 } else {
 if (rs.getString(vCol) == null) {
 queryDataCell.setCellValue("NULL")
 } else {
 queryDataCell.setCellValue(rs.getString(vCol));
 }
 }
 }
 }
 }
 arrayDiffColorCellCol.clear()
 row += 1
 }
 for (int queryCol = 0; queryCol <= getColCount(queryString); queryCol++) {
 querySheet.autoSizeColumn(queryCol)
 }
 String tcID = common.Excel.ReadFromExcel(sumFileName, sumFileSheetName, GlobalVariable.Env_Iteration, 0)
 String detailFileFullName = tcID + "_" + detailFileName + "." + detailFileExtName
 File file = new File(sumFileName)
 String outputDirPath = file.getParent() + "\\" + tcID + "_" + detailFileName + "." + detailFileExtName
 FileOutputStream fileOut = new FileOutputStream(outputDirPath);
 wb.write(fileOut);
 fileOut.close();
 //Add the link for the diff detail
 String colQS1 = common.Excel.ReadFromExcel(sumFileName, sumFileSheetName, GlobalVariable.Env_Iteration, 12)
 if (arrayDiffColorCell.size() != 0) {
 if (colQS1.isEmpty()) {
 common.Excel.WirteToExcelHyperlink(sumFileName,sumFileSheetName,detailFileFullName,GlobalVariable.Env_Iteration,"QS1",detailFileName)
 } else {
 common.Excel.WirteToExcelHyperlink(sumFileName,sumFileSheetName,detailFileFullName,GlobalVariable.Env_Iteration,"QS2",detailFileName)
 }
 }	else {
 return 0
 }
 }
 catch(Exception ex) {
 throw new Exception(ex.getMessage())
 }
 }
 //Closing the connection
 @Keyword
 def closeDatabaseConnection() {
 if(connection != null && !connection.isClosed()){
 connection.close()
 }
 connection = null
 }
 *//**
 * Execute non-query (usually INSERT/UPDATE/DELETE/COUNT/SUM...) on database
 * @param queryString a SQL statement
 * @return single value result of SQL statement
 *//*
 }*/