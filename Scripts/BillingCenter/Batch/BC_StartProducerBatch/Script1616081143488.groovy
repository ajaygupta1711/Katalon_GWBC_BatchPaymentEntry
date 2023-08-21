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

WebUI.click(findTestObject('BillingCenter/Batch/pge_BatchPage/div_Desktop'))

WebUI.delay(5)

WebUI.sendKeys(findTestObject(null), Keys.chord(Keys.ALT,Keys.SHIFT, 'T'))

WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)

WebUI.waitForElementPresent(findTestObject('Object Repository/BillingCenter/Batch/pge_BatchPage/div_Batch Process Info'), 50, FailureHandling.OPTIONAL)

WebUI.click(findTestObject('BillingCenter/Batch/pge_BatchPage/div_RunProducerBatch'))

WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)

WebUI.delay(1)

WebUI.click(findTestObject('Object Repository/BillingCenter/Batch/pge_BatchPage/span_Accounting Config_gw-icon'))

WebUI.click(findTestObject('Object Repository/BillingCenter/Batch/pge_BatchPage/div_Return to BillingCenter'))
