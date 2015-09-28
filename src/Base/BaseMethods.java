package Base;


import java.awt.Rectangle;
//import java.awt.Point;
//import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.Spring;

import org.apache.commons.io.FileUtils;
import org.junit.rules.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

//import com.sun.jna.platform.FileUtils;
import com.thoughtworks.selenium.webdriven.commands.Open;




public class BaseMethods {
public static  WebDriver driver ;

	
//public  BaseMethods(WebDriver driver)
//{
//
//	BaseMethods.driver = driver;
//
//	}
	
		
	
  
	
	
	
	

    
	 public static WebDriver GetDriver(String BrowserName){
     
         try{                  
             
             switch (BrowserName.toLowerCase())
             {
                 case "ie": 
                	 File file = new File("C:/Selenium/IE/IEDriverServer.exe");
                	 System.setProperty("webdriver.ie.driver", file.getAbsolutePath());

                     driver = new InternetExplorerDriver();                     
                     break;

                 case "chrome":
                     driver = new ChromeDriver();
                     break;

                 case "firefox":
                     driver = new FirefoxDriver();
                     break;


//                 default:
//                	 System.out.println("Sorry, invalid selection of ''Browser name''.");                     
//                     break;
             }
             
             
//             BaseMethods.driver.manage().window().maximize();
            // BaseMethods.driver.get(url);// also works
//             BaseMethods.driver.navigate().to(url);
             
             
         }         catch (NoSuchElementException e){         

        	 System.out.println(e.getMessage());

         }
         return driver ;
         
	 }

	 public static void GetDriver(){
		    
	       GetDriver("IE"); // Using regular method overloads>>> as parameter url has dynamic value "GlobalVariables.iPOSServer"
	    }
	 
	 
	 public static void SwitchFrame(WebDriver driver,String FrameValue,String FrameType,int WaitTimeInSec)
     {
         try
         {
             driver.switchTo().defaultContent();
             switch (FrameType.toLowerCase())
             {
                 case "id":
                     WaitForElement(driver,By.id(FrameValue), WaitTimeInSec);
                     driver.switchTo().frame(FrameValue);
                     break;

                 case "default":
                     driver.switchTo().defaultContent();                     
                     break;

                 case "name":
                     WaitForElement(driver,By.name(FrameValue), WaitTimeInSec);
                     driver.switchTo().frame(FrameValue);
                     break;
                 

                 default:
                	 System.out.println("Sorry, invalid selection of ''ByType''.");
                     break;
             }

             
         }
         catch (NoSuchElementException e){         

        	 System.out.println(e.getMessage());
         }
     }
	
	 public static void SwitchFrame(WebDriver driver,String FrameValue){
		SwitchFrame(driver,FrameValue,"ID",30);
	 }
	 
	 
	 
	 public static void SwitchWindow(WebDriver driver,String Title, int WindowCount, int WaitTimeInSec) throws InterruptedException  
     {
         try
         {
             // ..... METHOD 1 .......
             String BaseWindow = driver.getWindowHandle();

            
             int count = 0;
             while ((driver.getWindowHandles().size()) != WindowCount)
             {
                 count++;                 
                 Thread.sleep(1000);
                
                 if (count == 30)
                 {
                	 System.out.println("Window with title '" + Title + "' still not found even after  '" + WaitTimeInSec + "' seconds");
                     break;
                 }

                 if (driver.getWindowHandles().size() == WindowCount) break;
             }

             Set<String> handles = driver.getWindowHandles();

             for (String handle:handles)
                
             {
                 
                 if (handle != BaseWindow)
                 {

                     if (driver.switchTo().window(handle).getTitle().contains(Title))
                     {
                    	 System.out.println("Window "+ WindowCount +" title: "+driver.getTitle());
                         break;

                     }                      

                   
                 }
             }
             
         }
     
         catch (NoSuchElementException e){  


         }
     }
	 
	 public static void SwitchWindow(WebDriver driver,String Title, int WindowCount) throws InterruptedException
     {
		 SwitchWindow(driver,Title, WindowCount,10) ;
     }


