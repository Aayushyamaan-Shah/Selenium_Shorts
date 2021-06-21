import org.openqa.selenium.chrome.ChromeOptions;

public class LaunchBrowser {

    public static void handsOnOneRunner(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("headless");
        HandsOnOne runner = new HandsOnOne(options);
        System.out.println("Running Q1");
        runner.runQ1();
        System.out.println("\n\nRunning Q2");
        runner.runQ2();
        runner.stop();
    }
    public static void handsOnTwoRunner(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("headless");
        HandsOnTwo runner = new HandsOnTwo();
        runner.run();
        runner.stop();
    }

    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver","E:\\Tools\\Drivers\\ChromeDriver\\chromedriver.exe");
        System.out.println("Running Hands On 1");
        //handsOnOneRunner();
        System.out.println("\n\n\nRunning Hands On 2");
        handsOnTwoRunner();
    }
}
