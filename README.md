# STel Automation Framework

**1. Scenarios for Testcase**

1. Add to do task list

2. Perform Basic operations

   a. Mark as complete
   
   b. active task
   
   c. all tasks
   
   d. clear completed tasks
   
**2.Framework Design**

**Framework Designed with Page Object Model used with Java, Selenium Webdriver, TestNG, Cucumber. And data derived from excel with Apache POI.** 

**Prerequisite:**

**Java** – Download and Install java (Set environment variable)

**Eclipse** – Download and Install Eclipse.

**Maven** - Download and Install Maven (Set environment variable)

**TestNG** - Install TestNG on Eclipse.

**Cucumber** - Add Cucumber plugins.



**To import project from GIT**

Launch eclipse

Click File → import → Git → Project from Git → Clone URL → Enter URL (URL - https://github.com/kselvarajbe/STel.git - Connect to preview ), or get from this page. 

Build and update project.

**To Run**

Via Eclipse - Project → src/test/java/TestRunner → Run as → TestNG Test

Via Command prompt (used to view cucumber report)- Navigate to project folder → Run the command **'mvn clean verify'**

**To view Report**

Navigated to target > cucumber-html-reports > overview-fetures.html

Open ‘overview-fetures.html’ file with browser. screenshot added for test cases.
