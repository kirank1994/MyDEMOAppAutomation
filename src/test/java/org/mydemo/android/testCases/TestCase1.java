package org.mydemo.android.testCases;

import org.mydemo.driver.AndroidBaseTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class TestCase1 extends AndroidBaseTest {

	@Test(groups = {"E2E", "regression"})
    public void sampleTest1() throws InterruptedException {
    	driver.findElement(AppiumBy.accessibilityId("View menu")).click();
    	Thread.sleep(3000);
    	System.out.println("Running regression test in TestCase1");
    }
}