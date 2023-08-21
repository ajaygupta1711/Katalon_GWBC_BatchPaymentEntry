package common

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.configuration.RunConfiguration
import java.text.SimpleDateFormat
import common.Excel
import java.io.File
import java.io.IOException
import java.io.FileInputStream
import java.io.InputStreamReader
import java.io.Reader
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import internal.GlobalVariable as GlobalVariable



public class ReadTestReport {

	static String projDir = RunConfiguration.getProjectDir()
	static String reportSumPath = projDir + "/Reports/Report Summary.xlsx"
	static String reportSheet = "Data"
	static String logFilePath = projDir + "/Reports/execution0log.xlsx"
	static String logSheet = "Data"
	static String etreportFilePath = projDir + "/Reports/Elapsed Time Report.xlsx"
	static String etreportSheet = "Data"
	static String rawFilePath = Excel.ReadFromExcel(reportSumPath, reportSheet, 1, 'LogFilePath')
	static List lst_TestCaseName = new ArrayList<String>()
	static List lstMax = new ArrayList<String>()


	def static GenerateLogReport(){
		Excel.RemoveValuesFromExcel(logFilePath, logSheet, 1)
		String encoding="GBK"
		try{
			File file=new File(rawFilePath+'/execution0.log')
			InputStreamReader read = new InputStreamReader(new FileInputStream(file),encoding)//encoding
			BufferedReader bufferedReader = new BufferedReader(read)
			String lineTxt = null
			int rowNum=1
			//write to excel from log
			while((lineTxt = bufferedReader.readLine()) != null){
				if(lineTxt.contains('<date>')){
					String[] temp1=lineTxt.split('</date>')
					String temp2=temp1[0]
					String[] temp3=temp2.split('<date>')
					String temp4=temp3[temp3.length-1]
					Excel.WirteToExcel(logFilePath, logSheet, rowNum, "date", temp4)
				}
				if(lineTxt.contains('<millis>')){
					String[] temp1=lineTxt.split('</millis>')
					String temp2=temp1[0]
					String[] temp3=temp2.split('<millis>')
					String temp4=temp3[temp3.length-1]
					Excel.WirteToExcel(logFilePath, logSheet, rowNum, "millis", temp4)
				}
				if(lineTxt.contains('<level>')){
					String[] temp1=lineTxt.split('</level>')
					String temp2=temp1[0]
					String[] temp3=temp2.split('<level>')
					String temp4=temp3[temp3.length-1]
					Excel.WirteToExcel(logFilePath, logSheet, rowNum, "level", temp4)
				}
				if(lineTxt.contains('<method>')){
					String[] temp1=lineTxt.split('</method>')
					String temp2=temp1[0]
					String[] temp3=temp2.split('<method>')
					String temp4=temp3[temp3.length-1]
					Excel.WirteToExcel(logFilePath, logSheet, rowNum, "method", temp4)
				}
				if(lineTxt.contains('<message>')){
					String[] temp1=lineTxt.split('</message>')
					String temp2=temp1[0]
					String[] temp3=temp2.split('<message>')
					String temp4=temp3[temp3.length-1]
					Excel.WirteToExcel(logFilePath, logSheet, rowNum, "message", temp4.replaceAll('\\\\&amp;quot;', '"').replaceAll('&amp;apos;', '"'))
				}
				if(lineTxt.contains('<nestedLevel>')){
					String[] temp1=lineTxt.split('</nestedLevel>')
					String temp2=temp1[0]
					String[] temp3=temp2.split('<nestedLevel>')
					String temp4=temp3[temp3.length-1]
					Excel.WirteToExcel(logFilePath, logSheet, rowNum, "nestedLevel", temp4)
					rowNum++
				}
			}

			read.close()
		}catch(Exception e){
			println("Can't open the file!")
		}

		removeInvalidInfo()

		saveTestSuite()

		saveTestCase()

		saveTestStep()

		copyReport(etreportFilePath,rawFilePath+'/Elapsed Time Report.xlsx')

	}

	def static copyReport(String sourcePath, String destPath){
		File sourceReport = new File(sourcePath)
		File destReport = new File(destPath)
		Files.copy(sourceReport.toPath(), destReport.toPath())
	}

