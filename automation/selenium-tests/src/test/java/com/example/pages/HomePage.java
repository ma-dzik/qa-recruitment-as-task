package com.example.pages;

import com.example.data.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final By BUSINESS_BUTTON = By.xpath("//*[self::a or self::button][normalize-space()='DOWIEDZ SIĘ WIĘCEJ' or normalize-space()='Dowiedz się więcej']");
    private static final By CANDIDATE_BUTTON = By.xpath("//*[self::a or self::button][normalize-space()='WSKAKUJ NA POKŁAD' or normalize-space()='Wskakuj na pokład']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public HomePage open() {
        driver.get(TestData.HOME_URL);
        return this;
    }

    public ContextPage goToBusiness() {
        wait.until(ExpectedConditions.elementToBeClickable(BUSINESS_BUTTON)).click();
        return new ContextPage(driver);
    }

    public ContextPage goToCandidate() {
        wait.until(ExpectedConditions.elementToBeClickable(CANDIDATE_BUTTON)).click();
        return new ContextPage(driver);
    }
}