package stepDefinitions;

import org.openqa.selenium.WebDriver;

import commons.AbstractTest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.DeleteAccountPO;
import pageObjects.ManagerHomePO;
import pageObjects.PageGeneratorManager;

public class DeleteAccountPageSteps extends AbstractTest {
	private WebDriver driver;
	private DeleteAccountPO deleteAccountPage;
	private ManagerHomePO managerHomePage;
	
	public DeleteAccountPageSteps() {
		driver = Hooks.openAndQuitBrowser();
		deleteAccountPage = PageGeneratorManager.getDelelteAccountPage(driver);
	}

	@When("^the user inputs Account ID into Account No field of Delete Account Form$")
	public void theUserInputsAccountIDIntoAccountNoFieldOfDeleteAccountForm() {
		deleteAccountPage.inputToDynamicTextboxTextarea("accountno", AddNewAccountPageSteps.firstAccountID);
	}

	@And("^the user accepts a confirmation and successful alert$")
	public void theUserAcceptsAConfirmationAlert() {
		deleteAccountPage.acceptAlert();
		managerHomePage = (ManagerHomePO) deleteAccountPage.clickAcceptAlert();
	}
	
    @Then("^the Manager home page should be displayed after the users accepts alert$")
    public void theManagerHomePageShouldBeDisplayedAfterTheUsersAcceptsAlert() {
    	verifyTrue(managerHomePage.isWelcomeMgsDisplayed());
		verifyEquals(managerHomePage.getManagerID(), "Manger Id : " + RegisterPageSteps.userID);
    }
	
	@And("^that Account ID should not exist$")
	public void thatAccountIDShouldNotExist() {
		deleteAccountPage = (DeleteAccountPO) managerHomePage.openMultiplePages("Delete Account");
		verifyTrue(deleteAccountPage.isDynamicHeaderTextDisplayed("Delete Account Form"));
		deleteAccountPage.inputToDynamicTextboxTextarea("accountno", AddNewAccountPageSteps.firstAccountID);
		deleteAccountPage.clickOnDynamicButton("Submit");
		deleteAccountPage.acceptAlert();
		verifyEquals(deleteAccountPage.getTextAlert(), "Account does not exist");
		deleteAccountPage.acceptAlert();
	}
	
}
