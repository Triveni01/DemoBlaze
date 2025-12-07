package com.runner;

import java.io.IOException;

import com.base.Base_Class;
import com.pageobjectmanager.PageObjectManager;

public class Test_Class extends Base_Class{

	
		public static void main(String [] args) throws IOException {
		
			launchBrowser(PageObjectManager.getPageObjManager().getFileReader().getDataProperty("browser"));
			launchUrl(PageObjectManager.getPageObjManager().getFileReader().getDataProperty("url"));
			PageObjectManager.getPageObjManager().getLoginpage().validlogin();
			PageObjectManager.getPageObjManager().getSearchProd().searchProduct();
			
		

		
	}

}
