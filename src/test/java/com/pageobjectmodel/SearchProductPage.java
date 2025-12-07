package com.pageobjectmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.Base_Class;
import com.interfaceelements.SearchProductInterfaceElements;

public class SearchProductPage extends Base_Class implements SearchProductInterfaceElements{

	@FindBy(linkText = laptop_linkText)
	private WebElement laptop;
	
	@FindBy(xpath = model_xpath)
	private WebElement model;
	
	@FindBy(xpath = cart_xpath)
	private WebElement cart;
	
	public SearchProductPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void searchProduct()
	{
		clickElement(laptop);
		clickElement(model);
		explicitWait(By.xpath("//a[text()='Add to cart']"), 10, "visible");
		clickElement(cart);
		alertAction("getText");
		takeSnapWithDate();
		
	}

}
