package commons;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AbstractTest {
	private WebDriver driver;

	protected final Log log;
	
	protected AbstractTest() {
		log = LogFactory.getLog(getClass());
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	protected int randomNumber() {
		Random random = new Random();
		return random.nextInt(99999);
	}
	
	protected void sleepInSecondAbstractTest(long numberInSecond) {
		try {
			Thread.sleep(numberInSecond * 1000);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}

	protected String getCurrentDay() {
		DateTime nowUTC = new DateTime(DateTimeZone.UTC);
		int day = nowUTC.getDayOfMonth();
		if (day < 10) {
			String dayValue = "0" + day;
			return dayValue;
		}
		return day + "";
	}

	protected String getCurrentMonth() {
		DateTime now = new DateTime(DateTimeZone.UTC);
		int month = now.getMonthOfYear();
		if (month < 10) {
			String monthValue = "0" + month;
			return monthValue;
		}
		return month + "";
	}

	protected String getCurrentYear() {
		DateTime now = new DateTime(DateTimeZone.UTC);
		return now.getYear() + "";
	}
	
	protected String getCurrentWeekday() {
		DateTime now = new DateTime(DateTimeZone.UTC);
		return now.getDayOfWeek() + "";
	}

	protected String getToday() {
		return getCurrentYear() + "-" + getCurrentMonth() + "-" + getCurrentDay();
	}
	
	protected String getTodayWithWeekday() { 
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy"); 
		String weekday = formatter.format(date);
		System.out.println(weekday);
		return weekday;
	}
	
	protected String getTodayWithWeekdayByTimeZone(String zoneID) {
		LocalDate date = LocalDate.now(ZoneId.of(zoneID));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy"); 
		String weekday = formatter.format(date);
		System.out.println(weekday);
		return weekday;
	}
	
	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	public boolean isSortedStringAscending(WebDriver driver, String locator) {
		ArrayList<String> arrayList = new ArrayList<String>();
		List<WebElement> elementList = driver.findElements(By.xpath(locator));
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}
		ArrayList<String> sortingArray = new ArrayList<String>();
		for (String child : arrayList) {
			sortingArray.add(child);
		}
		Collections.sort(sortingArray);
		return sortingArray.equals(arrayList);

	}

	public boolean isSortedStringDescending(WebDriver driver, String locator) {
		ArrayList<String> arrayList = new ArrayList<String>();
		List<WebElement> elementList = driver.findElements(By.xpath(locator));
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}
		ArrayList<String> sortingArray = new ArrayList<String>();
		for (String child : arrayList) {
			sortingArray.add(child);
		}
		Collections.sort(sortingArray);
		Collections.reverse(sortingArray);
		return sortingArray.equals(arrayList);
	}

	public boolean isSortedFloatAscending(WebDriver driver, String locator) {
		ArrayList<Float> arrayList = new ArrayList<Float>();
		List<WebElement> elementList = driver.findElements(By.xpath(locator));
		for (WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
		}
		ArrayList<Float> sortingArray = new ArrayList<Float>();
		for (Float child : arrayList) {
			sortingArray.add(child);
		}
		Collections.sort(sortingArray);
		return sortingArray.equals(arrayList);

	}

	public boolean isSortedStringAscendingJDK8(WebDriver driver, String locator) {
		List<WebElement> elementList = driver.findElements(By.xpath(locator));
		List<String> items = elementList.stream().map(n -> n.getText()).collect(Collectors.toList());
		List<String> sortedList = items;
		Collections.sort(sortedList);
		return items.equals(sortedList);
	}	
	
	public boolean isSortedStringDescendingJDK8(WebDriver driver, String locator) {
		List<WebElement> elementList = driver.findElements(By.xpath(locator));
		List<String> itemList = elementList.stream().map(n -> n.getText()).collect(Collectors.toList());
		List<String> sortingList = itemList;
		Collections.sort(sortingList);
		Collections.reverse(sortingList);
		return sortingList.equals(itemList);
	}	
	
	public void selectItemInHTMLDropdown(WebDriver driver, String dropdownXpath, String expectedItem) {
		WebElement element = driver.findElement(By.xpath(dropdownXpath));
		Select select;
		select = new Select(element);
		select.selectByVisibleText(expectedItem);
	}
	
	public void deleteAllFilesInFolder() {
		try {
			String rootFolder = System.getProperty("user.dir");
			String screenshotFolderPath = rootFolder + "\\test-output\\screenshotReportNG";
			File file = new File(screenshotFolderPath);
			File[] listFiles = file.listFiles();
			for (int i = 0; i < listFiles.length; i++) {
				if (listFiles[i].isFile()) {
					System.out.println(listFiles[i].getName());
					new File(listFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}

