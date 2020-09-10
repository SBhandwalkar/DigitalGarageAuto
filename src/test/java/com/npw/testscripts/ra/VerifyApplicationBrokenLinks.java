package com.npw.testscripts.ra;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.testng.annotations.Test;

import com.npw.lib.RA.CommonFunc;
import com.om.framework.lib.Messages;

public class VerifyApplicationBrokenLinks {
	
	@Test
	public static void VerifyBrokenLinks() throws IOException
	{
		String sLink ="https://new-public-web-mw-qa.nonprod.digitalplatform.oldmutual.co.za/";
		URL url = new URL(sLink);
		//int iResponse=CommonFunc.chkBrokenLink(url);
		int iResponse=0;
		int response=0;
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		try
		{
			
			connection.connect();
			response=connection.getResponseCode();
			connection.disconnect();
			
		}

		catch(Exception e)
		{
			//Messages.errorMsg=e.getMessage();
			System.out.println(e);
		}
		
		if (iResponse!=200) {
			System.out.println("Response code of the URL "+sLink+" is... "+iResponse+" Expected response is 200");
			//listBadLinks.add("Response code of the URL "+sLink+" is... "+iResponse+" Expected response is 200");
			//objMapErrorUrls.put(sLink, subList);
		}
		else
		{
			//subList.add(sLink);
			//objSubListMap.put(sLink, subList);
			//System.out.println("sublinks.... "+ sLink);
			System.out.println(sLink);
		}
	}
	
	

}
