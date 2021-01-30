package Testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.testbase.Test_Init;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
public class TC006_DeleteBooking  extends Test_Init {
	
	@BeforeClass
	public void beforeclass() {
		logger.info("*********** TC006 Delete Booking ID - Started **********");
	} 
	@Test(priority=6)
	public void Deletebooking() {
		
		String tokenID = TC000_Get_Auth_Token.getTokenIds();
		
		/*------get newly created booking ID if not found get generic ID-----*/
		//String bookingId = TC003_1Create_Booking.newbooking_Id;
		String bookingId = TC003_1Create_Booking.bookingIDvar();
		if(bookingId=="")
			bookingId= genericID;
		//System.out.println(bookingId+"and"+tokenID);
		/*-------delete Booking based on Booking Id------*/
	Response response =	
	given()
			.cookie("token",tokenID)
			.contentType("application/JSON")
		.when()
			.delete(baseURI+"booking/"+bookingId)
		.then()
			.statusCode(201)
			.statusLine("HTTP/1.1 201 Created")
			.extract().response();
	int delstatuscode = response.getStatusCode();
	//String responsebody = response.asString();
	/*------ Verification----*/
	logger.info("Deleted booking with bookingid :"+bookingId+" status code is "+delstatuscode);
	//checkResposeBody(responsebody);
	
	/*----------Verify that booking is deleted--------*/
	given()
	
	.when()
		.get(baseURI+"booking/"+bookingId)
		
	.then()
		.statusCode(404)
		.extract().response();
	int statuscode = response.getStatusCode();
	String jsonresponse = response.asString();
	/*------ Verification----*/
	logger.info("Verying the deleted booking with get request, status code is "+statuscode);
	checkResposeBody(jsonresponse);
	}	
	
	@AfterClass
	public void afterclass() {
		logger.info("*********** TC006 Delete Booking - Completed **********");
	}
}