	 public static void WaitForElement( WebDriver driver,By by, int timeoutInSeconds)
     {
         
         //WebDriverWait wait =  new WebDriverWait(driver, Timeout.seconds(timeoutInSeconds);
         WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);

         //wait.Until(drv => drv.FindElement(by)); // meothod 1
         wait.until(ExpectedConditions.visibilityOfElementLocated(by)); // method 2 works for visible element only


     }
	 
	 public static void WaitForElement( WebDriver driver,By by){ // over load function to have default timeoutinSeconds=10
		 WaitForElement(driver,by,10);
	 }
	 
	 
	 public static <IWebElement> WebElement  GetElement(WebDriver driver,By by, int timeoutInSeconds)
     {
        
         if (timeoutInSeconds > 0)
         {
        	 WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
             //return wait.Until(drv => drv.FindElement(by));
        	 wait.until(ExpectedConditions.visibilityOfElementLocated(by));
         }
         return driver.findElement(by);
     }
	 
	 public static <IWebElement> WebElement  GetElement(WebDriver driver,By by){
		 
		 return GetElement(driver,by,10);
	 }
	 
	 
	 
	 public static  List <WebElement> GetElements(WebDriver driver,By by, int timeoutInSeconds)
     {
         
         if (timeoutInSeconds > 0)
         {
        	 
             WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);     
             wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
         }
         return driver.findElements(by);
     }
	 
	 // to click on web element
	 public static void ClickElement(WebElement WebElement)
     {
         try
         {
             WebElement.click();

         }
         catch (NoSuchElementException e)
         {
        	 System.out.println(e.getMessage());

         }


     }
	 
	 
	 // To double click on Web Element 
     public static void DoubleClickElement(WebElement WebElement){
     
         try{
         
             //create Actions object
        	 Actions act = new Actions(driver);
             //Use DoubleClick method to double click on any element
             act.doubleClick(WebElement).build().perform();
            
             
         }
         catch (NoSuchElementException e){
         
        	 System.out.println(e.getMessage());
             
         }
       

     }
     
     // To  keyin on Web Element 
     public static void KeyInElement(WebElement WebElement, String Value)
     {
         try
         {
             WebElement.sendKeys(Value);

         }
         catch (NoSuchElementException e)
         {
        	 System.out.println("Unable to key in value " + Value + " on web element");

         }
     }
	 
     
  // to select an element from given list of selection box            
     public static void SelectElement(WebElement WebElement, String Value)
     {
         try
         {
             Select select = new Select(WebElement);
             select.selectByValue(Value);
         }
         catch (NoSuchElementException e)
         {
        	 System.out.println("Unable to select value " + Value);

         }


     }

     // To take screen shot of page
     public  static void captureScreenShot(String obj) throws IOException {
         File screenshotFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         FileUtils.copyFile(screenshotFile,new File("Screenshots\\"+obj+""+GetTimeStampValue()+".png"));
   }

   public static  String GetTimeStampValue()throws IOException{

           Calendar cal = Calendar.getInstance();       
            java.util.Date time=cal.getTime();
            String timestamp=time.toString();
               System.out.println(timestamp);
               String systime=timestamp.replace(":", "-");
               System.out.println(systime);
           return systime;

   }
//taking screenshot of a specific element
   public void takeScreenshotElement(WebElement element) throws IOException {
	    WrapsDriver wrapsDriver = (WrapsDriver) element;
	    File screenshot = ((TakesScreenshot) wrapsDriver.getWrappedDriver()).getScreenshotAs(OutputType.FILE);
	    Rectangle rectangle = new Rectangle(element.getSize().width, element.getSize().height);
	    Point location = element.getLocation();
	    BufferedImage bufferedImage = ImageIO.read(screenshot);
	    BufferedImage destImage = bufferedImage.getSubimage(location.x, location.y, rectangle.width, rectangle.height);
	    ImageIO.write(destImage, "png", screenshot);
	    File file = new File("//path//to");
	    FileUtils.copyFile(screenshot, file);
	}


	 
	 
     
     
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 // write function above
	 
}


