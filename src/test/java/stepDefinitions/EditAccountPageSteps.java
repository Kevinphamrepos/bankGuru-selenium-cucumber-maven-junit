package stepDefinitions;

import org.openqa.selenium.WebDriver;

import commons.AbstractTest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.EditAccountPO;
import pageObjects.PageGeneratorManager;

public class EditAccountPageSteps extends AbstractTest {
	private WebDriver driver;
	private EditAccountPO editAccountPage;
	public static String editAccountType;
	
	public EditAccountPageSteps() {
		driver = Hooks.openAndQuitBrowser();
		editAccountPage = PageGeneratorManager.getEditAccountPage(driver);
	}

	 @When("^the user inputs Account ID into Account No field$")
	    public void theUserInputsAccountIDIntoAccountNoField() {
		 editAccountPage.inputToDynamicTextboxTextarea("accountno", AddNewAccountPageSteps.firstAccountID);
    }
	 
	@And("^the user selects \"([^\"]*)\" in Edit Account type dropdown$")
	public void theUserSelectsInEditAccountTypeDropdown(String editAccountType) {
		editAccountPage.selectItemInDynamicDropdown("a_type", editAccountType);
	}

    @Then("^the success message \"([^\"]*)\" and Updated Account details should be displayed on page$")
    public void theSuccessMessageAndUpdatedAccountDetailsShouldBeDisplayedOnPage(String successMessage) {
		verifyTrue(editAccountPage.isDynamicHeaderTextDisplayed(successMessage));
		verifyEquals(editAccountPage.getDynamicCellValue("Customer ID"), AddNewCustomerPageSteps.customerID);
		verifyEquals(editAccountPage.getDynamicCellValue("Account ID"), AddNewAccountPageSteps.firstAccountID);
		verifyEquals(editAccountPage.getDynamicCellValue("Account Type"), editAccountType);
		verifyEquals(editAccountPage.getDynamicCellValue("Date of Opening"), getToday());
    }

}
