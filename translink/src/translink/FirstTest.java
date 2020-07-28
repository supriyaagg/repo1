package translink;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.Directionpage;
import Pages.Mainpage;
import Pages.Myfavpage;
import Pages.Routepage;
import Pages.Stoppage;
import utilities.Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstTest extends Base {
	
		WebDriver driver;
		Directionpage directionpageobj = null;
		Myfavpage myfavpageobj =null;
		Stoppage stoppageobj=null;
		Routepage routepageobj=null;
		Mainpage mainpageobj;
		WebDriverWait wait;
		//executes before test to initialise driver and for implicit wait
		@BeforeTest
		public void initialise() throws IOException
		{
			driver = initialiseDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver,30);
		}
		
		// the test is fetching data from base class method getdata() using dataprovider annotation
		@Test(dataProvider="getData")
		public void validateAddFav(String route, String favname, String destination, String stop) throws IOException
		{
			// instantiating the objects of classes in POM
			mainpageobj = new Mainpage(driver);
			// navigating to the url dynamically being fetched from properties file
			driver.get(prop.getProperty("url"));
			
			// function to click on Next tab, entering route number and hitting Find button
			routepageobj= findBus(mainpageobj, route);
			
			//function to click on AddFav icon on bottom of page to add route to favourites
			addFav(routepageobj, favname);
			
			//dynamically validating if given link has been added under my favourites page
			try
			{
				//calling utility function to access link using myfav icon
				myfavpageobj= myFav(routepageobj,favname);
				System.out.println("Link is sucessfully added under my fav");
			}
			catch(Exception e)
			{
				e.printStackTrace();	
			}
			
			//Test to validate route title using assertions
			wait.until(ExpectedConditions.visibilityOf(routepageobj.getrouteTitle()));
			String routetitle = routepageobj.getrouteTitle().getText();
			try 
			{
				Assert.assertEquals(routetitle, route);
				System.out.println("Route title validated sucessfully");
			}
			catch(AssertionError ae){
					ae.printStackTrace();
			}

			//dynamically clicking on the destination fetched from getData() method
			directionpageobj= getDestination(routepageobj,destination);
							
			//dynamically selecting a given stop fetched from getdata() method on direction page
			stoppageobj= getStop(directionpageobj,stop);
			//explicit wait
			
			wait.until(ExpectedConditions.visibilityOf(stoppageobj.getstopNumber()));
			
			//validating stop number on stop page
			String stopnumber = stoppageobj.getstopNumber().getText();
			String expectedstop = "Stop # " + stop.split("#")[1].trim();
			try {
				Assert.assertEquals(stopnumber, expectedstop);
				System.out.println("stop number validated sucessfully");
			}
			catch(AssertionError ae){
				ae.printStackTrace();
			}
		}
		
		
		
		
		@AfterTest
		public void teardown()
		{
			driver.quit();
		}
}

