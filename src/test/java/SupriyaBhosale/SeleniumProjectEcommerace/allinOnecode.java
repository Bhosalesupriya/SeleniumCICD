package SupriyaBhosale.SeleniumProjectEcommerace;

import java.beans.Visibility;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import SupriyaBhosale.pageObjectsmodel.Landingpage;

public class allinOnecode {

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   System.setProperty("WebDriver.Chrome.driver", "C:\\Users\\hp\\Downloads\\chromedriver_win32");
	       
		     String productname = "ZARA COAT 3";
		       WebDriver  driver = new ChromeDriver();
		       
		       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		       driver.manage().window().maximize();
		      driver.get("https://rahulshettyacademy.com/client");
		       
		      
		        
		       driver.findElement(By.id("userEmail")).sendKeys("bhosalesupriya0912@gmail.com");
		       driver.findElement(By.id("userPassword")).sendKeys("Supriya1234@");
		       driver.findElement(By.id("login")).click();
		       
		       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		       wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		       List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		       
		       // we are checking whatever product name we are adding is equal to product name or not 
		       
		   WebElement prod = products.stream().filter(product ->  product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
			 
		         prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		         
		         // we are waiting until product added message display 
		      
		          wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		          
//		          wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		          wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		          
		         driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		         
		       
		        
		        // here we are checking if multiple product added in cart section , then what product name we added which is present in cart or not 
		        
		        List <WebElement> cartproducts =  driver.findElements(By.cssSelector(".cartSection h3"));
		        
		        Boolean match = cartproducts.stream().anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productname));
		        
		        Assert.assertTrue(match);
		        
		        driver.findElement(By.cssSelector(".totalRow button")).click(); 
		        
		        // here we are selecting drop down value using Action class 
		        
		        Actions a = new Actions(driver);
		        
		         //  a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();
		        
		        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		           
		           wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		           
		           // (//button[contains(@class, 'ta-item')])[2]  or  //     .ta-item:nth-of-type(2) 
		           
		           driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2] ")).click();
		           driver.findElement(By.cssSelector(".action__submit")).click();
		           
		           String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		           
		           Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		        
			}

	}

