package com.npw.testscript.BAU;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.npw.locators.RA.BAULocators;
import com.om.framework.basetest.BaseTest;
import com.om.framework.lib.Elements;

public class TC01_Personal_Loan_Calcutalor extends BaseTest {
	private static boolean bStatus;
	private static String Rate;
	
	@Test
	public static void TC_VerifyCalculations() throws InterruptedException
	{
		
		bStatus=Elements.clickElement(By.xpath(BAULocators.PersonalLoan.dropDownGeneric()));
		if(!bStatus) return ;
		Thread.sleep(1000);
		bStatus=Elements.clickElement(By.xpath(BAULocators.PersonalLoan.selectdropDownGeneric("R200 000")));
		Thread.sleep(2000);
		bStatus=Elements.clickElement(By.xpath(BAULocators.PersonalLoan.btnPLCGeneric("Next")));
		Thread.sleep(4000);
		bStatus=Elements.clickElement(By.xpath(BAULocators.PersonalLoan.selectdropDownMonts()));
		
		Thread.sleep(1000);
		bStatus=Elements.clickElement(By.xpath(BAULocators.PersonalLoan.selectdropDownGeneric("84 Months")));
		

		bStatus=Elements.clickElement(By.xpath(BAULocators.PersonalLoan.btnPLCGeneric("Calculate")));
		
		bStatus=Elements.clickElement(By.xpath(BAULocators.PersonalLoan.SelectSummary()));
		
		Rate=Elements.getText(By.xpath(BAULocators.PersonalLoan.SelectSummary()));
		
		
		
		
		
		
		
		
	}

}
