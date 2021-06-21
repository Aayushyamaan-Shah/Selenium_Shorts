package DriverUtils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;

// This is a simple utility to take a screenshot of the current page in the driver
// The screenshot is taken of the area which is visible at the time and NOT the full page
public class ScreenShot {

    // Main capture method
    // WebDriver - the driver of which you want to screenshot the loaded page
    // Nullable Location - the location where the screenshot is to be save
    // Name - the name of the file after saving it
    public static boolean capture(WebDriver driver, @Nullable String location, String name){
        System.out.println("Capturing screenshot..."); // Console log
        // Take the screenshot and returns a file
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            // Write the file to a location - Save the file

            // Get the current working directory
            String currentLocation = System.getProperty("user.dir").replace("/","\\\\");

            // Save the resource at the current working directory or the directory specified
            // The name of the file is the name that was passed in as the argument
            FileUtils.copyFile(screenshot, new File(((location==null)?currentLocation:location) + "\\"+name));
            System.out.println("Screenshot was captured."); // Console log

            // If successful, return true
            return true;
        } catch (IOException e) {
            // Printing appropriate message
            System.out.println("Screen shot was not captured. Please update the file path if necessary.");
        }
        // If unsuccessful, return false
        return false;
    }

    // Alternate capture method which can be called if you don't want to specify the location for saving the screenshot
    // In this case, the default working directory is selected as the location
    public static boolean capture(WebDriver driver, String name){
        return capture(driver, null, name);
    }
}