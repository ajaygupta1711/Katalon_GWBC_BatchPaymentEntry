import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.stringtemplate.v4.compiler.CodeGenerator.region_return

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import common.Excel

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.configuration.RunConfiguration
import java.text.SimpleDateFormat

class GWListener {
	
	static def iteration = 0
	//static def tempTestCaseId = ""
	static def tempRowNum = 0
	static def passedCnt = 0
	static def failedCnt = 0
	static def errorCnt = 0
	//Excel iExcel = new Excel()
	String projDir = RunConfiguration.getProjectDir()
	String statusFilePath = projDir + "/Reports/Execution Status.xlsx"
	String statusSheet = "Data"
	
	String reportSumPath = projDir + "/Reports/Report Summary.xlsx"
	String reportSheet = "Data"
	SimpleDateFormat dateformat = new SimpleDateFormat('yyyy-MM-dd HH:mm:ss')
	String date_tc_start_Time
	String date_tc_end_Time
	String date_ts_start_Time
	String date_ts_end_Time

	/**
	 * Executes before every test case starts.
	 * @param testCaseContext related information of the executed test case.
	 */
	@BeforeTestCase
	
/*	def sampleBeforeTestCase(TestCaseContext testCaseContext) {
		if(GlobalVariable.SKIP_REMAINING_TESTS==false){
			testCaseContext.skipThisTestCase()
			println "Test Case skipped"
		}
	}*/


	
 

	
	def gwBeforeTestCase(TestCaseContext testCaseContext) {	
		//println GlobalVariable.Env_TestCaseVariables.get("Iteration")
		//if(tempTestCaseId == testCaseContext.getTestCaseId())
		
		if(GlobalVariable.Env_TestCaseId == testCaseContext.getTestCaseId())
			iteration ++
		else 
			//tempTestCaseId = testCaseContext.getTestCaseId()
			iteration = 1
			
		GlobalVariable.Env_Iteration = iteration
		
		GlobalVariable.Env_TestCaseId = testCaseContext.getTestCaseId()
		GlobalVariable.Env_TestCaseVariables = testCaseContext.getTestCaseVariables()
		
		//println GlobalVariable.Env_TestCaseId
		//println("Iteration:" + GlobalVariable.Env_Iteration.toString())
		GlobalVariable.tc_start_Time = System.currentTimeMillis()
		date_tc_start_Time = dateformat.format(GlobalVariable.tc_start_Time)

	}

	/**
	 * Executes after every test case ends.
	 * @param testCaseContext related information of the executed test case.
	 */
	@AfterTestCase
	
/*	def sampleAfterTestCase(TestCaseContext testCaseContext) {
		if(testCaseContext.testCaseStatus=='FAILED'){
			GlobalVariable.SKIP_REMAINING_TESTS = false
		}
		
	}*/
	
	def gwAfterTestCase(TestCaseContext testCaseContext) {
		GlobalVariable.tc_end_Time = System.currentTimeMillis()
		 date_tc_end_Time = dateformat.format(GlobalVariable.tc_end_Time)
		//GlobalVariable.elapsed_Time = GlobalVariable.elapsed_Time+(GlobalVariable.tc_end_Time - GlobalVariable.tc_start_Time)
		tempRowNum ++
		
		String testCaseID = testCaseContext.getTestCaseId()
		TestCase testCase = TestCaseFactory.findTestCase(testCaseID)
		String testCaseName = testCase.getName()
		switch (testCaseContext.getTestCaseStatus()) {
			case "PASSED": 
				passedCnt ++
				break
			case "FAILED":
				failedCnt ++
				break
			case "ERROR":
				errorCnt ++
				break
		}
		
		//println statusFilePath
		//println tempRowNum
//		Excel.WirteToExcel(reportSumPath, reportSheet, 1, "ReportFilePath", "T")
	
		if (testCaseName == 'GenerateAndSendReport') return
		if (testCaseName == 'GenerateAndSaveReport') return
		if (testCaseName == 'GenerateTimeReport') return
		if (!testCaseName.contains('Submission')&&!testCaseName.contains('Issurance')&&!testCaseName.contains('NewAccount')
			&&!testCaseName.contains('FNOL')&&!testCaseName.contains('QuickClaim')&&!testCaseName.contains('verify'))
			Excel.WirteToExcel(statusFilePath, statusSheet, tempRowNum, "Name", testCaseName)
		if (testCaseName.contains('Submission')||testCaseName.contains('Issurance'))
			Excel.WirteToExcel(statusFilePath, statusSheet, tempRowNum, "Name", testCaseName+"\r\n(Pol#: "+GlobalVariable.gPolicyNumber+')')
		if (testCaseName.contains('NewAccount'))
			Excel.WirteToExcel(statusFilePath, statusSheet, tempRowNum, "Name", testCaseName+"\r\n(Acc#: "+GlobalVariable.gAccountNumber+')')
		if (testCaseName.contains('FNOL')||testCaseName.contains('QuickClaim')||testCaseName.contains('verify'))
			Excel.WirteToExcel(statusFilePath, statusSheet, tempRowNum, "Name", testCaseName+"\r\n(Claim#: "+GlobalVariable.gClaimNumber+')')
		Excel.WirteToExcel(statusFilePath, statusSheet, tempRowNum, "Status", testCaseContext.getTestCaseStatus())
		Excel.WirteToExcel(statusFilePath, statusSheet, tempRowNum, "Iteration", GlobalVariable.Env_Iteration.toString())
		Excel.WirteToExcel(statusFilePath, statusSheet, tempRowNum, "Start Time", date_tc_start_Time)
		Excel.WirteToExcel(statusFilePath, statusSheet, tempRowNum, "End Time", date_tc_end_Time)
		Excel.WirteToExcel(statusFilePath, statusSheet, tempRowNum, "Elapsed Time", common.FormatTime.formatElapsed(GlobalVariable.tc_end_Time-GlobalVariable.tc_start_Time))
		
//		Excel.WirteToExcel(statusFilePath, statusSheet, tempRowNum, "Status", testCaseContext.getTestCaseStatus())
//		Excel.WirteToExcel(statusFilePath, statusSheet, tempRowNum, "Iteration", GlobalVariable.Env_Iteration.toString())
		
		Excel.WirteToExcel(reportSumPath, reportSheet, 1, "Passed", passedCnt.toString())
		Excel.WirteToExcel(reportSumPath, reportSheet, 1, "Failed", failedCnt.toString())
		Excel.WirteToExcel(reportSumPath, reportSheet, 1, "Error", errorCnt.toString())
		
		/*
		String reportFilePath = RunConfiguration.getReportFolder()
		String[] temp = reportFilePath.split("\\\\")
		String fileName = temp[temp.length - 1]
		fileName = fileName + ".html"
		reportFilePath = reportFilePath + "\\" + fileName
		Excel.WirteToExcel(reportSumPath, reportSheet, 1, "ReportFilePath", reportFilePath)*/
		
		//println "testCaseName: " + testCaseName
		//println testCaseContext.getTestCaseStatus()

		
	}

