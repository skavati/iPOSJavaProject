package pageObjects;




import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;



public class LoginPage {
	final WebDriver driver;
//	 public PageObjectModel(WebDriver driver)
//     {  
//
//         PageFactory.initElements(driver,PageObjectModel.class);
//     }

	 @FindBy(how = How.ID, using = "usernameText")	 
     public WebElement UserNameText;

	 @FindBy(how = How.ID, using = "passwordText")     
     public WebElement PasswordText;
     
     //loginButton
     @FindBy(how=How.ID, using=  "loginButton")
     public WebElement LoginButton ;
     
     @FindBy(how=How.XPATH, using= "//span[contains(text(),'Create')]")
     public WebElement Create ;

     @FindBy(how=How.ID, using= "ibAdd")
     public WebElement AddItem ;

     //imgGeneralItemCode
     @FindBy(how=How.ID, using = "imgGeneralItemCode")
     public WebElement ItemCode_mag ;

     //SelectSupplier
     @FindBy(how=How.ID, using = "SelectSupplier")
     public WebElement Supplier_mag ;




public LoginPage(WebDriver driver)
{  

	this.driver = driver;
}

}

