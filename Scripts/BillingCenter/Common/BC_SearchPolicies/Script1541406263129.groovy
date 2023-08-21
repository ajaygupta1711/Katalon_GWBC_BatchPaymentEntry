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

WebUI.comment('Search producers by Account #, Account Name or Policy #')

//CustomKeywords.'gw.Text.TypeTextAndEnter'(findTestObject('Common/pge_Common/txt_QuickJump'), 'PolicySearch')


CustomKeywords.'gw.Actions.Click'(findTestObject('Object Repository/BillingCenter/pge_Common/menu_DesktopPolicy'))

//WebUI.verifyElementPresent(findTestObject('null'), GlobalVariable.gWaitTimeOut)

//CustomKeywords.'gw.Text.SetText'(findTestObject('BillingCenter/pge_SearchPolicies/txt_AccountNumber'), AccountNumber)

CustomKeywords.'gw.Text.SetText'(findTestObject('BillingCenter/pge_SearchPolicies/txt_PolicyNumber'), PolicyNumber)

//WebUI.selectOptionByLabel(findTestObject('BillingCenter/pge_SearchPolicies/lst_Product'),Product,false)
CustomKeywords.'gw.Actions.Click'(findTestObject('Common/pge_Common/btn_Search'))
//WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)
//WebUI.delay(20)

CustomKeywords.'gw.Actions.Click'(findTestObject('BillingCenter/pge_SearchPolicies/lnk_PolicyNo - Perf'))
//WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)
//WebUI.delay(20)
WebUI.verifyElementPresent(findTestObject('BillingCenter/pge_PolicySummary/wel_PolicySummary'), GlobalVariable.gWaitTimeOut)

