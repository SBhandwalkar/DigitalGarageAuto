package com.npw.testscript.BAU;

import static io.restassured.RestAssured.when;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.npw.locators.RA.BAULocators;
import com.om.framework.basetest.BaseTest;
import com.om.framework.lib.Browser;
import com.om.framework.lib.Elements;
import com.om.framework.lib.Utilities;

import io.restassured.response.Response;


public class NPW_CheckHeaderFooterLinksTest extends BaseTest {

	private static String TestData_path= "TestData\\NPW_SmokePack_TestData.xlsx";
	private static String sheetName="Sites";
	private static String sUrl;
	private static List<WebElement> headerLinks;
	private static List<WebElement> footerLinks;
	private static String sHeaderUrl;

	@Test
	public static void CheckHerderFooter() throws Exception
	{


		try {
			Utilities.setExcelFile(TestData_path,sheetName);

			int iRowCount = Utilities.getRowNum();
			for(int iRowCounter=1;iRowCounter<=iRowCount;iRowCounter++)
			{
				sUrl=Utilities.getCellData(iRowCounter,0);
				Browser.navigateTo(driver,sUrl);

				headerLinks=Elements.getWebElements(By.xpath(BAULocators.LandingPage.HeaderMenu()));
				footerLinks=Elements.getWebElements(By.xpath(BAULocators.LandingPage.FooterMenu()));


				for(int i=0;i<headerLinks.size()-1;i++) 
				{
					sHeaderUrl=headerLinks.get(i).getAttribute("href");
					Response res= when().get(sHeaderUrl);
					System.out.println(sHeaderUrl);
					System.out.println(res.getStatusCode());

				}
				
				for(int j=0;j<headerLinks.size()-1;j++) 
				{
					sHeaderUrl=headerLinks.get(j).getAttribute("href");
					Response res= when().get(sHeaderUrl);
					System.out.println(sHeaderUrl);
					System.out.println(res.getStatusCode());

				}
			}
			
			Utilities.closeexcel(TestData_path);
		}

		catch(Exception e) 
		{
			Utilities.closeexcel(TestData_path);
		}

	}

}
