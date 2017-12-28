package org.linuxalert.kampuni.model;

import java.util.Currency;
import java.util.Objects;

public class Money {

  private final Currency currency;
  private final Double amount;

  public Money(Double amount, Currency currency) {
    Objects.requireNonNull(amount, "Amount must be provided.");
    Objects.requireNonNull(currency, "Currency must be provided.");
    this.amount = amount;
    this.currency = currency;
  }

  public Currency getCurrency() {
    return currency;
  }

  public Double getAmount() {
    return amount;
  }
}
