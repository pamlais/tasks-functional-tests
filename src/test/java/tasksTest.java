import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class tasksTest {

	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void devesalvartarefacomsucesso() {
		WebDriver driver = acessarAplicacao();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			driver.findElement(By.id("saveButton")).click();
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);	
		}finally {
			driver.quit();
		}
	}

	@Test
	public void naodevesalvartarefasemdescricao() {
		WebDriver driver = acessarAplicacao();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			driver.findElement(By.id("saveButton")).click();
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);	
		}finally {
			driver.quit();
		}
	}

	@Test
	public void naodevesalvartarefasemData() {
		WebDriver driver = acessarAplicacao();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			driver.findElement(By.id("saveButton")).click();
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);		
		}finally {
			driver.quit();
		}
	}

	@Test
	public void naodevesalvartarefacomDataPassada() {
		WebDriver driver = acessarAplicacao();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2009");
			driver.findElement(By.id("saveButton")).click();
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);		
		}	finally {
			driver.quit();
		}
	}
}
