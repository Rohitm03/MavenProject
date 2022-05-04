package Delta_login;

import org.testng.annotations.Test;


import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

public class DeltaActions {
	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "D://automationDrivers//chromedriver100_60.exe");
		driver = new ChromeDriver();
		driver.get("https://stag1-delta.leadschool.in/login");
		driver.manage().window().maximize();
		driver.findElement(By.id("userId")).clear();
		driver.findElement(By.id("userId")).sendKeys("9833467714");
		driver.findElement(By.id("Password")).clear();
		driver.findElement(By.id("Password")).sendKeys("FuncByPass");
		WebElement element = driver.findElement(By.id("Login"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
	@DataProvider(name = "excelData")
    public Object[][] excelDataProvider() throws IOException {
        // We are creating an object from the excel sheet data by calling a method that
        // reads data from the excel stored locally in our system
        Object[][] arrObj = getExcelData(
                "C:\\Users\\Rohit Mishra\\Desktop\\filters.xlsx",
                "Sheet1");
        return arrObj;
    }
 
    // This method handles the excel - opens it and reads the data from the
    // respective cells using a for-loop & returns it in the form of a string array
    public String[][] getExcelData(String fileName, String sheetName) throws IOException {
        String[][] data = null;
        try {
             
            FileInputStream fis = new FileInputStream(fileName);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            XSSFRow row = sheet.getRow(0);
            int noOfRows = sheet.getPhysicalNumberOfRows();
            int noOfCols = row.getLastCellNum();
            Cell cell;
            data = new String[noOfRows - 1][noOfCols];
 
            for (int i = 1; i < noOfRows; i++) {
                for (int j = 0; j < noOfCols; j++) {
                    row = sheet.getRow(i);
                    cell = row.getCell(j);
                    data[i - 1][j] = cell.getStringCellValue();
                }
            }
        } catch (Exception e) {
            System.out.println("The exception is: " + e.getMessage());
        }
        return data;
    }
    
		  @Test(dataProvider = "excelData")
	 public void GetShellSequence(String Class,String Subject) throws InterruptedException {
	//public void GetShellSequence() throws InterruptedException {
		Thread.sleep(3000);
		driver.get("https://stag1-delta.leadschool.in/dashboard");
		Thread.sleep(3000);
		driver.findElement(By.linkText("Academics")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/nav/ul/li[3]/ul/li[5]/a/span")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("class")).click();
		new Select(driver.findElement(By.id("class"))).selectByVisibleText(Class);
		driver.findElement(By.xpath("//div[@id='app']/div[3]/div/div/div[3]/div/div[2]/form/div/div[2]/select"))
				.click();
		new Select(driver.findElement(By.xpath("//div[@id='app']/div[3]/div/div/div[3]/div/div[2]/form/div/div[2]/select"))).selectByVisibleText(Subject);
		driver.findElement(By.id("academicYear")).click();
		new Select(driver.findElement(By.id("academicYear"))).selectByVisibleText("2022-2023");
		driver.findElement(By.xpath("//div[@id='app']/div[3]/div/div/div[3]/div/div[2]/form/div[2]/button")).click();
	}

	@Test(priority = 2)
	public void InsideShellSequence() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath(
				"/html/body/div[2]/div[3]/div/div[1]/div[3]/div[3]/div[3]/div[1]/div[2]/div[2]/div/ul/li[1]/a[5]"))
				.click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/div[2]/div/div[3]/div/div/ul/li[2]/a")).click();
	}

	@AfterTest
	public void quit() {
		driver.close();
	 driver.quit();
	}

}