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
import java.text.DecimalFormat
import internal.GlobalVariable

/*SimpleDateFormat dateformat = new SimpleDateFormat('yyyy-MM-dd HH:mm:ss.SSS')
 String dateStr = dateformat.format(System.currentTimeMillis())
 try {
 long time = dateformat.parse("2016-09-02 23:02:17.256").getTime()
 System.out.println(time)
 //log.logInfo(time.toString())
 }
 catch (Exception e) {
 e.printStackTrace()
 }*/

public class FormatTime {
	//Enter million seconds parameter to get h, mom, s output
	def static formatElapsed(elapsed) {
		DecimalFormat df = new DecimalFormat("#.000")
		int millis = elapsed
		int hours = Math.floor(millis / (60 * 60 * 1000))
		millis -= hours * 60 * 60 * 1000
		int minutes = Math.floor(millis / (60 * 1000))
		millis -= minutes * 60 * 1000
		def seconds = df.format(millis / 1000)
		if(hours==0){
			if(minutes==0){
				return seconds.toString()+'s'
			}
			if(minutes!=0){
				return minutes.toString()+'min '+seconds.toString()+'s'
			}
		}
		if(hours!=0){
			if(minutes==0){
				return hours.toString()+'h '+seconds.toString()+'s'
			}
			if(minutes!=0){
				return hours.toString()+'h '+minutes.toString()+'min '+seconds.toString()+'s'
			}
		}
	}
}
