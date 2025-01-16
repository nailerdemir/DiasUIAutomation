package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage{
    public CartPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css = "[class*='item_list'] li [class*='product_name']")
    WebElement productName;
    @FindBy(css = "[class*='item_list'] li [class*='pricebox_right']")
    WebElement productPrice;

    private String cartPageUrl="https://checkout.hepsiburada.com/";


    public String getExpectedCartPageUrl(){
        return cartPageUrl;
    }

    public String getProductName(){
        return wait.until(ExpectedConditions.visibilityOf(productName)).getText();
    }

    public String getProductPrice(){
        return wait.until(ExpectedConditions.visibilityOf(productPrice)).getText();
    }
}
