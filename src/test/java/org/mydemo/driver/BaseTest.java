package org.mydemo.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.AppiumDriver;

import org.apache.commons.io.FileUtils;
import org.mydemo.utils.ConfigReader;
import org.testng.annotations.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseTest {
	public AppiumDriverLocalService service;
	protected AppiumDriver driver; // 👈 Static to ensure one shared instance per thread
//	protected FormPage formPage;
//	protected MyCart myCart;
	@Parameters({ "platform" })
	@BeforeClass(alwaysRun = true)
	public void getDriver(@Optional("ANDROID") String platform) throws MalformedURLException {
		
		ConfigReader prop = new ConfigReader(platform);
		String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress")
				: prop.get("ipAddress");
		try {
			UiAutomator2Options options;
			XCUITestOptions options1;
			if (platform.equalsIgnoreCase("ANDROID")) {
				DeviceConfig device = DeviceAllocator.getNextDevice();
				killPortIfInUse(device.port, device.systemPort);
				service = startAppiumServer(ipAddress, device.port);
				options = new UiAutomator2Options();
				options.setDeviceName("emulator"); // emulator
				options.setUdid(device.udid);
				options.setSystemPort(device.systemPort);
				options.setAppWaitActivity("*");
				options.setAppWaitDuration(Duration.ofSeconds(60)); // increase wait time
				// options.setCapability("browserName", "Chrome");
				// options.setCapability("chromedriverExecutable",
				// "//opt//homebrew//bin//chromedriver");
				options.setApp(
						"/Users/kondurikiran/Desktop/CromaCampus/MyDEMOAppAutomation/src/test/resources/resources/MyDemoApp.apk");
				driver = new AndroidDriver(service.getUrl(), options);
				System.out.println("✅ [BaseTest] setup() started with platform: " + platform);
			} else if (platform.equalsIgnoreCase("IOS")) {
				//killPortIfInUse(device.port, device.systemPort);
				service = startAppiumServer(ipAddress, 4723);
				options1 = new XCUITestOptions();
				options1.setDeviceName("iPhone 16 Pro");
				options1.setPlatformVersion("18.3"); // 🔁 Set this based on your simulator version
				// options1.setUdid(device.udid); // ✅ Optional for named simulator
				options1.setApp(
						"/Users/kondurikiran/Desktop/CromaCampus/MyDEMOAppAutomation/src/test/resources/resources/Payload/MyDemoApp.app");

				driver = new IOSDriver(service.getUrl(), options1);
				System.out.println("✅ [BaseTest] setup() started with platform: " + platform);
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			DriverManager.setDriver(driver);
		} catch (Exception e) {
			System.err.println("❌ [BaseTest] Failed to start driver for platform: " + platform);
			e.printStackTrace();
			throw new RuntimeException("Driver setup failed: " + e.getMessage());
		}
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver = DriverManager.getDriver();
		if (driver != null) {
			driver.quit();
			DriverManager.unload();
		}
	}

	public static void killPortIfInUse(int port, int systemPort) {
		try {
			Process process = Runtime.getRuntime().exec("/usr/sbin/lsof -t -i:" + port);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String pid;
			while ((pid = reader.readLine()) != null) {
				Runtime.getRuntime().exec("kill -9 " + pid);
				System.out.println("Killed process on port " + port + " (PID: " + pid + ")");
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			System.out.println("No process to kill or error: " + e.getMessage());
		}
	}

	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
		// ✅ Set ANDROID_HOME and ANDROID_SDK_ROOT environment variables
		Map<String, String> env = new HashMap<>(System.getenv());
		env.put("ANDROID_HOME", "/Users/kondurikiran/Library/Android/sdk");
		env.put("ANDROID_SDK_ROOT", "/Users/kondurikiran/Library/Android/sdk");

		service = new AppiumServiceBuilder().withAppiumJS(new File("/opt/homebrew/bin/appium")) // Appium CLI path
				.usingDriverExecutable(new File("/opt/homebrew/bin/node")) // Node.js path
				.withEnvironment(env).withArgument(() -> "--log-level", "error").withIPAddress(ipAddress)
				.usingPort(port).build();
		service.start();
		System.out.println("✅ Appium Server was started");
		return service;

	}

	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
		// System.getProperty("user.dir")+"//src//test//java//org//rahulshettyacademy//testData//eCommerce.json"
		// conver json file content to json string
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;

	}
}
