package com.npw.testscript.BAU;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.npw.lib.RA.CommonFunc;
import com.npw.locators.RA.BAULocators;

import com.om.framework.basetest.BaseTest;
import com.om.framework.lib.Browser;
import com.om.framework.lib.Elements;
import com.om.framework.lib.Utilities;

public class MIG_Verify_IncomeTax_CalculatorTest extends BaseTest {

	private static String sUrl = "https://new-public-web-mig1-qa.nonprod.digitalplatform.oldmutual.co.za/personal/solutions/income-tax-test";
	private static boolean bStatus;
	private static String TestData_path= "TestData\\ITCalculator_TestData.xlsx";
	private static String sheetName="Advanced";
	private static String sAge;
	private static String sPaymentFreq;
	private static String sSalary , sOtherIncome,sDeductionOIncome,sPention,sEmpPention,sPF,sEmpPF,sMedicalAid,sRAC,sEmpMAid,sDependants;
	private static String sMonthlyITax;
	private static String sNetIncome,sDisablity;



	@Test
	public static void IncomeTaxCalculator() throws Exception {

		Utilities.setExcelFile(TestData_path,sheetName);
		bStatus=Elements.clickElement(By.xpath(BAULocators.IncomeTax.btnGeneric("Advanced")));


		Thread.sleep(2000);

		int iRowCount = Utilities.getRowNum();
		for(int iRowCounter=1;iRowCounter<=iRowCount;iRowCounter++)
		{
			
			sAge=Utilities.getCellData(iRowCounter,0);
			sPaymentFreq=Utilities.getCellData(iRowCounter,1);
			sSalary=Utilities.getCellData(iRowCounter,2);
			sOtherIncome=Utilities.getCellData(iRowCounter,3);
			sDeductionOIncome=Utilities.getCellData(iRowCounter,4);
			sPention=Utilities.getCellData(iRowCounter,5);
			sEmpPention=Utilities.getCellData(iRowCounter,6);
			sPF=Utilities.getCellData(iRowCounter,7);
			sEmpPF=Utilities.getCellData(iRowCounter,8);
			sMedicalAid=Utilities.getCellData(iRowCounter,9);
			sEmpMAid=Utilities.getCellData(iRowCounter,10);
			sRAC=Utilities.getCellData(iRowCounter,11);
			sDisablity=Utilities.getCellData(iRowCounter,12);
			sDependants=Utilities.getCellData(iRowCounter,13);
			sMonthlyITax=Utilities.getCellData(iRowCounter,14);
			sNetIncome=Utilities.getCellData(iRowCounter,15);

			//Browser.navigateTo(driver,sUrl);
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
		    
			sSalary=sSalary.replace(",", ".");
			sMonthlyITax=sMonthlyITax.replace(",", ".");
			sNetIncome=sNetIncome.replace(",", ".");
			
			Thread.sleep(2000);
			//CommonFunc.scrollToViewElement(By.xpath(BAULocators.IncomeTax.radioBtnAges(sAge)));
			bStatus=Elements.movetoclickElement(By.xpath(BAULocators.IncomeTax.radioBtnAges(sAge)));
			if(!bStatus) return ;
			Thread.sleep(3000);
			bStatus=Elements.clickElement(By.xpath(BAULocators.IncomeTax.dropDownPaidFreq()));
			if(!bStatus) return ;
			Thread.sleep(1000);
			bStatus=Elements.clickElement(By.xpath(BAULocators.IncomeTax.selectdropDownPaidFreq(sPaymentFreq)));
			if(!bStatus) return ;
			//Thread.sleep(2000);
			bStatus=Elements.enterText(By.xpath(BAULocators.IncomeTax.inputText("How much do you earn before tax? *")),sSalary);
			if(!bStatus) return ;
			//Thread.sleep(1000);
			bStatus=Elements.clickElement(By.xpath(BAULocators.IncomeTax.addicon("Other income (optional)")));
			if(!bStatus) return ;
			//Thread.sleep(2000);
			bStatus=Elements.enterText(By.xpath(BAULocators.IncomeTax.inputforattribute("otherIncomeValue")),sOtherIncome);
			if(!bStatus) return ;
			//Thread.sleep(1000);
			bStatus=Elements.enterText(By.xpath(BAULocators.IncomeTax.inputforattribute("otherIncomeDeduction")),sDeductionOIncome);
			if(!bStatus) return ;
			//Thread.sleep(1000);





			bStatus=Elements.movetoclickElement(By.xpath(BAULocators.IncomeTax.addicon("Medical aid (optional)")));
			if(!bStatus) return ;
			//Thread.sleep(2000);

			bStatus=Elements.enterText(By.xpath(BAULocators.IncomeTax.inputforattribute("medicalAidOwnContribution")),sMedicalAid);
			if(!bStatus) return ;
			//Thread.sleep(1000);
			bStatus=Elements.enterText(By.xpath(BAULocators.IncomeTax.inputforattribute("medicalAidEmployerContribution")),sEmpMAid);
			//if(!bStatus) return ;
			//Thread.sleep(1000);
			bStatus=Elements.enterText(By.xpath(BAULocators.IncomeTax.inputforattribute("medicalAidDependents")),sDependants);
			if(!bStatus) return ;
			//Thread.sleep(1000);





			bStatus=Elements.movetoclickElement(By.xpath(BAULocators.IncomeTax.addicon("Pension fund (optional)")));
			if(!bStatus) return ;
			//Thread.sleep(2000);

			bStatus=Elements.enterText(By.xpath(BAULocators.IncomeTax.inputforattribute("pensionFunddOwnContribution")),sPention);
			if(!bStatus) return ;
			//Thread.sleep(1000);
			bStatus=Elements.enterText(By.xpath(BAULocators.IncomeTax.inputforattribute("pensionFunddEmployerContribution")),sEmpPention);
			if(!bStatus) return ;
			//Thread.sleep(1000);



			bStatus=Elements.movetoclickElement(By.xpath(BAULocators.IncomeTax.addicon("Provident fund (optional)")));
			if(!bStatus) return ;
			//Thread.sleep(2000);

			bStatus=Elements.enterText(By.xpath(BAULocators.IncomeTax.inputforattribute("providentFundOwnContributionValue")),sPF);
			if(!bStatus) return ;
			//Thread.sleep(1000);

			bStatus=Elements.enterText(By.xpath(BAULocators.IncomeTax.inputforattribute("providentFundEmployerContribution")),sEmpPF);
			if(!bStatus) return ;
			//Thread.sleep(1000);



			bStatus=Elements.movetoclickElement(By.xpath(BAULocators.IncomeTax.addicon("Retirement annuity (optional)")));
			if(!bStatus) return ;
			//Thread.sleep(1000);

			bStatus=Elements.enterText(By.xpath(BAULocators.IncomeTax.inputforattribute("retirementAnnuityValue")),sRAC);
			if(!bStatus) return ;
			//Thread.sleep(1000);



			Thread.sleep(2000);
			bStatus=Elements.movetoclickElement(By.xpath(BAULocators.IncomeTax.btnCalculate()));

			Thread.sleep(1000);

			
			//bStatus=Elements.movetoclickElement(By.xpath(BAULocators.IncomeTax.netsalary()));
			String sNetSal=Elements.getText(By.xpath(BAULocators.IncomeTax.netsalary()));
			//bStatus=Elements.movetoclickElement(By.xpath(BAULocators.IncomeTax.incometax()));
			
			String sIT=Elements.getText(By.xpath(BAULocators.IncomeTax.incometax()));

			sNetSal=sNetSal.replace("R", "");
			sNetSal=sNetSal.replace(" ", "");
			sIT=sIT.replace("R", "");
			sIT=sIT.replace(" ", "");

			if(sNetSal.contains(sNetIncome) && sIT.contains(sMonthlyITax)) 
			{
				Utilities.setCellData("Pass",iRowCounter,18);
				Utilities.setCellData(sNetSal,iRowCounter,16);
				Utilities.setCellData(sIT,iRowCounter,17);

			}
			else
			{
				Utilities.setCellData("Fail",iRowCounter,18);
				Utilities.setCellData(sNetSal,iRowCounter,16);
				Utilities.setCellData(sIT,iRowCounter,17);
			}



			



			bStatus=Elements.movetoclickElement(By.xpath(BAULocators.IncomeTax.btnReCalculate()));
			
			Thread.sleep(2000);


		}


		Utilities.closeexcel(TestData_path);










	}


}
