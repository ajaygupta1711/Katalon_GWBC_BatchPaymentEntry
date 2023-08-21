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
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

// Common Scripts for all payment types 
WebUI.selectOptionByLabel(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/Source'), Source, 
    false, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.setText(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_BatchAmount'), BatchAmount, 
    FailureHandling.CONTINUE_ON_FAILURE)

WebUI.sendKeys(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_BatchAmount'), Keys.chord(
        Keys.TAB))

WebUI.selectOptionByLabel(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/lst_Type'), Type, 
    false, FailureHandling.CONTINUE_ON_FAILURE)

// Payment on agency level
WebUI.verifyElementVisible(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_ABStatement'), 
    FailureHandling.CONTINUE_ON_FAILURE)

WebUI.setText(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_ABStatement'), ABStatement)

// Common steps for all payment types
WebUI.clearText(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_Amount'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.setText(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_Amount'), Amount, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.sendKeys(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_Amount'), Keys.chord(
        Keys.TAB))

WebUI.verifyElementVisible(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_CheckRef'), 
    FailureHandling.CONTINUE_ON_FAILURE)

WebUI.setText(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_CheckRef'), CheckRef, 
    FailureHandling.CONTINUE_ON_FAILURE)

WebUI.setText(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_Description'), Description, 
    FailureHandling.CONTINUE_ON_FAILURE)

WebUI.selectOptionByLabel(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/lst_PaymentInstrument'), 
    PaymentInstrument, false, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/btn_Next'), FailureHandling.CONTINUE_ON_FAILURE)

String BatchNumber = WebUI.getText(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/val_BatchNumber'), 
    FailureHandling.CONTINUE_ON_FAILURE)

WebUI.comment('Posted Payment Batch Number ' + BatchNumber)

WebUI.click(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/btn_Post'), FailureHandling.CONTINUE_ON_FAILURE)

// Run the batch job
WebUI.verifyElementPresent(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_BatchJobs/fld_GoTo_Quickjump'), 0)

WebUI.sendKeys(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_BatchJobs/fld_GoTo_Quickjump'), 'runBatchProcess PostBatchPayment_sp')

WebUI.sendKeys(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_BatchJobs/fld_GoTo_Quickjump'), Keys.chord(Keys.ENTER))

// Verify the posted payment
WebUI.click(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_Verify_PostedPayments/option_ProducerSearch'))

WebUI.sendKeys(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_Verify_PostedPayments/fld_SearchProducer'), AgencyCode)

WebUI.click(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_Verify_PostedPayments/icon_SearchProducer'))

WebUI.click(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_Verify_PostedPayments/icon_AgencyBillPaymentsArrow'))

WebUI.click(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_Verify_PostedPayments/option_PaymentsProducer'))

WebDriver driver = DriverFactory.getWebDriver()

WebElement Table = driver.findElement(By.xpath('//*[@id=\'AgencyBillExecutedPayments-PaymentsLV\']//tbody'))

List<String> elementsinColumn = CustomKeywords.'gw.Table.getTableElementsByColumn'(Table, 9)

WebUI.comment('Elements after function call to getTableElementsByColumn ' + elementsinColumn)

if (elementsinColumn.contains(BatchNumber)) {
    KeywordUtil.markPassed('Agency Statement Payment Batch Number Found')
} else {
    KeywordUtil.markFailed('Agency Statement Payment Batch Number NOT Found')
}

WebUI.click(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_Verify_PostedPayments/lbl_DesktopOption'))

WebUI.callTestCase(findTestCase('BillingCenter/BatchPaymentEntry/BC_NavigateBatchPaymentEntryScreen'), [:])

for (def index : (0..0)) {
}

