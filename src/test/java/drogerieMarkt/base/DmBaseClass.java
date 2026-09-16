package drogerieMarkt.base;

import org.testng.annotations.AfterMethod;

import java.time.Duration;

//import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;


import drogerieMarkt.page.DmHomePage;
import drogerieMarkt.page.DmLoginRegistrationPage;

public class DmBaseClass {
	public  WebDriver driver;
	public DmHomePage homePage;
	public DmLoginRegistrationPage loginRegistrationPage;
	public WebDriverWait wait;
	
	@BeforeMethod
	public void setup() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.dm.de/");
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		homePage = new DmHomePage(driver);
		loginRegistrationPage = new DmLoginRegistrationPage(driver);
		
	}
	
	@AfterMethod
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}
}
