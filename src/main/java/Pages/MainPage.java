package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".sf-voltran-body.voltran-body.full.NavigationDesktop div div div div div div ul li")
    public List<WebElement> mainCategories;

    private final String mainPageUrl="https://www.hepsiburada.com/";
    private final String tabletPageUrl="https://www.hepsiburada.com/tablet-c-3008012";



    public void navigateToMainPage(){
        driver.get(mainPageUrl);
    }

    public String getExpectMainPageUrl() {
        return mainPageUrl;
    }
    public String getExpectTablePageUrl() {
        return tabletPageUrl;
    }

    public void goToTabletCategory(){
        WebElement mainCategory=goToCategory(mainCategories,"Elektronik");
        hoverElement(mainCategory);
        List<WebElement> firstChildCategories=mainCategory.findElements(By.cssSelector(" div div div div ul li"));

        WebElement firstChildCategory=goToCategory(firstChildCategories,"Bilgisayar/Tablet");
        hoverElement(firstChildCategory);
        List<WebElement> secondChildCategories=firstChildCategory.findElements(By.cssSelector(" div ul li ul li a"));

        WebElement secondChildCategory=goToCategory(secondChildCategories,"Tablet");
        wait.until(ExpectedConditions.elementToBeClickable(secondChildCategory)).click();
    }

    public WebElement goToCategory(List<WebElement> childCategories ,String category){
        if (childCategories == null) {
            throw new IllegalStateException("childCategories is null.");
        }
        for (WebElement element:childCategories){
            wait.until(ExpectedConditions.visibilityOf(element));
            if (element != null && element.getText().trim().equalsIgnoreCase(category)){
                return element;
            }
        }
        System.out.println("No match found");
        return null;
    }
}