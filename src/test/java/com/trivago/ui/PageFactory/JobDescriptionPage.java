package com.trivago.ui.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JobDescriptionPage extends AbstractPage {

	public JobDescriptionPage(WebDriver driver) {
		super(driver);
	}
		
	//WebElement locators
	@FindBy(xpath="//*[contains(@class, 'common-title')]/*")
	WebElement jobTitle;
	
	@FindBy(xpath="//b[text() = 'Job Family']/following-sibling::span")
	WebElement jobFamily;
	
	@FindBy(xpath="//b[text() = 'Experience Level']/following-sibling::span")
	WebElement experienceLevel;
	
	@FindBy(xpath="//b[text() = 'Location']/following-sibling::span")
	WebElement location;
	
	@FindBy(xpath="//b[text() = 'Language']/following-sibling::span")
	WebElement language;
	
	@FindBy(xpath="//button[text() = 'Apply']")
	WebElement applyButton;
	
	@FindBy(xpath="//*[text() = 'What you’ll do:']")
	WebElement whatYoullDo;
	
	@FindBy(xpath="//h3[3]/*")
	WebElement whatYoullNeed;
	
	@FindBy(xpath="//*[text() = 'What we’d love you to have:']")
	WebElement whatWedLove;
	

	public WebElement getJobTitle() {
		return jobTitle;
	}

	public WebElement getJobFamily() {
		return jobFamily;
	}

	public WebElement getExperienceLevel() {
		return experienceLevel;
	}

	public WebElement getLocation() {
		return location;
	}

	public WebElement getLanguage() {
		return language;
	}

	public WebElement getApplyButton() {
		return applyButton;
	}

	public WebElement getWhatYoullDoHeader() {
		return whatYoullDo;
	}

	public WebElement getWhatYoullNeedHeader() {
		return whatYoullNeed;
	}

	public WebElement getWhatWedLoveHeader() {
		return whatWedLove;
	}
	
	
}
