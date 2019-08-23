package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBrowser {
	
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080");
		
		String title = driver.getTitle();
		System.out.println(title.length());
		System.out.println(title);
		String text = driver.findElement(By.xpath("//h1")).getText();
		System.out.println(text);
		
		driver.close();
		driver.quit();
	}

}
