package gw

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
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
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

import internal.GlobalVariable

public class Table {
	/* ***************************Prints all the elements in the table******************************* 
	 * ***************************Returns all the elements in the table as a List******************************* */
	@Keyword
	def static List<String> getTableElements(WebElement Table){

		WebDriver driver = DriverFactory.getWebDriver()
		'To locate rows of table it will Capture all the rows available in the table'
		List<WebElement> rows_table = Table.findElements(By.tagName('tr'))
		List<String> elementsinTable = new ArrayList<>()
		'To calculate no of rows In table'
		int rows_count = rows_table.size()
		println("Row count is " + rows_count)

		'Loop will execute for all the rows of the table'

		for (int row = 0; row < rows_count; row++) {
			'To locate columns(cells) of that specific row'
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName('td'))

			'To calculate no of columns(cells) In that specific row'
			int columns_count = Columns_row.size()

			println((('Number of columns In Row ' + row) + ' are ') + columns_count)

			'Loop will execute till the last cell of that specific row'

			for (int column = 0; column < columns_count; column++) {
				'It will retrieve text from each cell'
				String celltext = Columns_row.get(column).getText()
				elementsinTable.add(celltext)
				println((((('Cell Value Of row number ' + row) + ' and column number ') + column) + ' Is ') + celltext)
			}
		}
		println(elementsinTable)
		return elementsinTable
	}

	/* ***************************Prints all the elements in the specified row of the table*******************************
	 * ***************************Returns all the elements in the specified row of the table as a List******************************* */
	@Keyword
	def static List<String> getTableElementsByRow(WebElement Table, int rowNumber){

		WebDriver driver = DriverFactory.getWebDriver()
		'To locate rows of table it will Capture all the rows available in the table'
		List<WebElement> rows_table = Table.findElements(By.tagName('tr'))
		List<String> elementsinTableRow = new ArrayList<>()
		'To calculate no of rows In table'
		int rows_count = rows_table.size()
		println("Row count is " + rows_count)

		'Loop will execute for all the rows of the table'

		for (int row = rowNumber; row < rowNumber+1; row++) {
			'To locate columns(cells) of that specific row'
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName('td'))

			'To calculate no of columns(cells) In that specific row'
			int columns_count = Columns_row.size()

			println((('Number of columns In Row ' + row) + ' are ') + columns_count)

			'Loop will execute till the last cell of that specific row'

			for (int column = 0; column < columns_count; column++) {
				'It will retrieve text from each cell'
				String celltext = Columns_row.get(column).getText()
				elementsinTableRow.add(celltext)
				println((((('Cell Value Of row number ' + row) + ' and column number ') + column) + ' Is ') + celltext)
			}
		}
		println(elementsinTableRow)
		return elementsinTableRow
	}

	/* ***************************Prints all the elements in the table column*******************************
	 * ***************************Returns all the elements in the table column as a List******************************* */
	@Keyword
	def static List<String> getTableElementsByColumn(WebElement Table, int columnNumber){

		WebDriver driver = DriverFactory.getWebDriver()
		'To locate rows of table it will Capture all the rows available in the table'
		List<WebElement> rows_table = Table.findElements(By.tagName('tr'))
		List<String> elementsinTableColumn = new ArrayList<>()
		'To calculate no of rows In table'
		int rows_count = rows_table.size()
		println("Row count is " + rows_count)

		'Loop will execute for all the rows of the table'

		for (int row = 0; row < rows_count; row++) {
			'To locate columns(cells) of that specific row'
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName('td'))

			'To calculate no of columns(cells) In that specific row'
			int columns_count = Columns_row.size()

			println((('Number of columns In Row ' + row) + ' are ') + columns_count)

			'Loop will execute till the last cell of that specific row'

			for (int column = columnNumber; column < columnNumber+1; column++) {
				'It will retrieve text from each cell'
				String celltext = Columns_row.get(column).getText()
				elementsinTableColumn.add(celltext)
				println((((('Cell Value Of row number ' + row) + ' and column number ') + column) + ' Is ') + celltext)
			}
		}
		println(elementsinTableColumn)
		return elementsinTableColumn
	}

	/* ***************************Prints all the elements in the table*******************************
	 * ***************************Returns all the elements in the table as a List******************************* */
	@Keyword
	def static Boolean getMatchingTableElement(WebElement Table, String matchingText){
		Boolean status = false
		WebDriver driver = DriverFactory.getWebDriver()
		'To locate rows of table it will Capture all the rows available in the table'
		List<WebElement> rows_table = Table.findElements(By.tagName('tr'))
		List<String> elementsinTable = new ArrayList<>()
		'To calculate no of rows In table'
		int rows_count = rows_table.size()
		println("Row count is " + rows_count)

		'Loop will execute for all the rows of the table'

		for (int row = 0; row < rows_count; row++) {
			'To locate columns(cells) of that specific row'
			List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName('td'))

			'To calculate no of columns(cells) In that specific row'
			int columns_count = Columns_row.size()

			println((('Number of columns In Row ' + row) + ' are ') + columns_count)

			'Loop will execute till the last cell of that specific row'

			for (int column = 0; column < columns_count; column++) {
				'It will retrieve text from each cell'
				String celltext = Columns_row.get(column).getText()
				elementsinTable.add(celltext)
				println((((('Cell Value Of row number ' + row) + ' and column number ') + column) + ' Is ') + celltext)

				'Checking if Cell text is matching with the expected value'
				if ((celltext.equalsIgnoreCase(matchingText))) {
					println('Matching element found ' + celltext + 'at Row ' + row + 'at Column ' + column )
					status = true
					break
				}
			}
		}
		return status
	}


	/**
	 * Get all rows of HTML table
	 * @param Table - Katalon test object to represent HTML table
	 * @param headerRowCount -  Number of header rows which will be excluded from the row count
	 * @return All rows inside HTML table
	 */
	@Keyword
	def static int getNumberofTableRows(WebElement Table, int headerRowCount){

		WebDriver driver = DriverFactory.getWebDriver()
		'To locate rows of table it will Capture all the rows available in the table'
		List<WebElement> rows_table = Table.findElements(By.tagName('tr'))
		'To calculate no of rows In table'
		int rows_count = rows_table.size()
		return rows_count-headerRowCount
	}
}
