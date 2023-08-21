
/**
 * This class is generated automatically by Katalon Studio and should not be modified or deleted.
 */

import com.kms.katalon.core.testobject.TestObject

import java.lang.String

import org.openqa.selenium.WebElement


/**
	 * Verify if given web element is visible.
	 * @param to - represent a web element
	 * @param timeout - system will wait at most timeout (seconds) to return result
	 * @return true if the element is present and visible; otherwise, false
	 */
def static "gw.Assertion.VerifyVisible"(
    	TestObject to	
     , 	int timeout	) {
    (new gw.Assertion()).VerifyVisible(
        	to
         , 	timeout)
}


def static "gw.Assertion.VerifyLabelValue"(
    	String key	
     , 	String value	) {
    (new gw.Assertion()).VerifyLabelValue(
        	key
         , 	value)
}


def static "gw.Assertion.VerifyLabelValue"(
    	String sectionName	
     , 	String key	
     , 	String value	) {
    (new gw.Assertion()).VerifyLabelValue(
        	sectionName
         , 	key
         , 	value)
}


def static "gw.Assertion.GetTableRowSpecificContent"(
    	TestObject TableObject	
     , 	String RefColHeader	
     , 	String RefValue	) {
    (new gw.Assertion()).GetTableRowSpecificContent(
        	TableObject
         , 	RefColHeader
         , 	RefValue)
}


def static "gw.Assertion.VerifyTableContentsWithPage"(
    	TestObject TableObject	
     , 	TestObject NextPage	
     , 	TestObject FirstPage	
     , 	String ColName	
     , 	String CSV_Data	) {
    (new gw.Assertion()).VerifyTableContentsWithPage(
        	TableObject
         , 	NextPage
         , 	FirstPage
         , 	ColName
         , 	CSV_Data)
}


def static "gw.Assertion.VerifyTableContents"(
    	TestObject TableObject	
     , 	String ColName	
     , 	String CSV_Data	) {
    (new gw.Assertion()).VerifyTableContents(
        	TableObject
         , 	ColName
         , 	CSV_Data)
}

/**
	 * Verify text of an element.
	 * @param to - represent a web element
	 * @param text - text of the element to verify.
	 * @return true if the element is present and visible; otherwise, false
	 */
def static "gw.Assertion.VerifyText"(
    	TestObject to	
     , 	String text	) {
    (new gw.Assertion()).VerifyText(
        	to
         , 	text)
}


def static "gw.Assertion.VerifyPartialText"(
    	TestObject to	
     , 	String textregExp	) {
    (new gw.Assertion()).VerifyPartialText(
        	to
         , 	textregExp)
}

/**
	 * Set the value of an input field, as though you type it in. It will retry if any exception throws.
	 * @param to represent a web element
	 * @param text the text to type
	 */
def static "gw.Text.SetText"(
    	TestObject to	
     , 	String text	) {
    (new gw.Text()).SetText(
        	to
         , 	text)
}

/**
	 * Set the value of an input field, as though you type the value key-by-key. It will retry if any exception throws.
	 * @param to represent a web element
	 * @param text the text to type
	 */
def static "gw.Text.TypeText"(
    	TestObject to	
     , 	String text	) {
    (new gw.Text()).TypeText(
        	to
         , 	text)
}

/**
	 * Simulates keystroke events on the specified element, as though you typed the value key-by-key. It will retry if any exception throws.
	 * @param to - represent a web element
	 * @param strKey - the combination of keys to type
	 */
def static "gw.Text.SendKey"(
    	TestObject to	
     , 	String strKey	) {
    (new gw.Text()).SendKey(
        	to
         , 	strKey)
}


def static "gw.Text.EnterValue"(
    	String sectionName	
     , 	String key	
     , 	String value	
     , 	String type	) {
    (new gw.Text()).EnterValue(
        	sectionName
         , 	key
         , 	value
         , 	type)
}

