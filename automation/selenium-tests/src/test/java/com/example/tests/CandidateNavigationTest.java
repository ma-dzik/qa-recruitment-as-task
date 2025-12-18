package com.example.tests;

import com.example.data.TestData;
import com.example.pages.HomePage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CandidateNavigationTest extends BaseUiTest {

    @Test
    void should_navigate_from_home_to_candidate_context() {
        new HomePage(driver)
                .open()
                .goToCandidate();

        assertEquals(TestData.CANDIDATE_URL, driver.getCurrentUrl(),
                "After clicking 'Wskakuj na pokład' user should land on Candidate page");
    }
}