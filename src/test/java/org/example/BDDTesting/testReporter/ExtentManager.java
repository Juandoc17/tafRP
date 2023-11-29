package org.example.BDDTesting.testReporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class ExtentManager {
	private static ExtentReports extent;

	public static ExtentReports createInstance(String fileName) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		return extent;
	}
}