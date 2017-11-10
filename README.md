# computers

Hello,

For this auto tests I used JVM-Cucumber, Selenium WD, Java.

- Gherkin scenarios can be found at: /src/test/java/features/CrudOperations.feature
- Runner is at: /src/test/java/runner/TestRunner.java
- Scenarios steps are executed at: /src/test/java/com/bestbuy/qa/steps/CrudOperations.java

Other parts of code are grouped inside packages: helpers, resources, utils.
Test report can be found at: target/cucumber-report/index.html 
All dependencies are listed in pom.xml

Test set can be run in one of three ways:

- Run "TestRunner"
- Run "CrudOperations.feature"
- mvn clean test

Manual Tests are in folder /src/test/java/ManualTests/Test Plan.docx

