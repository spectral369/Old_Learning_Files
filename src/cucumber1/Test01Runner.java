package cucumber1;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(JUnit4.class)
@CucumberOptions(features = { "src/cucumber1" }, plugin = { "pretty", "html:target/html_report_cucumber" }, glue = {
		"cucumber1" }, monochrome = true)

public class Test01Runner extends AbstractTestNGCucumberTests {

	protected static WebDriver driver;
	protected static WebDriverWait wait;
	protected static JavascriptExecutor js;

	@BeforeTest
	public void Setup() {
		System.setProperty("webdriver.driver.chrome", "chromedriver");
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--start-maximized");
		op.addArguments("--disable-infobars");
		driver = new ChromeDriver(op);
		wait = new WebDriverWait(driver, 20);
		js = ((JavascriptExecutor) driver);
	}

	@BeforeMethod(alwaysRun = true)
	public void reset() {
		driver.navigate().to("https:///www.google.com");
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return js.executeScript("return document.readyState").equals("complete");
			}
		});
	}

	@AfterTest
	public void cleanUp() {
		driver.quit();
		js = null;
		wait = null;
		driver = null;
		System.gc();

	}

}
