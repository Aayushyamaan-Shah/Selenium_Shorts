# Selenium_Shorts
This small JAVA project is/was created to complete tasks for the Hands On and assignments that were provided during the TCS Selenium training for testing automation sessions.
I am uploading the source code as I have gone through and created a few simple utility classes that can help anyone to eleminate the need of writing the repeatating tasks such 
as closing a popup or taking a snapshot.


# Project Structure
The main project is divided in a few parts: 
  - The Runner file
    - This file contains the main function which will run all the code
  - Main files
    - These files are the ones that have the actual code for interracting with the pages
  - The generic files aka the DriverUtils package
    - This package contains methods that common to all/most of the main files


# DriverUtils Package
This DriveUtils Packacge is the package that you are looking for if you want my code that handles day to day taksk like adding loading logs to console/println or taking
a screen shot of the current visible section on the page, etc


# How it works
To run the program, you need all the .java files in the same manner they are presented here. Along with that the following are the additional requirements you will need
- Selenium Webdriver
- ChromeDriver
- JDK Version 1.8+ 

If you meet the above requirements, just download the files and run the "RunnerTheGunner.java" class and the output will be generated in the current project directory.

All the inputs in the program are taken from text files and are easy to change. Just change the values in the text file and save it. Once saved, run the RunnerTheGunner.java and the program should run with the new values.

If you find any bugs or are not able to run the program, feel free to contact me on my Discord: "TheElemental_A5#0444" or mail it to me at "aayush474@gmail.com"

# License
The whole project is licensed under the GNU GPL 3 license
