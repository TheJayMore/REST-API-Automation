package EMP;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.Base;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC002_Get_Single_Emp_Data extends Base {
	
	@BeforeClass
	void getSinfleEmployeeData() throws IOException, InterruptedException
	{
		logger.info("************* - Fetching Single Employee Data - ***********");
		
		RestAssured.baseURI="https://reqres.in/";
		httprequest = RestAssured.given();
		responce = httprequest.request(Method.GET,"/api/users/2");
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
	void CheckName() throws UnsupportedEncodingException
	{
		String RespoBody = responce.getBody().asString();
		System.out.println(RespoBody);
		JsonPath jsonPathEvaluator = responce.jsonPath();
		String empid = jsonPathEvaluator.get("[0].id");
		System.out.println(empid);
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
	
	@AfterClass
	void TearDown()throws IOException
	{
		logger.info("************* - Ending Test Case update Employee Data - ***********");
	}

}
