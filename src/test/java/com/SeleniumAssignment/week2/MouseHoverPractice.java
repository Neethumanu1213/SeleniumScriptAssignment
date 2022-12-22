package com.SeleniumAssignment.week2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MouseHoverPractice {

	WebDriver wd;

	WebDriverWait wdwait;

	Actions action;

	SoftAssert sf;

	@BeforeMethod
	public void setUp() {

		// Basic Setup to begin with Selenium
		System.setProperty("webdriver.chrome.driver", "C:\\Chrome Driver\\chromedriver.exe");

		// intialise webdriver instance
		wd = new ChromeDriver();

		wdwait = new WebDriverWait(wd, Duration.ofSeconds(10));

		sf = new SoftAssert();

		// Launch a page
		wd.get("http://seleniumpractise.blogspot.com/2016/08/how-to-perform-mouse-hover-in-selenium.html");

		action = new Actions(wd);

	}

	@Test
	public void validateWebPages() {
		// validating the text
		WebElement welcomeText = wd.findElement(By.cssSelector("h3[itemprop='name']"));
		sf.assertEquals(welcomeText.getText(), "How to perform mouse hover in Selenium Webdriver", "Text is incorrect");

		// finding window handles for the parent window
		String parentWinowHandle = wd.getWindowHandle();

		// move mouse to the automation tool
		WebElement hoveringToAutomationTool = wd.findElement(By.cssSelector("div.dropdown>button"));
		action.moveToElement(hoveringToAutomationTool).perform();

		// clicking selenium tab
		wdwait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.dropdown>div>a:first-of-type")));
		WebElement seliniumTab = wd.findElement(By.cssSelector("div.dropdown>div>a:first-of-type"));
		action.moveToElement(seliniumTab).click().perform();

		// moved to child window
		Set<String> childWindowHandleList1 = wd.getWindowHandles();
		childWindowHandleList1.removeIf(s->s.contains(parentWinowHandle));
		wd.switchTo().window(new ArrayList<String>(childWindowHandleList1).get(0));
		sf.assertEquals(wd.getTitle(), "Selenium Webdriver Tutorial - Selenium Tutorial for Beginners",
				"Title is not correct");
		wd.switchTo().window(parentWinowHandle);

		// hovering to automation tool
		action.moveToElement(hoveringToAutomationTool).perform();

		// clicking TestNgTab
		wdwait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.dropdown>div>a:nth-of-type(2)")));
		WebElement testNGTab = wd.findElement(By.cssSelector("div.dropdown>div>a:nth-of-type(2)"));
		action.moveToElement(testNGTab).click().perform();
		sf.assertEquals(wd.getTitle(), "TestNG Tutorials for Selenium Webdriver with Real Time Examples",
				"Title is not correct");
		wd.navigate().back();

		// hovering to automation tool
		action.moveToElement(hoveringToAutomationTool).perform();

		// clicking on AppiumTb
		wdwait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.dropdown>div>a:last-of-type")));
		WebElement appiumTab = wd.findElement(By.cssSelector("div.dropdown>div>a:last-of-type"));
		action.moveToElement(appiumTab).click().perform();
		sf.assertEquals(wd.getTitle(), "Complete Ultimate Appium tutorial for beginners using JAVA for Selenium",
				"Title is not correct");
		wd.navigate().back();

		sf.assertAll();

	}

	@AfterMethod
	public void teardown() {
		wd.quit();
	}
}
