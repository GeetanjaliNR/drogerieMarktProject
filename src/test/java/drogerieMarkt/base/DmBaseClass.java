package drogerieMarkt.base;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import java.lang.reflect.Method;

//import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import drogerieMarkt.page.DmCartPage;
import drogerieMarkt.page.DmHomePage;
import drogerieMarkt.page.DmLoginRegistrationPage;
import drogerieMarkt.page.DmProductPage;
import drogerieMarkt.page.DmSearchPage;

public class DmBaseClass {
	public  WebDriver driver;
	public DmHomePage homePage;
	public DmLoginRegistrationPage loginRegistrationPage;
	public DmProductPage productPage;
	public DmCartPage cartPage;
	public DmSearchPage searchPage;
	public WebDriverWait wait;
	
	public static ExtentSparkReporter reporter;  
	public static ExtentTest test;
	public static ExtentReports extent;
	
	@BeforeTest
	public void reportSetup() {
		reporter = new ExtentSparkReporter("./Reports/DrogerieMarkt.html");
		reporter.config().setDocumentTitle("Drogerie Markt Project Report");
		reporter.config().setReportName("Functional Testing Report");
		reporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("hostname", "localhost");
		extent.setSystemInfo("os", "macOS");
		extent.setSystemInfo("testedBy", "Geetanjali");
		extent.setSystemInfo("Browser Name", "Chrome");

	}
	@BeforeMethod
	public void setup(Method testName) throws Exception {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		
		driver.get("https://www.dm.de/");
//		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		homePage = new DmHomePage(driver);
		homePage.acceptCookies();
		loginRegistrationPage = new DmLoginRegistrationPage(driver);
		productPage = new DmProductPage(driver);
		cartPage = new DmCartPage(driver);
		searchPage = new DmSearchPage(driver);
		
		test = extent.createTest(testName.getName());
		
	}
	
	@AfterMethod
	public void browserClose(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test case failed: " + result.getName());
			test.log(Status.FAIL, "Test case failed: " + result.getThrowable());
		}else if(result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Test case skipped: " + result.getName());
		}else if(result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test case passed: " + result.getName());
		}
		
		if(driver != null) {
			driver.quit();
		}
	}
	
	@AfterTest
	public void tearDown() {
		extent.flush();
		
	}
}