	def static removeInvalidInfo(){
		Excel.removeEmptyRows(logFilePath, logSheet)
		List dellst = new ArrayList<String>()
		//delete 'RUN_DATA' record
		dellst=Excel.getRowNumbers(logFilePath, logSheet, 'level', 'RUN_DATA')
		Excel.RemoveValuesFromExcel(logFilePath, logSheet, dellst)
		Excel.removeEmptyRows(logFilePath, logSheet)

		//delete 'INFO' record
		dellst=Excel.getRowNumbers(logFilePath, logSheet, 'level', 'INFO')
		Excel.RemoveValuesFromExcel(logFilePath, logSheet, dellst)
		Excel.removeEmptyRows(logFilePath, logSheet)

		//delete listener related record
		List dellst_start = new ArrayList<String>()
		List dellst_end = new ArrayList<String>()
		List dellst_to = new ArrayList<String>()
		dellst_start=Excel.getRowNumbers(logFilePath, logSheet, 'Start listener action :')
		dellst_end=Excel.getRowNumbers(logFilePath, logSheet, 'End listener action :')
		for(int i=0;i<dellst_start.size();i++){
			for(int j=Integer.valueOf(dellst_start.get(i));j<=Integer.valueOf(dellst_end.get(i));j++){
				dellst_to.add(j.toString())
			}
		}
		Excel.RemoveValuesFromExcel(logFilePath, logSheet, dellst_to)
		Excel.removeEmptyRows(logFilePath, logSheet)
	}
	//write test suite info to excel
	def static saveTestSuite(){
		//remove values from target excel
		Excel.RemoveValuesFromExcel(etreportFilePath, etreportSheet, 1)
		List lst = new ArrayList<String>()
		lst = Excel.getRowNumbers(logFilePath, logSheet, 'Start Test Suite')
		String TS_StartTime=Excel.ReadFromExcel(logFilePath, logSheet, Integer.valueOf(lst.get(0)), 'date')
		String TS_StartTime_Millis=Excel.ReadFromExcel(logFilePath, logSheet, Integer.valueOf(lst.get(0)), 'millis')
		String[] Msg_Name=Excel.ReadFromExcel(logFilePath, logSheet, Integer.valueOf(lst.get(0)), 'message').split('Start Test Suite : ')
		String TS_Name=Msg_Name[Msg_Name.length-1]
		Excel.WirteToExcel(etreportFilePath, etreportSheet, 1, "TestSuite/TestCase/TestStep", TS_Name)
		Excel.WirteToExcel(etreportFilePath, etreportSheet, 1, "StartTime", TS_StartTime)
		lst = Excel.getRowNumbers(logFilePath, logSheet, 'End Test Suite')
		String TS_EndTime=Excel.ReadFromExcel(logFilePath, logSheet, Integer.valueOf(lst.get(0)), 'date')
		String TS_EndTime_Millis=Excel.ReadFromExcel(logFilePath, logSheet, Integer.valueOf(lst.get(0)), 'millis')
		Excel.WirteToExcel(etreportFilePath, etreportSheet, 1, "EndTime", TS_EndTime)
		Excel.WirteToExcel(etreportFilePath, etreportSheet, 1, "ElapsedTime", common.FormatTime.formatElapsed(Long.valueOf(TS_EndTime_Millis)-Long.valueOf(TS_StartTime_Millis)))
	}

	def static saveTestCase(){
		//set start rownumber to write test case level log
		int rowNum=2
		List lst = new ArrayList<String>()
		lst = Excel.getRowNumbers(logFilePath, logSheet, 'Start Test Case')
		lstMax = Excel.getRowNumbers(logFilePath, logSheet, 'End Test Suite')
		for(int i=0;i<lst.size();i++){
			//get test case start time, and exclude sub testcase
			if(Excel.ReadFromExcel(logFilePath, logSheet, Integer.valueOf(lst.get(i)), 'nestedLevel')=='1'){
				String TC_StartTime=Excel.ReadFromExcel(logFilePath, logSheet, Integer.valueOf(lst.get(i)), 'date')
				String TC_StartTime_Millis=Excel.ReadFromExcel(logFilePath, logSheet, Integer.valueOf(lst.get(i)), 'millis')
				String[] Msg_Name=Excel.ReadFromExcel(logFilePath, logSheet, Integer.valueOf(lst.get(i)), 'message').split('Start Test Case : ')
				String TC_Name=Msg_Name[Msg_Name.length-1]
				lst_TestCaseName.add(TC_Name)
				//println('我的case名字是： '+TC_Name)
				Excel.WirteToExcel(etreportFilePath, etreportSheet, rowNum, "TestSuite/TestCase/TestStep", TC_Name)
				Excel.WirteToExcel(etreportFilePath, etreportSheet, rowNum, "StartTime", TC_StartTime)
				//println('我的row数是： '+rowNum.toString())
				//get test case end time
				for(int j=Integer.valueOf(lst.get(i))+1;j<Integer.valueOf(lstMax.get(0));j++){
					if(Excel.ReadFromExcel(logFilePath, logSheet, j, 'message').contains('End Test Case : '+TC_Name)){
						String TC_EndTime=Excel.ReadFromExcel(logFilePath, logSheet, j, 'date')
						String TC_EndTime_Millis=Excel.ReadFromExcel(logFilePath, logSheet, j, 'millis')
						Excel.WirteToExcel(etreportFilePath, etreportSheet, rowNum, "EndTime", TC_EndTime)
						Excel.WirteToExcel(etreportFilePath, etreportSheet, rowNum, "ElapsedTime", common.FormatTime.formatElapsed(Long.valueOf(TC_EndTime_Millis)-Long.valueOf(TC_StartTime_Millis)))
						rowNum++
						break
					}
				}
			}
		}
	}

