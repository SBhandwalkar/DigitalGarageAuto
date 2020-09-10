package com.npw.testscripts.Generic;

import org.testng.annotations.Test;

import com.om.framework.basetest.BaseTest;
import com.om.framework.lib.Browser;
import com.om.framework.lib.Utilities;

public class TC01_Redirects extends BaseTest {
	
private static String sUrl,sRedirect;
	
	private static String TestData_path= "TestData\\Redirects_Testdata.xlsx";
	private static String sheetName="Prod";
	private static String currentUrl;
	
	
	@Test
	public static void VerifyRedirects() throws Exception {
		
		Utilities.setExcelFile(TestData_path,sheetName);
		
		int iRowCount = Utilities.getRowNum();
		for(int iRowCounter=1;iRowCounter<=iRowCount;iRowCounter++)
		{
			sUrl=Utilities.getCellData(iRowCounter,0);
			sRedirect=Utilities.getCellData(iRowCounter,1);
			Browser.navigateTo(driver,sUrl);
			currentUrl=driver.getCurrentUrl();
			
			if(sRedirect.equalsIgnoreCase(currentUrl) || sRedirect.equalsIgnoreCase(currentUrl +"#")) 
			{
				Utilities.setCellData("Pass",iRowCounter,2);
				System.out.println("Pass:   "+sUrl);
				Utilities.setCellData(currentUrl,iRowCounter,3);
			}
			else
			{
				Utilities.setCellData("Fail",iRowCounter,2);
				System.out.println("Pass:   "+sUrl);
				Utilities.setCellData(currentUrl,iRowCounter,3);
			}
			
		
		}
		
		Utilities.closeexcel(TestData_path);
	}
	

}
