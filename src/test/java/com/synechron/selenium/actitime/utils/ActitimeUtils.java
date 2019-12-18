package com.synechron.selenium.actitime.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActitimeUtils extends DriverUtils
{
	
	public static void launchApplication(String url)
	{
		System.out.println("Launching application - " + url);
		driver.get(url);
	}

	public static void login(String un,String pwd)
	{
		System.out.println("--- logging in to actitime using " + un + " and " + pwd);
		typeOnElement("id", "username", un);
		typeOnElement("name", "pwd", pwd);
		clickOnElement("id", "loginButton");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void clickOnModule(String moduleName)
	{
		String xpathExpression = "//div[text()='" + moduleName.toUpperCase() + "']/parent::a";
		clickOnElement("xpath", xpathExpression);
	
	}
	
	public static void clickOnNewCustomerButton()
	{
		clickOnElement("xpath", "//div[@class='addNewContainer']");
		clickOnElement("xpath", "//div[@class='item createNewCustomer ellipsis']");
		
	}
	
	
	public static void createCustomer(String cn , String cd) 
	{
		ActitimeUtils.typeOnElement("id", "customerLightBox_nameField", cn);
		ActitimeUtils.typeOnElement("id", "customerLightBox_descriptionField", cd);
		ActitimeUtils.clickOnElement("id", "customerLightBox_commitBtn");
	
		System.out.println("Waiting for success message to appear");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement toastEle = wait.until(ExpectedConditions.visibilityOf(ActitimeUtils.getMyElement("xpath", "//div[@class='toast']")));
		
		System.out.println("Success messge ************* : " + toastEle.getText());
		
		System.out.println("Waiting for success message to disapper");
		wait.until(ExpectedConditions.invisibilityOf(ActitimeUtils.getMyElement("xpath", "//div[@class='toast']")));
	}
	
	public static void logout()
	{
		ActitimeUtils.clickOnElement("id", "logoutLink");
	}
	
}
