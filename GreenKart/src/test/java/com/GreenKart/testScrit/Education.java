package com.GreenKart.testScrit;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Education {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		/* application url */
		driver.get("https://www.indiatoday.in/");
		
		/* click on menu */
		driver.findElement(By.xpath("//div[@class='container']/ul/li[@class='all-menu']")).click(); 
		
		/* click on education */
		driver.findElement(By.xpath("//ul[@id='newlist']/descendant::a[text()='Education']")).click();
		
		Thread.sleep(20);
		
//		WebElement ad = driver.findElement(By.xpath("//div[@class='block block-itg-ads odd']/div[text()='advertisement']"));
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		driver.switchTo().frame("block-itg-ads-ads-medium-rectangl-rhs1-300x250--2");
		WebElement ad = driver.findElement(By.xpath("(//div[text()='advertisement'])[2]"));
		if("advertisement".equalsIgnoreCase(ad.getText()))
				System.out.println("Dispalyed");
		else
			System.out.println(" not Dispalyed");
		
	}

}
