package stepDefinitions;

import Pages.CartPage;
import Pages.CategoryPage;
import Pages.MainPage;
import Pages.ProductDetailPage;
import utility.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static Pages.BasePage.sleep;

public class AddToCartSteps {
    private MainPage mainPage;
    private CategoryPage categoryPage;
    private ProductDetailPage productDetailPage;
    private CartPage cartPage;

    private static final Logger logger = LogManager.getLogger(AddToCartSteps.class);

    public String productPriceStr;
    public String productTitle;

    @Given("the user is on the main page")
    public void userIsOnMainPage() {
        logger.info("Ana sayfaya gidiliyor...");
        mainPage = new MainPage(Driver.getDriver());
        mainPage.navigateToMainPage();
        logger.info("Ana sayfa başarıyla yüklendi.");
    }

    @Given("the main page URL is verified to be correct")
    public void verifyMainPageUrl() {
        logger.info("Ana sayfa URL'si doğrulanıyor...");
        Assert.assertEquals(mainPage.getCurrentUrl(), mainPage.getExpectMainPageUrl(),
                "Beklenen URL ile mevcut URL farklı.");
        logger.info("Ana sayfa URL'si başarıyla doğrulandı.");
    }

    @When("the user navigates to the tablet category")
    public void navigateToTabletCategory() {
        logger.info("Tablet kategorisine gidiliyor...");
        mainPage.goToTabletCategory();
        sleep(2000);
        logger.info("Tablet kategorisine başarıyla gidildi.");
    }

    @When("the tablet category page URL is verified to be correct")
    public void verifyTabletCategoryPageUrl() {
        logger.info("Tablet kategorisi sayfasının URL'si doğrulanıyor...");
        Assert.assertEquals(mainPage.getCurrentUrl(), mainPage.getExpectTablePageUrl(),
                "Beklenen URL ile mevcut URL farklı.");
        logger.info("Tablet kategorisi sayfasının URL'si başarıyla doğrulandı.");
    }

    @When("the user filters the products by 'Apple' brand and '13.2 inch' screen size")
    public void filterProductsByBrandAndSize() {
        logger.info("Ürünler 'Apple' markası ve '13,2 inç' ekran boyutu ile filtreleniyor...");
        categoryPage = new CategoryPage(Driver.getDriver());
        categoryPage.selectBrandFromFilter("Apple");
        categoryPage.selectScreenSizeFromFilter("13,2");
        Assert.assertTrue(categoryPage.checkSelectedFilter("Apple"),
                "Seçilen marka filtresi doğru uygulanmadı.");
        Assert.assertTrue(categoryPage.checkSelectedFilter("13,2 inç"),
                "Seçilen ekran boyutu filtresi doğru uygulanmadı.");
        logger.info("Ürünler başarıyla filtrelendi.");
    }

    @When("the user selects the highest priced tablet")
    public void selectHighestPricedTablet() {
        logger.info("En yüksek fiyatlı tablet seçiliyor...");
        categoryPage.selectHighestPriceProduct();
        logger.info("En yüksek fiyatlı tablet başarıyla seçildi.");
    }

    @When("the user clicks the 'Add to Cart' button")
    public void clickAddToCartButton() {
        logger.info("'Sepete Ekle' butonuna tıklanıyor...");
        productDetailPage = new ProductDetailPage(Driver.getDriver());
        productPriceStr = productDetailPage.getProductPrice();
        productTitle = productDetailPage.getProductTitle();
        productDetailPage.addProductToCart();
        logger.info("Ürün başarıyla sepete eklendi.");
    }

    @When("the user is redirected to the cart page")
    public void verifyRedirectToCartPage() {
        logger.info("Sepet sayfasına yönlendiriliyor...");
        cartPage = new CartPage(Driver.getDriver());
        Assert.assertEquals(cartPage.getCurrentUrl(), cartPage.getExpectedCartPageUrl(),
                "Beklenen URL ile mevcut URL farklı.");
        logger.info("Sepet sayfasına başarıyla yönlendirildi.");
    }

    @Then("the product should be successfully added to the cart")
    public void verifyProductAddedToCart() {
        logger.info("Ürünün sepete başarıyla eklendiği doğrulanıyor...");
        Assert.assertEquals(cartPage.getProductName(), productTitle,
                "Eklenen ürünün adı ile sepetteki ürünün adı farklı.");
        logger.info("Ürün başarıyla sepete eklendi.");
    }

    @Then("the product price on the cart page should match the price on the product detail page")
    public void verifyCartPagePriceMatchesProductPage() {
        logger.info("Sepet sayfasındaki ürün fiyatının ürün detay sayfasındaki fiyatla eşleştiği doğrulanıyor...");
        Assert.assertEquals(cartPage.getProductPrice(), productPriceStr,
                "Eklenen ürünün fiyatı ile sepetteki ürünün fiyatı farklı.");
        logger.info("Ürün fiyatları başarıyla eşleştirildi.");
    }
}