package com.npw.testscript.BAU;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.om.framework.basetest.BaseTest;
import com.om.framework.lib.Browser;
import com.om.framework.lib.Elements;
import com.om.framework.lib.Utilities;

public class MIG_FundCenterTest extends BaseTest  {

	private static List<WebElement> FundsLinks;
	private static WebElement Icon;
	
	private static boolean bStatus;
	private static boolean bFundStatus=false;
	private static String FundName;
	private static String FundUrl;
	private static String sUrl = "https://www.oldmutual.co.za.qap/markets/fund-centre";
	
	private static String TestData_path= "TestData\\Funds On FundCentre.xlsx";
	private static String sheetName="Funds";

	private static Map<String, String> objFundListMap = new HashMap<String,  String>();
	private static String sFurl,sName;
	
	
	@Test
	public static void VerifyFundsMig() throws Exception
	{
		
		Browser.navigateTo(driver,sUrl);
		FundsLinks=Elements.getWebElements(By.xpath("//span[@class='fund-title']//a"));
		FundsLinks= Elements.getWebElements(By.xpath("//tr[@class='gridRow']/td/a"));
		for(WebElement link:FundsLinks)
		{
			
			sFurl = link.getAttribute("href");
			 sName=link.getText();
			System.out.println(sName);
			objFundListMap.put(sName,sFurl);
			
		}
		
		
		Utilities.setExcelFile(TestData_path,sheetName);		

		int iRowCount = Utilities.getRowNum();
		for(int iRowCounter=1;iRowCounter<=iRowCount;iRowCounter++)
		{
			
			
					
				FundName=Utilities.getCellData(iRowCounter,0);
				FundUrl= Utilities.getCellData(iRowCounter,1);
				bFundStatus=false;
			
				for (HashMap.Entry<String, String> entry : objFundListMap.entrySet()) 
				{
					System.out.println(entry.getValue());
					if(entry.getKey().trim().equalsIgnoreCase(FundName.trim()))
					{
						if(entry.getValue().contains(FundUrl))
						{
							
							Utilities.setCellData("Pass",iRowCounter,2);
							Utilities.setCellData(entry.getKey(),iRowCounter,3);
							bFundStatus=true;
							System.out.println("Pass");
						}
						
						else
						{
							Utilities.setCellData("Falied",iRowCounter,2);
							Utilities.setCellData("FactSheetURL is different than expected acutual url is:  "+ entry.getValue(),iRowCounter,3);
							bFundStatus=true;
							System.out.println("Failed");
						}
						
						
					}
				    
				}
				
				if(!bFundStatus) 
				{
					
					Utilities.setCellData("Falied",iRowCounter,2);
				}
				
			}
		
	}
	
}
