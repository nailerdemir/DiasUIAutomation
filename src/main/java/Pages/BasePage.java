package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions action;
    protected JavascriptExecutor js;

    protected BasePage(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver,this);
        this.action=new Actions(driver);
        this.js = (JavascriptExecutor) driver;
        System.setProperty("log4j.configurationFile", "src/main/resources/log4j2.xml");
    }

    public String getCurrentUrl(){
        return  driver.getCurrentUrl();
    }

    protected void refreshCurrentUrl(){
        String currentUrl=getCurrentUrl();
        driver.get(currentUrl);
    }

    protected void hoverElement(WebElement element){
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

    protected void scrollTo(WebElement element){
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void switchWindow(){
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String handle:driver.getWindowHandles()){
            driver.switchTo().window(handle);
        }
    }

    protected void waitForPageLoad(){
        wait.until(driver -> js.executeScript("return document.readyState").equals("complete"));
        Boolean isJqueryUsed = (Boolean) ((JavascriptExecutor) driver)
                .executeScript("return (typeof(jQuery) != 'undefined')");
        if (isJqueryUsed) {
            while (true) {
                Boolean ajaxIsComplete = (Boolean) (((JavascriptExecutor) driver)
                        .executeScript("return jQuery.active == 0"));
                if (ajaxIsComplete)
                    break;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    protected Double getDoublePriceFromString(String priceString){
        return Double.parseDouble(priceString.replace(".","")
                .replace(",",".")
                .replace(" TL",""));
    }

    protected Double findHighestPrice(List<WebElement> elements){
        double highestProductPrice=0;
        for (WebElement productPriceStr:elements){
            scrollTo(productPriceStr);
            wait.until(ExpectedConditions.visibilityOf(productPriceStr));
            double productPrice=getDoublePriceFromString(productPriceStr.getText());
            if (productPrice>highestProductPrice){
                highestProductPrice=productPrice;
            }
        }
        return highestProductPrice;
    }
}
