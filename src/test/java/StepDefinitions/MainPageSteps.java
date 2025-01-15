package StepDefinitions;

import Pages.MainPage;
import Utility.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;


public class MainPageSteps {
    private MainPage mainPage;


    @Given("user is on the main page")
    public void userIsOnTheMainPage(){
        mainPage=new MainPage(Driver.getDriver());
        mainPage.navigateToMainPage();
    }

    @Then("the main page URL should be correct")
    public void theMainPageUrlShouldBeCorrect(){
        Assert.assertEquals(mainPage.getCurrentUrl(),mainPage.getExpectMainPageUrl(),"The expected URL and the current URL are different.");
    }

    @Then("go to tablet category")
    public void goToTabletCategory(){
        mainPage.goToTabletCategory();
        mainPage.sleep(2000);
    }

    @Then("the tablet page URL should be correct")
    public void theTabletPageURLShouldBeCorrect(){
        Assert.assertEquals(mainPage.getCurrentUrl(),mainPage.getExpectTablePageUrl(),"The expected URL and the current URL are different.");
    }
}
