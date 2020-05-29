package stepDefinitions;

import org.openqa.selenium.WebDriver;

import commons.AbstractTest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.DeleteCustomerPO;
import pageObjects.LoginPO;
import pageObjects.PageGeneratorManager;

public class LogoutPageSteps extends AbstractTest {
	private WebDriver driver;
	private LoginPO loginPage;
	private DeleteCustomerPO deleteCustomerPage;
	
	public LogoutPageSteps() {
		driver = Hooks.openAndQuitBrowser();
		deleteCustomerPage = PageGeneratorManager.getDeleteCustomerPage(driver);
	}
	
    @Given("^the user has been navigated to Manager home page$")
    public void theUserHasBeenNavigatedToManagerHomePage() {
    	deleteCustomerPage = PageGeneratorManager.getDeleteCustomerPage(driver);
    }

    @When("^the user clicks on Log out link and accept alert$")
    public void theUserClicksOnLogOutLink() {
    	loginPage = (LoginPO) deleteCustomerPage.clickOnLogoutLink();
    }

    @And("^the Log in page is displayed after the users accepts alert$")
    public void theLogInPageIsDisplayedAfterTheUsersAcceptsAlert() {
		verifyEquals(loginPage.getPageTitle(), "Guru99 Bank Home Page");
    }

}
