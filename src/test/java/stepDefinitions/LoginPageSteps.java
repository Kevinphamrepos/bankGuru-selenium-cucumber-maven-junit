package stepDefinitions;

import org.openqa.selenium.WebDriver;

import commons.AbstractTest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.LoginPO;
import pageObjects.ManagerHomePO;
import pageObjects.PageGeneratorManager;

public class LoginPageSteps extends AbstractTest{
	private WebDriver driver;
	private LoginPO loginPage;
	private ManagerHomePO managerHomePage;
	
	public LoginPageSteps() {
		driver = Hooks.openAndQuitBrowser();
		loginPage = PageGeneratorManager.getLoginPage(driver);
	}

    @Given("^the user has been navigated to Login Page$")
    public void theUserHasBeenNavigatedToLoginPage() {
    	loginPage.openUrl(RegisterPageSteps.loginPageUrl);
    }

    @When("^the user inputs userID and password into required fields$")
    public void theUserInputsUserIDAndPasswordIntoRequiredFields() {
        loginPage.inputToUserIDTextbox(RegisterPageSteps.userID);
        loginPage.inputToPasswordTextbox(RegisterPageSteps.password);
    }

    @And("^the user clicks on Login button$")
    public void theUserClicksToLoginButton() {
    	managerHomePage = loginPage.clickOnLoginButton();
    }
    
    @Then("^the Welcome message should be displayed on Manager home page$")
    public void theWelcomeMessageIsDisplayedOnPage() {
    	verifyTrue(managerHomePage.isWelcomeMgsDisplayed());
		verifyEquals(managerHomePage.getManagerID(), "Manger Id : " + RegisterPageSteps.userID);
    }
}
