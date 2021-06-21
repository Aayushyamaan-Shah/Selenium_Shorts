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

// The class that actually does the job required in Hands On 1
public class HandsOnOne{

    // Create the WebDriver object
    WebDriver driver;
    // Create an array of string that will hold the last output values which can be used later without the page open
    String[] returnMessage;

    // Main Constructor
    public HandsOnOne(@Nullable ChromeOptions options){
        // If the Chrome Options are provided, create an object with the options else create a default object
        driver =(options != null)? new ChromeDriver(options):new ChromeDriver();

        // Set the driver widow to 1920x1080 size - can be changed to liking or passed as an argument
        driver.manage().window().setSize(new Dimension(1920,1080));

        //Initialize the string array with a size of two
        returnMessage = new String[2];
    }

    // If someone does not want to use the Chrome Options, they can call this constructor
    public HandsOnOne(){
        // Calling the main constructor with Chrome Options set ot null
        this(null);
    }

    // The implementation of first part of Hands On 1
    public void runQ1(){
        // Load the page using LoadPage.load (DriverUtils) and if it loads successfully, continue
        if(LoadPage.load(driver, "https://www.seleniumeasy.com/test/basic-first-form-demo.html","//*[@id=\"user-message\"]")){

            // The following LoadPage function closes the pop up
            // Wait for the popup element (2nd argument) to be visible and close it by clicking it
            // If the popup does not show up for 10 seconds (3rd argument), continue the work on page
            // 10 seconds is a fairly large timeout, feel free to reduce it to something like 6 (for slow internets)
            LoadPage.closePopup(driver, "//*[@id=\"at-cv-lightbox-close\"]", 10);

            try{
                // Open the file, at the given path, containing the message as a new Scanner object
                Scanner fileScanner = new Scanner(new File("E:\\Seminars\\TCS_Selenium\\SampleSelenium\\src\\main\\java\\HandsOnOneMessage.txt"));

                // Send the text in that file as input for the input field on the page
                driver.findElement(By.xpath("//input[@id=\"user-message\"]")).sendKeys(fileScanner.useDelimiter("\\Z").next());

                // Click the submit button
                driver.findElement(By.xpath("//*[@id=\"get-input\"]/button")).click();

                // Capture the screenshot of the page and save it in the current working directory
                ScreenShot.capture(driver, "handsOnOneQ1.png");

                // Update the returnMessage to contain the data of the display element
                returnMessage[0] = driver.findElement(By.xpath("//*[@id=\"display\"]")).getText();

                System.out.println("The value of the display span: "+returnMessage[0]); // Console log
            }

            // If the file mentioned in the Scanner object was not found, this exception will be thrown and caught here
            catch (FileNotFoundException e){
                // Printing appropriate message
                System.out.println("The file mentioned in the path was not found.");
            }

            // If any other exception is generated during the execution, it will be caught here and appropriate message will be displayed
            catch (Exception e){
                // Printing stacktrace to know what went wrong
                e.printStackTrace();

                // Printing appropriate message
                System.out.println("The page did not load properly.");
            }
        }

    }

    // The implementation of second part of Hands On 1
    public void runQ2(){
        // Load the page using LoadPage.load (DriverUtils) and if it loads successfully, continue
        if(LoadPage.load(driver, "https://www.seleniumeasy.com/test/basic-first-form-demo.html","//*[@id=\"user-message\"]")){

            // The following LoadPage function closes the pop up
            // Wait for the popup element (2nd argument) to be visible and close it by clicking it
            // If the popup does not show up for 10 seconds (3rd argument), continue the work on page
            // 10 seconds is a fairly large timeout, feel free to reduce it to something like 6 (for slow internets)
            LoadPage.closePopup(driver, "//*[@id=\"at-cv-lightbox-close\"]", 10);
            try{

                // Open the file, at the given path, containing the message as a new Scanner object
                Scanner fileScanner = new Scanner(new File("E:\\Seminars\\TCS_Selenium\\SampleSelenium\\src\\main\\java\\HandsOnOneNumbers.txt"));

                // Send the text from first line in that file as input for the input field 1 on the page
                driver.findElement(By.xpath("//*[@id=\"sum1\"]")).sendKeys(fileScanner.useDelimiter("\n").next());

                // Send the text from second line in that file as input for the input field 2 on the page
                driver.findElement(By.xpath("//*[@id=\"sum2\"]")).sendKeys(fileScanner.next());

                // Click the submit button
                driver.findElement(By.xpath("//*[@id=\"gettotal\"]/button")).click();

                // Capture the screenshot of the page and save it in the current working directory
                ScreenShot.capture(driver, "handsOnOneQ2.png");

                // Update the returnMessage to contain the data of the display element
                returnMessage[1] = driver.findElement(By.xpath("//*[@id=\"displayvalue\"]")).getText();
                System.out.println("The value of the display span: "+returnMessage[1]); // Console log

            }

            // If the file mentioned in the Scanner object was not found, this exception will be thrown and caught here
            catch (FileNotFoundException e){
                // Printing appropriate message
                System.out.println("The file mentioned in the path was not found.");
            }

            // If any other exception is generated during the execution, it will be caught here and appropriate message will be displayed
            catch (Exception e){
                // Printing stacktrace to know what went wrong
                e.printStackTrace();

                // Printing appropriate message
                System.out.println("The page did not load properly.");
            }
        }
    }

    // Getter method for the returnMessage object
    public String[] getMessages(){
        return returnMessage;
    }

    // The following method quites the driver closing any open pages
    public void stop(){driver.quit();}
}
