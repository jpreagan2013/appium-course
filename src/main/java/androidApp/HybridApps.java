package androidApp;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class HybridApps {

	public static void main(String[] args) throws MalformedURLException {

		File appFile = new File(new File("src"), "WebViewTest.apk"); // looks for app name in source folder
		URL serverLink = new URL("http://127.0.0.1:4723/wd/hub"); // creates link to server on localhost
		DesiredCapabilities cap = new DesiredCapabilities(); // this is our capability

		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "android8");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 100); // take this long before throwing an error
		cap.setCapability(MobileCapabilityType.APP, appFile.getAbsolutePath());
		cap.setCapability("automationName", "uiautomator2");

		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(serverLink, cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		System.out.println(driver.getContext());
		driver.findElementByXPath("//android.widget.ImageButton[@content-desc='Open navigation drawer']").click();
		driver.findElementByAndroidUIAutomator("text(\"WebView\")").click();
		driver.findElementByAndroidUIAutomator("text(\"MOVE\")").click();
		
		Set<String> contextSet = driver.getContextHandles();
		for (String context : contextSet) {
	        System.out.println(context);
	     }
		driver.context("WEBVIEW_com.snc.test.webview2");
		System.out.println(driver.getContext());
		driver.findElementByXPath("//*[@id=\"tsf\"]/div[2]/div/div[1]/div/div[1]/input").sendKeys("Test");
		driver.findElementByXPath("//*[@id=\"tsf\"]/div[2]/div[1]/div[2]/ul/li[2]").click();
		driver.findElementByXPath("//*[@id=\"rso\"]/div[3]/div[1]/div/div/div[1]/div/a/div[1]").click();
		
		
		
	}

}