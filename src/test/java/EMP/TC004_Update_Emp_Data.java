package EMP;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Base.Base;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import utilities.utils;

public class TC004_Update_Emp_Data extends Base {
	
	String empName = utils.getEmpName();
	String empJob = utils.getEmpJob();
	
	@SuppressWarnings("unchecked")
	@BeforeClass
	void getEmpData() throws InterruptedException
	{
		logger.info("************* - Fetching Updated Employee Data - ***********");
		
		RestAssured.baseURI="https://reqres.in/";
		
		httprequest = RestAssured.given();
		
		JSONObject empData = new JSONObject();
		
		empData.put(empName, "name");
		empData.put(empJob, "job");
		
		httprequest.header("Contetnt-Type","applictaion/json");
		
		httprequest.body(empData.toJSONString());
		
		responce = httprequest.request(Method.PUT,"/api/users/"+empID);
		
		Thread.sleep(2);
		logger.info("************* - Fetched Updated Employee Data - ***********");
	}
	
	@Test
	void checkResponceBody()
	{
		logger.info("************* - Checking Employee Data Response - ***********");
		String responceBody = responce.getBody().asString();
		Assert.assertTrue(responceBody!=null);
		logger.info("ResponceBody is ---->"+responceBody);
	}
	
	@Test
	void checkStatusCode()
	{
		logger.info("************* - Checking Request Status Code - *************");
		int StatusCode = responce.getStatusCode();
		Assert.assertEquals(StatusCode, 200);
		logger.info("Status Code is---->"+StatusCode);
	}
	
	@Test
	void checkStatusLine()
	{
		logger.info("************* - Checking Request Status Line - *************");
		String StatusLine = responce.getStatusLine();
		Assert.assertEquals(StatusLine, "HTTP/1.1 200 OK");
		logger.info("Status Line is ---->"+StatusLine);
	}
	
	@Test
	void checkContentType()
	{
		logger.info("************* - Checking Content Type - *************");
		String ContentType = responce.getContentType();
		Assert.assertEquals(ContentType, "application/json; charset=utf-8");
		logger.info("Content Type is ---->"+ContentType);
	}
	
	@AfterClass
	void TearDown()throws IOException
	{
		logger.info("************* - Ending Test Case New Employee Data - ***********");
	}
}
