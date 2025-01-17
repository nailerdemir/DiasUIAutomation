package utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotHelper {

    public static void takeScreenShot(WebDriver driver, String testName){
        File scrFile=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String time=new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
        String screenshotName=testName +"_"+time+".png";


        try {
            FileUtils.copyFile(scrFile,new File("screenshots/"+screenshotName));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}