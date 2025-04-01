package NKTech.AutoIT;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class fileUpload {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		String downloadPath = System.getProperty("user.dir");
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("Profile.default_content_settings.popup", 0);
		chromePrefs.put("download.default_directory", downloadPath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://www.ilovepdf.com/pdf_to_word");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#pickfiles")).click();
		Thread.sleep(4000);
		Runtime.getRuntime().exec("C:\\Users\\intense\\Desktop\\Narasimha\\Automation\\fileupload\\fileupload.exe");
		WebElement converter = driver.findElement(By.cssSelector("#processTask"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(converter));
		converter.click();
		WebElement download = driver.findElement(By.cssSelector("#download"));
		wait.until(ExpectedConditions.visibilityOf(download));
		download.click();
		Thread.sleep(5000);
		File f = new File(downloadPath + "/PR.pdf.docx");
		if (f.exists()) {
			System.out.println("file excited");
			Assert.assertTrue(f.exists());
			f.delete();
			System.out.println("file delete");
			Assert.assertFalse(f.exists());
			System.out.println("file deleted");
		}
		driver.close();
	}

}
