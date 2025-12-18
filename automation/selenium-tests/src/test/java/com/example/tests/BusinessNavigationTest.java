package com.example.tests;

import com.example.data.TestData;
import com.example.pages.HomePage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class BusinessNavigationTest extends BaseUiTest {

    @Test
    void should_navigate_from_home_to_business_context_and_show_business_top_menu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("[INFO] Open Home: " + TestData.HOME_URL);
        HomePage home = new HomePage(driver).open();

        System.out.println("[INFO] Click: 'DOWIEDZ SIĘ WIĘCEJ' -> expect Business page: " + TestData.BUSINESS_URL);
        home.goToBusiness();

        // --- URL assertion (robust against missing trailing slash / redirects) ---
        String actualUrl = normalizeUrl(driver.getCurrentUrl());
        String expectedUrl = normalizeUrl(TestData.BUSINESS_URL);

        System.out.println("[INFO] Current URL after navigation: " + actualUrl);
        assertEquals(expectedUrl, actualUrl,
                "After clicking 'Dowiedz się więcej' user should land on Business context URL");

        // --- Top menu assertions (all items visible) ---
        System.out.println("[INFO] Verify Business top menu items are visible: " + TestData.BUSINESS_MENU);

        assertAll("Business top menu items visibility",
                () -> assertTopMenuItemVisible(wait, "Dla biznesu"),
                () -> assertTopMenuItemVisible(wait, "O nas"),
                () -> assertTopMenuItemVisible(wait, "Oferta"),
                () -> assertTopMenuItemVisible(wait, "Kontakt"),
                () -> assertTopMenuItemVisibleContainsIgnoreCase(wait, "Kariera w", "alan systems")
        );

        System.out.println("[INFO] PASS: Business navigation + top menu verified.");
    }

    // ---- helpers (only for this test class) ----

    private static String normalizeUrl(String url) {
        if (url == null) return null;
        // usuń ewentualne # i ? (żeby porównanie było stabilne)
        String u = url.split("#", 2)[0].split("\\?", 2)[0];
        // ujednolić końcowy slash
        return u.endsWith("/") ? u : (u + "/");
    }

    private void assertTopMenuItemVisible(WebDriverWait wait, String exactLabel) {
        By locator = By.xpath("//header//*[self::a or self::button][normalize-space()='" + exactLabel + "']");
        System.out.println("[CHECK] Expect menu item visible: " + exactLabel);

        try {
            WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            assertTrue(el.isDisplayed(), "Top menu item should be visible: '" + exactLabel + "'");
            System.out.println("[OK] Menu visible: " + exactLabel);
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("[NOT OK] Menu missing: " + exactLabel);
            fail("Missing top menu item: '" + exactLabel + "'. Current URL: " + driver.getCurrentUrl());
        }
    }

    private void assertTopMenuItemVisibleContainsIgnoreCase(WebDriverWait wait, String... parts) {
        // Budujemy XPath, który sprawdza, że tekst zawiera wszystkie fragmenty (case-insensitive)
        // używamy translate() do zrobienia "lowercase" w XPath 1.0
        String textExpr = "translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZĄĆĘŁŃÓŚŻŹ', 'abcdefghijklmnopqrstuvwxyząćęłńóśżź')";
        StringBuilder predicate = new StringBuilder();
        for (String p : parts) {
            String lower = p.toLowerCase();
            predicate.append(" and contains(").append(textExpr).append(", '").append(lower).append("')");
        }

        By locator = By.xpath("//header//*[self::a or self::button][" + predicate.substring(5) + "]");
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        assertTrue(el.isDisplayed(), "Top menu item containing parts should be visible: " + String.join(" | ", parts));
        System.out.println("[INFO] Menu visible (contains): " + String.join(" | ", parts));
    }
}