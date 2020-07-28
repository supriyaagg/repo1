package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Routepage {
	WebDriver driver;
	private By addfavIcon = By.xpath("/html/body/form/div[4]/div/div[5]");
	private By addfavEditbox = By.cssSelector("textarea[name=newFavourite]");
	private By addButton = By.cssSelector("button[name=btnAddFav]");
	private By myfavIcon = By.cssSelector("div[id=myFav_tab]");
	private By routetitle = By.xpath("//div[@class='txtRouteTitle']");
	private By dest = By.xpath("//div[@class='CTALinkContainer']/a");
	
	public Routepage(WebDriver driver) {
		
		this.driver=driver;
		
	}
	
	public Directionpage getdestinationclick(int i)
	{
		driver.findElements(dest).get(i).click();
		return new Directionpage(driver);
	}
	
	public Myfavpage getmyfav()
	{
		driver.findElement(myfavIcon).click();
		return new Myfavpage(driver)	;
	}

	public WebElement getaddfavIcon()
	{
		return driver.findElement(addfavIcon);
	}
	public WebElement getaddfavEditbox()
	{
		return driver.findElement(addfavEditbox);
	}
	public WebElement getaddButton()
	{
		return driver.findElement(addButton);
	}
	public WebElement getmyfavIcon()
	{
		return driver.findElement(myfavIcon);
	}
	
	public WebElement getrouteTitle()
	{
		return driver.findElement(routetitle);
	}
	
	public List<WebElement> getdestinations()
	{
		return driver.findElements(dest);
	}
	
}
