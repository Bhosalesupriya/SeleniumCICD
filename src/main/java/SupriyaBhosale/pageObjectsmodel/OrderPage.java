package SupriyaBhosale.pageObjectsmodel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import SupriyaBhosale.AbstractComponents.AbstractCompoent;

public class OrderPage extends AbstractCompoent {
	
		
WebDriver driver;
	
	public OrderPage(WebDriver driver) {
	
		
		 super(driver);
		this.driver	= driver;
		PageFactory.initElements(driver, this);
	}
	
	 //List <WebElement> cartproducts =  driver.findElements(By.cssSelector(".cartSection h3"));
	 
	 @FindBy(css =".totalRow button")
     WebElement checkoutEle;
	
	 @FindBy(css ="tr td:nth-child(3)")
     private List<WebElement> ProductNames;
	 
	 
     //driver.findElement(By.cssSelector(".totalRow button")).click(); 
	 
	 
	 public Boolean verifyOrderDisply(String productname)
	 {
		 Boolean match = ProductNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productname));
	     
	     return  match; 
	 }
	 
	 public CheckoutPage goToCheckOut()
	 {
		 checkoutEle.click();
		 return new CheckoutPage(driver);
	 }
     


}



