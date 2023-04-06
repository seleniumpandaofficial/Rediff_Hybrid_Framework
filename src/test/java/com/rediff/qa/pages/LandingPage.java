package com.rediff.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	
	public WebDriver driver;
	
	//Objects
	
	@FindBy(className = "signin")
	private WebElement singinLink;
	
	@FindBy(linkText = "Create Account")
	private WebElement createAccountLink;
	
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		//PageFactory.initElements(driver, LandingPage.class);
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	public void clickOnSignInLink() {
		singinLink.click();
	}
	
	public void clickOnCreateAccountLink() {
		createAccountLink.click();
	}

}
