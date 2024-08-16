package SupriyaBhosale.pageObjectsmodel;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SupriyaBhosale.AbstractComponents.AbstractCompoent;

public class CheckoutPage extends AbstractCompoent {
	
	
WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
	
		 super(driver);
		this.driver	= driver;
		PageFactory.initElements(driver, this);
	}
     
	@FindBy(css =".action__submit")
    WebElement submit;
	
	
	 // driver.findElement(By.cssSelector(".action__submit")).click();
	//(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
	
	@FindBy(css ="[placeholder='Select Country']")
    WebElement country;
	
	@FindBy(xpath ="(//button[contains(@class, 'ta-item')])[2] ")
    WebElement selectcountry;
	
	
	By results = By.cssSelector(".ta-results");
	
	
	//(By.xpath("(//button[contains(@class, 'ta-item')])[2] ")).click();
	
	public void selectcountry( String CountryName)
	{
		 Actions a = new Actions(driver);
        a.sendKeys(country, CountryName).build().perform();
        waitForElementtoApper(results);

        selectcountry.click();
	}
	
	public ConfirmationPage submitOrder()
	{
	   submit.click();
	   return new ConfirmationPage(driver);
	}
}
