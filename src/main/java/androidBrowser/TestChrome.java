package androidBrowser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestChrome {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\jreagan\\Documents\\chromedriver.exe");

		// Initialize browser
		WebDriver driver = new ChromeDriver();

		// Maximize browser
		driver.manage().window().maximize();

		//open site, set xpath
		driver.get("https://fantasycricket.dream11.com/IN/");
		WebDriverWait wd = new WebDriverWait(driver,5); //explicit wait - will wait in this amount of time, but will stop when it finds the object
		//implicit wait will always wait X seconds
		
		try {
			//code for something you are worried might fail
		} catch (Exception e) {
			//take a screenshot when the script fails
		}
		
		By byPath = By.xpath("//*[@id=\"recaptcha-anchor\"]/div[5]"); //captcha
		
		/*
		 * TODO: type a little bit to get the dropdown to pop up
		 * This is an xpath with a sendkeys
		 * */
		
		By dropdown = By.xpath("//*[@id=\"m_frmRegister\"]/div[1]/ul"); //email dropdown
		
		wd.until(ExpectedConditions.elementToBeClickable(dropdown)); //we wait until the dropdown is clickable
		
		/*
		 * TODO: click on the value you want of the dropdown using XPATH of the value and .click()
		 * I did not add this because the code is really long and we cannot test it
		 * */
		
		int number = findFrameNumber(driver, byPath);
		driver.switchTo().frame(number); //switches the new frame
		driver.findElement(byPath).click();
		driver.switchTo().defaultContent(); //switches to either the first frame on the page, or the main document when a page contains iframes.
		
		int number2 = findFrameNumber(driver, By.xpath("//*[@id='recaptcha-verify-button']")); //captcha button
		driver.switchTo().frame(number2); //switches the new frame we're looking for
		driver.findElement(By.xpath("//*[@id='recaptcha-verify-button']")).click();
	}

	public static int findFrameNumber(WebDriver driver, By by) {

		//Code takes a driver and an XPath, looks for the iframe with that XPath, returns frame number
		int i;
		int frameCount = driver.findElements(By.tagName("iframe")).size();

		for (i = 0; i < frameCount; i++) {
			System.out.println(i);
			driver.switchTo().frame(i);
			int count = driver.findElements(by).size();
			if (count > 0) {
				break;
			} else {
				System.out.println("Still Looking");
			}
		}
		driver.switchTo().defaultContent(); //switches to either the first frame on the page, or the main document when a page contains iframes.
		return i;
	}

}