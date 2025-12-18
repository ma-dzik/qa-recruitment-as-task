package com.example.tests;

import com.example.components.FooterComponent;
import com.example.components.TopNavComponent;
import com.example.pages.HomePage;
import com.example.utils.Logger;
import com.example.data.TestData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

//@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public abstract class BaseUiTest {

    protected WebDriver driver;

    protected TopNavComponent topNav;
    protected FooterComponent footer;

    protected HomePage homePage;

    @BeforeEach
    void setUp() {
        Logger.info("=== Test setup ===");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0)); // świadomie: bez implicit wait
        driver.manage().window().maximize();

        topNav = new TopNavComponent(driver);
        footer = new FooterComponent(driver);
        homePage = new HomePage(driver);

        Logger.ok("Driver + components initialized");
    }

    @AfterEach
    void tearDown() {
        Logger.info("=== Test teardown ===");
        if (driver != null) {
            driver.quit();
            Logger.ok("Driver closed");
        }
    }

    protected void waitForPageReady() {
        //Logger.check("Wait for document.readyState=complete");

        long end = System.currentTimeMillis() + 10_000;

        while (System.currentTimeMillis() < end) {
            try {
                Object state = ((JavascriptExecutor) driver).executeScript("return document.readyState");
                if ("complete".equals(state)) {
                    //Logger.ok("Page ready (document.readyState=complete)");
                    return;
                }
            } catch (Exception ignored) {
            }

            try { Thread.sleep(100); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }

        Logger.notOk("Timeout waiting for page ready");
        throw new AssertionError("Timeout waiting for document.readyState=complete");
    }

    protected List<String> expectedTopMenuForCurrentContext() {
        String url = driver.getCurrentUrl().split("#")[0].split("\\?")[0];
        if (url.contains(TestData.BUSINESS_PATH_MARKER)) {
            return TestData.BUSINESS_TOP_MENU;
        }
        if (url.contains(TestData.CANDIDATE_PATH_MARKER)) {
            return TestData.CANDIDATE_TOP_MENU;
        }
        throw new AssertionError("Unknown context for URL: " + url);
    }

    protected void verifyTopMenu(List<String> expectedMenuItems) {
        //Logger.info("Verify Top Menu Nav items (count=" + expectedMenuItems.size() + ")");
        Logger.check("Top Menu Nav: verify required items for URL=" + driver.getCurrentUrl());
        for (String label : expectedMenuItems) {
            topNav.assertItemVisibleExact(label);
        }
        Logger.ok("Top Menu Nav verified");
    }

    protected void verifyFooter() {
        Logger.info("Verify Footer");
        footer.assertFooterVisible();
        footer.assertContactSectionPresent();
        Logger.ok("Footer verified");
    }

    protected void verifyChromeForCurrentContext() {
        List<String> expected = expectedTopMenuForCurrentContext();
        verifyTopMenu(expected);
        verifyFooter();
    }
}