	/**
	 * Executes before every test suite starts.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	@BeforeTestSuite
	def gwBeforeTestSuite(TestSuiteContext testSuiteContext) {

		String TestSuiteCollectionNamesad  = RunConfiguration.getExecutionGeneralProperties()
		
		println TestSuiteCollectionNamesad
		WebUI.comment(TestSuiteCollectionNamesad)
		
		Map m = RunConfiguration.getExecutionProperties()
		
		println m.get("general")
		
		
		GlobalVariable.gProjDirectory = projDir
		iteration = 1
		//tempTestCaseId = ''
		String[] suiteId = testSuiteContext.getTestSuiteId().split('/')
		String suiteName = suiteId[suiteId.length - 1]
		//println(testSuiteContext.getTestSuiteId())
		if(suiteName != 'EmailReport' & suiteName != 'GenerateReport' ){ 
			Excel.RemoveValuesFromExcel(statusFilePath, statusSheet, 1)
			//Excel.RemoveValuesFromExcel(reportSumPath, reportSheet, 1)
			Excel.WirteToExcel(reportSumPath, reportSheet, 1, "ReportFilePath", "T")
			
			String prodName = suiteName.split('_')[0]
			String envName = RunConfiguration.getExecutionProfile()
			String Email_Subject =  GlobalVariable.Email_Subject + "_" + prodName + " " + envName
			
			Excel.WirteToExcel(reportSumPath, reportSheet, 1, "SuiteName", suiteName)
			Excel.WirteToExcel(reportSumPath, reportSheet, 1, "EnvName", envName)
			Excel.WirteToExcel(reportSumPath, reportSheet, 1, "EmailSubject", Email_Subject)
		}
//		else GlobalVariable.Email_Subject = Excel.ReadFromExcel(reportSumPath, reportSheet, 1, "EmailSubject")
		
		GlobalVariable.Env_TestSuiteName = suiteName
		GlobalVariable.Env_TestSuiteId = testSuiteContext.getTestSuiteId()
		//println GlobalVariable.Env_TestSuiteId
		GlobalVariable.ts_start_Time = System.currentTimeMillis()
		date_ts_start_Time = dateformat.format(GlobalVariable.ts_start_Time)

		
	}

	/**
	 * Executes after every test suite ends.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	@AfterTestSuite
	def gwAfterTestSuite(TestSuiteContext testSuiteContext) {
		if(GlobalVariable.Env_TestSuiteName == 'EmailReport') return
		if(GlobalVariable.Env_TestSuiteName == 'GenerateReport') return
		if(GlobalVariable.Env_TestSuiteName == 'GenerateElapsedTimeReport') return
		GlobalVariable.ts_end_Time = System.currentTimeMillis()
		date_ts_end_Time = dateformat.format(GlobalVariable.ts_end_Time)
		Excel.WirteToExcel(reportSumPath, reportSheet, 1, "Elapsed Time", common.FormatTime.formatElapsed(GlobalVariable.ts_end_Time-GlobalVariable.ts_start_Time))
		Excel.WirteToExcel(reportSumPath, reportSheet, 1, "Start Time", date_ts_start_Time)
		Excel.WirteToExcel(reportSumPath, reportSheet, 1, "End Time", date_ts_end_Time)
		Excel.WirteToExcel(reportSumPath, reportSheet, 1, "LogFilePath", RunConfiguration.getReportFolder())
		common.HTMLReport.GenerateReport(0)
		
//		String reportFilePath = RunConfiguration.getReportFolder() + "\\Report.html"
		String reportFilePath = RunConfiguration.getReportFolder()
		
		String[] temp = reportFilePath.split("\\\\")
		String fileName = temp[temp.length - 1]
		fileName = fileName + ".html"
		
		reportFilePath = reportFilePath + "\\" + fileName
		
		//GlobalVariable.reportFilePath = reportFilePath.toString()
		Excel.WirteToExcel(reportSumPath, reportSheet, 1, "ReportFilePath", reportFilePath)
		
		//gw.Email.SendFileEmail("text@pwc.com", "test@accidenfund.com", "", "", reportSumPath)
		//println testSuiteContext.getTestSuiteId()
		

	}
}