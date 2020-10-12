package com.saudhing.ExtentListeners;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.saudhing.utilities.DriverManager;

public class ExtentManager {

	static ExtentReports extent;
	static Date d = new Date();
	private static Platform platform;
	private static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
	private static String macPath = System.getProperty("user.dir") + "/TestReport";
	private static String windowsPath = System.getProperty("user.dir") + "\\TestReport";
	private static String linuxPath = System.getProperty("user.dir") + "/TestReport";
	private static String macReportFileLoc = macPath + "/" + fileName;
	private static String winReportFileLoc = windowsPath + "\\" + fileName;
	private static String linuxReportFileLoc = linuxPath + "/" + fileName;

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {

			platform = getCurrentPlatform();
			fileName = getReportFileLocation(platform);
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);

			htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
			htmlReporter.config().setChartVisibilityOnOpen(true);
			htmlReporter.config().setTheme(Theme.STANDARD);
			htmlReporter.config().setDocumentTitle(fileName);
			htmlReporter.config().setEncoding("utf-8");
			htmlReporter.config().setReportName(fileName);

			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			extent.setSystemInfo("Automation Tester", "Saurabh Dhingra");
			extent.setSystemInfo("Organization", "Sapient");
			extent.setSystemInfo("Build no", "SAUDHING-1234");
		}
		return extent;
	}

	// Get current platform
	public static Platform getCurrentPlatform() {
		if (platform == null) {
			String operSys = System.getProperty("os.name").toLowerCase();
			if (operSys.contains("win")) {
				platform = Platform.WINDOWS;
			} else if (operSys.contains("mac")) {
				platform = Platform.MAC;
			} else {
				platform = Platform.LINUX;
			}
		}
		return platform;
	}

	// Select the extent report file location based on platform
	private static String getReportFileLocation(Platform platform) {
		String reportFileLocation = null;
		switch (platform) {
		case MAC:
			reportFileLocation = macReportFileLoc;
			createReportPath(macPath);
			System.out.println("ExtentReport Path for MAC: " + macPath + "\n");
			break;
		case WINDOWS:
			reportFileLocation = winReportFileLoc;
			createReportPath(windowsPath);
			System.out.println("ExtentReport Path for WINDOWS: " + windowsPath + "\n");
			break;
		case LINUX:
			reportFileLocation = linuxReportFileLoc;
			createReportPath(linuxPath);
			System.out.println("ExtentReport Path for LINUX: " + linuxPath + "\n");
			break;
		default:
			System.out.println("ExtentReport path has not been set! There is a problem!\n");
			break;
		}
		return reportFileLocation;
	}

	// Create the report path if it does not exist
	private static void createReportPath(String path) {
		File testDirectory = new File(path);
		if (!testDirectory.exists()) {
			if (testDirectory.mkdir()) {
				System.out.println("Directory: " + path + " is created!");
			} else {
				System.out.println("Failed to create directory: " + path);
			}
		} else {
			System.out.println("Directory already exists: " + path);
		}
	}

	public static String screenshotPath;
	public static String screenshotName;
	static int i = 0;

	public static void captureScreenshot() {

		i = i + 1;

		File scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "/TestReport/" + screenshotName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
