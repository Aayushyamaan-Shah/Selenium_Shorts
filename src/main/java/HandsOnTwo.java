import DriverUtils.LoadPage;
import DriverUtils.ScreenShot;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// The class that actually does the job required in Hands On 1
public class HandsOnTwo {

    // Create the WebDriver object
    WebDriver driver;

    // Main Constructor
    public HandsOnTwo(@Nullable ChromeOptions options){
        // If the Chrome Options are provided, create an object with the options else create a default object
        driver =(options != null)? new ChromeDriver(options):new ChromeDriver();

        // Set the driver widow to 1920x1080 size - can be changed to liking or passed as an argument
        driver.manage().window().setSize(new Dimension(1920,1080));
    }

    // If someone does not want to use the Chrome Options, they can call this constructor
    public HandsOnTwo(){
        // Calling the main constructor with Chrome Options set ot null
        this(null);
    }

    // The implementation of Hands On 2
    public void run(){
        // Load the page using LoadPage.load (DriverUtils) and if it loads successfully, continue
        if(LoadPage.load(driver, "https://www.seleniumeasy.com/test/input-form-demo.html","/html/body/div[2]/div/div[1]/div/div[1]")){

            // The following LoadPage function closes the pop up
            // Wait for the popup element (2nd argument) to be visible and close it by clicking it
            // If the popup does not show up for 10 seconds (3rd argument), continue the work on page
            // 10 seconds is a fairly large timeout, feel free to reduce it to something like 6 (for slow internets)
            LoadPage.closePopup(driver, "//*[@id=\"at-cv-lightbox-close\"]", 10);

            try{
                // Open the file, at the given path, containing the message as a new Scanner object
                Scanner fileScanner = new Scanner(new File("E:\\Seminars\\TCS_Selenium\\SampleSelenium\\src\\main\\java\\HandsOnTwoData.txt"));

                // Convert the JSON data to usable JSON object
                JSONArray persons = (JSONArray) (new JSONObject(fileScanner.useDelimiter("\\Z").next())).get("persons");
                int personID = 0;

                // Setting normal text data
                // Send the text in the JSON Object as input for the first name field on the page
                driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[1]/div/div/input")).sendKeys(persons.getJSONObject(personID).get("first name").toString());
                // Send the text in the JSON Object as input for the last name field on the page
                driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[2]/div/div/input")).sendKeys(persons.getJSONObject(personID).get("last name").toString());
                // Send the text in the JSON Object as input for the email field on the page
                driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[3]/div/div/input")).sendKeys(persons.getJSONObject(personID).get("email").toString());
                // Send the text in the JSON Object as input for the phone field on the page
                driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[4]/div/div/input")).sendKeys(persons.getJSONObject(personID).get("phone").toString());
                // Send the text in the JSON Object as input for the address field on the page
                driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[5]/div/div/input")).sendKeys(persons.getJSONObject(personID).get("address").toString());
                // Send the text in the JSON Object as input for the city field on the page
                driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[6]/div/div/input")).sendKeys(persons.getJSONObject(personID).get("city").toString());

                // Select the dropdown element as an Select Object
                Select select = new Select(driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[7]/div/div/select")));
                // Set the objects value to the state that corresponds the JSON Object
                select.selectByVisibleText(persons.getJSONObject(personID).get("state").toString());

                // Send the text in the JSON Object as input for the zip-code field on the page
                driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[8]/div/div/input")).sendKeys(persons.getJSONObject(personID).get("zip code").toString());
                // Send the text in the JSON Object as input for the website field on the page
                driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[9]/div/div/input")).sendKeys(persons.getJSONObject(personID).get("website").toString());

                // Selecting the radio button
                // Check the value in the JSON Object
                if(persons.getJSONObject(personID).get("hosting").toString().compareTo("no") == 0){
                    // If it is no, click the no radio button in the form
                    driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[10]/div/div[2]/label/input")).click();
                }else{
                    // If it is yes, click the yes radio button in the form
                    driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[10]/div/div[1]/label/input")).click();
                }

                // Filling the text area
                // Send the text in the JSON Object as input for the project description field on the page
                driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[11]/div/div/textarea")).sendKeys(persons.getJSONObject(personID).get("project_description").toString());

                // Click the submit button
                driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[13]/div/button")).click();

                // Capture the screenshot of the page and save it in the current working directory
                ScreenShot.capture(driver, "handsOnTwo.png");

                System.out.println("Everything was filled properly"); // Console log
            }
            // If the file mentioned for the input data is not found, this exception will be thrown
            catch (FileNotFoundException e){
                // Printing appropriate message
                System.out.println("The file mentioned in the path was not found.");
            }
            // For any other exception that may occur while setting data to an element
            catch (Exception e){
                // Printing stacktrace to know what went wrong
                e.printStackTrace();

                // Printing appropriate message
                System.out.println("The page did not load properly.");
            }
        }
    }

    // The following method quites the driver closing any open pages
    public void stop(){
        driver.close();
    }
}
