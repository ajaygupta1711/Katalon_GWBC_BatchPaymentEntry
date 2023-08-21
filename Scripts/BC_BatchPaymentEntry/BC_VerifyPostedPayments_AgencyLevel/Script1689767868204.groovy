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

WebUI.click(findTestObject('pge_BatchPaymentEntry/screen_Verify_PostedPayments/option_ProducerSearch'))

WebUI.sendKeys(findTestObject('pge_BatchPaymentEntry/screen_Verify_PostedPayments/fld_SearchProducer'), AgencyCode)

WebUI.click(findTestObject('pge_BatchPaymentEntry/screen_Verify_PostedPayments/icon_SearchProducer'))

WebUI.click(findTestObject('pge_BatchPaymentEntry/screen_Verify_PostedPayments/icon_AgencyBillPaymentsArrow'))

WebUI.click(findTestObject('pge_BatchPaymentEntry/screen_Verify_PostedPayments/option_PaymentsProducer'))

WebUI.click(findTestObject('pge_BatchPaymentEntry/screen_Verify_PostedPayments/lbl_DesktopOption'))