/**
	 * Set the value of an input field, as though you type it in. And send TAB key on the input filed.
	 * @param to represent a web element
	 * @param text the text to type
	 */
def static "gw.Text.SetTextAndTab"(
    	TestObject to	
     , 	String text	) {
    (new gw.Text()).SetTextAndTab(
        	to
         , 	text)
}

/**
	 * Set the value of an input field, as though you type it in. And send ENTER key on the input filed.
	 * @param to represent a web element
	 * @param text the text to type
	 */
def static "gw.Text.SetTextAndEnter"(
    	TestObject to	
     , 	String text	) {
    (new gw.Text()).SetTextAndEnter(
        	to
         , 	text)
}

/**
	 * Set the value of an input field, as though you type the value key-by-key. And send ENTER key on the input filed.
	 * @param to represent a web element
	 * @param text the text to type
	 */
def static "gw.Text.GetPolicyNumber"(
    	String PolicyString	) {
    (new gw.Text()).GetPolicyNumber(
        	PolicyString)
}


def static "gw.Text.TypeTextAndEnter"(
    	TestObject to	
     , 	String text	) {
    (new gw.Text()).TypeTextAndEnter(
        	to
         , 	text)
}


def static "gw.Text.TypeTextAndTab"(
    	TestObject to	
     , 	String text	) {
    (new gw.Text()).TypeTextAndTab(
        	to
         , 	text)
}

/**
	 * Set the value of FEIN/SSN field, as though you type it in. Type a automatically generated FEIN/SSN if no value received. 
	 * @param to represent the web element of FEIN/SSN
	 * @param text the text to type
	 */
def static "gw.Text.SetTaxID"(
    	TestObject to	
     , 	String text	) {
    (new gw.Text()).SetTaxID(
        	to
         , 	text)
}


def static "gw.Text.SetSSN"(
    	TestObject to	
     , 	String text	) {
    (new gw.Text()).SetSSN(
        	to
         , 	text)
}


def static "gw.Text.SetEIN"(
    	TestObject to	
     , 	String text	) {
    (new gw.Text()).SetEIN(
        	to
         , 	text)
}

/**
	 * Set the value of an input field from a spreadsheet based on column name , as though you type it in. Send ENTER key on the input filed after set the value.
	 * @param to represent the web element
	 * @param dataFileName name of the Data File which bound with the spreadsheet
	 * @param sheetName sheet name of the data the spreadsheet
	 * @param columnName column name of the data in the spreadsheet
	 */
def static "gw.Text.SetTextFromExcel"(
    	TestObject to	
     , 	String dataFileName	
     , 	String sheetName	
     , 	String columnName	) {
    (new gw.Text()).SetTextFromExcel(
        	to
         , 	dataFileName
         , 	sheetName
         , 	columnName)
}

/**
	 * Set the value of an input field from a spreadsheet based on column name & row number , as though you type it in. Send ENTER key on the input filed after set the value.
	 * @param to represent the web element
	 * @param dataFileName name of the Data File which bound with the spreadsheet
	 * @param sheetName sheet name of the data the spreadsheet
	 * @param columnName column name of the data in the spreadsheet
	 * @param rowNumber row number of the data the spreadsheet
	 */
def static "gw.Text.SetTextFromExcel"(
    	TestObject to	
     , 	String dataFileName	
     , 	String sheetName	
     , 	String cloumnName	
     , 	String rowNumber	) {
    (new gw.Text()).SetTextFromExcel(
        	to
         , 	dataFileName
         , 	sheetName
         , 	cloumnName
         , 	rowNumber)
}

/**
	 * Set the value of an input field from a spreadsheet based on column name , as though you type the value key-by-key. Send ENTER key on the input filed after set the value.
	 * @param to represent the web element
	 * @param dataFileName name of the Data File which bound with the spreadsheet
	 * @param sheetName sheet name of the data the spreadsheet
	 * @param columnName column name of the data in the spreadsheet
	 */
