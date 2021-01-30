package com.testbase;

import java.util.GregorianCalendar;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class Test_Init {
	public static Logger logger;
	public static String baseURI ="https://restful-booker.herokuapp.com/";
	public static String genericID="3";
	
	@BeforeClass
	public void Init() {
		logger=Logger.getLogger("restful-Booking_APITesting");
		PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);
	}
	
	public static void checkStatusCode(int code,String responseBody) {
		//logger.info("***********  Checking statuscode **********");
		logger.info("Status Code :"+code);
		Assert.assertEquals(code,200);
	}
	
	public static void checkResposeBody(String responseBody)
	{
		//logger.info("***********  Checking Respose Body **********");
		logger.info("Response Body==>"+responseBody);
		Assert.assertTrue(responseBody!=null);
		
	}
	
	//public static void Verifyresponsedata(String responsebody,String Firstname, String Lastname, String totalprice , String checkin,String checkout,String pref) {
		public static void Verifyresponsedata(String responsebody,String Firstname, String Lastname,String pref) {
		Assert.assertEquals(responsebody.contains(Firstname),true);
		Assert.assertEquals(responsebody.contains(Lastname),true);
		//Assert.assertEquals(responsebody.contains(checkout),true);
		//Assert.assertEquals(responsebody.contains(checkin),true);
		//Assert.assertEquals(responsebody.contains(totalprice),true);
		Assert.assertEquals(responsebody.contains(pref),true);

		
	}
	public static String Firstname() {
		String generatedstring = RandomStringUtils.randomAlphabetic(1);
				return ("Json1"+generatedstring);
	}
	
	public static String Lastname() {
		String generatedstring = RandomStringUtils.randomAlphabetic(1);
				return ("Rest1"+generatedstring);
	}
	public static String Update_Fn() {
		String generatedstring = RandomStringUtils.randomAlphabetic(1);
				return ("Json_U"+generatedstring);
	}
	
	public static String Update_Ln() {
		String generatedstring = RandomStringUtils.randomAlphabetic(1);
				return ("Rest_U"+generatedstring);
	}
	public static String Totalprice() {
		String generatedstring = RandomStringUtils.randomNumeric(5);
				return (generatedstring);
	}
	
	@SuppressWarnings("static-access")
	public static String Radom_date() {
	        GregorianCalendar cal = new GregorianCalendar();

	        int year = randBetween(2021, 2022);
	        cal.set(cal.YEAR,year);
	        int dayOfYear = randBetween(1, cal.getActualMaximum(cal.DAY_OF_YEAR));
	        cal.set(cal.DAY_OF_YEAR, dayOfYear);
	        String radomdate = cal.get(cal.YEAR) + "-" + (cal.get(cal.MONTH) + 1) + "-" + cal.get(cal.DAY_OF_MONTH);
	      //  System.out.println(cal.get(cal.YEAR) + "-" + (cal.get(cal.MONTH) + 1) + "-" + cal.get(cal.DAY_OF_MONTH));
			return radomdate;
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
    
    
    
}
	

