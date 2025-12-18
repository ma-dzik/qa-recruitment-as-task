package com.example.tests;

import com.example.utils.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Tag("zadanie2")
public class JobOfferContactFormRequiredFieldsTest extends BaseUiTest {

    private static final By SEND_CV_BUTTON = By.xpath(
            "//*[self::a or self::button][normalize-space()='WYŚLIJ SWOJE CV' or normalize-space()='Wyślij swoje CV']"
    );

    private static final By APPLY_BUTTON = By.xpath(
            "//*[self::a or self::button][normalize-space()='APLIKUJ' or normalize-space()='Aplikuj']"
    );

    private static final By CHECKBOX_VALIDATION_MESSAGES = By.xpath(
            "//*[normalize-space()='Zaznacz opcję' or normalize-space()='Zaznacz opcje']"
    );

    @Test
    void should_validate_required_fields_in_job_offer_contact_form() throws InterruptedException {

        Logger.test("TASK 2 – WATCH MODE: required fields validation");

        homePage.open();
        waitForPageReady();
        Thread.sleep(3000);

        homePage.goToCandidates();
        waitForPageReady();
        Thread.sleep(3000);

        topNav.clickItemExact("Kariera");
        waitForPageReady();
        Thread.sleep(4000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Logger.test("Click: WYŚLIJ SWOJE CV");
        WebElement sendCv = wait.until(ExpectedConditions.presenceOfElementLocated(SEND_CV_BUTTON));
        scrollIntoView(sendCv);
        Thread.sleep(2000);
        sendCv.click();

        Thread.sleep(4000);

        Logger.test("Click: APLIKUJ (empty form)");
        WebElement apply = wait.until(ExpectedConditions.presenceOfElementLocated(APPLY_BUTTON));
        scrollIntoView(apply);
        Thread.sleep(2000);
        apply.click();

        Thread.sleep(4000);

        Logger.test("Assert: form submission blocked and validation messages visible");

        List<WebElement> validationMessages =
                driver.findElements(CHECKBOX_VALIDATION_MESSAGES);

        assertTrue(
                validationMessages.size() >= 3,
                "Form should block submission and show validation messages for required fields"
        );

        Logger.ok("PASS: form correctly blocks submission when required fields are missing");
        Thread.sleep(6000);
    }

    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }
}