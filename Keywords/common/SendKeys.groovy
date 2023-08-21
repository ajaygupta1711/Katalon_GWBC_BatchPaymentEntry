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
import java.awt.Robot
import java.awt.event.KeyEvent


import internal.GlobalVariable

/**
 * Simulates keystroke events
 * @author blin038
 *
 */
public class SendKeys {

	static Robot robot = new Robot()

	/**
	 * Send SHIFT+ALT+T by keystroke events
	 */
	def static void SendShiftAltT(){
		robot.keyPress(KeyEvent.VK_SHIFT)
		robot.keyPress(KeyEvent.VK_ALT)
		robot.keyPress(KeyEvent.VK_T)
		robot.delay(50)
		robot.keyRelease(KeyEvent.VK_T)
		robot.keyRelease(KeyEvent.VK_ALT)
		robot.keyRelease(KeyEvent.VK_SHIFT)
	}

	/**
	 * Send PageUp by keystroke events
	 */
	def static void PageUp(){
		robot.keyPress(KeyEvent.VK_HOME)
		robot.delay(25)
		robot.keyRelease(KeyEvent.VK_HOME)
	}

	/**
	 * Send ENTER by keystroke events
	 */
	def static void SendEnter(int num){
		if(num<=0) return
		else{
			for(int i=1;i<num+1;i++)
				robot.keyPress(KeyEvent.VK_ENTER)
			robot.delay(50)
			robot.delay(25)
			robot.keyRelease(KeyEvent.VK_ENTER)
		}
	}

	/**
	 * Send ENTER by keystroke events
	 */
	def static void SendEnter(){
		robot.keyPress(KeyEvent.VK_ENTER)
		robot.delay(25)
		robot.keyRelease(KeyEvent.VK_ENTER)
	}

	/**
	 * Send TAB by keystroke events
	 */
	def static void SendTab(int num){
		if(num<=0) return
		else{
			for(int i=1;i<num+1;i++)
				robot.keyPress(KeyEvent.VK_TAB)
			robot.delay(50)
			robot.delay(25)
			robot.keyRelease(KeyEvent.VK_TAB)
		}
	}

	def static void SendTab(){
		robot.keyPress(KeyEvent.VK_TAB)
		robot.delay(25)
		robot.keyRelease(KeyEvent.VK_TAB)
	}
}
