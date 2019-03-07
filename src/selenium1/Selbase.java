package selenium1;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


public abstract class Selbase{
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected JavascriptExecutor js;
	
	@BeforeSuite
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		ChromeOptions op =  new ChromeOptions();
		op.addArguments("start-maximized");
		op.addArguments("disable-infobars");
		driver =  new ChromeDriver(op);
		wait =  new WebDriverWait(driver, 20);
		js =  ((JavascriptExecutor)driver);
	}
	
	@AfterSuite
	public void cleanUp() {
		driver.quit();
		js = null;
		wait = null;
		driver= null;
		System.gc();
	}
	
	@BeforeMethod(alwaysRun=true)
	public void startPoint() {
		driver.navigate().to("https://www.google.com");
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return js.executeScript("return document.readyState").equals("complete");
			}
		});
	}
	
}