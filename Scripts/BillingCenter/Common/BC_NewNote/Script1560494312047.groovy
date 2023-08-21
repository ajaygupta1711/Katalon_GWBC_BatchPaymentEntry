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

WebUI.callTestCase(findTestCase('Common/GW_OpenPolicy'), [('PolicyNumber') : PolicyNumber], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'gw.Actions.NavigateByActions'('New Note')

if (WebUI.waitForElementVisible(findTestObject('Common/pge_Note/btn_NoteTemplate'), GlobalVariable.gWaitTimeOut, FailureHandling.OPTIONAL) == 
false) {
    CustomKeywords.'gw.Actions.Click'(findTestObject('Common/pge_Note/btn_Icon'))
}

WebUI.waitForElementVisible(findTestObject('Common/pge_Note/btn_NoteTemplate'), GlobalVariable.gWaitTimeOut, FailureHandling.STOP_ON_FAILURE)

// input the note info
CustomKeywords.'gw.Actions.SelectOption'(findTestObject('Common/pge_Note/lst_Topic'), NoteTopic)

CustomKeywords.'gw.Text.SetText'(findTestObject('Common/pge_Note/txt_Subject'), NoteSubject)

CustomKeywords.'gw.Actions.SelectOption'(findTestObject('Common/pge_Note/lst_RelatedTo'), RelatedTo)


CustomKeywords.'gw.Actions.SelectOption'(findTestObject('Common/pge_Note/lst_SecurityLevel'), SecurityLevel)

CustomKeywords.'gw.Text.SetText'(findTestObject('Common/pge_Note/txt_Text'), NoteText)

CustomKeywords.'gw.Actions.Click'(findTestObject('Common/pge_Common/btn_Update'))

