package com.appium.test;

import com.appium.TestData.TestDataProvider;
import com.appium.base.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

public class DependantObjects extends TestBase {


    public DependantObjects() throws IOException {
    }

    @Test(dataProvider = "InputData",dataProviderClass = TestDataProvider.class)
    public void dependantObj(String input) throws IOException, InterruptedException {
        startServer();
        String device = prop.getProperty("Device");
        if (containsIgnoreCase(device,"emulator"))
            startEmulator();
        driver = Initialize(device, prop.getProperty("APIDemos"));
        driver.findElement(By.xpath("//android.widget.TextView[@text ='Preference']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text ='3. Preference dependencies']")).click();
        if(! driver.findElement(By.xpath("//android.widget.TextView[@text = 'WiFi settings']")).isEnabled())
            System.out.println("Wifi Settings Disabled");
        driver.findElement(By.id("android:id/checkbox")).click();
        System.out.println("Clicked on Wifi Checkbox");
       if(driver.findElement(By.xpath("//android.widget.TextView[@text = 'WiFi settings']")).isEnabled())
           System.out.println("Wifi Settings Enabled");
        driver.findElement(By.xpath("//android.widget.TextView[@text = 'WiFi settings']")).click();
        driver.findElement(By.id("android:id/edit")).sendKeys(input);
        driver.findElementById("android:id/button1").click();
        Thread.sleep(1000);
        driver.findElement(By.id("android:id/checkbox")).click();

    }


    @AfterMethod
    public void tearDown(){
        endServer();
    }
}
