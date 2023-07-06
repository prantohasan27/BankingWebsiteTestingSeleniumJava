package Assignment_02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.Select;

public class Pranto {
    public static void main(String[] args) throws IOException, InterruptedException {
        String excel_path = "D:\\Assignment_02\\src\\main\\java\\Sample_Test_Data.xlsx";
        FileInputStream inputstream = new FileInputStream(excel_path);
        XSSFWorkbook wb = new XSSFWorkbook(inputstream);
        XSSFSheet sheet = wb.getSheet("Sheet1");

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");


                               /* ************************ Bank Manager Login Feature********************************* */

        /* Click Bank Manager Login Button */
        driver.findElement(By.xpath("//button[@ng-click='manager()']")).click();
        Thread.sleep(1000);

        /* Click Add Customer Button */
        driver.findElement(By.xpath("//button[@ng-click='addCust()']")).click();
        Thread.sleep(1000);

        XSSFRow row = null;
        XSSFCell cell = null;
        String FirstName = null;
        String LastName = null;
        String PostCode = null;
        String Customer = null;
        String Currency = null;

        for (int i=1; i<=sheet.getLastRowNum();i++) {
            row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                if (j == 0) {
                    FirstName = cell.getStringCellValue();
                } else if (j == 1) {
                    LastName = cell.getStringCellValue();
                } else if (j == 2) {
                    PostCode = cell.getStringCellValue();
                }
                else if (j==3)
                {
                    Customer = cell.getStringCellValue();
                }
                else if( j==4)
                {
                    Currency = cell.getStringCellValue();
                }

            }
            /* Get the value of FirstName,LastName & PostCode */
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[@ng-model='fName']")).sendKeys(FirstName);
            driver.findElement(By.xpath("//input[@ng-model='lName']")).sendKeys(LastName);
            driver.findElement(By.xpath("//input[@ng-model='postCd']")).sendKeys(PostCode);
            Thread.sleep(1000);

            /* Click On Add Customer Button */
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            Thread.sleep(3000);

            /* Click "OK" button of popup */
            driver.switchTo( ).alert( ).accept();

            /* Click Open Account Button */
            Thread.sleep(1000);
            driver.findElement(By.xpath("//button[@ng-click='openAccount()']")).click();

            /* Select Customer Name From DropDown */
            WebElement dropdownCustomerName = driver.findElement(By.id("userSelect"));
            Select select1 = new Select(dropdownCustomerName);
            select1.selectByValue("6");

            /* Select Currency From DropDown */
            WebElement dropdownCurrency = driver.findElement(By.id("currency"));
            Select select2 = new Select(dropdownCurrency);
            select2.selectByValue("Dollar");

            /* Click On Process Button */
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            /* Click "OK" button of popup */
            Thread.sleep(3000);
            driver.switchTo( ).alert( ).accept();

            /* Click on Customers Button */
            driver.findElement(By.xpath("//button[@ng-click='showCust()']")).click();

            /*Search Customer */
            Thread.sleep(1500);
            driver.findElement(By.xpath("//input[@type='text']")).sendKeys(PostCode);
            //driver.findElement(By.xpath("//input[@type='text']")).sendKeys(FirstName);
            //driver.findElement(By.xpath("//input[@type='text']")).sendKeys(LastName);
            //driver.findElement(By.xpath("//input[@type='text']")).sendKeys("1016");
            Thread.sleep(4000);


                              /* **************************Customer Login Feature************************************/


            /* Return Home*/
            driver.findElement(By.xpath("//button[@ng-click='home()']")).click();
            Thread.sleep(1000);

            /* Customer Login*/
            driver.findElement(By.xpath("//button[@ng-click='customer()']")).click();

            /* Select Customer Name From DropDown*/

            WebElement dropdownCustomerNameLogin = driver.findElement(By.id("userSelect"));
            Select select3 = new Select(dropdownCustomerNameLogin);
            select3.selectByValue("6");

            /* Click Login Button*/
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            /* Verifying Deposit*/
            driver.findElement(By.xpath("//button[@ng-click='deposit()']")).click();
            driver.findElement(By.xpath("//input[@type='number']")).sendKeys("20");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            Thread.sleep(2000);

            /* Verifying Withdrawl */
            driver.findElement(By.xpath("//button[@ng-click='withdrawl()']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//input[@type='number']")).sendKeys("7");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[@type='submit']")).click();

           /* Verifying Transaction Failed */
            driver.findElement(By.xpath("//button[@ng-click='withdrawl()']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//input[@type='number']")).sendKeys("15");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            Thread.sleep(3000);

            /* Verifying Logout */
            driver.findElement(By.xpath("//button[@ng-click='byebye()']")).click();

        }

    }

}
