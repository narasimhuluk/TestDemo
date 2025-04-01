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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DemoTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String fileName = "C:\\Users\\intense\\Downloads\\download.xlsx";
		String fruitName = "Kivi";
		String updatedValue = "544";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
		driver.findElement(By.cssSelector("#downloadButton")).click();
		int col = getColumnNumber(fileName, "Price");
		int row = getRowNumber(fileName, fruitName);
		System.out.println("col is"+col);
		System.out.println("row is"+row);
		Assert.assertTrue(updateCellValue(fileName, row, col, updatedValue));
		driver.findElement(By.cssSelector("#fileinput")).sendKeys(fileName);
		WebElement toastMsg = driver.findElement(By.cssSelector(".Toastify__toast-body div:nth-child(2)"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastMsg));
		Assert.assertEquals("Updated Excel Data Successfully.", toastMsg.getText());
		wait.until(ExpectedConditions.invisibilityOf(toastMsg));
		String pricelColumn = driver.findElement(By.xpath("//div[text()='Price']")).getAttribute("data-column-id");
		String actualPrice = driver.findElement(By.xpath("//div[text()='" + fruitName
				+ "']/parent::div/parent::div/div[@id='cell-" + pricelColumn + "-undefined']")).getText();
		Assert.assertEquals(updatedValue, actualPrice);
	}

	public static int getColumnNumber(String fileName, String colName) throws IOException {
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Iterator<Row> rows = sheet.iterator();
		Row firstRow = rows.next();
		Iterator<Cell> cell = firstRow.cellIterator();
		int k = 1;
		int column = 0;
		while (cell.hasNext()) {
			Cell col = cell.next();
			if (col.getStringCellValue().equalsIgnoreCase(colName)) {
				column = k;
			}
			k++;
		}
		return column;
	}

	public static boolean updateCellValue(String fileName, int row, int col, String updatedValue) throws IOException {
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheets = workbook.getSheet("Sheet1");
		Row rowField = sheets.getRow(row-1);
		Cell colum = rowField.getCell(col-1);
		colum.setCellValue(updatedValue);
		FileOutputStream fos = new FileOutputStream(fileName);
		workbook.write(fos);
		workbook.close();
		fos.close();
		return true;
	}

	public static int getRowNumber(String fileName, String fruitName) throws IOException {
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		Iterator<Row> rows = sheet.iterator();
		int k = 1;
		int rowNum = -2;
		while (rows.hasNext()) {
			Row row = rows.next();
			Iterator<Cell> cell = row.cellIterator();
			while (cell.hasNext()) {
				Cell c = cell.next();
				if (c.getCellType() == CellType.STRING && c.getStringCellValue().equalsIgnoreCase(fruitName)) {
					rowNum = k;
				}
			}
			k++;
		}
		return rowNum;
	}

}
