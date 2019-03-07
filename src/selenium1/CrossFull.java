package selenium1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossFull {
	
	WebDriver driver  =  null;
	WebDriverWait wait  = null;
	JavascriptExecutor js =  null;
	
	
	
	@BeforeTest
	@Parameters("browser")
	public void setup(String browser) {
		//change to switch
		if(browser.equalsIgnoreCase("FF")) {
			
			System.setProperty("webdriver.gecko.driver","geckodriver");
			FirefoxOptions op =  new FirefoxOptions();
			
			driver =  new FirefoxDriver(op);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			wait =  new WebDriverWait(driver, 20);
			js = ((JavascriptExecutor)driver);
			
		}else if(browser.equalsIgnoreCase("Chrome")) {
		System.setProperty("wedriver.driver.chrome", "chromedriver");
		ChromeOptions op =  new ChromeOptions();
		op.addArguments("--disable-infobars");
		op.addArguments("--start-maximized");
		driver  =  new ChromeDriver(op);
		wait =  new WebDriverWait(driver, 20);
		js =  ((JavascriptExecutor) driver);
		}else if(browser.equalsIgnoreCase("IE")) {
			if(System.getProperty("os.name").toLowerCase().contains("nix")) {
				throw new IllegalAccessError("*Nix OS, IE not available(unless you use Wine)");
			}else if(System.getProperty("os.name").toLowerCase().contains("dos")){
				//to be done
				throw new IllegalStateException("not yet implemented");
			}else throw new Error("OS not supported..yet");
		}else {
			throw new IllegalArgumentException("Bad browser args");
		}
	}
	
	@AfterTest
	public void cleanup() {
		driver.quit();
		js =  null;
		wait =  null;
		driver =  null;
		System.gc();
	}
	
	@BeforeMethod(alwaysRun =  true)
	public void reset() {
		driver.navigate().to("https://www.google.com");
		driver.manage().window().maximize();//only for ff
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return js.executeScript("return document.readyState").equals("complete");
			}
		});
	}
	
	
	@DataProvider(name="data")
	public Object[][] data(){
		return new Object[][]{
			{"facebook"},
			{"youtube"}
		};
	}
	
	
	@Test(dataProvider="data")
	public void test01(String str) {
		WebElement elem =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text']")));
		Actions actions =  new Actions(driver);
		actions.moveToElement(elem).click(elem).sendKeys(elem, str).build().perform();;
		elem =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@type='submit']")));
		actions.moveToElement(elem).click(elem).pause(1000).build().perform();
		System.out.println(driver.getTitle());
	}
	
	
	

}
