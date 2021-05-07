package com.appium.PageObjects;

import com.appium.base.TestBase;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class CheckOutPage extends TestBase {

    public CheckOutPage() throws IOException {
        super();
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    public List<WebElement> productPrice;


}
