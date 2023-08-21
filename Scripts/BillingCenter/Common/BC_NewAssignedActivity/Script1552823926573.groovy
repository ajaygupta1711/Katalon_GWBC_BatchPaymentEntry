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

//Navigate to the new assigned activity page
CustomKeywords.'gw.Actions.NavigateByTab'('Desktop')

CustomKeywords.'gw.Actions.NavigateByActions'('New Assigned Activity;New Assigned Activity;Reminder;Reminder;notification')

//Check Page load Successfully
WebUI.verifyElementPresent(findTestObject('BillingCenter/pge_NewActivity/wel_NewActivity'), GlobalVariable.gWaitTimeOut)

//input activity info

//Text Subject
CustomKeywords.'gw.Text.SetText'(findTestObject('BillingCenter/pge_NewActivity/txt_Subject'), Subject)

//Text description
CustomKeywords.'gw.Text.SetText'(findTestObject('BillingCenter/pge_NewActivity/txt_Description_New'), Description)


//Set Priority
WebUI.selectOptionByLabel(findTestObject('BillingCenter/pge_NewActivity/lst_Priority'),Priority, false)


//Text DueDate
CustomKeywords.'gw.Text.SetText'(findTestObject('BillingCenter/pge_NewActivity/txt_DueDate'), DueDate)


//Text EscalationDate
CustomKeywords.'gw.Text.SetText'(findTestObject('BillingCenter/pge_NewActivity/txt_EscalationDate'), EscalationDate)

//Set Assigned to
WebUI.selectOptionByLabel(findTestObject('BillingCenter/pge_NewActivity/lst_AssignedTo'),AssignedTo, false)


//click on update button

CustomKeywords.'gw.Actions.Click'(findTestObject('Common/pge_Common/btn_Update'))

WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)