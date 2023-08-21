package common

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable as GlobalVariable
import common.Excel
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import org.jsoup.*
import org.jsoup.nodes.*

import com.kms.katalon.core.configuration.RunConfiguration
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

public class HTMLReport {

	static String projDir = RunConfiguration.getProjectDir()
	static String statusFilePath = projDir + "/Reports/Execution Status.xlsx"
	static String statusSheet = "Data"
	static String reportFilePath = projDir + "/Reports/Report Summary.xlsx"
	static String reportSheet = "Data"
	static String pwcLogo = projDir + "/Reports/PWC SDC.jpg"
	static String filePath = Excel.ReadFromExcel(reportFilePath, reportSheet, 1, "ReportFilePath")
	static String TestSuiteCollectionName  = RunConfiguration.getExecutionSource()

	static String testSuiteName = Excel.ReadFromExcel(reportFilePath, reportSheet, 1, "SuiteName")
	static String testEnv = Excel.ReadFromExcel(reportFilePath, reportSheet, 1, "EnvName")
	static String Email_Subject = Excel.ReadFromExcel(reportFilePath, reportSheet, 1, "EmailSubject")

	static String emailReportPath = GlobalVariable.Email_ReportFolder + "\\" + testSuiteName + "\\" + testEnv
	static String TestSuiteCollectionFolderName = RunConfiguration.getReportFolder()
	//static String emailReportPath = GlobalVariable.Email_ReportFolder + "\\"+TestSuiteCollectionFolderName.substring(TestSuiteCollectionFolderName.length() -15) +"\\" + testSuiteName + "\\"
	def static String GenerateReport(int indicator){
		//0 is for savereport, 1 is for sending report
		//Excel iExcel
		println TestSuiteCollectionName
		WebUI.comment(TestSuiteCollectionName)

		String reportTempalte = projDir + "/Reports/Report Template.html"
		//String reportTempalte = projDir + "/Reports/Report Template - Copy.html"
		File reportTo = new File(reportTempalte.toString())

		Document docReportTo = Jsoup.parse(reportTo, "UTF-8");

		/*File reportFrom = new File(reportFilePath);
		 Document docReportFrom = Jsoup.parse(reportFrom, "UTF-8");
		 docReportFrom.select("div#s1")
		 Element testSuiteFrom  = docReportFrom.getElementById("s1").getElementsByAttributeValue("class", "name").get(0)
		 String suiteName = testSuiteFrom.text()*/

		String suiteName = Excel.ReadFromExcel(reportFilePath, reportSheet, 1, "SuiteName")
		Element testSuiteTo  = docReportTo.getElementById("TestSuiteName")
		testSuiteTo.text(suiteName)
		println testSuiteTo.text()

		String envName = Excel.ReadFromExcel(reportFilePath, reportSheet, 1, "EnvName")
		Element env  = docReportTo.getElementById("TestEnvName")
		env.text(envName)
		println env.text()

		String passedCnt = "Passed: " + Excel.ReadFromExcel(reportFilePath, reportSheet, 1, "Passed")
		Element span  = docReportTo.getElementById("PassedCnt")
		span.text(passedCnt)
		println span.text()

		String failedCnt = "Failed: " + Excel.ReadFromExcel(reportFilePath, reportSheet, 1, "Failed")
		span  = docReportTo.getElementById("FailedCnt")
		span.text(failedCnt)
		println span.text()

		String errorCnt = "Error: " + Excel.ReadFromExcel(reportFilePath, reportSheet, 1, "Error")
		span  = docReportTo.getElementById("ErrorCnt")
		span.text(errorCnt)
		println span.text()

		String startTime = Excel.ReadFromExcel(reportFilePath, reportSheet, 1, "Start Time")
		span  = docReportTo.getElementById("TestSuiteStartTime")
		span.text(startTime)
		println span.text()

		String endTime = Excel.ReadFromExcel(reportFilePath, reportSheet, 1, "End Time")
		span  = docReportTo.getElementById("TestSuiteEndTime")
		span.text(endTime)
		println span.text()

		String elapsedTime = Excel.ReadFromExcel(reportFilePath, reportSheet, 1, "Elapsed Time")
		span  = docReportTo.getElementById("TestSuiteElapsedTime")
		span.text(elapsedTime)
		println span.text()

		Element TestCasesStatus = docReportTo.getElementById("TestCasesStatus")
		List TestCaseNames = Excel.GetValuesFromExcel(statusFilePath, statusSheet, "Name")
		List TestCaseStatus = Excel.GetValuesFromExcel(statusFilePath, statusSheet, "Status")
		List TestCaseIterations = Excel.GetValuesFromExcel(statusFilePath, statusSheet, "Iteration")
		List TestCaseElapsedTime = Excel.GetValuesFromExcel(statusFilePath, statusSheet, "Elapsed Time")
		List TestCaseStartTime = Excel.GetValuesFromExcel(statusFilePath, statusSheet, "Start Time")
		List TestCaseEndTime = Excel.GetValuesFromExcel(statusFilePath, statusSheet, "End Time")

		for(int i = 0; i < TestCaseNames.size(); i++) {
			//int i = 2
			String newCaseStatus = "<tr style='mso-yfti-irow:5;mso-yfti-lastrow:yes;height:27.0pt'><td width=\"8%\" style='width:8.0%;padding:.75pt .75pt .75pt .75pt;height:27.0pt'><p class=MsoNormal><span style='mso-fareast-font-family:\"Times New Roman\"'>" +
					(i+1).toString() + "</span></p></td><td width=\"25%\" style='width:25.0%;padding:.75pt .75pt .75pt .75pt;height:27.0pt'><p class=MsoNormal><span style='mso-fareast-font-family:\"Times New Roman\"'>" +
					TestCaseNames.get(i).toString() + "</span></p></td><td width=\"18%\" style='width:18.0%;padding:.75pt .75pt .75pt .75pt;height:27.0pt'><p class=MsoNormal><span style='mso-fareast-font-family:\"Times New Roman\"'>" +
					TestCaseStartTime.get(i).toString() + "</span></p></td><td width=\"18%\" style='width:18.0%;padding:.75pt .75pt .75pt .75pt;height:27.0pt'><p class=MsoNormal><span style='mso-fareast-font-family:\"Times New Roman\"'>" +
					TestCaseEndTime.get(i).toString() + "</span></p></td><td width=\"12%\" style='width:12.0%;padding:.75pt .75pt .75pt .75pt;height:27.0pt'><p class=MsoNormal><span style='mso-fareast-font-family:\"Times New Roman\"'>" +
					TestCaseElapsedTime.get(i).toString() + "</span></p></td><td width=\"10%\" style='width:10.0%;padding:.75pt .75pt .75pt .75pt;height:27.0pt'><p class=MsoNormal><span style='mso-fareast-font-family:\"Times New Roman\"'>" +
					TestCaseIterations.get(i).toString() + "</span></p></td>"
			String status = TestCaseStatus.get(i).toString()
			if(status == "PASSED")
				newCaseStatus = newCaseStatus + "<td width=\"9%\" style='width:9.0%;padding:.75pt .75pt .75pt .75pt;height:27.0pt'><p class=MsoNormal><span style='mso-fareast-font-family:\"Times New Roman\";color:green'>PASSED</span></p></td>"
			else
				newCaseStatus = newCaseStatus + "<td width=\"9%\" style='width:9.0%;padding:.75pt .75pt .75pt .75pt;height:27.0pt'><p class=MsoNormal><span style='mso-fareast-font-family:\"Times New Roman\";color:red'>" +
						status + "</span></p></td>"

			newCaseStatus = newCaseStatus + "</tr>"

			TestCasesStatus.append(newCaseStatus)
		}

		String htmlReport = docReportTo.html()

		htmlReport = UpdateLogoReportLink(docReportTo)

		if(indicator==1) htmlReport=UpdateForEmail(docReportTo)

		SaveReport(htmlReport)

		return htmlReport
	}

