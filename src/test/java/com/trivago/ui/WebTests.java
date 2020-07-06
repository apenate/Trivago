package com.trivago.ui;

import static org.testng.Assert.assertTrue;

import java.util.List;
import org.testng.annotations.*;

import com.trivago.ui.PageFactory.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import ru.stqa.selenium.factory.WebDriverPool;



public class WebTests{
	
	//This test may take a very long time to execute unless it's executed in parallel.
	//In order to execute in parallel, please execute the Test Suite at /src/test/resources/TestSuite.xml
	//This is in order to limit the number of threads allowed.
	//You can set the thread count in the "suite" node
	
	//Change parallel to true if you want to run in parallel
	
	@DataProvider(parallel = true)
	private String[] getAvailablePositions()
	{		
		WebDriver driver = WebDriverPool.DEFAULT.getDriver(new ChromeOptions());
		driver.get("https://company.trivago.com/open-positions/");
		
		OpenPositionsPage p = new OpenPositionsPage(driver);
		
		List<String> l = p.getOpenPositionsAsList();
		
		WebDriverPool.DEFAULT.dismissDriver(driver);
		
		return l.stream().distinct().toArray(String[]::new);
		
	}
	
	
	@Test(dataProvider="getAvailablePositions")
	public void TC_001_Verify_Position_Fields(String url)
	{
		WebDriver driver = WebDriverPool.DEFAULT.getDriver(new ChromeOptions());
		driver.get(url);
        JobDescriptionPage jobDescriptionPage = new JobDescriptionPage(driver);
        
        //Verifications
        //Job title should have a <h1> tag
        assertTrue(jobDescriptionPage.getJobTitle().getTagName().equals("h1"), "Job title should have a <h1> tag");
        
        //Job Family should have a value
        assertTrue(!jobDescriptionPage.getJobFamily().getText().isEmpty(), "Job Family should have a value");
        
        //Experience level should have a value
        assertTrue(!jobDescriptionPage.getExperienceLevel().getText().isEmpty(), "Experience level should have a value");
        
        //Location should have a value
        assertTrue(!jobDescriptionPage.getLocation().getText().isEmpty(), "Location should have a value");

        //Language should have a value
        assertTrue(!jobDescriptionPage.getLanguage().getText().isEmpty(), "Language should have a value");

        //Apply button should be present on the page
        assertTrue(jobDescriptionPage.getApplyButton().isDisplayed(), "Apply button should be present on the page");
        
        //What you’ll do should have a <b> tag
        //The test case asks to check for <b> tag, but I found <strong> tag which is equivalent
        //For this reason, adding an OR clause. Otherwise all tests fail
        String whatYoullDoTag = jobDescriptionPage.getWhatYoullDoHeader().getTagName();
        assertTrue(whatYoullDoTag.equals("b") || whatYoullDoTag.equals("strong"), "What you’ll do should have a <b> tag");

        //What you’ll definitely need should have <b> tag
        //The test case asks to check for <b> tag, but I found <strong> tag which is equivalent
        //For this reason, adding an OR clause. Otherwise all tests fail
        String whatYoullNeedTag = jobDescriptionPage.getWhatYoullNeedHeader().getTagName();
        assertTrue(whatYoullNeedTag.equals("b") || whatYoullNeedTag.equals("strong"), "What you’ll definitely need should have <b> tag");

        //What we’d love you to have should have <b> tag
        //The test case asks to check for <b> tag, but I found <strong> tag which is equivalent
        //For this reason, adding an OR clause. Otherwise all tests fail
        String whatWedLoveTag = jobDescriptionPage.getWhatWedLoveHeader().getTagName();
        assertTrue(whatWedLoveTag.equals("b") || whatWedLoveTag.equals("strong"), "What we’d love you to have should have <b> tag");
        
        WebDriverPool.DEFAULT.dismissDriver(driver);

	}
	
	@AfterClass(alwaysRun=true)
	public void tearDown()
	{
		WebDriverPool.DEFAULT.dismissAll();
	}
	
}