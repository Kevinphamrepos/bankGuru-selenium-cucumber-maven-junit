package stepDefinitions;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.WebDriver;

import commons.AbstractTest;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.PageGeneratorManager;
import pageObjects.WithdrawalPO;

public class WithdrawalPageSteps extends AbstractTest {
	private WebDriver driver;
	private WithdrawalPO withdrawalPage;
	public static String customerID;
	public static Integer currentAmount, withdrawalAmount;
	
	public WithdrawalPageSteps() {
		driver = Hooks.openAndQuitBrowser();
		withdrawalPage = PageGeneratorManager.getWithdrawalPage(driver);
	}
	
    @When("^the user inputs data into required fields of Amount Withdrawal Form$")
    public void theUserInputsDataIntoRequiredFieldsOfAmountWithdrawalForm(DataTable withdrawalDataTable) {
    	List<Map<String, String>> withdrawlInfo = withdrawalDataTable.asMaps(String.class, String.class);
    	withdrawalPage.inputToDynamicTextboxTextarea("accountno", AddNewAccountPageSteps.firstAccountID);
    	withdrawalPage.inputToDynamicTextboxTextarea("ammount", withdrawlInfo.get(0).get("Amount"));
    	withdrawalPage.inputToDynamicTextboxTextarea("desc", withdrawlInfo.get(0).get("Description"));
    	
    	withdrawalAmount = NumberUtils.toInt(withdrawlInfo.get(0).get("Amount"));
    	currentAmount = DepositPageSteps.currentAmount - withdrawalAmount;
    	System.out.println(withdrawalAmount);
    	System.out.println(currentAmount);
    	System.out.println(withdrawalAmount.getClass().getSimpleName());
    	System.out.println(currentAmount.getClass().getSimpleName());
    	
    }
    
    @Then("^the success message and Transaction details of Withdrawal should be displayed on page$")
    public void theSuccessMessageAndTransactionDetailsOfWithdrawalShouldBeDisplayedOnPage(DataTable depositDetails) {
    	List<Map<String, String>> depositInfo = depositDetails.asMaps(String.class, String.class);
    	verifyTrue(withdrawalPage.isDynamicHeaderTextDisplayed(depositInfo.get(0).get("Success Message") + " for Account " + AddNewAccountPageSteps.firstAccountID));
    	verifyEquals(withdrawalPage.getDynamicCellValue("Account No"), AddNewAccountPageSteps.firstAccountID);
		verifyEquals(withdrawalPage.getDynamicCellValue("Amount Debited"), depositInfo.get(0).get("Amount Debited"));
		verifyEquals(withdrawalPage.getDynamicCellValue("Description"), depositInfo.get(0).get("Description"));
		verifyEquals(withdrawalPage.getDynamicCellValue("Type of Transaction"), depositInfo.get(0).get("Type of Transaction"));
		verifyEquals(withdrawalPage.getDynamicCellValue("Current Balance"), Integer.toString(currentAmount));
		
    }
    
}
