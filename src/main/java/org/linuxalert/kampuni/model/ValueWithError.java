package org.linuxalert.kampuni.model;

import java.util.Optional;

public class ValueWithError<V, E> {

  private Optional<V> value;
  private Optional<E> error;

  private ValueWithError() {};

  private ValueWithError(Optional<V> value, Optional<E> error) {
    this.value = value;
    this.error = error;
  }

  public Optional<V> getValue() {
    return value;
  }

  public Optional<E> getError() {
    return error;
  }

  public boolean isCorrect() {
    return value.isPresent();
  }

  public boolean isError() {
    return error.isPresent();
  }

  public static class Builder<V, E> {

    private V value = null;
    private E error = null;

    public ValueWithError<V, E> build() {
      if (value != null && error != null) {
        throw new IllegalStateException("Both value and error cannot be set.");
      }
      return new ValueWithError<V,E>(Optional.ofNullable(value), Optional.ofNullable(error));
    }

    public Builder<V, E> value(V value) {
      this.value = value;
      return this;
    }

    public Builder<V, E> error(E error) {
      this.error = error;
      return this;
    }

  }

}
