package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Myfavpage {

		WebDriver driver;
		private By favlinks = By.xpath("//form[@class='textForm']//li/a");
		private By specificlink = By.linkText("Translink code test");
		
		
		public Myfavpage(WebDriver driver) {
			
			this.driver=driver;
			
		}

		public List<WebElement> getfavlinks()
		{
			return driver.findElements(favlinks);
		}
		
		public WebElement getspecificlink()
		{
			return driver.findElement(specificlink);
		}
		
		
}
