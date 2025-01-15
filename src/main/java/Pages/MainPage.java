package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".sf-voltran-body.voltran-body.full.NavigationDesktop div div div div div div ul li")
    public List<WebElement> electronicChildCategories;

    private final String mainPageUrl="https://www.hepsiburada.com/";



    public void navigateToMainPage(){
        driver.get(mainPageUrl);
    }

    public String getExpectUrl() {
        return mainPageUrl;
    }

    public void goToCategory(){
        for (WebElement element:electronicChildCategories){
            if (element.getText().equals(""));
        }

    }
}