def static "gw.Text.TypeTextFromExcel"(
    	TestObject to	
     , 	String dataFileName	
     , 	String sheetName	
     , 	String cloumnName	) {
    (new gw.Text()).TypeTextFromExcel(
        	to
         , 	dataFileName
         , 	sheetName
         , 	cloumnName)
}

/**
	 * Set the value of an input field from a spreadsheet based on column name & row number , as though you type the value key-by-key. Send ENTER key on the input filed after set the value.
	 * @param to represent the web element
	 * @param dataFileName name of the Data File which bound with the spreadsheet
	 * @param sheetName sheet name of the data the spreadsheet
	 * @param columnName column name of the data in the spreadsheet
	 * @param rowNumber row number of the data the spreadsheet
	 */
def static "gw.Text.TypeTextFromExcel"(
    	TestObject to	
     , 	String dataFileName	
     , 	String sheetName	
     , 	String cloumnName	
     , 	String rowNumber	) {
    (new gw.Text()).TypeTextFromExcel(
        	to
         , 	dataFileName
         , 	sheetName
         , 	cloumnName
         , 	rowNumber)
}

/**
	 * Get the visible innerText of the web element, and save the value into a spreadsheet based on column name 
	 * @param to represent the web element
	 * @param dataFileName name of the Data File which bound with the spreadsheet
	 * @param sheetName sheet name of the data the spreadsheet
	 * @param columnName column name of the data in the spreadsheet
	 */
def static "gw.Text.GetTextAndSave"(
    	TestObject to	
     , 	String dataFileName	
     , 	String sheetName	
     , 	String columnName	) {
    (new gw.Text()).GetTextAndSave(
        	to
         , 	dataFileName
         , 	sheetName
         , 	columnName)
}

/**
	 * Get the visible innerText of the web element, and save the value into a spreadsheet based on column name & row number 
	 * @param to represent the web element
	 * @param dataFileName name of the Data File which bound with the spreadsheet
	 * @param sheetName sheet name of the data the spreadsheet
	 * @param columnName column name of the data in the spreadsheet
	 * @param rowNumber row number of the data the spreadsheet
	 */
def static "gw.Text.GetTextAndSave"(
    	TestObject to	
     , 	String dataFileName	
     , 	String sheetName	
     , 	String columnName	
     , 	String rowNumber	) {
    (new gw.Text()).GetTextAndSave(
        	to
         , 	dataFileName
         , 	sheetName
         , 	columnName
         , 	rowNumber)
}


def static "gw.Text.SaveTextToExcel"(
    	String text	
     , 	String dataFileName	
     , 	String sheetName	
     , 	String columnName	
     , 	String rowNumber	) {
    (new gw.Text()).SaveTextToExcel(
        	text
         , 	dataFileName
         , 	sheetName
         , 	columnName
         , 	rowNumber)
}


def static "gw.Text.SaveTextToCSV"(
    	String text	
     , 	String dataFileName	
     , 	String sheetName	
     , 	String columnName	
     , 	String rowNumber	) {
    (new gw.Text()).SaveTextToCSV(
        	text
         , 	dataFileName
         , 	sheetName
         , 	columnName
         , 	rowNumber)
}


def static "gw.Text.GetClaimAndSave"(
    	TestObject to	
     , 	String dataFileName	
     , 	String sheetName	
     , 	String columnName	
     , 	String rowNumber	) {
    (new gw.Text()).GetClaimAndSave(
        	to
         , 	dataFileName
         , 	sheetName
         , 	columnName
         , 	rowNumber)
}


def static "gw.Text.SetSelection"(
    	TestObject to	
     , 	String text	) {
    (new gw.Text()).SetSelection(
        	to
         , 	text)
}


def static "gw.Text.uploadFile"(
    	TestObject to	
     , 	String filePath	) {
    (new gw.Text()).uploadFile(
        	to
         , 	filePath)
}


