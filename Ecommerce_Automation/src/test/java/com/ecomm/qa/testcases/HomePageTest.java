package com.ecomm.qa.testcases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ecomm.qa.base.TestBase;
import com.ecomm.qa.pages.CategoryOptionPage;
import com.ecomm.qa.pages.HomePage;

public class HomePageTest extends TestBase {

	HomePage homepg;
	CategoryOptionPage categorypage;
	public HomePageTest() {
		
		super();
	}
	@BeforeMethod
	public void setup() {
		
		initialization();
		homepg = new HomePage();
		categorypage = new CategoryOptionPage();
		
	}
	@Test(priority = 1)
	public void validateTitleTest() {
		String title = homepg.ValidatePageTitle();
		Assert.assertEquals(title, "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more");
		System.out.println("Passed");
		
	}
	@Test(priority = 2)
	public void checklogoimgTest() {
		homepg.checklogoimg();
	}
	
	
	@Test(priority = 3)
	public void validateTitleJSTest() {
		String pg_title = homepg.getTitleByJS();
		Assert.assertEquals(pg_title, "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more");
		System.out.println("Test passed");
		
	}

	/**/
	 
	@Test(priority = 4)
	public void dropdownSelectionTest() throws Exception {
	homepg.ddoptionselection("Computers", "HP");
	//	return categorypage;
	}
	
	@Test(priority = 5)
	public void ChangeDeliveryLocTest() throws InterruptedException {
		homepg.changeDeliveryLoc("90032");
	}
	@Test(priority = 6)
	public void SelectitemLeftMenuTest() throws InterruptedException {
		homepg.LeftNavMenu();
	    //Assert.assertEquals(driver.getTitle(), "Amazon.com: Beauty & Personal Care");
		//System.out.println("Test pass");
	} 
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}
