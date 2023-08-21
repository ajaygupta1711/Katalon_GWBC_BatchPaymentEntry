package gw

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
import com.kms.katalon.core.testdata.TestDataInfo
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.testobject.ConditionType

import internal.GlobalVariable as GlobalVariable
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
//import org.openqa.selenium.remote.server.handler.SendKeys
import java.text.SimpleDateFormat
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.common.WebUiCommonHelper

import common.CSVReader
import common.Excel as Excel
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import java.awt.Robot
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.event.KeyEvent
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger

public class Text {

	//	//Excel iExcel = new Excel()
	//	@Keyword
	//	def void SetTextByJS(TestObject to, String text ) {
	//		WebElement element = WebUiCommonHelper.findWebElement(to,GlobalVariable.gWaitTimeOut)
	//		WebUI.executeJavaScript("arguments[0].value=" + text, Arrays.asList(element))
	//	}

	/**
	 * Set the value of an input field, as though you type it in. It will retry if any exception throws.
	 * @param to represent a web element
	 * @param text the text to type
	 */
	@Keyword
	def void SetText(TestObject to, String text ) {
		if (text == null || text == "")
			return

		if(text.contains(',')){
			String[] inputs = text.split(',')
			TestData testData = TestDataFactory.findTestData(inputs[0])
			String filePath = testData.getSourceUrl()
			text = Excel.ReadFromExcel(filePath, inputs[1], Integer.valueOf(inputs[3]), inputs[2])
		}

		try {
			WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)
			WebUI.waitForElementVisible(to, GlobalVariable.gWaitTimeOut)//add to protect element, Edward 06/17
			WebUI.waitForElementClickable(to, GlobalVariable.gWaitTimeOut)//add to protect element, Edward 06/17
			WebUI.setText(to, text)
		} catch (Exception e) {
			//WebUI.sendKeys(to, Keys.chord(Keys.F5))
			//Thread.sleep(GlobalVariable.gSleepTime)
			Thread.sleep(GlobalVariable.gSleepTime)
			WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)
			WebUI.setText(to, text)
			e.printStackTrace()
		}
	}

	/**
	 * Set the value of an input field, as though you type the value key-by-key. It will retry if any exception throws.
	 * @param to represent a web element
	 * @param text the text to type
	 */
	@Keyword
	def void TypeText(TestObject to, String text ) {

		if (text == null || text == "")
			return

		if(text.contains(',')){
			String[] inputs = text.split(',')
			TestData testData = TestDataFactory.findTestData(inputs[0])
			String filePath = testData.getSourceUrl()
			text = Excel.ReadFromExcel(filePath, inputs[1], Integer.valueOf(inputs[3]), inputs[2])
		}

		try {
			WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)
			WebUI.click(to)
			Thread.sleep(GlobalVariable.gSleepTime)
			WebUI.sendKeys(to, text)
		} catch (Exception e) {
			//WebUI.sendKeys(to, Keys.chord(Keys.F5))
			WebUI.click(to)
			Thread.sleep(GlobalVariable.gSleepTime)
			WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)
			WebUI.sendKeys(to, text)
			e.printStackTrace()
		}
	}

	/**
	 * Simulates keystroke events on the specified element, as though you typed the value key-by-key. It will retry if any exception throws.
	 * @param to - represent a web element
	 * @param strKey - the combination of keys to type
	 */
	@Keyword
	def void SendKey(TestObject to, String strKey) {
		try {
			Thread.sleep(GlobalVariable.gSleepTime)
			WebUI.sendKeys(to, strKey)
			Thread.sleep(GlobalVariable.gSleepTime)
		} catch (Exception e) {
			Thread.sleep(GlobalVariable.gSleepTime)
			WebUI.sendKeys(to, strKey)
			Thread.sleep(GlobalVariable.gSleepTime)
			e.printStackTrace()
		}
	}

	@Keyword
	def void EnterValue(String sectionName, String key, String value, String type) {
		switch(type){
			case 'input':
				SetText(findTestObject('Common/pge_Common/txt_Input', [('SectionName'): sectionName, ('LabelKey'): key]), value)
				break
			case 'select':
				WebUI.selectOptionByLabel(findTestObject('Common/pge_Common/lst_Select', [('SectionName'): sectionName, ('LabelKey'): key]), value, false, FailureHandling.STOP_ON_FAILURE)
				break
			default:
				break
		}
	}

	/**
	 * Set the value of an input field, as though you type it in. And send TAB key on the input filed.
	 * @param to represent a web element
	 * @param text the text to type
	 */
	@Keyword
	def void SetTextAndTab(TestObject to, String text ) {
		SetText(to, text)
		Thread.sleep(GlobalVariable.gSleepTime)
		SendKey(to, Keys.chord(Keys.TAB))
		Thread.sleep(GlobalVariable.gSleepTime)
		//SendKey(to, Keys.chord(Keys.TAB))
	}


	/**
	 * Set the value of an input field, as though you type it in. And send ENTER key on the input filed.
	 * @param to represent a web element
	 * @param text the text to type
	 */
	@Keyword
	def void SetTextAndEnter(TestObject to, String text ) {
		SetText(to, text)
		Thread.sleep(GlobalVariable.gSleepTime)
		SendKey(to, Keys.chord(Keys.ENTER))
		Thread.sleep(GlobalVariable.gSleepTime)
		//WebUI.delay(GlobalVariable.delaySecond)
	}

	/**
	 * Set the value of an input field, as though you type the value key-by-key. And send ENTER key on the input filed.
	 * @param to represent a web element
	 * @param text the text to type
	 */

	@Keyword
	def GetPolicyNumber(String PolicyString){
		def policynum = PolicyString
		if(PolicyString.contains(',')){
			String[] inputs = PolicyString.split(',')
			TestData testData = TestDataFactory.findTestData(inputs[0])
			String filePath = testData.getSourceUrl()
			policynum = Excel.ReadFromExcel(filePath, inputs[1], Integer.valueOf(inputs[3]), inputs[2])
		}
		return policynum
	}

	@Keyword
	def void TypeTextAndEnter(TestObject to, String text ) {
		TypeText(to, text)
		Thread.sleep(GlobalVariable.gSleepTime)
		SendKey(to, Keys.chord(Keys.ENTER))
		Thread.sleep(GlobalVariable.gSleepTime)
	}

	@Keyword
	def void TypeTextAndTab(TestObject to, String text ) {
		TypeText(to, text)
		Thread.sleep(GlobalVariable.gSleepTime)
		SendKey(to, Keys.chord(Keys.TAB))
		Thread.sleep(GlobalVariable.gSleepTime)
	}

	/**
	 * Set the value of FEIN/SSN field, as though you type it in. Type a automatically generated FEIN/SSN if no value received. 
	 * @param to represent the web element of FEIN/SSN
	 * @param text the text to type
	 */
	@Keyword
	def void SetTaxID(TestObject to, String text ) {
		if (text == null || text == "")
			text = TaxID.TaxIDGenerator()

		SetText(to, text)
	}


	@Keyword
	def void SetSSN(TestObject to, String text ) {
		if (text == null || text == "")
			text = TaxID.SSNGenerator()

		SetText(to, text)
	}

	@Keyword
	def void SetEIN(TestObject to, String text ) {
		if (text == null || text == "")
			text = TaxID.FEINGenerator()

		SetText(to, text)
	}


	/**
	 * Set the value of an input field from a spreadsheet based on column name , as though you type it in. Send ENTER key on the input filed after set the value.
	 * @param to represent the web element
	 * @param dataFileName name of the Data File which bound with the spreadsheet
	 * @param sheetName sheet name of the data the spreadsheet
	 * @param columnName column name of the data in the spreadsheet
	 */
	@Keyword
	def void SetTextFromExcel(TestObject to, String dataFileName, String sheetName, String columnName){

		TestData testData = TestDataFactory.findTestData(dataFileName)
		String filePath = testData.getSourceUrl()

		//String text = iExcel.ReadFromExcel(filePath, sheetName, Integer.valueOf(rowNumber), columnNumber)
		String text = Excel.ReadFromExcel(filePath, sheetName, GlobalVariable.Env_Iteration, columnName)

		SetText(to, text)
		//	   WebUI.setText(to, text)
		//	   WebUI.sendKeys(to, Keys.chord(Keys.ENTER))
		//	   Thread.sleep(GlobalVariable.gSleepTime)
	}

	/**
	 * Set the value of an input field from a spreadsheet based on column name & row number , as though you type it in. Send ENTER key on the input filed after set the value.
	 * @param to represent the web element
	 * @param dataFileName name of the Data File which bound with the spreadsheet
	 * @param sheetName sheet name of the data the spreadsheet
	 * @param columnName column name of the data in the spreadsheet
	 * @param rowNumber row number of the data the spreadsheet
	 */
	@Keyword
	def void SetTextFromExcel(TestObject to, String dataFileName, String sheetName, String cloumnName, String rowNumber){

		TestData testData = TestDataFactory.findTestData(dataFileName)
		String filePath = testData.getSourceUrl()

		String text = Excel.ReadFromExcel(filePath, sheetName, Integer.valueOf(rowNumber), cloumnName)

		SetText(to, text)
	}

	/**
	 * Set the value of an input field from a spreadsheet based on column name , as though you type the value key-by-key. Send ENTER key on the input filed after set the value.
	 * @param to represent the web element
	 * @param dataFileName name of the Data File which bound with the spreadsheet
	 * @param sheetName sheet name of the data the spreadsheet
	 * @param columnName column name of the data in the spreadsheet
	 */
	@Keyword
	//def void TypeTextFromExcel(TestObject to, String dataFileName, String sheetName, String rowNumber, String cloumnName){
	def void TypeTextFromExcel(TestObject to, String dataFileName, String sheetName, String cloumnName){

		TestData testData = TestDataFactory.findTestData(dataFileName)
		String filePath = testData.getSourceUrl()

		//String text = iExcel.ReadFromExcel(filePath, sheetName, Integer.valueOf(rowNumber), columnNumber)
		String text = Excel.ReadFromExcel(filePath, sheetName, GlobalVariable.Env_Iteration, cloumnName)
		TypeTextAndEnter(to, text)

		//	   WebUI.sendKeys(to, text)
		//	   Thread.sleep(GlobalVariable.gSleepTime)
		//	   WebUI.sendKeys(to, Keys.chord(Keys.ENTER))
		//	   Thread.sleep(GlobalVariable.gSleepTime)
	}

	/**
	 * Set the value of an input field from a spreadsheet based on column name & row number , as though you type the value key-by-key. Send ENTER key on the input filed after set the value.
	 * @param to represent the web element
	 * @param dataFileName name of the Data File which bound with the spreadsheet
	 * @param sheetName sheet name of the data the spreadsheet
	 * @param columnName column name of the data in the spreadsheet
	 * @param rowNumber row number of the data the spreadsheet
	 */
	@Keyword
	def void TypeTextFromExcel(TestObject to, String dataFileName, String sheetName, String cloumnName, String rowNumber){

		TestData testData = TestDataFactory.findTestData(dataFileName)
		String filePath = testData.getSourceUrl()

		String text = Excel.ReadFromExcel(filePath, sheetName, Integer.valueOf(rowNumber), cloumnName)

		TypeTextAndEnter(to, text)
	}

	/**
	 * Get the visible innerText of the web element, and save the value into a spreadsheet based on column name 
	 * @param to represent the web element
	 * @param dataFileName name of the Data File which bound with the spreadsheet
	 * @param sheetName sheet name of the data the spreadsheet
	 * @param columnName column name of the data in the spreadsheet
	 */
	@Keyword
	//def void GetTextAndSave(TestObject to, String dataFileName, String sheetName, String rowNumber, String cloumnName) {
	def void GetTextAndSave(TestObject to, String dataFileName, String sheetName, String columnName) {

		String text = WebUI.getText(to)
		TestData testData = TestDataFactory.findTestData(dataFileName)
		String filePath = testData.getSourceUrl()

		Excel.WirteToExcel(filePath, sheetName, GlobalVariable.Env_Iteration, columnName, text)

		//iExcel.WirteToExcel(filePath, sheetName, Integer.valueOf(rowNumber), columnNumber, text)

	}

	def void GetTextAndSaveCSV(TestObject to, String sheetName, String columnName) {

		String text = WebUI.getText(to)
		//TestData testData = TestDataFactory.findTestData(dataFileName)
		//String filePath = testData.getSourceUrl()

		CSVReader.WirteToCSV(sheetName, GlobalVariable.Env_Iteration, columnName, text)

		//iExcel.WirteToExcel(filePath, sheetName, Integer.valueOf(rowNumber), columnNumber, text)

	}

	/**
	 * Get the visible innerText of the web element, and save the value into a spreadsheet based on column name & row number 
	 * @param to represent the web element
	 * @param dataFileName name of the Data File which bound with the spreadsheet
	 * @param sheetName sheet name of the data the spreadsheet
	 * @param columnName column name of the data in the spreadsheet
	 * @param rowNumber row number of the data the spreadsheet
	 */
	@Keyword
	def void GetTextAndSave(TestObject to, String dataFileName, String sheetName, String columnName, String rowNumber) {

		String text = WebUI.getText(to)
		TestData testData = TestDataFactory.findTestData(dataFileName)
		String filePath = testData.getSourceUrl()

		int rowNo
		if(rowNumber==''||rowNumber == null)
			rowNo = GlobalVariable.Env_Iteration
		else{
			rowNo = Integer.valueOf(rowNumber)
			if(rowNo<=0) rowNo = GlobalVariable.Env_Iteration
		}
		Excel.WirteToExcel(filePath, sheetName, rowNo, columnName, text)

	}

	@Keyword
	def static SaveTextToExcel(String text, String dataFileName, String sheetName, String columnName, String rowNumber) {

		TestData testData = TestDataFactory.findTestData(dataFileName)
		String filePath = testData.getSourceUrl()

		int rowNo
		if(rowNumber==''||rowNumber == null)
			rowNo = GlobalVariable.Env_Iteration
		else{
			rowNo = Integer.valueOf(rowNumber)
			if(rowNo<=0) rowNo = GlobalVariable.Env_Iteration
		}
		Excel.WirteToExcel(filePath, sheetName, rowNo, columnName, text)

	}
	@Keyword
	def static SaveTextToCSV(String text, String dataFileName, String sheetName, String columnName, String rowNumber) {

		TestData testData = TestDataFactory.findTestData(dataFileName)
		String filePath = testData.getSourceUrl()

		int rowNo
		if(rowNumber==''||rowNumber == null)
			rowNo = GlobalVariable.Env_Iteration
		else{
			rowNo = Integer.valueOf(rowNumber)
			if(rowNo<=0) rowNo = GlobalVariable.Env_Iteration
		}
		CSVReader.WirteToCSV(filePath, sheetName, rowNo, columnName, text)

	}

	@Keyword
	def String GetClaimAndSave(TestObject to, String dataFileName, String sheetName, String columnName, String rowNumber) {
		def claimlink= WebUI.getText(to)

		String[] cut1=claimlink.split(',')
		String cutmiddle = cut1[0]
		String[] cut2 = cutmiddle.split(' ')
		String Claimnumber = cut2[1]

		TestData testData = TestDataFactory.findTestData(dataFileName)
		String filePath = testData.getSourceUrl()

		int rowNo
		if(rowNumber==''||rowNumber == null)
			rowNo = GlobalVariable.Env_Iteration
		else{
			rowNo = Integer.valueOf(rowNumber)
			if(rowNo<=0) rowNo = GlobalVariable.Env_Iteration
		}
		Excel.WirteToExcel(filePath, sheetName, rowNo, columnName, Claimnumber)

		return Claimnumber

		/*
		 String[] allCloumnNames = testData.getColumnNames()
		 int columnNumber = 0
		 for(temp in allCloumnNames)
		 {
		 if(temp == cloumnName) break
		 columnNumber ++
		 }
		 Excel.WirteToExcel(filePath, sheetName, GlobalVariable.Env_Iteration, columnNumber, text)*/
	}




	@Keyword
	def void SetSelection(TestObject to, String text ) {
		if (text == null || text == "")
			return

		if(text.contains(',')){
			String[] inputs = text.split(',')
			TestData testData = TestDataFactory.findTestData(inputs[0])
			String filePath = testData.getSourceUrl()
			text = Excel.ReadFromExcel(filePath, inputs[1], Integer.valueOf(inputs[3]), inputs[2])
		}

		try {
			WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)
			WebUI.selectOptionByLabel(to, text,false)
		} catch (Exception e) {
			//WebUI.sendKeys(to, Keys.chord(Keys.F5))
			//Thread.sleep(GlobalVariable.gSleepTime)
			Thread.sleep(GlobalVariable.gSleepTime)
			WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)
			WebUI.selectOptionByLabel(to, text,false)
			e.printStackTrace()
		}
	}
	//upload file from Local
	@Keyword
	def uploadFile (TestObject to, String filePath) {
		WebUI.click(to)
		Thread.sleep(GlobalVariable.gSleepTime)
		StringSelection ss = new StringSelection(filePath)
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null)
		Robot robot = new Robot()
		robot.keyPress(KeyEvent.VK_ENTER)
		robot.delay(25)
		robot.keyRelease(KeyEvent.VK_ENTER)
		robot.keyPress(KeyEvent.VK_CONTROL)
		robot.keyPress(KeyEvent.VK_V)
		robot.delay(50)
		robot.keyRelease(KeyEvent.VK_V)
		robot.keyRelease(KeyEvent.VK_CONTROL)
		robot.keyPress(KeyEvent.VK_ENTER)
		robot.delay(25)
		robot.keyRelease(KeyEvent.VK_ENTER)
	}
}





