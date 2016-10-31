package com.edfward.RotationCoding;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;

public class Main {
    public static void main(String[] args) {
        String randomString = RandomStringUtils.randomAlphanumeric(1000000);

        // Run 100 times.
        for (int i = 0; i < 200; i++) {
            String encoded = RotationCoding.code(randomString);
            String decoded = RotationCoding.decode(encoded);

            boolean same = randomString.equals(decoded);
            System.out.println("Source and decoded are the same? " + (same ? "Yes." : "No"));
        }
    }
}

class RotationCoding {

    public static String code(String s) {
        int size = s.length();
        if (size == 0) {
            return "";
        }

        int n = ((int) Math.sqrt(size - 1)) + 1;
        char[][] chs = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int chIndex = i * n + j;
                chs[i][j] = chIndex >= size ? 11 : s.charAt(chIndex);
            }
        }
        char[][] rotatedClockwise = rotate(chs, true);

        return Arrays.stream(rotatedClockwise)
            .map(String::new)
            .collect(Collectors.joining("\n"));
    }

    public static String decode(String s) {
        s = s.replace("\n", "");
        int size = s.length();
        if (size == 0) {
            return "";
        }

        int n = (int) Math.sqrt(size);
        char[][] chs = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chs[i][j] = s.charAt(i * n + j);
            }
        }
        char[][] rotatedCounterClockwise = rotate(chs, false);

        StringBuilder resBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char ch = rotatedCounterClockwise[i][j];
                if (ch == 11) {
                    break;
                }
                resBuilder.append(ch);
            }
        }
        return resBuilder.toString();
    }

    private static char[][] rotate(char[][] src, boolean clockwise) {
        int srcSize = src.length;
        char[][] res = new char[srcSize][srcSize];
        if (clockwise) {  // Clockwise.
            for (int row = 0; row < srcSize; row++) {
                for (int col = 0; col < srcSize; col++) {
                    res[col][srcSize - 1 - row] = src[row][col];
                }
            }
        } else {  // Counter-clockwise.
            for (int row = 0; row < srcSize; row++) {
                for (int col = 0; col < srcSize; col++) {
                    res[srcSize - 1 - col][row] = src[row][col];
                }
            }
        }
        return res;
    }
}
