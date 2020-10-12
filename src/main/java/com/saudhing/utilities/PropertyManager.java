package com.saudhing.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertyManager {
	
	private static PropertyManager instance;
    private static final Object lock = new Object();
    Properties prop = new Properties();
    private static String propertyFilePath = "/properties/Config.properties";
    private static String browser;
	private static String testsiteurl;
	private static String defaultUserName;
    private static String defaultPassword;
    
 
    //Create a Singleton instance. We need only one instance of Property Manager.
    public static PropertyManager getInstance () {
        if (instance == null) {
            synchronized (lock) {
                instance = new PropertyManager();
                instance.loadData();
            }
        }
        return instance;
    }
    
    //Get all configuration data and assign to related fields.
    private void loadData() {

    	InputStream inputStream = this.getClass().getResourceAsStream(propertyFilePath);
 
        //Read configuration.properties file
        try {
            //prop.load(new FileInputStream(propertyFilePath));
        	prop.load(inputStream);
            //prop.load(this.getClass().getClassLoader().getResourceAsStream("configuration.properties"));
        } catch (IOException e) {
            System.out.println("Configuration properties file cannot be found");
        }
 
        //Get properties from configuration.properties
        browser = prop.getProperty("browser");
        testsiteurl = prop.getProperty("testsiteurl");
        defaultUserName = prop.getProperty("defaultUserName");
        defaultPassword = prop.getProperty("defaultPassword");
    }
    
    //getters and setters to read properties file
    public static String getTestsiteurl() {
		return testsiteurl;
	}

	public static void setTestsiteurl(String testsiteurl) {
		PropertyManager.testsiteurl = testsiteurl;
	}

	public static String getDefaultUserName() {
		return defaultUserName;
	}

	public static void setDefaultUserName(String defaultUserName) {
		PropertyManager.defaultUserName = defaultUserName;
	}

	public static String getDefaultPassword() {
		return defaultPassword;
	}

	public static void setDefaultPassword(String defaultPassword) {
		PropertyManager.defaultPassword = defaultPassword;
	}
	
	public static String getBrowser() {
		return browser;
	}

	public static void setBrowser(String browser) {
		PropertyManager.browser = browser;
	}

}
