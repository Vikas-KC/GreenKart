package com.GreenKart.testScrit;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TopStories {
	
public static void main(String[] args) throws InterruptedException, AWTException {
		
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		driver.get("https://www.indiatoday.in/");
//		Thread.sleep(30);
//		driver.switchTo().frame("twisstiframe");
//		driver.findElement(By.xpath("//div[@class='draggablearea']/parent::div/button")).click();
		
		Thread.sleep(30);
		driver.findElement(By.cssSelector("div#twister>button.crosscloseif")).click();
		
		Thread.sleep(20);
		/* top stories */
		WebElement story = driver.findElement(By.xpath("(//span[text()='Top Stories']/parent::div/descendant::a)[1]"));
		String title = story.getText();
		System.out.println(title);
		
		/* click on the 1st story */
		story.click();
		
		/* verify the title */
		String hTitle = driver.findElement(By.xpath("//h1[@itemprop='headline']")).getText();
		System.out.println(hTitle);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", driver.findElement(By.xpath("//h1[@itemprop='headline']")));
		
//		Robot r = new Robot();
//		
//		r.keyPress(KeyEvent.VK_SHIFT);
//		r.delay(100);
//		r.keyRelease(KeyEvent.VK_SHIFT);
//		System.out.println("done");
		
		if(title.equals(hTitle))
			System.out.println("title is same");
		else
			System.out.println("title is not same");
}

}
