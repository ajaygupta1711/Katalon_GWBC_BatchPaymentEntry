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
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.stringtemplate.v4.compiler.STParser.element_return
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.configuration.RunConfiguration

WebUI.comment(RunConfiguration.getExecutionProfile())

println(RunConfiguration.getExecutionProfile())

Profile_SourceName = RunConfiguration.getExecutionSourceName()

WebUI.comment(Profile_SourceName)




//def Profile

Profile = RunConfiguration.getExecutionProfile()

WebUI.comment(Profile)

if (WebUI.verifyElementClickable(findTestObject('Object Repository/Administration/btn_Administration')))
{
	WebUI.click(findTestObject('Object Repository/Administration/lst_Administration'))
}
else
{
	WebUI.click(findTestObject('Object Repository/Administration/btn_Menu_Administration'))

	WebUI.mouseOver(findTestObject('Object Repository/Administration/btn_Administration'))
}

WebUI.mouseOver(findTestObject('Object Repository/Administration/btn_Monitoring'))

WebUI.click(findTestObject('Object Repository/Administration/btn_Message_Queues'))

WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)

String Status = WebUI.getText(findTestObject('Object Repository/Administration/txt_AccountSync_Status'))

WebUI.comment(Status)

if(Status!='Suspended')
{
	WebUI.click(findTestObject('Object Repository/Administration/lnk_AccountSync'))
	
	WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)
	
	CustomKeywords.'gw.Text.SetText'(findTestObject('Object Repository/Administration/txt_Account'), GlobalVariable.gAccountNumber)
		
	CustomKeywords.'gw.Text.SetText'(findTestObject('Object Repository/Administration/txt_Account'), GlobalVariable.gAccountNumber)
	
	WebUI.click(findTestObject('Object Repository/Administration/btn_Account_Search'))
	
	String Account_Text = 'AccountAdded'
	
	WebDriver driver = DriverFactory.getWebDriver()
	
	WebElement Table = driver.findElement(By.xpath('//*[@id="MessageControlForSOOList-MessageControlForSOOListScreen-3"]/div'))
	
	List<String> elementsinColumn = CustomKeywords.'gw.Table.getTableElementsByColumn'(Table, 2)
	
	WebUI.comment("Elements after function call to getTableElementsByRow " + elementsinColumn)
	
	if(elementsinColumn.contains(Account_Text))
	{
		KeywordUtil.markFailed('Integration is unsuccessfull')
	}
	
	else
	{	
		KeywordUtil.markPassed('Integration is successfull')
	}
}

else
{
	KeywordUtil.markFailed('Status is suspended')
}
	
if(WebUI.verifyElementClickable(findTestObject('Object Repository/Administration/lnk_UpToDestination'), FailureHandling.CONTINUE_ON_FAILURE))
{
	WebUI.click(findTestObject('Object Repository/Administration/lnk_UpToDestination'))
	WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)
}

if(WebUI.verifyElementClickable(findTestObject('Object Repository/Administration/lnk_UpToDestination'), FailureHandling.CONTINUE_ON_FAILURE))	
{
	WebUI.click(findTestObject('Object Repository/Administration/lnk_UpToDestination'))
	

	WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)
}

if(Profile.equals('Cloud_Platform_dev') || Profile.equals('Cloud_Platform_QA1') || Profile.equals('Cloud_Platform_SIT1') || Profile.equals('Cloud_Platform_UAT'))
{
	String ActuarialStatus = WebUI.getText(findTestObject('Object Repository/Administration/txt_ActuarialApp_Status'))
	
	WebUI.comment(ActuarialStatus)
	
	if (ActuarialStatus!='Suspended')
	{
		WebUI.click(findTestObject('Object Repository/Administration/lnk_ActurialApp'))
		
		WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)
		
		CustomKeywords.'gw.Text.SetText'(findTestObject('Object Repository/Administration/txt_Account'), GlobalVariable.gAccountNumber)
			
		CustomKeywords.'gw.Text.SetText'(findTestObject('Object Repository/Administration/txt_Account'), GlobalVariable.gAccountNumber)
		
		WebUI.click(findTestObject('Object Repository/Administration/btn_Account_Search'))
		
		String Actuarial_Text = 'No data to display'
		
		WebDriver driver = DriverFactory.getWebDriver()
		
		WebElement Table = driver.findElement(By.xpath('//*[@id="MessageControlForSOOList-MessageControlForSOOListScreen-3"]/div'))
		
		List<String> elementsinColumn = CustomKeywords.'gw.Table.getTableElementsByColumn'(Table, 2)
		
		WebUI.comment("Elements after function call to getTableElementsByRow " + elementsinColumn)
		
		if(elementsinColumn.contains(Actuarial_Text))
		{
			KeywordUtil.markPassed('Integration is successfull')
		}
		
		else
		{
			KeywordUtil.markPassed('Integration is unsuccessfull')
		}
	}
	
	else
	{
		KeywordUtil.markFailed('Status is suspended')
	}
}

WebUI.click(findTestObject('Object Repository/Administration/lnk_Policy'))