package com.ecomm.qa.pages;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.text.html.parser.DocumentParser;

import org.apache.commons.exec.ExecuteStreamHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ecomm.qa.base.TestBase;
import com.sun.corba.se.spi.orbutil.fsm.Action;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.sun.xml.internal.txw2.Document;

import sun.awt.image.PixelConverter.Bgrx;

public class HomePage extends TestBase{

	@FindBy(id ="nav-logo")
	WebElement logoImage;
	
	
	//@FindBy(id = "nav-hamburger-menu")
	//WebElement lnkmenu;
	
	/* Searchbox Element */
	
	@FindBy( id = "searchDropdownBox")
	List<WebElement> dropdnSearch;
	
	@FindBy(id = "nav-search-dropdown-card")
	WebElement ddallSearch;
     
	@FindBy(id = "twotabsearchtextbox")
	WebElement txtsearchBar;
	
	@FindBy(css="input[type='Submit']")
	WebElement Btnsearch;	
	
	@FindBy(linkText = "Try Prime")
	WebElement lnktryprime;
	
	/* Change Delivery Location */

	@FindBy(xpath ="//a[@class='nav-a nav-a-2 a-popover-trigger a-declarative']//div[@id='glow-ingress-block']//span[@id='glow-ingress-line1']")
	WebElement lnkdeliver;
	
	//@FindBy(xpath ="//div[@class = 'nav-search-scope nav-sprite']")
	//WebElement lnkDeliver; 
	
	@FindBy(linkText = "Change")
	WebElement lnkchangeDeliverLoc;
	
	@FindBy(xpath ="//input[@id ='GLUXZipUpdateInput']")
	WebElement inputLoc;
	
	@FindBy(xpath ="//div[@class='a-column a-span4 a-span-last']")
    WebElement BtnApply;
	
	@FindBy(name = "glowDoneButton")
    WebElement BtnDone;

	/* Left Navigation Menu */
	 
	@FindBy(xpath="//a[@id = 'nav-hamburger-menu']")
	WebElement lnkleftnavmenu;
	
	@FindBy(xpath="//div[contains(text(),'All Beauty']")
	WebElement lnkleftnavsubMenu; 
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	// Verify Pagetitle
	public String ValidatePageTitle() {
		
		return driver.getTitle();
	}
	//Verify logo image
	public void checklogoimg() {

		// Boolean imgPresent =
		// (Boolean)((JavascriptExecutor)driver).executeScript("return
		// arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" &&
		// arguments[0].naturalWidth > 0", logoImage);
		if (logoImage.isDisplayed()) {
			System.out.print("Image is displayed");
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("arguments[0].style.border='3px solid red'", logoImage);
		} else {
			System.out.println("Image is not Displayed");
		}

	}
	// Get Page Title by JS
	public String getTitleByJS() {
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		String pageTitleByJS = js.executeScript("return document.title;").toString();
		return pageTitleByJS;
		
			
	}
	
	// Search option with ALL
	/*
	 * public void searchByAll(String alloption,String pname) { Actions action = new
	 * Actions(driver); action.moveToElement(dropdnSearch).build().perform(); Select
	 * ddsearch = new Select(dropdnSearch); ddsearch.selectByVisibleText(alloption);
	 * txtsearchBar.sendKeys(pname); Btnsearch.click(); // return new
	 * CategoryOptionPage(); }
	 */
	 
	// Search option with selected catogory
	public void ddoptionselection(String selected_option,String product_name) throws Exception
	{ 
		
		WebElement ddclick = driver.findElement(By.xpath("//div[@class='nav-search-scope nav-sprite']"));
		Actions action = new Actions(driver);
		action.moveToElement(ddclick).click();
		Thread.sleep(3000);
		for(WebElement ele :dropdnSearch)
		{
			if(ele.getText().contains(selected_option))
			{
				System.out.println(selected_option);
				System.out.println("element gettext:" +ele.getText());
				txtsearchBar.sendKeys(product_name);
				Btnsearch.click();
				
			}
		}
	}
	
	//Deliver to Location 
	
	public void changeDeliveryLoc(String LocationZip) throws InterruptedException {
		Actions action = new Actions(driver);
	    action.moveToElement(lnkdeliver);
	    JavascriptExecutor jsexecute = (JavascriptExecutor) driver;
	    jsexecute.executeScript("arguments[0].click();", lnkdeliver);
	        
		
		  String parentWindowHandler = driver.getWindowHandle(); // Store your 
		  String subWindowHandler = null; 
		  Set<String> handles = driver.getWindowHandles(); // get all window handles 
		  Iterator<String>iterator = handles.iterator();
		  while (iterator.hasNext())
		  { subWindowHandler = iterator.next(); }
		  driver.switchTo().window(subWindowHandler); // switch to
		 
		 
         Thread.sleep(3000);     
	     lnkchangeDeliverLoc.click();	
	     inputLoc.clear();
	     inputLoc.sendKeys(LocationZip);
	     BtnApply.click();
	     BtnDone.click(); 	
         driver.switchTo().window(parentWindowHandler); 
         Thread.sleep(3000);
         txtsearchBar.sendKeys("Babyfood");
         Btnsearch.click();
     //   driver.switchTo().activeElement();
	  // Sign in to see Address
	  //  WebElement ele;
	    //WebElement ele = driver.findElement(By.xpath("//a[@id='GLUXChangePostalCodeLink']"));
	  //  WebDriverWait wait = new WebDriverWait(driver, 30);
	  //  ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='a-button-inner a-declarative']//input[@type='submit']")));
	    // ele.click();
         
	}
	
	public void LeftNavMenu() throws InterruptedException {
		
		Actions action = new Actions(driver);
		action.moveToElement(lnkleftnavmenu).build().perform();

		lnkleftnavmenu.click();

		WebElement SelectOptionUL = driver.findElement(By.xpath("//div[@id = 'hmenu-content']//ul"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", SelectOptionUL);
		List<WebElement> optionlist = SelectOptionUL.findElements(By.tagName("li"));
		
		System.out.println(optionlist);
		for (WebElement li : optionlist) {
			if (li.getText().equals("Beauty & Health")) {
				action.moveToElement(li).build().perform();
				li.click();
			
	       //  WebElement SelectsubUl = driver.findElement(By.xpath("//ul[@class='hmenu hmenu-visible hmenu-translateX']"));
	        // List<WebElement> optionchildLi = SelectsubUl.findElements(By.tagName("li"));
			

				/*
				 * for(WebElement list : optionchildLi) { if
				 * (list.getText().equals("All Beauty")) {
				 * 
				 * lnkleftnavsubMenu.click();
				 * 
				 * //action.moveToElement(driver.findElement(By.
				 * xpath("//span[contain(text(),'All Beauty']"))).build().perform();
				 * 
				 * System.out.println(driver.getTitle()); txtsearchBar.sendKeys("Loreal");
				 * Btnsearch.click(); } }
				 */
				
			}

		}
	
	}    
}
