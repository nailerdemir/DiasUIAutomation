package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/Resources/Features",
        glue = {"StepDefinitions"}
)

public class TestRunner extends AbstractTestNGCucumberTests{
}
