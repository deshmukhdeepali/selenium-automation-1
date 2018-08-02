package flightBookJourney;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class JourneyLondonToParis {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		Properties prop = new Properties();
		InputStream input = null;
		input = new FileInputStream("flightbooking.properties");			
		prop.load(input);
		System.out.println(prop.getProperty("CHROME_DRIVER_EXE_PATH"));
		
		
		System.setProperty("webdriver.chrome.driver", prop.getProperty("CHROME_DRIVER_EXE_PATH"));
		WebDriver driver = new ChromeDriver();
		driver.get("http://newtours.demoaut.com/");
		
		long sleepTime = 2000L;
		
		
		//TC-01 : Entering login details on home page
		driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[3]/form/table/tbody/tr[4]/td/table/tbody/tr[2]/td[2]/input")).clear();
		driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[3]/form/table/tbody/tr[4]/td/table/tbody/tr[2]/td[2]/input")).sendKeys("deepali");
		driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[3]/form/table/tbody/tr[4]/td/table/tbody/tr[3]/td[2]/input")).sendKeys("deepali");
		
		Thread.sleep(sleepTime);
		
		driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[3]/form/table/tbody/tr[4]/td/table/tbody/tr[4]/td[2]/div/input")).click();
        
		//Checking the title of the page after login
		System.out.println(driver.getTitle());
		
		String TitleAfterClick = null;
		
		TitleAfterClick = driver.getTitle();
		
		try {
			Assert.assertEquals(TitleAfterClick, "Find a Flight: Mercury Tours:");
			System.out.println("TC-01 : Login Passed");
		} catch (AssertionError e) {
			System.out.println("TC-01 : Assertion failed " + e.getMessage());
			
		}
		
		
		//TC-02 : Finding flight from London to Paris departing from 20th July and returning on 30th July
		Select df = new Select(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[4]/td[2]/select")));
		df.selectByValue("London");
		
		Select dd = new Select(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[5]/td[2]/select[2]")));
		dd.selectByVisibleText("22");
		
		Select ai  = new Select(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[6]/td[2]/select")));
		ai.selectByValue("Paris");
		
		Select rd  = new Select(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[7]/td[2]/select[2]")));
		rd.selectByIndex(29);
		
		Thread.sleep(sleepTime);
		
		driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[14]/td/input")).click();
		
		System.out.println(driver.getTitle());
		
		TitleAfterClick = driver.getTitle();
		
		try {
			Assert.assertEquals(TitleAfterClick, "Select a Flight: Mercury Tours");
			System.out.println("TC-02 : Finding Flight Passed");
		} catch (AssertionError e) {
			System.out.println("TC-02 : Assertion failed " + e.getMessage());
		}
		
		
		//TC-03 : Selecting flight and continue
		Thread.sleep(sleepTime);
		
		driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/p/input")).click();
		
		System.out.println(driver.getCurrentUrl());

		TitleAfterClick = driver.getCurrentUrl();
		
		try {
			Assert.assertEquals(TitleAfterClick, "http://newtours.demoaut.com/mercurypurchase.php");
			System.out.println("TC-03 : Select Flight Passed");
		} catch (AssertionError e) {
			System.out.println("TC-03 : Assertion failed " + e.getMessage());
		}
			
		
		//TC-04 : Entering passenger details and payment card details
		driver.findElement(By.name("passFirst0")).sendKeys("Deepali");
		driver.findElement(By.name("passLast0")).sendKeys("Deshmukh");
		driver.findElement(By.name("creditnumber")).sendKeys("1234567890123456");
		
		Thread.sleep(sleepTime);
		
		driver.findElement(By.name("buyFlights")).click();
		
		System.out.println(TitleAfterClick = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[3]/td/p/font/b/font[2]")).getText());
		
		if (TitleAfterClick.equals("Your itinerary has been booked!"))
		{
			System.out.println("TC-04 : Passenger Itinerary and Booking Confirmation Passed");
		}
		else
		{
			System.out.println("TC-04 : Assertion failed");
		}
		
		
		//TC-05 : Clicking on Back to flight to book next flight
		Thread.sleep(sleepTime);
		
		driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[7]/td/table/tbody/tr/td[1]/a/img")).click();
		
		System.out.println(driver.getTitle());

		TitleAfterClick = driver.getTitle();
		
		if (TitleAfterClick.equals("Find a Flight: Mercury Tours:"))
		{
			System.out.println("TC-05 : Back To Flights Finder page Passed");
		}
		else
		{
			System.out.println("TC-05 : Assertion failed");
		}
		
		driver.close();
						
	}

}
