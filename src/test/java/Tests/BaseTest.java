import Utility.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void beforeMethod(ITestContext context){
        driver = Driver.getDriver();
        if (driver == null) {
            throw new IllegalStateException("Driver is null.");
        }
        driver.manage().window().maximize();
        context.setAttribute("driver",driver);
    }

    @AfterMethod
    public void afterMethod(){
        Driver.closeDriver();
    }

}
