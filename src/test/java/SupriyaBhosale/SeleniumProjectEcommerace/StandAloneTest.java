package SupriyaBhosale.SeleniumProjectEcommerace;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SupriyaBhosale.TestComponent.BaseTest;
import SupriyaBhosale.pageObjectsmodel.CartPage;
import SupriyaBhosale.pageObjectsmodel.CheckoutPage;
import SupriyaBhosale.pageObjectsmodel.ConfirmationPage;
import SupriyaBhosale.pageObjectsmodel.Landingpage;
import SupriyaBhosale.pageObjectsmodel.OrderPage;
import SupriyaBhosale.pageObjectsmodel.ProductCatlog;

public class StandAloneTest extends BaseTest {

	String productname = "ZARA COAT 3";    
	
	@Test 
	
	  public void StandAlone() throws IOException, InterruptedException
	  {		
             
   
        // Landingpage landpage = lunchApplication();
         //ProductCatlog productcatLog= landpage.loginApplication("bhosalesupriya0912@gmail.com", "Supriya1234@");      
		
		ProductCatlog productcatLog= landpage.loginApplication("gargi.b94@gmail.com","Prit0911");  
  
       //ProductCatlog productcatLog1 = new ProductCatlog(driver);
       List<WebElement> products =  productcatLog.getProductsList();
       productcatLog.addProductToCart(productname);    // this productname came from line no 24 
    
        CartPage cartpage = productcatLog.goToCartPage();
        Boolean match = cartpage.verifyProductDisply(productname);
        Assert.assertTrue(match);
        
        CheckoutPage checkoutpage = cartpage.goToCheckOut();   
        checkoutpage.selectcountry("india");
        ConfirmationPage confirmpage = checkoutpage.submitOrder();
       
           String confirmMessage = confirmpage.getConfirmMessage();
           
           Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        
	}
	
	@Test(dependsOnMethods={"StandAlone"})
	 
	public void OrderHistoryTest() 
	{
		  //"ZARA COAT 3"
		  ProductCatlog productcatLog= landpage.loginApplication("bhosalesupriya0912@gmail.com", "Supriya1234@"); 
		  OrderPage orderpage=  productcatLog.goToOrderPage();
		  Assert.assertTrue(orderpage.verifyOrderDisply(productname));
	}
	

}
