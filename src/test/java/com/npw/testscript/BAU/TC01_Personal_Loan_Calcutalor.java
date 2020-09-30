package com.npw.testscript.BAU;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.npw.locators.RA.BAULocators;
import com.om.framework.basetest.BaseTest;
import com.om.framework.lib.Elements;
import com.om.framework.lib.Utilities;

public class TC01_Personal_Loan_Calcutalor extends BaseTest {
	private static boolean bStatus;
	private static String sTotalLoanPayment,sMonthlyRepayment,sMonthlyRepaymentMin,sMonthlyRepaymentMax,sTerm,sCapital,sInitiationFee,sServiceFee,sInstallments,sInterstRates,sCreditLifeInsurance;
	private static String TestData_path= "TestData\\PersonalLoan_TestData.xlsx";
	private static String  sInstallmentsMin,sInstallmentsMax,sInterstRatesMin,sInterstRatesMax,sCreditLifeInsuranceMin,sCreditLifeInsuranceMax;
	private static String sheetName="Rates";
	private static String sExpTotalLoanPayment,sExpMonthlyRepaymentMin,sExpMonthlyRepaymentMax,sExpTerm,sOwnExpTerm,sExpCapital,sExpInitiationFee,sExpServiceFee,sExpInstallmentsMin,sExpInstallmentsMax,sExpInterstRatesMin,sExpInterstRatesMax,sExpCreditLifeInsuranceMin,sExpCreditLifeInsuranceMax;

