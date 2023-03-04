package com.jan23.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFile2 {
	public FileInputStream stream=null;
			public Properties propertiesFile=null;

	 public void loadFile(String filename) throws IOException 
	 {
		 propertiesFile=new Properties();
		 String propertyFilePath=null;
		 switch(filename)
		 {
		 
		 case "testDataProperties":
			 propertyFilePath = Constants.TEST_DATA_PROPERTIES;
			 System.out.println(filename);
			 System.out.println(propertyFilePath);
			 break;
			 
		 }
		 try {
			stream=new FileInputStream(propertyFilePath);
			propertiesFile.load(stream);
			System.out.println(stream);
		} catch (FileNotFoundException e) {
			
			System.out.println(propertyFilePath);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println(propertiesFile);
		
	 }
	 public String  getproperty(String key)
	 {
		 System.out.println(propertiesFile);
			String value=propertiesFile.getProperty(key);
			return value;
	 }
}
