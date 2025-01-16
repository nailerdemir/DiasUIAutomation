package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetailPage extends BasePage {
    public ProductDetailPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css = "[data-test-id$='default-price']")
    WebElement productDefaultPrice_Text;

    @FindBy(css = "[data-test-id$='title-area']")
    WebElement productTitle_Text;

    @FindBy(css = "[data-test-id='addToCart']")
    WebElement addToCart_Button;

    @FindBy(css = "[data-test-class$='modal_overflow']")
    WebElement addedToCart_Modal;

    @FindBy(css = "[class*='checkoutui-ProductOnBasketHeader'] button:first-child")
    WebElement goToCartFromModal_Button;



    public String getProductPrice(){
        switchWindow();
        wait.until(ExpectedConditions.visibilityOf(productDefaultPrice_Text));
        return productDefaultPrice_Text.getText();
    }

    public String getProductTitle(){
        return wait.until(ExpectedConditions.visibilityOf(productTitle_Text)).getText();
    }

    public void addProductToCart(){
        wait.until(ExpectedConditions.elementToBeClickable(addToCart_Button)).click();
        wait.until(ExpectedConditions.visibilityOf(addedToCart_Modal));
        wait.until(ExpectedConditions.elementToBeClickable(goToCartFromModal_Button)).click();
    }
}
