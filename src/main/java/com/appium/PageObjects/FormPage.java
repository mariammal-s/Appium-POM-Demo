package com.appium.PageObjects;

import com.appium.base.TestBase;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class FormPage extends TestBase {

    public FormPage() throws IOException {
        super();
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    public WebElement nameField;

    @AndroidFindBy(className = "android.widget.Button")
    public WebElement proceedButton;

    public void sendValuesToName(String name) {
        nameField.sendKeys(name);
    }

    public void proceedToNext(){
        proceedButton.click();
    }


}
