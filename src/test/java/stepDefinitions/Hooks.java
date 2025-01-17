package stepDefinitions;

import utility.Driver;
import io.cucumber.java.After;

public class Hooks {

    @After
    public void tearDown() {
        Driver.closeDriver();
    }
}