package org.mydemo.driver;

import org.testng.annotations.BeforeMethod;

import io.appium.java_client.ios.IOSDriver;

public class IOSBaseTest extends BaseTest {
    protected IOSDriver driver;

    @BeforeMethod(alwaysRun = true)
	public void preSetup() {
		this.driver=(IOSDriver) super.driver;
		//formPage = new FormPage();
//		Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
//		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of(
//		    "intent", "com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"
//		));
		
	}
}