package androidApp;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

import java.net.MalformedURLException;

import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AndroidTestingMethods extends AndroidDriverSetup {

	public static void firstTest(AndroidDriver<AndroidElement> driver) {

		driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click(); // using class and text, must be unique
		driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();
		driver.findElementById("android:id/checkbox").click(); // uses resource id
		driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click(); // this will click on the second "Relative Layout" class on the view
		driver.findElementByClassName("android.widget.EditText").sendKeys("TEST"); // this works because it is the only edit box
		driver.findElementById("android:id/button1").click();

	}

	public static void testAutomator(AndroidDriver<AndroidElement> driver) throws MalformedURLException {

		driver.findElementByAndroidUIAutomator("text(\"App\")").click(); // "attribute(\"Value\")"
		driver.findElementByAndroidUIAutomator("new UiSelector().index(4)").click(); // opens index 4

	}

	public static void testDragDrop(AndroidDriver<AndroidElement> driver) {

		TouchAction touch = new TouchAction(driver); // create a touch action using the driver

		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();

		driver.findElementByAndroidUIAutomator("new UiSelector().index(7)").click(); // clicks on drag and drop

		// setting up two web elements
		WebElement first = driver.findElementById("io.appium.android.apis:id/drag_dot_1");
		WebElement second = driver.findElementById("io.appium.android.apis:id/drag_dot_2");

		// longpress element one and move to element two; no options
		touch.longPress(element(first)).moveTo(element(second)).release().perform();

	}

	public static void testGestures(AndroidDriver<AndroidElement> driver) {

		TouchAction touch = new TouchAction(driver); // create a touch action using the driver

		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();

		WebElement expandList = driver.findElementByXPath("//android.widget.TextView[@text='Expandable Lists']");

		// tap element expandList; using options
		touch.tap(tapOptions().withElement(element(expandList))).perform();

		driver.findElementByAndroidUIAutomator("text(\"1. Custom Adapter\")").click();

		// updates expandList
		expandList = driver.findElementByXPath("//android.widget.TextView[@text='People Names']");

		// long press the web element; no options
		touch.longPress(element(expandList)).perform();

	}

	public static void testScrolling(AndroidDriver<AndroidElement> driver) {

		TouchAction touch = new TouchAction(driver);

		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"); // using UIAutomator to utilize Android API call
		driver.findElementByAndroidUIAutomator("text(\"WebView\")").click();

	}

	public static void miscTests(AndroidDriver<AndroidElement> driver) {

		// various validations
		System.out.println(driver.currentActivity()); // validates the activity
		System.out.println(driver.getContext()); // validates view: Native, Hybrid, WebView
		System.out.println(driver.getOrientation()); // validates orientation
		System.out.println(driver.isDeviceLocked()); // validates if device is locked or not

		// pressing the back button
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		driver.pressKey(new KeyEvent(AndroidKey.BACK));

	}

	public static void testSwipe(AndroidDriver<AndroidElement> driver) {

		TouchAction touch = new TouchAction(driver);

		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().index(6)").click();
		driver.findElementByAndroidUIAutomator("text(\"2. Inline\")").click();
		driver.findElementByAndroidUIAutomator("new UiSelector().index(8)").click();

		//swipes from one element to another
		WebElement first = driver.findElementByXPath("//*[@content-desc='15']"); // do not use index, because index changes. * represents all classes
		WebElement second = driver.findElementByXPath("//*[@content-desc='45']");
		touch.longPress(element(first)).moveTo(element(second)).release().perform();

	}

}