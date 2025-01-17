package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;
import utility.TestListener;

@Listeners(TestListener.class)
@CucumberOptions(
    features = "src/test/resources/Features",
    glue = {"stepDefinitions"},
    plugin = {
        "pretty",
        "json:target/cucumber-reports/cucumber.json",
        "testng:target/cucumber-reports/testng-report.xml",
        "html:target/cucumber-reports/cucumber-html-report.html",
        "rerun:target/failed_scenarios.txt"
    },
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
