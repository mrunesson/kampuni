package org.linuxalert.kampuni.model;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Optional;

public class Vintage {

  private Integer year;
  private VintageType type;

  public Vintage(String year) {
    if (year != null && year.matches("[0-9]")) {
      try {
        this.year = Integer.parseInt(year.trim());
        if (this.year < 100) {
          this.year += 2000;
        }
        type = VintageType.YEAR;
      } catch (NumberFormatException e) {
        type = VintageType.NA;
      }
    } else {
      // TODO: Do better validation.
      type = VintageType.NA;
    }
  }

  public Optional<Integer> getYear() {
    if (type == VintageType.YEAR) {
      return Optional.of(year);
    }
    return Optional.empty();
  }

  public boolean hasVintage() {
    return type == VintageType.YEAR;
  }

  @JsonValue
  public String getJsonValue() {
    if (type == VintageType.YEAR) {
      return year.toString();
    }
    return "NV";
  }

  private enum VintageType {
    YEAR, NA
  }
}
