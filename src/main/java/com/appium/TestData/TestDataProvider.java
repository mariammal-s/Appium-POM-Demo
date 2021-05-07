package com.appium.TestData;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "InputData")
    public Object[][] getDataForField(){
        Object[][] obj = new Object[][]{
                {"Hello"},{"@#$%^"}
        };
        return obj;
    }

}
