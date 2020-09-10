package com.npw.lib.RA;
import java.util.Map;
import org.openqa.selenium.By;

import com.npw.locators.RA.Locators;
import com.om.framework.basetest.BaseTest;
import com.om.framework.lib.Elements;
import com.om.framework.lib.Messages;
import com.om.framework.lib.Verify;
import com.om.framework.lib.Wait;
import com.om.framework.reporting.Reporting;




public class RA extends BaseTest
{
	private static boolean bStatus=true;
	private static int iSync=20;
	public static String sRAReferenceNum;

	public static boolean createRAApplication(Map<String,String> objRADtls)
	{

		try
		{

			//Step 1 : fill LeadTracker
			bStatus = enterLeadTrackerDtls(objRADtls);
			if(!bStatus) return bStatus;
			//Step 2 : Enter investment details page
			bStatus= enterInvestmentDetails(objRADtls);
			if(!bStatus) return bStatus;
			//Step 3 : Split your investment
			bStatus=splitYourInvestment();
			if(!bStatus) return bStatus;
			//Step 4 : Personal details
			bStatus=personalDetails(objRADtls);
			if(!bStatus) return bStatus;
			//Step 5 : Additional details
			bStatus=additionalDetails();
			if(!bStatus) return bStatus;
			//Step 6 : beneficiaryPage
			bStatus=beneficiaryPage();
			if(!bStatus) return bStatus;
			//Step 7 Final checks
			bStatus=finalChecks(objRADtls);
			if(!bStatus) return bStatus;
			//Step 8 paymentDetails
			bStatus=paymentDetails(objRADtls);
			if(!bStatus) return bStatus;
			//Step 9 paymentDetails
			bStatus=confirmationPage();
			if(!bStatus) return bStatus;

			sRAReferenceNum = "";

		}
		catch(Exception e)
		{

		}

		return bStatus;
	}



	public static boolean verifyInvalidDataLeadTrckr(Map<String,String>objInvalidNumData, Map<String,String>objInvalidNumError)
	{

		try
		{
			bStatus=enterLeadTrackerDtls(objInvalidNumData);
			if(!bStatus)
			{	
				bStatus=validateFieldErrorMsg(objInvalidNumError);
				if(!bStatus) return bStatus;
			}
		}

		catch (Exception e) {

		}
		return bStatus;
	}
	
	

	/*******************************************************************************
	Function Name 					: validateFieldErrorMsg
	Description						: This  is generic method to validate all error messages on the RA app 
	Parameters						: Hash map read from excelsheet for expected error messages against fields
	User info or parameter data		:
										
	Created By						: Kalpit Jadhav
	Created On						: 16 Jan 2020

	 ******************************************************************************/
	
	public static boolean validateFieldErrorMsg(Map<String,String> objFieldsNErrors)
	{
		boolean status=true;
		String sObservations = "";

		String[] arrFields = objFieldsNErrors.get("FieldsToBeValidated").split(",");
		String[] arrErrorMsgs = objFieldsNErrors.get("ErrorMessages").split(",");

		for(int i=0;i<arrFields.length;i++)
		{
			
			bStatus = validateFieldErrorMsg(arrFields[i].trim(), arrErrorMsgs[i].trim());
			if(bStatus)
				sObservations = sObservations + "\n" + "Validation is succesful for the field :"+ arrFields[i] + " against the exp error message :"+arrErrorMsgs[i];
			else
			{
				status=false;
				sObservations = sObservations + "\n" + Messages.errorMsg +"\n";
			}
		}

		Messages.errorMsg = sObservations;
		return status;
	}

	// this method is generic across RA app for validating expected error message against actual one
	public static boolean validateFieldErrorMsg(String sFieldName, String sExpErrorMsg)
	{

		Messages.errorMsg = "";

		String sValidationErrorXpath = Locators.RaApp.inputText(sFieldName)+"/following-sibling::span[@class='validation-error-text']";

		String sActualErrorMsg = Elements.getText(By.xpath(sValidationErrorXpath));

		if(sActualErrorMsg.contains(sExpErrorMsg)) return true;
		else
		{
			Messages.errorMsg = "Expected error message is:"+sExpErrorMsg +" but actual error displayed is: "+sActualErrorMsg +".....due to"+Messages.errorMsg ;
			return false;
		}
	}

