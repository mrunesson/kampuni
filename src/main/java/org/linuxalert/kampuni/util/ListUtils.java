package org.linuxalert.kampuni.util;

import java.util.List;

public class ListUtils {

  public ListUtils() {
    throw new IllegalAccessError("ListUtils is a util class.");
  }

  public static <T> List<T> of(T value) {
    if (value != null) {
      return List.<T>of(value);
    } else {
      return List.<T>of();
    }
  }
}
