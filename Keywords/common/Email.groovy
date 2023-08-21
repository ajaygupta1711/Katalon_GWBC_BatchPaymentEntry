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

//import java.util.*
import javax.mail.*
import javax.mail.internet.*
import javax.activation.*
import com.kms.katalon.core.configuration.RunConfiguration
import java.io.File
import java.nio.file.Files

public class Email {
	//Excel iExcel = new Excel()

	def static void SendFileEmail(String to, String cc, String from, String host, String port, String subject){

		String projDir = RunConfiguration.getProjectDir()

		String reportFilePath = projDir + "/Reports/Report Summary.xlsx"
		String reportSheet = "Data"

		// Recipient's email ID needs to be mentioned.
		String[] tos = to.split(';')

		// Sender's email ID needs to be mentioned
		//String from = "web@gmail.com"

		// Assuming you are sending email from localhost
		//String host = "localhost"

		// Get system properties
		Properties properties = System.getProperties()

		// Setup mail server
		properties.setProperty("mail.smtp.host", host)

		properties.setProperty("mail.smtp.port", port)

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties)

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session)

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from))

			// Set To: header field of the header.
			for (str in tos) {
				println str
				message.addRecipient(Message.RecipientType.TO,new InternetAddress(str))
			}

			if(cc != null & cc != ''){
				String[] ccs = cc.split(';')

				for (str in ccs) {
					println str
					message.addRecipient(Message.RecipientType.CC,new InternetAddress(str))
				}
			}
			// Set Subject: header field
			message.setSubject(subject)

			// Create a multipar message
			Multipart multipart = new MimeMultipart()

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart()

			// Fill the message
			//messageBodyPart.setText("This is message body")

			// Send the actual HTML message, as big as you like
			//File content = new File(filePath)
			messageBodyPart.setContent(HTMLReport.GenerateReport(1), "text/html")
			//messageBodyPart.setContent(content.toString(), "text/html")

			// Part two is attachment -- QX 11/08 commented out to prevent attaching HTML report
			String filePath = Excel.ReadFromExcel(reportFilePath, reportSheet, 1, "ReportFilePath")
			//			String testSuiteName = Excel.ReadFromExcel(reportFilePath, reportSheet, 1, "SuiteName")
			//			String testEnv = Excel.ReadFromExcel(reportFilePath, reportSheet, 1, "EnvName")
			String failedCnt = Excel.ReadFromExcel(reportFilePath, reportSheet, 1, "Failed")
			if (filePath!="T") {
				String[] temp = filePath.split("\\\\")
				String fileName = temp[temp.length - 2]
				fileName = "Report_" + fileName + ".html"

				BodyPart fileBodyPart = new MimeBodyPart()
				DataSource source = new FileDataSource(filePath)
				fileBodyPart.setDataHandler(new DataHandler(source))
				fileBodyPart.setFileName(fileName)

				if (failedCnt !="0") {
					multipart.addBodyPart(fileBodyPart)
				}

			}

			// Set text message part
			multipart.addBodyPart(messageBodyPart)

			// Send the complete message parts
			message.setContent(multipart )

			// Send message
			Transport.send(message)
			System.out.println("Sent message successfully....")
		} catch (MessagingException mex) {
			mex.printStackTrace()
		}
	}
}
