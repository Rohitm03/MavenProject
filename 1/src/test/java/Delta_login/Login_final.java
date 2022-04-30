package Delta_login;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login_final{

	public void adminLogin()
{
	  System.setProperty("webdriver.chrome.driver", "D://automationDrivers//chromedriver100_60.exe");
		WebDriver driver = new ChromeDriver();
		 driver.get("https://stag1-delta.leadschool.in/login");
		    driver.manage().window().maximize();
		    driver.findElement(By.id("userId")).clear();
		    driver.findElement(By.id("userId")).sendKeys("9833467714");
		    driver.findElement(By.id("Password")).clear();
		    driver.findElement(By.id("Password")).sendKeys("FuncByPass");
		    WebElement element = driver.findElement(By.id("Login"));
		    JavascriptExecutor executor = (JavascriptExecutor)driver;
		    executor.executeScript("arguments[0].click();", element);
		  		   }};
