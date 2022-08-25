package runners;

import org.testng.annotations.Test;



import cucumber.api.CucumberOptions;
//import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;


//@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features/todolist.feature"
			                },
                   plugin = { "pretty", "html:target/cucumber", "json:target/cucumber-report.json" }, 
                     glue = { "stepdef" }, 
                     dryRun = false, 
                     monochrome = true, 
                     
                     strict = true
                    //tags = {"@Sanity" }
                 )
@Test                    		 
public class SanityRunner extends AbstractTestNGCucumberTests{
	
}