	def static saveTestStep(){
		//set test step start rownumber
		int rowNum_Step=lst_TestCaseName.size()+4
		List lst_TC = new ArrayList<String>()
		List lst_StepStart = new ArrayList<String>()
		List lst_StepEnd = new ArrayList<String>()
		//loop within test case list
		for(int i=0;i<lst_TestCaseName.size();i++){
			lst_TC = Excel.getRowNumbers(logFilePath, logSheet, lst_TestCaseName.get(i))
			//loop within test step
			for(int j=Integer.valueOf(lst_TC.get(0))+1;j<Integer.valueOf(lst_TC.get(1));j++){
				if(Excel.ReadFromExcel(logFilePath, logSheet, j, 'message').contains('Start action : callTestCase')){
					String[] Msg_Name=Excel.ReadFromExcel(logFilePath, logSheet, j, 'message').split('Start action : ')
					String Step_Name=Msg_Name[Msg_Name.length-1]
					String Step_StartTime=Excel.ReadFromExcel(logFilePath, logSheet, j, 'date')
					String Step_StartTime_Millis=Excel.ReadFromExcel(logFilePath, logSheet, j, 'millis')
					Excel.WirteToExcel(etreportFilePath, etreportSheet, rowNum_Step, "TestSuite/TestCase/TestStep", Step_Name)
					Excel.WirteToExcel(etreportFilePath, etreportSheet, rowNum_Step, "StartTime", Step_StartTime)
					//get test step end time of selected test case
					for(int k=j+1;k<Integer.valueOf(lst_TC.get(1));k++){
						if(Excel.ReadFromExcel(logFilePath, logSheet, k, 'message').contains('End action : callTestCase')){
							String Step_EndTime=Excel.ReadFromExcel(logFilePath, logSheet, k, 'date')
							String Step_EndTime_Millis=Excel.ReadFromExcel(logFilePath, logSheet, k, 'millis')
							Excel.WirteToExcel(etreportFilePath, etreportSheet, rowNum_Step, "EndTime", Step_EndTime)
							Excel.WirteToExcel(etreportFilePath, etreportSheet, rowNum_Step, "ElapsedTime", common.FormatTime.formatElapsed(Long.valueOf(Step_EndTime_Millis)-Long.valueOf(Step_StartTime_Millis)))
							rowNum_Step++
							break
						}
					}
					continue
				}
				if(Excel.ReadFromExcel(logFilePath, logSheet, j, 'message').contains('Start action : ')){
					String[] Msg_Name=Excel.ReadFromExcel(logFilePath, logSheet, j, 'message').split('Start action : ')
					String Step_Name=Msg_Name[Msg_Name.length-1]
					String Step_StartTime=Excel.ReadFromExcel(logFilePath, logSheet, j, 'date')
					String Step_StartTime_Millis=Excel.ReadFromExcel(logFilePath, logSheet, j, 'millis')
					Excel.WirteToExcel(etreportFilePath, etreportSheet, rowNum_Step, "TestSuite/TestCase/TestStep", Step_Name)
					Excel.WirteToExcel(etreportFilePath, etreportSheet, rowNum_Step, "StartTime", Step_StartTime)
					//get test step end time of selected test case
					for(int k=j+1;k<Integer.valueOf(lst_TC.get(1));k++){
						if(Excel.ReadFromExcel(logFilePath, logSheet, k, 'message').contains('End action : ')){
							String Step_EndTime=Excel.ReadFromExcel(logFilePath, logSheet, k, 'date')
							String Step_EndTime_Millis=Excel.ReadFromExcel(logFilePath, logSheet, k, 'millis')
							Excel.WirteToExcel(etreportFilePath, etreportSheet, rowNum_Step, "EndTime", Step_EndTime)
							Excel.WirteToExcel(etreportFilePath, etreportSheet, rowNum_Step, "ElapsedTime", common.FormatTime.formatElapsed(Long.valueOf(Step_EndTime_Millis)-Long.valueOf(Step_StartTime_Millis)))
							rowNum_Step++
							break
						}
					}
				}

			}
			rowNum_Step++
		}
	}

}
