package DriverUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;

// This is a simple utility to load a page with a WebDriver
// If required, the driver can wait for a specific element to load before moving forward
public class LoadPage {

    // Main load method
    // WebDriver - the driver with which you want to load the page
    // Url - the url of the page you want to load
    // Name - the xpath of the element for which you want to wait before doing anything else (max wait 60s)
    public static boolean load(WebDriver driver, String url, @Nullable String elementToWait) {
        // Call to the default get method to load the page
        driver.get(url);

        try {
            System.out.println("Waiting for page to load..."); // Console log

            // If the xpath of the element to wait for is provided, wait for it
            if (elementToWait != null) {
                // Create the WebDriverWait object for the provided driver and a max wait of 60s
                WebDriverWait wait = new WebDriverWait(driver, 60);

                // Make the driver wait until the element is found in the webpage
                // This can be swapped to ExpectedArguments.elementToBeClickable if required
                wait.until(d -> d.findElement(By.xpath(elementToWait)));
            }
            System.out.println("Page loaded successfully."); // Console log

            // Returning true as the page was loaded successfully
            return true;
        }
        // If any exception occurs during the loading process, it will be caught here
        catch (Exception e) {
            // Printing appropriate message
            System.out.println("The page did not load properly / in time.");
        }
        // Returning false as the page did not load correctly
        return false;
    }

    // An alternate load method which can be used when elementToWait is not required
    public static boolean load(WebDriver driver, String url) {
        // Call to the original load with elementToWait is set to null
        return load(driver, url, null);
    }

    // Main load method
    // WebDriver - the driver with which you want to load the page
    // elementToWait - xpath of the element of a popup that will become visible when the popup shows up
    // Nullable closeButton - xpath of the x / ok / close button for the popup
    // Nullable timeTiWait - the maximum time you want to wait for the popup
    public static boolean closePopup(WebDriver driver, String elementToWait, @Nullable String closeButton, @Nullable Integer timeToWait) {
        // Close the popup that appears on page load
        try {
            System.out.println("Waiting for the popup..."); // Console log

            // Create the WebDriverWait object for the provided driver and a max wait of 10s if not provided
            WebDriverWait wait = new WebDriverWait(driver, (timeToWait == null)? 10:timeToWait);

            // Make the driver wait until the element is found in the webpage
            // This can be swapped to ExpectedArguments.elementToBeClickable if required
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementToWait)));

            // Click on the close button
            // If closeButton is null, click elementToWait other wise the dedicated closeButton
            driver.findElement(By.xpath((closeButton == null) ? elementToWait : closeButton)).click();
            System.out.println("The popup appeared and closed."); // Console log

            // Returning true as the popup was closed successfully
            return true;
        }

        // If the wait expires the max limit or the given elements are not clickable,
        // the exceptions generated are caught here
        catch (Exception e) {
            System.out.println("The popup did not appear."); // Console log
        }
        // Returning true as the popup was not closed properly or it did not appear
        return false;
    }

    // An alternate closePopup method which can be used when closeButton and element to wait are same
    // and wait duration is to be kept default
    public static boolean closePopup(WebDriver driver, String elementToWait) {
        // Call to the original closePopup with closeButton and timeToWait set to null
        return closePopup(driver, elementToWait, null, null);
    }

    // An alternate closePopup method which can be used when closeButton and element to wait are same
    public static boolean closePopup(WebDriver driver, String elementToWait, int timeToWait) {
        // Call to the original closePopup with closeButton set to null
        return closePopup(driver, elementToWait, null, timeToWait);
    }

    // An alternate closePopup method which can be used when timeToWait is to be kept default (10s)
    public static boolean closePopup(WebDriver driver, String elementToWait, String closeButton) {
        // Call to the original closePopup with timeToWait set to null
        return closePopup(driver, elementToWait, closeButton, null);
    }
}