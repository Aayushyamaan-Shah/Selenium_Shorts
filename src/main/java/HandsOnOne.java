import DriverUtils.LoadPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import DriverUtils.ScreenShot;

public class HandsOnOne{
    WebDriver driver;
    String[] returnMessage;

    public HandsOnOne(@Nullable ChromeOptions options){
        driver =(options != null)? new ChromeDriver(options):new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920,1080));
        returnMessage = new String[2];
    }

    public HandsOnOne(){
        this(null);
    }

    public void runQ1(){
        if(LoadPage.load(driver, "https://www.seleniumeasy.com/test/basic-first-form-demo.html","//*[@id=\"user-message\"]")){
            LoadPage.closePopup(driver, "//*[@id=\"at-cv-lightbox-close\"]", 10);
            try{

                Scanner fileScanner = new Scanner(new File("E:\\Seminars\\TCS_Selenium\\SampleSelenium\\src\\main\\java\\HandsOnOneMessage.txt"));

                driver.findElement(By.xpath("//input[@id=\"user-message\"]")).sendKeys(fileScanner.useDelimiter("\\Z").next());

                driver.findElement(By.xpath("//*[@id=\"get-input\"]/button")).click();

                ScreenShot.capture(driver, "handsOnOneQ1.png");
                returnMessage[0] = driver.findElement(By.xpath("//*[@id=\"display\"]")).getText();
                System.out.println("The value of the display span: "+returnMessage[0]);
            }
            catch (FileNotFoundException e){
                System.out.println("The file mentioned in the path was not found.");
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("The page did not load properly.");
            }
        }

    }

    public void runQ2(){
        if(LoadPage.load(driver, "https://www.seleniumeasy.com/test/basic-first-form-demo.html","//*[@id=\"user-message\"]")){
            LoadPage.closePopup(driver, "//*[@id=\"at-cv-lightbox-close\"]", 10);
            try{

                Scanner fileScanner = new Scanner(new File("E:\\Seminars\\TCS_Selenium\\SampleSelenium\\src\\main\\java\\HandsOnOneNumbers.txt"));

                driver.findElement(By.xpath("//*[@id=\"sum1\"]")).sendKeys(fileScanner.useDelimiter("\n").next());
                driver.findElement(By.xpath("//*[@id=\"sum2\"]")).sendKeys(fileScanner.next());

                driver.findElement(By.xpath("//*[@id=\"gettotal\"]/button")).click();

                ScreenShot.capture(driver, "handsOnOneQ2.png");
                returnMessage[1] = driver.findElement(By.xpath("//*[@id=\"displayvalue\"]")).getText();
                System.out.println("The value of the display span: "+returnMessage[1]);

            }
            catch (FileNotFoundException e){
                System.out.println("The file mentioned in the path was not found.");
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("The page did not load properly.");
            }
        }
    }

    public String[] getMessages(){
        return returnMessage;
    }

    public void stop(){
        driver.quit();
    }
}
