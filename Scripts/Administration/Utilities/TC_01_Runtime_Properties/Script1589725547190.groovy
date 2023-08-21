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

CustomKeywords.'gw.Actions.Click'(findTestObject('Object Repository/Administration/btn_Menu_Administration'))

WebUI.waitForElementPresent(findTestObject('Object Repository/Common/pge_Common/gw-click-overlay'), GlobalVariable.gWaitTimeOut, FailureHandling.OPTIONAL)

WebUI.mouseOver(findTestObject('Object Repository/Administration/btn_Administration'))

Thread.sleep(2)

//CustomKeywords.'gw.Actions.Click'(findTestObject('Object Repository/Administration/btn_Administration'))

//Thread.sleep(2)

WebUI.waitForElementPresent(findTestObject('Object Repository/Common/pge_Common/gw-click-overlay'), GlobalVariable.gWaitTimeOut, FailureHandling.OPTIONAL)

WebUI.waitForElementVisible(findTestObject('Object Repository/Administration/btn_Utilities'),  GlobalVariable.gWaitTimeOut)

WebUI.mouseOver(findTestObject('Object Repository/Administration/btn_Utilities'))

//CustomKeywords.'gw.Actions.Click'(findTestObject('Object Repository/Administration/btn_Utilities'))

WebUI.waitForElementPresent(findTestObject('Object Repository/Common/pge_Common/gw-click-overlay'), GlobalVariable.gWaitTimeOut, FailureHandling.OPTIONAL)

WebUI.mouseOver(findTestObject('Object Repository/Administration/btn_RunTime_Properties'))

CustomKeywords.'gw.Actions.Click'(findTestObject('Object Repository/Administration/btn_RunTime_Properties'))

WebUI.waitForElementPresent(findTestObject('Object Repository/Common/pge_Common/gw-click-overlay'), GlobalVariable.gWaitTimeOut, FailureHandling.OPTIONAL)

CustomKeywords.'gw.Actions.SelectOption'(findTestObject('Object Repository/Administration/lst_Property_Group'),
	Property_Group)

WebUI.waitForElementPresent(findTestObject('Object Repository/Common/pge_Common/gw-click-overlay'), GlobalVariable.gWaitTimeOut, FailureHandling.OPTIONAL)

CustomKeywords.'gw.Actions.Click'(findTestObject('Object Repository/Administration/lnk_Name',[('Name_Link'): Name]))

WebUI.waitForElementPresent(findTestObject('Object Repository/Common/pge_Common/gw-click-overlay'), GlobalVariable.gWaitTimeOut, FailureHandling.OPTIONAL)

CustomKeywords.'gw.Actions.Click'(findTestObject('Object Repository/ClaimCenter/pge_Admin/pge_Users/btn_Edit'))

WebUI.waitForElementPresent(findTestObject('Object Repository/Common/pge_Common/gw-click-overlay'), GlobalVariable.gWaitTimeOut, FailureHandling.OPTIONAL)

CustomKeywords.'gw.Text.SetText'(findTestObject('Object Repository/Administration/txt_Value'),
	Value)

WebUI.waitForElementPresent(findTestObject('Object Repository/Common/pge_Common/gw-click-overlay'), GlobalVariable.gWaitTimeOut, FailureHandling.OPTIONAL)

CustomKeywords.'gw.Actions.Click'(findTestObject('Object Repository/Common/pge_Common/btn_Update'))

WebUI.waitForElementPresent(findTestObject('Object Repository/Common/pge_Common/gw-click-overlay'), GlobalVariable.gWaitTimeOut, FailureHandling.OPTIONAL)


CustomKeywords.'gw.Actions.Click'(findTestObject('Object Repository/ClaimCenter/pge_NewExposure/btn_UpToExposure'))

WebUI.waitForElementPresent(findTestObject('Object Repository/Common/pge_Common/gw-click-overlay'), GlobalVariable.gWaitTimeOut, FailureHandling.OPTIONAL)




