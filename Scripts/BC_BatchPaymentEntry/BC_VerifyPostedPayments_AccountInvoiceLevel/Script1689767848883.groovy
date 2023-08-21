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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

WebUI.click(findTestObject('pge_BatchPaymentEntry/screen_Verify_PostedPayments/option_AccountSearch'))

WebUI.sendKeys(findTestObject('pge_BatchPaymentEntry/screen_Verify_PostedPayments/fld_SearchAccount'), Account)

WebUI.click(findTestObject('pge_BatchPaymentEntry/screen_Verify_PostedPayments/icon_SearchAccount'))

WebUI.click(findTestObject('pge_BatchPaymentEntry/screen_Verify_PostedPayments/icon_PaymentsDownArrow'))

WebUI.click(findTestObject('pge_BatchPaymentEntry/screen_Verify_PostedPayments/option_PaymentsAccount'))

WebDriver driver = DriverFactory.getWebDriver()

String batchNumber = driver.findElement(By.xpath("//*[@id='BatchPaymentEntryWizard-NewBatchPaymentScreen-BatchNumber']//div[@class='gw-value-readonly-wrapper']"))

WebElement Table = driver.findElement(By.xpath("//*[@id='AccountPayments-AccountDetailPaymentsScreen-DirectBillPaymentsListDetail-AccountDBPaymentsLV']"))

List<String> paymentsDateColumn = CustomKeywords.'gw.Table.getTableElementsByColumn'(Table,2)

WebUI.comment("Elements after function call to getTableElementsByColumn " + paymentsDateColumn)

if (batchNumber == paymentsDateColumn)
{
	System.console("PASS");
}
else
{
	System.console("FAIL");
}

WebUI.click(findTestObject('pge_BatchPaymentEntry/screen_Verify_PostedPayments/lbl_DesktopOption'))