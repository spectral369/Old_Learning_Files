package cucumber1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Test01 {

	private WebDriver driver;
	private WebDriverWait wait;
	@SuppressWarnings("unused")
	private JavascriptExecutor js;

	public Test01() {
		driver = Test01Runner.driver;
		wait = Test01Runner.wait;
		js = Test01Runner.js;
	}

	@Test
	@Given("^I am on google page$")
	public void toGooglePage() {
		Assert.assertTrue(driver.getTitle().contains("oogle"));
	}

	@Test
	@When("^I enter a search \"(.+)\"$")
	public void enterSearchTerm(String term) {
		WebElement elem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text']")));
		Actions action = new Actions(driver);
		action.moveToElement(elem).click(elem).sendKeys(elem, term).build().perform();

	}

	@Test
	@When("^I click Search$")
	public void ClickTheSearchButton() {
		Actions action = new Actions(driver);
		action.pause(700);
		action.moveByOffset(20, 20).click().build().perform();
		action.pause(300);
		WebElement elem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
		action.moveToElement(elem).click().build().perform();
	}

	@Test
	@Then("^I should see searched page$")
	public void weShouldSeeSearchedPage() {
		Assert.assertTrue(driver.getTitle().contains("e"));
	}

}
