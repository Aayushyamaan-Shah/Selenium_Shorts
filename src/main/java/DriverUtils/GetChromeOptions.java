package DriverUtils;

import org.openqa.selenium.chrome.ChromeOptions;

// This utility class is used to get the ChromeDriver with commonly used options using one single line
// The utility does not cover all the possible arguments, rather it covers the most common arguments that one uses

// The following list explains all the arguments that can be passed and what it does
// start-maximized: Used to open Chrome in maximize mode
// incognito: Used to open Chrome in incognito mode
// headless: Used to open Chrome in headless mode
// disable-extensions: Used to disable existing extensions on Chrome browser
// disable-popup-blocking: Used to disable pop-ups displayed on Chrome browser
// make-default-browser: Used to make Chrome as default browser
// version: Used to print chrome browser version
// disable-infobars: Used to prevent Chrome from displaying the notification.

public class GetChromeOptions {
    // The method accepts the options list, whitespace separated, as a string
    // requiredOptions - The list of all the arguments one needs added to the
    public static ChromeOptions withCommonArgs(String requiredOptions){
        // Creates the chrome options object
        ChromeOptions options = new ChromeOptions();

        // Check if headless is present in the list, if yes, add it
        if(requiredOptions.contains("headless"))
            options.addArguments("headless");

        // Check if start-maximized is present in the list, if yes, add it
        if(requiredOptions.contains("start-maximized"))
            options.addArguments("start-maximized");

        // Check if incognito is present in the list, if yes, add it
        if(requiredOptions.contains("incognito"))
            options.addArguments("incognito");

        // Check if disable-extensions is present in the list, if yes, add it
        if(requiredOptions.contains("disable-extensions"))
            options.addArguments("disable-extensions");

        // Check if disable-popup-blocking is present in the list, if yes, add it
        if(requiredOptions.contains("disable-popup-blocking"))
            options.addArguments("disable-popup-blocking");

        // Check if disable-infobars is present in the list, if yes, add it
        if(requiredOptions.contains("disable-infobars"))
            options.addArguments("disable-infobars");

        // Check if version is present in the list, if yes, add it
        if(requiredOptions.contains("version"))
            options.addArguments("version");

        // Check if make-default-browser is present in the list, if yes, add it
        if(requiredOptions.contains("make-default-browser"))
            options.addArguments("make-default-browser");

        // Return the object that has the required arguments
        return options;
    }
}
