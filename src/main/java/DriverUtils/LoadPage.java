package DriverUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;

public class LoadPage {
    public static boolean load(WebDriver driver, String url, @Nullable String elementToWait) {
        driver.get(url);
        try {
            System.out.println("Waiting for page to load...");
            if (elementToWait != null) {
                WebDriverWait wait = new WebDriverWait(driver, 60);
                wait.until(d -> d.findElement(By.xpath(elementToWait)));
            }
            System.out.println("Page loaded successfully.");
            return true;
        } catch (Exception e) {
            System.out.println("The page did not load properly / in time.");
        }
        return false;
    }

    public static boolean load(WebDriver driver, String url) {
        return load(driver, url, null);
    }

    public static boolean closePopup(WebDriver driver, String elementToWait, @Nullable String closeButton, @Nullable Integer timeToWait) {
        // Close the popup that appears on page load
        try {
            System.out.println("Waiting for the popup...");
            WebDriverWait wait = new WebDriverWait(driver, (timeToWait == null)? 10:timeToWait);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementToWait)));
            driver.findElement(By.xpath((closeButton == null) ? elementToWait : closeButton)).click();
            System.out.println("The popup appeared and closed.");
            return true;
        } catch (Exception e) {
            System.out.println("The popup did not appear.");
        }
        return false;
    }

    public static boolean closePopup(WebDriver driver, String elementToWait) {
        return closePopup(driver, elementToWait, null, null);
    }
    public static boolean closePopup(WebDriver driver, String elementToWait, int timeToWait) {
        return closePopup(driver, elementToWait, null, timeToWait);
    }
    public static boolean closePopup(WebDriver driver, String elementToWait, String closeButton) {
        return closePopup(driver, elementToWait, closeButton, null);
    }
}