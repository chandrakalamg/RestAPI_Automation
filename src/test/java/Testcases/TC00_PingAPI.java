package Testcases;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.testbase.Test_Init;

import io.restassured.response.Response;

public class TC00_PingAPI extends Test_Init {
	static boolean Pingstatuscheck = false;
	@BeforeClass
	public void beforeclass() {
		logger.info("*********** TC00  Ping API - Started **********");
	}
	
	@Test()
	public void PingStatus() {
		pingAPI();
	}

	public static boolean pingAPI() {
		
		Response response=	
				given()
				.when()
				.get(baseURI+"ping")
				.then()
				.statusCode(201)
				.statusLine("HTTP/1.1 201 Created")
				.extract().response();
		
		int Ping_Statuscode = response.getStatusCode();
		logger.info("Ping API -Health Status is "+Ping_Statuscode);
		if(Ping_Statuscode==201) {
			Pingstatuscheck =true;
		}
		return Pingstatuscheck;
		
	}
}
