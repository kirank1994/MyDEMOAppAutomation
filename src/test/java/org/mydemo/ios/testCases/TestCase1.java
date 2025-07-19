package org.mydemo.ios.testCases;

import org.mydemo.driver.IOSBaseTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class TestCase1 extends IOSBaseTest {

	@Test(groups = {"E2E", "regression"})
    public void sampleTest1() throws InterruptedException {
    	driver.findElement(AppiumBy.accessibilityId("More-tab-item")).click();
    	Thread.sleep(3000);
    	System.out.println("Running regression test in TestCase1");
    }
}