package gw

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.WebElement
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import internal.GlobalVariable
import org.openqa.selenium.By as By

public class Assertion {

	/**
	 * Verify if given web element is visible.
	 * @param to - represent a web element
	 * @param timeout - system will wait at most timeout (seconds) to return result
	 * @return true if the element is present and visible; otherwise, false
	 */
	@Keyword
	def VerifyVisible(TestObject to, int timeout) {

		//		while (!(WebUI.verifyElementVisible(to, FailureHandling.OPTIONAL))) {
		//			WebUI.delay(1)
		//		}
		for (int i = 0; i<timeout; i++) {
			if (!(WebUI.verifyElementVisible(to, FailureHandling.OPTIONAL)))
				WebUI.delay(1)
			else return true
		}

		return false
	}

	@Keyword
	def VerifyLabelValue(String key, String value) {
		VerifyText(findTestObject('Common/pge_Common/wel_LabelValue',[('LabelKey') : key]),value)
	}

	@Keyword
	def VerifyLabelValue(String sectionName, String key, String value) {
		VerifyText(findTestObject('Common/pge_Common/wel_Section_LabelValue',[('SectionName') : sectionName, ('LabelKey') : key]),value)
	}

	@Keyword
	def GetTableRowSpecificContent(TestObject TableObject,String RefColHeader,String RefValue){
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement Table = WebUiCommonHelper.findWebElement(TableObject, 30)

		//driver.findElement(By.xpath(obj_xpath))
		List<WebElement> Rows = Table.findElements(By.tagName('tr'))
		def FoundRowIndex = -1
		def RefColIndex = -1
		table: for (int i = 0; i < Rows.size(); i++) {
			List<WebElement> Cols = Rows.get(i).findElements(By.tagName('td'))

			if(i==0){
				TempCol:for (int k = 0; k < Cols.size(); k++) {
					Cols.get(k).getText()
					if (Cols.get(k).getText().equalsIgnoreCase(RefColHeader)) {
						RefColIndex = k
						TempCol: break
					}
				}
			}
			else {
				if (Cols.get(RefColIndex).getText().matches(RefValue)) {
					FoundRowIndex = i
					table: break
				}
			}

		}

		return FoundRowIndex


	}

	@Keyword
	def VerifyTableContentsWithPage(TestObject TableObject,TestObject NextPage,TestObject FirstPage,String ColName,String CSV_Data){
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement Table = WebUiCommonHelper.findWebElement(TableObject, 30)

		//driver.findElement(By.xpath(obj_xpath))
		List<WebElement> Rows = Table.findElements(By.tagName('tr'))
		println('No. of rows: ' + Rows.size())
		def FoundRowIndex = -1
		def RefColIndex = -1
		def ActionColumnIndex = -1
		def lblnClicked = false
		table: for (int i = 0; i < Rows.size(); i++) {
			List<WebElement> Cols = Rows.get(i).findElements(By.tagName('td'))

			if(i==0){
				TempCol:for (int k = 0; k < Cols.size(); k++) {
					Cols.get(k).getText()
					if (Cols.get(k).getText().equalsIgnoreCase(ColName)) {
						RefColIndex = k
						TempCol: break
					}
				}
			}
			table: break
		}
		def lblnfound
		def lblnNextPageAvailable
		String[] items = CSV_Data.split(',')
		for (item in items) {
			lblnfound =false
			lblnNextPageAvailable = true
			//while(lblnfound ==false ){

			while(lblnfound ==false && lblnNextPageAvailable == true){
				tableContent: for (int i = 1; i < Rows.size(); i++) {
					List<WebElement> Cols = Rows.get(i).findElements(By.tagName('td'))
					if (Cols.get(RefColIndex).getText().equalsIgnoreCase(item.toString())) {
						lblnfound = true
						tableContent: break
					}
					if(i == Rows.size()-1) {
						try{
							if(WebUI.verifyElementPresent(NextPage, 1, FailureHandling.OPTIONAL)){
								if(WebUI.verifyElementClickable(NextPage, FailureHandling.OPTIONAL) ) {

									WebUI.click(NextPage)
									Thread.sleep(GlobalVariable.gSleepTime)
									Table = WebUiCommonHelper.findWebElement(TableObject, 30)
									Rows = Table.findElements(By.tagName('tr'))
								}
								else {
									lblnNextPageAvailable = false
								}
							}

							else {
								lblnNextPageAvailable = false
							}

						}

						catch (Exception e) {
							println('No Pagination')
						}

					}

				}
			}

			if(item!=''){
				if(lblnfound == true) {
					println(item + " is found")
					WebUI.verifyEqual(item, item, FailureHandling.CONTINUE_ON_FAILURE)
				}
				else{
					WebUI.verifyEqual("Not Found", item, FailureHandling.CONTINUE_ON_FAILURE)
					//to exit While loop
					//lblnfound = true
				}
			}

			try{
				if( WebUI.verifyElementPresent(FirstPage, 1, FailureHandling.OPTIONAL)){
					if(WebUI.verifyElementClickable(FirstPage, FailureHandling.OPTIONAL) )  {
						WebUI.click(FirstPage)
						Thread.sleep(GlobalVariable.gSleepTime)
						Table = WebUiCommonHelper.findWebElement(TableObject, 30)
						Rows = Table.findElements(By.tagName('tr'))
					}
				}
			}
			catch (Exception e) {
				println('No Pagination')
			}
			//}


		}

	}

