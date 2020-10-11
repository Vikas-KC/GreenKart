package com.GreenKart.testScrit;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TopDeals {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		
		driver.findElement(By.linkText("Top Deals")).click();;
		Set<String> wn = driver.getWindowHandles();
		Iterator<String> itr = wn.iterator();
		String pid = itr.next();
		String cid1 = itr.next();
		driver.switchTo().window(cid1);
		
		Select s = new Select(driver.findElement(By.id("page-menu")));
		s.selectByVisibleText("10");

		/* Veg/fruit name */
		driver.findElement(By.xpath("//span[text()='Veg/fruit name']")).click();
		
		for(WebElement tdata : driver.findElements(By.tagName("tr")))
		{
			System.out.println(tdata.getText());
		}
	}

}
