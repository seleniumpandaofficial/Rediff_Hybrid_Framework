package com.rediff.qa.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.rediff.qa.testData.SupplyTestData;
import com.rediff.qa.testbase.TestBase;
import com.rediff.qa.utils.Utilities;

public class LoginTest extends TestBase{
	
	public LoginTest() throws Exception {
		super();	
	}

	public WebDriver driver;
	public SoftAssert softassert = new SoftAssert();
	
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		driver.findElement(By.className("signin")).click();	
	}
	
	@Test(priority = 1, dataProvider = "RediffExcelDataWithDataProvider", dataProviderClass = SupplyTestData.class)
	public void verifyRediffLoginWithValidUsernameAndValidPassword(String username, String password) throws Exception {
		
		Thread.sleep(1000);
		driver.findElement(By.id("login1")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.className("signinbtn")).click();
		
		softassert.assertTrue(driver.findElement(By.className("rd_logout")).isDisplayed());
		softassert.assertAll();
		
	}
	
	@Test(priority = 2)
	public void verifyRediffLoginWithInvalidUsernameAndInvalidPassword() throws Exception {

		Thread.sleep(1000);
		driver.findElement(By.id("login1")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("password")).sendKeys(dataprop.getProperty("invalidPassword"));
		driver.findElement(By.className("signinbtn")).click();
		
		String actualWarningMessage = driver.findElement(By.id("div_login_error")).getText();
		String expectedWarningMessage = dataprop.getProperty("tempIssueWarningMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message is not correct");
		softassert.assertAll();
		
	}
	
	@Test(priority = 3)
	public void verifyRediffLoginWithValidUsernameAndInvalidPassword() throws Exception {

		Thread.sleep(1000);
		driver.findElement(By.id("login1")).sendKeys(prop.getProperty("validUsername"));
		driver.findElement(By.id("password")).sendKeys(dataprop.getProperty("invalidPassword"));
		driver.findElement(By.className("signinbtn")).click();
		
		String actualWarningMessage = driver.findElement(By.id("div_login_error")).getText();
		String expectedWarningMessage = dataprop.getProperty("wrongCrentialsMessage");
		softassert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Warning Message is not correct");
		softassert.assertAll();
		
	}
	
	@Test(priority = 4)
	public void verifyRediffLoginWithEmptyUsernameAndValidPassword() throws Exception {

		Thread.sleep(1000);
		driver.findElement(By.id("password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.className("signinbtn")).click();
		Alert alert = driver.switchTo().alert();
		String actualAlertText = alert.getText();
		String expectedAlertText = dataprop.getProperty("alertUsername");
		
		softassert.assertEquals(actualAlertText, expectedAlertText);
		if(actualAlertText.equals(expectedAlertText)) {
			alert.accept();
		}else {
			alert.dismiss();
		}
	   softassert.assertAll();
	   
	}
	
	@Test(priority = 5)
	public void verifyRediffLoginWithValidUsernameAndEmptyPassword() throws Exception {

		driver.findElement(By.className("signin")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("login1")).sendKeys(prop.getProperty("validUsername"));
		driver.findElement(By.className("signinbtn")).click();
		Alert alert = driver.switchTo().alert();
		String actualAlertText = alert.getText();
		String expectedAlertText = dataprop.getProperty("alertPassword");
		
		softassert.assertEquals(actualAlertText, expectedAlertText);
		if(actualAlertText.equals(expectedAlertText)) {
			alert.accept();
		}else {
			alert.dismiss();
		}
	   softassert.assertAll();
	   
	}
	
	@Test(priority = 6)
	public void verifyRediffLoginWithEmptyUsernameAndEmptyPassword() throws Exception {

		driver.findElement(By.className("signin")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("signinbtn")).click();
		Alert alert = driver.switchTo().alert();
		String actualAlertText = alert.getText();
		String expectedAlertText = dataprop.getProperty("alertUsername");
		
		softassert.assertEquals(actualAlertText, expectedAlertText);
		if(actualAlertText.equals(expectedAlertText)) {
			alert.accept();
		}else {
			alert.dismiss();
		}
	   softassert.assertAll();
	   
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
}
