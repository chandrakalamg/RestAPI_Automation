package junk;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;


import java.util.HashMap;

import org.testng.annotations.Test;

public class API_Tests {
	//public static String jsonToken;
	static HashMap<String, String> data1=new HashMap<String, String>();
	@Test
	public static String get_authToken() {
		data1.put("username", "admin");
		data1.put("password", "password123");
		
		Response authToken =
		given()
			.contentType("application/json")
			.body(data1)
		.when()
			.post("https://restful-booker.herokuapp.com/auth")
		.then()
			.statusCode(200)
		//	.statusLine("HTTP/1.1 200 OK")	
			.extract().response();
		//	int code = authToken.getStatusCode();
		//	Assert.assertEquals(code,200);
		String jsonToken = authToken.asString();
		System.out.println(jsonToken+" and  "+authToken);
		return jsonToken;
		
	}
	
	//@Test(priority=1)
	public void getBookingIds() {
		
		given()
		.when()
		.then();
	}
	
	//@Test
	public void getBooking() {
		
		given()
		.when()
		.then();
	}
	
	//@Test
	public void createBooking() {
		
		given()
		.when()
		.then();
	}
	
	//@Test
	public void updateBooking() {
		
		given()
		.when()
		.then();
	}
	
	//@Test
	public void partialUpdateBooking() {
		
		given()
		.when()
		.then();
	}
	
	//@Test
	public void DeleteBooking() {
		
		given()
		.when()
		.then();
	}
}
