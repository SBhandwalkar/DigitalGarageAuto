package com.npw.testscripts.Generic;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;



public class TC01_TestAPI {
	
	@Test
	public void API()
	{
		
		System.setProperty("https.proxyHost", "fwdproxy.za.omlac.net");
		System.setProperty("https.proxyPort", "8080");
		
		Response res= when().get("http://localhost:3000/posts");
		
		System.out.println(res.getStatusCode());
		
		
		System.out.println(res.asString());
		
		String sTitle = res.then().
						contentType(ContentType.JSON).extract().path("title[0]");
		System.out.println(sTitle);
		
		
	}

}
