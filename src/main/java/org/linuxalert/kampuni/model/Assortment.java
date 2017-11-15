package org.linuxalert.kampuni.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDateTime;
import java.util.Collection;

@XmlRootElement
@XmlType(name = "assortment")
@XmlAccessorType(XmlAccessType.FIELD)
@Value.Immutable
@JsonSerialize(as = ImmutableAssortment.class)
@JsonDeserialize(as = ImmutableAssortment.class)
public interface Assortment {

  LocalDateTime sourceCreateTime();
  Collection<Item> items();

}
