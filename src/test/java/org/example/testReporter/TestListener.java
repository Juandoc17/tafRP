package org.example.testReporter;

import org.testng.*;

import com.aventstack.extentreports.*;


public class TestListener implements ITestListener {
	private ExtentReports extent = ExtentManager.createInstance("extent.html");
	private ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
				result.getMethod().getDescription());
		test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.get().fail(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().skip(result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
