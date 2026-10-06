package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstNameInput =
            By.id("first-name");

    private final By lastNameInput =
            By.id("last-name");

    private final By postalCodeInput =
            By.id("postal-code");

    private final By continueButton =
            By.id("continue");

    private final By finishButton =
            By.id("finish");

    private final By completeHeader =
            By.cssSelector("[data-test='complete-header']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillCustomerInformation(
            String firstName,
            String lastName,
            String postalCode
    ) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstNameInput)
        ).sendKeys(firstName);

        driver.findElement(lastNameInput)
                .sendKeys(lastName);

        driver.findElement(postalCodeInput)
                .sendKeys(postalCode);
    }

    public void clickContinue() {

        wait.until(
                ExpectedConditions.elementToBeClickable(continueButton)
        ).click();
    }

    public void clickFinish() {

        wait.until(
                ExpectedConditions.elementToBeClickable(finishButton)
        ).click();
    }

    public void validateOrderCompleted() {

        String expectedMessage =
                "Thank you for your order!";

        String actualMessage =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                completeHeader
                        )
                ).getText();

        assertTrue(
                driver.getCurrentUrl().contains("checkout-complete")
        );

        assertEquals(
                expectedMessage,
                actualMessage
        );
    }
}