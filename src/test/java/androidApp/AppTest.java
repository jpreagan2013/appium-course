package androidApp;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

/**
 * Unit test for simple App.
 */
public class AppTest {

	public AndroidDriver<AndroidElement> driver;
	
	@BeforeSuite
	public void appiumEnvironmentSetup() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("cmd /c start C:\\Users\\jreagan\\AppData\\Roaming\\npm\\appium.cmd"); //start appium
		Runtime.getRuntime().exec("cmd /c start C:\\Users\\jreagan\\AppData\\Local\\Android\\Sdk\\emulator\\emulator.exe -avd android9"); //start emulator
		Thread.sleep(7000L);
	}

	@BeforeSuite
	public void createDriver() throws MalformedURLException {

		File appFile = new File("C:\\Users\\jreagan\\Downloads\\com.mobilereach.splitware.apk");

		URL serverLink = new URL("http://127.0.0.1:4723/wd/hub"); //local server appium connect

		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "android8");
		cap.setCapability(MobileCapabilityType.APP, appFile.getAbsolutePath());
		cap.setCapability("automationName", "uiautomator2");
		// TODO set capability ability to switch between activities
		cap.setCapability("appActivity","md5aa543aa8df6c99f6e5601bb3927a01fb.Launcher"); // set to launcher
		//cap.setCapability("appWaitActivity", "md5790fc64191492b3b01d27749c406e004.LoginDialog"); // wait for login
		cap.setCapability("autoGrantPermissions", true);

		/*
		 * use this code if you have the package and activity, but no APK: cap.setCapability("appPackage", "io.appium.android.apis"); cap.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
		 */

		driver = new AndroidDriver<AndroidElement>(serverLink, cap); // creates android driver
		System.out.println(driver.currentActivity());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // wait 10 seconds

	}

	@Test
	public void logInTest() throws UnknownHostException {
		// This is specifically written to test my IP address
		InetAddress inetAddress = InetAddress.getLocalHost();
		String hostIP = inetAddress.getHostAddress();
		List<AndroidElement> Elements = driver.findElementsByClassName("android.widget.EditText");
		int elementNumber = 0;
		for (AndroidElement e : Elements) {
			if (elementNumber == 0) {
				e.sendKeys(hostIP);
			} else if (elementNumber == 1) {
				e.sendKeys("admin");
			} else {
				e.sendKeys("55cents$");
			}
			elementNumber++;
		}
		driver.findElementByAndroidUIAutomator("text(\"Login\")").click();
		By myPath = By.className("android.widget.ImageButton"); // first image button, settings
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30); // maximum wait is 30 seconds
			wait.until(ExpectedConditions.visibilityOfElementLocated(myPath));
		} catch (Exception e) {
			System.out.println("Timeout waiting for login");
		}
		driver.findElement(myPath).click();
		driver.findElementByAndroidUIAutomator("text(\"Reset\")").click();
		driver.findElementByAndroidUIAutomator("text(\"Yes\")").click();
		driver.closeApp();
	}
}
