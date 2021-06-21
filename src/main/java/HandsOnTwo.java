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

public class HandsOnTwo {

    WebDriver driver;

    public HandsOnTwo(@Nullable ChromeOptions options){
        driver =(options != null)? new ChromeDriver(options):new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920,1080));
    }

    public HandsOnTwo(){
        this(null);
    }

    public void run(){
        if(LoadPage.load(driver, "https://www.seleniumeasy.com/test/input-form-demo.html","/html/body/div[2]/div/div[1]/div/div[1]")){
            LoadPage.closePopup(driver, "//*[@id=\"at-cv-lightbox-close\"]", 10);
            try{
                // Open the file with the data
                Scanner fileScanner = new Scanner(new File("E:\\Seminars\\TCS_Selenium\\SampleSelenium\\src\\main\\java\\HandsOnTwoData.txt"));

                // Convert the JSON data to usable JSON object
                JSONArray persons = (JSONArray) (new JSONObject(fileScanner.useDelimiter("\\Z").next())).get("persons");
                int personID = 0;

                // Setting normal text data
                driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[1]/div/div/input")).sendKeys(persons.getJSONObject(personID).get("first name").toString());
                driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[2]/div/div/input")).sendKeys(persons.getJSONObject(personID).get("last name").toString());
                driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[3]/div/div/input")).sendKeys(persons.getJSONObject(personID).get("email").toString());
                driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[4]/div/div/input")).sendKeys(persons.getJSONObject(personID).get("phone").toString());
                driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[5]/div/div/input")).sendKeys(persons.getJSONObject(personID).get("address").toString());
                driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[6]/div/div/input")).sendKeys(persons.getJSONObject(personID).get("city").toString());

                // Select the state from the dropdown
                Select select = new Select(driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[7]/div/div/select")));
                select.selectByVisibleText(persons.getJSONObject(personID).get("state").toString());

                // Some more text data
                driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[8]/div/div/input")).sendKeys(persons.getJSONObject(personID).get("zip code").toString());
                driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[9]/div/div/input")).sendKeys(persons.getJSONObject(personID).get("website").toString());

                // Selecting the radio button
                if(persons.getJSONObject(personID).get("hosting").toString().compareTo("no") == 0){
                    driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[10]/div/div[2]/label/input")).click();
                }else{
                    driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[10]/div/div[1]/label/input")).click();
                }

                // Filling the text area
                driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[11]/div/div/textarea")).sendKeys(persons.getJSONObject(personID).get("project_description").toString());

                // Click the submit button
                driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[13]/div/button")).click();

                // Taking a screenshot
                ScreenShot.capture(driver, "handsOnTwo.png");
                System.out.println("Everything was filled properly");
            }
            // If the file mentioned for the input data is not found, this exception will be thrown
            catch (FileNotFoundException e){
                System.out.println("The file mentioned in the path was not found.");
            }
            // For any other exception that may occur while setting data to an element
            catch (Exception e){
                e.printStackTrace();
                System.out.println("The page did not load properly.");
            }
        }
    }

    public void stop(){
        driver.close();
    }
}
