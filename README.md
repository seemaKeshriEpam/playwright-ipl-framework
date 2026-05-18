## Playwright ##
This is a modern UI test automation framework built using Playwright with Java and Cucumber (BDD), integrated with Allure Reports for detailed test reporting.

The framework follows scalable automation principles:

**Behavior Driven Development (BDD)
Page Object Model (POM) design pattern
Cross-browser execution (Chromium, Firefox, WebKit)
Reusable utilities and hooks
Allure reporting for test insights**

**🛠️ Tech Stack**
Playwright
Cucumber (BDD)
Java
Allure Reports
Git & GitHub

**⏱️ Playwright Auto-Wait Mechanism**

Playwright automatically waits for elements before performing actions. Here's how the auto-wait works internally:

```
Start timer

while (current_time < timeout) {

    try to find element

    if found:
        ✓ check visibility
        ✓ check stability
        ✓ check enabled
        ✓ check receivable (receives pointer events)

        if all checks pass:
            perform action
            ✅ SUCCESS

    retry again
}

❌ throw TimeoutError
```

This eliminates the need for explicit waits like `Thread.sleep()` or custom wait utilities — Playwright handles it automatically!

**📁 Project Structure**

src
 ├── pages              # Page Object Model classes
 ├── runner             # Test runner configuration
 ├── stepDefinitions    # Cucumber step definitions
 ├── hooks              # Before/After hooks (setup & teardown)
 ├── utils              # Reusable helpers (waits, data, config)
features
 ├── *.feature          # Gherkin feature files


🚀 Installation & Setup
1️⃣ Clone repository
git clone <your-repo-url>
cd <your-project-folder>
2️⃣ Install dependencies
npm install
3️⃣ Install Playwright browsers
npx playwright install
▶️ Running Tests
Run all tests
npx cucumber-js
Run specific feature
npx cucumber-js features/login.feature

**🌐 Cross Browser Execution**

Playwright supports multiple browsers:

**Chromium**
BROWSER=chromium npx cucumber-js

**Firefox**
BROWSER=firefox npx cucumber-js

**WebKit**
BROWSER=webkit npx cucumber-js

**📊Allure Report**
Generate results
npx allure generate allure-results --clean -o allure-report
Open report
npx allure open allure-report
🧪 Sample Feature File
Feature: IPL Website Validation

**👨‍💻 Author**

Seema Keshri
Aastha Sinha

**🚀 Future Enhancements**
Parallel execution support
API testing integration with Playwright API
Dockerized execution environment
Retry mechanism for flaky tests



