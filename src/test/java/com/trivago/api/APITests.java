package com.trivago.api;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class APITests{
	

	@BeforeClass
    public void setup()
	{
		//Setting the base URI to be shared by all tests.
		//In case the endpoint ever changes, we only need to change it in one place.
		RestAssured.baseURI = "https://api.chucknorris.io/";
		
    }
	
	@DataProvider
	private String[] categories()
	{
		//This is a Data Provider method
		//We'll get all categories as a String array
		return
		get("jokes/categories")
		.as(String[].class);
	}
	
	@Test
	public void TC_001_Get_Joke_Categories()
	{
		//At the time of this writing, there are 16 different categories
		given()
		.when()
		.log()
		.all()
		.get("/jokes/categories")
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.body(".", hasSize(equalTo(16)));
	}
	
	@Test(dataProvider="categories")
	public void TC_002_Get_Joke_For_Each_Category(String category)
	{
		//This is data driven test
		//This test will be executed as many times as there are categories in the GET response
		given()
		.when()
		.log()
		.all()
		.get("/jokes/random?category=" + category)
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.body("categories[0]", equalTo(category));
	}
	
	@Test
	public void TC_003_Check_Existing_Joke() throws ParseException
	{
		//We'll need to extract the entire response as a JsonPath
		//This is because the updated_at String needs to be parsed as Date
		//We'll use TestNG's asserts to verify
		JsonPath response = 
		given()
		.when()
		.log()
		.all()
		.get("/jokes/1BYqNs0MSzmtl9ivZikisA")
		.then()
		.statusCode(200)
		.extract().jsonPath();
		
		//Assertions
		//Verifying update date is after January 1, 2016
		Date updatedAt = new SimpleDateFormat("yyyy-MM-dd").parse(response.getString("updated_at")); 
		assertTrue(updatedAt.after(new GregorianCalendar(2016, Calendar.JANUARY, 1).getTime()), "The update date is after January 1, 2016");
		
		//Verifying joke contains the string 'entire song'
		assertTrue(response.getString("value").contains("entire song"), "The joke contains the string 'entire song'");
		
		
		//Now that we have the icon_url String, we'll do a GET of said URL
		//We'll verify we get a 200 response, and the ContentType returned is a PNG image
		given()
		.when()
		.log()
		.all()
		.get(response.getString("icon_url"))
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.contentType(equalTo("image/png"));		
	}
	
	@Test
	public void TC_004_Check_Not_Existing_Joke()
	{
		//Verifying an incorrect id returns 404 response.
		given()
		.when()
		.log()
		.all()
		.get("/jokes/1BYqNs0MSzmtl9ivZikisAxxxxxx")
		.then()
		.assertThat()
		.statusCode(404);
	}

}
