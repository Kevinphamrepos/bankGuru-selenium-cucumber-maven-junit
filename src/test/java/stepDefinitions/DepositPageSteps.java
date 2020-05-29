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
import pageObjects.DepositPO;
import pageObjects.PageGeneratorManager;

public class DepositPageSteps extends AbstractTest {
	private WebDriver driver;
	private DepositPO depositPage;
	public static String customerID;
	public static Integer currentAmount, newDepositAmount, iniDepositAmount;
	
	public DepositPageSteps() {
		driver = Hooks.openAndQuitBrowser();
		depositPage = PageGeneratorManager.getDepositPage(driver);
	}
	
    @When("^the user inputs data into required fields of Amount Deposit Form$")
    public void theUserInputsDataIntoRequiredFieldsOfAmountDepositForm(DataTable depositDataTable) {
    	List<Map<String, String>> depositInfo = depositDataTable.asMaps(String.class, String.class);
    	depositPage.inputToDynamicTextboxTextarea("accountno", AddNewAccountPageSteps.firstAccountID);
    	depositPage.inputToDynamicTextboxTextarea("ammount", depositInfo.get(0).get("Amount"));
    	depositPage.inputToDynamicTextboxTextarea("desc", depositInfo.get(0).get("Description"));
    	newDepositAmount = NumberUtils.toInt(depositInfo.get(0).get("Amount"));
    	iniDepositAmount = NumberUtils.toInt(AddNewAccountPageSteps.iniDepositAmount);
    	currentAmount = iniDepositAmount + newDepositAmount;
    }
    
    @Then("^the success message and Transaction details of Deposit should be displayed on page$")
    public void theSuccessMessageAndTransactionDetailsOfDepositShouldBeDisplayedOnPage(DataTable depositDetails) {
    	List<Map<String, String>> depositInfo = depositDetails.asMaps(String.class, String.class);
    	verifyTrue(depositPage.isDynamicHeaderTextDisplayed(depositInfo.get(0).get("Success Message") + " for Account " + AddNewAccountPageSteps.firstAccountID));
    	verifyEquals(depositPage.getDynamicCellValue("Account No"), AddNewAccountPageSteps.firstAccountID);
		verifyEquals(depositPage.getDynamicCellValue("Amount Credited"), depositInfo.get(0).get("Amount Credited"));
		verifyEquals(depositPage.getDynamicCellValue("Description"), depositInfo.get(0).get("Description"));
		verifyEquals(depositPage.getDynamicCellValue("Type of Transaction"), depositInfo.get(0).get("Type of Transaction"));
		verifyEquals(depositPage.getDynamicCellValue("Current Balance"), Integer.toString(currentAmount));
    }
    
}
