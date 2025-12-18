package com.example.components;

import com.example.utils.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FooterComponent {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By footerRoot = By.cssSelector("footer");

    public FooterComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void assertFooterVisible() {
        Logger.check("Footer: visible");

        try {
            WebElement footer = wait.until(ExpectedConditions.presenceOfElementLocated(footerRoot));
            wait.until(ExpectedConditions.visibilityOf(footer));
            Logger.ok("Footer: visible");
        } catch (Exception e) {
            Logger.notOk("Footer: not visible. Reason: " + e.getClass().getSimpleName());
            throw new AssertionError("Footer: expected footer to be visible", e);
        }
    }

    public void assertContactSectionPresent() {
        Logger.check("Footer: contact section present");

        WebElement footer = wait.until(ExpectedConditions.presenceOfElementLocated(footerRoot));

        try {
            boolean hasMailto = !footer.findElements(By.cssSelector("a[href^='mailto:']")).isEmpty();
            boolean hasTel = !footer.findElements(By.cssSelector("a[href^='tel:']")).isEmpty();

            boolean hasKontaktText = !footer.findElements(
                    By.xpath(".//*[contains(translate(normalize-space(), 'KONTAKT', 'kontakt'), 'kontakt')]")
            ).isEmpty();

            if (hasMailto || hasTel || hasKontaktText) {
                Logger.ok("Footer: contact section present");
                return;
            }

            Logger.notOk("Footer: contact section NOT present (no mailto/tel/'kontakt' in footer)");
            throw new AssertionError("Footer: contact section not detected in footer");

        } catch (NoSuchElementException e) {
            Logger.notOk("Footer: contact section NOT present. Reason: NoSuchElementException");
            throw new AssertionError("Footer: contact section not detected in footer", e);
        }
    }

    public void assertContactData(String expectedEmailOrNull, String expectedPhoneOrNull) {
        Logger.check("Footer: contact data matches expected");

        WebElement footer = wait.until(ExpectedConditions.presenceOfElementLocated(footerRoot));

        try {
            if (expectedEmailOrNull != null) {
                String mailto = "mailto:" + expectedEmailOrNull;
                boolean emailOk = !footer.findElements(By.cssSelector("a[href='" + cssEscape(mailto) + "']")).isEmpty()
                        || footer.getText().contains(expectedEmailOrNull);

                if (!emailOk) {
                    Logger.notOk("Footer: expected email not found: " + expectedEmailOrNull);
                    throw new AssertionError("Footer: expected email not found: " + expectedEmailOrNull);
                }
            }

            if (expectedPhoneOrNull != null) {
                String tel = "tel:" + expectedPhoneOrNull;
                boolean phoneOk = !footer.findElements(By.cssSelector("a[href='" + cssEscape(tel) + "']")).isEmpty()
                        || footer.getText().contains(expectedPhoneOrNull);

                if (!phoneOk) {
                    Logger.notOk("Footer: expected phone not found: " + expectedPhoneOrNull);
                    throw new AssertionError("Footer: expected phone not found: " + expectedPhoneOrNull);
                }
            }

            Logger.ok("Footer: contact data matches expected");
        } catch (Exception e) {
            Logger.notOk("Footer: contact data check failed. Reason: " + e.getClass().getSimpleName());
            throw new AssertionError("Footer: contact data check failed", e);
        }
    }

    private String cssEscape(String value) {
        return value.replace("'", "\\'");
    }
}