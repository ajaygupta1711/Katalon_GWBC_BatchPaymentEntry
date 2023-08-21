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

// Split Payment 
WebUI.selectOptionByLabel(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/Source'), Source, 
    false, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.setText(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_BatchAmount'), BatchAmount, 
    FailureHandling.CONTINUE_ON_FAILURE)

WebUI.sendKeys(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_BatchAmount'), Keys.chord(
        Keys.TAB))

WebUI.verifyElementVisible(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/chk_SuspenseSplit'), 
    FailureHandling.CONTINUE_ON_FAILURE)

WebUI.check(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/chk_SuspenseSplit'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.selectOptionByLabel(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/lst_Type'), Type, 
    false, FailureHandling.CONTINUE_ON_FAILURE)

// Common steps for all payment types
WebUI.clearText(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_Amount'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.setText(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_Amount'), Amount, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.sendKeys(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_Amount'), Keys.chord(
        Keys.TAB))

WebUI.verifyElementVisible(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_CheckRef'), 
    FailureHandling.CONTINUE_ON_FAILURE)

WebUI.setText(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_CheckRef'), CheckRef, 
    FailureHandling.CONTINUE_ON_FAILURE)

WebUI.selectOptionByLabel(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/lst_PaymentInstrument'), 
    PaymentInstrument, false, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/btn_Split'), 
    FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/btn_Split'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/btn_AddSplit'), 
    FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/btn_AddSplit'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/drpdwn_TypeSplit1'), 
    FailureHandling.CONTINUE_ON_FAILURE)

WebUI.selectOptionByLabel(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/drpdwn_TypeSplit1'), 
    SplitType1, false, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_SplitAccount1'))

WebUI.setText(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_SplitAccount1'), SplitAccount1, 
    FailureHandling.CONTINUE_ON_FAILURE)

WebUI.sendKeys(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_SplitAccount1'), Keys.chord(
        Keys.TAB), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_SplitAmount1'), 
    FailureHandling.STOP_ON_FAILURE)

WebUI.clearText(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_SplitAmount1'))

WebUI.sendKeys(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_SplitAmount1'), SplitAmount1)

WebUI.sendKeys(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_SplitAmount1'), Keys.chord(
        Keys.TAB))

WebUI.verifyElementVisible(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_SplitDescription1'), 
    FailureHandling.CONTINUE_ON_FAILURE)

WebUI.setText(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_SplitDescription1'), 
    SplitDescription1, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/btn_AddSplit'), 
    FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/btn_AddSplit'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/drpdwn_TypeSplit2'), 
    FailureHandling.CONTINUE_ON_FAILURE)

WebUI.selectOptionByLabel(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/drpdwn_TypeSplit2'), 
    SplitType2, false, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_SplitAmount2'))

WebUI.clearText(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_SplitAmount2'))

WebUI.setText(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_SplitAmount2'), SplitAmount2, 
    FailureHandling.CONTINUE_ON_FAILURE)

WebUI.sendKeys(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_SplitAmount2'), Keys.chord(
        Keys.TAB))

WebUI.verifyElementVisible(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_SplitDescription2'), 
    FailureHandling.CONTINUE_ON_FAILURE)

WebUI.setText(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/txt_SplitDescription2'), 
    SplitDescription2, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/btn_SplitOK'), 
    FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/btn_SplitOK'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/btn_Next'), 
    7, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/btn_Next'), FailureHandling.CONTINUE_ON_FAILURE)

String BatchNumber = WebUI.getText(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/val_BatchNumber'), 
    FailureHandling.CONTINUE_ON_FAILURE)

WebUI.comment('Posted Payment Batch Number ' + BatchNumber)

WebUI.click(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/btn_Post'), FailureHandling.CONTINUE_ON_FAILURE)

// Run the batch job
WebUI.verifyElementPresent(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_BatchJobs/fld_GoTo_Quickjump'), 0, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.sendKeys(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_BatchJobs/fld_GoTo_Quickjump'), 'runBatchProcess PostBatchPayment_sp', FailureHandling.CONTINUE_ON_FAILURE)

WebUI.sendKeys(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_BatchJobs/fld_GoTo_Quickjump'), Keys.chord(Keys.ENTER), FailureHandling.CONTINUE_ON_FAILURE)

// Navigate to the Suspense Payments screen

WebUI.click(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_Verify_PostedPayments/lbl_DesktopOption'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/option_SuspensepPayment'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_MultiplePaymentWizard/option_SuspensepPayment'), FailureHandling.CONTINUE_ON_FAILURE)

// Verify the posted payment on suspense level

WebDriver driver = DriverFactory.getWebDriver()

WebElement Table = driver.findElement(By.xpath("//div[@id='DesktopSuspensePayments-DesktopSuspensePaymentsScreen-DesktopSuspensePaymentsLV']//tbody"))

List<String> elementsinColumn = CustomKeywords.'gw.Table.getTableElementsByColumn'(Table, 9)

WebUI.comment('Elements after function call to getTableElementsByColumn ' + elementsinColumn)

if (elementsinColumn.contains(BatchNumber)) {
    KeywordUtil.markPassed('Suspense Payment Batch Number Found')
} else {
    KeywordUtil.markFailed('Suspense Payment Batch Number Not Found')
}

// Navigation to the account payments screen

WebUI.click(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_Verify_PostedPayments/option_AccountSearch'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.sendKeys(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_Verify_PostedPayments/fld_SearchAccount'), SplitAccount1, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_Verify_PostedPayments/icon_SearchAccount'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_Verify_PostedPayments/icon_PaymentsDownArrow'), FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_Verify_PostedPayments/option_PaymentsAccount'), FailureHandling.CONTINUE_ON_FAILURE)

// Verify the posted payment on account level

WebElement Table1 = driver.findElement(By.xpath('//*[@id=\'AccountPayments-AccountDetailPaymentsScreen-DirectBillPaymentsListDetail-AccountDBPaymentsLV\']//tbody'))

List<String> elementsinColumn1 = CustomKeywords.'gw.Table.getTableElementsByColumn'(Table1, 8)

WebUI.comment('Elements after function call to getTableElementsByColumn ' + elementsinColumn1)

if (elementsinColumn1.contains(BatchNumber)) {
    KeywordUtil.markPassed('Account Payment Batch Number Found')
} else {
    KeywordUtil.markFailed('Account Payment Batch Number NOT Found')
}

WebUI.click(findTestObject('BillingCenter/pge_BatchPaymentEntry/screen_Verify_PostedPayments/lbl_DesktopOption'))
 
WebUI.callTestCase(findTestCase('BillingCenter/BatchPaymentEntry/BC_NavigateBatchPaymentEntryScreen'), [:])

for (def index : (0..0)) {
}