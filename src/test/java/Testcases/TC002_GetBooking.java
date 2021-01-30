package Testcases;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.testbase.Test_Init;

import io.restassured.response.Response;

public class TC002_GetBooking extends Test_Init {
	
	@BeforeClass
	public void beforeclass() {
		logger.info("***********  Get Booking Details Using Booking ID - Started **********");
		boolean statusout = TC00_PingAPI.pingAPI();
		logger.info("API Health Check status - Ping "+statusout);
	}
	
	@Test(priority=2)
	public void getBooking() {
	Response response=	
		given()
		.contentType("application/json")
		.when()
		.get(baseURI+"booking/"+genericID)
		.then()
		.statusCode(200)
		.extract().response();
	
	int statuscode = response.getStatusCode();
	String jsonresponse = response.asString();
	//Assert.assertEquals(jsonresponse.contains(genericID),true);
	checkStatusCode(statuscode,jsonresponse);
	logger.info("Booking Details of booking Id :" +genericID );
	checkResposeBody(jsonresponse);
	}
	
	@AfterClass
	public void afterclass() {
		logger.info("*********** TC002 Get Booking Details Using Booking ID- Completed**********");
	}
}
