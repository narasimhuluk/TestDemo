package NKTech.TESTRahul;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class upLoadTestCase {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String fruitName = "Mango";
		String updatedValue = "703";
		String fileName = "C:/Users/intense/Downloads/download.xlsx";

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
		driver.findElement(By.cssSelector("#downloadButton")).click();
		int col = getColumnNumber(fileName, "Price");
		int row = getRowNumber(fileName, fruitName);
		Assert.assertTrue(getUpdateCell(fileName, row, col, updatedValue));
		WebElement upload = driver.findElement(By.cssSelector("#fileinput"));
		upload.sendKeys(fileName);
		WebElement toastMsg = driver.findElement(By.cssSelector(".Toastify__toast-body div:nth-child(2)"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastMsg));
		Assert.assertEquals("Updated Excel Data Successfully.", toastMsg.getText());
		wait.until(ExpectedConditions.invisibilityOf(toastMsg));
		@Nullable
		String price = driver.findElement(By.xpath("//div[text()='Price']")).getAttribute("data-column-id");
		String actualPrice = driver.findElement(By.xpath(
				"//div[text()='" + fruitName + "']/parent::div/parent::div/div[@id='cell-" + price + "-undefined']"))
				.getText();
		Assert.assertEquals(updatedValue, actualPrice);

	}

	public static int getColumnNumber(String fileName, String pricecol) throws IOException {

		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Iterator<Row> rows = sheet.iterator();
		Row firstrow = rows.next();
		Iterator<Cell> ce = firstrow.cellIterator();
		int k = 1;
		int column = 0;
		while (ce.hasNext()) {
			Cell c = ce.next();
			if (c.getStringCellValue().equalsIgnoreCase(pricecol)) {
				column = k;

			}
			k++;
		}
		return column;
	}

	public static int getRowNumber(String fileName, String data) throws IOException {
		System.out.println("daya is+"+data);
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Iterator<Row> rows = sheet.iterator();
		int k = 1;
		int rowIndex = -1;
		while (rows.hasNext()) {
			Row row = rows.next();
			Iterator<Cell> cell = row.cellIterator();
			while (cell.hasNext()) {
				Cell c = cell.next();
				if (c.getCellType() == CellType.STRING && c.getStringCellValue().equalsIgnoreCase(data)) {
					rowIndex = k;
				}
			}
			k++;
			System.out.println(k);
		}

		return rowIndex;
	}

	public static boolean getUpdateCell(String fileName, int row, int col, String updatedValue) throws IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\intense\\Downloads\\download.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Row rowField = sheet.getRow(row - 1);
		Cell cellField = rowField.getCell(col-1);
		cellField.setCellValue(updatedValue);
		FileOutputStream fos = new FileOutputStream(fileName);
		workbook.write(fos);
		workbook.close();
		fis.close();

		return true;

	}
}
