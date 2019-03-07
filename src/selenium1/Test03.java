package selenium1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class Test03 extends Selbase{
	
	@BeforeMethod
	public void init() {
		System.out.println("before concrete class method");
	}
	
	@DataProvider(name="data")
	public Object[][] datap(){
		return new Object[][] {
			{"youtube"},
			{"facebook"}
		};
	}
	@Test(dataProvider="data")
	public void test(String data) {
		WebElement elem =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gs_taif0")));
		Actions action =  new Actions(driver);
		action.moveToElement(elem).sendKeys(elem,data).build().perform();
		action.pause(700L).build().perform();//suggestion appear 
		action.moveByOffset(20, 20).click().build().perform();
		action.pause(300L).build().perform();//suggestion disappear 
		elem =  driver.findElement(By.xpath("//input[@type='submit']"));
		wait.until(ExpectedConditions.visibilityOf(elem));
		action.moveToElement(elem).click().build().perform();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}