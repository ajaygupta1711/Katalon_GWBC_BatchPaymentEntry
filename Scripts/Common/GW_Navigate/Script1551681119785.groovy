import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.navigateToUrl(GW_URL)

if(WebUI.verifyElementPresent(findTestObject('Common/pge_LoginScreen/txt_UserName'), 0, FailureHandling.OPTIONAL)){
	CustomKeywords.'gw.Text.SetText'(findTestObject('Common/pge_LoginScreen/txt_UserName'), UserName)
	
	CustomKeywords.'gw.Text.SetText'(findTestObject('Common/pge_LoginScreen/txt_Password'), Password)
	
	CustomKeywords.'gw.Actions.Click'(findTestObject('Common/pge_LoginScreen/btn_LogIn'))
}



