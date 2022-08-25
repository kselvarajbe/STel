package stepdef;

import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import common.CommonMethods;
import common.TestDriver;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pagefactory.HomePage;

public class HomepageSteps {

	static Logger logger = Logger.getLogger(HomepageSteps.class.getName());

	private WebDriver driver;

	public HomepageSteps() {
		this.driver = TestDriver.driver;
	}

	HomePage home = PageFactory.initElements(driver, HomePage.class);

	@Given("^I am on Test page$")
	public void i_am_on_Test_page() throws Throwable {
		driver.get("https://todomvc.com/examples/vue/");
	}
	
	@When("^I create to do list$")
    public void i_create_to_do_list(DataTable testData) throws Throwable {
        
		Map<String, String> data = testData.asMap(String.class, String.class);
		home.create_TodoList(data.get("Task1"));
		home.create_TodoList(data.get("Task2"));
		home.create_TodoList(data.get("Task3"));
		home.create_TodoList(data.get("Task4"));
    }
	
	

    @And("^I select a task \"([^\"]*)\" and edit the task \"([^\"]*)\"$")
    public void i_select_a_task_something_and_edit_the_task_something(String tasknameedit, String tasknameupdate) throws Throwable {
     
    	home.edit_Task(tasknameedit, tasknameupdate);
    }

    @And("^I mark as complete a task \"([^\"]*)\"$")
    public void i_mark_as_complete_a_task_something(String taskname) throws Throwable {
    	home.markAsComplete(taskname);
        
    }

    @And("^I delte a task fro the list$")
    public void i_delte_a_task_fro_the_list() throws Throwable {
    	home.deleteTask();
        
    }

    @And("^I clear completed task$")
    public void i_clear_completed_task() throws Throwable {
        
    }
    
    @Then("^I verfiy that task \"([^\"]*)\" cleared successfully$")
    public void i_verfiy_that_task_cleared_successfully(String taskname) throws Throwable {
    	Assert.assertTrue(home.verify_clear_Completed(taskname));
    	System.out.println("Task Cleared successfully");
        
    }
    
    @And("^I click active button$")
    public void i_click_active_button() throws Throwable {
        home.click_active();
    }

    @And("^I click all button$")
    public void i_click_all_button() throws Throwable {
        home.click_all();
    }
    
    @When("^I create to do list \"([^\"]*)\" \"([^\"]*)\"$")
    public void i_create_to_do_list_something_something(String task1, String task2) throws Throwable {
    	home.create_TodoList(task1);
    	home.create_TodoList(task2);
        
    }

    @Then("^I verfiy all task \"([^\"]*)\" created successfully$")
    public void i_verfiy_all_task_created_successfully(String count) throws Throwable {
    	Assert.assertTrue(home.verify_All_Task_Created(count));
        
    }

	
	
}
