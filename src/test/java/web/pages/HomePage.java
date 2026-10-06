package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By productTitle =
            By.xpath("//*[@id='item_4_title_link']/div");

    private final By addBackpackButton =
            By.id("add-to-cart-sauce-labs-backpack");

    private final By shoppingCart =
            By.className("shopping_cart_link");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void validateOnHomepage() {

        WebElement productElement =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(productTitle)
                );

        assertTrue(productElement.isDisplayed());

        assertEquals(
                "Sauce Labs Backpack",
                productElement.getText()
        );
    }

    public void addBackpackToCart() {

        wait.until(
                ExpectedConditions.elementToBeClickable(addBackpackButton)
        ).click();
    }

    public void openShoppingCart() {

        wait.until(
                ExpectedConditions.elementToBeClickable(shoppingCart)
        ).click();
    }
}