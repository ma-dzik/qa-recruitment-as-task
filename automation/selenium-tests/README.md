# Automated UI Tests
This directory contains automated UI tests prepared as part of the recruitment assignment. The tests validate key user flows and UI elements on https://alan-systems.com

## Technology stack
- Java
- Selenium WebDriver
- JUnit
- Explicit waits (implicit waits disabled)

## Test assignment mapping
The automated tests were implemented according to the recruitment assignment and are grouped as follows:
Task 1 – Navigation and common UI elements. The following tests implement Task 1 of the assignment:
- BusinessNavigationTest
- CandidateNavigationTest

These tests validate:
- navigation starting from the Home page,
- correct navigation to Business and Candidate sections,
- visibility of the top navigation menu on each visited page,
- visibility and correctness of footer contact data.
The tests focus on verifying common UI elements that should be consistently visible across the main sections of the website.

Task 2 – Job offer contact form required fields. The following test implements Task 2 of the assignment:
- JobOfferContactFormRequiredFieldsTest

This test validates:
- navigation from the Home page to the Candidate section,
- navigation to the Career page using the top navigation menu,
- opening the job application form using the “Wyślij swoje CV” call-to-action,
- submitting the form without filling required fields,
- blocking of form submission,
- display of validation messages for required fields (CV upload and required checkboxes).

The test verifies user-visible behavior rather than relying on HTML required attributes,
as the form uses custom JavaScript-based validation.

## Project structure
com.example
 ├── components
 │   ├── TopNavComponent
 │   └── FooterComponent
 ├── pages
 │   └── HomePage
 ├── tests
 │   ├── BaseUiTest
 │   ├── BusinessNavigationTest
 │   ├── CandidateNavigationTest
 │   └── JobOfferContactFormRequiredFieldsTest
 └── utils
     └── Logger

## Running automated tests
Run all tests from the com.example.tests package using your IDE or execute the standard Maven command: mvn test
No additional test runners or dependencies were added intentionally to keep the setup minimal.

## Design notes
- Tests are independent and can be executed separately.
- Page Object and Component patterns are used to avoid duplication and improve readability.
- Assertions focus on observable UI behavior rather than internal implementation details.
- Logging was added to make test execution flow easy to follow.

## Notes for reviewers
- During development, short delays (Thread.sleep) were temporarily used to make test execution visible
and easier to follow in the browser.
- In a production-grade test suite, these delays would be removed and replaced by explicit waits only.