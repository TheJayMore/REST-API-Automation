package Base;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Base {
	
	public static RequestSpecification httprequest;
	public static Response responce;
	public String empID = "2";
	public Logger logger;
	
	@BeforeClass
	public void setUp()
	{
		logger = Logger.getLogger("EmpolyeeRestAPI");
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
	}
}
