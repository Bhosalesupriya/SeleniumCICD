package SupriyaBhosale.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SupriyaBhosale.pageObjectsmodel.CartPage;
import SupriyaBhosale.pageObjectsmodel.OrderPage;

public class AbstractCompoent {
	
	WebDriver driver;
	public AbstractCompoent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

	 //driver.findElement(By.cssSelector("[routerlink*='cart']")).click();  
	 
	 @FindBy(css ="[routerlink*='cart']")
     WebElement CartHeader;
	 
	 @FindBy(css ="[routerlink*='myorders']")
     WebElement ordersHeader;
	
	public void waitForElementtoApper(By findBy)
	{
	
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
   
	}
	
	public void waitForWebElementtoApper(WebElement findBy)
	{
	
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    wait.until(ExpectedConditions.visibilityOf(findBy));
   
	}
	public CartPage goToCartPage()
	{
		CartHeader.click();
		 CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	
	public OrderPage goToOrderPage()
	
	{
		ordersHeader.click();
		 OrderPage orderpage = new OrderPage(driver);
		return orderpage;
	}
	
	
	
	public void waitForElementToDisapper(WebElement ele) throws InterruptedException
	{
		Thread.sleep(3000);
//		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//		 wait.until(ExpectedConditions.invisibilityOf(ele));

	}
}
