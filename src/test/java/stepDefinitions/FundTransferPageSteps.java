package stepDefinitions;

import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.WebDriver;

import commons.AbstractTest;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.FundTransferPO;
import pageObjects.NewAccountPO;
import pageObjects.PageGeneratorManager;

public class FundTransferPageSteps extends AbstractTest{
	private WebDriver driver;
	private NewAccountPO newAccountPage;
	private FundTransferPO fundTransferPage;
	public static String transferAmount, iniAccountType, secondAccountID;
	public static Integer firstAccountBalance;
	
	public FundTransferPageSteps() {
		driver = Hooks.openAndQuitBrowser();
		fundTransferPage = PageGeneratorManager.getFundTransferPage(driver);
		transferAmount = "5000";
		iniAccountType = "Current";
	}
	
    @When("^the user inputs data into required fields of Fund Transfer Form$")
    public void theUserInputsDataIntoRequiredFieldsOfFundTransferForm() {
    	
    	// Creat secondAccountID
    	newAccountPage = (NewAccountPO) fundTransferPage.openMultiplePages("New Account");
		verifyTrue(newAccountPage.isDynamicHeaderTextDisplayed("Add new account form"));
		newAccountPage.inputToDynamicTextboxTextarea("cusid", AddNewCustomerPageSteps.customerID);
		newAccountPage.selectItemInDynamicDropdown("selaccount", iniAccountType);
		newAccountPage.inputToDynamicTextboxTextarea("inideposit", "2000");
		newAccountPage.clickOnDynamicButton("submit");
		verifyTrue(newAccountPage.isDynamicHeaderTextDisplayed("Account Generated Successfully!!!"));
		verifyEquals(newAccountPage.getDynamicCellValue("Customer ID"), AddNewCustomerPageSteps.customerID);
		verifyEquals(newAccountPage.getDynamicCellValue("Account Type"), iniAccountType);
		verifyEquals(newAccountPage.getDynamicCellValue("Current Amount"), "2000");
		secondAccountID = newAccountPage.getDynamicCellValue("Account ID");
		
		// Tranfering Process
		fundTransferPage = (FundTransferPO) newAccountPage.openMultiplePages("Fund Transfer");
		fundTransferPage.inputToDynamicTextboxTextarea("payersaccount", AddNewAccountPageSteps.firstAccountID);
		fundTransferPage.inputToDynamicTextboxTextarea("payeeaccount", secondAccountID);
		fundTransferPage.inputToDynamicTextboxTextarea("ammount", transferAmount);
		fundTransferPage.inputToDynamicTextboxTextarea("desc", "Fund Transfering");
		firstAccountBalance = WithdrawalPageSteps.currentAmount - NumberUtils.toInt(transferAmount);
	
    }

    @Then("^the success message and Fund Transfer Details should be displayed on page$")
    public void theSuccessMessageAndFundTransferDetailsShouldBeDisplayedOnPage() {
		verifyTrue(fundTransferPage.isDynamicHeaderTextDisplayed("Fund Transfer Details"));
		verifyEquals(fundTransferPage.getDynamicCellValue("From Account Number"), AddNewAccountPageSteps.firstAccountID);
		verifyEquals(fundTransferPage.getDynamicCellValue("To Account Number"), secondAccountID);
		verifyEquals(fundTransferPage.getDynamicCellValue("Amount"), transferAmount);
		verifyEquals(fundTransferPage.getDynamicCellValue("Description"), "Fund Transfering");

    }
 
	
	
}
