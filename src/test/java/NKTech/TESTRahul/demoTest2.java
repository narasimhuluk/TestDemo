package NKTech.TESTRahul;

import static org.testng.Assert.assertTrue;

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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import dev.failsafe.internal.util.Durations;

public class demoTest2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String fileName = "C:\\Users\\intense\\Downloads\\download.xlsx";
		String columnName = "Price";
		String fruitName = "Papaya";
		String updateValue = "789";

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
		driver.findElement(By.cssSelector("#downloadButton")).click();
		int cell = getCellNumber(fileName, columnName);
		int row = getRowNumber(fileName, fruitName);
		Assert.assertTrue(getUpdateString(fileName, row, cell, updateValue));
		driver.findElement(By.cssSelector("#fileinput")).sendKeys(fileName);
		WebElement toastMsg = driver.findElement(By.cssSelector(".Toastify__toast-body div:nth-child(2)"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastMsg));
		Assert.assertEquals("Updated Excel Data Successfully.", toastMsg.getText());
		wait.until(ExpectedConditions.visibilityOf(toastMsg));
		String priceCol = driver.findElement(By.xpath("//div[text()='Price']")).getAttribute("data-column-id");
		String actualPrice = driver.findElement(By.xpath("//div[text()='" + fruitName
				+ "']/parent::div/parent::div/div[@id=\"cell-" + priceCol + "-undefined\"]")).getText();
		Assert.assertEquals(actualPrice, updateValue);

	}

	public static boolean getUpdateString(String fileName, int rows, int cols, String updateValue) throws IOException {
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Row row = sheet.getRow(rows-1);
		Cell col = row.getCell(cols-1);
		col.setCellValue(updateValue);
		FileOutputStream fos = new FileOutputStream(fileName);
		workbook.write(fos);
		workbook.close();
		fis.close();
		return true;
	}

	public static int getRowNumber(String fileName, String fruitName) throws IOException {
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Iterator<Row> rows = sheet.iterator();
		int k = 1;
		int rowNum = 0;
		while (rows.hasNext()) {
			Row row = rows.next();
			Iterator<Cell> cell = row.cellIterator();
			while (cell.hasNext()) {
				Cell ce = cell.next();
				if (ce.getCellType() == CellType.STRING && ce.getStringCellValue().equalsIgnoreCase(fruitName)) {
					rowNum = k;
				}
			}
			k++;
		}

		return rowNum;
	}

	public static int getCellNumber(String fileName, String columName) throws IOException {
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Iterator<Row> rows = sheet.iterator();
		Row firsrRow = rows.next();
		Iterator<Cell> cell = firsrRow.cellIterator();
		int k = 1;
		int column = 0;
		while (cell.hasNext()) {
			Cell ce = cell.next();
			if (ce.getStringCellValue().equalsIgnoreCase(columName)) {
				column = k;
			}
			k++;
		}
		return column;
	}

}
