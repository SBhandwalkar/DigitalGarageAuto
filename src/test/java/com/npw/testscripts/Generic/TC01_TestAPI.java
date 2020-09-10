package com.npw.testscripts.Generic;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;



public class TC01_TestAPI {
	
	@Test
	public void API()
	{
		
		System.setProperty("https.proxyHost", "fwdproxy.za.omlac.net");
		System.setProperty("https.proxyPort", "8080");
		
		Response res= when().get("https://jsonplaceholder.typicode.com/users");
		
		System.out.println(res.getStatusCode());
		
	}

}
