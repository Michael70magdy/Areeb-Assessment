package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "",
        features = {"src/main/resources/Features/register.feature"},
        glue = {"Scenario"},
        plugin = {"pretty","html:target/htmlreport.html"})
public class Runner extends AbstractTestNGCucumberTests {
}
