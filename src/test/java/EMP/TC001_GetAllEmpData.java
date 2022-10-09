package EMP;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.Base;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_GetAllEmpData extends Base{
	
	@BeforeClass
	void getAllEmployees() throws IOException, InterruptedException
	{
		logger.info("************* - Getiing All Employees Data - ***********");
		
		RestAssured.baseURI="https://reqres.in/";
		httprequest = RestAssured.given();
		responce = httprequest.request(Method.GET,"/api/users");
		Thread.sleep(4);
	}
	
	@Test
	void CheckResponseBody()throws IOException
	{
		logger.info("************* - Checking Responce Body - ***********");
		String responseBody = responce.getBody().asString();
		Assert.assertTrue(responseBody!=null);
		logger.info("Responce Body is ="+responseBody);
	}
	@Test
	void CheckStatusCode()throws IOException
	{
		logger.info("************* - Checking Status Code - ***********");
		int statusCode= responce.getStatusCode();
		Assert.assertEquals(statusCode,200);
		logger.info("Status Code is = "+statusCode);
	}

	@Test
	void CheckResponceTime()throws IOException
	{
		logger.info("************* - Checking Responce Time - ***********");
		long respTime = responce.getTime();
		logger.info("Responce Time -- >"+respTime);
		Assert.assertTrue(respTime<10000);
	}
	
	@Test
	void CheckStatusLine()throws IOException
	{
		logger.info("************* - Checking Status Line - ***********");
		String statusLine = responce.getStatusLine();
		logger.info("Status Line is -- >"+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void ContentType()throws IOException
	{
		logger.info("************* - Checking Content Type - ***********");
		String contentType = responce.getContentType().toString();
		logger.info("Content Type is -- >"+contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}
	
	@AfterClass
	void TearDown()throws IOException
	{
		logger.info("************* - Ending Test Case All Employees Data - ***********");
	}
}
