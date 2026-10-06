package hooks;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class CucumberHooks extends BaseTest {

    @Before("@web")
    public void beforeScenario() {
        setUpDriver();
    }

    @After("@web")
    public void afterScenario() {
        tearDownDriver();
    }
}