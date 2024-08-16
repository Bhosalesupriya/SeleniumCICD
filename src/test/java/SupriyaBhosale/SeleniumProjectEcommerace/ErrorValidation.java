package SupriyaBhosale.SeleniumProjectEcommerace;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SupriyaBhosale.TestComponent.BaseTest;
import SupriyaBhosale.TestComponent.IRetry;
import SupriyaBhosale.pageObjectsmodel.CartPage;
import SupriyaBhosale.pageObjectsmodel.ProductCatlog;

public class ErrorValidation extends BaseTest {

	
	@Test(groups={"ErrorHandling"})
	//(groups={"ErrorHandling"}, retryAnalyzer=IRetry.class)
	
	  public void loginErroValidation() throws IOException, InterruptedException
	  {		
         String productname = "ZARA COAT 3";     
         // correct password id Bhosale1234@
        landpage.loginApplication("supriyabhosale0911@gmail.com", "Bhosale12"); 
       Assert.assertEquals("Incorrect email or password.", landpage.getErrorMessage());
    			
	  }
	
	@Test 
	
	  public void productErrorValidation() throws IOException, InterruptedException
	  {		
       String productname = "ZARA COAT 3";        
       ProductCatlog productcatLog= landpage.loginApplication("supriyabhosale0912@gmail.com", "Sup1234@");       
        //ProductCatlog productcatLog1 = new ProductCatlog(driver);
        List<WebElement> products =  productcatLog.getProductsList();
        productcatLog.addProductToCart(productname);    // this productname came from line no 24 
        CartPage cartpage = productcatLog.goToCartPage();
        Boolean match = cartpage.verifyProductDisply("ZARA COAT 3");
        Assert.assertTrue(match);
      
	  }
	
	
	
	
	
	
	
	
	
	
	
	
	   
	  }
