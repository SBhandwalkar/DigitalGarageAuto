package com.npw.testscript.BAU;

import org.testng.annotations.Test;

import com.om.framework.basetest.BaseTest;
import com.om.framework.lib.Browser;
import com.om.framework.lib.Utilities;

public class BAU_Verify_EMS_ImagesTest extends BaseTest {
	
	private static String sUrl;
	
	private static String TestData_path= "TestData\\EMS_TestData.xlsx";
	private static String sheetName="QA";
	
	
	@Test
	public static void VerifyEMSImages() throws Exception {
		
		Utilities.setExcelFile(TestData_path,sheetName);
		
		int iRowCount = Utilities.getRowNum();
		for(int iRowCounter=1;iRowCounter<=iRowCount;iRowCounter++)
		{
			sUrl=Utilities.getCellData(iRowCounter,0);
			Browser.navigateTo(driver,sUrl);
			
			if(driver.getPageSource().contains(sUrl)) 
			{
				Utilities.setCellData("Pass",iRowCounter,1);
				System.out.println("Pass:   "+sUrl);
			}
			else
			{
				Utilities.setCellData("Fail",iRowCounter,1);
				System.out.println("Pass:   "+sUrl);
			}
			
		
		}
		
		Utilities.closeexcel(TestData_path);
	}
	
	

}
