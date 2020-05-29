package cucumberOptions;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/features", 
		glue = "stepDefinitions", 
		monochrome = true, 
		plugin = {"pretty", "html:target/site/site/cucumber-default-report", "json:target/site/bankguru-cucumber-output.json"}, // để write vào report dạng html và theo format pretty, file json để làm đầu vào cho Cucumber-reporting và dùng để show report trên CI
		snippets = SnippetType.CAMELCASE, 
		tags = {"@register, @login, @new_customer, @edit_customer, @new_account, @edit_account, @deposit, @withdrawal, @fund_transfer, @balance_enquiry, @delete_account, @delete_customer, @logout"})
	
	public class CucumberTestRunner {
	}

