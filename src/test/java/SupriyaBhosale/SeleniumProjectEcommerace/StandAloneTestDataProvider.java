package SupriyaBhosale.SeleniumProjectEcommerace;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SupriyaBhosale.TestComponent.BaseTest;
import SupriyaBhosale.pageObjectsmodel.CartPage;
import SupriyaBhosale.pageObjectsmodel.CheckoutPage;
import SupriyaBhosale.pageObjectsmodel.ConfirmationPage;
import SupriyaBhosale.pageObjectsmodel.OrderPage;
import SupriyaBhosale.pageObjectsmodel.ProductCatlog;

public class StandAloneTestDataProvider extends BaseTest {

	String productname = "ZARA COAT 3";    
	
	@Test (dataProvider="getData", groups = {"purchase"})
	
	  public void StandAlone(String email, String password, String product) throws IOException, InterruptedException
	  {		
		
             
   
        // Landingpage landpage = lunchApplication();
         //ProductCatlog productcatLog= landpage.loginApplication("bhosalesupriya0912@gmail.com", "Supriya1234@");      
		
		ProductCatlog productcatLog= landpage.loginApplication(email, password);  
  
       //ProductCatlog productcatLog1 = new ProductCatlog(driver);
       List<WebElement> products =  productcatLog.getProductsList();
       productcatLog.addProductToCart(product);    // this productname came from line no 24 
    
        CartPage cartpage = productcatLog.goToCartPage();
        Boolean match = cartpage.verifyProductDisply(product);
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
	
	@DataProvider
	
	public Object[][] getData()
	{
		return new Object[][] {{ "bhosalesupriya0912@gmail.com","Supriya1234@","ZARA COAT 3"}, {"supriyabhosale0912@gmail.com", "Sup1234@", "ADIDAS ORIGINAL"} };           
	}
	

}
