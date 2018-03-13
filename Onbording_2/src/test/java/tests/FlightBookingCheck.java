package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
@Listeners(utility.Listner.TestListner.class)
public class FlightBookingCheck 
{
	WebDriver driver;
	String url="https://www.orbitz.com/";
	
	@Parameters({ "browser" })

	@BeforeTest
  public void openBrowser(String browser )
  {
	  if (browser.equalsIgnoreCase("Firefox"))
			{      
			    System.setProperty("webdriver.gecko.driver", "C:\\Users\\ruchika.gole\\geckodriver.exe");
				driver = new FirefoxDriver();
			}
	  else if (browser.equalsIgnoreCase("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver","C:\\Users\\ruchika.gole\\workspace\\OnBording_Assignment2\\lib\\chromedriver.exe");
			driver = new ChromeDriver();
		}
	  else if (browser.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver","D:/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
  }
  @Test
  public void  FlightBookingTest() throws InterruptedException
  {
	  driver.navigate().to(url);
		
				WebElement flightMenu=driver.findElement(By.id("primary-header-flight"));
				flightMenu.click();
				
				//select source
				WebElement flyingFrom=driver.findElement(By.id("flight-origin"));
				flyingFrom.sendKeys("Mumbai, India (BOM-Chhatrapati Shivaji Intl.)");
				Thread.sleep(1000);
				
				//select Destination
				WebElement flyingTo=driver.findElement(By.id("flight-destination"));
				flyingTo.sendKeys("Fort Lauderdale, FL (FLL-All Airports)");
				Thread.sleep(1000);
				

				//selecting date from date picker	
				driver.findElement(By.id("flight-departing")).click();
				WebElement departingDate = driver.findElement(By.className("datepicker-cal-dates"));
		    	
				JavascriptExecutor js = (JavascriptExecutor) driver;
				
		        
				List<WebElement> column=departingDate.findElements(By.tagName("td"));
			    
				js.executeScript("window.scrollBy(0,200)");
				for (WebElement cell: column)
			    {
			    	String date=cell.getText();
			    	if(date.equalsIgnoreCase("28"))
					{
			    		cell.click();
						break;
					}
				}
			   	Thread.sleep(1000);
				WebElement btnSearch=driver.findElement(By.id("search-button"));
				//Taking Screenshot
				
				
				btnSearch.click();
				
  }
  @AfterTest
  public void closeBrowser() 
  {
	  driver.quit();
	  
  }

}
