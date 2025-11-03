# service-nsw-automation-full
Maven Java automation for Service NSW stamp duty GUI (Selenium + Cucumber) and OpenLibrary API (RestAssured + Cucumber).

## Features
- UI tests using Selenium (headless Chrome)
- API tests using RestAssured
- Cucumber BDD feature files
- ExtentReports HTML report (target/extent-reports/ExtentReport.html)
- Cucumber HTML report (target/cucumber-reports/cucumber.html)
- GitHub Actions workflow

## Run tests locally
1. Ensure Java 17 and Maven are installed.
2. Chrome & chromedriver available on PATH (or run GUI tests in CI where workflow installs Chrome).
3. Run tests:
   mvn test
