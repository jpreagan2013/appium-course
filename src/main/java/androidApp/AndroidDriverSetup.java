package androidApp;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class AndroidDriverSetup {

	public static AndroidDriver<AndroidElement> startTesting(String deviceName, String appName, boolean permissions)
			throws MalformedURLException {

		File appFile = new File(new File("src"), appName); // looks for app name in source folder

		URL serverLink = new URL("http://127.0.0.1:4723/wd/hub"); // creates link to server on localhost

		DesiredCapabilities cap = new DesiredCapabilities(); // this is our capability

		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID); // sets platform to android
		cap.setCapability("deviceName", deviceName); // adds emulator to capabilities
		cap.setCapability(MobileCapabilityType.APP, appFile.getAbsolutePath()); // adds app to capabilities
		cap.setCapability("automationName", "uiautomator2"); // use new UI Automator

		/*
		 * use this code if you have the package and activity, but no APK:
		 * cap.setCapability("appPackage", "io.appium.android.apis");
		 * cap.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
		 */
		
		if (permissions == true) {
			cap.setCapability("autoGrantPermissions", true); // automatically grants permissions if true
		}

		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(serverLink, cap); // creates android driver
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // wait 10 seconds

		return driver;

	}

}