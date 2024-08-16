package SupriyaBhosale.pageObjectsmodel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SupriyaBhosale.AbstractComponents.AbstractCompoent;

public class ProductCatlog extends AbstractCompoent{
	
	WebDriver driver;
	
	public ProductCatlog(WebDriver driver) {
	
		 super(driver);
		this.driver	= driver;
		PageFactory.initElements(driver, this);
	}

	  //List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	By productsBy = By.cssSelector(".mb-3");
	By addTocart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	
	  @FindBy(css =".mb-3")
      List<WebElement> products;
	  

	  @FindBy(css =".ng-animating")
      WebElement spineer;
	  
	public List<WebElement> getProductsList()
	{
		waitForElementtoApper(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String ProductName)
	{
		WebElement prod = getProductsList().stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
		return prod;
		 
     }
	
	public void addProductToCart(String ProductName) throws InterruptedException
	{
	      WebElement prod = getProductByName(ProductName);
		prod.findElement(addTocart).click();	
		waitForElementtoApper(toastMessage);
		waitForElementToDisapper(spineer);
		 
	}
	
	
	
	
	
}
