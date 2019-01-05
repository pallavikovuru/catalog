package JASS.Pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import JASS.Common.CommonMethods;

public class BuyProductspage {
	private WebDriver driver;
	public CommonMethods CM;
	public static Logger logger = Logger.getLogger(BuyProductspage.class);

	@FindBy(how = How.XPATH, using = "//*[@id='bodyContent']/div/div[2]/table")
	private WebElement TABLEITEMS;
	
	@FindBy(how = How.XPATH, using = "//*[@id='bodyContent']/form/div[2]/div[2]/span[1]/span/button/span[2]")
	private WebElement ADDCART;
	
	@FindBy(how = How.XPATH, using = "//*[@id='bodyContent']/form/div/div[2]/span/span/a/span[2]")
	private WebElement CHECKOUT;
	
	@FindBy(how = How.XPATH, using = "//*[@id='tdb6']/span[2]")
	private WebElement CONTINUE;
	
	@FindBy(how = How.XPATH, using = "//*[@id='bodyContent']/form/div/div[4]/table[1]/tbody/tr/td[2]")
	private WebElement CASHCLICK;
	
	@FindBy(how = How.XPATH, using = "//*[@id='tdb5']/span[2]")
	private WebElement CONFIRMORDER;
	
	@FindBy(how = How.XPATH, using = "//*[@id='bodyContent']/h1")
	private WebElement CONFIRMMESSAGE;
		
	
	public BuyProductspage(WebDriver driver) {
		this.driver = driver;
		CM = new CommonMethods();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("Loading SIgnIN page...");
		PageFactory.initElements(this.driver, this);
	}

	public void buyItems() {
		List<WebElement> allRows = TABLEITEMS.findElements(By.tagName("tr"));
		int random = RandomUtils.nextInt(1, allRows.size());
		WebElement item=null;;
		for (int i = 0; i < allRows.size(); i++) {
			WebElement row = allRows.get(i);
			List<WebElement> tds = row.findElements(By.tagName("td"));
			for (int j = 0; j < tds.size(); j++) {
				logger.info(tds.get(j).getText());

			}
			if (random == i) {
				item = tds.get(0);

			}

		}
		
		item.click();
		ADDCART.click();
		CHECKOUT.click();
		CONTINUE.click();
		CASHCLICK.click();
		CONTINUE.click();
		CONFIRMORDER.click();
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
	public void verifyOrder(String eMsg) {
		String ActuvalMsg=CONFIRMMESSAGE.getText();
		logger.info("From browser:" +ActuvalMsg);
		Assert.assertEquals(ActuvalMsg.trim(), eMsg.trim());
		logger.info("ActuvalMessage:" + ActuvalMsg +  "  ExpectedMessage:" + eMsg);
		
	}

}
