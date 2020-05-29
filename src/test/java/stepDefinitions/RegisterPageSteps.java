package stepDefinitions;

import org.openqa.selenium.WebDriver;

import commons.AbstractTest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.LoginPO;
import pageObjects.PageGeneratorManager;
import pageObjects.RegisterPO;

public class RegisterPageSteps extends AbstractTest {
	private WebDriver driver;
	private LoginPO loginPage;
	private RegisterPO registerPage;
	public static String validEmail, userID, password, loginPageUrl;
	
	public RegisterPageSteps() {
		driver = Hooks.openAndQuitBrowser();
		loginPage = PageGeneratorManager.getLoginPage(driver);
	}

    @Given("^the user has been navigated to Register page$")
    public void theUserHasBeenNavigatedToRegisterPage() {
		loginPageUrl = loginPage.getLoginPageUrl();
		registerPage = loginPage.clickOnHereLink();
	}

    @When("^the user inputs email into Email ID field$")
    public void theUserInputsEmailIntoEmailIDField() {
    	validEmail = "Kevinpham_" + randomNumber() + "@gmail.com";
    	registerPage.inputToEmailIDTextbox(validEmail);
    }
    
	@Then("^the Access details should be displayed on page$")
    public void theAccessDetailsShouldBeDisplayedOnPage() {
		verifyTrue(registerPage.isAccessDetailsDisplayed());
		userID = registerPage.getDynamicCellValue("User ID :");
		password = registerPage.getDynamicCellValue("Password :");
	}
    
}
