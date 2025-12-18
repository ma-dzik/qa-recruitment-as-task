package com.example.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Logger {
    private static final DateTimeFormatter TS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Logger() {}

    public static void test(String message)  { log("TEST", message); }
    public static void debug(String message) { log("DEBUG", message); }
    public static void info(String message)  { log("INFO", message); }
    public static void warn(String message)  { log("WARN", message); }
    public static void error(String message) { log("ERROR", message); }

    public static void check(String message) { log("CHECK", message); }
    public static void ok(String message)    { log("OK", message); }
    public static void notOk(String message) { log("NOT_OK", message); }

    private static void log(String level, String message) {
        String ts = LocalDateTime.now().format(TS);
        System.out.println("[" + ts + "][" + level + "] " + message);
    }
}