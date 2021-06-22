import org.openqa.selenium.chrome.ChromeOptions;

public class RunnerTheGunner {

    // The runner code for Hands On 1
    public static void handsOnOneRunner(){
        ChromeOptions options = new ChromeOptions(); // Create chrome options, if required. Optional

        // The following arguments are all options and can be added or removed as required
        // A list of all chrome options can be found at:
        // https://peter.sh/experiments/chromium-command-line-switches/
        options.addArguments("start-maximized"); // If the code is running in headed mode, this is maximize the window
        options.addArguments("headless"); // If you don't want to see the window, use this option

        // Create the object of the HandsOnOne class with chrome options
        HandsOnOne runner = new HandsOnOne(options);

        System.out.println("Running Q1");
        runner.runQ1(); // Run the code for Question 1

        System.out.println("\n\nRunning Q2");
        runner.runQ2(); // Run the code for Question 2

        // Stop and close the browser - compulsory line or else
        // there will be an open port hanging unless the program is closed
        runner.stop();
    }

    // The runner code for Hands On 2
    public static void handsOnTwoRunner(){
        ChromeOptions options = new ChromeOptions();  // Create chrome options, if required. Optional

        // The following arguments are all options and can be added or removed as required
        // A list of all chrome options can be found at:
        // https://peter.sh/experiments/chromium-command-line-switches/
        options.addArguments("start-maximized"); // If the code is running in headed mode, this is maximize the window
        options.addArguments("headless"); // If you don't want to see the window, use this option

        // Create the object of the HandsOnOne class with chrome options
        HandsOnTwo runner = new HandsOnTwo(options);

        runner.run();  // Run the code for Hands On 2

        // Stop and close the browser - compulsory line or else
        // there will be an open port hanging unless the program is closed
        runner.stop();
    }

    public static void assignmentOneRunner(){
        ChromeOptions options = new ChromeOptions();  // Create chrome options, if required. Optional

        // The following arguments are all options and can be added or removed as required
        // A list of all chrome options can be found at:
        // https://peter.sh/experiments/chromium-command-line-switches/
        options.addArguments("start-maximized"); // If the code is running in headed mode, this is maximize the window
        options.addArguments("headless"); // If you don't want to see the window, use this option

        // Create the object of the HandsOnOne class with chrome options
        AssignmentOne runner = new AssignmentOne(options);

        runner.run();  // Run the code for Hands On 2

        // Stop and close the browser - compulsory line or else
        // there will be an open port hanging unless the program is closed
        runner.stop();
    }

    // Main function
    public static void main(String[] args){
       // Setting the webdriver location, this will change per machine
        System.setProperty("webdriver.chrome.driver","E:\\Tools\\Drivers\\ChromeDriver\\chromedriver.exe");

        System.out.println("Running Hands On 1\n\n\n"); // Console log
        handsOnOneRunner(); // Call the handsOnOneRunner()

        System.out.println("Running Hands On 2\n\n\n"); // Console log
        handsOnTwoRunner(); // Call the handsOnTwoRunner()

        System.out.println("Running Assignment 1\n\n\n"); // Console log
        assignmentOneRunner(); // Call the assignmentOneRunner()

    }
}
