package com.appium.test;

import com.appium.PageObjects.CheckOutPage;
import com.appium.PageObjects.FormPage;
import com.appium.base.TestBase;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

public class CheckOutAmountCheck extends TestBase {

    FormPage fp;
    CheckOutPage cp;


    public CheckOutAmountCheck() throws IOException {
    }

    @BeforeTest
    public void killAllNode() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("killall node");
        Thread.sleep(3000);
        fp = new FormPage();
        cp = new CheckOutPage();
    }


    @Test
    public void amountCheck() throws IOException, InterruptedException {
        startServer();
        String device = prop.getProperty("Device");
        if (containsIgnoreCase(device,"emulator"))
            startEmulator();
        driver = Initialize(device, prop.getProperty("GeneralStoreApp"));
        Thread.sleep(1000);
        fp.sendValuesToName("Hello");
        driver.hideKeyboard();
        fp.proceedToNext();
        Thread.sleep(1000);
        List<WebElement> productList = driver.findElementsById("com.androidsample.generalstore:id/productAddCart");
        for (WebElement e : productList) {
            e.click();
        }
        driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();
        Thread.sleep(1000);
        double totalAmount = 0.0 ;
        for (WebElement e : cp.productPrice) {
            String price = e.getText();
            Double amount = getAmount(price);
            totalAmount += amount;
        }
        String amount = driver.findElementById("com.androidsample.generalstore:id/totalAmountLbl").getText();
        double actualAmount = getAmount(amount);

        Assert.assertEquals(totalAmount,actualAmount);
        System.out.println("Amount matches");


        driver.findElementByClassName("android.widget.CheckBox").click();

        TouchAction touch = new TouchAction(driver);
        WebElement termsC = driver.findElementByXPath("//*[@text = 'Please read our terms of conditions']");
        touch.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(termsC)).withDuration(Duration.ofSeconds(2))).release().perform();
        driver.findElementById("android:id/button1").click();
        driver.findElementById("com.androidsample.generalstore:id/btnProceed").click();
        driver.pressKeyCode(AndroidKeyCode.BACK);

    }

    //Removes Dollar symbol and returns double value
    public static double getAmount(String amount) {
        String newAmount = amount.replaceAll("\\$","");
        double actualAmount = Double.parseDouble(newAmount);
        return actualAmount;
    }

    @AfterMethod
    public void tearDown(){
        endServer();
    }

}
