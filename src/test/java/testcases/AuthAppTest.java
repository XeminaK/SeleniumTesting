package testcases;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AuthAppTest {
	
	private static WebDriver driver;
	
	@BeforeClass
	public static void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterClass
	public static void tearDown() {
		driver.close();
		driver.quit();
	}
	
	@Test
	public void testAuthDemoTitle() {
		driver.get("http://localhost:8080");
		String title = driver.getTitle();
		assertEquals("Auth Demo", title);
	}
	
	@Test
	public void testH1Text() {
		driver.get("http://localhost:8080");
		String text = driver.findElement(By.xpath("//h1")).getText();
		assertEquals("Welcome", text);
	}
	
	@Test
	public void testLogInButton() {
		//add logout feature before you run this
		driver.get("http://localhost:8080");
		WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Login')]"));
		element.click();
		String text = driver.findElement(By.xpath("//h1")).getText();
		String URL = driver.getCurrentUrl();
		assertEquals("Login", text);
		assertEquals("http://localhost:8080/login", URL);
	}
	
	@Test
	public void testInvalidLogInCredentials() {
		driver.get("http://localhost:8080/login");
		WebElement element = driver.findElement(By.id("username"));
		element.sendKeys("jjohns");
		WebElement element2 = driver.findElement(By.id("password"));
		element2.sendKeys("freakyfast");
		WebElement elementForm = driver.findElement(By.tagName("button"));
		elementForm.click();
		WebElement element3 = driver.findElement(By.className("message"));
		String text = element3.getText();
		assertEquals("Incorrect username or password.", text);
	}
	
	@Test
	public void testValidLogInCredentials() {
		driver.get("http://localhost:8080/login");
		WebElement preLogin = driver.findElement(By.tagName("span"));
		String preLoginText = preLogin.getText();
		WebElement element = driver.findElement(By.id("username"));
		element.sendKeys("jjohns");
		WebElement element2 = driver.findElement(By.id("password"));
		element2.sendKeys("freaky fast");
		WebElement elementForm = driver.findElement(By.tagName("button"));
		elementForm.click();
		String text = driver.findElement(By.tagName("span")).getText();
		String h1Text = driver.findElement(By.tagName("h1")).getText();
		String URL = driver.getCurrentUrl();
		assertEquals("Welcome", h1Text);
		assertEquals("Welcome Jimmy Johns Logout", text);
		assertEquals("http://localhost:8080/", URL);
		assertEquals("Login Sign Up", preLoginText);
	}
	
	@Test
	public void testSecretsShowAfterLogIn() {
		driver.get("http://localhost:8080/login");
		WebElement element = driver.findElement(By.id("username"));
		element.sendKeys("jjohns");
		WebElement element2 = driver.findElement(By.id("password"));
		element2.sendKeys("freaky fast");
		WebElement elementForm = driver.findElement(By.tagName("button"));
		elementForm.click();
		WebElement SecretElement = driver.findElement(By.xpath("//body//p[3]"));
		String text = SecretElement.getText();
		assertEquals("Feel free to browse our secrets.", text);
	}
	
	@Test
	public void test() {
		int expected = 4;
		int actual = 2 + 2;
		assertEquals(expected, actual);
	}
}
