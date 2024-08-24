package SupriyaBhosale.SeleniumProjectEcommerace;


import java.io.IOException;
import java.util.HashMap;
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

public class StandAloneTestHashmap extends BaseTest {
String productname = "ZARA COAT 3";    
	
	@Test (dataProvider="getData", groups= {"purchase"})
	
	  public void StandAlone(HashMap<String, String> input) throws IOException, InterruptedException
	  {		
            // did some changes for CI CD  
   
        // Landingpage landpage = lunchApplication();
         //ProductCatlog productcatLog= landpage.loginApplication("bhosalesupriya0912@gmail.com", "Supriya1234@");      
		
		ProductCatlog productcatLog= landpage.loginApplication(input.get("email"), input.get("password"));  
  
       //ProductCatlog productcatLog1 = new ProductCatlog(driver);
       List<WebElement> products =  productcatLog.getProductsList();
       productcatLog.addProductToCart(input.get("product"));    // this productname came from line no 24 
    
        CartPage cartpage = productcatLog.goToCartPage();
        Boolean match = cartpage.verifyProductDisply(input.get("product"));
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
	
	public Object[][] getData()throws IOException 
	{
	
		// here we created one json file and added the below data in json file.
		
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "bhosalesupriya0912@gmail.com");
//		map.put("password", "Supriya1234@");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "supriyabhosale0912@gmail.com");
//		map1.put("password", "Sup1234@");
//		map1.put("product", "ADIDAS ORIGINAL");
//
//		return new Object[][] {{map} , {map1}};
		
     List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+ "\\src\\test\\java\\SupriyaBhosale\\data\\purchaseOrder.json"); 	
	
	    return  new Object[][] {{data.get(0)} , {data.get(1)}};                            
		
		
	}
	
}
