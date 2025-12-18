package com.example.pages;

import com.example.data.TestData;
import com.example.utils.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final By BUSINESS_BUTTON = By.xpath(
            "//*[self::a or self::button][normalize-space()='DOWIEDZ SIĘ WIĘCEJ' or normalize-space()='Dowiedz się więcej']"
    );

    private static final By CANDIDATE_BUTTON = By.xpath(
            "//*[self::a or self::button][normalize-space()='WSKAKUJ NA POKŁAD' or normalize-space()='Wskakuj na pokład']"
    );

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        Logger.info("Open HomePage");
        driver.get(TestData.HOME_URL);
    }

    public void goToBusiness() {
        Logger.check("HomePage: go to Business via CTA");
        wait.until(ExpectedConditions.elementToBeClickable(BUSINESS_BUTTON)).click();
        Logger.ok("HomePage: Business context opened");
    }

    public void goToCandidates() {
        Logger.check("HomePage: go to Candidates via CTA");
        wait.until(ExpectedConditions.elementToBeClickable(CANDIDATE_BUTTON)).click();
        Logger.ok("HomePage: Candidate context opened");
    }
}