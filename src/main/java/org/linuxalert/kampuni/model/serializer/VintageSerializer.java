package org.linuxalert.kampuni.model.serializer;

import org.linuxalert.kampuni.model.Vintage;

import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;

public class VintageSerializer implements JsonbSerializer<Vintage> {

  @Override
  public void serialize(Vintage obj, javax.json.stream.JsonGenerator generator, SerializationContext ctx) {
    generator.write(obj.toString());
  }
}
