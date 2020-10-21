package com.GreenKartApp.testPages;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.GreenKartApp.base.BasePage;
import com.GreenKartApp.page.OrderPage;
import com.relevantcodes.extentreports.LogStatus;

public class orderPageTest2 extends BasePage {

	OrderPage op;
	
	@BeforeMethod
	public void setup(ITestResult result) {
		driverInitialize();
		goToGreenKartApp();
		op = new OrderPage(driver);
		extentTest = extent.startTest(result.getMethod().getQualifiedName());
	}
	
	@Test
	public void addItem3IntoTheCart() {
		System.out.println(configProp.getProperty("Item3"));
		op.enterItem(configProp.getProperty("Item3"));
		op.clickOnSearchBtn();
	}
	
//	@Test
//	public void addItem5IntoTheCart() {
//		System.out.println(configProp.getProperty("Item5"));
//		op.enterItem(configProp.getProperty("Item5"));
//		op.clickOnSearchBtn();
//	}
	
	@Test
	public void addItem4IntoTheCart() {
		System.out.println(configProp.getProperty("Item4"));
		op.enterItem(configProp.getProperty("Item4"));
		op.clickOnSearchBtn();
	}
	
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		
		if(result.getStatus()==ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "Test case failed is " + result.getName());
			extentTest.log(LogStatus.FAIL, "Test case failed is " + result.getThrowable());
			
			String screenshotPath = BasePage.getScreenShot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath));
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test case skipped is " + result.getName());
			
		}
		else if(result.getStatus()==ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test case is passed " + result.getName());
		}
		else {
			extentTest.log(LogStatus.SKIP, "Test case skipped is " + result.getName());
		}
		extent.endTest(extentTest);
		driver.quit();
		
	}

}
