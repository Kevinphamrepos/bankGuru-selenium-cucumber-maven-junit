package stepDefinitions;

import org.openqa.selenium.WebDriver;

import commons.AbstractTest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.DeleteAccountPO;
import pageObjects.DeleteCustomerPO;
import pageObjects.ManagerHomePO;
import pageObjects.PageGeneratorManager;

public class DeleteCustomerPageSteps extends AbstractTest {
	private WebDriver driver;
	private ManagerHomePO managerHomePage;
	private DeleteAccountPO deleteAccountPage;
	private DeleteCustomerPO deleteCustomerPage;
	
	public DeleteCustomerPageSteps() {
		driver = Hooks.openAndQuitBrowser();
		deleteCustomerPage = PageGeneratorManager.getDeleteCustomerPage(driver);
	}

    @When("^the user inputs Customer ID into Customer ID field of Delete Customer Form$")
    public void theUserInputsCustomerIDIntoCustomerIDFieldOfDeleteCustomerForm() {
        // Delete secondAccountID
    	deleteAccountPage = (DeleteAccountPO) deleteCustomerPage.openMultiplePages("Delete Account");
		verifyTrue(deleteAccountPage.isDynamicHeaderTextDisplayed("Delete Account Form"));
		deleteAccountPage.inputToDynamicTextboxTextarea("accountno", FundTransferPageSteps.secondAccountID);
		deleteAccountPage.clickOnDynamicButton("Submit");
		deleteAccountPage.acceptAlert();
		managerHomePage = (ManagerHomePO) deleteAccountPage.clickAcceptAlert();
		// Verify 2nd Account is deleted
		deleteAccountPage = (DeleteAccountPO) managerHomePage.openMultiplePages("Delete Account");
		verifyTrue(deleteAccountPage.isDynamicHeaderTextDisplayed("Delete Account Form"));
		deleteAccountPage.inputToDynamicTextboxTextarea("accountno", FundTransferPageSteps.secondAccountID);
		deleteAccountPage.clickOnDynamicButton("Submit");
		deleteAccountPage.acceptAlert();
		verifyEquals(deleteAccountPage.getTextAlert(), "Account does not exist");
		managerHomePage = (ManagerHomePO) deleteAccountPage.clickAcceptAlert();

		// Delete Customer ID
		deleteCustomerPage = (DeleteCustomerPO) managerHomePage.openMultiplePages("Delete Customer");
		verifyTrue(deleteCustomerPage.isDynamicHeaderTextDisplayed("Delete Customer Form"));
		deleteCustomerPage.inputToDynamicTextboxTextarea("cusid", AddNewCustomerPageSteps.customerID);
    }
		
	@And("^that Customer ID should not exist$")
	public void thatCustomerIDShouldNotExist() {
		deleteCustomerPage = (DeleteCustomerPO) managerHomePage.openMultiplePages("Delete Customer");
		verifyTrue(deleteCustomerPage.isDynamicHeaderTextDisplayed("Delete Customer Form"));
		deleteCustomerPage.inputToDynamicTextboxTextarea("cusid", AddNewCustomerPageSteps.customerID);
		deleteCustomerPage.clickOnDynamicButton("Submit");
		deleteCustomerPage.acceptAlert();
		verifyEquals(deleteAccountPage.getTextAlert(), "Customer does not exist!!");
		deleteCustomerPage.acceptAlert();
	}
	
}
