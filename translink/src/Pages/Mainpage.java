package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Mainpage {

	WebDriver driver;
	private By nextBusTab = By.cssSelector("button[id=next-bus]");
	private By searchText = By.cssSelector("input[name=NextBusSearchTerm]");
	private By findButton = By.xpath("//div[@class='flexContainer verticallyCenteredContent contentItem']//button");
		
	public Mainpage(WebDriver driver) {
		
		this.driver=driver;
		
	}

	public Routepage getFind()
	{
		driver.findElement(findButton).click();
		return new Routepage(driver);
	}
	public WebElement getSearchText()
	{
		return driver.findElement(searchText);
	}
	public WebElement getNextTab()
	{
		return driver.findElement(nextBusTab);
	}
	
	public WebElement getFindButton()
	{
		return driver.findElement(findButton);
	}
}


