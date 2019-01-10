package JASS.Common;


import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

//import io.github.bonigarcia.wdm.WebDriverManager;


public class CommonMethods {
	private WebDriver driver;
	public static  Logger logger= Logger.getLogger(CommonMethods.class);

	public WebDriver openBrowser(String remoteBrowserType) {
		if(remoteBrowserType==null) {
		 remoteBrowserType=ReadPropertyFile.getConfigPropertyVal("BrowserType");
		}
		if (remoteBrowserType.equalsIgnoreCase("Chrome")) {
			ClassLoader classLoader = CommonMethods.class.getClassLoader();
			File chromeFile = new File(classLoader.getResource("chromedriver").getFile());
			chromeFile.setExecutable(true, false);
			logger.info("webdriver.chrome.driver path=" + chromeFile.getAbsolutePath());
			System.setProperty("webdriver.chrome.driver", chromeFile.getAbsolutePath());
			//WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			logger.info("OPeninng the chrome");
		} else if (remoteBrowserType.equalsIgnoreCase("FireFox")) {
			ClassLoader classLoader = CommonMethods.class.getClassLoader();
			File firefoxFile = new File(classLoader.getResource("geckodriver").getFile());
			firefoxFile.setExecutable(true, false);
			logger.info("webdriver.gecko.driver path=" + firefoxFile.getAbsolutePath());
			System.setProperty("webdriver.gecko.driver", firefoxFile.getAbsolutePath());
			
			//WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			logger.info("Opening the Firefox");
		} else {
			Assert.fail("Please select browser");
		}
		// driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;

	}

	public void closeBrowser() {
		driver.quit();
		logger.info("close browser");
	}

	public void openUrl(String sURL) {
		driver.get(sURL);
		logger.info("Opening the URL");
	}

	public void setValue(WebElement slocator, String sValue) {
		String Element = slocator.getText();
		try {
			logger.info(Element + "trying to set the value");
			slocator.clear();
			slocator.sendKeys(sValue);
			logger.info(sValue + " entered");
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.info(Element + "field not found");
		}
	}
	
	

	public void click(WebElement slocator) {
		try {

			String Element = slocator.getText();
			if ((Element.isEmpty()) || (Element == null)) {
				Element = slocator.getAttribute("value");
			}
			logger.info(Element + " trying to click");

			slocator.click();
			logger.info(Element + " clicked ");

			// acceptPopup();
			// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.info(slocator + " not clicked ");
		}
	}

}
