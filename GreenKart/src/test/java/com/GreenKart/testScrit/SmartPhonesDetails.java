package com.GreenKart.testScrit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SmartPhonesDetails {

	static Workbook wb;
	static FileInputStream fis;
	static FileOutputStream fos;
	static int rnum = 1;
	static int rownum = 0;
	public static void main(String[] args) throws Exception  {
		

		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		/* application url */
		driver.get("https://www.reliancedigital.in/smart-phones/c/S101711?searchQuery=:relevance:availability:Exclude%20out%20of%20Stock&page=0");
	
		 List<WebElement> phones = driver.findElements(By.xpath("//p[@class='sp__name']"));
		 for(WebElement wb : phones)
		 {
			 String pName = wb.getText();
			 System.out.println(pName);
			 String[] name = pName.split(",");
			 writeToExcel(name);
			
			 
		 }
//		writeToExcel();
	}
	
	public static void writeToExcel(String[] name)
	{
		//create blank workbook
		  XSSFWorkbook workbook = new XSSFWorkbook(); 
		 
		  //Create a blank sheet
		  XSSFSheet sheet = workbook.createSheet("Details");
		  
		  ArrayList<String[]> data=new ArrayList<String[]>();
		  if(name.length==3)
			  data.add(new String[]{name[0]," NA",name[1],name[2]});
		  else if(name.length==2)
			  data.add(new String[]{name[0]," NA"," NA",name[2]});
		  else
			  data.add(new String[]{name[0],name[1],name[2],name[3]});
		 
		 
		  //Iterate over data and write to sheet
		 
		  for (String[] cnt : data)
		  {
		   Row row = sheet.createRow(rownum++);
		 
		   int cellnum = 0;
		   for (String obj : cnt)
		   {
		    Cell cell = row.createCell(cellnum++);
//		    if(obj instanceof String)
		     cell.setCellValue(obj);
//		    else if(obj instanceof Double)
//		     cell.setCellValue((Double)obj);
//		    else if(obj instanceof Integer)
//		     cell.setCellValue((Integer)obj);
		   }
		  }
		  try
		  {
		   //Write the workbook in file system
		   FileOutputStream out = new FileOutputStream(new File("./PhoneDetails.xlsx"));
		   workbook.write(out);
//		   out.close();
		   System.out.println("Details has been created successfully");
		  } 
		  catch (Exception e) 
		  {
		   e.printStackTrace();
		  }
		  finally {
		   try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  }
	}
	
//	public static void writeToExcel()
//	{
//		try {
//			fis = new FileInputStream("./PhoneDetails.xlsx");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		try {
//			wb = WorkbookFactory.create(fis);
//		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Sheet s = wb.getSheet("Details");
////		int rnum = s.getLastRowNum();
//		s.getRow(rnum);
//		int cnum = s.getRow(0).getLastCellNum();
//		
//			s.createRow(rnum).createCell(0).setCellValue("vikasa");
//			s.createRow(rnum).createCell(1).setCellValue("vikas");
////			s.createRow(rnum).createCell(2).setCellValue("vikas");
////			s.createRow(rnum).createCell(3).setCellValue("vikas");
//
//		
////		for(int i=0; i<1; i++)
////		{
////			for(int j=0; j<cnum; j++)
////			{
////				wb.getSheet("Details").createRow(i+1).createCell(j).setCellValue(nm);
////			}
////		}
//		
//		
//		try {
//			fos = new FileOutputStream("./PhoneDetails.xlsx");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			wb.write(fos);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			wb.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	
}
