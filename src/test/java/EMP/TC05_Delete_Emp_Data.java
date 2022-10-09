package EMP;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.Base;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import utilities.utils;

public class TC05_Delete_Emp_Data extends Base{
	
	String empName = utils.getEmpName();
	String empJob = utils.getEmpJob();
	
	@SuppressWarnings("unchecked")
	@BeforeClass
	void getEmpData() throws InterruptedException
	{
		logger.info("************* - Deleting Employee Data - ***********");
		
		RestAssured.baseURI="https://reqres.in/";
		
		httprequest = RestAssured.given();
		
		JSONObject empData = new JSONObject();
		
		empData.put(empName, "name");
		empData.put(empJob, "job");
		
		httprequest.header("Contetnt-Type","applictaion/json");
		
		httprequest.body(empData.toJSONString());
		
		responce = httprequest.request(Method.DELETE,"/api/users/"+empID);
		
		Thread.sleep(2);
		logger.info("************* - Deleted Employee Data - ***********");
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
		Assert.assertEquals(statusCode,204);
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
		Assert.assertEquals(statusLine, "HTTP/1.1 204 No Content");
	}
	
	@AfterClass
	void TearDown()throws IOException
	{
		logger.info("************* - Ending Test Case Delete Employee Data Request - ***********");
	}

}
