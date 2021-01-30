package Testcases;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.testbase.Test_Init;

import io.restassured.response.Response;

public class TC001_GetBookingIds extends Test_Init {

	@BeforeClass
	public void beforeclass() {
		logger.info("*********** TC001 Get Booking Ids - Started **********");
		boolean statusout = TC00_PingAPI.pingAPI();
		logger.info("API Health Check status - Ping "+statusout);
	}
	
	@Test(priority=1)
	public void getBookingIds() {
	Response response=	
		given()
		.when()
		.get(baseURI+"booking")
		.then()
		.statusCode(200)
		.extract().response();
	
	int code = response.getStatusCode();
	String responseBody = response.getBody().asString();
	
	checkStatusCode(code,responseBody);
	checkResposeBody(responseBody);
	
	// all booking Id's
	List<Integer> bookingIds = response.path("bookingid");
	logger.info("List of Booking Ids: "+bookingIds);
	}
	
	@AfterClass
	public void afterclass() {
		logger.info("***********TC001  Get Booking Ids  - Completed**********");
	}
	
}
