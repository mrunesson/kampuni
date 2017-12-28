package org.linuxalert.kampuni.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;

public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

  public LocalDateTimeSerializer() {
    this(null);
  }

  public LocalDateTimeSerializer(Class t) {
    super(t);
  }

  @Override
  public void serialize (LocalDateTime value, JsonGenerator gen, SerializerProvider arg2)
      throws IOException, JsonProcessingException {
    gen.writeString(value.toString());
  }

}
