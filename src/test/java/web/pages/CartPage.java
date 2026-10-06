package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cartItem =
            By.id("item_4_title_link");

    private final By checkoutButton =
            By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void validateProductInCart(String expectedProductName) {

        WebElement productElement =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(cartItem)
                );

        assertTrue(productElement.isDisplayed());

        assertEquals(
                expectedProductName,
                productElement.getText()
        );
    }

    public void clickCheckout() {

        wait.until(
                ExpectedConditions.elementToBeClickable(checkoutButton)
        ).click();
    }
}