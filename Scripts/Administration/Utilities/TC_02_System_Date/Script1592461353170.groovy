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
import com.kms.katalon.core.configuration.RunConfiguration

/*WebUI.comment(RunConfiguration.getExecutionProfile())

println(RunConfiguration.getExecutionProfile())

Profile_SourceName = RunConfiguration.getExecutionSourceName()

WebUI.comment(Profile_SourceName)


//def Profile

Profile = RunConfiguration.getExecutionProfile()

WebUI.comment(Profile)


if(Profile.equals('Cloud_Platform_QA2') || Profile.equals('Cloud_Platform_SIT1') || Profile.equals('Cloud_Platform_PERF') || Profile.equals('Cloud_Platform_SIT2') || Profile.equals('Cloud_Platform_QA1') || Profile.equals('Cloud_Platform_UAT') || Profile.equals('Cloud_Platform_Training'))

{*/

	WebUI.sendKeys(findTestObject('Common/pge_Common/txt_QuickJump'), Keys.chord(Keys.SHIFT, Keys.ALT, 'T'))
	
	WebUI.waitForElementPresent(findTestObject('Object Repository/Common/pge_Common/gw-click-overlay'), GlobalVariable.gWaitTimeOut, FailureHandling.OPTIONAL)
	WebUI.click(findTestObject('Object Repository/Administration/btn_Internal_Tools'))
	WebUI.waitForElementPresent(findTestObject('Object Repository/Common/pge_Common/gw-click-overlay'), GlobalVariable.gWaitTimeOut, FailureHandling.OPTIONAL)
	
	WebUI.click(findTestObject('Object Repository/Administration/tab_Testing_System_Clock'))
	WebUI.waitForElementPresent(findTestObject('Object Repository/Common/pge_Common/gw-click-overlay'), GlobalVariable.gWaitTimeOut, FailureHandling.OPTIONAL)
	
	GlobalVariable.SystemDate = WebUI.getAttribute(findTestObject('Object Repository/Administration/txt_Date'),'value')
	WebUI.comment(GlobalVariable.SystemDate)
	
	WebUI.click(findTestObject('Object Repository/Administration/tab_Actions'))
	
	WebUI.waitForElementPresent(findTestObject('Object Repository/Administration/btn_Return_Policy_Center'), GlobalVariable.gWaitTimeOut, FailureHandling.OPTIONAL)
	WebUI.waitForElementPresent(findTestObject('Object Repository/Common/pge_Common/gw-click-overlay'), GlobalVariable.gWaitTimeOut, FailureHandling.OPTIONAL)
	WebUI.waitForElementVisible(findTestObject('Object Repository/Administration/btn_Return_Policy_Center'), GlobalVariable.gWaitTimeOut, FailureHandling.OPTIONAL)
	
	WebUI.click(findTestObject('Object Repository/Administration/btn_Return_Policy_Center'))
	WebUI.waitForElementPresent(findTestObject('Object Repository/Common/pge_Common/gw-click-overlay'), GlobalVariable.gWaitTimeOut, FailureHandling.OPTIONAL)
	
//}