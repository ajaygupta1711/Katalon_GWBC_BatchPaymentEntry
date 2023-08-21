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

// Common Scripts for all payment types 
WebUI.selectOptionByLabel(findTestObject('pge_BatchPaymentEntry/screen_MultiplePaymentWizard/Source'), Source, false, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.setText(findTestObject('pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_BatchAmount'), BatchAmount, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.sendKeys(findTestObject('pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_BatchAmount'), Keys.chord(Keys.TAB))

WebUI.selectOptionByLabel(findTestObject('pge_BatchPaymentEntry/screen_MultiplePaymentWizard/lst_Type'), Type, false, FailureHandling.CONTINUE_ON_FAILURE)

// Payment on agency level

WebUI.verifyElementVisible(findTestObject('pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_AgencyCode'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.setText(findTestObject('pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_AgencyCode'), AgencyCode)


// Common steps for all payment types
WebUI.clearText(findTestObject('pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_Amount'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.setText(findTestObject('pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_Amount'), Amount, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.sendKeys(findTestObject('pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_Amount'), Keys.chord(Keys.TAB))

WebUI.verifyElementVisible(findTestObject('pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_CheckRef'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.setText(findTestObject('pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_CheckRef'), CheckRef, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.setText(findTestObject('pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_Description'), Description, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.selectOptionByLabel(findTestObject('pge_BatchPaymentEntry/screen_MultiplePaymentWizard/lst_PaymentInstrument'), PaymentInstrument, 
    false, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('pge_BatchPaymentEntry/screen_MultiplePaymentWizard/btn_Next'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('pge_BatchPaymentEntry/screen_MultiplePaymentWizard/btn_Post'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.callTestCase(findTestCase('BC_BatchPaymentEntry/BC_NavigateBatchPaymentEntryScreen'), [:])

for (def index : (0..0)) {
}

