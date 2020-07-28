package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.DataProvider;

import Pages.Directionpage;
import Pages.Mainpage;
import Pages.Myfavpage;
import Pages.Routepage;
import Pages.Stoppage;


public class Base {
	
	//declaring global variables
	public WebDriver driver;
	public Properties prop;
	
	//method to initialise driver
	public WebDriver initialiseDriver() throws IOException
	{	
		//creating object of properties class to access global parameters in properties file 
		prop = new Properties();
		//creating object of file input stream 
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\resources\\properties");
		prop.load(fis);
		 //code to open browser specific instance of driver
		if(prop.getProperty("browser").contains("chrome"))
		{
			System.out.println(System.getProperty("user.dir") + "\\src\\resources\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		else if(prop.getProperty("browser").contains("firefox"))
		{
			System.setProperty("webdriver.gecko.driver" , System.getProperty("user.dir") + "\\src\\resources\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(prop.getProperty("browser").contains("IE"))
		{
			System.setProperty("webdriver.IE.driver" , System.getProperty("user.dir") + "\\src\\resources\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		else
		{
			System.out.println("unable to get browser from prop file");
		}

	return driver;

	}
	
	
	//method to pass parameters in test
	@DataProvider
	public static Object[][] getData()
	{
		// Array with String data type 2 rows and 4 columns 
		String[][] obj= new String[2][4];
		// Route , custom name , destination, stop
		obj[0][0]="99 Commercial-Broadway / UBC (B-Line)";
		obj[0][1]="Translink code test";
		obj[0][2]="Comm'l-Bdway Stn / Boundary B-Line";
		obj[0][3]="UBC Exchange Bay 7 # 61935";
		
		//negative test data
		obj[1][0]="100 22nd St Stn / Marpole Loop";
		obj[1][1]="Marpole route";
		obj[1][2]="Marpole / Marpole Via Trapp";
		obj[1][3]="Marine Dr at 7 Ave # 52184";
				
		return obj;
	}
	
	public Routepage findBus(Mainpage obj,String route)
	{	
		obj.getNextTab().click();
		obj.getSearchText().sendKeys(route.split(" ")[0]);
		return obj.getFind();
		
	}
	public void addFav(Routepage obj,String favname)
	{
		obj.getaddfavIcon().click();
		obj.getaddfavEditbox().clear();
		obj.getaddfavEditbox().sendKeys(favname);
		obj.getaddButton().click();
	}
	
	public Myfavpage myFav(Routepage obj,String favname)
	{
		Myfavpage mfobj= obj.getmyfav();
		int numberoflinks= mfobj.getfavlinks().size();
		for(int i=0;i<numberoflinks;i++)
		{
			String favlink = mfobj.getfavlinks().get(i).getText();
			if(favlink.equalsIgnoreCase(favname))
				{
					mfobj.getfavlinks().get(i).click();
					break;
				}
		}
		return mfobj;
	}
	
	public Directionpage getDestination(Routepage obj,String destination)
	{
		Directionpage dpobj = null;
		int noofdest = obj.getdestinations().size();
		for(int i=0;i<noofdest;i++)
		{
			String destname= obj.getdestinations().get(i).getText();
			if(destname.contains(destination))
			{
				dpobj= obj.getdestinationclick(i);
				break;
			}
		}
		return dpobj;
	}
	
	public Stoppage getStop(Directionpage obj,String stop)
	{
		Stoppage spobj=null;
		int s = obj.getstoplinks().size();
		for(int i=0;i<s;i++)
		{
			String stopname=obj.getstoplinks().get(i).getText();
			if(stopname.contains(stop.split("#")[0].trim()))
			{
				spobj= obj.getstoppage(i);
				break;
			}
		}
		return spobj;
	}
}