package org.mydemo.driver;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class DriverManager {

    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    private DriverManager() {
        // Prevent external instantiation
    }

    // Set the driver instance (Android or iOS)
    public static void setDriver(AppiumDriver driverInstance) {
        driver.set(driverInstance);
    }

    // Get AppiumDriver (generic)
    public static AppiumDriver getDriver() {
        return driver.get();
    }

    // Get as AndroidDriver if platform is Android
    public static AndroidDriver getAndroidDriver() {
        return (AndroidDriver) driver.get();
    }

    // Get as IOSDriver if platform is iOS
    public static IOSDriver getIOSDriver() {
        return (IOSDriver) driver.get();
    }

    // Remove the driver to avoid memory leaks
    public static void unload() {
        driver.remove();
    }
}