package com.SeleniumAssignment.week2;

import java.time.Duration;

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

public class ValidatePurchaseOfItem {
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
		wd.get("https://www.demoblaze.com/index.html");

		action = new Actions(wd);

	}
	
	@Test
	public void validateThePurchase() {
		String titleOfThePage=wd.getTitle();
		sf.assertEquals(titleOfThePage, "STORE","The title is not matches");
		wdwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#tbodyid>div:nth-of-type(3)>div>div>h4 a")));
		WebElement nexus6btn=wd.findElement(By.cssSelector("#tbodyid>div:nth-of-type(3)>div>div>h4 a"));
		clickMethod(nexus6btn);
		
		//text is displayed
		wdwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("h2[class='name']")));
		WebElement phoneNameText=wd.findElement(By.cssSelector("h2[class='name']"));
		sf.assertEquals(getTextMethodMethod(phoneNameText),"Nexus 6", "Phone name is not displayed");
		WebElement phonePriceText=wd.findElement(By.cssSelector("h3[class='price-container']"));
		sf.assertEquals(getTextMethodMethod(phonePriceText),"$650", "Phone price is not displayed");
		
		//to add to cart
		WebElement addToCartBtn=wd.findElement(By.cssSelector("a[onclick='addToCart(3)']"));
		clickMethod(addToCartBtn);
		
		//accept the alert
		wdwait.until(ExpectedConditions.alertIsPresent());
		wd.switchTo().alert().accept();
		
		//clicking on cart
		WebElement CartBtn=wd.findElement(By.cssSelector("div.container>div>ul>li:nth-of-type(4)"));
		clickMethod(CartBtn);
		
		//validate product details
		wdwait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#tbodyid>tr>td:nth-of-type(2)")));
		WebElement productName=wd.findElement(By.cssSelector("#tbodyid>tr>td:nth-of-type(2)"));
		sf.assertEquals(getTextMethodMethod(productName), "Nexus 6","Phone name is not correct");
		WebElement productPrice=wd.findElement(By.cssSelector("#tbodyid>tr>td:nth-of-type(3)"));
		sf.assertEquals(getTextMethodMethod(productPrice), "650","Phone price is not correct");
		WebElement productPriceText=wd.findElement(By.cssSelector("#totalp"));
		sf.assertEquals(getTextMethodMethod(productPriceText), "650","Price textnis not correct");
		WebElement placeOrderBtn=wd.findElement(By.cssSelector("div.row>div:nth-of-type(2)>button"));
		clickMethod(placeOrderBtn);
		
		//payment details
		wdwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#name")));
		WebElement name=wd.findElement(By.cssSelector("#name"));
		name.sendKeys("Neethu");
		WebElement country=wd.findElement(By.cssSelector("#country"));
		country.sendKeys("Ontario");
		WebElement city=wd.findElement(By.cssSelector("#city"));
		city.sendKeys("Franklin");
		WebElement creditCard=wd.findElement(By.cssSelector("#card"));
		creditCard.sendKeys("12345678910");
		WebElement month=wd.findElement(By.cssSelector("#month"));
		month.sendKeys("December");
		WebElement year=wd.findElement(By.cssSelector("#year"));
		year.sendKeys("2022");
		WebElement purchaseBtn=wd.findElement(By.cssSelector("button[onclick='purchaseOrder()']"));
		clickMethod(purchaseBtn);
		
		//validate the details
		WebElement thankYouForPurchaseText=wd.findElement(By.cssSelector("div.sa-icon.sa-custom"));
		sf.assertEquals(getTextMethodMethod(thankYouForPurchaseText),"Thank you for your purchase!","Text is not displayed");
		WebElement amountText=wd.findElement(By.cssSelector("p.lead.text-muted "));
		sf.assertEquals(amountText.getText(), "Amount: 650 USD", "Amount is not correct");
		WebElement cardNumberText=wd.findElement(By.cssSelector("p.lead.text-muted "));
		sf.assertEquals(cardNumberText.getText(), "12345678910", "Card number is not correct");
		WebElement nameText=wd.findElement(By.cssSelector("p.lead.text-muted "));
		sf.assertEquals(nameText.getText(), "Neethu", "Name is not correct");
		
		wdwait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.sa-button-container>div button")));
		WebElement okBtn=wd.findElement(By.cssSelector("div.sa-button-container>div button"));
		clickMethod(okBtn);
		
		//verify the title of the page
		sf.assertEquals(titleOfThePage, "STORE","The title is not matches");
	}
	@AfterMethod
	public void teardown() { 
		wd.quit();
	}
	
	public void clickMethod(WebElement element) {
		element.click();
	}

	public String getTextMethodMethod(WebElement element) {
		return element.getText();
	}

}
