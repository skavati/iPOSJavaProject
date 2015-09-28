package utilities;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Base.BaseMethods;

public class TableUtil {
	private static DocumentBuilderFactory factory;
	private static DocumentBuilder builder;
	private static Document doc;
    public static void TableToXML(WebDriver driver,String KeyColumn)
    {
        //....... Exmaple 1 ............... 

        //if (driver.FindElement(By.XPath("//table[@id='accounts']/thead/tr/th")).Displayed)
        //{
        //    IWebElement webElementHead = driver.FindElement(By.XPath("//table[@id='accounts']/thead/tr"));
        //    IList<IWebElement> ElementCollectionHead = webElementHead.FindElements(By.XPath("//table[@id='accounts']/thead/tr/th"));
        //    foreach (IWebElement item in ElementCollectionHead)
        //    {
        //        Console.WriteLine(item.Text);
        //    }
        //}
        //if (driver.FindElement(By.XPath("//table[@id='accounts']/tbody/tr")).Displayed)
        //{
        //    IWebElement webElementBody = driver.FindElement(By.XPath("//table[@id='accounts']/tbody/tr"));
        //    IList<IWebElement> ElementCollectionBody = webElementBody.FindElements(By.XPath("//table[@id='accounts']/tbody/tr"));
        //    foreach (IWebElement item in ElementCollectionBody)
        //    {
        //        string[] arr = new string[4];
        //        arr = item.Text.Split(' ');
        //        for (int i = 0; i < arr.Length; i++)
        //        {
        //            Console.WriteLine(arr[i]);
        //        }
        //    }
        //}
        //Console.ReadLine();



        //...................... Exmaple 2 ............... .......


        //   IWebElement table = _driverWithJs.FindElement(By.XPath("//div[@id='main']/table"));


        //ReadOnlyCollection<IWebElement> allRows = table.FindElements(By.TagName("tr"));


        //    for (int z = 0; z < allRows.Count; z++)
        //                    {
        //                        ReadOnlyCollection<IWebElement> cells = allRows[z].FindElements(By.TagName("td"));


        //       for (int y = 0; y < cells.Count; y++)
        //                            {
        //                                var value = allRows[z].FindElements(By.TagName("td"))[y].Text;


        //           if (value.Equals(header))
        //                                    {
        //                                        Assert.AreEqual(expected, allRows[z].FindElements(By.TagName("td"))[y + 1].Text);
        //                                   }
        //                            }
        //                   }


        //      ................. Exmaple 3 ......................... 
    	 Date timeStamp = new Date();
 	    try{
    	 factory = DocumentBuilderFactory.newInstance();
 	    builder = factory.newDocumentBuilder();
 	    doc = builder.newDocument();
 	    //root element
 	    Element xmlfile=doc.createElement("xmlfile");
 	   xmlfile.setAttribute("time", timeStamp.toString());
 	    doc.appendChild(xmlfile);
 	    //XML Storage Element
 	    Element XMLStorage=doc.createElement("XMLStorage");
 	    xmlfile.appendChild(XMLStorage );	 
        WebElement table = GetTable(driver,KeyColumn);
        List<WebElement> allRows = table.findElements(By.tagName("tr"));
        int i=0;
     
        for (WebElement row: allRows) {
        	if (! row.isDisplayed()) continue;
        	i += 1;        	
        	Element Row=doc.createElement("Row"+i);
        	XMLStorage.appendChild(Row);            
            List<WebElement> cells = row.findElements(By.tagName("td"));
             
            int j=0;
         for (WebElement cell:cells) {
        	 if (! cell.isDisplayed()) continue;     
        	 j += 1;
            if ( cell.getText().isEmpty() || cell.getText().contentEquals("  ")) continue;           
           	 Element Col=doc.createElement("Col"+j);
           	  Row.appendChild(Col); 
           	  Col.appendChild(doc.createTextNode(cell.getText()));
            }
        }
        
        // write the content into xml file
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	         transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	          transformer.setOutputProperty(OutputKeys.METHOD,"xml");
	        DOMSource source = new DOMSource(doc);
	        StreamResult result = new StreamResult(new File("C:\\TEMP\\TESTJAVA.xml"));
	     
	        // Output to console for testing
	        // StreamResult result = new StreamResult(System.out);
	     
	        transformer.transform(source, result);
	     
	        System.out.println("File saved!");
	     
 	   } catch (ParserConfigurationException pce) {
	        pce.printStackTrace();
	        } catch (TransformerException tfe) {
	        tfe.printStackTrace();
	        }
	      }
 

   // To get table instancce 
    //arguments : driver, KeyColumn ( pick any cell value on the screen)
    public static WebElement GetTable(WebDriver driver, String KeyColumn)    {
        List<WebElement> allTables =  driver.findElements(By.tagName("table"));//BaseMethods.GetElements(driver,By.tagName("table"),10);
        WebElement tbl = null;
        ExitLoops:
        for (WebElement Table: allTables) {
            List<WebElement> cells = Table.findElements(By.tagName("td"));

            for (WebElement cell: cells)  {            	
                if (cell.getText().equalsIgnoreCase(KeyColumn))    {
                    tbl = Table;
                    break ExitLoops;       // similar to goto label                
                   
                }
            }
          
        }
        
        return tbl;

    }


}