	public static boolean enterLeadTrackerDtls(Map<String, String> objLeadTrackerDtls)
	{
		try
		{
			//Wait for page to load
			bStatus=Wait.waitForElementVisibility(By.xpath(Locators.RaApp.clickDropDown(objLeadTrackerDtls.get("Title"))), iSync);
			//Select Title
			if(!bStatus) return bStatus;
			bStatus=CommonFunc.selectDropDown(objLeadTrackerDtls.get("Title"));
			if(!bStatus) return bStatus;
			//enter first name
			bStatus=Elements.enterText(By.xpath(Locators.RaApp.inputText("first-name")), objLeadTrackerDtls.get("FirstName"));
			if(!bStatus) return bStatus;
			//enter surname name
			bStatus=Elements.enterText(By.xpath(Locators.RaApp.inputText("surname")), objLeadTrackerDtls.get("LastName"));
			if(!bStatus) return bStatus;
			//enter Email
			bStatus=Elements.enterText(By.xpath(Locators.RaApp.inputText("Email")), objLeadTrackerDtls.get("Email"));
			if(!bStatus) return bStatus;
			//Enter Mobile number
			bStatus=Elements.enterText(By.xpath(Locators.RaApp.inputText("cellphone")), objLeadTrackerDtls.get("CellPhone"));
			if(!bStatus) return bStatus;
			//click continue
			bStatus=Elements.clickElement(By.xpath(Locators.RaApp.btnContinue("Continue")));
			if(!bStatus) return bStatus;
			//wait for next page to load
			bStatus=Wait.waitForElementVisibility(By.xpath(Locators.RaApp.subHeaderTitle("start your investment")), iSync);
			if(!bStatus) return bStatus;
			//Check next page loaded
			bStatus=Verify.verifyElementVisible(By.xpath(Locators.RaApp.subHeaderTitle("start your investment")));
			if(!bStatus) return bStatus;

			Reporting.logResults("Pass", "Enter lead tracker", "Succesfully entered data to leadtracker");

		}
		catch(Exception e)
		{

		}
		return bStatus;
	}

	public static boolean enterInvestmentDetails(Map<String,String> objInvestmentDtls) throws InterruptedException {
		try {

			//Enter date of birth
			bStatus=Elements.enterText(By.xpath(Locators.RaApp.inputText("date")), objInvestmentDtls.get("DateOfBirth"));
			if(!bStatus) return bStatus;
			//enter investment duration
			bStatus=CommonFunc.selectDropDown(objInvestmentDtls.get("investmentDuration"));
			if(!bStatus) return bStatus;
			//Enter Monthly Contribution
			bStatus=Elements.enterText(By.xpath(Locators.RaApp.inputText("currency")), objInvestmentDtls.get("MonthlyContribution"));
			if(!bStatus) return bStatus;
			//select radio button for How would you like to increase your payments?
			bStatus=Elements.clickElement(By.xpath(Locators.RaApp.radioBtnInvestmentPage("Increase")));
			if(!bStatus) return bStatus;
			//select % increase 
			bStatus=CommonFunc.selectDropDown("15%");
			if(!bStatus) return bStatus;

			//select investment plan 
			bStatus=CommonFunc.scrollToViewElement(By.xpath(Locators.RaApp.checkBoxInvestmentPage("Low Risk")));
			if(!bStatus) return bStatus;
			bStatus=Elements.clickElement(By.xpath(Locators.RaApp.checkBoxInvestmentPage("Low Risk")));
			if(!bStatus) return bStatus;
			bStatus=CommonFunc.scrollToViewElement(By.xpath(Locators.RaApp.checkBoxInvestmentPage("High Risk")));
			if(!bStatus) return bStatus;
			bStatus=Elements.clickElement(By.xpath(Locators.RaApp.checkBoxInvestmentPage("High Risk")));
			if(!bStatus) return bStatus;

			//click continue 
			bStatus=Elements.clickElement(By.xpath(Locators.RaApp.btnContinue("Continue")));
			if(!bStatus) return bStatus;

			//Check next page loaded
			bStatus=Wait.waitForElementVisibility(By.xpath(Locators.RaApp.subHeaderTitle("How would you like to split")), iSync);
			if(!bStatus) return bStatus;
			bStatus=Verify.verifyElementVisible(By.xpath(Locators.RaApp.subHeaderTitle("How would you like to split")));
			if(!bStatus) return bStatus;

			Reporting.logResults("Pass", "enterInvestmentDetails", "Succesfully entered data to Investment page");

		}
		catch(Exception e) {

		}
		return bStatus;
	}

