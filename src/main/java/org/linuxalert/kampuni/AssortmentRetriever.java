package org.linuxalert.kampuni;

import org.linuxalert.kampuni.model.Artiklar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class AssortmentRetriever {

  private static final String DATASET_URL = "http://www.systembolaget.se/Assortment.aspx?Format=Xml";

  public static Artiklar getArtiklar() throws IOException, JAXBException {
    URL url = new URL(DATASET_URL);
    URLConnection con = url.openConnection();
    InputStream is = con.getInputStream();
    JAXBContext ctx = JAXBContext.newInstance(Artiklar.class);
    Unmarshaller u = ctx.createUnmarshaller();
    Artiklar artiklar = (Artiklar) u.unmarshal(is);
    return artiklar;
  }

}
