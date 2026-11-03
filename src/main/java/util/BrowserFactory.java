package util;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserFactory {
	
	static WebDriver driver;
	
	// Open Browser using URL from Excel sheet
	public static WebDriver LaunchBrowser() {
		ExcelReader reader = new ExcelReader("./data/testdata.xlsx");
		String url = reader.getCellData("Sheet2", "url", 2);
		return LaunchBrowser(url);
	}
	
	// Overloaded Open Browser method with custom URL
	public static WebDriver LaunchBrowser(String targetUrl) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(targetUrl);
		return driver;
	}
	
	// Close Browser
	public static void CloseBrowser() {		
		if (driver != null) {
			driver.quit();
		}
	}
}
