package NKTech.TESTRahul;

import java.time.Duration;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class downloadAndUploadTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String fruitName = "Apple";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("window.scrollBy(0, 500)");
		driver.findElement(By.id("downloadButton")).click();
		driver.findElement(By.cssSelector("[type='file']")).sendKeys("C:\\Users\\intense\\Downloads\\download.xlsx");
		WebElement toastMsg = driver.findElement(By.xpath("//div[contains(@class,'Toastify__toast-body')]/div[2]"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(toastMsg));
		Assert.assertEquals("Updated Excel Data Successfully.", toastMsg.getText());
		wait.until(ExpectedConditions.invisibilityOf(toastMsg));

		String priceColumn = driver.findElement(By.xpath("//div[text()='Price']")).getAttribute("data-column-id");
		String ActualPrice = driver.findElement(By.xpath("//div[text()='" + fruitName
				+ "']/parent::div/parent::div/div[@id='cell-" + priceColumn + "-undefined']")).getText();
		System.out.println(ActualPrice);
		Assert.assertEquals("345", ActualPrice);

	}

}
