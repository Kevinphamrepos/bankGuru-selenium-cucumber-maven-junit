package stepDefinitions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import commons.AbstractTest;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.NewCustomerPO;
import pageObjects.PageGeneratorManager;

public class AddNewCustomerPageSteps extends AbstractTest {
	private WebDriver driver;
	private NewCustomerPO newCustomerPage;
	public static String customerID;
	
	public AddNewCustomerPageSteps() {
		driver = Hooks.openAndQuitBrowser();
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);
	}

    @When("^the user inputs data into required fields of Add New Customer Form$")
    public void theUserInputsDataIntoRequiredFieldsOfAddNewCustomerForm(DataTable newCustomerInfo) {
    	List<Map<String, String>> infoValue = newCustomerInfo.asMaps(String.class, String.class);
    	newCustomerPage.inputToDynamicTextboxTextarea("name", infoValue.get(0).get("Customer Name"));
    	newCustomerPage.inputToDynamicTextboxTextarea("dob", infoValue.get(0).get("DateOfBirth"));
    	newCustomerPage.inputToDynamicTextboxTextarea("addr", infoValue.get(0).get("Address"));
    	newCustomerPage.inputToDynamicTextboxTextarea("city", infoValue.get(0).get("City"));
    	newCustomerPage.inputToDynamicTextboxTextarea("state", infoValue.get(0).get("State"));
    	newCustomerPage.inputToDynamicTextboxTextarea("pinno", infoValue.get(0).get("Pin"));
    	newCustomerPage.inputToDynamicTextboxTextarea("telephoneno", infoValue.get(0).get("Mobile Number"));
    	newCustomerPage.inputToDynamicTextboxTextarea("emailid", infoValue.get(0).get("Email"));
    	newCustomerPage.inputToDynamicTextboxTextarea("password", infoValue.get(0).get("Password"));
    	
    }
    
    @Then("^the Registered Successfully message and Customer Info should be displayed$")
    public void theRegisteredSuccessfullyMessageAndCustomerInfShouldBeDisplayed(DataTable outputCustomerInfo) {
    	List<Map<String, String>> outputInfoValue = outputCustomerInfo.asMaps(String.class, String.class);
    	verifyTrue(newCustomerPage.isDynamicHeaderTextDisplayed(outputInfoValue.get(0).get("Success Message")));
		verifyEquals(newCustomerPage.getDynamicCellValue("Customer Name"), outputInfoValue.get(0).get("Customer Name"));
		verifyEquals(newCustomerPage.getDynamicCellValue("Gender"), outputInfoValue.get(0).get("Gender"));
		verifyEquals(newCustomerPage.getDynamicCellValue("Birthdate"), outputInfoValue.get(0).get("Birthdate"));
		verifyEquals(newCustomerPage.getDynamicCellValue("Address"), outputInfoValue.get(0).get("Address"));
		verifyEquals(newCustomerPage.getDynamicCellValue("City"), outputInfoValue.get(0).get("City"));
		verifyEquals(newCustomerPage.getDynamicCellValue("State"), outputInfoValue.get(0).get("State"));
		verifyEquals(newCustomerPage.getDynamicCellValue("Pin"), outputInfoValue.get(0).get("Pin"));
		verifyEquals(newCustomerPage.getDynamicCellValue("Mobile No."), outputInfoValue.get(0).get("Mobile No."));
		verifyEquals(newCustomerPage.getDynamicCellValue("Email"), outputInfoValue.get(0).get("Email"));
    	
    	customerID = newCustomerPage.getDynamicCellValue("Customer ID");
    }
    
}
