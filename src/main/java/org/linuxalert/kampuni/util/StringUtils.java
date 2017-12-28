package org.linuxalert.kampuni.util;

public class StringUtils {

  public StringUtils() {
    throw new IllegalAccessError("StringUtils is a util class.");
  }

  public static String cleanValue(String value) {
    if (value != null) {
      String trimmedValue = value.trim();
      if (trimmedValue.isEmpty()) {
        return null;
      } else {
        return trimmedValue;
      }
    } else {
      return null;
    }
  }
}
