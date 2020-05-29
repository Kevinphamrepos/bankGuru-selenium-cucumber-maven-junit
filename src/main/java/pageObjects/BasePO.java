package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPO;

public class BasePO extends AbstractPO {
	WebDriver driver;

	public BasePO(WebDriver linkDriver) {
		super(linkDriver);
		driver = linkDriver;
	}


	
}
