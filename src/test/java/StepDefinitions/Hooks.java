package StepDefinitions;

import Utility.Driver;
import io.cucumber.java.After;

public class Hooks { // Bu sınıfı StepDefinitions paketine ekleyin

    @After // io.cucumber.java.After import edin
    public void tearDown() {
        Driver.closeDriver();
    }
}