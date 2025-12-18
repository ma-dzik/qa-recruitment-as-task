# QA Recruitment Tasks

This repository contains solutions for QA recruitment tasks, divided into:
- **Manual testing tasks**
- **Automation testing tasks (Java + Selenium)**
This repository contains solutions for QA recruitment tasks and is intended to reflect my skills in both manual and automated testing.
The manual part focuses on test analysis, test case design and defect reporting, while the automation part presents basic UI test implementation using Java and Selenium.

---

## Repository structure

├── manual/
│ ├── Zadanie1/
│ ├── Zadanie2/
│ ├── Zadanie3/
│ └── Zadanie4/
│
├── automation/
│ └── selenium-tests/
│
└── docs/
│ └── assets/

---

## Manual testing

All manual testing tasks are located in the `manual/` directory.
Each task contains:
- short task description
- test cases or bug reports
- assumptions and testing approach

---

## Automation testing

Automated tests are implemented in: `automation/selenium-tests` directory.

### Tech stack
- Java 21
- Maven
- Selenium WebDriver
- JUnit 5
- WebDriverManager

### How to run automated tests

```bash
cd automation/selenium-tests
mvn test