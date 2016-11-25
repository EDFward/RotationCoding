package com.edfward.RotationCoding;

import java.util.Arrays;
import java.util.stream.Collectors;

public interface RotationCoding {
  String code(String s);

  String decode(String s);
}


class RotationCodingOriginal implements RotationCoding {

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

  public String code(String s) {
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

  public String decode(String s) {
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
}

class RotationCodingV1 implements RotationCoding {

  private static char[][] rotate(String src, int n, boolean clockwise) {
    int srcSize = src.length();
    char[][] res = new char[n][n];
    if (clockwise) {  // Clockwise.
      for (int row = 0; row < n; row++) {
        for (int col = 0; col < n; col++) {
          int idx = row * n + col;
          char ch = idx >= srcSize ? 11 : src.charAt(idx);
          res[col][n - 1 - row] = ch;
        }
      }
    } else {  // Counter-clockwise.
      for (int row = 0; row < n; row++) {
        for (int col = 0; col < n; col++) {
          int idx = row * n + col;
          char ch = idx >= srcSize ? 11 : src.charAt(idx);
          res[n - 1 - col][row] = ch;
        }
      }
    }
    return res;
  }

  public String code(String s) {
    int size = s.length();
    if (size == 0) {
      return "";
    }

    int n = ((int) Math.sqrt(size - 1)) + 1;
    char[][] rotatedClockwise = rotate(s, n, true);

    return Arrays.stream(rotatedClockwise)
        .map(String::new)
        .collect(Collectors.joining("\n"));
  }

  public String decode(String s) {
    s = s.replace("\n", "");
    int size = s.length();
    if (size == 0) {
      return "";
    }

    int n = (int) Math.sqrt(size);
    char[][] rotatedCounterClockwise = rotate(s, n, false);

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
}

class RotationCodingV2 implements RotationCoding {

  private static char[][] rotate(String src, int n, boolean clockwise) {
    int srcSize = src.length();
    char[][] res = new char[n][n];
    if (clockwise) {  // Clockwise.
      for (int row = 0; row < n; row++) {
        for (int col = 0; col < n; col++) {
          int idx = row * n + col;
          char ch = idx >= srcSize ? 11 : src.charAt(idx);
          res[col][n - 1 - row] = ch;
        }
      }
    } else {  // Counter-clockwise.
      for (int row = 0; row < n; row++) {
        for (int col = 0; col < n; col++) {
          int idx = row * n + col;
          char ch = idx >= srcSize ? 11 : src.charAt(idx);
          res[n - 1 - col][row] = ch;
        }
      }
    }
    return res;
  }

  public String code(String s) {
    int size = s.length();
    if (size == 0) {
      return "";
    }

    int n = ((int) Math.sqrt(size - 1)) + 1;
    char[][] rotatedClockwise = rotate(s, n, true);

    StringBuilder res = new StringBuilder();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        res.append(rotatedClockwise[i][j]);
      }
      if (i != n - 1) {
        res.append('\n');
      }
    }

    return res.toString();
  }

  public String decode(String s) {
    s = s.replace("\n", "");
    int size = s.length();
    if (size == 0) {
      return "";
    }

    int n = (int) Math.sqrt(size);
    char[][] rotatedCounterClockwise = rotate(s, n, false);

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
}

class RotationCodingV3 implements RotationCoding {

  public String code(String s) {
    int size = s.length();
    if (size == 0) {
      return "";
    }

    int n = ((int) Math.sqrt(size - 1)) + 1;

    StringBuilder res = new StringBuilder();
    for (int row = 0; row < n; row++) {
      for (int col = 0; col < n; col++) {
        int i = (n - 1 - col) * n + row;
        res.append(i >= size ? 11 : s.charAt(i));
        if (col == n - 1 && row != n - 1) {
          res.append('\n');
        }
      }
    }
    return res.toString();
  }

  public String decode(String s) {
    s = s.replace("\n", "");
    int size = s.length();
    if (size == 0) {
      return "";
    }

    int n = (int) Math.sqrt(size);

    StringBuilder resBuilder = new StringBuilder();
    OUTER:
    for (int row = 0; row < n; row++) {
      for (int col = 0; col < n; col++) {
        int i = col * n + (n - 1 - row);
        char ch = s.charAt(i);
        if (ch == 11) {
          break OUTER;
        }
        resBuilder.append(ch);
      }
    }
    return resBuilder.toString();
  }
}
