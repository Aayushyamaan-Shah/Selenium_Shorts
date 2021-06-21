package DriverUtils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;

// This is a simple utility to take a screenshot of the current page of a driver
public class ScreenShot {
    public static boolean capture(WebDriver driver, @Nullable String location, String name){
        System.out.println("Capturing screenshot...");
        // Take the screenshot and returns a file
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            // Write the file to a location - Save the file

            // Get the current working directory
            String currentLocation = System.getProperty("user.dir").replace("/","\\\\");

            // Save the resource at the current working directory or the directory specified
            FileUtils.copyFile(screenshot, new File(((location==null)?currentLocation:location) + "\\"+name));
            System.out.println("Screenshot was captured.");

            // If successful, return true
            return true;
        } catch (IOException e) {
            System.out.println("Screen shot was not captured. Please update the file path if necessary.");
        }
        // If unsuccessful, return false
        return false;
    }

    public static boolean capture(WebDriver driver, String name){
        return capture(driver, null, name);
    }
}