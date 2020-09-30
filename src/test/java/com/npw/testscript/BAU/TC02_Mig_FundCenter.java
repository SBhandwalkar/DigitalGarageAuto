package com.npw.testscript.BAU;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.om.framework.lib.Elements;

public class TC02_Mig_FundCenter {

	private static List<WebElement> FundsLinks;
	private static WebElement Icon;
	
	
	@Test
	public static void VerifyFundsMig() throws Exception
	{
		
		FundsLinks=Elements.getWebElements(By.xpath("//span[@class='fund-title']//a"));
		Icon= Elements.getWebElement(By.xpath("//span[@class='fund-title']//a[contains(text(),'Old Mutual Flexible Fund')]//..//..//om-icon"));
	}
	
}
