package org.example.BDDTesting.testRunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/java/org/example/features" },
		           glue = { "stepsDefinitions" /*, "hooks"*/ },
				     plugin = { "pretty", "html:target/SystemTestReports/html",
							  					  "json:target/SystemTestReports/json/report.json", "junit:target/SystemTestReports/junit/report.xml" })
public class LoginFunctionalitiesTest {
}
