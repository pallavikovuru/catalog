package JASS.Tests;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import JASS.Common.CommonMethods;
import JASS.Common.ExcelFileReader;
import JASS.Common.ScreenCapture;
import JASS.Pages.SignInPage;
import JASS.Pages.WelcomePage;

public class LoginTests {

	private WebDriver driver;
	public String sURL = "http://107.170.213.234/catalog/index.php";
	public CommonMethods CM;
	public static  Logger logger= Logger.getLogger(LoginTests.class);
	public static String bType; 
	
	@BeforeClass
	@Parameters({"remoteBrowserType"})
	public void getBrowser(String browserType ) {
		bType=browserType;
		
	}
	
	@BeforeMethod
	public void setUp() {
		
		CM = new CommonMethods();
		driver = CM.openBrowser(bType);
		CM.openUrl(sURL);
	}

	@AfterMethod
	public void tearDown(ITestResult it) throws Exception {
		ScreenCapture ScreenCapture=new ScreenCapture(driver);
		ScreenCapture.takeScreenShoot(it.getMethod());
		logger.info("screenshot captured for: " +it.getMethod()+ " Failed TestCase");
		
		CM.closeBrowser();
	}

	@DataProvider(name = "userData")
	public static String[][] dataProvider() throws EncryptedDocumentException, IOException, InvalidFormatException {
		String[][] userdata = ExcelFileReader.getInput();
		return userdata;
	}

	@Test(dataProvider = "userData")
	public void testcase02(String userName, String password) throws Exception {
		WelcomePage WP = new WelcomePage(driver);
		WP.clickYourSelfLink();
		SignInPage SI = new SignInPage(driver);
		
		SI.Login(userName, password);
		WP.verifyHeader("Welcome to iBusiness");
		WP.Logoff();

	}
	
}