def static "common.Excel.getRowNumbers"(
    	String filePath	
     , 	String sheetName	
     , 	String cellContent	) {
    (new common.Excel()).getRowNumbers(
        	filePath
         , 	sheetName
         , 	cellContent)
}


def static "common.Excel.getRowNumbers"(
    	String filePath	
     , 	String sheetName	
     , 	String columnName	
     , 	String cellContent	) {
    (new common.Excel()).getRowNumbers(
        	filePath
         , 	sheetName
         , 	columnName
         , 	cellContent)
}


def static "common.Excel.WirteToExcelHyperlink"(
    	String filePath	
     , 	String sheetName	
     , 	String hyperAddress	
     , 	int iRowNumber	
     , 	String columnName	
     , 	String text	) {
    (new common.Excel()).WirteToExcelHyperlink(
        	filePath
         , 	sheetName
         , 	hyperAddress
         , 	iRowNumber
         , 	columnName
         , 	text)
}


def static "common.Excel.WirteToExcelLocationHyperlink"(
    	String filePath	
     , 	String sheetName	
     , 	String hyperAddress	
     , 	String hyperAddressLocation	
     , 	int iRowNumber	
     , 	String columnName	
     , 	String text	) {
    (new common.Excel()).WirteToExcelLocationHyperlink(
        	filePath
         , 	sheetName
         , 	hyperAddress
         , 	hyperAddressLocation
         , 	iRowNumber
         , 	columnName
         , 	text)
}


def static "common.Excel.RemoveValuesFromExcelCell"(
    	String filePath	
     , 	String sheetName	
     , 	int iRowNumber	
     , 	String iColName	) {
    (new common.Excel()).RemoveValuesFromExcelCell(
        	filePath
         , 	sheetName
         , 	iRowNumber
         , 	iColName)
}


def static "common.Excel.insertFirstColumns"(
    	String excelFile	
     , 	String excelSheet	
     , 	int insertColNum	) {
    (new common.Excel()).insertFirstColumns(
        	excelFile
         , 	excelSheet
         , 	insertColNum)
}


def static "common.Excel.duplicateExcelColHeader"(
    	String excelFile	
     , 	String excelSheet	) {
    (new common.Excel()).duplicateExcelColHeader(
        	excelFile
         , 	excelSheet)
}


def static "common.Excel.compareExcelData"(
    	String dataFileName	
     , 	String sumFileName	
     , 	String sumSheetName	
     , 	String excelFile1	
     , 	String excelSheet1	
     , 	String excelFile2	
     , 	String excelSheet2	
     , 	String dataColor	
     , 	String headColor	) {
    (new common.Excel()).compareExcelData(
        	dataFileName
         , 	sumFileName
         , 	sumSheetName
         , 	excelFile1
         , 	excelSheet1
         , 	excelFile2
         , 	excelSheet2
         , 	dataColor
         , 	headColor)
}


def static "common.Excel.ifExistsInExcelData"(
    	String dataFileName	
     , 	String sumFileName	
     , 	String sumSheetName	
     , 	String excelFile1	
     , 	String excelSheet1	
     , 	String excelFile2	
     , 	String excelSheet2	
     , 	String dataColor	
     , 	String headColor	) {
    (new common.Excel()).ifExistsInExcelData(
        	dataFileName
         , 	sumFileName
         , 	sumSheetName
         , 	excelFile1
         , 	excelSheet1
         , 	excelFile2
         , 	excelSheet2
         , 	dataColor
         , 	headColor)
}


def static "common.Excel.copyExcelSheetByIndex"(
    	String excelFile	
     , 	int copyIndex	
     , 	String copySheetName	) {
    (new common.Excel()).copyExcelSheetByIndex(
        	excelFile
         , 	copyIndex
         , 	copySheetName)
}


def static "common.Excel.copyExcelSheetByName"(
    	String excelFile	
     , 	String copySourceSheet	
     , 	String copyTargetSheet	) {
    (new common.Excel()).copyExcelSheetByName(
        	excelFile
         , 	copySourceSheet
         , 	copyTargetSheet)
}


