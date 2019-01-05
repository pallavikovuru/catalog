package JASS.Pages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import JASS.Common.CommonMethods;

public class SearchPage {
	private WebDriver driver;
	public CommonMethods CM;
	public static  Logger logger= Logger.getLogger(SearchPage.class);
	
	 @FindBy(how=How.NAME, using="keywords")
	  private WebElement SEARCH;
	 
	 @FindBy(how=How.XPATH, using="//*[@id=\"columnLeft\"]/div[3]/div[2]/form/input[3]")
	  private WebElement CLICKBTN;
	 
	  @FindBy(how=How.XPATH, using="//*[@id=\"bodyContent\"]/div/div[1]/p")
	  private WebElement NORESULTSMESSAGE;
	  
	  @FindBy(how=How.XPATH, using="//*[@id=\"bodyContent\"]/div/div[1]/div[2]/span[2]")
	  private WebElement SUCCESSRESULTMSG;
	  
	  
	

public SearchPage(WebDriver driver){
	this.driver = driver;
	CM = new CommonMethods();
	this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	logger.info("Loading WelcomePage page..."); 
	PageFactory.initElements(this.driver, this);
}

 public void search(String key) {
	 CM.setValue(SEARCH, key);
	 logger.info("going to click....");
	//Object returnObj = ((JavascriptExecutor)driver).executeScript("arguments[0].click();", CLICKBTN);
	 
	 SEARCH.sendKeys(Keys.RETURN);

	logger.info(SEARCH + " clicked");
	
	 //CM.click(CLICKBTN);

 }
 public void validateNoResultsSearch(String rText ) {
		String ActualMessage=NORESULTSMESSAGE.getText();
		 Assert.assertEquals(ActualMessage.trim(),rText.trim());
		logger.info("ExpectedText="+rText+"\n"+"ActualText="+ActualMessage);
	}
 
	public void validateSucessfulSearch( ) {
		String ActualMessage=SUCCESSRESULTMSG.getText();
		 Assert.assertNotNull(ActualMessage);
		logger.info(ActualMessage);
	}
	
	

}