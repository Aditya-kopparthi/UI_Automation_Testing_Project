# Amazon UI Automation Framework

## Overview
This project is a Hybrid Test Automation Framework built using:

- Java
- Selenium WebDriver
- Cucumber (BDD)
- TestNG
- Maven

The framework automates key user scenarios on Amazon and is designed to be scalable, maintainable, and reusable.

---

## Objective
- Implement Page Object Model (POM)
- Use BDD with Cucumber
- Support Data-Driven Testing (JSON)
- Enable Cross-Browser Execution
- Generate Reports and Logs
- Support Parallel Execution

---

## Framework Architecture

```
Test Runner
     ↓
Feature Files (BDD)
     ↓
Cucumber Engine
     ↓
Hooks (@Before)
     ↓
Step Definitions (Test Logic)
     ↓
Page Object Layer (POM)
     ↓
Driver Factory
     ↓
Browser (Chrome / Edge)
     ↓
Hooks (@After)

---------------------------------------
Utilities Layer
(Config | JSON | Wait | Screenshot)
---------------------------------------

Reporting Layer
(Extent Reports + Log4j2)
```

---

## Project Structure

```
src
 ├── main/java
 │   ├── pages
 │   ├── utils
 │
 ├── test/java
 │   ├── stepdefinitions
 │   ├── runners
 │   ├── hooks
 │
 ├── test/resources
 │   ├── features
 │   │   ├── search.feature
 │   │   └── add_to_cart.feature
 │   ├── testdata
 │   │   └── searchData.json
```

---

## Test Scenarios

### Search Product (Data-Driven)
- Open Amazon homepage
- Enter product from JSON
- Click first suggestion
- Validate results

### Add to Cart
- Navigate to Toys category
- Select product
- Add to cart
- Validate cart and subtotal

---

## Data Driven Testing

```json
{
  "product": "iPhone"
}
```

---

## Utilities

| Utility | Description |
|--------|------------|
| ConfigReader | Reads configuration (browser, URL) |
| JsonReader | Fetches test data |
| WaitUtil | Handles synchronization |
| ScreenshotUtil | Captures screenshots on failure |
| ExtentManager | Generates reports |

---

## Cross Browser Support

Supported browsers:
- Chrome
- Edge

Configuration (config.properties):
```
browser=chrome
```

---

## Parallel Execution

```java
@DataProvider(parallel = true)
public Object[][] scenarios() {
    return super.scenarios();
}
```

---

## Reporting and Logging

- Extent Reports
- Log4j2 Logging
- Screenshots on failure

---

## Execution

Run using Maven:
```
mvn clean test
```

---

## Reports Location

- Extent Reports → /reports
- Screenshots → /reports/screenshots
- Logs → /logs

---

## OOP Concepts Used

- Encapsulation → Page classes
- Abstraction → Utility classes
- Inheritance → Base classes
- Polymorphism → Browser handling

---

## Key Features

- Hybrid Framework (POM + BDD + JSON)
- Cross Browser Testing
- Parallel Execution
- Logging and Reporting
- Screenshot on Failure
- Scalable Architecture

---

## Future Enhancements

- Retry mechanism
- CI/CD integration (Jenkins)
- Docker support
- Tag-based execution

---

## Authors and Contributions

### Aditya Kopparthi
- Designed and implemented the complete hybrid automation framework architecture (POM + BDD + Data-Driven)
- Developed Driver Factory with cross-browser support (Chrome, Edge) and configuration management
- Implemented parallel execution using TestNG DataProvider
- Built reusable utility layer (ConfigReader, JsonReader, WaitUtils, ScreenshotUtil)
- Integrated Extent Reports and Log4j2 logging for detailed execution reporting
- Implemented screenshot capture mechanism on test failure
- Designed scalable folder structure and framework modularization
- Ensured Maven build integration and execution (mvn clean test)
- Handled synchronization issues using explicit wait strategies
- Overall framework optimization for maintainability and scalability

### Kalyan Sai Ram Akula
- Developed Cucumber feature files for test scenarios
- Implemented step definitions mapped to page objects
- Created and maintained Page Object classes for UI interactions
- Executed test cases and validated results
- Assisted in data-driven testing and test coverage