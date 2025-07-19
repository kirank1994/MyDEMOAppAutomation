package org.mydemo.ios.testCases;

import org.mydemo.driver.IOSBaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TestCase2 extends IOSBaseTest {

	@Test(groups = {"regression", "smoke"})
    public void sampleTest1() throws InterruptedException {
    	driver.findElement(By.xpath("//XCUIElementTypeCollectionView/XCUIElementTypeCell[1]")).click();
    	Thread.sleep(3000);
    	System.out.println("Running regression test in TestCase2");
    }
}