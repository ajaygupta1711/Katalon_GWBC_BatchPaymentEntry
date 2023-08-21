package gw
import java.util.Date

import com.kms.katalon.core.annotation.Keyword

import java.text.SimpleDateFormat
import java.lang.StringBuilder

public class TaxID {
	/**
	 * Generate a tax ID
	 * @return TaxID
	 */
	def static String TaxIDGenerator(){
		sleep(1000)
		Date currentTime = new Date()
		SimpleDateFormat sdf = new SimpleDateFormat("DHHmmss")
		String TaxID = sdf.format(currentTime)
		TaxID = AddLeadingZeros(TaxID)

		return TaxID;
	}

	/**
	 * Generate a tax id with FEIN format
	 * @return FEIN
	 */
	def static String FEINGenerator(){
		sleep(1000)
		Date currentTime = new Date()
		SimpleDateFormat sdf = new SimpleDateFormat("DHHmmss")
		String FEIN = sdf.format(currentTime)
		FEIN = AddLeadingZeros(FEIN)

		StringBuilder sb = new StringBuilder(FEIN)
		sb.insert(2,"-")
		FEIN = sb.toString()
		println("Newly generated FEIN: " + FEIN)

		return FEIN;
	}

	/**
	 * Generate a tax id with SSN format
	 * @return SSN
	 */
	def static String SSNGenerator(){
		sleep(1000)
		Date currentTime = new Date()
		SimpleDateFormat sdf = new SimpleDateFormat("DHHmmss")
		String SSN = sdf.format(currentTime)
		SSN = AddLeadingZeros(SSN)

		StringBuilder sb = new StringBuilder(SSN)
		sb.insert(3,"-")
		sb.insert(6,"-")
		SSN = sb.toString()
		println("Newly generated SSN: " + SSN)

		return SSN;
	}

	def static String AddLeadingZeros(String text){
		String newText
		int len = text.length()

		switch(len) {
			case 7:
				newText = "00"+text
				break
			case 8:
				newText = "0"+text
				break
			default:
				newText = text
		}
		return newText
	}
}