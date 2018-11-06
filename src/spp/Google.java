package spp;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Google
{
	private WebDriver wdriver = null;
	
	public static void main (String [] args)
	{
		if (args.length != 1)
		{
			System.out.println ("Usage: Google <browser> where f = Firefox, c = Chrome");
			System.exit (-1);
		}
		Google testme = new Google ();
		testme.setup (args [0]);
		testme.runtest ();
		testme.cleanup ();
	}
	
	public void setup (String browserName)
	{
		System.out.print ("Setting up ");
		if (browserName.equals ("c"))
		{
			System.out.println ("Chrome browser...");
			chrome ();
		}
		else if (browserName.equals ("f"))
		{
			System.out.println ("Firefox browser...");
			firefox ();
		}
		else
		{
			System.out.println ("Unsupported browser - aborting");
			System.exit (-2);
		}
		
		wdriver.manage ().timeouts ().implicitlyWait (10, TimeUnit.SECONDS);
	}
	
	public void runtest ()
	{
		System.out.println ("Running test...");
		wdriver.get ("http://www.google.com");
		
		// search for something
		WebElement searchBar = wdriver.findElement (By.name ("q"));
		searchBar.sendKeys ("Les Paul Guitars" + Keys.RETURN);
		
		// select a menu option
		WebElement menuButton = wdriver.findElement (By.linkText ("More"));
		Actions builder = new Actions (wdriver);
		builder.moveToElement (menuButton).click ().perform ();
		wdriver.findElement (By.linkText ("Books")).click ();
		
		waitaFewSeconds (2);    // just so we can enjoy the results of our hard work!
	}
	
	public void cleanup ()
	{
		System.out.println ("Bye bye!");
		if (wdriver != null)
			wdriver.quit ();
	}
	
	// helper methods
	public void firefox ()
	{
		System.setProperty ("webdriver.gecko.driver", "C:\\Program Files\\Java\\drivers\\geckodriver-v0.23.0-win64\\geckodriver.exe");
		wdriver = new FirefoxDriver ();
		if (wdriver == null)
		{
			System.out.println("Failed to load gecko driver - aborting");
			System.exit (-3);
		}
	}
	
	// NOTE: to use Chrome, manually start chromedrive.exe from command line
	public void chrome ()
	{
		System.setProperty ("webdriver.chrome.driver", "C:\\Program Files\\Java\\drivers\\chromedriver\\chromedriver.exe");
		wdriver = new ChromeDriver ();  
		if (wdriver == null)
		{
			System.out.println("Failed to load Chrome driver - aborting");
			System.exit (-4);
		}  
	}
	
	public void waitaFewSeconds (int secs) 
	{
		if (secs < 1) secs = 1;
		else if (secs > 60) secs = 60;
		
		try
		{
			Thread.sleep (secs * 1000);
			return;
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
	} 
}
