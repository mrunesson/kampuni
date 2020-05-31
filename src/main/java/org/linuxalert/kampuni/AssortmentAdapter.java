package org.linuxalert.kampuni;

import com.google.common.collect.ImmutableMap;
import com.neovisionaries.i18n.CountryCode;
import org.linuxalert.kampuni.model.Artiklar;
import org.linuxalert.kampuni.model.Assortment;
import org.linuxalert.kampuni.model.Item;
import org.linuxalert.kampuni.model.Money;
import org.linuxalert.kampuni.model.ValueWithError;
import org.linuxalert.kampuni.model.Vintage;
import org.linuxalert.kampuni.util.ListUtils;
import org.linuxalert.kampuni.util.StringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class AssortmentAdapter {

  private static final System.Logger LOGGER = System.getLogger(AssortmentAdapter.class.getName());
  private static final String DATASET_URL =
      "https://www.systembolaget.se/api/assortment/products/xml";
  private static final DateTimeFormatter CREATION_TIME_FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
  private Optional<InputStream> assortmentStream = Optional.empty();

  private static final Map<String, CountryCode> swedishToCountryCode = ImmutableMap.<String, CountryCode>builder()
      .put("Argentina", CountryCode.AR)
      .put("Armenien", CountryCode.AM)
      .put("Australien", CountryCode.AU)
      .put("Bahamas", CountryCode.BS)
      .put("Barbados", CountryCode.BB)
      .put("Belgien", CountryCode.BE)
      .put("Belize", CountryCode.BZ)
      .put("Bosnien-Hercegovina", CountryCode.BA)
      .put("Brasilien", CountryCode.BR)
      .put("Bulgarien", CountryCode.BG)
      .put("Chile", CountryCode.CL)
      .put("Colombia", CountryCode.CO)
      .put("Costa Rica", CountryCode.CR)
      .put("Cypern", CountryCode.CY)
      .put("Danmark", CountryCode.DK)
      .put("Dominikanska Republiken", CountryCode.DO)
      .put("EU", CountryCode.EU)
      .put("Estland", CountryCode.EE)
      .put("Etiopien", CountryCode.ET)
      .put("Fiji", CountryCode.FJ)
      .put("Filippinerna", CountryCode.PH)
      .put("Finland", CountryCode.FI)
      .put("Folkrepubliken Kina", CountryCode.CN)
      .put("Frankrike", CountryCode.FR)
      .put("Georgien", CountryCode.GE)
      .put("Grekland", CountryCode.GR)
      .put("Grenada", CountryCode.GD)
      .put("Guatemala", CountryCode.GT)
      .put("Guyana", CountryCode.GY)
      .put("Haiti", CountryCode.HT)
      .put("Indien", CountryCode.IN)
      .put("Indonesien", CountryCode.ID)
      .put("Internationellt märke", CountryCode.UNDEFINED)
      .put("Irland", CountryCode.IE)
      .put("Island", CountryCode.IS)
      .put("Israel", CountryCode.IL)
      .put("Italien", CountryCode.IT)
      .put("Jamaica", CountryCode.JM)
      .put("Japan", CountryCode.JP)
      .put("Kambodja", CountryCode.KH)
      .put("Kanada", CountryCode.CA)
      .put("Kenya", CountryCode.KE)
      .put("Kroatien", CountryCode.HR)
      .put("Kuba", CountryCode.CU)
      .put("Laos", CountryCode.LA)
      .put("Lettland", CountryCode.LV)
      .put("Libanon", CountryCode.LB)
      .put("Litauen", CountryCode.LT)
      .put("Luxemburg", CountryCode.LU)
      .put("Marocko", CountryCode.MA)
      .put("Mauritius", CountryCode.MR)
      .put("Mexiko", CountryCode.MX)
      .put("Moldavien", CountryCode.MD)
      .put("Montenegro", CountryCode.ME)
      .put("Namibia", CountryCode.NA)
      .put("Nederländerna", CountryCode.NL)
      .put("Nicaragua", CountryCode.NI)
      .put("Nigeria", CountryCode.NG)
      .put("Norge", CountryCode.NO)
      .put("Nya Zeeland", CountryCode.NZ)
      .put("Okänt ursprung", CountryCode.UNDEFINED)
      .put("Palestina", CountryCode.PS)
      .put("Panama", CountryCode.PA)
      .put("Paraguay", CountryCode.PY)
      .put("Peru", CountryCode.PE)
      .put("Polen", CountryCode.PL)
      .put("Portugal", CountryCode.PT)
      .put("Republiken Makedonien", CountryCode.MK)
      .put("Rumänien", CountryCode.RO)
      .put("Ryssland", CountryCode.RU)
      .put("Schweiz", CountryCode.CH)
      .put("Serbien", CountryCode.RS)
      .put("Singapore", CountryCode.SG)
      .put("Slovakien", CountryCode.SK)
      .put("Slovenien", CountryCode.SI)
      .put("Spanien", CountryCode.ES)
      .put("Sri Lanka", CountryCode.LK)
      .put("St Lucia", CountryCode.LC)
      .put("Storbritannien", CountryCode.GB)
      .put("Sverige", CountryCode.SE)
      .put("Sydafrika", CountryCode.ZA)
      .put("Sydkorea", CountryCode.KR)
      .put("Thailand", CountryCode.TH)
      .put("Tjeckien", CountryCode.CZ)
      .put("Trinidad", CountryCode.TT)
      .put("Tunisien", CountryCode.TN)
      .put("Turkiet", CountryCode.TR)
      .put("Tyskland", CountryCode.DE)
      .put("USA", CountryCode.US)
      .put("Uganda", CountryCode.UG)
      .put("Ukraina", CountryCode.UA)
      .put("Ungern", CountryCode.HU)
      .put("Uruguay", CountryCode.UY)
      .put("Varierande ursprung", CountryCode.UNDEFINED)
      .put("Venezuela", CountryCode.VE)
      .put("Vietnam", CountryCode.VN)
      .put("Österrike", CountryCode.AT)
      .put("Övriga ursprung", CountryCode.UNDEFINED)
      .build();


private static final Map<String, Set<String>> cleanVarugruppMap = ImmutableMap.<String, Set<String>>builder()
      .put("vitt vin", Set.of("white"))
      .put("vita", Set.of("white"))
      .put("övrigt starkvin", Set.of("fortified"))
      .put("smaksatt vin", Set.of("flavored"))
      .put("rött vin", Set.of("red"))
      .put("röda", Set.of("red"))
      .put("rosévin", Set.of("rose"))
      .put("rosé", Set.of("rose"))
      .put("portvin", Set.of("port", "fortified"))
      .put("mousserande vin", Set.of("sparkling"))
      .put("montilla", Set.of("montilla", "fortified"))
      .put("sherry", Set.of("sherry", "fortified"))
      .put("vermouth", Set.of("vermouth", "fortified"))
      .put("madeira", Set.of("madeira", "fortified"))
      .put("fruktvin", Set.of("fruit wine"))
      .put("blå stilla", Set.of("blue"))
      .put("blå mousserande", Set.of("blue", "sparkling"))
      .build();

  // TODO: review translations
  private static final Map<String, Set<String>> cleanTypMap = ImmutableMap.<String, Set<String>>builder()
      .put("söt", Set.of("sweet"))
      .put("sött", Set.of("sweet"))
      .put("torr", Set.of("dry"))
      .put("torrt", Set.of("dry"))
      .put("bual", Set.of("bual")) // Grape
      .put("malvoisie/malmsey", Set.of("malvoisie", "malmsey")) // Grape
      .put("sercial", Set.of("sercial")) // Grape
      .put("verdelho", Set.of("verdelho"))
      .put("rosé", Set.of("rose"))
      .put("rött", Set.of("red"))
      .put("röd sött", Set.of("red", "sweet"))
      .put("vitt", Set.of("white"))
      .put("vitt halvtorrt", Set.of("white", "half dry"))
      .put("vitt sött", Set.of("white", "sweet"))
      .put("vitt torrt", Set.of("white", "dry"))
      .put("vitt söt", Set.of("white", "sweet"))
      .put("vitt torr", Set.of("white", "dry"))
      .put("fruktigt & smakrikt", Set.of("fruity", "aromatic"))
      .put("friskt & bärigt", Set.of("fresh", "berry"))
      .put("friskt & fruktigt", Set.of("fresh", "fruity"))
      .put("Lätt & Avrundat", Set.of("light", "rounded"))
      .put("kryddigt & mustigt", Set.of("spicy", "full-bodied"))
      .put("mjukt & bärigt", Set.of("soft", "berry"))
      .put("stramt & nyanserat", Set.of("tight", "nuanced"))
      .put("halvtorr", Set.of("half dry"))
      .put("druvigt & blommigt", Set.of("flowers"))
      .put("fylligt & smakrikt", Set.of("full bodied", "rich"))
      .build();


  public AssortmentAdapter() {}

  public AssortmentAdapter(InputStream assortmentStream) {
    this.assortmentStream = Optional.ofNullable(assortmentStream);
  }

  public Assortment getAssortment() throws DataReadException {
    try {
      Artiklar remoteAssortment = getData()
          .orElseThrow(() -> new DataReadException("No data received."));
      Assortment assortment = new Assortment();
      assortment.setSourceCreateTime(
        LocalDateTime.parse(remoteAssortment.getSkapadTid(), CREATION_TIME_FORMATTER));
      assortment.setItems(remoteAssortment.getArtikel().stream()
        .map(AssortmentAdapter::translateItem)
        .filter((v) -> {
          if (v.isCorrect()) {
            return true;
          } else {
            LOGGER.log(System.Logger.Level.INFO, "Record not parsed, ignored. " + v);
            return false;
          }
        })
        .map(ValueWithError::getValue)
        .map(Optional::get)
        .collect(Collectors.toList()));
      return assortment;
    } catch (JAXBException e) {
      throw new DataReadException(e);
    }
  }

  private static ValueWithError<Item, Map<String, Exception>> translateItem(Artiklar.Artikel remoteItem) {
    Item item = new Item();
    item.setAlcoholPercent(parsePercentString(StringUtils.cleanValue(remoteItem.getAlkoholhalt())));
    item.setVintage(new Vintage(StringUtils.cleanValue(remoteItem.getArgang())));
    item.setName(StringUtils.cleanValue(remoteItem.getNamn()));
    item.setSubName(StringUtils.cleanValue(remoteItem.getNamn2()));
    item.setSupplier(StringUtils.cleanValue(remoteItem.getLeverantor()));
    item.setProducer(StringUtils.cleanValue(remoteItem.getProducent()));
    item.setPrice(new Money(Double.parseDouble(StringUtils.cleanValue(remoteItem.getPrisinklmoms())), Currency.getInstance("SEK")));
    item.setVolumeLitre(Double.parseDouble(StringUtils.cleanValue(remoteItem.getVolymiml()))/1000);
    item.setCountry(swedishToCountryCode.getOrDefault(StringUtils.cleanValue(remoteItem.getUrsprunglandnamn()), CountryCode.UNDEFINED));
    item.setPrimaryProductsText(StringUtils.cleanValue(remoteItem.getRavarorBeskrivning())); // TODO: Might need some processing
    item.setSourceRecord(remoteItem); // TODO: This must be a good format. Json for now.
    item.setProductId("systemet:" + StringUtils.cleanValue(remoteItem.getVarnummer())); // Ignore artikelid and nr for now.
    Set<String> tags = new HashSet<>();
    tags.addAll(cleanVarugrupp(remoteItem.getVarugrupp()));
    tags.addAll(cleanTyp(remoteItem.getTyp()));
    tags.addAll(organic(remoteItem.getEkologisk()));
    tags.addAll(kosher(remoteItem.getKoscher()));
    tags.addAll(ethical(remoteItem.getEtiskt()));
    if (remoteItem.getEtisktEtikett() != null) {
      tags.addAll(Set.of(remoteItem.getEtisktEtikett()));
    }
    item.setTags(tags);
    item.setRegion(ListUtils.of(StringUtils.cleanValue(remoteItem.getUrsprung())));
    /*  Known fields not taken into account:
     * sortimentText
     * sortiment
     * stil - more for beer
     * utgått
     * pant
     * prisPerLiter
     * saljstart
     * slutlev
     * forpackning
     * forslutning
     * provadargang
     * modul - Not used anymore?
     */
    return new ValueWithError.Builder<Item, Map<String, Exception>>().value(item).build();
    }

  private static Set<String> cleanVarugrupp(String varugrupp) {
    return cleanVarugruppMap.getOrDefault(StringUtils.cleanValue(varugrupp.toLowerCase()), new HashSet<String>());
  }

  private static Set<String> cleanTyp(String typ) {
    return cleanTypMap.getOrDefault(StringUtils.cleanValue(typ.toLowerCase()), new HashSet<String>());
  }

  private static Set<String> organic(String ekologisk) {
    return "1".equals(StringUtils.cleanValue(ekologisk)) ? Set.of("organic") : Set.of();
  }

  private static Set<String> kosher(String kosher) {
    return "1".equals(StringUtils.cleanValue(kosher)) ? Set.of("kosher") : Set.of();
  }

  private static Set<String> ethical(String etiskt) {
    return "1".equals(StringUtils.cleanValue(etiskt)) ? Set.of("ethical") : Set.of();
  }

  private static Double parsePercentString(String stringValue) {
    return Double.parseDouble(StringUtils.cleanValue(stringValue).replace("%", ""));
  }

  private Optional<InputStream> getStream() {
    return assortmentStream.or( () -> {
      try {
        URL url = new URL(DATASET_URL);
        URLConnection con = url.openConnection();
        return Optional.of(con.getInputStream());
      } catch (IOException e) {
        LOGGER.log(System.Logger.Level.WARNING, "Cannot get remote assortment from " + DATASET_URL, e);
        return Optional.empty();
      }
    }
    );
  }

  private Optional<Artiklar> unmarshallArtiklar(InputStream stream) {
    try {
      JAXBContext ctx = JAXBContext.newInstance(Artiklar.class);
      Unmarshaller u = ctx.createUnmarshaller();
      return Optional.of((Artiklar) u.unmarshal(stream));
    } catch (JAXBException e) {
      LOGGER.log(System.Logger.Level.ERROR, "Cannot unmarshall Artiklar", e);
      return Optional.empty();
    }
  }

  private Optional<Artiklar> getData() throws JAXBException {
    LOGGER.log(System.Logger.Level.INFO, "Start fetching data.");
    Optional<Artiklar> data = getStream().flatMap(this::unmarshallArtiklar);
    LOGGER.log(System.Logger.Level.INFO, "Done fetching data.");
    return data;
  }
}
