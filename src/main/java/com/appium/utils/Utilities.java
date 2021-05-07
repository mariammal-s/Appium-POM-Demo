package com.appium.utils;

import com.appium.base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

public class Utilities extends TestBase {

    static ServerSocket socket;

    public Utilities() throws IOException {
    }

    public static boolean isServerRunningCheck(int port) {
        Boolean isServerRunning = false;
        try {
            socket = new ServerSocket(port);
            socket.close();
        } catch (IOException e) {
            isServerRunning = true;
            e.printStackTrace();
        } finally {
            socket = null;
        }
        return isServerRunning;
    }

    public static void getscreenshot(String fileName) throws Exception {
        TakesScreenshot takescreenshot= (TakesScreenshot)driver;
        File srcFile = takescreenshot.getScreenshotAs(OutputType.FILE);
        String currentDir = System.getProperty("user.dir");
        String OS = System.getProperty("os.name").toLowerCase();

            try {
                FileUtils.copyFile(srcFile, new File(currentDir + "/Screenshots/" + fileName + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
