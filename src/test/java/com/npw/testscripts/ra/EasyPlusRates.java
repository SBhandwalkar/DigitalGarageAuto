package com.npw.testscripts.ra;


import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.npw.locators.RA.Locators;
import com.om.framework.basetest.BaseTest;
import com.om.framework.lib.Browser;
import com.om.framework.lib.Elements;
import com.om.framework.lib.Messages;
import com.om.framework.lib.Utilities;

public class EasyPlusRates extends BaseTest {
	private static String TestData_path= "TestData\\NPW_EasyPlusTest.xlsx";
	private static String sheetName="Covers";
	private static String RatesheetName="";

	private static String TestDataRates_path= "TestData\\NPW_EasyPlusRates.xlsx";
	private static String ReadTestDataRates_path= "TestData\\NPW_EasyPlusRates.xls";

	private static String sUrl = "https://www.oldmutual.co.za/npw/online-funeral-quote?theme=OM%20GREEN";
	private static String sMenu;
	private static String sSubMenu;
	private static String AgeRange;
	private static String AgeRangeU;
	private static Map<String,String> objRADtls;
	private static String ExpectedValue;
	private static String sStatus;
	public static ArrayList<String> arrResult= new ArrayList<>();
	public static ArrayList<String> arrStatus= new ArrayList<>();
	private static Logger logger=Logger.getLogger("Elements");

