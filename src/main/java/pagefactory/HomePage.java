package pagefactory;

import common.CommonMethods;
import common.TestDriver;
import junit.framework.Assert;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends TestDriver {

	
	static By task_inputBox = By.xpath("//input[@placeholder='What needs to be done?']");

	static By task = By.xpath("//label[normalize-space()='test1']");
	
	static By first_task = By.xpath("(//li[@class='todo']//label)[1]");
	
	static By delete_Btn = By.xpath("//button[@class='destroy']");
	
	static By clear_Completed = By.xpath("//button[normalize-space()='Clear completed']");
	
	static By active = By.xpath("//a[normalize-space()='Active']");
	
	static By all = By.xpath("//a[normalize-space()='All']");
	
	
	public void create_TodoList(String taskname) {
		driver.findElement(task_inputBox).sendKeys(taskname);
		driver.findElement(task_inputBox).sendKeys(Keys.ENTER);	
	}
	
	public void edit_Task(String taskname, String task_Update) throws InterruptedException {
		
		WebElement task = driver.findElement(By.xpath("//label[normalize-space()='"+taskname+"']"));
		
		Actions act = new Actions(driver);
		act.doubleClick(task).perform();
		Thread.sleep(5000);
		WebElement edit = driver.findElement(By.xpath("//li[@class='todo editing']//input[@type='text']"));
		edit.clear();
		edit.sendKeys(task_Update);
		
		
	}
	
	public void markAsComplete(String taskname) throws InterruptedException {
		Thread.sleep(2000);
		
		String task = "//label[normalize-space()='"+taskname+"']/preceding-sibling::input";
		
		driver.findElement(By.xpath(task)).click();
	}
	
    public void deleteTask() throws InterruptedException {
    	
    	Thread.sleep(2000);
		
    	Actions act = new Actions(driver);
    	act.moveToElement(driver.findElement(first_task));
    	driver.findElement(delete_Btn).click();
	}
	
    
    public void click_clear_Completed() {
    	driver.findElement(clear_Completed).click();
    }
    
    public boolean verify_clear_Completed(String taskname) {
    	
    	boolean verify = false;
    	
    	List<WebElement> tasks = driver.findElements(By.xpath("//li[@class='todo']//label"));
    	
    	for (int i=0; i<tasks.size();i++) {
    		if(!tasks.get(i).getText().equalsIgnoreCase(taskname))
    			verify = true;
    		
    	}
    		
    	return verify;
    }
    
    public void click_active() throws InterruptedException {
    	driver.findElement(active).click();
    	Thread.sleep(2000);
    }
    
    public void click_all() throws InterruptedException {
    	driver.findElement(all).click();
    	Thread.sleep(2000);
    }
    
    public boolean verify_All_Task_Created(String count) {
    	
    	boolean value = false;
    	int number = Integer.parseInt(count);
    	List<WebElement> tasks = driver.findElements(By.xpath("//li[@class='todo']//label"));
    	if(tasks.size()==number) {
    		value = true;
    	}
    	
    	return value;
    		
    	}
    	
    
	
	
	

}
