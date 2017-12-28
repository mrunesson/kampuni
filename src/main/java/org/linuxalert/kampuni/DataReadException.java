package org.linuxalert.kampuni;

public class DataReadException extends Throwable {

  public DataReadException(String message) {
    super(message);
  }

  public DataReadException(Exception e) {
    super(e);
  }
}
