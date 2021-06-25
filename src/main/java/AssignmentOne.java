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


// NOTE: The "email" field is named "userName" and the "username" field is named "email"
// field -> name
// username -> email
// email -> userName


// The class that actually does the job required in Assignment 1
public class AssignmentOne {
    // Create the WebDriver object
    WebDriver driver;

    // Main Constructor
    public AssignmentOne(@Nullable ChromeOptions options){
        // If the Chrome Options are provided, create an object with the options else create a default object
        driver =(options != null)? new ChromeDriver(options):new ChromeDriver();

        // Set the driver widow to 1920x1080 size - can be changed to liking or passed as an argument
        driver.manage().window().setSize(new Dimension(1920,1080));
    }

    // If someone does not want to use the Chrome Options, they can call this constructor
    @SuppressWarnings("unused")
    public AssignmentOne(){
        // Calling the main constructor with Chrome Options set ot null
        this(null);
    }

    // The implementation of Hands On 2
    public void run(){
        // Load the page using LoadPage.load (DriverUtils) and if it loads successfully, continue
        if(LoadPage.load(driver, "http://demo.guru99.com/test/newtours/index.php","/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[2]/a")){
            try{
                // Open the file, at the given path, containing the message as a new Scanner object
                Scanner fileScanner = new Scanner(new File("E:\\Seminars\\TCS_Selenium\\SampleSelenium\\src\\main\\java\\AssignmentOneData.txt"));

                // Convert the JSON data to usable JSON object
                JSONArray registrations = (JSONArray) (new JSONObject(fileScanner.useDelimiter("\\Z").next())).get("registrations");
                int registrationID = 0;

                driver.findElement(By.xpath("//a[@href=\"register.php\"]")).click();

                // Wait for the next page to load
                LoadPage.waitToLoad(driver, "/html/body/div[2]");

                // Setting normal text data
                // Send the text in the JSON Object as input for the first name field on the page
                driver.findElement(By.xpath("//input[@name=\"firstName\"]")).sendKeys(registrations.getJSONObject(registrationID).get("first name").toString());
                // Send the text in the JSON Object as input for the last name field on the page
                driver.findElement(By.xpath("//input[@name=\"lastName\"]")).sendKeys(registrations.getJSONObject(registrationID).get("last name").toString());
                // Send the text in the JSON Object as input for the phone field on the page
                driver.findElement(By.xpath("//input[@name=\"phone\"]")).sendKeys(registrations.getJSONObject(registrationID).get("phone").toString());
                // Send the text in the JSON Object as input for the email field on the page
                driver.findElement(By.xpath("//input[@name=\"userName\"]")).sendKeys(registrations.getJSONObject(registrationID).get("email").toString());
                // Send the text in the JSON Object as input for the address field on the page
                driver.findElement(By.xpath("//input[@name=\"address1\"]")).sendKeys(registrations.getJSONObject(registrationID).get("address").toString());
                // Send the text in the JSON Object as input for the city field on the page
                driver.findElement(By.xpath("//input[@name=\"city\"]")).sendKeys(registrations.getJSONObject(registrationID).get("city").toString());
                // Send the text in the JSON Object as input for the state field on the page
                driver.findElement(By.xpath("//input[@name=\"state\"]")).sendKeys(registrations.getJSONObject(registrationID).get("state").toString());
                // Send the text in the JSON Object as input for the postalCode field on the page
                driver.findElement(By.xpath("//input[@name=\"postalCode\"]")).sendKeys(registrations.getJSONObject(registrationID).get("postal code").toString());

                // Select the dropdown element as an Select Object
                Select select = new Select(driver.findElement(By.xpath("//select[@name=\"country\"]")));
                // Set the objects value to the country that corresponds the JSON Object
                select.selectByVisibleText(registrations.getJSONObject(registrationID).get("country").toString().toUpperCase());


                // Filling the text area
                // Send the text in the JSON Object as input for the user name field on the page
                driver.findElement(By.xpath("//input[@name=\"email\"]")).sendKeys(registrations.getJSONObject(registrationID).get("user name").toString());
                // Send the text in the JSON Object as input for the password field on the page
                driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys(registrations.getJSONObject(registrationID).get("password").toString());
                // Send the text in the JSON Object as input for the confirm password field on the page
                driver.findElement(By.xpath("//input[@name=\"confirmPassword\"]")).sendKeys(registrations.getJSONObject(registrationID).get("password").toString());

                // Capture the screenshot of the page and save it in the current working directory
                ScreenShot.capture(driver, "assignmentOne_registration.png");

                // Click the submit button
                driver.findElement(By.xpath("//input[@name=\"submit\"]")).click();

                // Wait for the final page to load
                LoadPage.waitToLoad(driver, "/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table");

                // Capture the screenshot of the page and save it in the current working directory
                ScreenShot.capture(driver, "assignmentOne_registered.png");


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
