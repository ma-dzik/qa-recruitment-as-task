package com.example.utils;

public final class Logger {
    private Logger() {}

    public static void info(String message)  { System.out.println("[INFO] " + message); }
    public static void check(String message) { System.out.println("[CHECK] " + message); }
    public static void ok(String message)    { System.out.println("[OK] " + message); }
    public static void notOk(String message) { System.out.println("[NOT OK] " + message); }
}