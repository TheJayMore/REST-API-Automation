package utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class utils {

	public static String getEmpName()
	{
		String empName = RandomStringUtils.randomAlphabetic(3);
		return ("RaOne "+empName);
	}
	
	public static String getEmpJob()
	{
		String Job = RandomStringUtils.randomAlphabetic(1);
		return ("Associate Level "+Job);
	}
	
}
