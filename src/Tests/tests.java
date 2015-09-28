package Tests;




import Base.BaseMethods;
import Base.GlobalVariables;
import pageObjects.LoginPage;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import utilities.TableUtil;

import org.testng.annotations.BeforeMethod;
 
import org.testng.annotations.AfterMethod;


public class tests {
	public static WebDriver driver;
	
	public  Assertion hardAssert = new Assertion(); 
	public  SoftAssert softAssert = new SoftAssert(); 

	@BeforeMethod
	public void beforeMethod() throws Exception {
		 
		driver= BaseMethods.GetDriver("ie");  
		driver.manage().window().maximize();        
       BaseMethods.driver.navigate().to("http://" + GlobalVariables.iPOSServer + "/iPOS/Web/Login.aspx"); 
	}
	
	@AfterMethod
	public void AfterMethod() throws Exception {
		 
//		driver.quit();
	}
	
	@Test

 public  void test1() throws InterruptedException, IOException { //throws InterruptedException {
				
	 LoginPage page = PageFactory.initElements(driver, LoginPage.class);	
     BaseMethods.KeyInElement(page.UserNameText, "rh");            
     BaseMethods.KeyInElement(page.PasswordText, "rh1");           
     BaseMethods.ClickElement(page.LoginButton);
     System.out.println("User name and password entered successfully");   
     BaseMethods.SwitchFrame(driver,"topFrame");            
     BaseMethods.ClickElement(page.Create);           
     BaseMethods.SwitchFrame(driver,"mainFrame");
     TableUtil.TableToXML(driver,"Raised By");
     BaseMethods.captureScreenShot(driver,"AddItemPage");
     // testing for aruna
//     System.out.println("\t" + TableUtilities.GetTable("Created Date").getAttribute("class"));
//     WebElement AddItem=BaseMethods.GetElement(driver,(By.id("ibAdd")));
//     BaseMethods.ClickElement(AddItem);
////     hardAssert.assertTrue(false);
//     softAssert.assertTrue(false);;
//     
//	 BaseMethods.SwitchWindow(driver,"Req Creation", 2);	
//     BaseMethods.SwitchFrame(driver,"REQAddLineBody");
//     BaseMethods.ClickElement(page.ItemCode_mag);   
//	 BaseMethods.SwitchWindow(driver,"SelectionItem", 3);
//     BaseMethods.KeyInElement(BaseMethods.GetElement( driver,By.id("xgvItems_DXFREditorcol0_I")), "CFEE1");
// 	Thread.sleep(2000);
//     WebElement caption = BaseMethods.GetElement(driver,By.xpath("//td[contains(text(),'CFEE1')]"));
//     BaseMethods.DoubleClickElement(caption);// driver.FindElement(By.Id("xgvItems_DXDataRow0")));
//     BaseMethods.SwitchWindow(driver,"Req Creation", 2);      
//     BaseMethods.SwitchFrame(driver,"REQAddLineBody");
//     BaseMethods.WaitForElement(driver,By.xpath("//span[contains(text(),'Item Code')]"), 3);
//     BaseMethods.ClickElement(page.Supplier_mag);   
//	 BaseMethods.SwitchWindow(driver,"Approved Suppliers", 3);	
////	 Thread.sleep(5000);
//     BaseMethods.KeyInElement(BaseMethods.GetElement(driver,By.id("xgvSuppliers_DXFREditorcol0_I")), "81006");     //xgvSuppliers_DXFREditorcol0_I
//	Thread.sleep(2000);	
//     WebElement datacell = BaseMethods.GetElement(driver,By.xpath("//td[contains(text(),'81006')]"));     
//     BaseMethods.DoubleClickElement(datacell);
//     BaseMethods.SwitchWindow(driver,"Req Creation", 2);
//     BaseMethods.SwitchFrame(driver,"REQAddLineBody");  

//     //SelectContract
//     WebElement co_img = BaseMethods.GetElement(driver,By.id("SelectContract"));   
//     BaseMethods.ClickElement(co_img);
//    TableUtilities.TableToXML(driver,"Raised By");
//     System.out.println("\t" + TableUtilities.GetTable(driver,"Created Date").getAttribute("class"));
     //BaseMethods.TakeScreenShot();
//     softAssert.assertAll();
		 //test
	}	   
	
	@Test
	 public void test2() throws InterruptedException { //throws InterruptedException {
			
		 LoginPage page = PageFactory.initElements(driver, LoginPage.class);	
	     BaseMethods.KeyInElement(page.UserNameText, "ss");            
	     BaseMethods.KeyInElement(page.PasswordText, "ss1");           
	     BaseMethods.ClickElement(page.LoginButton);
	     System.out.println("User name and password entered successfully");            
//	     BaseMethods.SwitchFrame("mainFrame");
//	     BaseMethods.SwitchFrame("topFrame");            
//	     BaseMethods.ClickElement(page.Create);           
//	     BaseMethods.SwitchFrame("mainFrame");
//	     BaseMethods.WaitForElement(By.id("ibAdd"), 10);
//	     BaseMethods.ClickElement(page.AddItem);
//	    
//		 BaseMethods.SwitchWindow("Req Creation", 2);	
//	     BaseMethods.SwitchFrame("REQAddLineBody");
//	     BaseMethods.ClickElement(page.ItemCode_mag);   
//		 BaseMethods.SwitchWindow("SelectionItem", 3);
//	     BaseMethods.KeyInElement(BaseMethods.FindElement( By.id("xgvItems_DXFREditorcol0_I")), "SVR2");
//	     Thread.sleep(2000);	
//	     WebElement caption = BaseMethods.FindElement(By.xpath("//td[contains(text(),'SVR2')]"));
//	     BaseMethods.DoubleClickElement(caption);// driver.FindElement(By.Id("xgvItems_DXDataRow0")));
//	     BaseMethods.SwitchWindow("Req Creation", 2);
//		
//	     BaseMethods.SwitchFrame("REQAddLineBody");
//	     BaseMethods.WaitForElement(By.xpath("//span[contains(text(),'Item Code')]"), 3);
//	     BaseMethods.ClickElement(page.Supplier_mag);   
//		 BaseMethods.SwitchWindow("Approved Suppliers", 3);	
//	     BaseMethods.KeyInElement(BaseMethods.FindElement(By.id("xgvSuppliers_DXFREditorcol0_I")), "81004");     
//		Thread.sleep(2000);	
//	     WebElement datacell = BaseMethods.FindElement(By.xpath("//td[contains(text(),'81004')]"));     
//	     BaseMethods.DoubleClickElement(datacell);
	    
			           
		}	   

}
