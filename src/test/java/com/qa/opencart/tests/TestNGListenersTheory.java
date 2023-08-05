package com.qa.opencart.tests;

public class TestNGListenersTheory {

	/*
	 * Listeners : which will listen something
	 * TestNG Listeners is feature of TestNG
	 * 
	 * Listeners --- after @Test got executed will check Status--Pass/fail/Skipped
	 * method name,class name---captures some basic info like from which package it is coming,which class it is coming
	 * in case of any fail will take screenshot so we can attach in report
	 * 
	 * same process for all the test cases
	 * 
	 * same thing will be done by Allure Report Listener and Extent Report Listener
	 * 
	 * once entire execution is done it will capture all the results of each and every test and generate nice report
	 * 
	 * Extent Report Listener will generate directly one .HTML file
	 * 
	 * Allure Report Listener will generate JSON Files --In the JSON File it will maintain all the status
	 * and also it will generate some attachments in case any failures are there
	 * attachmenets will be byte[] ie..byte array form 
	 * JSON files and attachmenets will be stored in Allure  report folder 
	 * we will run a command and we generate HTML File
	 * 
	 * Allure report folder will ge generated under project by default ---inside that all attachmenets and JSON Files will be generated
	 * 
	 * we have to use one Utility/Allure Server with the help of it we can read all these JSON files ---
	 * ----after reading it will generate a nice look and feel Report in the .html format
	 * 
	 * 
	 * To Install Allure in our system
	 * 1. First install scoop from Powerterminal
	 * Set-ExecutionPolicy RemoteSigned -Scope CurrentUser
	 * Y
	 * irm get.scoop.sh | iex
	 * 2. Install Allure in Command Prompt
	 * scoop install allure
	 * 3. To check Allure installed in system allure --version
	 * 
	 * 4. To check DIR command to see all directories and folders in command prompt
	 * 
	 * 5. allure serve  allure-results/   ---to check the report from JSON Files and attachments
	 * 
	 * 6. it will open in browser with a html file http://192.168.1.2:56123/index.html#
	 * 
	 * we cannot take any backup of the allure report as it a web report
	 * 
	 * every time a new report will be generated when we run the command
	 * 
	 * 
	 * In JIRA at higher level we create epic under which we create user stories
	 * 
	 * 
	 * Listeners will be running always in the background
	 * but we have to attach this two listeners in the testng.xml file ie.. in runner file 
	 * 
	 * when we run xml file --these two listeners will get activated
	 * test will start running --in the background both the listeners will start listening execution status of tests
	 * 
	 * once entire execution is completed entire report will be generated
	 * 
	 * These listeners are provided by third party libraries we no need to create we just use them
	 * which are available in the form of Java files because we are using selenium with java and TestNG we are using 
	 * and testng will support only Java
	 * 
	 * 
	 * who will take Screenshot ---always By Selenium 
	 * Because  it is selenium feature
	 * selenium has to interact with browser --we take screenshot for browser
	 * we create a seperate utility with method for taking screenshot and return path of the screenshot which can be attached in the report
	 * 
	 * so whenever test get failed we call the method which will take the screenshot 
	 * so listeners will attach the screenshot in the reports
	 * 
	 * 
	 * 
	 * In order to add Listeners--Listeners also part of testNG- we add under src/main /java
	 * 
	 * in Listeners package we have to add two listeners files
	 * 
	 * 
	 * we add Extent report dependency in pom.xml file
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
}