	public static boolean splitYourInvestment() {

		try {
			//Enter split %
			bStatus=CommonFunc.enterTextForSplitPercentage(By.xpath(Locators.RaApp.enterSplitPercentage("Old Mutual Stable")), "40");
			if(!bStatus)  return bStatus;
			//Enter split %
			bStatus=CommonFunc.enterTextForSplitPercentage(By.xpath(Locators.RaApp.enterSplitPercentage("Old Mutual Edge")), "60");
			if(!bStatus)  return bStatus;
			//click continue 
			bStatus=Elements.clickElement(By.xpath(Locators.RaApp.btnContinue("Continue")));
			if(!bStatus) return bStatus;

			//Check next page loaded
			bStatus=Wait.waitForElementVisibility(By.xpath(Locators.RaApp.subHeaderTitle("Tell us about yourself")), iSync);
			if(!bStatus) return bStatus;
			bStatus=Verify.verifyElementVisible(By.xpath(Locators.RaApp.subHeaderTitle("Tell us about yourself")));
			if(!bStatus) return bStatus;

			Reporting.logResults("Pass", "splitYourInvestment", "Succesfully entered data to Split Investment page");
		}

		catch(Exception e) {

		}

		return bStatus;
	}

	public static boolean personalDetails(Map<String,String> objPersonalDtls) {
		try {
			//Enter Work contact Number
			bStatus=Elements.enterText(By.xpath(Locators.RaApp.inputText("Work Contact Number")),objPersonalDtls.get("WorkNumber"));
			if(!bStatus) return bStatus;
			//Select dropdown for Maritial status
			bStatus=CommonFunc.selectDropDown(objPersonalDtls.get("MaritialStatus"));
			if(!bStatus) return bStatus;
			//Select dropdown for country of birth
			bStatus=CommonFunc.selectDropDownPersonalDtls("Country of Birth", objPersonalDtls.get("countryOfBirth"));
			if(!bStatus) return bStatus;
			//Select dropdown for Nationality
			bStatus=CommonFunc.selectDropDownPersonalDtls("Nationality", objPersonalDtls.get("Nationality"));
			if(!bStatus) return bStatus;
			//select radio button for Identity Document
			bStatus=Elements.clickElement(By.xpath(Locators.RaApp.radioBtnInvestmentPage("South African Identity Document")));
			if(!bStatus) return bStatus;
			//Enter Identity Number
			bStatus=Elements.enterText(By.xpath(Locators.RaApp.inputText("Identity Number")),"8702025800080");
			if(!bStatus) return bStatus;
			//Enter Income Tax Number
			bStatus=Elements.enterText(By.xpath(Locators.RaApp.inputText("Income Tax Number")),"2039060179");
			if(!bStatus) return bStatus;
			// select Preferred Correspondence Language
			bStatus=Elements.clickElement(By.xpath(Locators.RaApp.radioBtnInvestmentPage("English")));
			if(!bStatus) return bStatus;
			//click continue 
			bStatus=Elements.clickElement(By.xpath(Locators.RaApp.btnContinue("Continue")));
			if(!bStatus) return bStatus;

			//Check next page loaded
			bStatus=Wait.waitForElementVisibility(By.xpath(Locators.RaApp.subHeaderTitle("Where do you live")), iSync);
			if(!bStatus) return bStatus;
			bStatus=Verify.verifyElementVisible(By.xpath(Locators.RaApp.subHeaderTitle("Where do you live")));
			if(!bStatus) return bStatus;

			//Enter Street Number
			bStatus=Elements.enterText(By.xpath(Locators.RaApp.inputText("Street Number")),objPersonalDtls.get("StreetNumber"));
			if(!bStatus) return bStatus;
			//Enter Street Name
			bStatus=Elements.enterText(By.xpath(Locators.RaApp.inputText("Street Name")),objPersonalDtls.get("StreetName"));
			if(!bStatus) return bStatus;
			//Enter Suburb
			bStatus=Elements.enterText(By.xpath(Locators.RaApp.inputText("Suburb")),objPersonalDtls.get("Suburb"));
			if(!bStatus) return bStatus;
			//Enter City
			bStatus=Elements.enterText(By.xpath(Locators.RaApp.inputText("City")),objPersonalDtls.get("City"));
			if(!bStatus) return bStatus;
			//Enter Postal code
			bStatus=Elements.enterText(By.xpath(Locators.RaApp.inputText("Postal Code")),objPersonalDtls.get("PostalCode"));
			if(!bStatus) return bStatus;
			//click continue 
			bStatus=Elements.clickElement(By.xpath(Locators.RaApp.btnContinue("Continue")));
			if(!bStatus) return bStatus;

			//Check next page loaded
			bStatus=Wait.waitForElementVisibility(By.xpath(Locators.RaApp.subHeaderTitle("Additional Details")), iSync);
			if(!bStatus) return bStatus;
			bStatus=Verify.verifyElementVisible(By.xpath(Locators.RaApp.subHeaderTitle("Additional Details")));
			if(!bStatus) return bStatus;

			Reporting.logResults("Pass", "personalDetails", "Succesfully entered data to Personal details page");
		}
		catch(Exception e)
		{

		}
		return bStatus;
	}

