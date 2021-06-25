import DriverUtils.LoadPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// The class that actually does the job required in Assignment 2
public class AssignmentTwo {
    // Create the WebDriver object
    WebDriver driver;

    // Main Constructor
    public AssignmentTwo(@Nullable ChromeOptions options){
        // If the Chrome Options are provided, create an object with the options else create a default object
        driver =(options != null)? new ChromeDriver(options):new ChromeDriver();

        // Set the driver widow to 1920x1080 size - can be changed to liking or passed as an argument
        driver.manage().window().setSize(new Dimension(1920,1080));
    }

    // If someone does not want to use the Chrome Options, they can call this constructor
    @SuppressWarnings("unused")
    public AssignmentTwo(){
        // Calling the main constructor with Chrome Options set ot null
        this(null);
    }

    // This function writes all the data to a file in csv format, the file generated is a .txt file
    public void writeResultToFile(String[] information, String enrollmentNo, @Nullable String name, @Nullable String sgpa){
        String storageLocation = System.getProperty("user.dir") + "\\src\\main\\java\\ResultFetcher\\OutputFiles\\";

        String fileName = storageLocation + information[0].replaceAll("\n", "") + " " +
                information[1].replaceAll("\n", "") + " SEM" +
                information[2].replaceAll("\n", "") + " " +
                information[3].replaceAll("\n", "") + " .txt";
        try(FileWriter fileWriter = new FileWriter(fileName, true)){
            System.out.println("PRINTER:");
            String toWrite = enrollmentNo.replaceAll("\n","") + ((name==null)?"":"," + name) + ((sgpa==null)?",No Data Found\n":","+sgpa+"\n");
            System.out.println(toWrite);
            System.out.println("PRINTED");
            fileWriter.write(toWrite);
        }catch (IOException e){
            System.out.println("Data was not inserted properly.");
        }

    }

    public void writeResultToFile(String[] information, String enrollmentNo){
        writeResultToFile(information, enrollmentNo, null, null);
    }


    // The implementation of Assignment 2
    public void run() {
        // Load the page using LoadPage.load (DriverUtils) and if it loads successfully, continue
        try {
            // Open the file, at the given path, containing the message as a new Scanner object
            Scanner fileScanner = new Scanner(new File(System.getProperty("user.dir") + "\\src\\main\\java\\ResultFetcher\\InputFiles\\toFetchResultsOf.txt"));
            while (fileScanner.hasNext()) {
                String currentLine = fileScanner.nextLine();
                Scanner idsFile = new Scanner(new File(
                        System.getProperty("user.dir")
                                + "\\src\\main\\java\\ResultFetcher\\InputFiles\\en_ids "
                                + currentLine.replaceAll(",", " ").replaceAll("\n", "")
                                + ".txt"
                ));

                String[] enrollmentCategories = idsFile.useDelimiter("\\Z").next().split("\n");
                for (String enrollmentCategory : enrollmentCategories) {
                    int counter = 1;
                    while (counter < 301) {
                        if (LoadPage.load(driver, "https://charusat.edu.in:912/Uniexamresult/frmUniversityResult.aspx", "//*[@id=\"form1\"]/div[3]/table[2]")) {
                            StringBuilder enrollmentID = new StringBuilder();

                            // Adding the required 0s
                            if (counter < 10) enrollmentID.append("00");
                            else if (counter < 100) enrollmentID.append("0");

                            //Converting the string to enrollment number
                            enrollmentID = new StringBuilder(
                                    enrollmentCategory.replaceAll("\n", "").replace("\r","")
                                    + enrollmentID.toString().replaceAll("\r","").replaceAll("\n","")
                                    + counter
                            );

                            System.out.println("SEE ME:");

                            System.out.print("TEST: ");
                            System.out.print(enrollmentCategory);
                            System.out.println(enrollmentID.toString().replaceAll("\n", ""));

                            System.out.println("Sending ID: " + enrollmentID); // Console log

                            String[] examInformation = currentLine.split(",");

                            Select institute_select = new Select(driver.findElement(By.xpath("//*[@id=\"ddlInst\"]")));
                            institute_select.selectByVisibleText(examInformation[0].replaceAll("\n", ""));

                            // Wait for the selection menu to be filled with data and select the appropriate degree
                            LoadPage.presenceToLoad(driver, "//*[@id=\"ddlDegree\"]");
                            Select degree_select = new Select(driver.findElement(By.xpath("//*[@id=\"ddlDegree\"]")));
                            degree_select.selectByVisibleText(examInformation[1].replaceAll("\n", ""));

                            // Wait for the selection menu to be filled with data and select the appropriate Semester
                            LoadPage.presenceToLoad(driver, "//*[@id=\"ddlSem\"]");
                            Select sem_select = new Select(driver.findElement(By.xpath("//*[@id=\"ddlSem\"]")));
                            sem_select.selectByVisibleText(examInformation[2].replaceAll("\n", ""));

                            // Wait for the selection menu to be filled with data and select the appropriate Examination
                            LoadPage.presenceToLoad(driver, "//*[@id=\"ddlScheduleExam\"]");
                            Select scheduleExam_select = new Select(driver.findElement(By.xpath("//*[@id=\"ddlScheduleExam\"]")));
                            scheduleExam_select.selectByVisibleText(examInformation[3].replaceAll("\n", ""));

                            // Set the enrollment number
                            driver.findElement(By.xpath("//*[@id=\"txtEnrNo\"]")).sendKeys(enrollmentID);

                            System.out.println("Data filled"); // Console log


                            // Try and click the search button
                            try{
                                // If clickable, click and go to the result page
                                driver.findElement(By.xpath("//*[@id=\"btnSearch\"]")).click();
                            }catch (Exception e){
                                System.out.println("Error... Search button is not clickable."); // Console log
                            }

                            try {
                                LoadPage.presenceToLoad(driver, "//*[@id=\"form1\"]");

                                // Write everything in the file
                                writeResultToFile(
                                        examInformation,
                                        driver.findElement(By.xpath("//*[@id=\"uclGrd1_lblExamNo\"]")).getText(),
                                        driver.findElement(By.xpath("//*[@id=\"uclGrd1_lblStudentName\"]")).getText(),
                                        driver.findElement(By.xpath("//*[@id=\"uclGrd1_lblSGPA\"]")).getText()
                                        );

                                // Click the back button
                                driver.findElement(By.xpath("//*[@id=\"btnBack1\"]")).click();
                            }catch (Exception e){
                                writeResultToFile(examInformation, enrollmentID.toString());
                            }
                        }
                        counter++;
                    }
                }
            }
        }
        // If the file mentioned for the input data is not found, this exception will be thrown
        catch (FileNotFoundException e) {
            // Printing appropriate message
            System.out.println("The file mentioned in the path was not found.");
        }
        // For any other exception that may occur while setting data to an element
        catch (Exception e) {
            // Printing stacktrace to know what went wrong
            e.printStackTrace();

            // Printing appropriate message
            System.out.println("The page did not load properly.");
        }
    }
    // The following method quites the driver closing any open pages
    public void stop(){
        driver.close();
    }

}
