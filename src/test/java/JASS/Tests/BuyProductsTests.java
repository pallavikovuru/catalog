package JASS.Tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import JASS.Common.CommonMethods;
import JASS.Common.ScreenCapture;
import JASS.Pages.BuyProductspage;
import JASS.Pages.SignInPage;
import JASS.Pages.WelcomePage;
import org.testng.ITestResult;

public class BuyProductsTests {
	private WebDriver driver;
	public String sURL = "http://107.170.213.234/catalog/index.php";
	public CommonMethods CM;
	public static  Logger logger= Logger.getLogger(BuyProductsTests.class);
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
	
	@Test
	public void buy() throws Exception {
		WelcomePage WP = new WelcomePage(driver);
		WP.clickYourSelfLink();
		SignInPage SI = new SignInPage(driver);
		SI.Login("ecalix@test.com","test123");
		BuyProductspage bp=new BuyProductspage(driver);
		bp.buyItems();
		String ExpectedMsg="Your Order Has Been Processed!";
		bp.verifyOrder(ExpectedMsg);
	}
	
	
	

}
