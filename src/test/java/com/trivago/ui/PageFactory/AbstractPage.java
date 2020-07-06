package com.trivago.ui.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//This class must be extended by any class that requires to implement Page Object Model
abstract class AbstractPage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	//This is the constructor for all Pages
	//WebElements are initialized here so it does not need to be called in each Page
	public AbstractPage(WebDriver driver)
	{
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 20, 50);
		PageFactory.initElements(driver, this);
	}
	
	//This method is not used in this implementation, but included it for use on Page methods
	public WebElement waitFor(WebElement e)
	{
		return wait.until(ExpectedConditions.visibilityOf(e));
	}
	
	//This method is not used in this implementation, but included it for use on Page methods
	public void waitThenClick(WebElement e)
	{
		waitFor(e).click();
	}
		
}
