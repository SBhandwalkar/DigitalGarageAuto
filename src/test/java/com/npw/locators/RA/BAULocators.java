package com.npw.locators.RA;


public class BAULocators {

	public static String sXpath = "";


	public static class FundCenter
	{
		public static String radioBtnLifeFund() {

			sXpath="//input[@id='cphMainContent_C003_rbFundTypes_1' and @value='1']";
			return sXpath;
		}


		public static String IndividualFund() {

			sXpath="//div[@id='cphMainContent_C003_FundCategories_chosen']";
			return sXpath;
		}

		public static String SelectIndividualFund(String Fund) {

			sXpath="//ul[@class ='chosen-results']/li[text()='"+Fund+"']";
			return sXpath;
		}



		public static String FundOption() {

			sXpath="//div[@id='cphMainContent_C003_FundOptions_chosen']";
			return sXpath;

		}

		public static String SelectFundOption(String Fund) {

			sXpath="//ul[@class ='chosen-results']/li[text()='"+Fund+"']";
			return sXpath;
		}


	}//End Fund Centre Locators

	
//IncomeTax Locators	
	public static class IncomeTax
	{

		public static String radioBtnAges(String Stext) 
		{

			sXpath="//om-radio-button[contains(@radio-button-text,'"+Stext.trim()+"')]";
			return sXpath;
		}

		public static String dropDownPaidFreq(String sFieldName, String sValue)
		{
			sXpath="//span[contains(text(),'"+sFieldName+"')]//..//ul[@class='dropdown-options-list']//li[text()='"+sValue+"']";
			return sXpath;

		}

		public static String dropDownPaidFreq () 
		{

			sXpath="//div[@class='om-dropdown-field']";
			return sXpath;
		}

		public static String selectdropDownPaidFreq(String sValue)
		{
			sXpath="//ul[@class='dropdown-options-list']//li[text()='"+sValue+"']";
			return sXpath;

		}
		public static String inputText(String Stext)
		{

			sXpath="//input[((@type='text') and (preceding-sibling::span[text()='"+Stext+"'] or @placeholder='"+Stext+"')) or "
					+ "ancestor::om-input-field[contains(@preset,'"+Stext+"') or contains(@placeholder,'"+Stext+"')]]";

			return sXpath;
		}
		public static String btnGeneric(String btnContinue)
		{
			sXpath="//*[text()='"+btnContinue+"']";
			return sXpath;
		}


		public static String netsalary()
		{
			sXpath="/html/body/om-wc-config/om-product-detail-page/om-page/div/div/div/div/main/div/section[3]/om-application-container/div/om-1-col-layout/div/om-section/div/div/div[2]/div/om-itc-container/div/div/div[2]/div/div/div[1]/section[3]/table/tr/td[2]/div/div/om-calculator-result-card[1]/div/div/div[1]/div[3]/table/tr/td[2]/table/tr[2]/td/span[1]";
			return sXpath;
		}

		public static String incometax()
		{
			sXpath="/html/body/om-wc-config/om-product-detail-page/om-page/div/div/div/div/main/div/section[3]/om-application-container/div/om-1-col-layout/div/om-section/div/div/div[2]/div/om-itc-container/div/div/div[2]/div/div/div[1]/section[3]/table/tr/td[2]/div/div/om-calculator-result-card[1]/div/div/div[1]/div[2]/table/tr/td[2]";
			return sXpath;
		}


		public static String addicon(String icon)
		{
			sXpath="//om-itc-accordion-form-fields[@category='"+icon+"']//td[@class='left-cell']";
			return sXpath;
		}

		public static String inputforattribute(String attribute)
		{
			sXpath="//input[@for ='"+attribute+"']";
			return sXpath;
		}

		public static String btnCalculate()
		{
			sXpath="/html/body/om-wc-config/om-product-detail-page/om-page/div/div/div/div/main/div/section[3]/om-application-container/div/om-1-col-layout/div/om-section/div/div/div[2]/div/om-itc-container/div/div/div[2]/div/div/div[1]/section[3]/table/tr/td[1]/div[1]/om-button/button/span[1]";
			return sXpath;
		}

		public static String btnReCalculate()
		{
			sXpath="/html/body/om-wc-config/om-product-detail-page/om-page/div/div/div/div/main/div/section[3]/om-application-container/div/om-1-col-layout/div/om-section/div/div/div[2]/div/om-itc-container/div/div/div[2]/div/div/div[1]/section[3]/table/tr/td[2]/div/div/om-calculator-result-card[1]/div/div/div[1]/div[3]/table/tr/td[2]/table/tr[2]/td/span[2]/om-button/button/span";
			return sXpath;
		}




	}

	//IncomeTax Locators	
	
	public static class PersonalLoan
	{
		
		public static String inputText(String Stext)
		{

			sXpath="//input[((@type='text') and (preceding-sibling::span[text()='"+Stext+"'] or @placeholder='"+Stext+"')) or "
					+ "ancestor::om-input-field[contains(@preset,'"+Stext+"') or contains(@placeholder,'"+Stext+"')]]";

			return sXpath;
		}
		
		public static String btnPLCGeneric(String btnContinue)
		{
			sXpath="//div[@id='web-container']//*[text()='"+btnContinue+"']";
			return sXpath;
		}
		
		
		public static String dropDownGeneric() 
		{

			sXpath="//div[@class='om-dropdown-field']";
			return sXpath;
		}

		public static String selectdropDownGeneric(String sValue)
		{
			sXpath="//ul[@class='dropdown-options-list']//li[text()='"+sValue+"']";
			return sXpath;

		}
		
		public static String selectdropDownMonts()
		{
			sXpath="//*[@id='repaymentDuration']//div[@class='om-dropdown-field']";
			return sXpath;

		}
		
		public static String SelectSummary()
		{
			sXpath="//div[@id='web-container']//om-icon[@icon-name='help_outline']";
			return sXpath;

		}
		
		
		
		
		
		
	}


}
