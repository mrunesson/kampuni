package org.linuxalert.kampuni.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDateTime;
import java.util.Collection;

@XmlRootElement
@XmlType(name = "assortment")
@XmlAccessorType(XmlAccessType.FIELD)
public class Assortment {

  private LocalDateTime sourceCreateTime;
  private Collection<Item> items;

  public LocalDateTime getSourceCreateTime() {
    return sourceCreateTime;
  }

  public void setSourceCreateTime(LocalDateTime sourceCreateTime) {
    this.sourceCreateTime = sourceCreateTime;
  }

  public Collection<Item> getItems() {
    return items;
  }

  public void setItems(Collection<Item> items) {
    this.items = items;
  }
}
