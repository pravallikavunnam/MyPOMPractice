package com.qa.opencart.tests;

public class MavenTheory {

	public static void main(String[] args) {

/*
 * whenever we run any maven command what is the seq of execution
 * 
 * 1. First it will goes to POM.XML file---that is the first thing maven understands
 * 2. In the pom.xml file it will check all the dependencies
 * 3. It will complie the code
 * 4. Then it will go to surefire pulgin
 * 5. and then it will go to TestNG.xml file--it has thread count,test blocks--
 * -----and whatever test blocks we have written it will start running from there
 * surefire plugin will start running my test cases with the help of testng.xml file
 * 
 * 
 * parallel execution and thread control is done by TestNG not by Maven
 * 
 * Maven -- with the help of pom.xml maven is going to compile code and run test cases with the help of surefire pulgin
 * 
 * 
 * 
 * if we don't want to use eclipse
 * 
 * we use terminal 
 * 
 * firstly we have to install maven in your laptop
 * 
 * 
 * Maven says JAVA should be installed in your system if we want to install maven in our system
 * 
 * To check maven installed in our system or not form cmd ====   mvn -version
 * 
 * 
 * Different ways of running test cases
 * 
 * 1. Eclipse Maven ---Run as ---Maven Build...---clean install -Denv="qa"
 * 2.from Command Prompt ----mvn clean install -Denv="stage"
 * 3. testng_regression.xml--Run it
 * 4. 
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

}
