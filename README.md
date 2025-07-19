Design Pattern Where Used and Purpose
1. Page Object Model(LoginPage.java) : Organizes elements and actions for one screen
2. Page Factory(LoginPage.java)      : @FindBy + PageFactory,Initializes WebElements using annotations
3. Singleton(DriverManager.java)	    : Manages a single thread-safe driver instance
4. DataProvider(DriverManager.java)  : Test classes	Supplies test data sets to the same method
5. RetryAnalyzer(RetryAnalyzer.java) : Retries flaky tests automatically

OPPS Concepts used in Framework 
Design Pattern	OOP Concepts Used	Where Applied
Page Object Model : Encapsulation, Abstraction, Inheritance	Page classes, BasePage
Page Factory	      : Encapsulation, Abstraction	@FindBy + Methods
Singleton	      : Encapsulation, Static	DriverManager
DataProvider	      : Abstraction, Polymorphism	Data utility class
RetryAnalyzer     :	Polymorphism, Abstraction, Inheritance	Implements IRetryAnalyzer

📁 Project Root
AppiumAutomation/
│
├── pom.xml
├── testng-android_NativeApp_local.xml
├── testng-android_HybridApp_local.xml
├── testng-android_WebApp_local.xml
├── testng-android_NativeApp_saucelab.xml
├── testng-android_HybridApp_saucelab.xml
├── testng-android_WebApp_saucelab.xml
│
├── Jenkinsfile (optional)
├── extent-config.xml
├── logs/
│   └── automation.log

📂 src/main/java
src/main/java/
└── core/
    ├── base/
    │   ├── BaseTest.java
    │   ├── DriverManager.java
    │   ├── PlatformFactory.java
    │   └── SauceLabManager.java
    ├── config/
    │   └── ConfigLoader.java
    ├── utils/
    │   ├── WaitUtils.java
    │   ├── ScreenshotUtils.java
    │   └── LogUtils.java
    
📂 src/test/java
src/test/java/
└── tests/
    ├── android/
    │   ├── nativeapps/
    │   │   └── LoginTest.java
    │   ├── hybridapps/
    │   │   └── WebViewSwitchTest.java
    │   └── webapps/
    │       └── ChromeSearchTest.java
    │
    ├── ios/
    │   ├── nativeapps/
    │   │   └── LoginTest.java
    │   ├── hybridapps/
    │   │   └── WebViewSwitchTest.java
    │   └── webapps/
    │       └── SafariSearchTest.java
    │
    └── listeners/
        └── TestListener.java
        
📂 src/test/resources
src/test/resources/
├── testdata/
│   └── user_data.json
├── capabilities/
│   ├── android_native.json
│   ├── ios_web.json
│   └── ...
├── extent-config.xml
└── config.properties

🧪 TestNG XML Files
testng-android_NativeApp_local.xml
testng-android_HybridApp_local.xml
testng-android_WebApp_local.xml
testng-android_NativeApp_saucelab.xml
testng-android_HybridApp_saucelab.xml
testng-android_WebApp_saucelab.xml

Need to implement  Dynamic Locator Management using a centralized Object Repository Pattern
Self-Healing Locator a Strategy or a Pattern?
👉 It is primarily a Strategy — and can also be part of a broader Pattern in test automation design.
Feature Purpose
Dynamic Locator Management    : Centralizes and externalizes locator info
Centralized Object Repository : Single source of truth for all locators
Self-Healing Locator Strategy : Automatically recovers from broken locators

