package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Stoppage {
	WebDriver driver;
	private By stopNumber = By.xpath("//html/body/form/div[3]/div[3]/div[1]/div[1]/div[1]/div[3]");
		
	
	public Stoppage(WebDriver driver) {
		
		this.driver=driver;
		
	}

	public WebElement getstopNumber()
	{
		return driver.findElement(stopNumber);
	}
	
}