	public static boolean additionalDetails() {
		try {

			//click continue 
			bStatus=Elements.clickElement(By.xpath(Locators.RaApp.btnContinue("Continue")));
			if(!bStatus) return bStatus;

			//Check next page loaded
			bStatus=Wait.waitForElementVisibility(By.xpath(Locators.RaApp.subHeaderTitle("Beneficiary details")), iSync);
			if(!bStatus) return bStatus;
			bStatus=Verify.verifyElementVisible(By.xpath(Locators.RaApp.subHeaderTitle("Beneficiary details")));
			if(!bStatus) return bStatus;

			Reporting.logResults("Pass", "additionalDetails", "Succesfully entered data to Additional details page");
		}
		catch(Exception e)
		{

		}
		return bStatus;
	}


	public static boolean beneficiaryPage() {
		try {

			//click continue 
			bStatus=Elements.clickElement(By.xpath(Locators.RaApp.btnContinue("Continue")));
			if(!bStatus) return bStatus;

			//Check next page loaded
			bStatus=Wait.waitForElementVisibility(By.xpath("//strong[text()='Final Checks']"), iSync);
			if(!bStatus) return bStatus;
			bStatus=Verify.verifyElementVisible(By.xpath("//strong[text()='Final Checks']"));
			if(!bStatus) return bStatus;

			Reporting.logResults("Pass", "beneficiaryPage", "Succesfully entered data to Beneficiary page");
		}
		catch(Exception e)
		{

		}
		return bStatus;
	}

	public static boolean finalChecks(Map<String,String> objFinalCheck) {
		try {

			//select source of funds
			bStatus=CommonFunc.selectDropDownPersonalDtls("Source of Funds", objFinalCheck.get("SourceOfFunds"));
			if(!bStatus) return bStatus;
			//select source of Income
			bStatus=CommonFunc.selectDropDownPersonalDtls("Source of Income", objFinalCheck.get("SourceOfIncome"));
			if(!bStatus) return bStatus;
			//select Employment Position
			bStatus=CommonFunc.selectDropDownPersonalDtls("Employment Position", objFinalCheck.get("EmploymentPosition"));
			if(!bStatus) return bStatus;
			//select Nature Of Business
			bStatus=CommonFunc.selectDropDownPersonalDtls("Nature Of Business", objFinalCheck.get("NatureOfBusiness"));
			if(!bStatus) return bStatus;
			//click continue 
			bStatus=Elements.clickElement(By.xpath(Locators.RaApp.btnContinue("Continue")));
			if(!bStatus) return bStatus;

			//Check next page loaded
			bStatus=Wait.waitForElementVisibility(By.xpath(Locators.RaApp.subHeaderTitle("Payment details")), iSync);
			if(!bStatus) return bStatus;
			bStatus=Verify.verifyElementVisible(By.xpath(Locators.RaApp.subHeaderTitle("Payment details")));
			if(!bStatus) return bStatus;

			Reporting.logResults("Pass", "finalChecks", "Succesfully entered data to final Checks page");
		}
		catch(Exception e)
		{

		}
		return bStatus;
	}


