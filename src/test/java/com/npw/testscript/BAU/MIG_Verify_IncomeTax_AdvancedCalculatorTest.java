package com.npw.testscript.BAU;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.npw.locators.RA.BAULocators;

import com.om.framework.basetest.BaseTest;
import com.om.framework.lib.Elements;
import com.om.framework.lib.Utilities;

public class MIG_Verify_IncomeTax_AdvancedCalculatorTest extends BaseTest {
	
	private static String sUrl = "https://www.oldmutual.co.za/personal/tools-and-calculators/income-tax-calculator";
	private static boolean bStatus;
	private static String TestData_path= "TestData\\ITCalculator_TestData.xlsx";
	private static String sheetName="Basic";
	private static String sAge;
	private static String sPaymentFreq;
	private static String sSalary;
	private static String sMonthlyITax;
	private static String sNetIncome;
	
	
	@Test
	public static void IncomeTaxCalculator() throws Exception {
		
		Utilities.setExcelFile(TestData_path,sheetName);		

		int iRowCount = Utilities.getRowNum();
		for(int iRowCounter=1;iRowCounter<=iRowCount;iRowCounter++)
		{
			
			sAge=Utilities.getCellData(iRowCounter,0);
			sPaymentFreq=Utilities.getCellData(iRowCounter,1);
			sSalary=Utilities.getCellData(iRowCounter,2);
			sMonthlyITax=Utilities.getCellData(iRowCounter,3);
			sNetIncome=Utilities.getCellData(iRowCounter,4);
			sMonthlyITax=sMonthlyITax.replace(",", ".");
			sNetIncome=sNetIncome.replace(",", ".");
			
			
			
			bStatus=Elements.clickElement(By.xpath(BAULocators.IncomeTax.radioBtnAges(sAge)));
			if(!bStatus) return ;
			Thread.sleep(2000);
			bStatus=Elements.clickElement(By.xpath(BAULocators.IncomeTax.dropDownPaidFreq()));
			if(!bStatus) return ;
			Thread.sleep(2000);
			bStatus=Elements.clickElement(By.xpath(BAULocators.IncomeTax.selectdropDownPaidFreq(sPaymentFreq)));
			if(!bStatus) return ;
			Thread.sleep(2000);
			bStatus=Elements.enterText(By.xpath(BAULocators.IncomeTax.inputText("How much do you earn before tax? *")),sSalary);
			if(!bStatus) return ;
			Thread.sleep(1000);
			bStatus=Elements.clickElement(By.xpath(BAULocators.IncomeTax.btnGeneric("CALCULATE")));
		
			Thread.sleep(2000);
			
			
			
			String sNetSal=Elements.getElementAttribute(By.xpath(BAULocators.IncomeTax.netsalary()), "secondary-payment-value");
			String sIT=Elements.getElementAttribute(By.xpath(BAULocators.IncomeTax.incometax()), "main-payment-value");
			
			sNetSal=sNetSal.replace("R", "");
			sNetSal=sNetSal.replace(" ", "");
			sIT=sIT.replace("R", "");
			sIT=sIT.replace(" ", "");
			
			if(sNetSal.contains(sMonthlyITax)) 
			{
				System.out.println("Pass:   "+sNetSal);
			}
			else
			{
				System.out.println("Fail:   "+sNetSal);
			}
			
			
			if(sIT.contains(sNetIncome)) 
			{
				System.out.println("Pass:   "+sIT);
			}
			else
			{
				System.out.println("Fail:   "+sIT);
			}
			
			
			
			
			bStatus=Elements.clickElement(By.xpath(BAULocators.IncomeTax.btnGeneric("RECALCULATE")));
			if(!bStatus) return ;
			Thread.sleep(2000);
			
			
		}
		
		
		
	
		
		
		
		
		
		
		
		
		
	}


}