def static "common.Excel.writeToExcel"(
    	String filePath	
     , 	String sheetName	
     , 	int rowIndex	
     , 	int columnIndex	
     , 	String value	) {
    (new common.Excel()).writeToExcel(
        	filePath
         , 	sheetName
         , 	rowIndex
         , 	columnIndex
         , 	value)
}

/**
	 * Execute non-query (usually INSERT/UPDATE/DELETE/COUNT/SUM...) on database
	 * @param queryString a SQL statement
	 * @return single value result of SQL statement
	 */
def static "common.DBConnection.execute"(
    	String queryString	) {
    (new common.DBConnection()).execute(
        	queryString)
}

/**
	 * Click on the given element. It will retry if any exception throws.
	 * @param to - represent a web element
	 */
def static "gw.Actions.Click"(
    	TestObject to	) {
    (new gw.Actions()).Click(
        	to)
}

/**
	 * Wait for page finished loading.
	 * @param to - represent a web element
	 */
def static "gw.Actions.WaitForPageLoading"() {
    (new gw.Actions()).WaitForPageLoading()
}

/**
	 * Select the option with the given label. It will retry if any exception throws.
	 * @param to - represent a web element
	 * @param option - displayed text of the options to select
	 */
def static "gw.Actions.SelectOption"(
    	TestObject to	
     , 	String option	) {
    (new gw.Actions()).SelectOption(
        	to
         , 	option)
}

/**
	 * Select the option as though you type the value key-by-key, and send TAB key. It will retry if any exception throws.
	 * @param to - represent a web element
	 * @param option - displayed text of the options to select
	 */
def static "gw.Actions.SelectOptionAndEnter"(
    	TestObject to	
     , 	String option	) {
    (new gw.Actions()).SelectOptionAndEnter(
        	to
         , 	option)
}

/**
	 * Click on the given element, and send TAB key on the element. It will retry if any exception throws.
	 * @param to - represent a web element
	 */
def static "gw.Actions.ClickAndTab"(
    	TestObject to	) {
    (new gw.Actions()).ClickAndTab(
        	to)
}

/**
	 * Click on the given element, and send ENTER key on the element. It will retry if any exception throws.
	 * @param to - represent a web element
	 */
def static "gw.Actions.ClickAndEnter"(
    	TestObject to	) {
    (new gw.Actions()).ClickAndEnter(
        	to)
}

/**
	 * Use to navigate menuItem through not exact match.
	 * @param menuItems - menu links under the widget of contact, like "MenuItem1;MenuItem1"
	 */
def static "gw.Actions.NavigateMenuItems"(
    	String menuItems	) {
    (new gw.Actions()).NavigateMenuItems(
        	menuItems)
}

/**
	 * Use the Actions menu to start an action.
	 * @param menuItems - menu links under the Actions, like "New Document;Create from a template"
	 */
def static "gw.Actions.NavigateByActions"(
    	String menuItems	) {
    (new gw.Actions()).NavigateByActions(
        	menuItems)
}

/**
	 * Use the Actions menu to create an exposure.
	 * @param menuItems - menu links under the Actions, like "Choose by Coverage;Policy Level Coverage"
	 */
def static "gw.Actions.NavigateByClaimActions"(
    	String menuItems	) {
    (new gw.Actions()).NavigateByClaimActions(
        	menuItems)
}

/**
	 * Use the Sidebar menu links to navigate to different pages.
	 * @param menuItems - menu links in the Sidebar, like "Monitoring;Message Queues"
	 */
def static "gw.Actions.NavigateBySideMenus"(
    	String menuItems	) {
    (new gw.Actions()).NavigateBySideMenus(
        	menuItems)
}

/**
	 * Use the menu links from the tab to start an action or navigate to different pages.
	 * @param tabName name of the tab, like "Contact"
	 * @param menuItems - menu links from the tab, like "New Contact;New Company"
	 */
