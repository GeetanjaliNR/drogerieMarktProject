package drogerieMarkt.base;

import org.testng.annotations.AfterTest;

//import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import drogerieMarkt.page.DmHomePage;

public class DmBaseClass {
	public  WebDriver driver;
	public DmHomePage homePage;
	public WebDriverWait wait;
	
	@BeforeTest
	public void setup() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.dm.de/");
	}
	
	@BeforeMethod
	public void baseMethod() {
		homePage = new DmHomePage(driver);
//		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	@AfterTest
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}
}
