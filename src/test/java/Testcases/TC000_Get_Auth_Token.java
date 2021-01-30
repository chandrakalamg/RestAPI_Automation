package Testcases;

import static io.restassured.RestAssured.given;

import java.net.ResponseCache;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.testbase.Test_Init;

import io.restassured.response.Response;

public class  TC000_Get_Auth_Token extends Test_Init {
	static HashMap<String, String> data1=new HashMap<String, String>();
//	static Response response;
	
	@BeforeClass
	public void beforeclass() {
		logger.info("*********** TC000 Capture Auth Token - Started **********");
		boolean statusout = TC00_PingAPI.pingAPI();
		logger.info("API Health Check status - Ping "+statusout);
	}
	
	@Test(description="Getting Token ID")
	public static void getId()  {
		getTokenIds();
	//	verifyStatuscode(200);
	}
	
	public static String getTokenIds() {
		data1.put("username", "admin");
		data1.put("password", "password123");
	Response response=	
		given()
		.contentType("application/json")
		.body(data1)
		
		.when()
		.post(baseURI+"auth")
		
		.then()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.extract().response();
	
	//--Verification and reporting---------
	
	//logger.info("***********  Checking statuscode **********");
	int code = response.getStatusCode();
	logger.info("Status Code :"+code);
	Assert.assertEquals(code,200);
	
	//logger.info("***********  Checking Token **********");
	String Authtoken= response.path("token");
	Assert.assertTrue(Authtoken!=null);
	//System.out.println("Authtoken is generated :"+Authtoken);
	logger.info("Authtoken is generated :"+Authtoken);
	return Authtoken;
	}

	@AfterClass
	public void afterclass() {
		logger.info("*********** TC000  Capture Auth Token - Completed**********");
	}
	}
	

