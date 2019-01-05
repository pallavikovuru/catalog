package JASS.Pages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import JASS.Common.CommonMethods;
import JASS.Common.ScreenCapture;

public class SignInPage {
	private WebDriver driver;
	public CommonMethods CM;
	public static  Logger logger= Logger.getLogger(SignInPage.class);
	  
	  @FindBy(how=How.NAME, using="email_address")
	  private WebElement EMAILADDRESS;
	  
	  @FindBy(how=How.NAME, using="password")
	  private WebElement PWD;
	  
	  @FindBy(how=How.XPATH, using="//*[@id='tdb5']/span[2]")
	  private WebElement SIGNIN;
	
	  
	  

	  
	  public SignInPage(WebDriver driver){
			this.driver = driver;
			CM = new CommonMethods();
			this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			logger.info("Loading SIgnIN page..."); 
			PageFactory.initElements(this.driver, this);
	  }
	  

		
		public void Login(String sUserNamae, String sPWD) throws Exception{
			CM.setValue(EMAILADDRESS,sUserNamae);
			CM.setValue(PWD,sPWD);  
			
			ScreenCapture ScreenCapture=new ScreenCapture(driver);
			ScreenCapture.takeScreenShoot();
			
			CM.click(SIGNIN);
		}
		
		
				
	
}

