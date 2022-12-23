package com.SeleniumAssignment.week2;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ValidateTheTitleOfThePage {
	
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
		wd.manage().window().maximize();

		// Launch a page
		wd.get("http://seleniumpractise.blogspot.com/2017/07/multiple-window-examples.html");

		action = new Actions(wd);

	}
	
	@Test
	public void validateTheTitle() {

		// home page
		WebElement welcomeText = wd.findElement(By.cssSelector("h3[itemprop='name']"));
		sf.assertEquals(welcomeText.getText(), "Multiple window examples", "Text is not matched");

		// parent window handle
		Set<String> parentWinowHandle = wd.getWindowHandles();
		

		// clicking on first tab
		WebElement newTab1 = wd.findElement(By.cssSelector("div.post-body.entry-content>a:first-of-type"));
		elementClicking(newTab1);
		Set<String> handlelist1 = wd.getWindowHandles();
		String firstTabHandle=findingHandle(handlelist1, parentWinowHandle);


		// clicking on the second tab
		WebElement newTab2 = wd.findElement(By.cssSelector("div.post-body.entry-content>a:nth-of-type(2)"));
		elementClicking(newTab2);
		Set<String> handlelist2 = wd.getWindowHandles();
		String secondTabHandle=findingHandle(handlelist2, handlelist1);


		// Clicking on the third tab
		WebElement newTab3 = wd.findElement(By.cssSelector("div.post-body.entry-content>a:nth-of-type(3)"));
		elementClicking(newTab3);
		Set<String> handlelist3 = wd.getWindowHandles();
		String thirdTabHandle=findingHandle(handlelist3, handlelist2);
		


		// clicking fourth tab
		WebElement newTab4 = wd.findElement(By.cssSelector("div.post-body.entry-content>a:last-of-type"));
		elementClicking(newTab4);
		//Set<String> handlelist4 = wd.getWindowHandles();
		String forthTabHandle=wd.getWindowHandle();

		sf.assertAll();


		wd.switchTo().window(firstTabHandle);
		sf.assertEquals(wd.getTitle(), "Google", "Title is not match");
		wd.switchTo().window(secondTabHandle);
		sf.assertEquals(wd.getTitle(), "Facebook - log in or sign up", "Title is not match");
		wd.switchTo().window(thirdTabHandle);
		sf.assertEquals(wd.getTitle(),
				"Yahoo | Mail, Weather, Search, News, Finance, Sports, Shopping, Entertainment, Video",
				"Title is not match");
		wd.switchTo().window(forthTabHandle);
		sf.assertEquals(wd.getTitle(), "Facebook - log in or sign up","Title is not match");
		


	}
	
	@AfterMethod
	public void teardown() {
		//wd.quit();
	}


	public void elementClicking(WebElement element) {
		//action.moveToElement(element).click().perform();
		element.click();

	}

	public String findingHandle(Set<String> set, Set<String> set1) {
		
		set.removeAll(set1);
		List<String> handle=set.stream().collect(Collectors.toList());
		return handle.get(0);
	
		
		
		
		

	}

}