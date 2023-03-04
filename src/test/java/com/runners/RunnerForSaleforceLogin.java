package com.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions
(
		features= {"src/test/resources/featurefile.feature"},
		glue= {"com.steps"},
		plugin= {"pretty","html:target/cucumber.html","json:target/cucumber.json"},
		monochrome=true,
		dryRun=false,
		tags="@rani"
		
		
		
		)
public class RunnerForSaleforceLogin extends AbstractTestNGCucumberTests
{

}
