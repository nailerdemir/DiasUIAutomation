package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions action;

    public BasePage(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver,this);
        this.action=new Actions(driver);
    }

    public String getCurrentUrl(){
        return  driver.getCurrentUrl();
    }

    public void hoverElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        action.moveToElement(element).build().perform();
    }

    public static void sleep(long time){
    try {
        Thread.sleep(time);
    }
    catch (InterruptedException e) {
        e.printStackTrace();
    }
    }
}
