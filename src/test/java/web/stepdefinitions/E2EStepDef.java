package web.stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import web.pages.CartPage;
import web.pages.CheckoutPage;
import web.pages.HomePage;
import web.pages.LoginPage;

public class E2EStepDef extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @Given("user logs in with valid credentials")
    public void userLogsInWithValidCredentials() {

        loginPage = new LoginPage(driver);

        loginPage.openLoginPage();

        loginPage.inputUsername("standard_user");

        loginPage.inputPassword("secret_sauce");

        loginPage.clickLoginButton();

        homePage = new HomePage(driver);

        homePage.validateOnHomepage();
    }

    @When("user adds Sauce Labs Backpack to cart")
    public void userAddsSauceLabsBackpackToCart() {

        homePage.addBackpackToCart();
    }

    @When("user opens the shopping cart")
    public void userOpensTheShoppingCart() {

        homePage.openShoppingCart();

        cartPage = new CartPage(driver);
    }

    @Then("shopping cart should contain {string}")
    public void shoppingCartShouldContain(String productName) {

        cartPage.validateProductInCart(productName);
    }

    @When("user proceeds to checkout")
    public void userProceedsToCheckout() {

        cartPage.clickCheckout();

        checkoutPage = new CheckoutPage(driver);
    }

    @When("user enters checkout information")
    public void userEntersCheckoutInformation() {

        checkoutPage.fillCustomerInformation(
                "Ali",
                "Automation",
                "12345"
        );

        checkoutPage.clickContinue();
    }

    @When("user completes the order")
    public void userCompletesTheOrder() {

        checkoutPage.clickFinish();
    }

    @Then("order confirmation should be displayed")
    public void orderConfirmationShouldBeDisplayed() {

        checkoutPage.validateOrderCompleted();
    }
}