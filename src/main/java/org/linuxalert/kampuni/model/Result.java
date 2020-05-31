package org.linuxalert.kampuni.model;

import org.linuxalert.kampuni.model.serializer.LocalDateTimeSerializer;

import javax.json.bind.annotation.JsonbTypeSerializer;
import java.time.LocalDateTime;
import java.util.Collection;

public class Result {

  Collection<Item> items;
  LocalDateTime updateTime;

  public Result(Collection<Item> items, LocalDateTime updateTime) {
    this.items = items;
    this.updateTime = updateTime;
  }

  public Collection<Item> getItems() {
    return items;
  }

  @JsonbTypeSerializer(LocalDateTimeSerializer.class)
  public LocalDateTime getUpdateTime() {
    return updateTime;
  }
}