	private static String AgeRangeL;
	@Test
	public static void easyplusrates() throws Exception
	{


		Utilities.setExcelFile(TestData_path,sheetName);		

		int iRowCount = Utilities.getRowNum();
		for(int iRowCounter=1;iRowCounter<=iRowCount;iRowCounter++)
		{

			try {
					
				sMenu=Utilities.getCellData(iRowCounter,0);
				sSubMenu= Utilities.getCellData(iRowCounter,1);
				if(sMenu!= "" || sSubMenu !="")
				{
					Browser.navigateTo(driver,sUrl);
					selectCover(sMenu,sSubMenu);

					Utilities.setExcelFile(TestData_path,sheetName);
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}

		}

		Utilities.setExcelFile(TestData_path,"Result");
		for(int icounter=0;icounter<=arrResult.size()-1;icounter++)
		{

			Utilities.setCellData(arrResult.get(icounter),icounter,0);
			Utilities.setCellData(arrStatus.get(icounter),icounter,1);
		}

		Utilities.closeexcel(TestData_path);
	}

	public static void selectCover(String Menu,String SubMenu) throws Exception
	{
		Thread.sleep(1000);
		Elements.clickElement(By.xpath(Locators.NPW_EasyPlus.persondropdown(Menu)));
		Thread.sleep(1000);
		Elements.clickElement(By.xpath(Locators.NPW_EasyPlus.persondropdownitem(SubMenu)));
		Thread.sleep(1000);
		if(Menu.equalsIgnoreCase("My parents or in-laws"))
			MyParentInLawCover(SubMenu);
		else if(Menu.equalsIgnoreCase("Me and My Direct Family"))
			MyDirectFamilyCover(SubMenu);
		else if(Menu.equalsIgnoreCase("My extended family"))
			MyExtendedCover(SubMenu);


	}

	public static void checkrates(String Upper, String Lower,String VerifyValue,String submenu,String Cover) throws Exception
	{

		int upper =Integer.parseInt(Upper.trim());
		int lower =Integer.parseInt(Lower.trim());

		for(int i=lower;i<=upper;i++) 
		{	
			Elements.enterNumericValue(By.xpath(Locators.NPW_EasyPlus.inputText("e.g.32")),i);
			Thread.sleep(1000);

			String Rates=Elements.getText(By.xpath(Locators.NPW_EasyPlus.VerifyRates()));
			if(Rates.contains(VerifyValue))
			{
				logger.info("Verified Value Rates for "+submenu+" for Cover= "+Cover+" age = "+i+" in Age Range "+ Lower+"-"+Upper+" Expected: "+VerifyValue+" Actual: "+Rates+"  Pass");
				System.out.println("Verified Value Rates for "+submenu+" for Cover= "+Cover+" age = "+i+" in Age Range "+ Lower+"-"+Upper+" Expected: "+VerifyValue+" Actual: "+Rates+"  Pass");
				sStatus="Verified Value Rates for "+submenu+" for Cover= "+Cover+" age = "+i+" in Age Range "+ Lower+"-"+Upper+" Expected: "+VerifyValue+" Actual: "+Rates+"  Pass";
				arrResult.add(sStatus);
				arrStatus.add("Pass");
			}
			else
			{
				logger.info("Verified Value Rates for "+submenu+" age "+i+" in Age Range "+ Lower+"-"+Upper+" Expected: "+VerifyValue+" Actual: "+Rates+"  Fail");
				System.out.println("Verified Value Rates for "+submenu+" age "+i+" in Age Range "+ Lower+"-"+Upper+" Expected: "+VerifyValue+" Actual: "+Rates+"  Fail");
				sStatus="Verified Value Rates for "+submenu+" for Cover= "+Cover+" age = "+i+" in Age Range "+ Lower+"-"+Upper+" Expected: "+VerifyValue+" Actual: "+Rates+"  Fail";
				arrResult.add(sStatus);
				arrStatus.add("Fail");
			}



		}

	}

	public static void setCover(String Cover) throws InterruptedException
	{
		WebElement dragElementFrom = driver.findElement(By.xpath("//span[@role='slider']"));

		if(Cover.equalsIgnoreCase("RAND5000"))
		{
			new Actions(driver).dragAndDropBy(dragElementFrom, -50, 0).build().perform();
			Thread.sleep(1000);
		}

		else if(Cover.equalsIgnoreCase("RAND10000"))
		{	
			new Actions(driver).dragAndDropBy(dragElementFrom, 50, 0).build().perform();
			Thread.sleep(1000);
		}
		else if(Cover.equalsIgnoreCase("RAND15000"))
		{	
			new Actions(driver).dragAndDropBy(dragElementFrom, 50, 0).build().perform();
			Thread.sleep(1000);
		}
		else if(Cover.equalsIgnoreCase("RAND20000"))
		{
			new Actions(driver).dragAndDropBy(dragElementFrom, 50, 0).build().perform();
			Thread.sleep(1000);
		}
		else if(Cover.equalsIgnoreCase("RAND25000"))
		{
			new Actions(driver).dragAndDropBy(dragElementFrom, 50, 0).build().perform();
			Thread.sleep(1000);
		}
		else if(Cover.equalsIgnoreCase("RAND30000"))
		{
			new Actions(driver).dragAndDropBy(dragElementFrom, 50, 0).build().perform();
			Thread.sleep(1000);
		}
		else if(Cover.equalsIgnoreCase("RAND40000"))
		{
			new Actions(driver).dragAndDropBy(dragElementFrom, 50, 0).build().perform();
			Thread.sleep(1000);
		}
		else if(Cover.equalsIgnoreCase("RAND50000"))
		{
			new Actions(driver).dragAndDropBy(dragElementFrom, 50, 0).build().perform();
			Thread.sleep(1000);
		}

		else if(Cover.equalsIgnoreCase("RAND60000"))
		{
			new Actions(driver).dragAndDropBy(dragElementFrom, 50, 0).build().perform();
			Thread.sleep(1000);
		}

		else if(Cover.equalsIgnoreCase("RAND70000"))
		{
			new Actions(driver).dragAndDropBy(dragElementFrom, 50, 0).build().perform();
			Thread.sleep(1000);
		}
		else if(Cover.equalsIgnoreCase("Default"))
		{
			new Actions(driver).dragAndDropBy(dragElementFrom, -450, 0).build().perform();
			Thread.sleep(1000);
		}
		else {

			Messages.errorMsg="Cover Cant be set";

		}




	}


	public static void MyParentInLawCover(String SubMenu) throws Exception
	{

		RatesheetName="Parents";
		Utilities.setExcelFile(TestDataRates_path,RatesheetName);
		
		int iRowCountCover = Utilities.getRowNum();
		for(int iRowCounterCover=1;iRowCounterCover<=iRowCountCover;iRowCounterCover++)
		{
			try {


				AgeRange=Utilities.getCellData(iRowCounterCover,0);
				if(AgeRange.contains("Up to "))
				{
					String[] arrAges = AgeRange.split("Up to ");
					AgeRangeU=arrAges[1];
					AgeRangeL=arrAges[1];

				}
				else if(AgeRange.contains("-"))
				{
					String[] arrAges = AgeRange.split("-");
					AgeRangeU=arrAges[1];
					AgeRangeL=arrAges[0];
				}

				objRADtls = Utilities.readTestData(ReadTestDataRates_path,RatesheetName, AgeRange);
				
				
				
				
				ExpectedValue=objRADtls.get("RAND5000");
				setCover("RAND5000");
				checkrates(AgeRangeU,AgeRangeL,ExpectedValue,SubMenu,"RAND5000");

				ExpectedValue=objRADtls.get("RAND10000");
				setCover("RAND10000");
				checkrates(AgeRangeU,AgeRangeL,ExpectedValue,SubMenu,"RAND10000");

				ExpectedValue=objRADtls.get("RAND15000");
				setCover("RAND15000");
				checkrates(AgeRangeU,AgeRangeL,ExpectedValue,SubMenu,"RAND15000");

				ExpectedValue=objRADtls.get("RAND20000");
				setCover("RAND20000");
				checkrates(AgeRangeU,AgeRangeL,ExpectedValue,SubMenu,"RAND20000");

				ExpectedValue=objRADtls.get("RAND25000");
				setCover("RAND25000");
				checkrates(AgeRangeU,AgeRangeL,ExpectedValue,SubMenu,"RAND25000");

				ExpectedValue=objRADtls.get("RAND30000");
				setCover("RAND30000");
				checkrates(AgeRangeU,AgeRangeL,ExpectedValue,SubMenu,"RAND30000");

				ExpectedValue=objRADtls.get("RAND40000");
				setCover("RAND40000");
				checkrates(AgeRangeU,AgeRangeL,ExpectedValue,SubMenu,"RAND40000");

				ExpectedValue=objRADtls.get("RAND50000");
				setCover("RAND50000");
				checkrates(AgeRangeU,AgeRangeL,ExpectedValue,SubMenu,"RAND50000");

				
				setCover("Default");


			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}


	}

	public static void MyDirectFamilyCover(String SubMenu) throws Exception
	{
		if(SubMenu.equalsIgnoreCase("Me")) {
			RatesheetName="Individual";
			Utilities.setExcelFile(TestDataRates_path,RatesheetName);

		}
		else if(SubMenu.equalsIgnoreCase("Me and my family")) {
			RatesheetName="Family";
			Utilities.setExcelFile(TestDataRates_path,RatesheetName);
		}
		else if(SubMenu.equalsIgnoreCase("Me and my children")) {
			RatesheetName="SingleAdultFamily";
			Utilities.setExcelFile(TestDataRates_path,RatesheetName);
		}
		else
		{
			Messages.errorMsg="no submenu not found";

		}




		int iRowCountCover = Utilities.getRowNum();
		for(int iRowCounterCover=1;iRowCounterCover<=iRowCountCover;iRowCounterCover++)
		{
			try {


				AgeRange=Utilities.getCellData(iRowCounterCover,0);
				if(AgeRange.contains("Up to "))
				{
					String[] arrAges = AgeRange.split("Up to ");
					AgeRangeU=arrAges[1];
					AgeRangeL=arrAges[1];

				}
				else if(AgeRange.contains("-"))
				{
					String[] arrAges = AgeRange.split("-");
					AgeRangeU=arrAges[1];
					AgeRangeL=arrAges[0];
				}

				objRADtls = Utilities.readTestData(ReadTestDataRates_path,RatesheetName, AgeRange);

				ExpectedValue=objRADtls.get("RAND5000");
				setCover("RAND5000");
				checkrates(AgeRangeU,AgeRangeL,ExpectedValue,SubMenu,"RAND5000");

				ExpectedValue=objRADtls.get("RAND10000");
				setCover("RAND10000");
				checkrates(AgeRangeU,AgeRangeL,ExpectedValue,SubMenu,"RAND10000");

				ExpectedValue=objRADtls.get("RAND15000");
				setCover("RAND15000");
				checkrates(AgeRangeU,AgeRangeL,ExpectedValue,SubMenu,"RAND15000");

				ExpectedValue=objRADtls.get("RAND20000");
				setCover("RAND20000");
				checkrates(AgeRangeU,AgeRangeL,ExpectedValue,SubMenu,"RAND20000");

				ExpectedValue=objRADtls.get("RAND25000");
				setCover("RAND25000");
				checkrates(AgeRangeU,AgeRangeL,ExpectedValue,SubMenu,"RAND25000");

				ExpectedValue=objRADtls.get("RAND30000");
				setCover("RAND30000");
				checkrates(AgeRangeU,AgeRangeL,ExpectedValue,SubMenu,"RAND30000");

				ExpectedValue=objRADtls.get("RAND40000");
				setCover("RAND40000");
				checkrates(AgeRangeU,AgeRangeL,ExpectedValue,SubMenu,"RAND40000");

				ExpectedValue=objRADtls.get("RAND50000");
				setCover("RAND50000");
				checkrates(AgeRangeU,AgeRangeL,ExpectedValue,SubMenu,"RAND50000");

				ExpectedValue=objRADtls.get("RAND60000");
				setCover("RAND60000");
				checkrates(AgeRangeU,AgeRangeL,ExpectedValue,SubMenu,"RAND60000");

				ExpectedValue=objRADtls.get("RAND70000");
				setCover("RAND70000");
				checkrates(AgeRangeU,AgeRangeL,ExpectedValue,SubMenu,"RAND70000");
				setCover("Default");


			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}






	}

	public static void MyExtendedCover(String SubMenu) throws Exception
	{

		RatesheetName="Extended";
		Utilities.setExcelFile(TestDataRates_path,RatesheetName);

		int iRowCountCover = Utilities.getRowNum();
		for(int iRowCounterCover=1;iRowCounterCover<=iRowCountCover;iRowCounterCover++)
		{
			try {


				AgeRange=Utilities.getCellData(iRowCounterCover,0);
				if(AgeRange.contains("Up to "))
				{
					String[] arrAges = AgeRange.split("Up to ");
					AgeRangeU=arrAges[1];
					AgeRangeL=arrAges[1];

				}
				else if(AgeRange.contains("-"))
				{
					String[] arrAges = AgeRange.split("-");
					AgeRangeU=arrAges[1];
					AgeRangeL=arrAges[0];
				}

				objRADtls = Utilities.readTestData(ReadTestDataRates_path,RatesheetName, AgeRange);

				ExpectedValue=objRADtls.get("RAND5000");
				setCover("RAND5000");
				checkrates(AgeRangeU,AgeRangeL,ExpectedValue,SubMenu,"RAND5000");

				ExpectedValue=objRADtls.get("RAND10000");
				setCover("RAND10000");
				checkrates(AgeRangeU,AgeRangeL,ExpectedValue,SubMenu,"RAND10000");

				ExpectedValue=objRADtls.get("RAND15000");
				setCover("RAND15000");
				checkrates(AgeRangeU,AgeRangeL,ExpectedValue,SubMenu,"RAND15000");

				ExpectedValue=objRADtls.get("RAND20000");
				setCover("RAND20000");
				checkrates(AgeRangeU,AgeRangeL,ExpectedValue,SubMenu,"RAND20000");

				ExpectedValue=objRADtls.get("RAND25000");
				setCover("RAND25000");
				checkrates(AgeRangeU,AgeRangeL,ExpectedValue,SubMenu,"RAND25000");

				ExpectedValue=objRADtls.get("RAND30000");
				setCover("RAND30000");
				checkrates(AgeRangeU,AgeRangeL,ExpectedValue,SubMenu,"RAND30000");
				setCover("Default");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}


		}

	}
}
