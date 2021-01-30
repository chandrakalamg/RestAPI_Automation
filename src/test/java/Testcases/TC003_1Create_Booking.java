package Testcases;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.testbase.Test_Init;

import io.restassured.response.Response;

public class TC003_1Create_Booking extends Test_Init {
public static String newbooking_Id = "";
	@BeforeClass
	public void beforeclass() {
		logger.info("*********** TC003_1 Create New Booking - Started **********");
	}
	
	@Test(priority=3)
	
	public static void New_booking() {
		//public static String newbooking_Id;
		newbooking_Id = Createbooking();
	}
	/*---to use newbooking ID value in next classes*/
	public static String bookingIDvar() {
		//public static String newbooking_Id;
	//	System.out.println("create"+newbooking_Id);
		return newbooking_Id;
	}
	
	public static String Createbooking() {
		/* Booking details for new Booking */
		
		String Inp_firstname = Test_Init.Firstname();
		String Inp_lastname=Test_Init.Lastname();
		String Inp_totalprice=Test_Init.Totalprice();
		String Inp_checkin = Test_Init.Radom_date();
		String Inp_checkout = Test_Init.Radom_date();
		String Inp_addneeds = "IdlyVada";
		
	Map<String,Object> jsoninputdata=new HashMap<String,Object>();
	//jsoninputdata.put("firstname","Elon");
	jsoninputdata.put("firstname",Inp_firstname);
	jsoninputdata.put("lastname",Inp_lastname);
	jsoninputdata.put("depositpaid",true);
	jsoninputdata.put("totalprice",Test_Init.Totalprice());
	
	Map<String,String> bookingDatesdata=new HashMap();
	bookingDatesdata.put("checkin", Inp_checkin);
	//bookingDatesdata.put("checkin", "2021-09-01");
	bookingDatesdata.put("checkout",Inp_checkout);
	
	jsoninputdata.put("bookingdates", bookingDatesdata);
	jsoninputdata.put("additionalneeds", Inp_addneeds);
	
	
	Response response=	
			given()
			.contentType("application/JSON")
			.body(jsoninputdata)
			.when()
				.post("https://restful-booker.herokuapp.com/booking")
			.then()
			.statusCode(200)
			.extract().response();
	
	int statuscode = response.getStatusCode();
	String responsebody = response.asString();
	checkStatusCode(statuscode,responsebody);
	//logger.info("***********  Get new Booking ID **********");
	String bookingId= String.valueOf(response.path("bookingid"));
	logger.info("Booking_Id is generated :"+bookingId);
	logger.info("New Booking Details with booking Id:"+ bookingId);
	checkResposeBody(responsebody);
	
	
	Verifyresponsedata(responsebody,Inp_firstname,Inp_lastname,Inp_addneeds);
	return bookingId;

	}	
	
	@AfterClass
	public void afterclass() {
		logger.info("*********** TC003_1 Create New Booking - Completed **********");
	}
	
}
