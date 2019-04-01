package androidApp;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class ChromeAndroidSetup {

	public static AndroidDriver<AndroidElement> startTesting(String deviceName)
			throws MalformedURLException {
		
		//this is for testing the chrome browser on android

		URL serverLink = new URL("http://127.0.0.1:4723/wd/hub"); // creates link to server on localhost

		DesiredCapabilities cap = new DesiredCapabilities(); // this is our capability
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName); // adds emulator to capabilities
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome"); // sets browser to chrome
		

		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(serverLink, cap); // creates android driver

		return driver;

	}

}

