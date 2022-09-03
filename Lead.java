package week6.day1;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Lead {
	public EdgeDriver driver;
	 @BeforeMethod
	  public void preCondition() {
		 WebDriverManager.edgedriver().setup();
		 driver = new EdgeDriver();
		driver.get("http://leaftaps.com/opentaps/control/main");
		driver.manage().window().maximize();
		WebElement username=driver.findElement(By.id("username"));
		WebElement password=driver.findElement(By.id("password"));
		WebElement login=driver.findElement(By.className("decorativeSubmit"));
		username.sendKeys("demosalesmanager");
		password.sendKeys("crmsfa");
		login.click();
	}
	 @Test
	 public void testLead() {
		 driver.findElement(By.linkText("CRM/SFA")).click();
			driver.findElement(By.linkText("Leads")).click();
			driver.findElement(By.linkText("Create Lead")).click();
			driver.findElement(By.id("createLeadForm_companyName")).sendKeys("Testleaf");
			driver.findElement(By.id("createLeadForm_firstName")).sendKeys("NaveenRaj");
			driver.findElement(By.id("createLeadForm_lastName")).sendKeys("Bala");
			driver.findElement(By.id("createLeadForm_firstNameLocal")).sendKeys("N");
			driver.findElement(By.id("createLeadForm_lastNameLocal")).sendKeys("B");
			driver.findElement(By.id("createLeadForm_departmentName")).sendKeys("Quality Dept");
			driver.findElement(By.id("createLeadForm_description")).sendKeys("Quality dept checks the quality of the product");
			driver.findElement(By.id("createLeadForm_primaryEmail")).sendKeys("naveenraj@gmail.com");
			WebElement state = driver.findElement(By.id("createLeadForm_generalStateProvinceGeoId"));
			Select drop=new Select(state);
			drop.selectByVisibleText("New York");
			driver.findElement(By.className("smallSubmit")).click();
			System.out.println(driver.getTitle()); 
	 }
	 @Test
	 public void testDuplicateLead() {
		 driver.findElement(By.linkText("CRM/SFA")).click();
			driver.findElement(By.linkText("Leads")).click();
			driver.findElement(By.linkText("Create Lead")).click();
			driver.findElement(By.id("createLeadForm_companyName")).sendKeys("Testleaf");
			driver.findElement(By.id("createLeadForm_firstName")).sendKeys("NaveenRaj");
			driver.findElement(By.id("createLeadForm_lastName")).sendKeys("Bala");
			driver.findElement(By.id("createLeadForm_firstNameLocal")).sendKeys("N");
			driver.findElement(By.id("createLeadForm_lastNameLocal")).sendKeys("B");
			driver.findElement(By.id("createLeadForm_departmentName")).sendKeys("Quality Dept");
			driver.findElement(By.id("createLeadForm_description")).sendKeys("Quality dept checks the quality of the product");
			driver.findElement(By.id("createLeadForm_primaryEmail")).sendKeys("naveenraj@gmail.com");
			WebElement state = driver.findElement(By.id("createLeadForm_generalStateProvinceGeoId"));
			Select drop=new Select(state);
			drop.selectByVisibleText("New York");
			driver.findElement(By.className("smallSubmit")).click();
			System.out.println(driver.getTitle());
			//click on duplicate lead
			driver.findElement(By.className("subMenuButton")).click();
			driver.findElement(By.id("createLeadForm_companyName")).clear();
			driver.findElement(By.id("createLeadForm_companyName")).sendKeys("infosys");
			driver.findElement(By.id("createLeadForm_firstName")).clear();
			driver.findElement(By.id("createLeadForm_firstName")).sendKeys("B.NAVEENRAJ");
			driver.findElement(By.className("smallSubmit")).click();
			System.out.println(driver.getTitle());
	 }
	 
	 @Test
	 public void testEditLead() {
		 driver.findElement(By.linkText("CRM/SFA")).click();
			driver.findElement(By.linkText("Leads")).click();
			driver.findElement(By.partialLinkText("Find Leads")).click();
			driver.findElement(By.xpath("//label[text()='Lead ID:']//following::input[2]")).sendKeys("NaveenRaj");
			String title = driver.getTitle();
			System.out.println(title);
	        driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
	        driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a[1]")).click();
	        driver.findElement(By.xpath("//a[text()='Edit']")).click();
	        driver.findElement(By.xpath("(//input[@name='companyName'])[2]")).clear();
	        driver.findElement(By.xpath("(//input[@name='companyName'])[2]")).sendKeys("newleaf");
	        driver.findElement(By.xpath("(//input[@name='submitButton'])[1]")).click();
	        String title1 = driver.getTitle();
			System.out.println(title1);
			String companyName = driver.findElement(By.xpath("//span[@id='viewLead_companyName_sp']")).getText();
			System.out.println("Changed Company Name ="+ companyName);
	 }
	 
	 @Test
	 public void testDeleteLead() throws InterruptedException {
		 driver.findElement(By.linkText("CRM/SFA")).click();
			driver.findElement(By.linkText("Leads")).click();
			driver.findElement(By.partialLinkText("Find Leads")).click();
			driver.findElement(By.xpath("//span[text()='Email']")).click();
			driver.findElement(By.xpath("//input[@name='emailAddress']")).sendKeys("naveenraj@gmail.com");
			driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
			Thread.sleep(1000);
			WebElement lead1 = driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[1]/a"));
			String lead1Id = lead1.getText();
			System.out.println("lead: "+lead1Id);
			lead1.click();
			driver.findElement(By.className("subMenuButtonDangerous")).click();
			Thread.sleep(100);
			driver.findElement(By.partialLinkText("Find Leads")).click();//		15	Enter captured lead ID
			driver.findElement(By.xpath("//label[text()='Lead ID:']/following::input")).sendKeys(lead1Id);//		16	Click find leads button
			driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
			Thread.sleep(1000);
			WebElement searchResult = driver.findElement(By.xpath("//div[text()='No records to display']"));
			String expectedMsg = searchResult.getText();
			String actualMsg = "No records to display";
			if(expectedMsg.equalsIgnoreCase(actualMsg)) {
				System.out.println("Lead Id: "+ lead1Id + " is deleted successfully" );				
			}else {
				System.out.println("Lead Id: "+ lead1Id + " is not deleted successfully");
			}
	 }
	 
	 @Test
	 public void testMergeContact() {
		 driver.findElement(By.linkText("CRM/SFA")).click();
			driver.findElement(By.linkText("Contacts")).click();
			driver.findElement(By.xpath("//a[@href='/crmsfa/control/mergeContactsForm']")).click();
			//driver.findElement(By.xpath("//*[@id='ext-gen604']")).click();
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/div[2]/div/form/table/tbody/tr[1]/td[2]/a/img"))).click().build().perform();
			String parent = driver.getWindowHandle();
			Set<String> windows = driver.getWindowHandles();
			for (String winow : windows) {
				driver.switchTo().window(winow);
				System.out.println(driver.getTitle());
				if(driver.getTitle().contains("Find Contacts")) {
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					Actions act1 = new Actions(driver);
					//act1.moveToElement(driver.findElement(By.xpath("//table[@class='x-grid3-row-table']/tbody/tr[1]/td[1]/div/a"))).click().build().perform();
					act1.moveToElement(driver.findElement(By.xpath("//div[1]/table/tbody/tr[1]/td[1]/div/a"))).click().build().perform();
				}

			}
			driver.switchTo().window(parent);
			Actions act2 = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/div[2]/div/form/table/tbody/tr[2]/td[2]/a/img"))).click().build().perform();
			windows = driver.getWindowHandles();
			for (String winow : windows) {
				driver.switchTo().window(winow);
				System.out.println(driver.getTitle());
				if(driver.getTitle().contains("Find Contacts")) {
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					Actions act1 = new Actions(driver);
					act1.moveToElement(driver.findElement(By.xpath("//div[3]/table/tbody/tr[1]/td[1]/div/a"))).click().build().perform();
				}

			}
			driver.switchTo().window(parent);
			driver.findElement(By.linkText("Merge")).click();
			driver.switchTo().alert().accept();
			System.out.println(driver.getTitle());
	 }
	 
	 @AfterMethod
		public void postCondition() {
			driver.close();

		}

	

}
