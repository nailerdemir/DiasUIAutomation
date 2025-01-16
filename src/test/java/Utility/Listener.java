package Utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Listener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(Listener.class);

    @Override
    public void onStart(ITestContext context) {
        logger.info("===========================================");
        logger.info("Test Suite Started: " + context.getName());
        logger.info("===========================================");
    }

    @Override
    public void onTestStart(ITestResult result) {
        String scenarioName = getScenarioName(result);
        logger.info("Test Started -> " + scenarioName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String scenarioName = getScenarioName(result);
        logger.info("Test PASSED ✓ -> " + scenarioName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String scenarioName = getScenarioName(result);
        logger.error("Test FAILED ✗ -> " + scenarioName);
        logger.error("Failure Details: " + result.getThrowable());
        
        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("driver");
        if (driver != null) {
            ScreenshotHelper.takeScreenShot(driver, scenarioName);
            logger.info("Screenshot taken for failed test: " + scenarioName);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.warn("Test skipped: " + testName);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.warn("Test failed but within success percentage: " + testName);
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("===========================================");
        logger.info("Test Suite Finished: " + context.getName());
        logger.info("===========================================");
    }

    private String getScenarioName(ITestResult result) {
        try {
            Object[] parameters = result.getParameters();
            if (parameters != null && parameters.length > 0) {
                if (parameters[0] instanceof io.cucumber.testng.PickleWrapper) {
                    io.cucumber.testng.PickleWrapper wrapper = (io.cucumber.testng.PickleWrapper) parameters[0];
                    return wrapper.getPickle().getName();
                }
            }
            return result.getName();
        } catch (Exception e) {
            logger.error("Error getting scenario name: " + e.getMessage());
            return result.getName();
        }
    }
}