package com.trivago.ui.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OpenPositionsPage extends AbstractPage {

	public OpenPositionsPage(WebDriver driver) {
		super(driver);
	}
	
	//WebElement locators
	@FindBy(className="job-description-wd")
	List<WebElement> allJobTitles;
	

	@FindBy(className="list-jobs-wd")
	List<WebElement> allJobTitles2;
	
	//This method gets the URLs for all job positions displayed on screen
	public List<String> getOpenPositionsAsList()
	{
		List<String> titles = new ArrayList<String>();
				
		for (WebElement w : allJobTitles2) {
			//Need to strip the javascript code so we get only the pure URL
			titles.add(w.getAttribute("onclick").replace("window.location =", "").replace("'", "").trim());
		}
		return titles;
	}
	
	//This method is not used, but can be used to click on a job position by its title
	public void clickOnJob(String jobTitle)
	{
		for (WebElement job : allJobTitles) {
			if(job.getText().trim().equals(jobTitle))
			{
				job.click();
				return;
			}
		}
		throw new NoSuchElementException("Job title " + jobTitle + " not found.");
	}
	
	//This method is not used, but can be used to click on a job position by its position in the list
	public void clickOnJob(int index)
	{
		if(index >= allJobTitles.size())
		{
			throw new NoSuchElementException("There is no element with index " + index + " in jobs list.");
		}
		else
		{
			allJobTitles.get(index).click();
		}
	}

}
