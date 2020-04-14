package com.codechallenge.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber/test_report", "json:target/cucumber/test_report.json",
		"junit:target/cucumber/test_report.xml" }, features = "src/test/resources/cucumber", monochrome = true)
public class CodeChallengeTest {

}