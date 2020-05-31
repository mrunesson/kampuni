package org.linuxalert.kampuni.model.serializer;

import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import java.time.LocalDateTime;

public class LocalDateTimeSerializer implements JsonbSerializer<LocalDateTime> {

  @Override
  public void serialize(LocalDateTime obj, javax.json.stream.JsonGenerator generator, SerializationContext ctx) {
    generator.write(obj.toString());
  }
}
