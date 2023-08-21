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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

File out = new File('./Reports/outputPerf.log')
KeywordUtil.metaClass.static.saveInfo = { String message ->
	out.append(message)
	message = ''
}

String lineSeparator = System.getProperty('line.separator')
KeywordUtil.saveInfo(lineSeparator)
KeywordUtil.saveInfo('BC_AddnewNote: ')

WebUI.click(findTestObject('BillingCenter/pge_AddNewNote/div_P_gw-icon gw-icon--expand'))

WebUI.setText(findTestObject('BillingCenter/pge_AddNewNote/input_PolicyNumber'), PolicyNumber)

WebUI.click(findTestObject('BillingCenter/pge_AddNewNote/span_Policy _gw-icon'))

WebUI.click(findTestObject('BillingCenter/pge_AddNewNote/span_NotesAddIcon'))

WebUI.setText(findTestObject('BillingCenter/pge_AddNewNote/input_Subject'), Subject)

WebUI.setText(findTestObject('BillingCenter/pge_AddNewNote/textarea_TextBody'), TextBody)

WebUI.selectOptionByValue(findTestObject('BillingCenter/pge_AddNewNote/select_Account Only'), 'account', true)

WebUI.click(findTestObject('BillingCenter/pge_AddNewNote/div_Update'))

WebUI.delay(5)

WebUI.click(findTestObject('BillingCenter/pge_AddNewNote/div_Policy Notes'))

WebUI.click(findTestObject('BillingCenter/pge_AddNewNote/div_Search'))

Boolean successfulFind = WebUI.verifyElementPresent(findTestObject('BillingCenter/pge_AddNewNote/div_test'),50, FailureHandling.OPTIONAL)

if(successfulFind == true){
	KeywordUtil.saveInfo("Note Added successfully")
} else {
	KeywordUtil.saveInfo("Note not added successfully")
}