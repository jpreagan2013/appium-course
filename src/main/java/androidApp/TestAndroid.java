package androidApp;

import java.net.MalformedURLException;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TestAndroid extends AndroidTestingMethods {

	public static void main(String[] args) throws MalformedURLException {

		//String nexusAndroid6 = "070f204c0073ac30"; creates string with Device ID

		AndroidDriver<AndroidElement> driver = startTesting("android8", "ApiDemos-debug.apk", true); // android driver with given emulator and APK, set permissions to true

		firstTest(driver); // tests various findElement methods
		driver = startTesting("android8", "ApiDemos-debug.apk", true);
		testAutomator(driver); // tests findElementByAndroidUIAutomator
		driver = startTesting("android8", "ApiDemos-debug.apk", true);
		testDragDrop(driver);
		driver = startTesting("android8", "ApiDemos-debug.apk", true);
		testGestures(driver);
		driver = startTesting("android8", "ApiDemos-debug.apk", true);
		testScrolling(driver);
		driver = startTesting("android8", "ApiDemos-debug.apk", true);
		miscTests(driver);
		driver = startTesting("android8", "ApiDemos-debug.apk", true);
		testSwipe(driver);

	}

}