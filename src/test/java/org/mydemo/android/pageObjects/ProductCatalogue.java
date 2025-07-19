package org.mydemo.android.pageObjects;

import java.sql.DriverManager;
import java.util.List;

import org.mydemo.driver.AndroidBaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductCatalogue extends AndroidBaseTest {

	AndroidDriver driver;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='ADD TO CART']")
	private List<WebElement> addToCart;
	//driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']"))
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cart;
	

	
	public ProductCatalogue()
	{
		this.driver =(AndroidDriver) DriverManager.getDrivers();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this); //
		
	}
	
	public void addItemToCartByIndex(int index)
	{
		addToCart.get(index).click();
	
	}
	public MyCart goToCartPage() throws InterruptedException
	{
		cart.click();
		Thread.sleep(2000);
		return new MyCart(driver);
		
		
		
	}
	
}
