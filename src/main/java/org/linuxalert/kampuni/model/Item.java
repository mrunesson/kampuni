package org.linuxalert.kampuni.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
@JsonSerialize(as = ImmutableItem.class)
@JsonDeserialize(as = ImmutableItem.class)
public interface Item {

  String name();
  Optional<String> subName();

}
