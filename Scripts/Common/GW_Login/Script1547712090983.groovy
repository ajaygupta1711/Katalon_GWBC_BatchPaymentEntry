import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.firefox.internal.ProfilesIni
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.driver.WebUIDriverType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI


/*WebUIDriverType executedBrowser = DriverFactory.getExecutedBrowser()
switch(executedBrowser) {
	case WebUIDriverType.FIREFOX_DRIVER:          // "Firefox"
		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile FF = profile.getProfile("jmeterProfile");
		FirefoxOptions options = new FirefoxOptions().setProfile(FF);
		System.setProperty('webdriver.gecko.driver', DriverFactory.getGeckoDriverPath());
		WebDriver driver = new FirefoxDriver(options);
		DriverFactory.changeWebDriver(driver)
		break;
	case WebUIDriverType.CHROME_DRIVER:
		ChromeOptions options = new ChromeOptions();
		System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverPath());
		options.addArguments("disable-extensions-except=C:\\QA Scripts\\emc.guidewire.jmeter\\blazemeterExtension\\4.17.0_0");
		WebDriver driver = new ChromeDriver(options);
		DriverFactory.changeWebDriver(driver);
		System.out.println("Waiting for 20 seconds to set up blazemeter recording...");
		Thread.sleep(20000);
		System.out.println("Waiting over");
		break;
	default:
		WebUI.openBrowser('');
		break
}*/
WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GW_URL)

CustomKeywords.'gw.Text.SetText'(findTestObject('Common/pge_LoginScreen/txt_UserName'), UserName)

CustomKeywords.'gw.Text.SetText'(findTestObject('Common/pge_LoginScreen/txt_Password'), Password)

CustomKeywords.'gw.Actions.Click'(findTestObject('Common/pge_LoginScreen/btn_LogIn'))
