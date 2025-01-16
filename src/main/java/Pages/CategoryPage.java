package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CategoryPage extends BasePage{
    public CategoryPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css = "div[id='markalar'] div[data-test-id='filterbox-content-children'] div:nth-child(2) div label")
    List<WebElement> brandFilters;

    @FindBy(css = "div[id='ekranboyutu'] div[data-test-id='filterbox-content-children'] div:nth-child(2) div label")
    List<WebElement> screenSizeFilters;

    @FindBy(css = "div[id='appliedVerticalFilterBar'] div[data-test-id$='filterbox-content-children'] div div button")
    List<WebElement> selectedFilters;

    @FindBy(css = "[class*='moria-ProductCard'] div[data-test-id$='price-current-price']")
    List<WebElement> productPrices_Text;



    public void selectBrandFromFilter(String brandName){
        scrollTo(brandFilters.get(0));
        wait.until(ExpectedConditions.visibilityOf(brandFilters.get(0)));
        for (WebElement brand:brandFilters){
            if (brand.getText().equals(brandName)){
                wait.until(ExpectedConditions.elementToBeClickable(brand)).click();
                refreshCurrentUrl(); //CORS Hatasından kurtulmak için eklendi.
                waitForPageLoad();
                break;
            }
        }
    }

    public void selectScreenSizeFromFilter(String screenSizeToChoose){
        scrollTo(screenSizeFilters.get(0));
        wait.until(ExpectedConditions.visibilityOf(screenSizeFilters.get(0)));
        for (WebElement screenSize:screenSizeFilters){
            if (screenSize.getText().contains(screenSizeToChoose)){
                scrollTo(screenSize);
                WebElement screenSizeCheckbox=screenSize.findElement(By.cssSelector(" input"));
                wait.until(ExpectedConditions.elementToBeClickable(screenSizeCheckbox)).click();
                refreshCurrentUrl(); //CORS Hatasından kurtulmak için eklendi.
                waitForPageLoad();
                break;
            }
        }
    }

    public boolean checkSelectedFilter(String selectedFilterName){
        wait.until(ExpectedConditions.visibilityOf(selectedFilters.get(0)));
        scrollTo(selectedFilters.get(0));
        for (WebElement selectedFilter:selectedFilters){
            if (selectedFilter.getText().contains(selectedFilterName)){
                return true;
            }
        }
        return false;
    }

    public void selectHighestPriceProduct(){
        wait.until(ExpectedConditions.visibilityOf(productPrices_Text.get(0)));
        double highestPrice= findHighestPrice(productPrices_Text);
        for (WebElement productPriceStr: productPrices_Text){
            double productPrice=getDoublePriceFromString(productPriceStr.getText());
            if (productPrice==highestPrice){
                productPriceStr.click();
                break;
            }
        }
    }


}
