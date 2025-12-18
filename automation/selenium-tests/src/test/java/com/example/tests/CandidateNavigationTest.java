package com.example.tests;

import com.example.data.TestData;
import com.example.utils.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("zadanie1")
public class CandidateNavigationTest extends BaseUiTest {

    @Test
    void should_navigate_through_all_candidate_top_menu_items_and_validate_topnav_and_footer() {

        Logger.info("Open Home: " + TestData.HOME_URL);
        homePage.open();
        waitForPageReady();

        Logger.info("Go to Candidate context via HomePage CTA");
        homePage.goToCandidates();
        waitForPageReady();

        String actualUrl = normalizeUrl(driver.getCurrentUrl());
        String expectedUrl = normalizeUrl(TestData.CANDIDATE_URL);

        Logger.info("Current URL: " + actualUrl);
        assertEquals(expectedUrl, actualUrl,
                "User should land on Candidate page after clicking candidate CTA");


        Logger.info("Iterate through Candidate Top Menu items (count=" + TestData.CANDIDATE_TOP_MENU.size() + ")");

        for (String label : TestData.CANDIDATE_TOP_MENU) {
            topNav.clickItemExact(label);
            waitForPageReady();
            verifyChromeForCurrentContext();
        }

        Logger.ok("PASS: Candidate navigation across top menu + footer verified on each page");
    }

    private String normalizeUrl(String url) {
        if (url == null) return null;
        String normalized = url.split("#")[0].split("\\?")[0];
        return normalized.endsWith("/") ? normalized : normalized + "/";
    }
}