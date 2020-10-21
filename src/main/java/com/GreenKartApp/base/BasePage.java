package com.GreenKartApp.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BasePage {

	public static Properties configProp;
	protected WebDriver driver;
	public ExtentReports extent;
	public ExtentTest extentTest;

	@BeforeTest
	public void extent() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/extentReport.html", true);
		extent.addSystemInfo("Host Name", "Amit");
		extent.addSystemInfo("User Name", "Amit Chiddarwar");
		extent.addSystemInfo("Environment", "Staging");

	}

	public BasePage() {
		initConfigGKAprop();
	}

	public void initConfigGKAprop() {
		try {
			configProp = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+File.separator +
					"src" +File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator+"GreenKartApp"+File.separator+
					"configFiles"+File.separator+"configGKA.properties");
			
			configProp.load(ip);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void driverInitialize() {

		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("Operating System: " + os);

		String browserName = configProp.getProperty("browser");
		System.out.println("Browser: " + browserName);

		if (os.contains("windows")) {

			if (browserName.equals("Chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\DriverFolder\\Chrome_Driver86\\chromedriver.exe");
				driver = new ChromeDriver();
			}

			else if (browserName.equals("Firefox")) {
				System.setProperty("webdriver.geko.driver", "     ");
				driver = new FirefoxDriver();
			}
		}

		if (os.contains("linux")) {

			if (browserName.equals("Chrome")) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless"); //should be enabled for Jenkins
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/DriverFolder/Chrome_Driver86_linux/chromedriver");
				driver = new ChromeDriver(options);
			}

			else if (browserName.equals("Firefox")) {
				System.setProperty("webdriver.geko.driver", "     ");
				driver = new FirefoxDriver();
			}
		}
	}

	public void goToGreenKartApp() {
		driver.get(configProp.getProperty("URL"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public static String getScreenShot(WebDriver driver, String methodName) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot ts = (TakesScreenshot) driver;
		File Source = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/Screenshot/" + methodName + dateName + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(Source, destination);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;

	}

	@AfterTest
	public void endReport() {
		extent.flush();
		extent.close();
	}

}
