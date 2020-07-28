package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Directionpage {
	
	WebDriver driver;
	private By stoplinks = By.xpath("//div[@class='CTALinkContainer']");
	private By specificstoplink = By.linkText("UBC Exchange Bay 7");
	
	
	public Directionpage(WebDriver driver) {
		
		this.driver=driver;
		
	}
	
	public Stoppage getstoppage(int i)
	{
		driver.findElements(stoplinks).get(i).findElement(By.tagName("a")).click();
		return new Stoppage(driver);
	}
	
	public List<WebElement> getstoplinks()
	{
		return driver.findElements(stoplinks);
	}
	
	
	public WebElement getspecificstoplink()
	{
		return driver.findElement(specificstoplink);
	}
	

}