	@Keyword
	def VerifyTableContents(TestObject TableObject,String ColName,String CSV_Data){
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement Table = WebUiCommonHelper.findWebElement(TableObject, 30)

		//driver.findElement(By.xpath(obj_xpath))
		List<WebElement> Rows = Table.findElements(By.tagName('tr'))
		println('No. of rows: ' + Rows.size())
		def FoundRowIndex = -1
		def RefColIndex = -1
		def ActionColumnIndex = -1
		def lblnClicked = false
		table: for (int i = 0; i < Rows.size(); i++) {
			List<WebElement> Cols = Rows.get(i).findElements(By.tagName('td'))

			if(i==0){
				TempCol:for (int k = 0; k < Cols.size(); k++) {
					Cols.get(k).getText()
					if (Cols.get(k).getText().equalsIgnoreCase(ColName)) {
						RefColIndex = k
						TempCol: break
					}
				}
			}
			table: break
		}
		def lblnfound
		String[] items = CSV_Data.split(',')
		for (item in items) {
			lblnfound =false
			tableContent: for (int i = 1; i < Rows.size(); i++) {
				List<WebElement> Cols = Rows.get(i).findElements(By.tagName('td'))
				if (Cols.get(RefColIndex).getText().equalsIgnoreCase(item.toString())) {
					lblnfound = true
					tableContent: break
				}
			}


			if(item!=''){
				if(lblnfound == true) {
					println(item + " is found")

					WebUI.verifyEqual(item, item, FailureHandling.CONTINUE_ON_FAILURE)
				}
				else{
					WebUI.verifyEqual("Not Found", item, FailureHandling.CONTINUE_ON_FAILURE)
				}
			}



		}




	}

	/**
	 * Verify text of an element.
	 * @param to - represent a web element
	 * @param text - text of the element to verify.
	 * @return true if the element is present and visible; otherwise, false
	 */
	@Keyword
	def VerifyText(TestObject to, String text ) {
		def flag
		try {
			WebUI.verifyElementPresent(to, GlobalVariable.gWaitTimeOut, FailureHandling.STOP_ON_FAILURE)
			WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)
			flag = WebUI.verifyElementText(to, text)
		} catch (Exception e) {
			//WebUI.sendKeys(to, Keys.chord(Keys.F5))
			Thread.sleep(GlobalVariable.gSleepTime)
			Thread.sleep(GlobalVariable.gSleepTime)
			WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)
			flag = WebUI.verifyElementText(to, text)
			e.printStackTrace()
		}
		return flag
	}


	@Keyword
	def VerifyPartialText(TestObject to, String textregExp ) {
		def flag
		def lstrElementText
		try {
			WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)
			lstrElementText = WebUI.getText(to)

			flag = WebUI.verifyMatch(lstrElementText, textregExp, true, FailureHandling.STOP_ON_FAILURE)

		} catch (Exception e) {
			//WebUI.sendKeys(to, Keys.chord(Keys.F5))
			Thread.sleep(GlobalVariable.gSleepTime)
			Thread.sleep(GlobalVariable.gSleepTime)
			WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)
			lstrElementText = WebUI.getText(to)
			flag = WebUI.verifyMatch(lstrElementText, textregExp, true, FailureHandling.STOP_ON_FAILURE)
			//flag = WebUI.verifyElementText(to, text)
			e.printStackTrace()
		}
		return flag
	}
}
