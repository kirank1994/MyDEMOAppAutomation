package org.mydemo.android.testCases;

import org.mydemo.driver.AndroidBaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TestCase2 extends AndroidBaseTest {

	@Test(groups = {"regression", "smoke"})
    public void sampleTest1() throws InterruptedException {
    	driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@content-desc=\"Displays all products of catalog\"]/android.view.ViewGroup[1]")).click();
    	Thread.sleep(3000);
    	System.out.println("Running regression test in TestCase2");
    }
}