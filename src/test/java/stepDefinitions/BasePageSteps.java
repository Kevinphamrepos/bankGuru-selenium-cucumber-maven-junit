package stepDefinitions;

import org.openqa.selenium.WebDriver;

import commons.AbstractTest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.BasePO;
import pageObjects.PageGeneratorManager;

public class BasePageSteps extends AbstractTest{
	private WebDriver driver;
	private BasePO basePage;
	
	public BasePageSteps() {
		driver = Hooks.openAndQuitBrowser();
		basePage = PageGeneratorManager.getBasePage(driver);
	}

    @Given("^the user has been navigated to \"([^\"]*)\" page$")
    public void theUserHasBeenNavigatedToPage(String pageName) {
    	basePage.openMultiplePages(pageName);
    }

    @When("^the user clicks on \"([^\"]*)\" button$")
    public void clicksOnDynamicButton(String buttonValue) {
    	basePage.clickOnDynamicButton(buttonValue);
    }
    
}
