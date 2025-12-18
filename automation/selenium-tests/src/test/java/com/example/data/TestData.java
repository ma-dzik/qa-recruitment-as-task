package com.example.data;

import java.util.List;

public final class TestData {

    private TestData() {}

    // URLs
    public static final String HOME_URL = "https://alan-systems.com/";
    public static final String BUSINESS_URL = "https://alan-systems.com/pl/dla-biznesu/";
    public static final String CANDIDATE_URL = "https://alan-systems.com/pl/dla-kandydatow/";
    public static final String BUSINESS_PATH_MARKER = "/dla-biznesu";
    public static final String CANDIDATE_PATH_MARKER = "/dla-kandydatow";

    // Expected top menu labels (exact labels as in requirement)
    public static final List<String> BUSINESS_TOP_MENU = List.of(
            "Dla biznesu",
            "O nas",
            "Oferta",
            "Kontakt",
            "Kariera w ALAN SYSTEMS"
    );

    public static final List<String> CANDIDATE_TOP_MENU = List.of(
            "Dla kandydata",
            "O nas",
            "Kariera",
            "Kontakt",
            "Rozwiązania do biznesu"
    );

    // Footer contact (expected)
    public static final String CONTACT_ADDRESS_LINE = "ul. Obwiednia Południowa 22";
    public static final String CONTACT_POSTAL_CITY = "44-200 Rybnik";
    public static final String CONTACT_PHONE = "+48 32 733 86 00";
    public static final String CONTACT_EMAIL = "info@alan-systems.com";
}