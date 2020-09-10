package com.npw.testscripts.ra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.npw.locators.RA.BAULocators;
import com.om.framework.basetest.BaseTest;
import com.om.framework.lib.Browser;
import com.om.framework.lib.Elements;
import com.om.framework.lib.Utilities;



public class Verify_All_Funds_Fund_Center extends BaseTest {
	
	private static boolean bStatus;
	private static boolean bFundStatus;
	private static String strFund="";
	private static List<WebElement> UTFundsLinks;
	private static List<WebElement> LifeLinks;
	private static Map<String, String> objUTListMap = new HashMap<String,  String>();
	private static Map<String, String> objLifeListMap = new HashMap<String,  String>();
	private static String sUrl = "https://www.oldmutual.co.za.qap/markets/fund-centre";
	
	private static String TestData_path= "TestData\\Funds On FundCentre.xlsx";
	private static String sheetName="UTFunds";
	private static String FundName;
	private static String FundUrl;
	
	@Test
	public static void VerifyFunds() throws Exception
	{
		
		strFund="UnitTrust";
		getfunds(strFund);
		Thread.sleep(5000);
		
		
		UTFundsLinks= Elements.getWebElements(By.xpath("//tr[@class='gridRow']/td/a"));
		for(WebElement link:UTFundsLinks)
		{
			
			String sFurl = link.getAttribute("href");
			String sName=link.getText();
			System.out.println(sName);
			objUTListMap.put(sName,sFurl);
			
		}
		
		checkFundLinks("UTFunds");
		Utilities.closeexcel(TestData_path);
		
		
		
		strFund="LifeFund";
		getfunds(strFund);
		Thread.sleep(5000);
		
		LifeLinks= Elements.getWebElements(By.xpath("//tr[@class='gridRow']/td/a"));
		for(WebElement link:LifeLinks)
		{
			
			String sFurl = link.getAttribute("href");
			String sName=link.getText();
			System.out.println(sName);
			objLifeListMap.put(sName,sFurl);
			
		}
		
		checkFundLinks("LifeFunds");
		Utilities.closeexcel(TestData_path);
		checkFundLinks("AdditionalFunds");
		
		Utilities.closeexcel(TestData_path);
	
		
	}
	
	
	public static void checkFundLinks(String sheet) throws Exception
	{
		
		Utilities.setExcelFile(TestData_path,sheet);		

		int iRowCount = Utilities.getRowNum();
		for(int iRowCounter=1;iRowCounter<=iRowCount;iRowCounter++)
		{
			
			try {
					
				FundName=Utilities.getCellData(iRowCounter,1);
				FundUrl= Utilities.getCellData(iRowCounter,3);
				bFundStatus=false;
			if(sheet.contains("UTFunds") || sheet.contains("AdditionalFunds"))
			{
				for (HashMap.Entry<String, String> entry : objUTListMap.entrySet()) 
				{
					System.out.println(entry.getValue());
					if(entry.getKey().trim().equalsIgnoreCase(FundName.trim()))
					{
						if(entry.getValue().contains(FundUrl))
						{
							
							Utilities.setCellData("PassUT",iRowCounter,4);
							Utilities.setCellData(entry.getKey(),iRowCounter,5);
							bFundStatus=true;
							System.out.println("PassUT");
						}
						
						else
						{
							Utilities.setCellData("FaliedUT",iRowCounter,4);
							Utilities.setCellData("FactSheetURL is different than expected acutual url is:  "+ entry.getValue(),iRowCounter,5);
							bFundStatus=true;
							System.out.println("Failed");
						}
						
						
					}
				    
				}
				
				if(!bFundStatus && sheet.contains("UTFunds")) 
				{
					
					Utilities.setCellData("Falied",iRowCounter,4);
				}
				
			}
			if(sheet.contains("LifeFunds") || sheet.contains("AdditionalFunds") && !bFundStatus)
			{
					
				for (HashMap.Entry<String, String> entry : objLifeListMap.entrySet()) 
				{
					System.out.println(entry.getValue());
					if(entry.getKey().trim().equalsIgnoreCase(FundName.trim()))
					{
						if(entry.getValue().contains(FundUrl))
						{
							Utilities.setCellData("PassLife",iRowCounter,4);
							Utilities.setCellData(entry.getKey(),iRowCounter,5);
							bFundStatus=true;
						}
						
						else
						{
							Utilities.setCellData("FaliedLife",iRowCounter,4);
							Utilities.setCellData("FactSheetURL is different than expected acutual url is:  "+ entry.getValue(),iRowCounter,5);
							bFundStatus=true;
						}
						
						
					}
				    
				}
				
				if(!bFundStatus) 
				{
					
					Utilities.setCellData("Falied",iRowCounter,4);
				}	
					
			}
					
					
			}
			catch(Exception e)
			{
				System.out.println(e);
			}

		}
		
		
	}
	
	
	
	
	
	
	
	
	public static void getfunds(String Fundtype) throws InterruptedException
	{
		
		Browser.navigateTo(driver,sUrl);
		Thread.sleep(5000);
		if(Fundtype.contains("LifeFund")) 
		{
			bStatus=Elements.movetoclickElement(By.xpath(BAULocators.FundCenter.radioBtnLifeFund()));
			if(!bStatus) return;
		}
		Thread.sleep(5000);
		bStatus=Elements.clickElement(By.xpath(BAULocators.FundCenter.IndividualFund()));
		if(!bStatus) return;
		Thread.sleep(1000);
		bStatus=Elements.clickElement(By.xpath(BAULocators.FundCenter.SelectIndividualFund("Individual Funds")));
		if(!bStatus) return;
		
		bStatus=Elements.clickElement(By.xpath(BAULocators.FundCenter.FundOption()));
		if(!bStatus) return;
		Thread.sleep(1000);
		bStatus=Elements.clickElement(By.xpath(BAULocators.FundCenter.SelectFundOption("All Funds")));
		if(!bStatus) return;
		
	}

}
