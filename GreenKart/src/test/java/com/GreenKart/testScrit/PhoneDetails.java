package com.GreenKart.testScrit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
public class PhoneDetails {

	public static void main(String[] args) throws IOException, InvalidFormatException {
	       
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		/*Maximize window*/
		driver.manage().window().maximize();
		/*Wait condition*/
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		/*Navigate to the url*/
		driver.get("https://www.reliancedigital.in/smart-phones/c/S101711?searchQuery=:relevance:availability:Exclude%20out%20of%20Stock&page=0");
		
		/*WebElement mobile = driver.findElement(By.xpath("//div[@class='nav__title']"));
		Actions a = new Actions(driver);
		a.moveToElement(mobile);
		
		
		/* Find all the smart phone names */
		List<WebElement> phoneName = driver.findElements(By.xpath("//p[@class='sp__name']"));
		
		/* Phone price */
		List<WebElement> phonePrice = driver.findElements(By.xpath("//span[@class='sc-bxivhb cHwYJ']"));
		
		/* Actutal phone price */
		List<WebElement> actualPrice = driver.findElements(By.tagName("em"));
		
		/*discount percentage*/
		List<WebElement> discount = driver.findElements(By.xpath("//span[@class='sc-bwzfXH hMTXuH sc-bxivhb fTuWaG']"));
		
		int totalPhone = phoneName.size();
		System.out.println(totalPhone);
		
		FileInputStream fis = new FileInputStream("./PhoneDetails.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheetAt(0);
		sh.createRow(0);
		sh.getRow(0).createCell(0).setCellValue("PhoneName");
		sh.getRow(0).createCell(1).setCellValue("RAM");
		sh.getRow(0).createCell(2).setCellValue("Color");
		sh.getRow(0).createCell(3).setCellValue("Type");
		sh.getRow(0).createCell(4).setCellValue("Price");
		sh.getRow(0).createCell(5).setCellValue("ActualPrice");
		sh.getRow(0).createCell(6).setCellValue("Discount");
		for(int i=0; i<totalPhone; i++) 
		{
			sh.createRow(i+1);
			String pName = phoneName.get(i).getText();
			System.out.println(pName);
			String[] splitName = pName.split(",");
			String price = phonePrice.get(i).getText();
			 String act = actualPrice.get(i).getText();
			 String disc = discount.get(i).getText();
			 
			
			for(int j=0; j<splitName.length; j++)
			{
				
				if(splitName[j].contains(" Gold") || splitName[j].contains(" Black") || splitName[j].contains(" Space Grey") 
						|| splitName[j].contains(" White") || splitName[j].contains(" Blue")
						 || splitName[j].contains("RED")) 
					sh.getRow(i+1).createCell(2).setCellValue(splitName[j]);
				else if(splitName[j].equals(" Smartphone"))
					sh.getRow(i+1).createCell(3).setCellValue(splitName[j]);	
				else
					sh.getRow(i+1).createCell(j).setCellValue(splitName[j]);
				
				
				if(j==splitName.length-1)
				{
					sh.getRow(i+1).createCell(4).setCellValue(price);
					sh.getRow(i+1).createCell(5).setCellValue(act);
					sh.getRow(i+1).createCell(6).setCellValue(disc);
				}
				try {
					FileOutputStream fos = new FileOutputStream("./PhoneDetails.xlsx");
					 wb.write(fos);
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				}
			}
		}
		
	
	}
}
