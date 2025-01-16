package StepDefinitions;

import Pages.CartPage;
import Pages.CategoryPage;
import Pages.MainPage;
import Pages.ProductDetailPage;
import Utility.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static Pages.BasePage.sleep;


public class AddToCartSteps {
    private MainPage mainPage;
    private CategoryPage categoryPage;
    private ProductDetailPage productDetailPage;
    private CartPage cartPage;


    public String productPriceStr;
    public String productTitle;



    @Given("the user is on the main page")
    public void userIsOnMainPage(){
        mainPage=new MainPage(Driver.getDriver());
        mainPage.navigateToMainPage();
    }

    @Given("the main page URL is verified to be correct")
    public void verifyMainPageUrl(){
        Assert.assertEquals(mainPage.getCurrentUrl(),mainPage.getExpectMainPageUrl(),
                "The expected URL and the current URL are different.");
    }

    @When("the user navigates to the tablet category")
    public void navigateToTabletCategory(){
        mainPage.goToTabletCategory();
        sleep(2000);
    }

    @When("the tablet category page URL is verified to be correct")
    public void verifyTabletCategoryPageUrl(){
        Assert.assertEquals(mainPage.getCurrentUrl(),mainPage.getExpectTablePageUrl(),
                "The expected URL and the current URL are different.");
    }

    @When("the user filters the products by 'Apple' brand and '13.2 inch' screen size")
    public void filterProductsByBrandAndSize(){

        categoryPage=new CategoryPage(Driver.getDriver());
        categoryPage.selectBrandFromFilter("Apple");
        categoryPage.selectScreenSizeFromFilter("13,2");
        Assert.assertTrue(categoryPage.checkSelectedFilter("Apple"),
                "Selected brand filter is not applied correctly.");
        Assert.assertTrue(categoryPage.checkSelectedFilter("13,2 inç"),
                "Selected screen size filter is not applied correctly.");
    }

    @When("the user selects the highest priced tablet")
    public void selectHighestPricedTablet(){
        categoryPage.selectHighestPriceProduct();
    }

    @When("the user clicks the 'Add to Cart' button")
    public void clickAddToCartButton(){
        productDetailPage=new ProductDetailPage(Driver.getDriver());
        productPriceStr=productDetailPage.getProductPrice();
        productTitle= productDetailPage.getProductTitle();
        productDetailPage.addProductToCart();
    }

    @When("the user is redirected to the cart page")
    public void verifyRedirectToCartPage(){
        cartPage=new CartPage(Driver.getDriver());
        Assert.assertEquals(cartPage.getCurrentUrl(),cartPage.getExpectedCartPageUrl(),
                "The expected URL and the current URL are different.");
    }

    @Then("the product should be successfully added to the cart")
    public void verifyProductAddedToCart(){
        Assert.assertEquals(cartPage.getProductName(),productTitle,
                "The names of the added product and the product in the cart are different.");
    }

    @Then("the product price on the cart page should match the price on the product detail page")
    public void verifyCartPagePriceMatchesProductPage() {
        Assert.assertEquals(cartPage.getProductPrice(), productPriceStr,
                "The price of the added product is different from the price of the product in the cart.");
    }
}
