package com.saudhing.utilities;

public class DriverFactory {
	
	private static String chromeDriverExePath;
	private static String geckoDriverExePath;
	private static String ieDriverExePath;
	private static String configPropertyFile;
	private static String gridPath;
	private static boolean isRemote;
	private static String testsiteurl;

	public static String getTestsiteurl() {
		return testsiteurl;
	}

	public static void setTestsiteurl(String testsiteurl) {
		DriverFactory.testsiteurl = testsiteurl;
	}

	public static String getChromeDriverExePath() {
		return chromeDriverExePath;
	}

	public static void setChromeDriverExePath(String chromeDriverExePath) {
		DriverFactory.chromeDriverExePath = chromeDriverExePath;
	}

	public static String getGeckoDriverExePath() {
		return geckoDriverExePath;
	}

	public static void setGeckoDriverExePath(String geckoDriverExePath) {
		DriverFactory.geckoDriverExePath = geckoDriverExePath;
	}

	public static String getIeDriverExePath() {
		return ieDriverExePath;
	}

	public static void setIeDriverExePath(String ieDriverExePath) {
		DriverFactory.ieDriverExePath = ieDriverExePath;
	}

	public static String getConfigPropertyFile() {
		return configPropertyFile;
	}

	public static void setConfigPropertyFile(String configPropertyFile) {
		DriverFactory.configPropertyFile = configPropertyFile;
	}

	public static String getGridPath() {
		return gridPath;
	}

	public static void setGridPath(String gridPath) {
		DriverFactory.gridPath = gridPath;
	}

	public static boolean isRemote() {
		return isRemote;
	}

	public static void setRemote(boolean isRemote) {
		DriverFactory.isRemote = isRemote;
	}


}
