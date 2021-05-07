package com.appium.base;

import com.appium.utils.Utilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static AndroidDriver<WebElement> driver;
    public static Properties prop;
    public static AppiumDriverLocalService service;
    public static AppiumServiceBuilder build;

    public TestBase() throws IOException {
        FileInputStream propertyFile = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/appium/prop/global.properties");
        prop = new Properties();
        prop.load(propertyFile);

    }

    public static void startServer(){
        if(!Utilities.isServerRunningCheck(4723)) {
            build = new AppiumServiceBuilder();
            build.usingPort(4723);
            service = AppiumDriverLocalService.buildService(build);
            service.start();
        }
    }

    public static void endServer(){
        service.stop();
    }

    public static void startEmulator() throws IOException, InterruptedException {
        Runtime.getRuntime().exec(System.getProperty("user.dir")+"/src/main/java/com/appium/resource/startEmulator.sh");
        Thread.sleep(10000);
    }

    public static AndroidDriver<WebElement> Initialize(String device,String appName) throws IOException {


        File appDir = new File("app");
        File appEcommerce = new File(appDir,appName);

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME,device);

        cap.setCapability(MobileCapabilityType.DEVICE_NAME,"Android Device");
        cap.setCapability(MobileCapabilityType.APP , appEcommerce.getAbsolutePath());
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "20");
        cap.setCapability("platformName", "Android");
        cap.setCapability("noReset","true");

        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver;
    }
}
