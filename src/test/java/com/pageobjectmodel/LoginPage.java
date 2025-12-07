package com.pageobjectmodel;


import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.Base_Class;
import com.interfaceelements.LoginPageInterfaceElements;
import com.pageobjectmanager.PageObjectManager;

public class LoginPage extends Base_Class implements LoginPageInterfaceElements{
	
	@FindBy(linkText = Login_linkText)
	private WebElement login;

	@FindBy(id = username_id)
	private WebElement username;
	
	@FindBy(css = password_css)
	private WebElement password;
	
	@FindBy(xpath = signin_xpath)
	private WebElement Signin;
	
	@FindBy(id = nametitle_id)
	private WebElement title;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public void validlogin() throws IOException {
		clickElement(login);
		explicitWait(By.id("loginusername"), 10, "visible");
		passInput(username, PageObjectManager.getPageObjManager().getFileReader().getDataProperty("username"));
		passInput(password, PageObjectManager.getPageObjManager().getFileReader().getDataProperty("password"));
		clickElement(Signin);
		explicitWait(By.id("nameofuser"), 10, "visible");
		getText(title);
	//	takeSnapWithDate();
		
		
		
	}
	
	
	

}
