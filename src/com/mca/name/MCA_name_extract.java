package com.mca.name;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.txt.read.Txt_Read;
import com.txt.write.Txt_write;

public class MCA_name_extract
{
	public static void main(String[] args) throws IOException
	{
		String inFilename = "C:\\Users\\leelaram.j\\Downloads\\companyInput.txt";
		String opFileName = "C:\\Users\\leelaram.j\\workspace\\Company_Name_Updation\\Output\\insertedList1.txt";
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver,600);
        Txt_write.write2Txt(opFileName,"Input_Name","CIN","MCA_Name","Status");
        try
        {
            String[] lines = Txt_Read.txtRead(inFilename);
            for (String line : lines) 
            {
            	String[] arrOfStr = line.split(",");
            	String CIN = arrOfStr[1];
        		String name =arrOfStr[0];
            	driver.get("https://www.instafinancials.com/");
                driver.findElement(By.xpath("//*[@class='iz_block_button']")).click();
                driver.findElement(By.xpath("//div[@class='check-box']/span[3]/label/div/ins")).click();
                driver.findElement(By.id("txtCompanySearch")).sendKeys(CIN);
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@id='compSearchResults']/li")));
                driver.findElement(By.xpath("//ul[@id='compSearchResults']/li")).click();
                String status = driver.findElement(By.xpath("//table[@id='CompanyOverview']/tbody/tr[3]/td[2]")).getText();
                String mcaName = driver.findElement(By.xpath("//div[@id='AppCompany_compDescription']/section/span[1]")).getText();
                Txt_write.write2Txt(opFileName,name,CIN,mcaName,status);
            }
            
        }
        catch (ElementNotFoundException e)
        {
        	System.out.println("Element not found error.");
        }
        catch (IOException e)
        {
        	System.out.println("IO Exception");
        }
        
	}

}
