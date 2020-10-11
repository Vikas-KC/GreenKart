package com.GreenKart.testScrit;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.Select;

public class GreenKart {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		
		driver.findElement(By.xpath("//input[@placeholder='Search for Vegetables and Fruits']")).sendKeys("ca");;
		
		List<WebElement> pdName = driver.findElements(By.xpath("//h4[contains(text(),'Ca')]"));
//		Iterator<WebElement> el = pdName.iterator();
		Map<String, String> mp = new HashMap<String, String>();
		System.out.println(pdName.size());
		for(WebElement we : pdName)
		{
			String product = we.getText();
			System.out.println(product);
			if(!(product.contains("Cashews")))
			{
				driver.findElement(By.xpath("//h4[text()='"+product+"']/../div/following-sibling::div/button")).click();
				;
				mp.put(product, driver.findElement(By.xpath("//h4[text()='"+product+"']/../p")).getText());
				System.out.println(product);
			}
			else
				System.out.println();
		}
	
		/* clik on cart-icon */
		driver.findElement(By.xpath("//a[@class='cart-icon']/img")).click();
		
		/* click on PROCEED TO CHECKOUT */
		driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();
		
		/* verify the amount */
		Integer tot=0;
		Set<String> name = mp.keySet();
		for(String s : name)
		{
			String quantity = driver.findElement(By.xpath("//p[text()='"+s+"']/../following-sibling::td/p")).getText();
			String price = driver.findElement(By.xpath("(//p[text()='"+s+"']/../following-sibling::td/p[@class='amount'])[1]")).getText();
			String total = driver.findElement(By.xpath("(//p[text()='"+s+"']/../following-sibling::td/p[@class='amount'])[2]")).getText();
			System.out.println(s);
			System.out.println(quantity);
			System.out.println(price);
			Integer iQuantity = Integer.valueOf(quantity);
			Integer iPrice = Integer.valueOf(price);
			Integer iTotal = Integer.valueOf(total);
			if(iTotal==(iQuantity*iPrice))
			{
				System.out.println("Total is correct");
				tot+=iTotal;
				System.out.println(tot);
			}
			else
				System.out.println("Total is not correct");
		}
		System.out.println("Summation"+tot);
		/* Verify total Amount */
		String actTA = driver.findElement(By.xpath("//b[text()='Total Amount     : ']/following-sibling::span")).getText();
		Integer iactTA = Integer.valueOf(actTA);
		System.out.println("actual ammount"+iactTA);
		int exsumi = tot.intValue();
		int actsumi = iactTA.intValue();
		System.out.println("actual ammount"+actsumi);
		if(exsumi==(exsumi))
		{
			System.out.println("Total amount is correct");
		}
		else
			System.out.println("Total amount not is correct");
//		driver.findElement(By.xpath("//p[text()='Cauliflower - 1 Kg']/../following-sibling::td/p")).getText();
		
		/* click on Enter promo code */ 
		driver.findElement(By.xpath("//input[@placeholder='Enter promo code']")).sendKeys("rahulshettyacademy");
		Thread.sleep(10);
		
		/* click on apply button */
		driver.findElement(By.xpath("//button[text()='Apply']")).click();
		Thread.sleep(30);
		
		/*verifing the discount amount */
		String discAmt = driver.findElement(By.xpath("//b[text()='Discount          : ']/following-sibling::span[contains(text(),'%')]")).getText();
		
//		System.out.println("dis % "+discAmt);
//		char[] ar = discAmt.toCharArray();
//		String newDis ="";
//		for(int i=0;i<ar.length;i++)
//		{
//			if(ar[i]>='0' && ar[i]<='9')
//			{
//				newDis = newDis+ar[i];
//				System.out.println(newDis);
//			}
//		}
//		Integer idiscAmt = Integer.valueOf(newDis);
//		int actdsct = idiscAmt.intValue();
//		System.out.println(actdsct);
		
		/* Calculating expected discount */
		float expdis = ( ((float)actsumi) -( ((float)actsumi) * (float)10/(float)100) );
		System.out.println("expected discount "+expdis);
		
		Thread.sleep(25);
		/*verifing total amount after discount */
		String tAmt = driver.findElement(By.xpath("//span[@class='discountAmt']")).getText();
		System.out.println("Total After Discount "+tAmt);
		Float iTAmt = Float.valueOf(tAmt);
		float fTAmt = iTAmt.floatValue();
		System.out.println(fTAmt);
		if(fTAmt==expdis)
			System.out.println("Total discount amt is correct");
		else
			System.out.println("Total discount amt is not correct");
		/* click on Place order button */
		driver.findElement(By.xpath("//button[text()='Place Order']")).click();
		
		/* select country */
		Select ss = new Select(driver.findElement(By.xpath("//select[contains(@style,'width')]")));
		ss.selectByVisibleText("India");
		
		/* Click on terms and condition */
		driver.findElement(By.xpath("//input[@class='chkAgree']")).click();
		
		/* click on Proceed button */
		driver.findElement(By.xpath("//button[text()='Proceed']")).click();
		
		driver.close();
	}

}
