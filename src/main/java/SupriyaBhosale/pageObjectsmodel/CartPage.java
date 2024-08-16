package SupriyaBhosale.pageObjectsmodel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import SupriyaBhosale.AbstractComponents.AbstractCompoent;

public class CartPage extends AbstractCompoent {
	
		
WebDriver driver;
	
	public CartPage(WebDriver driver) {
	
		 super(driver);
		this.driver	= driver;
		PageFactory.initElements(driver, this);
	}
	
	 //List <WebElement> cartproducts =  driver.findElements(By.cssSelector(".cartSection h3"));
	 
	 @FindBy(css =".cartSection h3")
      List<WebElement> ProductTitel;
	 
	 @FindBy(css =".totalRow button")
     WebElement checkoutEle;
	
     
     //driver.findElement(By.cssSelector(".totalRow button")).click(); 
	 
	 
	 public Boolean verifyProductDisply(String productname)
	 {
		 Boolean match = ProductTitel.stream().anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productname));
	     
	     return  match; 
	 }
	 
	 public CheckoutPage goToCheckOut()
	 {
		 checkoutEle.click();
		 return new CheckoutPage(driver);
	 }
     


}



