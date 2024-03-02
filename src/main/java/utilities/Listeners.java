package utilities;

import com.google.common.util.concurrent.Uninterruptibles;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class Listeners extends CommonOps implements ITestListener {

    // Method Name: onStart
    // Method Description: This method is executed before the entire test suite starts execution.
    // It prints a message indicating the start of the execution.
    // Method Parameters: ITestContext execution - Information about the entire test execution context.
    public void onStart(ITestContext execution) {

        System.out.println("------------ Starting Execution ------------");

    }

    // Method Name: onFinish
    // Method Description: This method is executed after the entire test suite has finished execution.
    // It prints a message indicating the end of the execution.
    // Method Parameters: ITestContext execution - Information about the entire test execution context.
    public void onFinish(ITestContext execution) {

        System.out.println("------------ Execution Ended ------------");
    }

    // Method Name: onTestFailedButWithinSuccessPercentage
    // Method Description: This method is executed when a test method has failed but is within success percentage.
    // It is not currently implemented and marked as TODO.
    // Method Parameters: ITestResult arg0 - Information about the test result.
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        // TODO Auto-generated method stub
    }

    // Method Name: onTestSkipped
    // Method Description: This method is executed when a test method is skipped.
    // It prints a message indicating the skipped test.
    // Method Parameters: ITestResult test - Information about the skipped test.
    public void onTestSkipped(ITestResult test) {
        System.out.println
                ("------------ Skipping Test: " + test.getName() + " ------------");
    }

    // Method Name : onTestStart
    // Method Description: This method is executed when a test starts.
    // Method Parameters: ITestResult test - Information about the test that is starting.
    // Print a message indicating the start of the test
    public void onTestStart(ITestResult test) {
        System.out.println
                ("------------ Starting Test: " + test.getName() + " ------------");
    }

    // Method Name : onTestSuccess
    // Method Description: This method is executed when a test method passes. if platform is not api it delete the video file of the run
    // Method Parameters: ITestResult test - Information about the passed test.
    public void onTestSuccess(ITestResult test) {
        if (!platform.equalsIgnoreCase("api")) {
            System.out.println
                    ("------------ Test: " + test.getName() + " Passed ------------");

            // Stop Monte Test recording

            try {
                MonteScreenRecorder.stopRecord();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // Delete video file
            File file = new File("./test-recordings/" + test.getName() + ".avi");
            if (file.delete())
                System.out.println("Video file Deleted Successfully");
            else
                System.out.println("Failed to delete video file");
        }
    }

    // Method Name : onTestFailure
    // Method Description: This method is executed when a test method fails.
    // taking a screenshot and stop video record if platform is not api
    // Method Parameters: ITestResult test - Information about the failed test.
    public void onTestFailure(ITestResult test) {
        if (!platform.equalsIgnoreCase("api")) {

            Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
            System.out.println("------------ Test: " + test.getName() + " Failed ------------");

            // Stop Monte Test recording

            try {
                MonteScreenRecorder.stopRecord();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("Taking Screenshot...");

            // Take Screenshot
            saveScreenshot();

        }
    }

    // Method Name: saveScreenshot
    // Method Description: This method captures a screenshot of the current page.
    // If the platform is not mobile, it uses the 'driver' object to capture the screenshot.
    // If the platform is mobile, it uses the 'mobileDriver' object to capture the screenshot.
    // Return Value: A byte representing the screenshot in PNG format.
    @Attachment(value = "Page Screen-Shot", type = "image/png")
    public byte[] saveScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }


}