	def static String UpdateLogoReportLink(Document docReportTo){

		String emailReportName = "Test Logs"
		String emailReportFile = GlobalVariable.Email_ReportFolder + "\\" + emailReportName
		File rf = new File(emailReportFile)
		rf.mkdirs()

		if (filePath!="T") {

			String[] temp = filePath.split("\\\\")
			String fileName = temp[temp.length - 2]
			fileName = "Test Log_" + fileName + ".html"

			emailReportName = testSuiteName + "_" + fileName
			emailReportFile = emailReportFile + "\\" + emailReportName
			File sourceReport = new File(filePath)
			File destReport = new File(emailReportFile)
			Files.copy(sourceReport.toPath(), destReport.toPath())
		}

		Element logo = docReportTo.getElementById("_x0000_i1025")
		logo.attr("src", pwcLogo)
		Element report  = docReportTo.getElementById("ReportURL")
		report.text(emailReportName)
		report.attr("href", emailReportFile)


		return docReportTo.html()
	}

	def static String UpdateForEmail(Document docReportTo){

		Element reportindicator = docReportTo.getElementById("ReportIndicator")
		reportindicator.text("You can now open your project to view the execution report.")
		Element report  = docReportTo.getElementById("ReportURL")
		report.text('')
		report.attr("href", '')
		return docReportTo.html()
	}


	def static void SaveReport(String htmlReport){

		File rf = new File(emailReportPath)
		rf.mkdirs()
		String fileName
		String reportFile

		if (filePath=="T") {
			String reportFilePath = RunConfiguration.getReportFolder()
			String[] temp = reportFilePath.split("\\\\")
			fileName = GlobalVariable.Email_Subject + "_" + temp[temp.length - 1]
		}else {
			String[] temp = filePath.split("\\\\")
			fileName = GlobalVariable.Email_Subject + "_" + temp[temp.length - 2]
		}

		reportFile = emailReportPath + "\\" + fileName + ".html"

		Files.write(Paths.get(reportFile), htmlReport.getBytes())

	}
}