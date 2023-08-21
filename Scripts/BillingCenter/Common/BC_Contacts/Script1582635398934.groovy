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

WebUI.callTestCase(findTestCase('BillingCenter/Common/BC_Login'), [('UserName') : 'svisor', ('Password') : 'gw'], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)

WebUI.callTestCase(findTestCase('Common/GW_OpenAccount'), [('AccountNumber') : AccountNumber], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)

Thread.sleep(GlobalVariable.gSleepTime)

WebUI.click(findTestObject('Object Repository/BillingCenter/pge_AccountContacts/btn_ContactsSideMenu'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/BillingCenter/pge_AccountContacts/btn_Edit'))

WebUI.waitForElementPresent(findTestObject('Object Repository/Common/pge_Common/gw-click-overlay'), 50, FailureHandling.OPTIONAL)

WebUI.click(findTestObject('Object Repository/BillingCenter/pge_AccountContacts/btn_Add'))

WebUI.waitForElementPresent(findTestObject('Object Repository/Common/pge_Common/gw-click-overlay'), 50, FailureHandling.OPTIONAL)

WebUI.click(findTestObject('Object Repository/BillingCenter/pge_AccountContacts/btn_AddNewCompany'), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Object Repository/Common/pge_Common/gw-click-overlay'), 50, FailureHandling.OPTIONAL)
