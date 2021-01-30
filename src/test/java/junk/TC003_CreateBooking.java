package junk;

import static io.restassured.RestAssured.given;

import java.util.Date;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_CreateBooking {
	@Test(dataProvider="createbooking")
	void CreateBooking(String firstname,String lastname,String totalprice,String depositpaid,String checkin,String checkout,String additionalneeds) {
	//	RestAssured.baseURI="";
		
		JSONObject inputdata=new JSONObject();
		inputdata.put("firstname",firstname);
		inputdata.put("lastname",lastname);
		inputdata.put("totalprice",totalprice);
		inputdata.put("depositpaid",depositpaid);
		inputdata.put("checkin",checkin);
		inputdata.put("checkout",checkout);
		inputdata.put("additionalneeds",additionalneeds);
		
		
		RestAssured.baseURI ="https://restful-booker.herokuapp.com/booking";
		 RequestSpecification request = RestAssured.given();
		
		 request.body(inputdata.toJSONString());
		 Response response = request.request(Method.POST,"/");
		 String responsebody = response.getBody().asString();
		 System.out.println("responsebody"+responsebody);
		 int statusCode = response.getStatusCode();
		 System.out.println(statusCode);
	/*	Response response=	
		given()
		.contentType("application/json")
		.body(inputdata.toJSONString())
		.when()
		.post("https://restful-booker.herokuapp.com/booking/register")
		.then()
		.statusCode(200)
		.log().all()
		.extract().response();
	
	String jsonresponse = response.asString();
	System.out.println(jsonresponse);
	*/
}
	
	@DataProvider(name="createbooking")
	
	String [][] getBookingDetails()
	{
		String bookingdata[][]= {{"Json","rest","100","true","2021-01-10","2021-01-20","Test"}};
		return(bookingdata);
	}
	
}
