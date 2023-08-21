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


WebUI.comment('New_TroubleTicketsWizard_Step 1 of 5')

//Top Menu Selection
CustomKeywords.'gw.Actions.NavigateByTabMenus'('Desktop', 'My Trouble Tickets')

Thread.sleep(GlobalVariable.gSleepTime)

// Click new button to create the ticket
CustomKeywords.'gw.Actions.Click'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/btn_New'))

//Check Page load Successfully
WebUI.verifyElementPresent(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/wel_TroubleTicketWizard_Step1of5'), GlobalVariable.gWaitTimeOut)

//Type Selection
WebUI.selectOptionByLabel(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/lst_Step1_Type'),Type, false)

//Text Subject
CustomKeywords.'gw.Text.SetText'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/txt_Step1_Subject'), Subject)

//Text Details
CustomKeywords.'gw.Text.SetText'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/txt_Step1_Details'), Details)

//lst_Priority
WebUI.selectOptionByLabel(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/lst_Step1_Priority'),Priority, false)

//txt_DueDate_Optional
CustomKeywords.'gw.Text.SetText'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/txt_Step1_DueDate'), DueDate)

//txt_EscalationDate_Optional
CustomKeywords.'gw.Text.SetText'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/txt_Step1_EscalationDate'), EscalationDate)

// Next Step
CustomKeywords.'gw.Actions.Click'(findTestObject('Common/pge_Common/btn_Next'))

WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)

WebUI.comment('New_TroubleTicketsWizard_Step 2 of 5 - Accounts')

//Check Page load Successfully
WebUI.verifyElementPresent(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/wel_TroubleTicketWizard_Step2of5'), GlobalVariable.gWaitTimeOut)

//Click on Add Accounts
CustomKeywords.'gw.Actions.Click'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/btn_Add Accounts'))


//Check Accounts Page load Successfully
WebUI.verifyElementPresent(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/wel_Accounts'), GlobalVariable.gWaitTimeOut)

/*Search account
 *
 */

//Click on Search button
CustomKeywords.'gw.Actions.Click'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/btn_Search'))

// checkbox for item0
CustomKeywords.'gw.Actions.Click'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/chx_select_account'))

//select accounts
CustomKeywords.'gw.Actions.Click'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/btn_Select Accounts'))

WebUI.comment('New_TroubleTicketsWizard_Step 2 of 5 - Policies')

//Check Page load Successfully
WebUI.verifyElementPresent(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/wel_TroubleTicketWizard_Step2of5'), GlobalVariable.gWaitTimeOut)


//Click on Add Policies
CustomKeywords.'gw.Actions.Click'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/btn_Add Policies'))


//Check add Policies Page load Successfully
WebUI.verifyElementPresent(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/wel_Policies'), GlobalVariable.gWaitTimeOut)

/*Search Policies
 *
 */

//Click on Search button
CustomKeywords.'gw.Actions.Click'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/btn_Search'))

// checkbox for item0
CustomKeywords.'gw.Actions.Click'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/chx_select_policy'))


//select Policies
CustomKeywords.'gw.Actions.Click'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/btn_SelectPolicy'))


WebUI.comment('New_TroubleTicketsWizard_Step 3 of 5 - Optional')

// Next Step navigate to step3
CustomKeywords.'gw.Actions.Click'(findTestObject('Common/pge_Common/btn_Next'))

WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)

//Check Page load Successfully
WebUI.verifyElementPresent(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/wel_TroubleTicketWizard_Step3of5'), GlobalVariable.gWaitTimeOut)

//checkbox Delinquency
CustomKeywords.'gw.Actions.Click'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/chx_Delinquency'))

// testbox release date0
CustomKeywords.'gw.Text.SetText'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/txt_ReleaseDate0'), ReleaseDate0)

//Checkbox Invoice / Statement Sending
CustomKeywords.'gw.Actions.Click'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/chx_InvoiceSending'))

// testbox release date1
CustomKeywords.'gw.Text.SetText'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/txt_ReleaseDate1'), ReleaseDate1)

//Checkbox Commission policy Earnings
CustomKeywords.'gw.Actions.Click'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/chx_CommissionPolicyEarnings'))

// testbox release date2
CustomKeywords.'gw.Text.SetText'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/txt_ReleaseDate2'), ReleaseDate2)

//Checkbox Commission Payments
CustomKeywords.'gw.Actions.Click'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/chx_CommissionPaymets'))

// testbox release date3
CustomKeywords.'gw.Text.SetText'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/txt_ReleaseDate3'), ReleaseDate3)

//Checkbox Payment Distribution
CustomKeywords.'gw.Actions.Click'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/chx_PaymentDistribution'))

// testbox release date4
CustomKeywords.'gw.Text.SetText'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/txt_ReleaseDate4'), ReleaseDate4)

//Checkbox Automatic
CustomKeywords.'gw.Actions.Click'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/chx_AutomaticDisbursements'))

// Next Step
CustomKeywords.'gw.Actions.Click'(findTestObject('Common/pge_Common/btn_Next'))

WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)



WebUI.comment('New_TroubleTicketsWizard_Step 4 of 5 - add transaction Optional')

//Check Page load Successfully
WebUI.verifyElementPresent(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/wel_TroubleTicketWizard_Step4of5'), GlobalVariable.gWaitTimeOut)

//Click on add button
CustomKeywords.'gw.Actions.Click'(findTestObject('Common/pge_Common/btn_Add'))

//checkbox add transactions
CustomKeywords.'gw.Actions.Click'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/chx_Transactions'))

//select transaction
CustomKeywords.'gw.Actions.Click'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/btn_TransactionSelect'))

/*remove transaction on step4  optional
 

CustomKeywords.'gw.Actions.Click'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/chx_transaction_step4'))

CustomKeywords.'gw.Actions.Click'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/btn_TransactionRemove'))
 
 */

// Next Step
CustomKeywords.'gw.Actions.Click'(findTestObject('Common/pge_Common/btn_Next'))

WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)


//step 5 finished

CustomKeywords.'gw.Actions.Click'(findTestObject('BillingCenter/pge_NewTroubleTicketWizard/btn_FinishTickets'))

WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)


