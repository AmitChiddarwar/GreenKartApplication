package com.GreenKartApp.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GreenKartApp.common.CommonFunctions;

public class OrderPage extends CommonFunctions {
	
	@FindBy(xpath="//input[@placeholder='Search for Vegetables and Fruits']")
	WebElement searchBox;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement submitBtn;
	
	
	public OrderPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		
	}
	
	public void enterItem(String itemName) {
		sendText(searchBox, itemName);
	}
	
	public void clickOnSearchBtn() {
		clickOnElement(submitBtn);
	}
	

}
