import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

File out = new File('./Reports/outputPerf.log')
KeywordUtil.metaClass.static.saveInfo = { String message ->
	out.append(message)
	message = ""
}
String lineSeparator = System.getProperty("line.separator");
KeywordUtil.saveInfo(lineSeparator)
KeywordUtil.saveInfo("BC_AddNewSharedActivities: ")

WebUI.click(findTestObject('BillingCenter/Activities/pge_NewSharedActivity/div_Actions'))

WebUI.mouseOver(findTestObject('BillingCenter/Activities/pge_NewSharedActivity/div_NewShared Activity'))

WebUI.mouseOver(findTestObject('BillingCenter/Activities/pge_NewSharedActivity/div_Reminder'))

WebUI.click(findTestObject('BillingCenter/Activities/pge_NewSharedActivity/div_notification'))

WebUI.click(findTestObject('BillingCenter/Activities/pge_NewSharedActivity/div_Update'))

WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)

Boolean activityExists = WebUI.verifyElementPresent(findTestObject('BillingCenter/pge_MyActivities/lbl_Subject'), GlobalVariable.gWaitTimeOut, FailureHandling.OPTIONAL)

String subjectText = WebUI.getText(findTestObject('BillingCenter/pge_MyActivities/lbl_Subject'))

if(activityExists == true) {
	if(subjectText == 'notification'){
		KeywordUtil.saveInfo("Add Activity successful")
	} else {
	KeywordUtil.saveInfo("Add Activity not successful")
	}
} else {
	KeywordUtil.saveInfo("Add Activity not successful")
}
