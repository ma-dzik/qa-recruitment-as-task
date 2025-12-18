package com.example.components;

import com.example.utils.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TopNavComponent {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By navRoot = By.cssSelector("header nav");

    public TopNavComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickItemExact(String label) {
        Logger.check("TopNav: click '" + label + "'");

        WebElement item = findNavItemByExactText(label);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(item));
            item.click();
            Logger.ok("TopNav: clicked '" + label + "'");
        } catch (Exception e) {
            Logger.notOk("TopNav: cannot click '" + label + "'. Reason: " + e.getClass().getSimpleName());
            throw new AssertionError("TopNav: click failed for '" + label + "'", e);
        }
    }

    public void assertItemVisibleExact(String label) {
        Logger.check("TopNav: item visible '" + label + "'");

        try {
            WebElement item = findNavItemByExactText(label);
            wait.until(ExpectedConditions.visibilityOf(item));
            Logger.ok("TopNav: visible '" + label + "'");
        } catch (Exception e) {
            Logger.notOk("TopNav: not visible '" + label + "'. Reason: " + e.getClass().getSimpleName());
            throw new AssertionError("TopNav: expected item not visible: '" + label + "'", e);
        }
    }


    private WebElement findNavItemByExactText(String label) {
        WebElement nav = wait.until(ExpectedConditions.presenceOfElementLocated(navRoot));

        String xpath =
                ".//a[normalize-space()='" + escapeQuotesForXPath(label) + "']" +
                        " | .//button[normalize-space()='" + escapeQuotesForXPath(label) + "']";

        try {
            return nav.findElement(By.xpath(xpath));
        } catch (NoSuchElementException e) {
            Logger.notOk("TopNav: item not found in header nav: '" + label + "'");
            throw new AssertionError("TopNav: item not found in header nav: '" + label + "'", e);
        }
    }


    private String escapeQuotesForXPath(String text) {

        if (!text.contains("'")) {
            return text;
        }

        String[] parts = text.split("'");
        StringBuilder sb = new StringBuilder("concat(");
        for (int i = 0; i < parts.length; i++) {
            sb.append("'").append(parts[i]).append("'");
            if (i < parts.length - 1) {
                sb.append(", \"'\", ");
            }
        }
        return sb.toString();
    }
}