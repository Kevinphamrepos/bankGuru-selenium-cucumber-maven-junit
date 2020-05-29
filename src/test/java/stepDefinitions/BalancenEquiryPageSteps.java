package stepDefinitions;

import org.openqa.selenium.WebDriver;

import commons.AbstractTest;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.BalanceEnquiryPO;
import pageObjects.PageGeneratorManager;

public class BalancenEquiryPageSteps extends AbstractTest {
	private WebDriver driver;
	private BalanceEnquiryPO balanceEnquiryPage;
	
	public BalancenEquiryPageSteps() {
		driver = Hooks.openAndQuitBrowser();
		balanceEnquiryPage = PageGeneratorManager.getBalanceEnquiryPage(driver);
	}

	 @When("^the user inputs Account ID into Account No field of Balance Enquiry Form$")
	 public void theUserInputsAccountIDIntoAccountNoField() {
		 balanceEnquiryPage.inputToDynamicTextboxTextarea("accountno", AddNewAccountPageSteps.firstAccountID);
    }

    @Then("^the Balance Details for that Account should be displayed on page$")
    public void theBalanceDetailsForThatAccountShouldBeDisplayedOnPage() {
		verifyTrue(balanceEnquiryPage.isDynamicHeaderTextDisplayed("Balance Details for Account " + AddNewAccountPageSteps.firstAccountID));
		verifyEquals(balanceEnquiryPage.getDynamicCellValue("Account No"), AddNewAccountPageSteps.firstAccountID);
		verifyEquals(balanceEnquiryPage.getDynamicCellValue("Type of Account"), EditAccountPageSteps.editAccountType);
		verifyEquals(balanceEnquiryPage.getDynamicCellValue("Balance"), Integer.toString(FundTransferPageSteps.firstAccountBalance));
    }

}
