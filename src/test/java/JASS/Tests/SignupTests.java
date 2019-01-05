package JASS.Tests;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
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
import JASS.Pages.CreateAccountPage;

public class SignupTests {
	private WebDriver driver;
	public String sURL = "http://107.170.213.234/catalog/index.php";
	public CommonMethods CM;
	public static  Logger logger= Logger.getLogger(SignupTests.class);
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

	@DataProvider(name = "userDataFromExcel")
	public static String[][] dataProviderFromExcel()
			throws EncryptedDocumentException, IOException, InvalidFormatException {
		String[][] userdata = ExcelFileReader.getData();
		return userdata;
	}

	@DataProvider(name = "newData")
	public static String[][] dataProviderNew() throws EncryptedDocumentException, IOException, InvalidFormatException {
		String[][] userdata = new String[1][17];
		int n = RandomUtils.nextInt(0, 2);
		if (n == 0) {
			userdata[0][0] = "Female";
		} else if (n == 1) {
			userdata[0][0] = "Male";
		}
		userdata[0][1] = "cha";
		userdata[0][2] = "kov";
		userdata[0][3] = "11/10/1990";
		userdata[0][4] = RandomStringUtils.randomAlphanumeric(10) + "@" + RandomStringUtils.randomAlphanumeric(5) + "."
				+ RandomStringUtils.randomAlphabetic(3);
		userdata[0][5] = "vbvb";
		userdata[0][6] = "gfgfgf";
		userdata[0][7] = "fff";
		userdata[0][8] = "45454";
		userdata[0][9] = "dublin";

		userdata[0][10] = "CA";
		userdata[0][11] = "United States";
		userdata[0][12] = "123456";
		userdata[0][13] = "2223";
		userdata[0][14] = "y";
		userdata[0][15] = "abcdeeg";
		userdata[0][16] = "abcdeeg";
		return userdata;

	}

	@Test(dataProvider = "userDataFromExcel")
	public void testAddExistingAccount(String gender, String fname, String lname, String dob, String emailID,
			String cname, String saddress, String subrub, String zip, String city, String state, String country,
			String phone, String faxnum, String newsletter, String password, String pconfirm) {

		CreateAccountPage CA = new CreateAccountPage(driver);
		CA.signup(gender, fname, lname, dob, emailID, cname, saddress, subrub, zip, city, state, country, phone, faxnum,
				newsletter, password, pconfirm);
		String message = "Your E-Mail Address already exists in our records - please log in with the e-mail address or create an account with a different address.";
		CA.verifyExsitingAccountMessage(message);

	}

	@Test(dataProvider = "newData")
	public void testAddNewAccount(String gender, String fname, String lname, String dob, String emailID, String cname,
			String saddress, String subrub, String zip, String city, String state, String country, String phone,
			String faxnum, String newsletter, String password, String pconfirm) {

		CreateAccountPage CA = new CreateAccountPage(driver);
		CA.signup(gender, fname, lname, dob, emailID, cname, saddress, subrub, zip, city, state, country, phone, faxnum,
				newsletter, password, pconfirm);
		String message = "Your Account Has Been Created!";
		CA.verifyNewAccountMessage(message);

	}

}