	public static boolean paymentDetails(Map<String,String> objPaymnetDtls) {
		try {

			//Enter account holder name
			bStatus=Elements.enterText(By.xpath(Locators.RaApp.inputText("Account Holder Name")), objPaymnetDtls.get("AccountHolderName"));
			if(!bStatus) return bStatus;

			//select Debit Order Date
			bStatus = Elements.clickElement(By.xpath("//span[contains(text(),'Debit Order Date')]//..//div[@class='om-dropdown-field']"));
			if(!bStatus) return bStatus;
			bStatus=Elements.clickElement(By.xpath("//span[contains(text(),'Debit')]//..//ul//li"));
			if(!bStatus) return bStatus;

			//select Bank Name
			bStatus=CommonFunc.selectDropDownPersonalDtls("Bank Name", objPaymnetDtls.get("BankName"));
			if(!bStatus) return bStatus;
			//Enter Account Number
			bStatus=Elements.enterText(By.xpath(Locators.RaApp.inputText("Account Number")), objPaymnetDtls.get("AccountNumber"));
			if(!bStatus) return bStatus;
			//select Bank Account Type
			bStatus=CommonFunc.selectDropDownPersonalDtls("Bank Account Type", objPaymnetDtls.get("AccountType"));
			if(!bStatus) return bStatus;

			//select radio button to confirm terms and conditions
			bStatus=CommonFunc.scrollToViewElement(By.xpath(Locators.RaApp.checkBoxPaymentDetails("TERMS AND CONDITIONS")));
			if(!bStatus) return bStatus;
			bStatus=Elements.clickElement(By.xpath(Locators.RaApp.checkBoxPaymentDetails("TERMS AND CONDITIONS")));
			if(!bStatus) return bStatus;

			//click continue 
			bStatus=Elements.clickElement(By.xpath(Locators.RaApp.btnContinue("Continue")));
			if(!bStatus) return bStatus;

			//Check next page loaded
			bStatus=Wait.waitForElementVisibility(By.xpath(Locators.RaApp.subHeaderTitle2("almost done")), iSync);
			if(!bStatus) return bStatus;
			bStatus=Verify.verifyElementVisible(By.xpath(Locators.RaApp.subHeaderTitle2("almost done")));
			if(!bStatus) return bStatus;

			Reporting.logResults("Pass", "paymentDetails", "Succesfully entered data to Payment Details page");
		}
		catch(Exception e)
		{

		}
		return bStatus;
	}

	public static boolean confirmationPage() {
		try {

			//click continue 
			bStatus=Elements.clickElement(By.xpath(Locators.RaApp.btnContinue("Continue")));
			if(!bStatus) return bStatus;

			//Check next page loaded
			bStatus=Wait.waitForElementVisibility(By.xpath(Locators.RaApp.subHeaderTitle2("please confirm if you are happy with your purchase")), iSync);
			if(!bStatus) return bStatus;
			bStatus=Verify.verifyElementVisible(By.xpath(Locators.RaApp.subHeaderTitle2("please confirm if you are happy with your purchase")));
			if(!bStatus) return bStatus;

			//click Submit 
			bStatus=Elements.clickElement(By.xpath(Locators.RaApp.btnBackToHome("Submit")));
			if(!bStatus) return bStatus;

			//Check next page loaded
			bStatus=Wait.waitForElementVisibility(By.xpath(Locators.RaApp.btnBackToHome("Back to Home")), iSync);
			if(!bStatus) return bStatus;
			bStatus=Verify.verifyElementVisible(By.xpath(Locators.RaApp.btnBackToHome("Back to Home")));
			if(!bStatus) return bStatus;

			Reporting.logResults("Pass", "confirmationPage", "Succesfully entered data to confirmation page");
		}
		catch(Exception e)
		{

		}
		return bStatus;
	}
}
