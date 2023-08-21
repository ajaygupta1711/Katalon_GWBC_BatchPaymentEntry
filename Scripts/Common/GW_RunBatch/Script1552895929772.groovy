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
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import org.openqa.selenium.Keys as Keys

//KeywordLogger log = new KeywordLogger()
//log.logInfo("")
//common.SendKeys.SendShiftAltT()

WebUI.sendKeys(findTestObject('Common/pge_Common/txt_QuickJump'), Keys.chord(Keys.SHIFT, Keys.ALT, 'T'))

WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)

Thread.sleep(GlobalVariable.gSleepTime)

WebUI.verifyElementPresent(findTestObject('Common/pge_BatchProcessInfo/wel_BatchProcessInfo'), GlobalVariable.gWaitTimeOut, 
    FailureHandling.STOP_ON_FAILURE)

id = WebUI.getAttribute(findTestObject('Common/pge_BatchProcessInfo/wel_Batch', [('BatchName') : BatchName]), 'id').toString()

String[] txtId = id.split('LV-')

String rightId = txtId[1]

String[] txtIndex = rightId.split('-')

rowNumber = Integer.valueOf(txtIndex[0])

CustomKeywords.'gw.Actions.Click'(findTestObject('Common/pge_BatchProcessInfo/btn_Run', [('Row') : rowNumber]))

WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)

Thread.sleep(GlobalVariable.gSleepTime)

WebUI.verifyElementPresent(findTestObject('Common/pge_BatchProcessInfo/wel_Batch', [('BatchName') : BatchName]), GlobalVariable.gWaitTimeOut, 
    FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'gw.Actions.Click'(findTestObject('Common/pge_Common/btn_TabMenu'))

CustomKeywords.'gw.Actions.Click'(findTestObject('Common/pge_BatchProcessInfo/wel_ReturnToGW'))

//Below is used for RunBatchProcess enabled
//CustomKeywords.'gw.Text.SetTextAndEnter'(findTestObject('Common/pge_Common/txt_QuickJump'),'RunBatchProcess '+BatchName)