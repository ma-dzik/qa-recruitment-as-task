package com.example.tests;

import com.example.data.TestData;
import com.example.utils.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BusinessNavigationTest extends BaseUiTest {

    @Test
    void should_navigate_through_all_business_top_menu_items_and_validate_topnav_and_footer() {

        Logger.info("Open Home: " + TestData.HOME_URL);
        homePage.open();
        waitForPageReady();

        Logger.info("Go to Business context via HomePage CTA");
        homePage.goToBusiness();
        waitForPageReady();

        String actualUrl = normalizeUrl(driver.getCurrentUrl());
        String expectedUrl = normalizeUrl(TestData.BUSINESS_URL);

        Logger.info("Current URL: " + actualUrl);
        assertEquals(expectedUrl, actualUrl,
                "User should land on Business page after clicking business CTA");

        Logger.info("Iterate through Business Top Menu items (count=" + TestData.BUSINESS_TOP_MENU.size() + ")");

        for (String label : TestData.BUSINESS_TOP_MENU) {
            Logger.info("Navigate via TopNav: '" + label + "'");
            topNav.clickItemExact(label);
            waitForPageReady();
            verifyChromeForCurrentContext();
        }

        Logger.ok("PASS: Business navigation across top menu + footer verified on each page");
    }

    private String normalizeUrl(String url) {
        if (url == null) return null;
        String normalized = url.split("#")[0].split("\\?")[0];
        return normalized.endsWith("/") ? normalized : normalized + "/";
    }
}