def static "gw.Actions.NavigateByTabMenu"(
    	String tabName	
     , 	String menuItems	) {
    (new gw.Actions()).NavigateByTabMenu(
        	tabName
         , 	menuItems)
}


def static "gw.Actions.NavigateByTabMenus"(
    	String tabName	
     , 	String menuItems	) {
    (new gw.Actions()).NavigateByTabMenus(
        	tabName
         , 	menuItems)
}


def static "gw.Actions.NavigateByTab"(
    	String tabName	) {
    (new gw.Actions()).NavigateByTab(
        	tabName)
}


def static "gw.Actions.NavigateBySubTab"(
    	String tabName	) {
    (new gw.Actions()).NavigateBySubTab(
        	tabName)
}


def static "gw.Actions.SelectRadioByLabelValue"(
    	String label	
     , 	String value	) {
    (new gw.Actions()).SelectRadioByLabelValue(
        	label
         , 	value)
}


def static "gw.Actions.SelectTableItemByRefColumn"(
    	TestObject TableObject	
     , 	String RefColHeader	
     , 	String RefColValue	
     , 	String ActionColHeader	) {
    (new gw.Actions()).SelectTableItemByRefColumn(
        	TableObject
         , 	RefColHeader
         , 	RefColValue
         , 	ActionColHeader)
}


def static "gw.Actions.SelectTableActionByRefColumn"(
    	TestObject TableObject	
     , 	String RefColHeader	
     , 	String RefColValue	
     , 	String ActionColHeader	
     , 	String ActionMenu	) {
    (new gw.Actions()).SelectTableActionByRefColumn(
        	TableObject
         , 	RefColHeader
         , 	RefColValue
         , 	ActionColHeader
         , 	ActionMenu)
}


def static "gw.Actions.TableClickWithPages"(
    	TestObject TableObject	
     , 	TestObject NextPageBtn	
     , 	String RefColHeader	
     , 	String RefColValue	
     , 	String ActionColHeader	) {
    (new gw.Actions()).TableClickWithPages(
        	TableObject
         , 	NextPageBtn
         , 	RefColHeader
         , 	RefColValue
         , 	ActionColHeader)
}


def static "gw.Actions.SetCheckBoxValue"(
    	TestObject TO	
     , 	String YesOrNo	) {
    (new gw.Actions()).SetCheckBoxValue(
        	TO
         , 	YesOrNo)
}


def static "gw.Actions.SelectMultipleItems"(
    	TestObject TO	
     , 	String SelectionItems	) {
    (new gw.Actions()).SelectMultipleItems(
        	TO
         , 	SelectionItems)
}


def static "gw.Table.getTableElements"(
    	WebElement Table	) {
    (new gw.Table()).getTableElements(
        	Table)
}


def static "gw.Table.getTableElementsByRow"(
    	WebElement Table	
     , 	int rowNumber	) {
    (new gw.Table()).getTableElementsByRow(
        	Table
         , 	rowNumber)
}


def static "gw.Table.getTableElementsByColumn"(
    	WebElement Table	
     , 	int columnNumber	) {
    (new gw.Table()).getTableElementsByColumn(
        	Table
         , 	columnNumber)
}


def static "gw.Table.getMatchingTableElement"(
    	WebElement Table	
     , 	String matchingText	) {
    (new gw.Table()).getMatchingTableElement(
        	Table
         , 	matchingText)
}

/**
	 * Get all rows of HTML table
	 * @param Table - Katalon test object to represent HTML table
	 * @param headerRowCount -  Number of header rows which will be excluded from the row count
	 * @return All rows inside HTML table
	 */
def static "gw.Table.getNumberofTableRows"(
    	WebElement Table	
     , 	int headerRowCount	) {
    (new gw.Table()).getNumberofTableRows(
        	Table
         , 	headerRowCount)
}
