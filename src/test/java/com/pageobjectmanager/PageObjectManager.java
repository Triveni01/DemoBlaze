package com.pageobjectmanager;

import com.pageobjectmodel.LoginPage;
import com.pageobjectmodel.SearchProductPage;
import com.utility.FileReaderManager;

public class PageObjectManager {

	private FileReaderManager fileReader;
	private LoginPage loginpage;
	private static PageObjectManager pageObjManager;
	private static SearchProductPage searchProd;

	public static SearchProductPage getSearchProd() {
		if(searchProd ==null)
			searchProd = new SearchProductPage();
		return searchProd;
	}

	public FileReaderManager getFileReader() {
		if (fileReader == null) {
			fileReader = new FileReaderManager();
		}
		return fileReader;
	}

	public LoginPage getLoginpage() {
		if (loginpage == null) {
			loginpage = new LoginPage();
		}
		return loginpage;

	}

	public static PageObjectManager getPageObjManager() {
		if (pageObjManager == null) {
			pageObjManager = new PageObjectManager();
		}
		return pageObjManager;

	}

}
