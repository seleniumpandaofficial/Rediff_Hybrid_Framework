package com.rediff.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class MyExtentReporter {
	
	public static ExtentReports generateExtentReport() throws Exception {
		
		//STEP 1 - create object of ExtenetReports
		ExtentReports extentReport = new ExtentReports();
		
		//STEP 2 - create the folder in test-output and pass the extentReport as an html file
		File extentReportFile = new File(System.getProperty("user.dir") + "\\test-ouput\\ExtentReports\\extentReport.html");
		
		//STEP 3 - create the Object of ExtentSparkReporter
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile); //sparkReporter will help us set configurations
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("REDIFF TEST RESULTS");
		sparkReporter.config().setDocumentTitle("RediffAutomationTest");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		extentReport.attachReporter(sparkReporter);
		
		Properties configProp = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\com\\rediff\\qa\\config\\config.properties");
		configProp.load(ip);
		
		extentReport.setSystemInfo("Application url", configProp.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", configProp.getProperty("browserName"));
		extentReport.setSystemInfo("Username", configProp.getProperty("validUsername"));
		extentReport.setSystemInfo("Password", configProp.getProperty("validPassword"));
		extentReport.setSystemInfo("Operating System", System.getProperty("os.version"));
		extentReport.setSystemInfo("Tester Name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentReport;
		
	}

}
