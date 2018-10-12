package spp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Google
{
	private WebDriver wdriver = null;
	
	public static void main (String [] args)
	{
		Google testme = new Google ();
		testme.setup ();
		testme.runtest ();
		testme.cleanup ();
	}
	
	public void setup ()
	{
		System.out.println ("Setting up...");
		System.setProperty ("webdriver.gecko.driver", "C:\\Program Files\\Java\\drivers\\geckodriver-v0.23.0-win64\\geckodriver.exe");
		wdriver = new FirefoxDriver ();
	}
	
	public void runtest ()
	{
		System.out.println ("Running test...");
		wdriver.get ("http://www.google.com");
		
		waitaFewSeconds (2);
	}
	
	public void cleanup ()
	{
		System.out.println ("Bye bye!");
		if (wdriver != null)
			wdriver.quit ();
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
