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

###1. Clone the repository:
git clone https://github.com/nitishmaxQA/Nitish-Kumar.git

## 1. Configure Environment in `main/resources/globaldata/test.properties` file

- **ANDROID_HOME**: Specify the `ANDROID_HOME` path (if not already set in your system).
- **APPIUM JS File Path**: 


## 2. How to Run Tests

### Running Tests with Maven
- **mvn test
- **For API tests only run:mvn test -Dtest=apitest.ApiTest
- **For APP tests only run :mvn test -Dtest=uitest.MonefyTest
- ** or run testng.xml file
- **Before API test ,please run mvn package jetty:run as explained in guide https://github.com/swagger-api/swagger-petstore


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

## Tech Stack

- **Selenium**: For element interaction (used internally with Appium).
- **Java**: As the programming language.
- **Appium**: For Android/iOS native and hybrid app testing.
- **TestNG**: Test runner for managing test cases and reports.
- **ExtentReports**: For detailed and customizable test reporting.
- **Maven**: For project build, dependency management, and automation.
- **Page Object Model (POM)**: A design pattern that enhances test code structure and maintenance.
- **RestAssured**: For REST API automation (validation, assertions) and ExtentReports reporting.


### Java Compatibility

- This project is tested with **Java 8**.
- If you're using **Java 15 or later**, consider:
  - Switching to Java 8 via environment settings, OR
  - Ensuring backward compatibility in your IDE and `pom.xml` (set compiler source/target to 1.8).
  
### Project Structure Overview

- **Driver Management**:  
  The `DriverManager` class handles **driver initialization** and **management**.

- **Mobile App Tests**:  
  Mobile app test classes and the `BaseTest` class are located under folder `src/test/java/uitest`.

- **API Tests**:  
  API test classes and the `BaseApiTest` class are located under folder `src/test/java/apitest`.

- **Page Object Model (Locators)**:  
  Locator classes (Page Objects) are found under folder `src/test/java/pageobject`.

- **User Actions**:  
  The `ActionHelper` class, where all app user actions are defined, is located under folder `src/main/java/utils`.

- **API Helper Utilities**:  
  API helper utilities are located under folder `src/main/java/apiutils`.

- **TestNG Configuration**:  
  The `testng.xml` configuration file is located under folder `src/test/resources`.

- **Test Execution Reports**:  
  Test execution reports are generated and can be found under the `test-output` folder (apitest_report.html Api test report and apptest_report.html App test report).



