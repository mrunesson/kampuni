package org.linuxalert.kampuni;

import org.junit.jupiter.api.Test;

import java.io.InputStream;

public class AssortmentAdapterTest {

  @Test
  void readFullFileFromSystembolaget() throws Throwable {
    InputStream inputStream = this.getClass().getResourceAsStream("artiklar.xml");
    AssortmentAdapter toTest = new AssortmentAdapter(inputStream);
    toTest.getAssortment();
  }



}
