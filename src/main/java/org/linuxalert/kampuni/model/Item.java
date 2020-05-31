package org.linuxalert.kampuni.model;

import com.neovisionaries.i18n.CountryCode;
import org.linuxalert.kampuni.model.serializer.VintageSerializer;

import javax.json.bind.annotation.JsonbTypeSerializer;
import java.util.List;
import java.util.Set;

public class Item {

  private String name;
  private String subName;
  private Double alcoholPercent;
  @JsonbTypeSerializer(VintageSerializer.class)
  private Vintage vintage;
  private String supplier;
  private String producer;
  private Money price;
  private double volumeLitre;
  private CountryCode country;
  private String primaryProductsText;
  private Artiklar.Artikel sourceRecord;
  private String productId;
  private Set<String> tags;
  private List<String> region;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSubName() {
    return subName;
  }

  public void setSubName(String subName) {
    this.subName = subName;
  }

  public void setAlcoholPercent(Double alcoholPercent) {
    this.alcoholPercent = alcoholPercent;
  }

  public Double getAlcoholPercent() {
    return alcoholPercent;
  }

  public void setVintage(Vintage vintage) {
    this.vintage = vintage;
  }

  public Vintage getVintage() {
    return vintage;
  }

  public void setSupplier(String supplier) {
    this.supplier = supplier;
  }

  public String getSupplier() {
    return supplier;
  }

  public void setProducer(String producer) {
    this.producer = producer;
  }

  public String getProducer() {
    return producer;
  }

  public void setPrice(Money price) {
    this.price = price;
  }

  public Money getPrice() {
    return price;
  }

  public void setVolumeLitre(double volumeLitre) {
    this.volumeLitre = volumeLitre;
  }

  public double getVolumeLitre() {
    return volumeLitre;
  }

  public void setCountry(CountryCode country) {
    this.country = country;
  }

  public CountryCode getCountry() {
    return country;
  }

  public void setPrimaryProductsText(String primaryProductsText) {
    this.primaryProductsText = primaryProductsText;
  }

  public String getPrimaryProductsText() {
    return primaryProductsText;
  }

  public void setSourceRecord(Artiklar.Artikel sourceRecord) {
    this.sourceRecord = sourceRecord;
  }

  public Artiklar.Artikel getSourceRecord() {
    return sourceRecord;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getProductId() {
    return productId;
  }

  public void setTags(Set<String> tags) {
    this.tags = tags;
  }

  public Set<String> getTags() {
    return tags.isEmpty()?null:tags;
  }

  public void setRegion(List<String> region) {
    this.region = region;
  }

  public List<String> getRegion() {
    return region.isEmpty()?null:region;
  }
}
