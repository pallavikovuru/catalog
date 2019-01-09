package JASS.Pages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import JASS.Common.CommonMethods;

public class WelcomePage {
	private WebDriver driver;
	public CommonMethods CM;
	public static Logger logger = Logger.getLogger(WelcomePage.class);

	@FindBy(how = How.LINK_TEXT, using = "log yourself in")
	private WebElement LOGYOURSELFIN;

	@FindBy(how = How.XPATH, using = "//*[@id='bodyContent']/h1")
	private WebElement HEADER;

	@FindBy(how = How.XPATH, using = "//*[@id=\"bodyContent\"]/table/tbody/tr/td")
	private WebElement ERRORMSG;

	@FindBy(how = How.XPATH, using = "//*[@id='tdb4']/span")
	private WebElement LOGOFF;

	public WelcomePage(WebDriver driver) {
		this.driver = driver;
		CM = new CommonMethods();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		logger.info("Loading WelcomePage page...");
		PageFactory.initElements(this.driver, this);
	}

	public void clickYourSelfLink() {
		CM.click(LOGYOURSELFIN);

	}

	public void Logoff() {
		CM.click(LOGOFF);
	}

	public void verifyHeader(String sText) {

		String ActualText = HEADER.getText();

		logger.info("ExpectedText=" + sText + "\n" + "ActualText=" + ActualText);
		Assert.assertEquals(sText.trim(), ActualText.trim());
	}

	public void verifyError(String rText) {
		String ActualText = ERRORMSG.getText();
		logger.info("ExpectedText=" + rText + "\n" + "ActualText=" + ActualText);
		// Assert.assertTrue(ActualText.contains(rText));
		Assert.assertEquals(rText.trim(), ActualText.trim());
	}
}