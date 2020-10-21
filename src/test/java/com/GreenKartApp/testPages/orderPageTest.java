package com.GreenKartApp.testPages;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.GreenKartApp.base.BasePage;
import com.GreenKartApp.page.OrderPage;

public class orderPageTest extends BasePage {

	OrderPage op;
	
	@BeforeMethod
	public void setup() {
		driverInitialize();
		goToGreenKartApp();
		op = new OrderPage(driver);
	}
	
	@Test
	public void addItemIntoTheCart() {
		System.out.println(configProp.getProperty("Item1"));
		op.enterItem(configProp.getProperty("Item1"));
		op.clickOnSearchBtn();
	}
	
	@Test
	public void addItem2IntoTheCart() {
		System.out.println(configProp.getProperty("Item2"));
		op.enterItem(configProp.getProperty("Item2"));
		op.clickOnSearchBtn();
	}

}
