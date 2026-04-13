# 🚀 Precision UI BDD Automation Framework

## 🔹 Overview
This project is a **Hybrid Test Automation Framework** built using:

- Java
- Selenium WebDriver
- Cucumber (BDD)
- TestNG
- Maven

The framework automates key user scenarios on Amazon and is designed to be **scalable, maintainable, reusable, and robust**.

---

## 🎯 Objective
- Implement **Page Object Model (POM)**
- Use **BDD with Cucumber**
- Support **Data-Driven Testing (JSON + Parameterization)**
- Enable **Cross-Browser Execution**
- Generate **Advanced Reports (Extent Reports)**
- Support **Parallel Execution**
- Maintain **Clean and Modular Architecture**

---

## 🏗️ Framework Architecture


Test Runner (TestNG)
↓
Feature Files (BDD Scenarios)
↓
Cucumber Engine
↓
Hooks (@Before / @After)
↓
Step Definitions (Test Logic)
↓
Page Object Layer (POM)
↓
Driver Factory (Thread-safe driver handling)
↓
Browser (Chrome / Edge)

Utilities Layer
(ConfigReader | JsonReader | WaitUtil | ScreenshotUtil | ExtentManager)
Reporting Layer
(Extent Reports integrated via Hooks)

---

## 📁 Project Structure


src
├── main/java
│ ├── factory → DriverFactory
│ ├── pages → Page Objects
│ ├── utils → Utilities
│
├── test/java
│ ├── stepdefinitions
│ ├── runners
│ ├── hooks
│
├── test/resources
│ ├── features
│ │ ├── search_json.feature
│ │ ├── search_parameterized.feature
│ │ └── add_to_cart.feature
│ ├── testdata
│ │ └── searchData.json
│
logs/
screenshots/
test-output/
└── ExtentReports/


---

## 🧪 Test Scenarios

### 🔍 Search Product (JSON Data Driven)
- Open Amazon homepage
- Read product from JSON file
- Search product
- Validate results

---

### 🔍 Search Product (Parameterized Scenario)


Scenario Outline: Search product
Given user is on homepage
When user searches for "<product>"
Then results should be displayed

Examples:
| product |
| iPhone |
| Laptop |


---

### 🛒 Add to Cart
- Navigate to category
- Select product
- Add to cart
- Validate cart

---

## 🔄 Data-Driven Testing

### 🔹 JSON-Based


src/test/resources/testdata/searchData.json


```json
{
  "product": "iPhone"
}
String product = JsonReader.getData("product");
🔹 Parameterization
Uses Scenario Outline
Data passed via feature file
✅ Advantage

Supports both:

External data (JSON)
Inline BDD parameterization
🛠️ Utilities
Utility	Description
ConfigReader	Reads config
JsonReader	Reads JSON data
WaitUtil	Explicit waits
ScreenshotUtil	Failure screenshots
ExtentManager	Reporting
DriverFactory	Driver handling
🌐 Cross Browser Support
browser=chrome

Supported:

Chrome
Edge
⚡ Parallel Execution
@DataProvider(parallel = true)
public Object[][] scenarios() {
    return super.scenarios();
}
📊 Reporting
Extent Reports
Integrated via Hooks
Managed using ExtentManager
Includes screenshots
Log4j2
Logs stored in /logs
▶️ Execution
mvn clean test
📂 Reports
Extent → test-output/ExtentReports/
Screenshots → /screenshots
Logs → /logs
🧠 OOP Concepts
Encapsulation
Abstraction
Inheritance
Polymorphism
⭐ Key Features
Hybrid (POM + BDD + Data-Driven)
JSON + Parameterization
Parallel Execution
Extent Reports
Screenshot on Failure
Scalable Design
🚀 Future Enhancements
Retry mechanism
Jenkins CI/CD
Docker
Selenium Grid
👨‍💻 Authors
Aditya Kopparthi
Framework design
DriverFactory
Utilities
Reporting
Parallel execution
Kalyan Sai Ram Akula
Feature files
Step definitions
Page objects
💡 Interview Tip

"I implemented data-driven testing using both Cucumber parameterization and external JSON files."


---


Just tell me 👍