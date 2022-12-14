package com.neethuManu.SelenuimScriptAssignment;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LaptopPurchasingAutomation {

	WebDriver webDriver;
	String randomEmail = randomEmail();

	@BeforeMethod
	public void setUp() {

		// Driver location to begin with Selenium
		System.setProperty("webdriver.chrome.driver", "C:\\Chrome Driver\\chromedriver.exe");

		// Initialize web driver instance
		webDriver = new ChromeDriver();

		//implicit wait
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Launch a page
		webDriver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/register");
		webDriver.manage().window().maximize();

	}

	@Test
	public void verifyPhonePurchase() {

		//registering the account
		accountRegisteration();
		
		//Navigating to the login page
		webDriver.navigate().to("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		sleep();

		//login in to the account
		WebElement emailInputFieldLogin = webDriver.findElement(By.cssSelector("input#input-email"));
		WebElement passwordInputFieldLogin = webDriver.findElement(By.cssSelector("input#input-password"));
		WebElement loginBtn = webDriver.findElement(By.cssSelector("input[type='submit']"));
		emailInputFieldLogin.sendKeys(randomEmail);
		passwordInputFieldLogin.sendKeys("Learningsel@01");
		loginBtn.click();

		// verifying the title page of my account
		String titleOfThePage = webDriver.getTitle();
		Assert.assertEquals(titleOfThePage, "My Account", "User is not logged in");

		// clicking on the laptop button
		WebElement laptopAndNoteBookBtn = webDriver
				.findElement(By.cssSelector("nav#menu>div:last-of-type>ul>li:nth-of-type(2)>a"));
		laptopAndNoteBookBtn.click();

		// clicking on the show all menu button
		WebElement showAllMenuBtn = webDriver
				.findElement(By.cssSelector("nav#menu>div:last-of-type>ul>li:nth-of-type(2)>div>a"));
		showAllMenuBtn.click();

		// verifying the title page
		String titleOfLaptopPage = webDriver.getTitle();
		Assert.assertEquals(titleOfLaptopPage, "Laptops & Notebooks");

		// selecting the HTC Touch HD button
		WebElement hpLaptopBtn = webDriver.findElement(
				By.cssSelector("div#content>div:nth-of-type(4)>div:first-of-type>div>div:last-of-type>div>h4>a"));
		hpLaptopBtn.click();

		// adding item into the cart button#button-cart
		WebElement addToCartBtn = webDriver.findElement(By.cssSelector("button#button-cart"));
		addToCartBtn.click();
		sleep();

		// item cart with price
		WebElement itemsBtn = webDriver.findElement(By.cssSelector("div#cart span"));
		sleep();
		String itemcartText = itemsBtn.getText();
		Assert.assertEquals(itemcartText, "1 item(s) - $122.00");
		itemsBtn.click();

		// click checkout from the dialog box
		WebElement checkOutBtn = webDriver.findElement(By.cssSelector("p.text-right>a:last-of-type>strong"));
		checkOutBtn.click();

		// fill the details for payment
		paymentDetails();

	}

	public void paymentDetails() {

		// Payment details
		WebElement firstNameInputField = webDriver.findElement(By.cssSelector("input#input-payment-firstname"));
		WebElement lastNameInputField = webDriver.findElement(By.cssSelector("input#input-payment-lastname"));
		WebElement address1InputField = webDriver.findElement(By.cssSelector("input#input-payment-address-1"));
		WebElement cityInputField = webDriver.findElement(By.cssSelector("input#input-payment-city"));
		WebElement postCodeInputField = webDriver.findElement(By.cssSelector("input#input-payment-postcode"));
		WebElement countryInputField = webDriver.findElement(By.cssSelector("select#input-payment-country"));
		WebElement regionInputField = webDriver.findElement(By.cssSelector("select#input-payment-zone"));
		WebElement continueBtn = webDriver.findElement(By.cssSelector("input#button-payment-address"));
		firstNameInputField.sendKeys("Learning");
		lastNameInputField.sendKeys("Selenium");
		address1InputField.sendKeys("1234 Valley Lake Drive");
		cityInputField.sendKeys("Franklin");
		postCodeInputField.sendKeys("98765");
		selectElementByVisibleText(countryInputField, "Canada");
		selectElementByVisibleText(regionInputField, "Nunavut");
		sleep();
		continueBtn.click();

		// delivery details
		WebElement DeliverDetailsRadioBtn = webDriver
				.findElement(By.cssSelector("div#collapse-shipping-address>div>form>div:first-of-type input "));
		boolean isSelectedDeliveryDetails = DeliverDetailsRadioBtn.isSelected();
		Assert.assertTrue(isSelectedDeliveryDetails, "Delivery details is not selected");
		sleep();
		WebElement continueBtnForDeliveryDetails = webDriver
				.findElement(By.cssSelector("input#button-shipping-address  "));
		continueBtnForDeliveryDetails.click();

		// delivery method
		WebElement DeliverMethodRadioBtn = webDriver.findElement(By.cssSelector("input[name='shipping_method']"));
		boolean isSelectedDeliveryMethod = DeliverMethodRadioBtn.isSelected();
		Assert.assertTrue(isSelectedDeliveryMethod, "Delivery method is not selected");
		sleep();
		WebElement commentInputField = webDriver.findElement(By.cssSelector("textarea[name='comment'] "));
		commentInputField.sendKeys("Hi");
		WebElement continueBtnForDeliveryMethod = webDriver
				.findElement(By.cssSelector("input#button-shipping-method  "));
		continueBtnForDeliveryMethod.click();

		// cash on delivery
		WebElement cashOnDeliveryRadioBtn = webDriver
				.findElement(By.cssSelector("div#collapse-payment-method>div>div:first-of-type input"));
		boolean isSelectedCashOnDelivery = cashOnDeliveryRadioBtn.isSelected();
		Assert.assertTrue(isSelectedCashOnDelivery, "Cash On Delivery method is not selected");
		sleep();
		WebElement checkboxPrivacyPolicy = webDriver.findElement(By.cssSelector("input[name='agree']"));
		checkboxPrivacyPolicy.click();
		WebElement continueBtnForCashOnDelivery = webDriver.findElement(By.cssSelector("input#button-payment-method"));
		continueBtnForCashOnDelivery.click();

		// confirm order
		WebElement productName = webDriver.findElement(By.cssSelector("div.table-responsive>table>tbody>tr a"));
		sleep();
		String prodNameText = productName.getText();
		Assert.assertEquals(prodNameText, "HP LP3065");
		WebElement quantity = webDriver
				.findElement(By.cssSelector("div.table-responsive>table>tbody>tr>td:nth-of-type(3)"));
		sleep();
		String quantityText = quantity.getText();
		Assert.assertEquals(quantityText, "1");
		WebElement confirmOrderBtn = webDriver.findElement(By.cssSelector("input#button-confirm"));
		confirmOrderBtn.click();
		sleep();
		String titleOfConfirmPage = webDriver.getTitle();
		Assert.assertEquals(titleOfConfirmPage, "Your order has been placed!", "Item not confirmed");

	}

	@AfterMethod
	public void teardown() {
		// webDriver.close();
	}

	public void selectElementByVisibleText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	public void sleep() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	//Generating random emails
	public String randomEmail() {
		Random random = new Random();
		int randomNum = random.nextInt(1000);
		String randomEmail = "test" + randomNum + "@gmail.com";
		return randomEmail;
	}

	//Account registering
	public void accountRegisteration() {

		WebElement inputFieldFirstName = webDriver
				.findElement(By.cssSelector("fieldset#account>div:nth-of-type(2) div input"));
		WebElement inputFieldLastName = webDriver
				.findElement(By.cssSelector("fieldset#account>div:nth-of-type(3) div input"));
		WebElement inputFieldEmail = webDriver
				.findElement(By.cssSelector("fieldset#account>div:nth-of-type(4) div input"));
		WebElement inputFieldTelephone = webDriver
				.findElement(By.cssSelector("fieldset#account>div:nth-of-type(5) div input"));
		WebElement inputFieldPassword = webDriver.findElement(
				By.cssSelector("form fieldset:nth-of-type(2) div.form-group.required:first-of-type input"));
		WebElement inputFieldPasswordConfirm = webDriver
				.findElement(By.cssSelector("form fieldset:nth-of-type(2) div.form-group.required:last-of-type input"));
		WebElement checkboxPrivacyPolicy = webDriver.findElement(By.cssSelector("input[type='checkbox']"));
		WebElement continueBtn = webDriver.findElement(By.cssSelector("input[type='submit']"));

		// Send a text into an input field
		inputFieldFirstName.sendKeys("Learning");
		inputFieldLastName.sendKeys("Selenium");
		inputFieldEmail.sendKeys(randomEmail);
		inputFieldTelephone.sendKeys("1234567890");
		inputFieldPassword.sendKeys("Learningsel@01");
		inputFieldPasswordConfirm.sendKeys("Learningsel@01");
		checkboxPrivacyPolicy.click();
		continueBtn.submit();
		String titleOfPage = webDriver.getTitle();
		Assert.assertEquals(titleOfPage, "Your Account Has Been Created!", "User is not registered");
		sleep();

		WebElement myAccountBtn = webDriver
				.findElement(By.cssSelector("div#top-links>ul>li:nth-of-type(2)>a>span:first-of-type"));
		myAccountBtn.click();
		sleep();
		WebElement logOutBtn = webDriver
				.findElement(By.cssSelector("div#top-links>ul>li:nth-of-type(2)>ul>li:nth-of-type(5) a"));
		logOutBtn.click();

	}
}
