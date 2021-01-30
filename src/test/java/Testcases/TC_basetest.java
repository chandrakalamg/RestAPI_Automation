package Testcases;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.testbase.Test_Init;

import io.restassured.response.Response;

public class TC_basetest extends Test_Init {
	static HashMap<String, String> data1=new HashMap<String, String>();
	static Response response;
	@BeforeClass
	void beforeclass() {
		logger.info("-----Getting Authentication Token----");
	}
	
	@Test(priority=1)
	public static void getAuthTokenIds() {
		//HashMap<String, String> data1=new HashMap<String, String>();
		data1.put("username", "admin");
		data1.put("password", "password123");
		
	//Response response=	
		given()
			.contentType("application/json")
			.body(data1)	
		.when()
			.post("https://restful-booker.herokuapp.com/auth")
		.then()
		.body("token", Matchers.notNullValue())
		.extract().response();
		logger.info("***********  Checking Respose  **********");
		

	
	}
	@Test(priority=2)
	public void checkResposeBody()
	{
		logger.info("***********  Checking Respose Body **********");
		
		//String responseBody = response.getBody().asString();
		//logger.info("Response Body==>"+responseBody);
		//Assert.assertTrue(responseBody!=null);
		
	}
	
	/*
	public static void verifyStatuscode(int expStatuscode) {
		logger.info("***********  Checking statuscode **********");
		int code = response.getStatusCode();
		logger.info("Status Code :"+code);
		Assert.assertEquals(code,expStatuscode);
	}
	
	
	public static String verifyToken() {
		String Authtoken= response.path("token");
		Assert.assertTrue(Authtoken!=null);
		logger.info("Authtoken is generated :"+Authtoken);
		return Authtoken;
		//System.out.println(Authtoken);
		
	}
	*/
	@AfterClass
	void afterclass() {
		logger.info("-----Getting Authentication Token----");
	}
	
}
