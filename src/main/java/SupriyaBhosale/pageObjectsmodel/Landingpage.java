package SupriyaBhosale.pageObjectsmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SupriyaBhosale.AbstractComponents.AbstractCompoent;

public class Landingpage extends AbstractCompoent {
	
	WebDriver driver;
	
	public Landingpage(WebDriver driver) {
	
		super(driver);
		this.driver	= driver;
		PageFactory.initElements(driver, this);
	}

	  @FindBy(id ="userEmail")
       WebElement userEmail;
	  
	  @FindBy(id="userPassword")
	   WebElement Password;
	  
	  @FindBy(id="login")
	  WebElement Submit;
	  
	  @FindBy(css="[class*='flyInOut']")
	  WebElement errorMessage;
	  
	  public ProductCatlog loginApplication(String email, String passWord)
	  {
	       userEmail.sendKeys(email);
	       Password.sendKeys(passWord);
	       Submit.click();
	      ProductCatlog productcatLog = new ProductCatlog(driver);
		return productcatLog;
	  }
	  
	  public String getErrorMessage()
	  {
		  waitForWebElementtoApper(errorMessage);
		 return errorMessage.getText();
	  }
	  
	  
	  
	  public void goTo()
	  {
		  driver.get("https://rahulshettyacademy.com/client");
	  }

}
