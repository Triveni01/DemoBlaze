package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;

public class FileReaderManager {
	
	private static FileInputStream fileInputStream;
	private static Properties properties;
	
	private static void setupProperty() throws IOException {
		File file = new File ("/Users/fi-triveni/eclipse-workspace/org.apache.maven/src/main/resources/TestData.properties");
		try {
			fileInputStream = new FileInputStream(file);
			properties = new Properties();
			properties.load(fileInputStream);
		}catch(FileNotFoundException e) {
			Assert.fail("Error: Occurred during loading");
		}catch(Exception e) {
			Assert.fail("Error: Occurred during file reading");
		}
	}
	
	
	public String getDataProperty(String dataValue) throws IOException {
		setupProperty();
		String property =properties.getProperty(dataValue);
		return property;
		
	}
	
	

}
