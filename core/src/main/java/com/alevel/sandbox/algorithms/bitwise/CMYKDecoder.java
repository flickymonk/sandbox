package com.alevel.sandbox.algorithms.bitwise;

import java.util.Scanner;

public class CMYKDecoder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int color = scanner.nextInt();

        int mask = 0xff;
        int cyan = color >>> 24;
        int magenta = color >>> 16 & mask;
        int yellow = color >>> 8 & mask;
        int black = color & mask;

        String ln = System.lineSeparator();

        System.out.print("For color " +
                Integer.toBinaryString(color) + ln +
                "Cyan = " + Integer.toHexString(cyan) + ln +
                "Magenta = " + Integer.toHexString(magenta) + ln +
                "Yellow = " + Integer.toHexString(yellow) + ln +
                "Black = " + Integer.toHexString(black) + ln
        );
    }
}
