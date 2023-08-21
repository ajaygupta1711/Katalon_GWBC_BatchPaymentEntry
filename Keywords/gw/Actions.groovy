package gw

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
import com.kms.katalon.core.testobject.TestObjectXpath
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.thoughtworks.selenium.webdriven.commands.Click

import internal.GlobalVariable as GlobalVariable
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.WebElement
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

import org.openqa.selenium.By as By

public class Actions {

	/**
	 * Click on the given element. It will retry if any exception throws.
	 * @param to - represent a web element
	 */
	@Keyword
	def void Click(TestObject to) {

		try {
			WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)
			WebUI.waitForElementClickable(to, GlobalVariable.gWaitTimeOut)
			//WebUI.scrollToElement(to, GlobalVariable.gWaitTimeOut)
			WebUI.click(to)
		} catch (Exception e) {
			//WebUI.delay(GlobalVariable.delaySecond)
			Thread.sleep(GlobalVariable.gSleepTime)
			WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)
			WebUI.waitForElementClickable(to, GlobalVariable.gWaitTimeOut)
			WebUI.click(to)
			e.printStackTrace()
		}
	}

	/**
	 * Wait for page finished loading.
	 * @param to - represent a web element
	 */
	@Keyword
	def void WaitForPageLoading() {

		try {
			WebUI.waitForElementPresent(findTestObject('Common/pge_Common/wel_LoadIndicator'), GlobalVariable.gWaitTimeOut, FailureHandling.OPTIONAL)
		} catch (Exception e) {
			//WebUI.delay(GlobalVariable.delaySecond)
			WebUI.waitForElementPresent(findTestObject('Common/pge_Common/wel_LoadIndicator'), GlobalVariable.gWaitTimeOut, FailureHandling.OPTIONAL)
			e.printStackTrace()
		}
	}

	/**
	 * Select the option with the given label. It will retry if any exception throws.
	 * @param to - represent a web element
	 * @param option - displayed text of the options to select
	 */
	@Keyword
	def void SelectOption(TestObject to, String option) {
		if (option == null || option == "") return

			option = ".*" + option + ".*"
		try {
			WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)
			WebUI.selectOptionByLabel(to, option, true)
		} catch (Exception e) {
			//WebUI.delay(GlobalVariable.delaySecond)
			Thread.sleep(GlobalVariable.gSleepTime)
			WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)
			WebUI.selectOptionByLabel(to, option, true)
			e.printStackTrace()
		}
	}

	/**
	 * Select the option as though you type the value key-by-key, and send TAB key. It will retry if any exception throws.
	 * @param to - represent a web element
	 * @param option - displayed text of the options to select
	 */
	@Keyword
	def void SelectOptionAndEnter(TestObject to, String option) {
		if (option == null || option == "") return

			try {
				WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)
				WebUI.sendKeys(to, option)
				Thread.sleep(GlobalVariable.gSleepTime)
				common.SendKeys.SendEnter()
			} catch (Exception e) {
				//WebUI.delay(GlobalVariable.delaySecond)
				Thread.sleep(GlobalVariable.gSleepTime)
				WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)
				WebUI.sendKeys(to, option)
				Thread.sleep(GlobalVariable.gSleepTime)
				common.SendKeys.SendEnter()
				e.printStackTrace()
			}

		common.SendKeys.SendEnter()
		Thread.sleep(GlobalVariable.gSleepTime)
	}

	/**
	 * Click on the given element, and send TAB key on the element. It will retry if any exception throws.
	 * @param to - represent a web element
	 */
	@Keyword
	def void ClickAndTab(TestObject to) {
		Click(to)
		common.SendKeys.SendTab()
		Thread.sleep(GlobalVariable.gSleepTime)
	}

	/**
	 * Click on the given element, and send ENTER key on the element. It will retry if any exception throws.
	 * @param to - represent a web element
	 */
	@Keyword
	def void ClickAndEnter(TestObject to) {
		Click(to)
		common.SendKeys.SendEnter()
		Thread.sleep(GlobalVariable.gSleepTime)
	}
	//	@Keyword
	//	def void ClickAfterVisable(TestObject to) {
	//		try {
	//			WebUI.waitForElementVisible(to,GlobalVariable.gWaitTimeOut)
	//		} catch (Exception e) {
	//			//WebUI.waitForElementVisible(to,GlobalVariable.gWaitTimeOut)
	//			Thread.sleep(GlobalVariable.gSleepTime)
	//			e.printStackTrace()
	//		}
	//
	//		Click(to)
	//	}
	//
	//	@Keyword
	//	def void ClickAfterClickable(TestObject to) {
	//
	//		try {
	//			WebUI.waitForElementClickable(to,GlobalVariable.gWaitTimeOut)
	//		} catch (Exception e) {
	//			Thread.sleep(GlobalVariable.gSleepTime)
	//			e.printStackTrace()
	//		}
	//
	//		Click(to)
	//	}

	/**
	 * Use to navigate menuItem through not exact match.
	 * @param menuItems - menu links under the widget of contact, like "MenuItem1;MenuItem1"
	 */
	@Keyword
	def void NavigateMenuItems(String menuItems) {
		String[] items = menuItems.split(';')
		def len = items.length
		for (def i=0; i<len - 1; i++ ) {
			def menu = ObjectRepository.findTestObject('Common/pge_Common/wel_MenuItem_NotExact', [('item') : items[i]])
			Thread.sleep(GlobalVariable.gSleepTime)
			WebUI.mouseOver(menu)
		}
		def lastmenu = ObjectRepository.findTestObject('Common/pge_Common/wel_MenuItem_NotExact', [('item') : items[len-1]])
		Click(lastmenu)
	}

	/**
	 * Use the Actions menu to start an action.
	 * @param menuItems - menu links under the Actions, like "New Document;Create from a template"
	 */
	@Keyword
	def void NavigateByActions(String menuItems) {
		def actions = ObjectRepository.findTestObject("Common/pge_Common/wel_Actions")
		//		Thread.sleep(GlobalVariable.gSleepTime)
		//		WebUI.click(actions)
		Click(actions)

		/*String[] items = menuItems.split(';')
		 for (item in items) {
		 def menu = ObjectRepository.findTestObject('Common/pge_Common/wel_MenuItem', [('item') : item])
		 //			Thread.sleep(GlobalVariable.gSleepTime)
		 //			WebUI.click(menu)
		 Click(menu)
		 }*/
		String[] items = menuItems.split(';')
		def len = items.length
		for (def i=0; i<len - 1; i++ ) {
			def menu = ObjectRepository.findTestObject('Common/pge_Common/wel_MenuItem', [('item') : items[i]])
			Thread.sleep(GlobalVariable.gSleepTime)
			WebUI.mouseOver(menu)
		}
		def lastmenu = ObjectRepository.findTestObject('Common/pge_Common/wel_MenuItem', [('item') : items[len-1]])
		Click(lastmenu)
	}

	/**
	 * Use the Actions menu to create an exposure.
	 * @param menuItems - menu links under the Actions, like "Choose by Coverage;Policy Level Coverage"
	 */
	@Keyword
	def void NavigateByClaimActions(String menuItems) {
		def actions = ObjectRepository.findTestObject("Common/pge_Common/wel_Actions")
		//		Thread.sleep(GlobalVariable.gSleepTime)
		//		WebUI.click(actions)
		Click(actions)

		/*String[] items = menuItems.split(';')
		 for (item in items) {
		 def menu = ObjectRepository.findTestObject('Common/pge_Common/wel_MenuItem', [('item') : item])
		 //			Thread.sleep(GlobalVariable.gSleepTime)
		 //			WebUI.click(menu)
		 Click(menu)
		 }*/
		String[] items = menuItems.split(';')
		def len = items.length
		for (def i=0; i<len - 1; i++ ) {
			def menu = ObjectRepository.findTestObject('ClaimCenter/pge_Common/wel_MenuItem', [('item') : items[i]])
			Thread.sleep(GlobalVariable.gSleepTime)
			WebUI.mouseOver(menu)
		}
		def lastmenu = ObjectRepository.findTestObject('ClaimCenter/pge_Common/wel_MenuItem', [('item') : items[len-1]])
		Click(lastmenu)
	}
	//	@Keyword
	//	def void NavigateBySideMenu(String menuItem) {
	//		def menu = ObjectRepository.findTestObject("Common/pge_Common/wel_SideMenu", [('item') : menuItem])
	//		//		Thread.sleep(GlobalVariable.gSleepTime)
	//		//		WebUI.click(menu)
	//		Click(menu)
	//	}

	/**
	 * Use the Sidebar menu links to navigate to different pages.
	 * @param menuItems - menu links in the Sidebar, like "Monitoring;Message Queues"
	 */
	@Keyword
	def void NavigateBySideMenus(String menuItems) {
		String[] items = menuItems.split(';')
		for (item in items) {
			def menu = ObjectRepository.findTestObject('Common/pge_Common/wel_MenuItem', [('item') : item])
			Click(menu)
		}
	}

	/**
	 * Use the menu links from the tab to start an action or navigate to different pages.
	 * @param tabName name of the tab, like "Contact"
	 * @param menuItems - menu links from the tab, like "New Contact;New Company"
	 */

	@Keyword
	def void NavigateByTabMenu(String tabName, String menuItems) {
		def tab = ObjectRepository.findTestObject("Common/pge_Common/wel_TabBar", [('item') : tabName])
		//WebUI.delay(GlobalVariable.gDelaysecond)
		WebUI.clickOffset(tab, 40, 0)

		String[] items = menuItems.split(';')
		for (item in items) {
			def menu = ObjectRepository.findTestObject('Common/pge_Common/wel_MenuItem', [('item') : item])
			//			WebUI.delay(GlobalVariable.delaySecond)
			//			WebUI.click(menu)
			Click(menu)
		}
	}

	@Keyword
	def void NavigateByTabMenus(String tabName, String menuItems) {
		//		def tab = ObjectRepository.findTestObject("Common/pge_Common/wel_TabBar", [('item') : tabName])
		//		Thread.sleep(GlobalVariable.gSleepTime)
		//		WebUI.clickOffset(tab, 80, 0)

		def tab = ObjectRepository.findTestObject("Common/pge_Common/wel_TabToggle", [('item') : tabName])


		if(WebUI.verifyElementVisible(tab, FailureHandling.OPTIONAL))
			Click(tab)

		if(!WebUI.verifyElementVisible(tab, FailureHandling.OPTIONAL)){
			Click(findTestObject('Common/pge_Common/wel_TabsMoreOptions'))
			def menu = ObjectRepository.findTestObject('Common/pge_Common/wel_MenuItem', [('item') : tabName])
			WebUI.mouseOver(menu)
			Thread.sleep(GlobalVariable.gSleepTime)
		}

		String[] items = menuItems.split(';')
		def len = items.length
		for (def i=0; i<len - 1; i++ ) {
			def menu = ObjectRepository.findTestObject('Common/pge_Common/wel_MenuItem', [('item') : items[i]])
			Thread.sleep(GlobalVariable.gSleepTime)
			WebUI.mouseOver(menu)
		}
		def lastmenu = ObjectRepository.findTestObject('Common/pge_Common/wel_MenuItem', [('item') : items[len-1]])
		Click(lastmenu)
	}

	@Keyword
	def void NavigateByTab(String tabName) {
		def tab = ObjectRepository.findTestObject("Common/pge_Common/wel_TabBar", [('item') : tabName])
		//		WebUI.waitForElementClickable(tab,GlobalVariable.gWaitTimeOut)
		//		WebUI.click(tab)
		Click(tab)
	}

	@Keyword
	def void NavigateBySubTab(String tabName) {
		def tab = ObjectRepository.findTestObject("Common/pge_Common/wel_SubTab", [('item') : tabName])
		//		WebUI.waitForElementClickable(tab,GlobalVariable.gWaitTimeOut)
		//		WebUI.click(tab)
		Click(tab)
	}

	@Keyword
	def void SelectRadioByLabelValue(String label,String value){
		if(value == null || value == "")  return

			Thread.sleep(GlobalVariable.gSleepTime)

		def tab = ObjectRepository.findTestObject('Common/pge_Common/wrg_Generic_Radio', [('label'): label, ('value') : value ] )

		Click(tab)
	}


	@Keyword
	def void SelectTableItemByRefColumn(TestObject TableObject,String RefColHeader,String RefColValue,String ActionColHeader){
		//String obj_xpath,String RefColHeader,String RefColValue,String ActionColHeader
		//def TableObject = ObjectRepository.findTestObject('PolicyCenter/pge_AccountSummary/wtb_OpenTransactions')

		WebDriver driver = DriverFactory.getWebDriver()
		WebElement Table = WebUiCommonHelper.findWebElement(TableObject, 30)

		//driver.findElement(By.xpath(obj_xpath))
		List<WebElement> Rows = Table.findElements(By.tagName('tr'))
		println('No. of rows: ' + Rows.size())
		def FoundRowIndex = -1
		def RefColIndex = -1
		def ActionColumnIndex = -1
		def lblnClicked = false
		table: for (int i = 0; i < Rows.size(); i++) {
			List<WebElement> Cols = Rows.get(i).findElements(By.tagName('td'))
			println('No. of Cols: ' + Cols.size())
			if(i==0){

				for (int k = 0; k < Cols.size(); k++) {
					Cols.get(k).getText()
					if (Cols.get(k).getText().equalsIgnoreCase(RefColHeader)) {
						RefColIndex = k
						println('Ref col index is ' + RefColIndex )
					}
					if (Cols.get(k).getText().equalsIgnoreCase(ActionColHeader) && ActionColumnIndex == -1) {
						ActionColumnIndex = k
						println('Action col index is ' + ActionColumnIndex )
					}
				}
			}


			COLSLoop:for (int j = 0; j < Cols.size(); j++) {
				Cols.get(j).getText()
				if (Cols.get(j).getText().equalsIgnoreCase(RefColValue)) {

					println(Cols.get(ActionColumnIndex).getText())

					String Linkpath = Cols.get(ActionColumnIndex).getAttribute("xpath") + '/descendant::div[last()]'
					println(Linkpath)
					try{
						Cols.get(ActionColumnIndex).findElement(By.xpath('./descendant::div[1]')).click()
						lblnClicked = true
						COLSLoop: break
					}
					catch (Exception e) {
						println("Child Element not found")
					}
					try{
						Cols.get(ActionColumnIndex).findElement(By.xpath('./descendant::div[last()]')).click()
						lblnClicked = true
						COLSLoop: break
					}
					catch (Exception e) {
						println("Child Element not found")
					}
					try{
						Cols.get(ActionColumnIndex).findElement(By.xpath('./descendant::div[last()][1]')).click()
						lblnClicked = true
						COLSLoop: break
					}
					catch (Exception e) {
						println("Child Element not found")
					}
				}

				//println('Column Data : ' + Cols.get(j).getText())
			}

			if(lblnClicked==true) table: break
		}

	}


	@Keyword
	def void SelectTableActionByRefColumn(TestObject TableObject,String RefColHeader,String RefColValue,String ActionColHeader,String ActionMenu){
		//String obj_xpath,String RefColHeader,String RefColValue,String ActionColHeader
		//def TableObject = ObjectRepository.findTestObject('PolicyCenter/pge_AccountSummary/wtb_OpenTransactions')

		WebDriver driver = DriverFactory.getWebDriver()
		WebElement Table = WebUiCommonHelper.findWebElement(TableObject, 30)

		//driver.findElement(By.xpath(obj_xpath))
		List<WebElement> Rows = Table.findElements(By.tagName('tr'))
		println('No. of rows: ' + Rows.size())
		def FoundRowIndex = -1
		def RefColIndex = -1
		def ActionColumnIndex = -1
		def lblnClicked = false
		table: for (int i = 0; i < Rows.size(); i++) {
			List<WebElement> Cols = Rows.get(i).findElements(By.tagName('td'))
			if(i==0){

				for (int k = 0; k < Cols.size(); k++) {
					Cols.get(k).getText()
					if (Cols.get(k).getText().equalsIgnoreCase(RefColHeader)) {
						RefColIndex = k
					}
					if (Cols.get(k).getText().equalsIgnoreCase(ActionColHeader)) {
						ActionColumnIndex = k
					}
				}
			}


			COLSLoop:for (int j = 0; j < Cols.size(); j++) {
				Cols.get(j).getText()
				if (Cols.get(j).getText().equalsIgnoreCase(RefColValue)) {

					println(Cols.get(ActionColumnIndex).getText())

					String Linkpath = Cols.get(ActionColumnIndex).getAttribute("xpath") + '/descendant::div[contains(text(),"'+ActionMenu+'")][last()]'
					println(Linkpath)
					Cols.get(ActionColumnIndex).findElement(By.xpath('./descendant::div[1]')).click()
					WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)

					Cols.get(ActionColumnIndex).findElement(By.xpath('./descendant::div[contains(text(),"'+ActionMenu+'")]')).click()
					lblnClicked = true

					COLSLoop: break
				}

				//println('Column Data : ' + Cols.get(j).getText())
			}

			if(lblnClicked==true) table: break
		}

	}

	@Keyword

	def void TableClickWithPages(TestObject TableObject,TestObject NextPageBtn,String RefColHeader,String RefColValue,String ActionColHeader){
		WebDriver driver = DriverFactory.getWebDriver()
		WebElement Table = WebUiCommonHelper.findWebElement(TableObject, 30)

		List<WebElement> Rows = Table.findElements(By.tagName('tr'))

		def FoundRowIndex = -1
		def RefColIndex = -1
		def ActionColumnIndex = -1
		def lblnClicked = false
		def lblnNextPageAvailable = true
		while(lblnClicked ==false && lblnNextPageAvailable == true){
			table: for (int i = 0; i < Rows.size(); i++){
				List<WebElement> Cols = Rows.get(i).findElements(By.tagName('td'))
				if(i==0){

					for (int k = 0; k < Cols.size(); k++) {
						Cols.get(k).getText()
						if (Cols.get(k).getText().equalsIgnoreCase(RefColHeader)) {
							RefColIndex = k
						}
						if (Cols.get(k).getText().equalsIgnoreCase(ActionColHeader)) {
							ActionColumnIndex = k
						}
					}
				}

				else {
					println(Cols.size())
					COLSLoop:for (int j = 0; j < Cols.size(); j++) {
						println(Cols.get(j).getText())
						if (Cols.get(j).getText().equalsIgnoreCase(RefColValue)) {

							println(Cols.get(ActionColumnIndex).getText())

							String Linkpath = Cols.get(ActionColumnIndex).getAttribute("xpath") + '/descendant::div[last()]'
							println(Linkpath)
							Cols.get(ActionColumnIndex).findElement(By.xpath('./descendant::div[last()]')).click()
							println('clicked')
							Thread.sleep(3000)
							lblnClicked = true

							COLSLoop: break
						}

						//println('Column Data : ' + Cols.get(j).getText())
					}

				}

				if(lblnClicked==true) table: break

				if(i == Rows.size()-1) {
					if(WebUI.verifyElementClickable(NextPageBtn, FailureHandling.OPTIONAL)) {

						WebUI.click(NextPageBtn)
						Thread.sleep(GlobalVariable.gSleepTime)
						Table = WebUiCommonHelper.findWebElement(TableObject, 30)
						Rows = Table.findElements(By.tagName('tr'))
					}
					else {
						lblnNextPageAvailable = false
					}
				}
			}
		}

	}

	@Keyword

	def void SetCheckBoxValue(TestObject TO,String YesOrNo){
		def CurrentStatus = "No"
		if(WebUI.verifyElementChecked(TO, GlobalVariable.gWaitTimeOut,FailureHandling.OPTIONAL)) {
			CurrentStatus = "Yes"
		}
		if(! CurrentStatus.equalsIgnoreCase(YesOrNo)){
			WebUI.click(TO)
			WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)
		}
	}

	@Keyword

	def void SelectMultipleItems(TestObject TO,String SelectionItems){

		if (SelectionItems == null || SelectionItems == "") return

			WebUI.deselectAllOption(TO)

		String[] EachSelectItem = SelectionItems.split(';')
		for (item in EachSelectItem) {
			WebUI.selectOptionByLabel(TO, item, false)
			//SelectOption(TO, EachSelectItem[item])
			Thread.sleep(GlobalVariable.gSleepTime)
			WebUI.waitForPageLoad(GlobalVariable.gWaitTimeOut)
		}

	}

	//	@Keyword
	//	def void ClickCheckbox(String PolicyCondition) {
	//		def tab = ObjectRepository.findTestObject("Common/pge_Common/wel_Checkbox", [('condition') : PolicyCondition])
	//		WebUI.clickOffset(tab, 10, 10)
	//	}
	//
	//	@Keyword
	//	def void ClickYes(String PolicyQuestion) {
	//		TestObject tab = ObjectRepository.findTestObject("Common/pge_Common/btn_Yes", [('Question') : PolicyQuestion])
	//		Click(tab)
	//		Click(tab)
	//	}
	//
	//	@Keyword
	//	def void ClickNo(String PolicyQuestion) {
	//		TestObject tab = ObjectRepository.findTestObject("Common/pge_Common/btn_No", [('Question') : PolicyQuestion])
	//		Click(tab)
	//		Click(tab)
	//	}
	//
	//	@Keyword
	//	def void SelectLocation(String LocationNumber) {
	//		TestObject tab = ObjectRepository.findTestObject("PolicyCenter/pge_JobWizard/pge_Locations/btn_SelectLocation", [('Number') : LocationNumber])
	//		Click(tab)
	//	}

	//	@Keyword
	//	def void SelectInstallmentPlan(String InstallmentPlan) {
	//		if (InstallmentPlan == null || InstallmentPlan == "") return
	//			TestObject tab = ObjectRepository.findTestObject("PolicyCenter/pge_JobWizard/pge_Payment/wel_Select", [('InstallmentPlan') : InstallmentPlan])
	//		Click(tab)
	//	}
	//
	//	@Keyword
	//	def void SelectState(String State) {
	//		TestObject tab = ObjectRepository.findTestObject("PolicyCenter/pge_JobWizard/pge_WCCoverages/wel_State", [('State') : State])
	//		Click(tab)
	//	}
	//	@Keyword
	//	def void NavigateByActions_2(String menuItems) {
	//		def actions = ObjectRepository.findTestObject("Common/pge_Common/wel_Actions")
	//		//		Thread.sleep(GlobalVariable.gSleepTime)
	//		//		WebUI.click(actions)
	//		Click(actions)
	//
	//		/*String[] items = menuItems.split(';')
	//		 for (item in items) {
	//		 def menu = ObjectRepository.findTestObject('Common/pge_Common/wel_MenuItem', [('item') : item])
	//		 //			Thread.sleep(GlobalVariable.gSleepTime)
	//		 //			WebUI.click(menu)
	//		 Click(menu)
	//		 }*/
	//		String[] items = menuItems.split(';')
	//		def len = items.length
	//		for (def i=0; i<len - 1; i++ ) {
	//			def menu = ObjectRepository.findTestObject('Common/pge_Common/wel_MenuItem_2', [('item') : items[i]])
	//			Thread.sleep(GlobalVariable.gSleepTime)
	//			WebUI.mouseOver(menu)
	//		}
	//		def lastmenu = ObjectRepository.findTestObject('Common/pge_Common/wel_MenuItem_2', [('item') : items[len-1]])
	//		Click(lastmenu)
	//	}
}

