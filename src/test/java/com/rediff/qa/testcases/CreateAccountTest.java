package com.rediff.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.rediff.qa.testbase.TestBase;
import com.rediff.qa.utils.Utilities;

public class CreateAccountTest extends TestBase {
	
	public CreateAccountTest() throws Exception {
		super();
	}

    public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();
	public Select select;
	
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		driver.findElement(By.linkText("Create Account")).click();
	}
	
	@Test(priority = 1)
	public void enterAllValidFields() {
		driver.findElement(By.xpath("//input[starts-with(@name, 'name')]")).sendKeys(dataprop.getProperty("fullName"));
		driver.findElement(By.xpath("//input[starts-with(@name, 'login')]")).sendKeys(Utilities.generateNameforEmailWithTimeStamp());
		driver.findElement(By.className("btn_checkavail")).click();
		String actualAvailableMessage = driver.findElement(By.id("check_availability")).getText();
		String expectedAvailableMessage = dataprop.getProperty("successAvailabilityIdMessage");
		softassert.assertTrue(actualAvailableMessage.contains(expectedAvailableMessage));
		
		driver.findElement(By.id("newpasswd")).sendKeys(dataprop.getProperty("createPassword"));
		driver.findElement(By.id("newpasswd1")).sendKeys(dataprop.getProperty("retypePassword"));
		driver.findElement(By.xpath("//input[starts-with(@name, 'altemail')]")).sendKeys(prop.getProperty("validUsername"));
		driver.findElement(By.id("mobno")).sendKeys("9876543210");
		
		select = new Select(driver.findElement(By.xpath("//select[starts-with(@name, 'DOB_Day')]")));
		select.selectByVisibleText("01");
		
		select = new Select(driver.findElement(By.xpath("//select[starts-with(@name, 'DOB_Month')]")));
		select.selectByVisibleText("JAN");
		
		select = new Select(driver.findElement(By.xpath("//select[starts-with(@name, 'DOB_Year')]")));
		select.selectByVisibleText("2000");
		
		driver.findElement(By.xpath("//input[starts-with(@name, 'gender')][@value = 'm']")).click();
		
		select = new Select(driver.findElement(By.id("country")));
		select.selectByVisibleText("United States");
		
		driver.findElement(By.className("captcha")).sendKeys("ABCD");
		driver.findElement(By.id("Register")).click();
		
		String actualFailureMessageCreateAccount = driver.findElement(By.className("errbody")).getText();
		String expectedFailureMessageCreateAccount = dataprop.getProperty("failRegistrationMessage");
		softassert.assertEquals(actualFailureMessageCreateAccount,expectedFailureMessageCreateAccount);
		softassert.assertAll();
		
	}
	
	
	
	@AfterMethod
	public void tearDown() {
		//driver.quit();
	}
}