	@Test
	public static void TC_VerifyCalculations() throws Exception
	{

		Utilities.setExcelFile(TestData_path,sheetName);
		int iRowCount = Utilities.getRowNum();
		for(int iRowCounter=1;iRowCounter<=iRowCount;iRowCounter++)
		{
			sExpCapital=Utilities.getCellData(iRowCounter,0);
			sExpTerm=Utilities.getCellData(iRowCounter,1);
			sOwnExpTerm=sExpTerm;
			sExpServiceFee=Utilities.getCellData(iRowCounter,2);
			sExpInitiationFee=Utilities.getCellData(iRowCounter,3);
			sExpInstallmentsMin=Utilities.getCellData(iRowCounter,4);
			sExpInstallmentsMax=Utilities.getCellData(iRowCounter,5);
			sExpInterstRatesMin=Utilities.getCellData(iRowCounter,6);
			sExpInterstRatesMax=Utilities.getCellData(iRowCounter,7);
			sExpCreditLifeInsuranceMin=Utilities.getCellData(iRowCounter,8);
			sExpCreditLifeInsuranceMax=Utilities.getCellData(iRowCounter,9);
			sExpMonthlyRepaymentMin=Utilities.getCellData(iRowCounter,10);
			sExpMonthlyRepaymentMax=Utilities.getCellData(iRowCounter,11);

			/*
			 * sExpInitiationFee=sExpInitiationFee.replace(",", ".");
			 * sExpInstallmentsMin=sExpInstallmentsMin.replace(",", ".");
			 * sExpInstallmentsMax=sExpInstallmentsMax.replace(",", ".");
			 * sExpInterstRatesMin=sExpInterstRatesMin.replace(",", ".");
			 * sExpInterstRatesMax=sExpInterstRatesMax.replace(",", ".");
			 * sExpInterstRatesMin=sExpInterstRatesMin.replace("%", "");
			 * sExpInterstRatesMax=sExpInterstRatesMax.replace("%", "");
			 * 
			 * sExpCreditLifeInsuranceMin=sExpCreditLifeInsuranceMin.replace(",", ".");
			 * sExpCreditLifeInsuranceMax=sExpCreditLifeInsuranceMax.replace(",", ".");
			 */




			




			bStatus=Elements.clickElement(By.xpath(BAULocators.PersonalLoan.dropDownGeneric()));
			if(!bStatus) return ;
			Thread.sleep(1000);
			bStatus=Elements.clickElement(By.xpath(BAULocators.PersonalLoan.selectdropDownGeneric("Own amount")));
			bStatus=Elements.enterText(By.xpath(BAULocators.IncomeTax.inputText("Enter your own loan amount")),sExpCapital);
			if(!bStatus) return ;


			Thread.sleep(1000);
			bStatus=Elements.clickElement(By.xpath(BAULocators.PersonalLoan.btnPLCGeneric("Next")));
			Thread.sleep(1000);
			bStatus=Elements.clickElement(By.xpath(BAULocators.PersonalLoan.selectdropDownMonts()));
			Thread.sleep(1000);


			if(sExpTerm.contentEquals("1"))
			{
				sExpTerm= "1 Month";
			}
			else
			{
				sExpTerm = sExpTerm + " Months";
			}

			bStatus=Elements.clickElement(By.xpath(BAULocators.PersonalLoan.selectdropDownGeneric(sExpTerm)));
			
			if(!bStatus)
			{
				bStatus=Elements.clickElement(By.xpath(BAULocators.PersonalLoan.selectdropDownGeneric("Own number of months")));
				Thread.sleep(2000);
				bStatus=Elements.enterText(By.xpath(BAULocators.PersonalLoan.inputTextRepay()),sOwnExpTerm);
				
			}


			bStatus=Elements.clickElement(By.xpath(BAULocators.PersonalLoan.btnPLCGeneric("Calculate")));

			Thread.sleep(2000);
			sTotalLoanPayment=Elements.getElementAttribute(By.xpath(BAULocators.PersonalLoan.ResultCard()),"secondary-payment-value");
			sMonthlyRepayment=Elements.getElementAttribute(By.xpath(BAULocators.PersonalLoan.ResultCard()),"main-payment-value");

			sMonthlyRepayment=sMonthlyRepayment.replace(" ", "");
			String arrmonthlyrepay[]=sMonthlyRepayment.split("-");
			sMonthlyRepaymentMin=arrmonthlyrepay[0];
			sMonthlyRepaymentMax=arrmonthlyrepay[1];


			bStatus=Elements.clickElement(By.xpath(BAULocators.PersonalLoan.SelectSummary()));
			

			sTerm=Elements.getText(By.xpath(BAULocators.PersonalLoan.MonthlyRepayment("Term:")));
			sCapital=Elements.getText(By.xpath(BAULocators.PersonalLoan.MonthlyRepayment("Capital:")));
			sCapital=sCapital.replace(" ", "");
			sInitiationFee=Elements.getText(By.xpath(BAULocators.PersonalLoan.MonthlyRepayment("Initiation Fee:")));
			sInitiationFee=sInitiationFee.replace(" ", "");
			sServiceFee=Elements.getText(By.xpath(BAULocators.PersonalLoan.MonthlyRepayment("Service Fee:")));
			sInstallments=Elements.getText(By.xpath(BAULocators.PersonalLoan.MonthlyRepayment("Instalment:")));
			sInterstRates=Elements.getText(By.xpath(BAULocators.PersonalLoan.MonthlyRepayment("Interest Rate:")));
			sCreditLifeInsurance=Elements.getText(By.xpath(BAULocators.PersonalLoan.MonthlyRepayment("Credit Life Insurance")));

			String arrinstallments[]=sInstallments.split("-");
			sInstallmentsMin=arrinstallments[0];
			sInstallmentsMax=arrinstallments[1];


			if(sInterstRates.contains("-"))
			{
				String arrInterstRates[]=sInterstRates.split("-");
				sInterstRatesMin=arrInterstRates[0];
				sInterstRatesMax= arrInterstRates[1];
			}
			else
			{
				sInterstRatesMin=sInterstRates;
				sInterstRatesMax= sInterstRates;
			}

			String arrCreditLifeInsurance[]=sCreditLifeInsurance.split("-");

			sCreditLifeInsuranceMin=arrCreditLifeInsurance[0];
			sCreditLifeInsuranceMax=arrCreditLifeInsurance[1];
			
			
			if(sTerm.contains(sOwnExpTerm) && sCapital.contains(sExpCapital) && sInitiationFee.contains(sExpInitiationFee)&& sServiceFee.contains(sExpServiceFee)&&sMonthlyRepaymentMin.contains(sExpMonthlyRepaymentMin)&&sMonthlyRepaymentMax.contains(sExpMonthlyRepaymentMax) ) 
			{
				
					if(sInstallmentsMin.contains(sExpInstallmentsMin) && sInstallmentsMax.contains(sExpInstallmentsMax)&& sInterstRatesMin.contains(sExpInterstRatesMin)&& sInterstRatesMax.contains(sExpInterstRatesMax)&& sCreditLifeInsuranceMin.contains(sExpCreditLifeInsuranceMin)&& sCreditLifeInsuranceMax.contains(sExpCreditLifeInsuranceMax) )
				{
					Utilities.setCellData("Pass",iRowCounter,12);
					System.out.println("Pass");
				}
				
			}
			
			


			bStatus=Elements.clickElement(By.xpath(BAULocators.PersonalLoan.CloseIcon()));
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
			
			Thread.sleep(2000);
			bStatus=Elements.clickElement(By.xpath(BAULocators.PersonalLoan.btnPLCGeneric("Recalculate")));
			Thread.sleep(2000);
		
			js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");




		}


		Utilities.closeexcel(TestData_path);

	}

}
