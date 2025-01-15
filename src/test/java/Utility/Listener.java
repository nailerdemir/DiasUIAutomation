package Utility;

import Utility.ScreenshotHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;



public class Listener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("driver");

        if (driver != null) {
            ScreenshotHelper.takeScreenShot(driver, result.getName());
        } else {
            System.out.println("Driver is null. Screenshot could not be taken.");
        }
    }

    @Override
    public void onStart(ITestContext context) {}

    @Override
    public void onTestStart(ITestResult result) { }

    @Override
    public void onTestSuccess(ITestResult result) { }

    @Override
    public void onTestSkipped(ITestResult result) { }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) { }

    @Override
    public void onFinish(ITestContext context) { }
}
