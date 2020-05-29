package stepDefinitions;

import org.openqa.selenium.WebDriver;

import commons.AbstractTest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.NewAccountPO;
import pageObjects.PageGeneratorManager;

public class AddNewAccountPageSteps extends AbstractTest {
	private WebDriver driver;
	private NewAccountPO newAccountPage;
	public static String firstAccountID, iniAccountType, iniDepositAmount;
	
	public AddNewAccountPageSteps() {
		driver = Hooks.openAndQuitBrowser();
		newAccountPage = PageGeneratorManager.getNewAccountPage(driver);
		
	}

	@When("^the user inputs Customer ID into Customer ID field$")
    public void theUserInputsCustomerIDIntoCustomerIDField() {
		newAccountPage.inputToDynamicTextboxTextarea("cusid", AddNewCustomerPageSteps.customerID);
    }

	@And("^the user selects \"([^\"]*)\" in New Account type dropdown$")
	public void theUserSelectsInNewAccountTypeDropdown(String accountType) {
		iniAccountType = accountType;
		newAccountPage.selectItemInDynamicDropdown("selaccount", iniAccountType);
	}
	
    @And("^the user inputs \"([^\"]*)\" into Initial deposit field$")
    public void theUserInputsSomethingIntoInitialDepositField(String depositAmount) {
    	iniDepositAmount = depositAmount;
    	newAccountPage.inputToDynamicTextboxTextarea("inideposit", iniDepositAmount);
    }

    @Then("^the success message \"([^\"]*)\" and Created Account details should be displayed on page$")
    public void theSuccessMessageAndCreatedAccountDetailsShouldBeDisplayedOnPage(String successMessage) {
		verifyTrue(newAccountPage.isDynamicHeaderTextDisplayed(successMessage));
		verifyEquals(newAccountPage.getDynamicCellValue("Customer ID"), AddNewCustomerPageSteps.customerID);
		verifyEquals(newAccountPage.getDynamicCellValue("Account Type"), iniAccountType);
		verifyEquals(newAccountPage.getDynamicCellValue("Date of Opening"), getToday());
		verifyEquals(newAccountPage.getDynamicCellValue("Current Amount"), iniDepositAmount);
		
		firstAccountID = newAccountPage.getDynamicCellValue("Account ID");

    }

    
    
}
