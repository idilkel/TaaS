package com.jb.TaaS.utils;

public class TestUtils {
    private static int count = 1;

    public static void printTitle(String title) {
        System.out.printf("---------- Test %d - %s ----------\n", count++, title);
    }
}
