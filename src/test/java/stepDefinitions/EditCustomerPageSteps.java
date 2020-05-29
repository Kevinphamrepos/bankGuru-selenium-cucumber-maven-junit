package stepDefinitions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import commons.AbstractTest;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.EditCustomerPO;
import pageObjects.PageGeneratorManager;

public class EditCustomerPageSteps extends AbstractTest {
	private WebDriver driver;
	private EditCustomerPO editCustomerPage;
	public static String customerID;
	
	public EditCustomerPageSteps() {
		driver = Hooks.openAndQuitBrowser();
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);
	}
	
	@When("^the user inputs data into Customer ID field$")
	public void theUserInputsDataIntoCustomerIDField() {
		editCustomerPage.inputToDynamicTextboxTextarea("cusid", AddNewCustomerPageSteps.customerID);
	}
	
    @Then("^the Edit Customer Form should be displayed$")
    public void theEditCustomerFormShouldBeDisplayed() {
    	verifyTrue(editCustomerPage.isDynamicHeaderTextDisplayed("Edit Customer"));
    }

    @When("^the user inputs data into required fields of Edit Customer Form$")
    public void theUserInputsDataIntoRequiredFieldsOfEditCustomerForm(DataTable editCustomerInfo) {
    	List<Map<String, String>> editValue = editCustomerInfo.asMaps(String.class, String.class);
    	editCustomerPage.inputToDynamicTextboxTextarea("addr", editValue.get(0).get("Address"));
    	editCustomerPage.inputToDynamicTextboxTextarea("city", editValue.get(0).get("City"));
    	editCustomerPage.inputToDynamicTextboxTextarea("state", editValue.get(0).get("State"));
    	editCustomerPage.inputToDynamicTextboxTextarea("pinno", editValue.get(0).get("Pin"));
    	editCustomerPage.inputToDynamicTextboxTextarea("telephoneno", editValue.get(0).get("Mobile Number"));
    	editCustomerPage.inputToDynamicTextboxTextarea("emailid", editValue.get(0).get("Email"));
    }
    
    @Then("^the Customer Details Updated Successfully message and Updated Customer Info should be displayed$")
    public void theCustomerDetailsUpdatedSuccessfullyMessageAndUpdatedCustomerInfoShouldBeDisplayed(DataTable outputCustomerInfo) {
    	List<Map<String, String>> outputInfoValue = outputCustomerInfo.asMaps(String.class, String.class);
    	verifyTrue(editCustomerPage.isDynamicHeaderTextDisplayed(outputInfoValue.get(0).get("Success Message")));
    	verifyEquals(editCustomerPage.getDynamicCellValue("Customer ID"), AddNewCustomerPageSteps.customerID);
		verifyEquals(editCustomerPage.getDynamicCellValue("Address"), outputInfoValue.get(0).get("Address"));
		verifyEquals(editCustomerPage.getDynamicCellValue("City"), outputInfoValue.get(0).get("City"));
		verifyEquals(editCustomerPage.getDynamicCellValue("State"), outputInfoValue.get(0).get("State"));
		verifyEquals(editCustomerPage.getDynamicCellValue("Pin"), outputInfoValue.get(0).get("Pin"));
		verifyEquals(editCustomerPage.getDynamicCellValue("Mobile No."), outputInfoValue.get(0).get("Mobile No."));
		verifyEquals(editCustomerPage.getDynamicCellValue("Email"), outputInfoValue.get(0).get("Email"));
    }
    
}
