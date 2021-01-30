package Testcases;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.testbase.Test_Init;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;	
	
public class TC004_PartialUpdateBooking extends Test_Init {
	
	@BeforeClass
	public void beforeclass() {
		logger.info("***********TC004  Partial update Booking - Started **********");
	}
	
	@Test(priority=4)
	public void PartialUpdatebooking() {
		/*------get token runtime-----*/
		String tokenID = TC000_Get_Auth_Token.getTokenIds();
		
		/*------get newly created booking ID if not found get generic ID-----*/
		String bookingId = TC003_1Create_Booking.bookingIDvar();
		
		if(bookingId=="")
			bookingId= genericID;
		
		String update_Firstname =Test_Init.Update_Fn();
		String update_Lastname = Test_Init.Update_Ln();
		
		/*------Input data to update the booking details---*/
	Map<String,Object> jsonupdateinputdata=new HashMap<String,Object>();
	jsonupdateinputdata.put("firstname",update_Firstname);
	jsonupdateinputdata.put("lastname",update_Lastname );
	Response response=
	given()
			.cookie("token",tokenID)
			.contentType("application/JSON")
			.body(jsonupdateinputdata)
			
	.when()
	
			.patch(baseURI+"booking/"+bookingId)
			
	.then()
			.statusCode(200)
		//	.body("firstname", Matchers.equalTo(update_Firstname))
			.extract().response();
	
			int statuscode = response.getStatusCode();
			String jsonresponse = response.asString();
			/*------ Verification----*/
			logger.info("Partially updated booking details for booking ID :"+ bookingId);
			String res_FN=response.path("firstname");
			Assert.assertTrue(res_FN.equals(update_Firstname));
			
			String res_LN=response.path("lastname");
			Assert.assertTrue(res_LN.equals(update_Lastname));
			logger.info("Partially updated booking details for booking ID :"+ bookingId+" are firstname:"+update_Firstname+" and lastname :"+update_Lastname);
			checkStatusCode(statuscode,jsonresponse);
			checkResposeBody(jsonresponse);
		
	}	
	@AfterClass
	public void afterclass() {
		logger.info("*********** TC004 Partial update Booking - Completed **********");
	}
	
}

