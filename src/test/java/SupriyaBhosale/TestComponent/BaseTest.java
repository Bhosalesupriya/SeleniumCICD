package SupriyaBhosale.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;

import SupriyaBhosale.pageObjectsmodel.Landingpage;

public class BaseTest {
		
	public WebDriver driver;
	public Landingpage landpage;

	   public  WebDriver  initalizeDriver() throws IOException
	   {		   
		   //properties class 		   
		   Properties prop = new Properties();
		   
		   // C:\Users\hp\eclipse-workspace\SeleniumProjectEcommerace ---> user.dir
		                                                                                            
		    FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\SupriyaBhosale\\resources\\GlobalData.properties");
		   
		    
		   //FileInputStream fis = new FileInputStream("C:\\Users\\hp\\eclipse-workspace\\SeleniumProjectEcommerace\\src\\test\\java\\SupriyaBhosale\\resources\\GlobalData.properties");	   
		   prop.load(fis);
		   
		   String browserName=System.getProperty("browser")!=null ?   System.getProperty("browser"):prop.getProperty("browser");
		   
		   
		   //String browserName = prop.getProperty("browser");		   
		   if(browserName.contains("chrome"))
		   {
			   
//                 WebDriverManager.chromedriver().setup();
//			     driver = new ChromeDriver();
			     
		        ChromeOptions  options = new  ChromeOptions ();  //this line for headlees mode
			   WebDriverManager.chromedriver().setup();
			   
			   if(browserName.contains("headless"));
			   
			   {
				   options.addArguments("headless");  //this line for headlees mode
			   }
			     driver = new ChromeDriver(options);
			     driver.manage().window().setSize(new Dimension(1440,900));
			  
		    
	        }
		   
            else if  (browserName.equalsIgnoreCase("firefox"))
           {
		   	 System.setProperty("WebDriver.gecko.driver", "C:\\Users\\hp\\Downloads\\geckodriver-v0.33.0-win64");
			
			  driver = new FirefoxDriver();
     	   }
		   
//		   else if  (browserName.equalsIgnoreCase("edge"))
//		   {                                                        
//			   System.setProperty("WebDriver.Edge.driver", "C:\\Users\\hp\\Downloads\\edgedriver_win64");
//		            driver = new EdgeDriver();
//		   }
		   
		   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		     
	       driver.manage().window().maximize();
	       return driver;
            }
	
	   public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException 
		{
			//  converted json file into string 
		  String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		    
		  
		
		    // convert String to HashMap jackson databind then we got list 
		   
		   ObjectMapper objMapper = new ObjectMapper();
		   
		   List <HashMap<String, String>> data = objMapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
				   });
		    return data;
		} 
	   
       
		public String getScreenShot(String TestCaseName, WebDriver driver) throws IOException
		{
		  TakesScreenshot ts = (TakesScreenshot)driver;
		  File source = ts.getScreenshotAs(OutputType.FILE);
		  File Target = new File(System.getProperty("user.dir")+ "//reports//" + TestCaseName + ".png");
		  FileUtils.copyFile(source, Target);
		  return System.getProperty("user.dir")+ "//reports//" + TestCaseName + ".png" ;
		}
		
	   @BeforeMethod (alwaysRun=true)
	   public Landingpage lunchApplication() throws IOException
	   {
		   driver = initalizeDriver();
		   //Landingpage landpage = new Landingpage(driver);
		   landpage = new Landingpage(driver);
	       landpage.goTo();
	       
	       return landpage;
	   }
	   
	   @AfterMethod (alwaysRun=true)
	   public void tearDown()
	   {
		   driver.close();
	   }
}

