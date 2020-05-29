package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;
import pageUIs.RegisterPageUI;

public class RegisterPO extends AbstractPO {
	WebDriver driver;

	public RegisterPO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}

	public void inputToEmailIDTextbox(String randomEmail) {
		waitForElementVisibleByXpath(RegisterPageUI.EMAILID_TEXTBOX);
		sendKeysToElement(RegisterPageUI.EMAILID_TEXTBOX, randomEmail);
	}
	
	public boolean isAccessDetailsDisplayed() {
		waitForElementVisibleByXpath(RegisterPageUI.ACCESS_DETAILS_TITLE);
		return isElementDisplayed(RegisterPageUI.ACCESS_DETAILS_TITLE);
	}

}
