# Test Automation Framework - Selenium, Appium & RestAssured (Page Object Model)

## Overview

This project is a cross-platform **UI and API test automation framework** designed for **iOS/Android mobile applications** and **REST APIs**.  
It follows the **Page Object Model (POM)** design pattern to ensure better maintainability, scalability, and readability.


##  Setup

### Prerequisites

Before setting up the framework, ensure you have the following installed:

- **Java** (JDK 8 or higher)
- **Maven** (build and dependency management)
- **Node.js & npm** (for Appium installation)
- **Appium Server** (started automatically via code)
- **Android Studio** (for emulator/device configuration)
- **Device Drivers** (ADB, Android SDK tools)
- **Appium Java Client** (for mobile automation)
- **Selenium WebDriver** (for mobile element interactions via Appium)
- Enable **Developer Options** and **USB Debugging** on real devices.

### Installation Steps

1. Clone the repository:
git clone <repository-url>


2.Configure env. in main/resources/globaldata/test.properties file
-Platform (Android in for implemented app tests)
-ANDROID_HOME path (if not already set in system)
-APPIUM JS file path
-app.package (no need to change)
-app.activity(no need to change)
-jsonFilePath (for api automation, request body)
-base_url and endpoints (for api automation)

Install dependencies using Maven:
mvn clean install

3.Right-click on testng.xml to run, or use mvn test 
For API tests only run:mvn test -Dtest=apitest.ApiTest
For APP tests only run :mvn test -Dtest=uitest.MonefyTest


— The Appium server will start automatically via the code, so you do not need to start it manually.
-Before API test ,please run mvn package jetty:run as explained in guide https://github.com/swagger-api/swagger-petstore


###### **Approach & Tech Stack**

### UI Automation Approach

- Follows the Page Object Model (POM) design pattern, separating page structure from test logic for better readability and maintainability.
- All mobile app locators are centralized in a dedicated Page Object class, making UI updates easier without impacting test scripts.
- Enhances code reusability by promoting common action methods across multiple test cases.
- Appium is used with Selenium WebDriver to automate mobile applications across Android and iOS platforms, 
- Common mobile actions like click, send keys, swipe, and scroll are centralized in utility helper classes.
- Device setup, platform-specific configurations, and driver management are handled dynamically through configuration files and DriverManager.
- The setup is fully scalable, allowing easy extension to new devices, platforms, or app versions.
- Support for web application testing can be added.
- The framework uses open-source tools like Appium and Selenium, ensuring community support, flexibility, and cost-effectiveness.

### API Automation Approach

- RestAssured is used for automating REST API testing and validation.
- Wrapper methods are created over RestAssured calls to standardize request building, execution, and response handling.
- JSONPath and simple assertions are used for validating API responses and extracting dynamic data.
- Test data (endpoints, parameters) are externalized in property files to support dynamic configurations and easy environment switching.
- API utilities are organized separately under `src/main/java/apiutils` for better reusability and separation of concerns.
- The framework supports both positive and negative API scenarios.
- The solution is lightweight, modular, and fully integrated within the existing Maven/TestNG structure for easy execution and reporting.

Tech Stack
	•	Selenium for Element interaction (used internally with Appium)
	•	Java as the programming language
	•	Appium for Android/iOS native and hybrid app testing
	•	TestNG:Test runner for managing test cases and reports.
	•	ExtentReports: For detailed and customizable test reporting.
	•	Maven: For project build, dependency management, and automation.
	•	Page Object Model (POM): A design pattern that enhances test code structure and maintenance.
	•	RestAssured for REST API automation (validation and assertions) and ExtentReports reporting.

### Java Compatibility

- This project is tested with **Java 8**.
- If you're using **Java 15 or later**, consider:
  - Switching to Java 8 via environment settings, OR
  - Ensuring backward compatibility in your IDE and `pom.xml` (set compiler source/target to 1.8).
  
### "Project Structure" 
The project is structured to separate responsibilities:

-DriverManager handles driver initialization and management.
-Mobile app test classes and BaseTest are under src/test/java/uitest.
-API test and BaseApiTest classes are under src/test/java/apitest.
-Page Object (locator)classes are under src/test/java/pageobject.
-ActionHelper class (where all app user actions are defined) is located under src/main/java/utils.
-API helper utility is organized under src/main/java/apiutils.
-testng.xml can be found under src/test/resources


