package JASS.Pages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import JASS.Common.CommonMethods;

public class CreateAccountPage {
	private WebDriver driver;
	public CommonMethods CM;
	public static Logger logger = Logger.getLogger(CreateAccountPage.class);
	@FindBy(how = How.LINK_TEXT, using = "create an account")
	private WebElement CREATEANACCOUNT;

	@FindBy(how = How.NAME, using = "firstname")
	private WebElement FIRSTNAME;

	@FindBy(how = How.XPATH, using = "//*[@id=\'bodyContent\']/form/div/div[2]/table/tbody/tr[1]/td[2]/input[1]")
	private WebElement MGENDER;

	@FindBy(how = How.XPATH, using = "//*[@id=\"bodyContent\"]/form/div/div[2]/table/tbody/tr[1]/td[2]/input[2]")
	private WebElement FGENDER;

	@FindBy(how = How.NAME, using = "lastname")
	private WebElement LASTNAME;

	@FindBy(how = How.ID, using = "dob")
	private WebElement DOB;

	@FindBy(how = How.NAME, using = "email_address")
	private WebElement EMAIL;

	@FindBy(how = How.NAME, using = "company")
	private WebElement COMPANY;

	@FindBy(how = How.NAME, using = "street_address")
	private WebElement ADDRESS;

	@FindBy(how = How.NAME, using = "suburb")
	private WebElement SUBURB;

	@FindBy(how = How.NAME, using = "postcode")
	private WebElement ZIP;

	@FindBy(how = How.NAME, using = "city")
	private WebElement CITY;

	@FindBy(how = How.NAME, using = "state")
	private WebElement STATE;

	@FindBy(how = How.NAME, using = "country")
	private WebElement COUNTRY;

	@FindBy(how = How.NAME, using = "telephone")
	private WebElement CONTACT;

	@FindBy(how = How.NAME, using = "fax")
	private WebElement FAX;

	@FindBy(how = How.NAME, using = "newsletter")
	private WebElement NEWSLETTER;

	@FindBy(how = How.NAME, using = "password")
	private WebElement PASSWORD;

	@FindBy(how = How.NAME, using = "confirmation")
	private WebElement PCONFIRM;

	@FindBy(how = How.XPATH, using = "//*[@id=\"tdb4\"]/span[2]")
	private WebElement CONTINUEBTN;
	
	@FindBy(how=How.XPATH, using="//*[@id=\"bodyContent\"]/h1")
	private WebElement SUCCESSHEADING;
	
	@FindBy(how=How.XPATH, using="//*[@id=\"bodyContent\"]/table/tbody/tr/td")
	private WebElement ALERTMSG;

	public CreateAccountPage(WebDriver driver) {
		this.driver = driver;
		CM = new CommonMethods();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("Loading SIgnIN page...");
		PageFactory.initElements(this.driver, this);
	}

	public void signup(String gender, String fname, String lname, String dob, 
			String emailID, String cname,
			String saddress, String subrub, String zip, String city, String state, String country, String phone,
			String faxnum, String newsletter, String password, String pconfirm) {
		CM.click(CREATEANACCOUNT);
		if (gender.equalsIgnoreCase("Male")) {
			CM.click(MGENDER);
		} else if (gender.equalsIgnoreCase("Female")) {
			CM.click(FGENDER);
		} else {
			logger.info("Invalid gender input:" + gender);
		}
		if(fname!=null) {
	    CM.setValue(FIRSTNAME, fname);
		}
		if(lname!=null ) {
		CM.setValue(LASTNAME, lname);
		}
		if(dob!=null) {
		CM.setValue(DOB, dob);
		}
		if(emailID!=null) {
		CM.setValue(EMAIL, emailID);
		}
		if(cname!=null) {
		CM.setValue(COMPANY, cname);
		}
		if(saddress!=null) {
		CM.setValue(ADDRESS, saddress);
		}
		if(subrub!=null) {
		CM.setValue(SUBURB, subrub);
		}
		if(zip!=null) {
		CM.setValue(ZIP, zip);
		}
		if(city!=null) {
		CM.setValue(CITY, city);
		}
		if(state!=null) {
		CM.setValue(STATE, state);
		}
		Select selectbyname = new Select(COUNTRY);
		selectbyname.selectByVisibleText(country);
		if(phone!=null) {
		CM.setValue(CONTACT, phone);
		}
		if(faxnum!=null) {
		CM.setValue(FAX, faxnum);
		}
		if (newsletter != null && newsletter.equalsIgnoreCase("Y")) {
			CM.click(NEWSLETTER);
		}
		if(password!=null) {
		CM.setValue(PASSWORD, password);
		}
		if(pconfirm!=null) {
		CM.setValue(PCONFIRM, pconfirm);
		}
		CM.click(CONTINUEBTN);
		 
	}
	
	public void verifyExsitingAccountMessage(String sText) {
		String actualText=ALERTMSG.getText();
		  System.out.println("ExpectedText="+sText+"\n"+"ActualText="+actualText);
		  Assert.assertEquals(actualText.trim(),sText.trim());				
		
	}
	
    public void verifyNewAccountMessage(String sText) {
		String actualText=SUCCESSHEADING.getText();
		  System.out.println("ExpectedText="+sText+"\n"+"ActualText="+actualText);
		  Assert.assertEquals(actualText.trim(),sText.trim());
						
		
	}

}
