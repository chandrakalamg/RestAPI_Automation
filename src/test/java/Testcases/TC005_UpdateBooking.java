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
public class TC005_UpdateBooking  extends Test_Init{
	@BeforeClass
	public void beforeclass() {
		logger.info("*********** TC005 Update details of Booking - Started **********");
	}
	
	@Test(priority=5)
	public void Updatebooking() {
		String tokenID = TC000_Get_Auth_Token.getTokenIds();
	
		/*------get newly created booking ID if not found get generic ID-----*/
		String bookingId = TC003_1Create_Booking.bookingIDvar();
		if(bookingId=="")
			bookingId= genericID;
		System.out.println(bookingId);
		String Upd_firstname = Test_Init.Firstname();
		String Upd_lastname=Test_Init.Lastname();
		String Upd_totalprice=Test_Init.Totalprice();
		String Upd_checkin = Test_Init.Radom_date();
		String Upd_checkout = Test_Init.Radom_date();
		String Upd_addneeds = "breakfast1";
		
	Map<String,Object> jsonupdateinputdata=new HashMap<String,Object>();
	
	jsonupdateinputdata.put("firstname",Upd_firstname);
	jsonupdateinputdata.put("lastname",Upd_lastname);
	jsonupdateinputdata.put("depositpaid",true);
	jsonupdateinputdata.put("totalprice",Upd_totalprice);
	
	Map<String,String> bookingDatesdata=new HashMap();
	bookingDatesdata.put("checkin", Upd_checkout);
	bookingDatesdata.put("checkout",Upd_checkout);
	
	jsonupdateinputdata.put("bookingdates", bookingDatesdata);
	jsonupdateinputdata.put("additionalneeds", Upd_addneeds);
	Response response =
	given()
			.cookie("token",tokenID)
			.contentType("application/JSON")
			.body(jsonupdateinputdata)
		
		.when()
			.patch(baseURI+"booking/"+bookingId)
		
		.then()
			.statusCode(200)
			.extract().response();
	
	int statuscode = response.getStatusCode();
	String jsonresponse = response.asString();
	/*------ Verification----*/
	
	checkStatusCode(statuscode,jsonresponse);
	String act_totalprice=String.valueOf(response.path("totalprice"));
	Assert.assertTrue(act_totalprice.equals(Upd_totalprice));
	
	/*--verify response booking details*/
	logger.info("Updated booking details of ID :"+bookingId);
	checkResposeBody(jsonresponse);
	Verifyresponsedata(jsonresponse,Upd_firstname,Upd_lastname,Upd_addneeds);
			/*Upd_checkin,Upd_checkout,Upd_addneeds);*/
	
	}
	
	@AfterClass
	public void afterclass() {
		logger.info("*********** TC005 Update details of Booking - Completed **********");
	}
	
}
